package cn.com.blueline.web.php;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.SystemUser;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.service.TimeScheduleService;
import cn.com.blueline.utils.UUIDGenerate;

@Controller
@RequestMapping("/timephp")
public class TimePHP {
	
	@Autowired
	private TimeScheduleService service;
	
	
	/**
	 * 后台管理系统:
	 * 根据当前用户查询时间列表
	 * @param model
	 * @param request
	 * @param attributes
	 * @return 时间列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public  ResultWithJson<TimeSchedule> list(HttpServletRequest request) {
		String user = request.getParameter("user");
		List<TimeSchedule> list =  service.queryTimeScheduleByUser(user);
		if (list != null && list.size() > 0){
			return new ResultWithJson<TimeSchedule>("0", ""+list.size(), list);
		}else{
			return new ResultWithJson("1", "error");
		}
		
	}
	
	
	
	/**
	 * 后台管理系统
	 * 保存时间列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson save(HttpServletRequest request,
			TimeSchedule schedule) throws Exception {
		System.out.println(schedule);
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
		    String currentUser = request.getParameter("createUser");
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
	 * PHP保存时间
	 * 保存时间列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/savephp", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson savephp(HttpServletRequest request,@RequestBody Map<String, String> map) throws Exception {
		
		List<TimeSchedule> list = new ArrayList<TimeSchedule>();
		ResultWithJson resultWithJson;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String uuid = UUIDGenerate.getUUID();
			String timeType = map.get("timeType");
			String section = map.get("section");
		    String currentUser = map.get("createUser");
			StringBuilder timeDesc = new StringBuilder();
			for (int i = 1; i <=Integer.parseInt(section); i++) {
				TimeSchedule timeManagement = new TimeSchedule();
				timeManagement.setId(uuid);
				timeManagement.setEffdt(new Date());
				timeManagement.setStatus("A");
				timeManagement.setTimeType(timeType);
				timeManagement.setSection(i);
				timeManagement.setStartDate(format.parse(map.get("startDate"+i)));
				timeManagement.setStartTime(map.get("startTime"+i));
				timeManagement.setEndTime(map.get("endTime"+i));
				timeDesc.append(map.get("startDate"+i).toString());
				if (timeType.equals("con")) {
					timeManagement.setEndDate(format.parse(map.get("endDate"+i)));
					timeDesc.append("-"+map.get("endDate"+i).toString());
				}
				timeDesc.append(" "+map.get("startTime"+i)+"-"+map.get("endTime"+i)+" ");
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

	
	
	
	
	
	
	
	
	
}
