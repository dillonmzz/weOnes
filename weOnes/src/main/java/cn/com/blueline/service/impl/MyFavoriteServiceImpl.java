package cn.com.blueline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.blueline.dao.MyFavoriteDao;
import cn.com.blueline.entity.MyFavorite;
import cn.com.blueline.service.MyFavoriteService;


@Service
public class MyFavoriteServiceImpl implements MyFavoriteService{
	
	@Autowired
	private MyFavoriteDao favoriteDao;

	@Override
	public List<MyFavorite> queryMyFavoriteListByOpenId(String openId) {
		List<MyFavorite> favorites = favoriteDao.queryMyFavoriteListByOpenId(openId);
		if(favorites== null || favorites.size()==0){
			return null;
		}
		return favorites;
		
	}
	
	

	

}
