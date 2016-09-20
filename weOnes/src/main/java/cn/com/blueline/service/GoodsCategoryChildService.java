package cn.com.blueline.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.GoodsAttrKey;
import cn.com.blueline.entity.GoodsCategoryChild;

public interface GoodsCategoryChildService {
	
	/**
	 * 根据父类别ID查询子类别
	 * @param categoryId 父类别ID
	 * @return  List<GoodsCategoryChild> 子类别信息
	 */
	List<GoodsCategoryChild> findByCategoryId(@Param("categoryId") Long categoryId);
	
	/**
	 * 查询所有的子类别信息
	 * @return  List<GoodsCategoryChild> 子类别信息
	 */
	List<GoodsCategoryChild> findAll();
	
	/**
	 * 查询所有的商品属性
	 * @return  List<GoodsAttrKey> 所有的商品属性
	 */
	List<GoodsAttrKey> findAllGoodsAttrKey();
	
	
	
}
