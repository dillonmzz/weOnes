package cn.com.blueline.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.blueline.dao.ActivityDao;
import cn.com.blueline.dao.TimeScheduleDao;
import cn.com.blueline.entity.Activity;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private TimeScheduleDao timeManagementDao;

	public List<Activity> getActivityList() {
		List<Activity> activities = this.activityDao.queryAll();
		for (Activity activity : activities) {
			activity.setNewPrice(activity.getNewPrice()
					.divide(new BigDecimal(100)).setScale(2));
			activity.setOldPrice(activity.getOldPrice()
					.divide(new BigDecimal(100)).setScale(2));
		}
		return activities;
	}

	public Activity getActivityByActivityId(Long activityId) {
		Activity activity = this.activityDao.queryById(activityId.longValue());
		activity.setNewPrice(activity.getNewPrice().divide(new BigDecimal(100))
				.setScale(2));
		activity.setOldPrice(activity.getOldPrice().divide(new BigDecimal(100))
				.setScale(2));
		return activity;
	}

	@Transactional
	public int saveActivity(Activity activity) {
		int rowNum = this.activityDao.insertActivity(activity);
		System.out.println("插入的rowNum:"+rowNum);
		System.out.println("插入后主键为:"+activity.getActivityId());
		return rowNum;
	}

	@Transactional
	public int favoriteOrCancelActivity(String openId, Long activityId,
			Integer state) {
		int rowNum = 0;
		try {
			rowNum = this.activityDao.favoriteOrCancelActivity(openId,
					activityId, state);
		} catch (Exception e) {
		}

		return rowNum;
	}

	public int queryStateByOpenIdAndActivityId(String openId, Long activityId) {
		int rowNum = 0;
		try {
			rowNum = this.activityDao.queryStateByOpenIdAndActivityId(openId,
					activityId);
		} catch (Exception e) {
		}
		return rowNum;
	}

	@Transactional
	public int updateActivityStateById(String activityId, String state) {
		int rowNum = 0;
		try {
			rowNum = this.activityDao
					.updateActivityStateById(activityId, state);
		} catch (Exception e) {
		}
		return rowNum;
	}

	@Override
	@Transactional
	public int saveActivity(Activity activity, List<TimeSchedule> list) {
		int rowNum = 0;
		try {
			rowNum = this.activityDao.insertActivity(activity);
			System.out.println("插入后主键为:"+activity.getActivityId());
			/*for (TimeManagement timeManagement : list) {
				timeManagement.setActivityId(activity.getActivityId());
			}
			timeManagementDao.save(list);*/
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
		
	}

	@Override
	public int updateActivity(Activity activity) {
		int rowNum = 0;
		try {
			rowNum = activityDao.updateActivityStateById(Long.toString(activity.getActivityId()),"9");
			rowNum = activityDao.insertActivity(activity);
			return rowNum;
		} catch (Exception e) {
			return rowNum;
		}
	}

}
