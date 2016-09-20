package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.MyFavorite;


public interface MyFavoriteService {
	
	/**
	*   查询关注收藏列表
	**/
	List<MyFavorite> queryMyFavoriteListByOpenId(String openId);
}
