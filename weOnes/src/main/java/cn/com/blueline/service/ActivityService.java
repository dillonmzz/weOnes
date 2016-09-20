package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.Activity;
import cn.com.blueline.entity.TimeSchedule;

public interface ActivityService {
	
	
	 /**
     * 查询所有活动
     *
     * @return List<Activity>
     */
    List<Activity> getActivityList();
	 
    /**
     * 根据活动ID查询
     *
     * @return
     */
     Activity getActivityByActivityId(Long activityId);
	 
    /**
     * 保存活活动
     *
     * @return
     */
	int saveActivity(Activity activity);
	
	/**
	 * 保存活动 包含时间表
	 * @param Activity activity 活动
	 * @param List<TimeManagement> list 时间表集合
	 * @return
	 */
	int saveActivity(Activity activity,List<TimeSchedule> list);
    
	/**
	* 收藏活动
	*/
	int favoriteOrCancelActivity(String openId,Long activityId,Integer state);
	
	
	//查询某个用户关注某个活动最新状态
	int queryStateByOpenIdAndActivityId(String openId,Long activityId);
	
	//根据activityId update活动记录(删除：update state=9,发布：update state=9)
	int updateActivityStateById(String activityId,String state);
	
	//修改活动，先将原来的活动state状态设为9,然后插入新的记录
	int updateActivity(Activity activity);

}
