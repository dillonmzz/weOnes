package cn.com.blueline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.dto.ProductCollectDto;

public interface ProductCollectDtoDao {
	
	
	/**
	 * 移动端
	 * 分页查询产品列表
	 * @param startIdex 当前页的起点索引
	 * @param rows 显示条数
	 * @return List<ProductCollectDto> 
	 */
	List<ProductCollectDto> appQueryByPage(Integer startIdex,Integer rows);
	
	/**
	 * 发布者查看发布的活动列表
	 * @param createUser 发布者
	 * @param state 状态
	 * @return List<ProductCollectDto>活动列表
	 */
	List<ProductCollectDto> queryByUser(@Param("createUser")String createUser,@Param("state")String state);
	
	
	
	/**
	 * 根据城市和产品类别分页查询
	 * @param startIdex 当前页的起点索引
	 * @param rows 显示条数
	 * @param city 城市
	 * @Param type 产品类别
	 * @return
	 */
	List<ProductCollectDto> queryByCityAndType(Integer startIdex,Integer rows,@Param("city") String city,@Param("type") String type);
	
	
	/**
	 * 移动端
	 * 根据产品ID 查询产品详细
	 * @param id productCollectId
	 * @return 发布过的产品详情
	 */
	ProductCollectDto queryById(Long id);
	

}
