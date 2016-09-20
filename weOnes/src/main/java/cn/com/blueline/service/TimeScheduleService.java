package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.TimeSchedule;

public interface TimeScheduleService {

	/**
	 * 查看当前用户下的时间表
	 * @param createUser 当前用户
	 * @return List<TimeManagement> 时间表集合
	 */
	List<TimeSchedule> queryTimeScheduleByUser(String createUser);
	
	/**
	 * 用户角色:
	 *     根据时间类型与当前用户查询时间表
	 * @param timeType 时间类型
	 * @param createUser 当前用户
	 * @return
	 */
	List<TimeSchedule> findTimeScheduleByTimeType(String timeType,String createUser);

	/**
	 * 保存时间表
	 * @param list 时间表集合
	 * @return 1成功 0 失败
	 */
	int save(List<TimeSchedule> list);
	
	/**
	 * 根据id删除(修改)时间表
	 * (将status的状态修改为I,默认A为有效)
	 * @param id 时间表ID
	 * @return 1成功 0 失败
	 */
	int updateToInvalid(String status,String id);
	
	/**
	 * 修改时间表
	 * 先将历史记录状态改为I,然后保存新的记录
	 * @param list 时间表集合
	 * @return 1成功 0 失败
	 */
	int updateTimeManagement(List<TimeSchedule> list);
	
	
	/**
	 * 根据时间ID和日期查询时间表
	 * @param id 时间ID
	 * @param startDate 日期
	 * @return
	 */
	List<TimeSchedule> queryTimeByDate(String id,String startDate);
}
