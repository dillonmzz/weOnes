package cn.com.blueline.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.ActivityAddress;

public interface ActivityAddressService {

	/**
	 * 查看当前用户的地点
	 * @param createUser 当前用户
	 * @return 地点集合
	 */
	List<ActivityAddress> queryActivityAddressBycreateUser(String createUser);

	/**
	 * 保存地点
	 * @param address 地点实体
	 * @return 1成功 0 失败
	 */
	int save(ActivityAddress address);
	
	/**
	 * 删除(修改)地点
	 * (将status的状态修改为I,默认A为有效)
	 * @param addressId
	 * @return 1成功 0 失败
	 */
	int updateToInvalid(String status,String addressId);
	
	/**
	 * 修改地点
	 * 先将历史记录状态改为I,然后保存新的记录
	 * @param address
	 * @return
	 */
	int updateActivityAddress(ActivityAddress address);
}
