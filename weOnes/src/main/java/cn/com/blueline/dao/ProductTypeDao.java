package cn.com.blueline.dao;

import java.util.List;

import cn.com.blueline.entity.ProductType;


public interface ProductTypeDao {
	
	
	/**
	 * 查询所有的产品类别
	 * @return List<ProductType> 产品类别集合
	 */
	List<ProductType> findAll();
	
	/**
	 *  根据产品类别查询
	 * @param productType 产品类别
	 * @return ProductType 产品类别实体
	 */
	ProductType findByType(String productType);
	
}
