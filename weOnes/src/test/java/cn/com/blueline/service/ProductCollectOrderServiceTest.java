package cn.com.blueline.service;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.blueline.entity.ProductCollectOrder;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.utils.UUIDGenerate;


/**
 * @author Zhaozhi
 */
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class ProductCollectOrderServiceTest {
	 
	 @Autowired
	 private ProductCollectService productCollectService;
	 
	 
	 
	 
	 @Test
	 public void queryTimeByDate() throws Exception{
		 String openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		 //ProductCollectOrder o = productCollectService.queryOrderById("20160803103059160");
		 List<ProductCollectOrder> orders = productCollectService.queryOrdersByOpenId(openId);
		 System.out.println(orders.size());
		// System.out.println(o.toString());
	}
	 
	
	

}
