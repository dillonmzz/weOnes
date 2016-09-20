package cn.com.blueline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.WxPayOrder;

public interface WxPayOrderDao {

	/**
	 * 保存微信支付订单记录
	 *
	 * @param WxPayOrder
	 * @return int 保存记录数
	 */
	int saveWxPayOrder(WxPayOrder wxPayOrder);

	WxPayOrder queryByOutTradeNo(String outTradeNo);

	int updateByOutTradeNo(@Param("transactionId") String transactionId,
			@Param("outTradeNo") String outTradeNo);

	// 根据openid查询用户订单列表
	// 参数 :用户ID
	// 返回:活动ID、活动title、活动缩略图、活动时间、订单号、订单状态、订单金额
	List<WxPayOrder> queryOrdersByOpenId(String openId);

	// 根据 微信订单交易号 删除订单记录
	// 参数 :微信订单交易号(用户未支付或者支付失败的情况下与系统的订单号一致)、openId
	// 返回:删除记录数据
	int deleteOrderByOpenId(@Param("transactionId") String transactionId,
			@Param("openId") String openId);

	// 查询所有订单记录
	List<WxPayOrder> queryOrders();

}
