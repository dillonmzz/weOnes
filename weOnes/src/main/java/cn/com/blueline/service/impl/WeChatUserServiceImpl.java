package cn.com.blueline.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.blueline.dao.WeChatUserDao;
import cn.com.blueline.entity.WeChatUser;
import cn.com.blueline.service.WeChatUserService;


@Service
public class WeChatUserServiceImpl implements WeChatUserService{
	
	
	private Log LOG = LogFactory.getLog(this.getClass());
	
	public WeChatUserServiceImpl() {
		super();
		System.out.println("***加载WeChatUserServiceImpl*****");
	}

	@Autowired
	private WeChatUserDao weChatUserDao;

	@Override
	public List<WeChatUser> getWeChatUserList(){
		List<WeChatUser> weChatUsers = weChatUserDao.queryAll();
		return weChatUsers;
	}

	@Override
	@Transactional
	public int saveWeChatUser(WeChatUser weChatUser) {
		//判断用户是否存在，若不存在则保存，若存在则跳过
		WeChatUser user = weChatUserDao.queryById(weChatUser.getOpenId());
		int rowNum = 0;
		if(user!=null){
			System.out.println("该用户已经存在....");
			rowNum =1;
			return rowNum;
		}else{
			rowNum = weChatUserDao.insertWeChatUser(weChatUser);
			return rowNum;
		}
	}
	
	
	@Override
    public WeChatUser queryById(String openid){
		
		WeChatUser weChatUser =weChatUserDao.queryById(openid);
		
		return  weChatUser;
    }
	
	

}
