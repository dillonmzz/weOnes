package cn.com.blueline.web.me;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.WeChatUser;
import cn.com.blueline.service.WeChatUserService;

@Controller
@RequestMapping("/myController")
public class MyController {
	
	@Autowired
	private WeChatUserService weChatUserService;

	/*
	 * 首页跳转我的页面 路由转发
	 */
	@RequestMapping(value = "/v2",method = RequestMethod.GET)
	public String v2(Model model, HttpServletRequest request) {
		return "app/me/me";
	}
	
	
	/**
	 * 跳转至个人中心
	 * @param request
	 * @return 用户信息
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request){
		String openId = (String) request.getSession().getAttribute("openId");
		//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		return "app/me/myInfo";
	}
	
	/**
	 * 根据当前用户查看用户信息
	 * @param request
	 * @return 用户信息
	 */
	@RequestMapping(value = "/myinfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultWithJson<WeChatUser> myinfo(HttpServletRequest request){
		try {
			String openId = (String) request.getSession().getAttribute("openId");
			//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
			WeChatUser user = weChatUserService.queryById(openId);
			if(user!=null){
				return new ResultWithJson<WeChatUser>("0", "ok",user);
			}else {
				return new ResultWithJson<WeChatUser>("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson<WeChatUser>("1", "error");
		}
		
	}

}
