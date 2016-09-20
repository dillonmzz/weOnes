package cn.com.blueline.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;


import cn.com.blueline.entity.PlaceDevice;
import cn.com.blueline.service.ProductService;

@Component
public class StartupListener implements ServletContextAware{
	
	@Autowired
	private ProductService service;

	@Override
	public void setServletContext(ServletContext servletContext) {
		System.out.println("*********************容器加载时触发了该方法*********************");
		//查询设备信息并以map集合的方式放入servletContext中
		List<PlaceDevice> list = service.findAllPlaceDevice();
		System.out.println(list.size());
		Map<String, Object> deviceMap = new HashMap<String, Object>();
		for (PlaceDevice placeDevice : list) {
			deviceMap.put(placeDevice.getPlaceDevice(), placeDevice.getName());
		}
		servletContext.setAttribute("device", deviceMap);
		
		
	}

}
