package cn.com.blueline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.blueline.dao.ProductCollectConmmentDao;
import cn.com.blueline.entity.ProductCollectComment;
import cn.com.blueline.service.ProductCollectConmmentService;

@Service
public class ProductCollectConmmentServiceImpl implements ProductCollectConmmentService {
	
	@Autowired
	private ProductCollectConmmentDao  conmmentDao;

	@Override
	public int save(ProductCollectComment comment) {
		int rowNum = 0;
		try {
			rowNum = conmmentDao.save(comment);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

	@Override
	public List<ProductCollectComment> queryCommentByProductCollectId(
			Long productCollectId) {
		List<ProductCollectComment> list = null;
		try {
			list = conmmentDao.queryCommentByProductCollectId(productCollectId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
	
	
	
	
}
