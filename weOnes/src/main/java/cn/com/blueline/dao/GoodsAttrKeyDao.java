package cn.com.blueline.dao;

import java.util.List;

import cn.com.blueline.entity.GoodsAttrKey;


public interface GoodsAttrKeyDao {
	
	/**
	 * 查询所有的商品属性
	 * @return  List<GoodsAttrKey> 所有的商品属性
	 */
	List<GoodsAttrKey> findAll();
	
}
