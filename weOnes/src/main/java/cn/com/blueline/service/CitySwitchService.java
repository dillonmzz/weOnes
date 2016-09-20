package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.dto.ProductCollectDto;


public interface CitySwitchService {

	
	
	/**
	 * 根据城市和产品类别分页查询
	 * @param startIdex 当前页的起点索引
	 * @param rows 显示条数
	 * @param city 城市
	 * @Param type 产品类别
	 * @return
	 */
	List<ProductCollectDto> queryByCityAndType(Integer currentPageNum,String city,String type);
	
}
