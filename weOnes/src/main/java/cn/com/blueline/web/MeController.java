package cn.com.blueline.web;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/meController")
public class MeController {

	private Log LOG = LogFactory.getLog(this.getClass());

	
	/*
	 * 首页跳转值我的页面 路由转发
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String orderList(Model model, HttpServletRequest request) {
		return "me";
	}
	

}
