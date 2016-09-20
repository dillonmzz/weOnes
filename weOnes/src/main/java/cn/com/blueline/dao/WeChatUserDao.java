package cn.com.blueline.dao;

import java.util.List;

import cn.com.blueline.entity.WeChatUser;



public interface WeChatUserDao{
	
	 /**
     * 根据openid查询weChat用户
     *
     * @param openid
     * @return  WeChatUser
     */
	public WeChatUser queryById(String openid);

	/**
	 * 插入weChat用户
     *
	 * @param activity
	 * @return
	 */
	 int insertWeChatUser(WeChatUser weChatUser);
	
		/**
		 * 修改weChat用户
	     *
		 * @param weChatUser
		 * @return
		 */
	int updateWeChatUser(WeChatUser weChatUser);
	
	List<WeChatUser>  queryAll();
	 
	
	

}
