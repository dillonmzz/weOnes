package cn.com.blueline.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.MyFavorite;
import cn.com.blueline.entity.WxPayOrder;
import cn.com.blueline.service.ActivityService;
import cn.com.blueline.service.MyFavoriteService;


@Controller
@RequestMapping("/myFavorite")
public class MyFavoriteController {
	
	
	@Autowired
	private MyFavoriteService myFavoriteService;
	
	@Autowired
	private ActivityService activityService;

	/*
	 * 查询我的收藏列表页面
	 */
	@RequestMapping(value="list",method = RequestMethod.GET)
	public String orderList(Model model, HttpServletRequest request) {
		String openId = (String)request.getSession().getAttribute("openId");
		openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		System.out.println("当前用户("+openId+")正在查看我的收藏列表");
		
		List<MyFavorite> favorites = myFavoriteService.queryMyFavoriteListByOpenId(openId);
		if (favorites == null) {
			model.addAttribute("nullInfo", "您还没有关注");
		} else {
			model.addAttribute("list", favorites);
		}
		return "myFavorite";
	}
	
	/*
	 * 根据activityId删除收藏记录
	 */
	@RequestMapping(value = "/delMyFavoriteByActivityId", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson delMyFavoriteByActivityId(HttpServletRequest request) {
		ResultWithJson json = null;
		try {
			String openId = (String) request.getSession().getAttribute("openId"); 
			openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
			String activityId = request.getParameter("activityId");
			System.out.println("当前用户("+openId+")正在移除收藏("+activityId+")");
			if(openId ==null || activityId == null){
				json = new ResultWithJson();
				json.setErrorCode("1");
				json.setMsg("SUCCESS");
				return json;
			}
			int rowNum = activityService.favoriteOrCancelActivity(openId, Long.parseLong(activityId), 0);
			if (rowNum > 0) {
				json = new ResultWithJson();
				json.setErrorCode("0");
				json.setMsg("SUCCESS");
			} else {
				json = new ResultWithJson();
				json.setErrorCode("1");
				json.setMsg("FAIL");
			}
			return json;
		} catch (Exception e) {
			System.out.println("移除收藏过程出现异常,可能是当前会话中(session)无openId");
			json = new ResultWithJson();
			json.setErrorCode("1");
			json.setMsg("FAIL");
			return json;
		}
		
	}

}
