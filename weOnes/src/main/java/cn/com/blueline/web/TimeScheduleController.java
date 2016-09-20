package cn.com.blueline.web;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.SystemUser;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.service.TimeScheduleService;
import cn.com.blueline.utils.UUIDGenerate;

@Controller
@RequestMapping("/timemgr")
public class TimeScheduleController {
	
	//http://www.aitdog.com/article/JAVA/2016/03/191.shtml
	@Autowired
	private TimeScheduleService service;
	
	
	
	/**
	 * 后台管理系统:
	 * 根据当前用户查询活动列表
	 * @param model
	 * @param request
	 * @param attributes
	 * @return 
	 */
	@RequestMapping(value = "/toaddTimeSchedule", method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
	public String toaddTimeSchedule(Model model,HttpServletRequest request) {
		return "admin/time/add";
	}
	
	
	/**
	 * 后台管理系统
	 * 保存地点(管理员)
	 * @param request
	 * @param activity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson save(HttpServletRequest request,
			TimeSchedule schedule) throws Exception {
		List<TimeSchedule> list = new ArrayList<TimeSchedule>();
		ResultWithJson resultWithJson;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String uuid = UUIDGenerate.getUUID();
			String timeType = request.getParameter("timeType");
			String section = request.getParameter("section");
			String startDates[] = request.getParameterValues("startDate");
			String endDates[] = request.getParameterValues("endDate");
			String startTimes[] = request.getParameterValues("startTime");
			String endTimes[] = request.getParameterValues("endTime");
			
			 SystemUser user = (SystemUser)request.getSession().getAttribute("user");
		      String currentUser = user.getUserName();
			StringBuilder timeDesc = new StringBuilder();
			for (int i = 0; i < Integer.parseInt(section); i++) {
				TimeSchedule timeManagement = new TimeSchedule();
				timeManagement.setId(uuid);
				timeManagement.setEffdt(new Date());
				timeManagement.setStatus("A");
				//timeManagement.setTimeDesc(schedule.getTimeDesc());
				timeManagement.setTimeType(timeType);
				timeManagement.setSection(i + 1);
				timeManagement.setStartDate(format.parse(startDates[i]));
				timeManagement.setStartTime(startTimes[i]);
				timeManagement.setEndTime(endTimes[i]);
				timeDesc.append(startDates[i].toString());
				if (timeType.equals("con")) {
					timeManagement.setEndDate(format.parse(endDates[i]));
					timeDesc.append("-"+endDates[i].toString());
				}
				timeDesc.append(" "+startTimes[i]+"-"+endTimes[i]+" ");
				System.out.println(timeManagement.getTimeDesc());
				timeManagement.setCreateUser(currentUser);
				timeManagement.setCreateTime(new Date());
				list.add(timeManagement);
			}
			for (TimeSchedule timeManagement : list) {
				timeManagement.setTimeDesc(timeDesc.toString());
			}
			service.save(list);
			resultWithJson = new ResultWithJson("0", "保存成功");
			return resultWithJson;
		} catch (Exception e) {
			resultWithJson = new ResultWithJson("1", "保存异常");
			return resultWithJson;
		}

	}

	
	/**
	 * 后台管理系统:
	 * 根据当前用户查询活动列表
	 * @param model
	 * @param request
	 * @param attributes
	 * @return 活动列表json串
	 */
	@RequestMapping(value = "/adminList", method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
	public String adminListGet(Model model,HttpServletRequest request) {
		 SystemUser user = (SystemUser)request.getSession().getAttribute("user");
	      String currentUser = user.getUserName();
		List<TimeSchedule> timeSchedule =  service.queryTimeScheduleByUser(currentUser);
		model.addAttribute("list", timeSchedule);
		return "admin/time/list";
	}
	
	
	
	
	
	
	
}
