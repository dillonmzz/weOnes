package cn.com.blueline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.blueline.dao.PlaceDeviceDao;
import cn.com.blueline.dao.ProductDao;
import cn.com.blueline.entity.PlaceDevice;
import cn.com.blueline.entity.Product;
import cn.com.blueline.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao dao;
	@Autowired
	private PlaceDeviceDao deviceDao;

	@Override
	public List<Product> findProductByUser(String productType, String currentUserId) {
		List<Product> list = null;
		try {
			list = dao.findProductByUser(productType, currentUserId);
			return list;
		} catch (Exception e) {
			return list;
		}
		
	}

	@Override
	public int save(Product product) {
		int rowNum = 0;
		try {
			rowNum = dao.save(product);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
		
	}

	@Override
	public int updateToInvalid(String id, String currentUserId) {
		int rowNum = 0;
		try {
			rowNum = dao.updateToInvalid(id,currentUserId);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

	@Override
	public List<PlaceDevice> findAllPlaceDevice() {
		return deviceDao.findAll();
	}

	@Override
	public PlaceDevice findByPlaceDeviceCode(String placeDevice) {
		return deviceDao.findByPlaceDeviceCode(placeDevice);
	}

	@Override
	public Product findProductByUserAndId(String productType,
			String currentUserId, String id) {
		Product list = null;
		try {
			list = dao.findProductByUserAndId(productType, currentUserId, id);
			return list;
		} catch (Exception e) {
			return list;
		}
	}

	@Override
	public int update(Product product) {
		int rowNum = 0;
		try {
			rowNum = dao.update(product);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

}
