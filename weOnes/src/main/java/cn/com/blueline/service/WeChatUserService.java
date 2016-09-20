package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.WeChatUser;

public interface WeChatUserService {
	
	
	 /**
     * 查询WeChat用户
     *
     * @return List<WeChatUser>
     */
    List<WeChatUser> getWeChatUserList();
	 
    /**
     * 添加WeChat用户
     *
     * @return
     */
	int saveWeChatUser(WeChatUser weChatUser);
    
	 /**
     * 根据openid查询WeChat用户
     *
     * @return WeChatUser
     */
     WeChatUser queryById(String openid);

}
