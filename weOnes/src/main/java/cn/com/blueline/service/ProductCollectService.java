package cn.com.blueline.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.dto.ProductCollectDto;
import cn.com.blueline.entity.ProductCollect;
import cn.com.blueline.entity.ProductCollectFavorite;
import cn.com.blueline.entity.ProductCollectOrder;


public interface ProductCollectService {

	/**
	 * 用户角色:
	 * 	发布产品
	 * @param productCollect 产品发布实体
	 * @return
	 */
	int save(ProductCollect productCollect);
	
	
	/**
	 * 用户角色:
	 * 	  查询个人发布过的产品列表
	 * @param createUser 当前用户
	 * @return List<ProductCollect> 发布过的产品列表
	 */
	List<ProductCollect> findProductCollectByUser(String createUser);
	
	
	/**
	 * 用户角色:
	 * 	  将发布后的产品状态改为无效状态(state=9：无效/已删除)
	 * 管理员角色:
	 * 	  审批用户发布的产品(state=1:已拒绝  state=2:已审核)
	 * @param id 发布的产品ID
	 * @param state 状态
	 * @return
	 */
	int updateStateById(Long id,String state);
	
	
	/**
	 * 管理员角色
	 * 查询用户已提交但未审批的产品列表
	 * @param state 发布的产品状态0
	 * @return List<ProductCollect> 发布过的产品列表
	 */
	List<ProductCollect> findProductCollectByState(String state);
	
	/**
	 * 查询已审核的产品列表总记录数
	 * @return Integer 总记录数
	 */
	Integer countByState();


	/**
	 * 移动端
	 * 普通用户角色
	 * 分页查询产品列表
	 * @param currentPageNum 当前页码
	 * @return List<ProductCollectDto> ProductCollectDto集合
	 */
	List<ProductCollectDto> appQueryByPage(Integer currentPageNum);
	
	
	/**
	 * 发布者查看发布的活动列表
	 * @param createUser 发布者
	 * @param state 状态
	 * @return List<ProductCollectDto>活动列表
	 */
	List<ProductCollectDto> queryByUser(String createUser,String state);
	
	/**
	 * 移动端
	 * 普通用户角色
	 * 根据产品ID 查询产品详细
	 * @param id productCollectId
	 * @return 发布过的产品详情
	 */
	ProductCollectDto appQueryById(Long id);
	
	/**
	 * 移动端
	 * 用户下订单时,根据产品ID查询价格
	 * @param id 产品ID
	 * @return BigDecimal价格
	 */
	Integer queryPriceById(Long id);
	
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
	int payOrder( String outTradeNo,
				 //long collectId,
				 String openId,
				 String transactionId);
	
	/**
	 * 根据订单号查询订单信息
	 * @param outTradeNo 订单号
	 * @return ProductCollectOrder 订单信息
	 */
	ProductCollectOrder queryOrderById(String outTradeNo);
	
	
	/**
	 * 根据openId查询订单
	 * @param openId 微信用户
	 * @return 订单列表
	 */
	List<ProductCollectOrder> queryOrdersByOpenId(String openId);
	
	/**
	 * 根据商家用户查询订单
	 * @param createUser 商家用户
	 * @return 订单列表
	 */
	List<ProductCollectOrder> queryOrderByUser(String createUser);
	
	
	
	
	/**
	 * 将订单状态改为无效状态"9"
	 * @param outTradeNo 订单号
	 * @param openId 微信用户Id
	 * @return 
	 */
	int updatetoInvalid(String outTradeNo,String openId);
	
	/**
	 * 关注或者取消 产品
	 * @param openId 微信用户ID
	 * @param productCollectId 产品ID
	 * @param state 状态 0:取消关注  1:已关注
	 * @return
	 */
	int favoriteOrCancel(String openId,Long productCollectId, Integer state);
	
	/**
	 * 根据微信用户ID查询关注的产品列表
	 * @param openId 微信用户
	 * @return 产品列表集合
	 */
	List<ProductCollectFavorite> queryAllByOpenId(String openId);
	
	/**
	 * 根据当前用户ID和产品ID查询出最新的状态
	 * @param openId 用户ID
	 * @param productCollectId 产品ID
	 * @return 最新状态
	 */
	Integer queryStateByOpenIdAndProductCollectId(String openId,Long productCollectId);
	
}
