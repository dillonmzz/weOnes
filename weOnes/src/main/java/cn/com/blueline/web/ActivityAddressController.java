package cn.com.blueline.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.ActivityAddress;
import cn.com.blueline.service.ActivityAddressService;

@Controller
@RequestMapping("/activityAddr")
public class ActivityAddressController {

	@Autowired
	private ActivityAddressService addressService;
	
	/**
	 * 后台管理系统
	 * 保存地点(管理员)
	 * @param request
	 * @param activity
	 * @return
	 */
	@RequestMapping(value = "/saveAddr", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson saveAddress(
			HttpServletRequest request, ActivityAddress activityAddress){
		ResultWithJson resultWithJson;
		try {
			 resultWithJson = new ResultWithJson("0", "保存成功");
			return resultWithJson;
		} catch (Exception e) {
			resultWithJson = new ResultWithJson("1", "保存异常");
			return resultWithJson;
		}

	}

	
	
	/**
	 * 后台管理系统:
	 * 查询活动列表
	 * @param model
	 * @param request
	 * @param attributes
	 * @return 活动列表json串
	 */
	@RequestMapping(value = "/adminList", method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson adminListPost() {
		ResultWithJson<ActivityAddress> resultWithJson;
		// 获取列表页
		List<ActivityAddress> list = addressService.queryActivityAddressBycreateUser("MZZ");
		resultWithJson = new ResultWithJson<ActivityAddress>("0", list.size()+"",list);
		return resultWithJson;
	}
	
	/**
	 * 后台管理系统
	 * 根据activityId修改状态
	 * 0:未发布
	 * 1:已发布 (管理员)
	 * 9:删除该活动 (管理员),其余角色将无法查看到该活动
	 * @param request
	 * @return
	 
	@RequestMapping(value = "/updateActivityStateById", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson updateActivityStateById(HttpServletRequest request) {
		String activityId = request.getParameter("activityId");
		String state = request.getParameter("state");
		ResultWithJson json = new ResultWithJson();
		int rowNum = acService.updateActivityStateById(activityId,state);
		if (rowNum > 0) {
			json.setErrorCode("0");
			json.setMsg("SUCCESS");
		} else {
			json.setErrorCode("1");
			json.setMsg("FAIL");
		}
		return json;
	}*/
	
	
}
