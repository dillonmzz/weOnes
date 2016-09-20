package cn.com.blueline.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import cn.com.blueline.dao.WxPayOrderDao;
import cn.com.blueline.dto.WxPaySentData;
import cn.com.blueline.entity.WxPayOrder;
import cn.com.blueline.service.UnifiedOrderService;
import cn.com.blueline.utils.CommonUtils;
import cn.com.blueline.utils.WxPayConfig;
import cn.com.blueline.utils.WxSign;
/*作者:Dillon
 *日期:2016年6月12日
 **/
import cn.com.blueline.web.WxJsAPI;

@Service
public class UnifiedOrderServiceImpl implements UnifiedOrderService {
	
	@Autowired
	WxPayOrderDao wxPayOrderDao;

	@Override
	public String unifiedOrder(WxPaySentData data, String key) {
		  //统一下单支付
	   String returnXml = null;
	   try {
	      //生成sign签名
	      SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
	      parameters.put("appid", data.getAppid()); 
	      parameters.put("attach", data.getAttach());
	      parameters.put("body", data.getBody());
	      parameters.put("mch_id", data.getMch_id());
	      parameters.put("nonce_str", data.getNonce_str());
	      parameters.put("notify_url", data.getNotify_url());
	      parameters.put("out_trade_no", data.getOut_trade_no());
	      parameters.put("total_fee", data.getTotal_fee());
	      parameters.put("trade_type", data.getTrade_type());
	      parameters.put("spbill_create_ip", data.getSpbill_create_ip());
	      parameters.put("openid", data.getOpenid());
	      //对当前设置的参数进行sign签名
	      String sign = WxSign.createSign(parameters, key);
	      System.out.println("sign签名:"+sign);
	      data.setSign(sign);
	      //object转xml格式
	      XStream xs = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_", "_")));
	      xs.alias("xml", WxPaySentData.class);
	      String xml = xs.toXML(data);
	      System.out.println("统一下单请求xml为:\n"+xml);
	      //发送接口请求
	     // HttpClientUtils util = HttpClientUtil.getInstance();
          returnXml = CommonUtils.sendPostRequest(WxPayConfig.UNIFIEDREQURL,xml,WxPayConfig.CHARSET);
          System.out.println("统一下单返回xml为:\n"+returnXml);
	   } catch (Exception e) {
           e.printStackTrace();
       }    
		return returnXml;
	}

	@Override
	@Transactional
	public int saveUnifiedOrder(WxPayOrder wxPayOrder) {
		int rowNum = wxPayOrderDao.saveWxPayOrder(wxPayOrder);
		return rowNum;
	}
	
	@Override
	public WxPayOrder findWxPayOrderByOutTradeNo(String outTradeNo){
		
		return wxPayOrderDao.queryByOutTradeNo(outTradeNo);
	}
	
	@Override
	@Transactional
	//public int updateByOutTradeNo(String transactionId, Integer state,String outTradeNo){
	public int updateByOutTradeNo(String transactionId, String outTradeNo){
		int rowNum =  wxPayOrderDao.updateByOutTradeNo(transactionId, outTradeNo);
		return rowNum;
	}
	
	@Override
	public List<WxPayOrder> queryOrdersByOpenId(String openId){
		List<WxPayOrder> orders = wxPayOrderDao.queryOrdersByOpenId(openId);
		if(orders== null || orders.size()==0){
			return null;
		}
		
		for (WxPayOrder wxPayOrder : orders) {
			wxPayOrder.setTotalFee(wxPayOrder.getTotalFee().divide(new BigDecimal(WxPayConfig.WXAMOUNTCONVERSION)).setScale(2));
		}
		
		return orders;
	}
	
	@Override
	@Transactional
	public int deleteOrderByOpenId(String transactionId,String openId){
		int rowNum = 0;
		rowNum = wxPayOrderDao.deleteOrderByOpenId(transactionId,openId);
		if(rowNum>0){
			return rowNum;
		}
		return rowNum;
	}
	
	@Override
	public List<WxPayOrder> queryOrders(){
		List<WxPayOrder> orders = wxPayOrderDao.queryOrders();
		if(orders== null || orders.size()==0){
			return null;
		}
		for (WxPayOrder wxPayOrder : orders) {
			wxPayOrder.setTotalFee(wxPayOrder.getTotalFee().divide(new BigDecimal(WxPayConfig.WXAMOUNTCONVERSION)).setScale(2));
		}
		return orders;
		
	}
	

}
