package cn.com.blueline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.blueline.dao.ActivityAddressDao;
import cn.com.blueline.entity.ActivityAddress;
import cn.com.blueline.service.ActivityAddressService;

@Service
public class ActivityAddressServiceImpl implements ActivityAddressService{
	
	@Autowired
	private ActivityAddressDao activityAddressDao;

	@Override
	public List<ActivityAddress> queryActivityAddressBycreateUser(String createUser) {
		List<ActivityAddress> list =null;
		try {
			list = activityAddressDao.queryActivityAddressBycreateUser(createUser);
			if(list.size()>0){
				return list;
			}
		} catch (Exception e) {
			
		}
		return list;
	}

	@Override
	@Transactional
	public int save(ActivityAddress address) {
		int rowNum = 0;
		try {
			rowNum = activityAddressDao.save(address);
			if(rowNum>0){
				return rowNum;
			}
		} catch (Exception e) {
		}
		return rowNum;
	}

	@Override
	@Transactional
	public int updateToInvalid(String status, String addressId) {
		int rowNum = 0;
		try {
			rowNum = activityAddressDao.updateToInvalid(status, addressId);
			if(rowNum>0){
				return rowNum;
			}
		} catch (Exception e) {
		}
		return rowNum;
	}

	@Override
	@Transactional
	public int updateActivityAddress(ActivityAddress address) {
		int rowNum = 0;
		try {
			activityAddressDao.updateToInvalid("I", address.getAddressId());
			rowNum= activityAddressDao.save(address);
			if(rowNum>0){
				return rowNum;
			}
		} catch (Exception e) {

		}
		return rowNum;
	}
	

}