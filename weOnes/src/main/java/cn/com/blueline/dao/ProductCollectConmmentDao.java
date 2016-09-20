package cn.com.blueline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.ProductCollectComment;
import cn.com.blueline.entity.ProductCollectOrder;

/**
 * 产品评论
 * @author Zhaozhi
 *
 */
public interface ProductCollectConmmentDao {
	
	
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
