package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.dto.WxPaySentData;
import cn.com.blueline.entity.WxPayOrder;


/**
 *  统一下单支付
 * @author Dillon
 *
 */
public interface UnifiedOrderService {
	
	String unifiedOrder(WxPaySentData data,String key);
	
	int saveUnifiedOrder(WxPayOrder wxPayOrder);
	
	//根据商户单号(业务系统生成)查询订单数据
	WxPayOrder findWxPayOrderByOutTradeNo(String outTradeNo);
	
	//int updateByOutTradeNo(String transactionId, Integer state,String outTradeNo);
	int updateByOutTradeNo(String transactionId,String outTradeNo);
	
	
	 //根据openid查询用户订单列表
	 //参数 :用户ID
	 //返回:活动ID、活动title、活动缩略图、活动时间、订单号、订单状态、订单金额
	 List<WxPayOrder> queryOrdersByOpenId(String openId);
	 
	 
	//根据 微信订单交易号 删除订单记录
	//参数 :微信订单交易号(用户未支付或者支付失败的情况下与系统的订单号一致)
	//返回:删除记录数据
	int deleteOrderByOpenId(String transactionId,String openId);
	
	
	//查询所有订单记录
	List<WxPayOrder> queryOrders();


	
}
