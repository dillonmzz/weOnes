package cn.com.blueline.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.blueline.dao.SystemUserDao;
import cn.com.blueline.entity.SystemUser;
import cn.com.blueline.service.SystemUserService;

@Service
public class SystemUserServiceImpl implements SystemUserService{
	
	@Autowired
	private SystemUserDao dao;

	@Override
	public int save(SystemUser user) {
		int rowNum = 0;
		try {
			
			rowNum = dao.save(user);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

	@Override
	public SystemUser queryByUserName(String userName) {
		
		SystemUser user = null;
		try {
			user = dao.queryByUserName(userName);
			return user;
		} catch (Exception e) {
			return user;
		}
	}

	@Override
	@Transactional
	public int updateById(SystemUser user) {
		int rowNum = 0;
		try {
			rowNum = dao.updateById(user);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

	@Override
	@Transactional
	public int certificate(Long userId, String certificateImg) {
		int rowNum = 0;
		try {
			rowNum = dao.certificate(userId,certificateImg);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

	@Override
	@Transactional
	public int changeIsAuthenticatioin(Long userId) {
		int rowNum = 0;
		try {
			rowNum = dao.changeIsAuthenticatioin(userId);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

	@Override
	public List<SystemUser> queryCertificateUsers() {
		List<SystemUser> list = null;
		try {
			list = dao.queryCertificateUsers();
			return list;
		} catch (Exception e) {
			return list;
		}
	}
	
	

}
