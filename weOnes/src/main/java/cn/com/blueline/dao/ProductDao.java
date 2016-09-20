package cn.com.blueline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.Product;

public interface ProductDao {
	
	
	/**
	 * 保存产品
	 * @param product 产品对象
	 * @return
	 */
	int save(Product product);
	
	/**
	 * 修改产品
	 * @param Product 产品实体
	 * @return 1成功 0 失败
	 */
	int update(Product product);
	
	/**
	 * 将当前用户产品修改为无效状态
	 * @param id 产品Id
	 * @return
	 */
	int updateToInvalid(String id,String currentUserId);
	
	
	/**
	 * 根据产品类别、用户ID查询产品
	 * @param productType 产品类别
	 * @param createUser 用户ID
	 * @return List<Product> 产品集合
	 */
	List<Product> findProductByUser(@Param("productType") String productType,@Param("createUser") String createUser);
	
	/**
	 * 根据产品类别、用户ID查询产品
	 * @param productType 产品类型
	 * @param createUser 当前用户
	 * @param id 产品ID
	 * @return Product 产品对象
	 */
	 Product findProductByUserAndId(@Param("productType") String productType,@Param("createUser") String currentUserId,@Param("id") String id);
	
	
	
	
	
}
