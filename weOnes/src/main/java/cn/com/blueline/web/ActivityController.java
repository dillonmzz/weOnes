package cn.com.blueline.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.Activity;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.service.ActivityService;
import cn.com.blueline.service.TimeScheduleService;
import cn.com.blueline.utils.UUIDGenerate;
import cn.com.blueline.utils.WxPayConfig;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	  @Autowired
	  private ActivityService acService;

	  @Autowired
	  private ActivityService activityService;

	  @RequestMapping(value={"/list"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  public String list(Model model, HttpServletRequest request)
	  {
	    List list = this.activityService.getActivityList();
	    System.out.println("活动记录数据:" + list.size());
	    model.addAttribute("list", list);
	    return "activityList";
	  }

	  @RequestMapping(value={"/activityDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  public String activityDetailsById(Model model, HttpServletRequest request)
	  {
	    System.out.println("进入活动详情页面");

	    Long activityId = Long.valueOf(Long.parseLong(request.getParameter("activityId")));
	    System.out.println("活动ID:" + activityId);

	    Activity activity = this.activityService.getActivityByActivityId(activityId);

	    String openId = (String)request.getSession().getAttribute("openId");
	    System.out.println("当前openid:" + openId);
	    int state = this.activityService.queryStateByOpenIdAndActivityId(openId, 
	      activityId);
	    model.addAttribute("activityState", Integer.valueOf(state));
	    model.addAttribute("activity", activity);
	    return "activityDetails";
	  }

	  @RequestMapping(value={"/favorite"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json;charset=UTF-8"})
	  @ResponseBody
	  public ResultWithJson favoriteActivity(HttpServletRequest request)
	  {
	    String activityId = request.getParameter("activityId");
	    String openId = (String)request.getSession().getAttribute("openId");
	    String state = request.getParameter("state");
	    System.out.println("状态变为" + state);
	    ResultWithJson json = new ResultWithJson();

	    int rowNum = this.acService.favoriteOrCancelActivity(openId, 
	      Long.valueOf(Long.parseLong(activityId)), Integer.valueOf(Integer.parseInt(state)));
	    if (rowNum > 0) {
	      json.setErrorCode("0");
	      json.setMsg("SUCCESS");
	    } else {
	      json.setErrorCode("1");
	      json.setMsg("FAIL");
	    }
	    return json;
	  }
	  @RequestMapping(value={"/saveActivity"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
	  @ResponseBody
	  public ResultWithJson saveActivity(HttpServletRequest request, Activity activity) {
	    System.out.println("保存的数据activity" + activity.toString());
	    ResultWithJson resultWithJson;
	    try {
	      System.out.println("进入保存activity方法");

	      String newPriceY = request.getParameter("newPrice");
	      BigDecimal newPriceF = new BigDecimal(newPriceY).multiply(
	        new BigDecimal(100)).setScale(0);
	      String oldPriceY = request.getParameter("oldPrice");
	      BigDecimal oldPriceF = new BigDecimal(oldPriceY).multiply(
	        new BigDecimal(100)).setScale(0);
	      activity.setNewPrice(newPriceF);
	      activity.setOldPrice(oldPriceF);

	      int rowNum = this.acService.saveActivity(activity);
	      if (rowNum > 0)
	        resultWithJson = new ResultWithJson("0", "success");
	      else {
	        resultWithJson = new ResultWithJson("1", "error");
	      }
	      return resultWithJson;
	    } catch (Exception e) {
	      resultWithJson = new ResultWithJson("1", "保存activity数据时出错");
	    }return resultWithJson;
	  }

	  @RequestMapping(value={"/adminList"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  public ModelAndView adminList(Model model, HttpServletRequest request, RedirectAttributes attributes)
	  {
	    List list = this.activityService.getActivityList();
	    System.out.println("后台管理系统查询活动列表" + list.size());

	    request.getSession().setAttribute("list", list);
	    return new ModelAndView("redirect:/activityAdmin/activityList.jsp");
	  }

	  @RequestMapping(value={"/adminList"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
	  @ResponseBody
	  public ResultWithJson adminListPost(Model model, HttpServletRequest request, RedirectAttributes attributes)
	  {
	    List list = this.activityService.getActivityList();
	    System.out.println("后台管理系统查询活动列表" + list.size());
	    ResultWithJson resultWithJson = new ResultWithJson("0", ""+list.size(), list);
	    return resultWithJson;
	  }

	  @RequestMapping(value={"/updateActivityStateById"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
	  @ResponseBody
	  public ResultWithJson updateActivityStateById(HttpServletRequest request)
	  {
	    String activityId = request.getParameter("activityId");
	    String state = request.getParameter("state");
	    ResultWithJson json = new ResultWithJson();
	    int rowNum = this.acService.updateActivityStateById(activityId, state);
	    if (rowNum > 0) {
	      json.setErrorCode("0");
	      json.setMsg("SUCCESS");
	    } else {
	      json.setErrorCode("1");
	      json.setMsg("FAIL");
	    }
	    return json;
	  }

	  @RequestMapping(value={"/updateActivityById"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ModelAndView updateActivityById(HttpServletRequest request)
	  {
	    Long activityId = Long.valueOf(Long.parseLong(request.getParameter("activityId")));

	    Activity activity = this.activityService.getActivityByActivityId(activityId);
	    Map model = new HashMap();
	    model.put("activity", activity);
	    return new ModelAndView("/activityAdmin/activityEdit.jsp", model);
	  }
	  
	  /**
		 * TODO
		 * 后台管理系统
		 * 修改活动(管理员)
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/toUpdateActivityById", method = RequestMethod.POST)
		public @ResponseBody ResultWithJson<Activity> updateActivityById(HttpServletRequest request,RedirectAttributes redirectAttributes){
			ResultWithJson<Activity> resultWithJson = null;
			Long activityId = Long.parseLong(request.getParameter("activityId"));
			System.out.println("修改活动方法");
			Activity activity = activityService.getActivityByActivityId(activityId);
			List<Activity> list = new ArrayList<Activity>();
			list.add(activity);
			resultWithJson = new ResultWithJson<Activity>("0", "ok", list);
			System.out.println("activity:"+activity.toString());
			return resultWithJson;
		}
}
