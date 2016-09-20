package cn.com.blueline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.Activity;



public interface ActivityDao{
	
	 /**
     * 根据ID查询活动对象
     *
     * @param activityId
     * @return
     */
	public Activity queryById(long activityId);

	/**
	 * 插入活动实体
     *
	 * @param activity
	 * @return
	 */
	 int insertActivity(Activity activity);
	
		/**
		 * 修改活动实体
	     *
		 * @param activity
		 * @return
		 */
	int updateActivity(Activity activity);
		
	
	List<Activity>  queryAll();
	
	/*
	* 收藏\取消关注 活动
	*/
	//int favoriteOrCancelActivity(@Param("activityId") String activityId,@Param("openId") String openId,@Param("state")Integer state);
	int favoriteOrCancelActivity(String openId,Long activityId,Integer state);
	
	// 查询某个用户关注某个活动最新状态
	int queryStateByOpenIdAndActivityId(String openId,Long activityId);
	
	//根据activityId update活动记录(删除：update state=9,发布：update state=9)
	int updateActivityStateById(@Param("activityId") String activityId,@Param("state") String state);
	
	

}
