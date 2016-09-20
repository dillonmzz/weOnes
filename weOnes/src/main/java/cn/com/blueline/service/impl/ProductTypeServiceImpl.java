package cn.com.blueline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.blueline.dao.ProductSubTypeDao;
import cn.com.blueline.dao.ProductTypeDao;
import cn.com.blueline.entity.ProductSubType;
import cn.com.blueline.entity.ProductType;
import cn.com.blueline.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	
	@Autowired
	private ProductTypeDao typeDao;
	
	@Autowired
	private ProductSubTypeDao subTypeDao;

	@Override
	public List<ProductSubType> findAllByType(Long productTypeId) {
		
		return subTypeDao.findAllByType(productTypeId);
	}

	@Override
	public ProductSubType findByTypeAndSubtype(Long productType,
			String subType) {
		return subTypeDao.findByTypeAndSubtype(productType, subType);
	}

	@Override
	public List<ProductType> findAll() {
		return typeDao.findAll();
	}

	@Override
	public ProductType findByType(String productType) {
		return typeDao.findByType(productType);
	}


}
