package cn.com.blueline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.ProductSubType;


public interface ProductSubTypeDao {

	/**
	 * 根据产品类别查询产品子类别
	 * @param productType 产品类别
	 * @return List<ProductSubType> 产品子类别集合
	 */
	List<ProductSubType> findAllByType(Long productTypeId);
	
	/**
	 * 根据产品类别和产品子类别查询
	 * @param productType 产品类别
	 * @param subType 产品子类别
	 * @return ProductSubType 产品子类别实体
	 */
	ProductSubType findByTypeAndSubtype(@Param("productTypeId") Long productTypeId,@Param("subType") String subType);
	
	
}
