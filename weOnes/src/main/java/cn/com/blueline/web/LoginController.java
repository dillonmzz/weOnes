package cn.com.blueline.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.blueline.entity.SystemUser;
import cn.com.blueline.service.SystemUserService;


@Controller
@RequestMapping("/loginController")
public class LoginController {

	
	@Autowired private SystemUserService  userService;
	
	
	/**
	 * TODO
	 * 后台管理系统
	 * 修改活动(管理员)
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/validation", method = RequestMethod.POST)
	public ModelAndView validation(HttpServletRequest request,HttpServletResponse response){
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		SystemUser dbuser = userService.queryByUserName(userName);
		System.out.println("dbuser="+dbuser);
		if(dbuser!=null && password.equals(dbuser.getPassword())){
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			request.getSession().setAttribute("sessionId", sessionId);
			request.getSession().setAttribute("user", dbuser);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("userName", userName);
			System.out.println("认证成功,跳转至iframe.jsp");
			return new ModelAndView("redirect:/iframe.jsp");
		}else{
			System.out.println("用户验证失败,跳转至login.html");
			return new ModelAndView("redirect:/login.html");
		}
		
	}
	
}
