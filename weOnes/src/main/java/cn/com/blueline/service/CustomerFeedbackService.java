package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.CustomerFeedback;

public interface CustomerFeedbackService {

	/**
	 * 添加
	 * @param feedback 反馈实体
	 * @return 
	 */
	int save(CustomerFeedback feedback);
	
	/**
	 * 查询所有的客户反馈意见
	 * @return 客户反馈意见集合
	 */
	List<CustomerFeedback> queryAll();
	
}
