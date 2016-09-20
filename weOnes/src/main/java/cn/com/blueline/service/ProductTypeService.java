package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.ProductSubType;
import cn.com.blueline.entity.ProductType;
public interface ProductTypeService {

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
	ProductSubType findByTypeAndSubtype(Long productTypeId,String subType);
	
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
