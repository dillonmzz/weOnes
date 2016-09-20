package cn.com.blueline.web;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cn.com.blueline.dto.WxNotifyReturnData;
import cn.com.blueline.dto.WxPayReturnData;
import cn.com.blueline.dto.WxPaySentData;
import cn.com.blueline.entity.Activity;
import cn.com.blueline.entity.ProductCollectOrder;
import cn.com.blueline.entity.WxPayOrder;
import cn.com.blueline.service.ActivityService;
import cn.com.blueline.service.ProductCollectService;
import cn.com.blueline.service.UnifiedOrderService;
import cn.com.blueline.utils.CommonUtils;
import cn.com.blueline.utils.WxPayConfig;
import cn.com.blueline.utils.WxSign;

@Controller
@RequestMapping("/wxpay")
public class WxJsAPI {
	
	
	@Autowired
	private UnifiedOrderService unifiedOrderService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ProductCollectService productCollectService ;
	
	
	
	 @RequestMapping(value = "/orderprocess", method = RequestMethod.GET)
	 public ModelAndView orderProcess(Model model,HttpServletRequest request,RedirectAttributes attr) {
		 System.out.println("微信支付控制器");
		//判断是否微信授权
		 String openId = (String)request.getSession().getAttribute("openId");
		// openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		if(openId==null){//无openId未授权则跳转至微信授权页面
			 System.out.println("当前用户无授权微信登录,无法下单,正在跳转至微信授权页面");
			 return new ModelAndView("redirect:"+WxPayConfig.OAUTHURL);
		 }else{
			 //session:有openId 已授权,继续下单
			 String activityId = request.getParameter("activityId");
			 System.out.println("商品ID:"+activityId);
			 Activity activity = activityService.getActivityByActivityId(Long.parseLong(activityId));
	    	 request.getSession().setAttribute("activity",activity);
	    	 return new ModelAndView("redirect:/pay/activityOrderPay.jsp");
		 }
		 
		 
         
	 }
	 
	 /*
	 * 1、获取用户下单数量计算金额
	 * 2、将下单数据返回下单页面"activityOrderPay.jsp"
	 * 3、获取用户下单时提交的数据并保存到数据库中,状态设置为0:未支付状态
	 * 
	 */
	 @RequestMapping(value = "/orderBeforProcess",method = RequestMethod.POST,
	 		 		produces = {"application/json;charset=UTF-8"})
	 public @ResponseBody WxPayReturnData orderBeforProcess(HttpServletRequest request, HttpServletResponse response) {
		 	 String openId = (String)request.getSession().getAttribute("openId");
			 //1、获取用户下单数量计算金额
			 String quantity = request.getParameter("shuliang");
			 String activityId = request.getParameter("activityId");
			 System.out.println("订单提交的数据"+quantity+activityId);
			 Activity activity = activityService.getActivityByActivityId(Long.parseLong(activityId));
			int Total_fee = activity.getNewPrice().multiply(new BigDecimal(quantity)).multiply(new BigDecimal(WxPayConfig.WXAMOUNTCONVERSION)).intValue();
			System.out.println("用户("+openId+")正在进行订单处理,需支付:"+Total_fee+"分");
			 //2、将下单数据返回下单页面"activityOrderPay.jsp"
			 String outTradeNo = CommonUtils.getCurrentTimetoString();
			 String nonceStr = WxSign.getNonceStr();
			 WxPaySentData data = new WxPaySentData();
			 data.setAppid(WxPayConfig.APPID);
			 data.setAttach("weixinpay");
	         data.setBody(activity.getTitle());// 商品名称
	         data.setMch_id(WxPayConfig.MCHID);
	         data.setNotify_url(WxPayConfig.NOTIFYURL);
	         data.setOut_trade_no(outTradeNo);//商户系统内部的订单号,32个字符内、可包含字母
	         data.setTotal_fee(Total_fee);//单位：分
	         data.setTrade_type("JSAPI");
	         data.setSpbill_create_ip(request.getRemoteAddr());
	         data.setOpenid(openId);
	         data.setNonce_str(nonceStr);//随机字符串，不长于32位
	         String returnXml = unifiedOrderService.unifiedOrder(data,WxPayConfig.KEY);
	         // XML 转 Object
	         WxPayReturnData unifiedOrderResponseData = new WxPayReturnData();
	         XStream xs = new XStream(new DomDriver());
	         xs.alias("xml", WxPayReturnData.class);
	         unifiedOrderResponseData = (WxPayReturnData) xs.fromXML(returnXml);
	         String timeStamp = WxSign.getTimeStamp();
	         System.out.println("调用统一下单API微信返回的结果"+unifiedOrderResponseData.toString());
	         SortedMap<Object,Object> signMap = new TreeMap<Object,Object>();
	         signMap.put("appId", unifiedOrderResponseData.getAppid()); 
	         signMap.put("timeStamp", timeStamp);
	         signMap.put("nonceStr", unifiedOrderResponseData.getNonce_str());
	         signMap.put("package", "prepay_id="+unifiedOrderResponseData.getPrepay_id());
	         signMap.put("signType", "MD5");
	         unifiedOrderResponseData.setSign(WxSign.createSign(signMap,WxPayConfig.KEY));
	         unifiedOrderResponseData.setTimeStamp(timeStamp);
	         System.out.println("返回结果再次签名结果数据:"+unifiedOrderResponseData.toString());
	         if(unifiedOrderResponseData.getReturn_code().equals("SUCCESS" )
	        		 && unifiedOrderResponseData.getReturn_msg().equals("OK")
	        		 && unifiedOrderResponseData.getResult_code().equals("SUCCESS")){
	        	//3、获取用户下单时提交的数据并保存到数据库中,状态设置为0:未支付状态
				 if(!saveWxpayOrder(request,activityId,quantity,outTradeNo,openId,Total_fee)){
					 return null;
				 }
	        	 
	        	 return unifiedOrderResponseData;
	         }else{
	        	 //model.addAttribute("payErrorMsg", "调用统一下单接口失败,请返回重新提交下单");
	        	 //return new ModelAndView("redirect:/pay/payError.jsp");
	        	 return null;
	         }
		 
	 }
	 
	 //获取用户下单时提交的数据并保存到数据库中,状态设置为0:未支付状态
	 private boolean saveWxpayOrder(HttpServletRequest request,String activityId,String quantity,String outTradeNo,String openId,
			 int Total_fee){
		 boolean flag = true;
		 
		 String userName = request.getParameter("userName");
		 String userPhone = request.getParameter("userPhone");
		 String userAddr = request.getParameter("userAddr");
		 String remarks  = request.getParameter("remarks");
		 
		 WxPayOrder order = new WxPayOrder();
		 order.setTransactionId(outTradeNo);
		 order.setOutTradeNo(outTradeNo);
		 order.setActivityId(Long.parseLong(activityId));
		 order.setOpenId(openId);
		 order.setUserName(userName);
		 order.setUserPhone(userPhone);
		 order.setUserAddr(userAddr);
		 order.setQuantity(Integer.parseInt(quantity));
		 order.setTotalFee(new BigDecimal(Total_fee));
		 order.setState(0);//TODO 0为未支付,待更新为1 已支付
		 order.setTimeEnd(new Date(System.currentTimeMillis()));
		 order.setRemark(remarks);
		 int rowNum = unifiedOrderService.saveUnifiedOrder(order);
		 if(rowNum>0){
			 flag = true;
		 }else{
			 flag = false;
		 }
		 return flag;
	 }
	 
	 
	 /*
	 *  1、用户在线付款了,并且付款成功了,微信服务器会主动调用该方法,会携带一些参数(XML格式的字符串)
	 *  2、获取XML中的transaction_id、out_trade_no值,根据 out_trade_no查询出订单记录(唯一数据),
	 *     并update transaction_id字段 和state字段(1:表示已付款)
	 *	3、修改成功后,通知微信服务器不要再发通知了
	 */
	 @RequestMapping(value = "/successfulPayProcess",method = RequestMethod.POST)
	 public void successfulPayProcess(HttpServletRequest request, HttpServletResponse response) {
		 System.out.println("用户支付完成了.....update we_user_order transaction_id and state(1:表示已付款)");
		 InputStream inStream = null;
		 ByteArrayOutputStream outSteam = null;
		 String resultXml = null;
		 String return_code = null;
   	     String return_msg = null; 
		 try {
			 inStream = request.getInputStream();
			 outSteam = new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];
			 int len = 0;
			 while ((len = inStream.read(buffer)) != -1) {
			 	outSteam.write(buffer, 0, len);
			 }
			 outSteam.close();
			 inStream.close();
			 resultXml  = new String(outSteam.toByteArray(),"utf-8");
			 System.out.println("付款成功后微信发起通知XML数据:\n"+resultXml);
			 //XML 转Object
			 XStream xs = new XStream(new DomDriver());
	         xs.alias("xml", WxNotifyReturnData.class);
	         WxNotifyReturnData notifyReturnData = (WxNotifyReturnData) xs.fromXML(resultXml);
	         System.out.println("支付成功后微信回调XML转javabean数据\n"+notifyReturnData.toString());
	         //收到微信支付通知时，先检查业务数据状态，判断该通知是否已经处理过
	         if(notifyReturnData.getResult_code().equals("SUCCESS")){
	        	  String openId  = notifyReturnData.getOpenid();
	        	  String transactionId = notifyReturnData.getTransaction_id();
	        	  String outTradeNo = notifyReturnData.getOut_trade_no();
	        	  String attach = notifyReturnData.getAttach();
	        	  System.out.println("微信支付回调函数返回的attach="+attach);
	        	  if(attach.equals("weixinpay")){ //如果是第一版
	        		  System.out.println("进入第一版代码");
	        		  WxPayOrder order =  unifiedOrderService.findWxPayOrderByOutTradeNo(outTradeNo);
		        	  if(order!=null && order.getState()==0){ //未处理
		        		 int rowNum=  unifiedOrderService.updateByOutTradeNo(transactionId, outTradeNo);
		        		 if(rowNum>0){
			        		  return_code = "SUCCESS";
				        	  return_msg = "OK";
				        	  String  returnToWx = "<xml><return_code><![CDATA[" + return_code+ "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
				        	  System.out.println("支付成功了!不用再发信息了"+returnToWx);
				        	  response.getWriter().write(returnToWx);
			        	  }else{
			        		  return_code = "FAIL";
				        	  return_msg = "支付失败";
				        	  String  returnToWx = "<xml><return_code><![CDATA[" + return_code+ "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
				        	  System.out.println("支付失败了");
				        	  response.getWriter().write(returnToWx);
			        	  }
		        	  }
	        	  }else{//如果是第二版
	        		  System.out.println("进入第二版本版代码");
	        		  ProductCollectOrder order = productCollectService.queryOrderById(outTradeNo);
	        		  if(order!=null && order.getState()==0){ //未处理
			        		 int rowNum=  productCollectService.payOrder(outTradeNo,openId, transactionId);
			        		 if(rowNum>0){
				        		  return_code = "SUCCESS";
					        	  return_msg = "OK";
					        	  String  returnToWx = "<xml><return_code><![CDATA[" + return_code+ "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
					        	  System.out.println("支付成功了!不用再发信息了"+returnToWx);
					        	  response.getWriter().write(returnToWx);
				        	  }else{
				        		  return_code = "FAIL";
					        	  return_msg = "支付失败";
					        	  String  returnToWx = "<xml><return_code><![CDATA[" + return_code+ "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
					        	  System.out.println("支付失败了");
					        	  response.getWriter().write(returnToWx);
				        	  }
			        	  }
	        	  }
	        	  
	        	  
	        	  
	         }else{
	        	  return_code = "FAIL";
	        	  return_msg = "支付失败";
	        	  String  returnToWx = "<xml><return_code><![CDATA[" + return_code+ "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
	        	  System.out.println("支付失败了");
	        	  response.getWriter().write(returnToWx);
	         }
		 } catch (Exception e) {
			 return_code = "FAIL";
       	  	 return_msg = "支付失败";
       	  	 String  returnToWx = "<xml><return_code><![CDATA[" + return_code+ "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
       	  	 System.out.println("支付失败了,异常处理");
       	  //response.getWriter().write(returnToWx);
		 }
	 }
	
	
}
