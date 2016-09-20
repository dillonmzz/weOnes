package cn.com.blueline.dao;

import java.util.List;

import cn.com.blueline.entity.MyFavorite;
import cn.com.blueline.entity.ProductCollectFavorite;

public interface MyFavoriteDao{
	
	
	/**
	*   查询关注收藏列表
	**/
	List<MyFavorite> queryMyFavoriteListByOpenId(String openId);
	
	
	/**
	 * 关注或者取消 产品
	 * @param openId 微信用户ID
	 * @param productCollectId 产品ID
	 * @param state 状态 0:取消关注  1:已关注
	 * @return
	 */
	int favoriteOrCancel(String openId,Long productCollectId, Integer state);
	
	/**
	 * 根据微信用户ID查询关注的产品列表
	 * @param openId 微信用户
	 * @return 产品列表集合
	 */
	List<ProductCollectFavorite> queryAllByOpenId(String openId);
	
	/**
	 * 根据当前用户ID和产品ID查询出最新的状态
	 * @param openId 用户ID
	 * @param productCollectId 产品ID
	 * @return 最新状态
	 */
	Integer queryStateByOpenIdAndProductCollectId(String openId,Long productCollectId);
	

}
