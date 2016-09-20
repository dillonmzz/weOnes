package cn.com.blueline.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.blueline.dao.TimeScheduleDao;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.service.TimeScheduleService;

@Service
public class TimeScheduleServiceImpl implements TimeScheduleService{
	
	@Autowired
	private TimeScheduleDao dao;

	@Override
	public List<TimeSchedule> queryTimeScheduleByUser(String createUser) {
		List<TimeSchedule> list =null;
		try {
			list = dao.queryScheduleByUser(createUser);
			if(list.size()>0){
				return list;
			}
		} catch (Exception e) {
			
		}
		return list;
	}

	@Override
	@Transactional
	public int save(List<TimeSchedule> list) {
		int rowNum = 0;
		try {
			rowNum = dao.save(list);
			if(rowNum>0){
				return rowNum;
			}
		} catch (Exception e) {
		}
		return rowNum;
	}

	@Override
	@Transactional
	public int updateToInvalid(String status,String id) {
		int rowNum = 0;
		try {
			rowNum = dao.updateToInvalid(status, id);
			if(rowNum>0){
				return rowNum;
			}
		} catch (Exception e) {
		}
		return rowNum;
	}

	@Override
	@Transactional
	public int updateTimeManagement(List<TimeSchedule> list){
		int rowNum = 0;
		try {
			dao.updateToInvalid("I",list.get(0).getId());
			rowNum= dao.save(list);
			if(rowNum>0){
				return rowNum;
			}
		} catch (Exception e) {

		}
		return rowNum;
	}

	@Override
	public List<TimeSchedule> findTimeScheduleByTimeType(String timeType,
			String createUser) {
		List<TimeSchedule> list = null;
		try {
			list = dao.findTimeScheduleByTimeType(timeType,createUser);
			for (TimeSchedule timeSchedule : list) {
				System.out.println(timeSchedule.getStartDate()+"-"+timeSchedule.getEndDate());
			}
		} catch (Exception e) {
			return list;
		}
		return list;
	}

	@Override
	public List<TimeSchedule> queryTimeByDate(String id,String startDate) {
		List<TimeSchedule> list = null;
		try {
			list = dao.queryTimeByDate(id, startDate);
			return list;
		} catch (Exception e) {
			return list;
		}
		
	}
	

}