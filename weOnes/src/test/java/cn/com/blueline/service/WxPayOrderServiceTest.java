package cn.com.blueline.service;
/*作者:Dillon
 *日期:2016年5月31日
 **/
 
 import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.blueline.entity.Activity;
import cn.com.blueline.entity.WxPayOrder;

 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class WxPayOrderServiceTest {
	 
	 @Autowired
	 private UnifiedOrderService  unifiedOrderService;
	 
	 /*
	 @Test
	 public void testSaveActivity(){
	       WxPayOrder wxPayOrder = new WxPayOrder();
	       wxPayOrder.setTransactionId("4000482001201606137206594300");
	       wxPayOrder.setOutTradeNo("20160613041825057");
	       wxPayOrder.setActivityId(1071L);//TODO 活动ID写死
	       wxPayOrder.setOpenId("o9bmjsy3prTRGYuK6mqxm21E_vdg");
	       wxPayOrder.setUserPhone("15810586386"); //TODO 活动ID写死
	       wxPayOrder.setUserName("dillonmz");//TODO 写死
	       wxPayOrder.setQuantity(Integer.parseInt("1")); //TODO 写死
	       wxPayOrder.setTotalFee(11);
	       wxPayOrder.setTimeEnd(new Date(System.currentTimeMillis()));
	       wxPayOrder.setState(1);
	       wxPayOrder.setRemark("终极好评");
	       
	       int rowNum = unifiedOrderService.saveUnifiedOrder(wxPayOrder);
	       if(rowNum>0){
	    	   System.out.println("保存成功");
	       }else{
	    	   System.out.println("保存失败");
	       }
	    }
	    
	 @Test
	 public void findWxPayOrderByOutTradeNo(){
	    	String outTradeNo = "20160615104316293";
	 
	    	WxPayOrder order = unifiedOrderService.findWxPayOrderByOutTradeNo(outTradeNo);
	    	int rowNum=  unifiedOrderService.updateByOutTradeNo("4000482001201606137206594300", outTradeNo);
	    	System.out.println(order.toString());
	    	System.out.println(rowNum);
	 }*/
	 
	 @Test
	 public void testQueryOrderByOpenId(){
	    	String openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
	    	List<WxPayOrder> orders = unifiedOrderService.queryOrdersByOpenId(openId);
	    	System.out.println("订单列表"+orders.size());
	    	for (WxPayOrder wxPayOrder : orders) {
			//	System.out.println(wxPayOrder.getActivity().getStartTime());
			}
	 }
	 
	    
	 
	 
}
