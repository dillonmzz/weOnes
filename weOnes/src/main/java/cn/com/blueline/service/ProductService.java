package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.PlaceDevice;
import cn.com.blueline.entity.Product;
public interface ProductService {
	
	/**
	 * 根据产品类别、用户ID查询产品
	 * @param productType 产品类型
	 * @param createUser 当前用户
	 * @param id 产品ID
	 * @return Product 产品对象
	 */
	 Product findProductByUserAndId(String productType,String currentUserId,String id);
	
	

	/**
	 * 根据产品类别、用户ID查询产品
	 * @param productType 产品类型
	 * @param createUser 当前用户
	 * @return List<Product> 产品集合
	 */
	List<Product> findProductByUser(String productType,String currentUserId);

	/**
	 * 保存产品
	 * @param Product 产品实体
	 * @return 1成功 0 失败
	 */
	int save(Product product);
	
	/**
	 * 修改产品
	 * @param Product 产品实体
	 * @return 1成功 0 失败
	 */
	int update(Product product);
	
	/**
	 * 将当前用户产品修改为无效状态
	 * @param id 产品ID
	 * @param currentUser 用户ID
	 * @return
	 */
	int updateToInvalid(String id,String currentUserId);
	
	
	/**
	 * 查询所有的场地设备
	 * @return List<PlaceDevice> 场地设备集合
	 */
	List<PlaceDevice> findAllPlaceDevice();
	
	/**
	 * 根据设备code查询设备信息
	 * @param placeDevice 设备code
	 * @return PlaceDevice 场地设备信息
	 */
	PlaceDevice findByPlaceDeviceCode(String placeDevice);
	
}
