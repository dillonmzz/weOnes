package cn.com.blueline.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.ProductCollect;

public interface ProductCollectDao {
	
	
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
	int updateStateById(@Param("id") Long id,@Param("state")String state);
	
	
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
	 * 用户下订单时,根据产品ID查询价格
	 * @param id 产品ID
	 * @return 价格
	 */
	Integer queryPriceById(Long id);
	
	
	
	
}
