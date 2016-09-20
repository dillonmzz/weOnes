package cn.com.blueline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.ProductCollectOrder;

/**
 * 产品支付接口
 * @author Zhaozhi
 *
 */
public interface ProductCollectOrderDao {
	
	
	/**
	 * 保存订单
	 * @param order 产品订单详情
	 * @return
	 */
	int saveBook(ProductCollectOrder order);
	
	
	/**
	 * 支付订单
	 * @param outTradeNo 订单号
	 * @param collectId 产品ID
	 * @param openId 微信openId
	 * @param transactionId 微信支付交易号
	 * @return 
	 */
	int payOrder(@Param("outTradeNo") String outTradeNo,
				//@Param("collectId") long collectId,
				@Param("openId") String openId,
				@Param("transactionId") String transactionId);
	
	
	/**
	 * 根据订单号查询订单信息
	 * @param outTradeNo 订单号
	 * @return
	 */
	ProductCollectOrder queryOrderById(String outTradeNo);
	
	/**
	 * 根据openId查询订单
	 * @param openId 微信用户
	 * @return 订单列表
	 */
	List<ProductCollectOrder> queryOrderByOpenId(String openId);
	
	/**
	 * 根据商家用户查询订单
	 * @param createUser 商家用户
	 * @return 订单列表
	 */
	List<ProductCollectOrder> queryOrderByUser(@Param("createUser") String createUser);
	
	
	/**
	 * 将订单状态改为无效状态"9"
	 * @param outTradeNo 订单号
	 * @param openId 微信用户Id
	 * @return 
	 */
	int updatetoInvalid(@Param("outTradeNo") String outTradeNo,@Param("openId") String openId);
	
	
}
