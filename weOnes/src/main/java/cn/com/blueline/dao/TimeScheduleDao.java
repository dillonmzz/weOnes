package cn.com.blueline.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.entity.TimeSchedule;

public interface TimeScheduleDao {
	
	
	/**
	 * 保存时间表
	 * 可能是多期次
	 * @param TimeManagement集合
	 * @return 1成功 0 失败
	 */
	int save(List<TimeSchedule> list);
	
	/**
	 * 根据当前用户查看时间表
	 * @param createUser 当前用户
	 * @return List<TimeManagement> 时间表集合
	 */
	List<TimeSchedule> queryScheduleByUser(String createUser);
	
	/**
	 * 用户角色:
	 *     根据时间类型与当前用户查询时间表
	 * @param timeType 时间类型
	 * @param createUser 当前用户
	 * @return
	 */
	List<TimeSchedule> findTimeScheduleByTimeType(@Param("timeType") String timeType,@Param("createUser")String createUser);
	
	
	/**
	 * 根据时间表ID 删除(修改)时间表
	 * (将status的状态修改为I,默认A为有效)
	 * @param id 时间表ID
	 * @return 1成功 0 失败
	 */
	int updateToInvalid(@Param("status") String status,@Param("id") String id);
	
	/**
	 * 根据时间ID和日期查询时间表
	 * @param id 时间ID
	 * @param startDate 日期
	 * @return
	 */
	List<TimeSchedule> queryTimeByDate(@Param("id") String id,@Param("startDate") String startDate);

}
