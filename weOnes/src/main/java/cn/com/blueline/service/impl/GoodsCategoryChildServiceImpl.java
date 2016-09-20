package cn.com.blueline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.blueline.dao.GoodsAttrKeyDao;
import cn.com.blueline.dao.GoodsCategoryChildDao;
import cn.com.blueline.entity.GoodsAttrKey;
import cn.com.blueline.entity.GoodsCategoryChild;
import cn.com.blueline.service.GoodsCategoryChildService;

@Service
public class GoodsCategoryChildServiceImpl implements GoodsCategoryChildService {
	
	@Autowired
	private GoodsCategoryChildDao dao;
	@Autowired
	private GoodsAttrKeyDao attrKeydao;

	@Override
	public List<GoodsCategoryChild> findByCategoryId(Long categoryId) {
		List<GoodsCategoryChild> list = null;
		try {
			list = dao.findByCategoryId(categoryId);
			return list;
		} catch (Exception e) {
			return list;
		}
	}

	@Override
	public List<GoodsCategoryChild> findAll() {
		List<GoodsCategoryChild> list = null;
		try {
			list = dao.findAll();
			return list;
		} catch (Exception e) {
			return list;
		}
	}

	@Override
	public List<GoodsAttrKey> findAllGoodsAttrKey() {
		List<GoodsAttrKey> list = null;
		try {
			list = attrKeydao.findAll();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}


}
