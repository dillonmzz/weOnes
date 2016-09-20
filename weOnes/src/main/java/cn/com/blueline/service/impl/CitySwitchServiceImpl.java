package cn.com.blueline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.blueline.dao.ProductCollectDao;
import cn.com.blueline.dao.ProductCollectDtoDao;
import cn.com.blueline.dto.ProductCollectDto;
import cn.com.blueline.service.CitySwitchService;
import cn.com.blueline.utils.PageQueryUtil;

@Service
public class CitySwitchServiceImpl implements CitySwitchService{
	
	@Autowired
	private ProductCollectDtoDao collectDtoDao;
	
	@Override
	public List<ProductCollectDto> queryByCityAndType(Integer currentPageNum,String city,String type){
		List<ProductCollectDto> list = null;
		try {
			int startRowIndex = (currentPageNum-1)*PageQueryUtil.DEFAULT_PAGESIZE;
			list = collectDtoDao.queryByCityAndType(startRowIndex, PageQueryUtil.DEFAULT_PAGESIZE, city, type);
		} catch (Exception e) {
			return list;
		}
		return list;
	}
	
	

}
