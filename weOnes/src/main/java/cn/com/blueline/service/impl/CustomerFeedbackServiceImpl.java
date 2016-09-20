package cn.com.blueline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.blueline.dao.CustomerFeedbackDao;
import cn.com.blueline.entity.CustomerFeedback;
import cn.com.blueline.service.CustomerFeedbackService;

@Service
public class CustomerFeedbackServiceImpl implements CustomerFeedbackService {
	
	@Autowired
	private CustomerFeedbackDao  dao;

	@Override
	public int save(CustomerFeedback feedback) {
		int rowNum = 0;
		try {
			rowNum = dao.save(feedback);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

	@Override
	public List<CustomerFeedback> queryAll() {
		List<CustomerFeedback> list = null;
		try {
			list = dao.queryAll();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
	
	
	
	
}
