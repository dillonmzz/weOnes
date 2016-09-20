package cn.com.blueline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.ActivityAddress;

public interface ActivityAddressDao{
	
	
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
	int updateToInvalid(@Param("status") String status,@Param("addressId") String addressId);
	
	

}
