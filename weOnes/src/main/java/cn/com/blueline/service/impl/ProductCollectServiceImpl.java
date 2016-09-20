package cn.com.blueline.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.blueline.dao.MyFavoriteDao;
import cn.com.blueline.dao.ProductCollectDao;
import cn.com.blueline.dao.ProductCollectDtoDao;
import cn.com.blueline.dao.ProductCollectOrderDao;
import cn.com.blueline.dto.ProductCollectDto;
import cn.com.blueline.entity.ProductCollect;
import cn.com.blueline.entity.ProductCollectFavorite;
import cn.com.blueline.entity.ProductCollectOrder;
import cn.com.blueline.service.ProductCollectService;
import cn.com.blueline.service.TimeScheduleService;
import cn.com.blueline.utils.PageQueryUtil;

@Service
public class ProductCollectServiceImpl implements ProductCollectService{
	
	@Autowired
	private ProductCollectDao collectDao;
	@Autowired
	private ProductCollectDtoDao collectDtoDao;
	@Autowired
	private TimeScheduleService  timeScheduleService;
	@Autowired
	private ProductCollectOrderDao orderDao;
	
	@Autowired
	private MyFavoriteDao myFavoriteDao;
	
	
	@Override
	public int save(ProductCollect productCollect) {
		int rowNum = 0;
		try {
			rowNum = collectDao.save(productCollect);
		} catch (Exception e) {
			return rowNum;
		}
		return rowNum;
		
	}
	@Override
	public List<ProductCollect> findProductCollectByUser(String createUser) {
		List<ProductCollect> list = null;
		try {
			list = collectDao.findProductCollectByUser(createUser);
		} catch (Exception e) {
			return list;
		}
		return list;
	}
	@Override
	public int updateStateById(Long id, String state) {
		int rowNum = 0;
		try {
			rowNum = collectDao.updateStateById(id, state);
		} catch (Exception e) {
			return rowNum;
		}
		return rowNum;
	}
	@Override
	public List<ProductCollect> findProductCollectByState(String state) {
		List<ProductCollect> list = null;
		try {
			list = collectDao.findProductCollectByState(state);
		} catch (Exception e) {
			return list;
		}
		return list;
	}
	
	
	@Override
	public List<ProductCollectDto> appQueryByPage(Integer currentPageNum) {
		List<ProductCollectDto> list = null;
		try {
			int totalCount=collectDao.countByState();//总记录数
			//计算出总页数
			int pageCount = totalCount % PageQueryUtil.DEFAULT_PAGESIZE == 0 ? 
					totalCount/PageQueryUtil.DEFAULT_PAGESIZE:totalCount/PageQueryUtil.DEFAULT_PAGESIZE+1;
			//计算当前当前页的起点索引位置
			int startRowIndex = (currentPageNum-1)*PageQueryUtil.DEFAULT_PAGESIZE;
			list = collectDtoDao.appQueryByPage(startRowIndex,PageQueryUtil.DEFAULT_PAGESIZE);
			System.out.println(list.size());
		} catch (Exception e) {
			return list;
		}
		return list;
	}
	
	
	@Override
	public Integer countByState() {
		return collectDao.countByState();
	}
	
	@Override
	public ProductCollectDto appQueryById(Long id) {
		ProductCollectDto ProductCollectDto = null;
		try {
			ProductCollectDto = collectDtoDao.queryById(id);
			return ProductCollectDto;
		} catch (Exception e) {
			return ProductCollectDto;
		}
		
	}
	@Override
	public Integer queryPriceById(Long id) {
		Integer price = null;
		try {
			price = collectDao.queryPriceById(id);
			return price;
		} catch (Exception e) {
			return price;
		}
	}
	@Override
	public int saveBook(ProductCollectOrder order) {
		int rowNum = 0;
		try {
			rowNum = orderDao.saveBook(order);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}
	@Override
	public int payOrder(String outTradeNo, String openId,
			String transactionId) {
		int rowNum = 0;
		try {
			rowNum = orderDao.payOrder(outTradeNo,  openId, transactionId);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}
	@Override
	public ProductCollectOrder queryOrderById(String outTradeNo) {
		ProductCollectOrder collectOrder = orderDao.queryOrderById(outTradeNo);
		return collectOrder;
	}
	@Override
	public List<ProductCollectOrder> queryOrdersByOpenId(String openId) {
		List<ProductCollectOrder> list = null;
		try {
			System.out.println("openId"+openId);
			list = orderDao.queryOrderByOpenId(openId);
			return list;
		} catch (Exception e) {
			return list;
		}
		
	}
	@Override
	public int updatetoInvalid(String outTradeNo, String openId) {
		int rowNum = 0;
		try {
			rowNum =  orderDao.updatetoInvalid(outTradeNo, openId);
			if(rowNum>0){
				return rowNum;
			}else{
				return rowNum;
			}
		} catch (Exception e) {
			return rowNum;
		}
		
		
	}
	@Override
	public int favoriteOrCancel(String openId, Long productCollectId,
			Integer state) {
		int rowNum = 0;
		try {
			rowNum = myFavoriteDao.favoriteOrCancel(openId, productCollectId, state);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
		
	}
	@Override
	public List<ProductCollectFavorite> queryAllByOpenId(String openId) {
		List<ProductCollectFavorite> list = null;
		try {
			list = myFavoriteDao.queryAllByOpenId(openId);
			return list;
		} catch (Exception e) {
			return list;
		}
		
	}
	@Override
	public Integer queryStateByOpenIdAndProductCollectId(String openId,
			Long productCollectId) {
		Integer state = null;
		try {
			state = myFavoriteDao.queryStateByOpenIdAndProductCollectId(openId, productCollectId);
			return state;
		} catch (Exception e) {
			return state;
		}
	}
	@Override
	public List<ProductCollectDto> queryByUser(String createUser,String state) {
		List<ProductCollectDto> list = null;
		try {
			list = collectDtoDao.queryByUser(createUser,state);
		} catch (Exception e) {
			return list;
		}
		return list;
	}
	@Override
	public List<ProductCollectOrder> queryOrderByUser(String createUser) {
		List<ProductCollectOrder> list = null;
		try {
			list = orderDao.queryOrderByUser(createUser);
			return list;
		} catch (Exception e) {
			return list;
		}
	}

	

}
