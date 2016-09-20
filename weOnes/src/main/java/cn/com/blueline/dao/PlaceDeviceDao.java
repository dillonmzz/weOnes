package cn.com.blueline.dao;

import java.util.List;

import cn.com.blueline.entity.PlaceDevice;


public interface PlaceDeviceDao {
	
	/**
	 * 查询所有的场地设备
	 * @return List<PlaceDevice> 场地设备集合
	 */
	List<PlaceDevice> findAll();
	
	/**
	 * 根据设备code查询设备信息
	 * @param placeDevice 设备code
	 * @return PlaceDevice 场地设备信息
	 */
	PlaceDevice findByPlaceDeviceCode(String placeDevice);

	
}
