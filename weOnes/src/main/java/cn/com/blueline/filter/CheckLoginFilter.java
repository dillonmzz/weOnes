package cn.com.blueline.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import cz.mallat.uasparser.UserAgentInfo;
import cn.com.blueline.utils.PublicIPUtil;
import cn.com.blueline.utils.UserAgentUtil;

public class CheckLoginFilter extends OncePerRequestFilter implements
		Serializable {
	private static final long serialVersionUID = -3694035267892765657L;

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String[] notFilter = { "/login.html", "/css", "/js", "/fonts",
				"/images", "/pay", "/ueditor", "/loginController/validation",
				"wxpay/successfulPayProcess",
				"/weChatProcessController/weChatUserInfo", "/meController",
				"/fileUpload/upload", "/fileUpload/uploadPhp",
				"activity/saveActivity", "activity/adminList",
				"activity/updateActivityStateById", "/wxPayOrder","/productphp","/timephp","/productcollectphp","/uploads/",
				"/goodsCategory","/goodsAttr","/goods","/pc"};
		boolean flag = true;

		String url = request.getRequestURL().toString();
		
		//获取访问者的IP
		String publicIp = PublicIPUtil.getIpAddress(request);
		
		//获取访问者的设备
		String userAgent = request.getHeader("User-Agent");
		System.out.println("UE="+userAgent);
		UserAgentInfo userAgentInfo = UserAgentUtil.getUserAgent(userAgent);
		//System.out.println("操作系统名称：" + userAgentInfo.getOsFamily());
		System.out.println("操作系统：" + userAgentInfo.getOsName());//
		System.out.println("设备类型：" + userAgentInfo.getDeviceType());
		//System.out.println("浏览器名称：" + userAgentInfo.getUaFamily());//
		//System.out.println("浏览器版本：" + userAgentInfo.getBrowserVersionInfo());//
		System.out.println("浏览器信息:" + userAgentInfo.getUaName());
		System.out.println("访问类型：" + userAgentInfo.getType());
		for (String s : notFilter) {
			if (url.contains(s)) {
				flag = false;
				break;
			}
		}
		HttpSession session = request.getSession();

		String sessionId = (String) session.getAttribute("sessionId");

		String openId = (String) session.getAttribute("openId");
		System.out.println("sessionId:" + session.getId());
		if (flag) {
			System.out.println(publicIp+"该请求需要拦截验证:" + url);
			if ((sessionId != null) || (openId != null)) {
				System.out.println("用户已经登录");
				filterChain.doFilter(request, response);
			} else {
				response.sendRedirect("/weOnes/login.html");
				return;
			}
		} else {
			System.out.println(publicIp+"该请求不拦截:" + url);
			filterChain.doFilter(request, response);
		}
	}
}