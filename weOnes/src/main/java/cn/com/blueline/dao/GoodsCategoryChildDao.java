package cn.com.blueline.dao;

import java.util.List;



import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.GoodsCategoryChild;

public interface GoodsCategoryChildDao {
	
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
	
}
