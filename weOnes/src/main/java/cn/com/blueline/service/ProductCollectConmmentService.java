package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.ProductCollectComment;

public interface ProductCollectConmmentService {

	/**
	 * 添加评论
	 * @param comment 评论实体
	 * @return 当前产品有评论记录数
	 */
	int save(ProductCollectComment comment);
	
	/**
	 * 根据产品ID查询评论内容
	 * @param productCollectId 产品ID
	 * @return 评论内容集合
	 */
	List<ProductCollectComment> queryCommentByProductCollectId(Long productCollectId);
	
}
