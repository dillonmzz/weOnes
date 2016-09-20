package cn.com.blueline.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonFilter implements HandlerInterceptor {
	
	// 不过滤的URL
	 private static final String[] IGNORE_URI = {"/login.html", "/loginController/validation",
		 "/weChatProcessController/weChatUserInfo","/meController/meController"};
    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
    	boolean flag = false;
		System.out.println("preHandle");
		String url = request.getRequestURL().toString();
        System.out.println("URL>>>: " + url);
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                System.out.println(s+"该URI不需要拦截");
                break;
            }
        }
        if (!flag) {
           String userId = (String) request.getSession().getAttribute("userName");
            if (userId != null) {
            	flag = true;
            }else{
            	response.sendRedirect("login.html");
            	return false;
            }
        }
        return flag;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");
	}


}
