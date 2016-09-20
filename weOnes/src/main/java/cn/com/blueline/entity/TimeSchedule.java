package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动关联的时间管理
 * @author Zhaozhi
 *
 */
public class TimeSchedule implements Serializable{
	
	private static final long serialVersionUID = -7523243835862899455L;
	
	private String id;
	private Date effdt;
	private String status;
	private String timeDesc;//时间描述
	private String timeType;//日期类型:sin:单一时间,con:连续时间
	private Integer section;//时间期次:默认为1
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;//开始日期：2016-07-18
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;//结束日期：2016-08-01
	
	private String startTime;//开始时间 如:08:00
	private String endTime;//结束时间13:20
	
	private String createUser;//创建人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getEffdt() {
		return effdt;
	}
	public void setEffdt(Date effdt) {
		this.effdt = effdt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public Integer getSection() {
		return section;
	}
	public void setSection(Integer section) {
		this.section = section;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getTimeDesc() {
		return timeDesc;
	}
	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
	}
	@Override
	public String toString() {
		return "TimeSchedule [id=" + id + ", effdt=" + effdt + ", status="
				+ status + ", timeDesc=" + timeDesc + ", timeType=" + timeType
				+ ", section=" + section + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createUser=" + createUser
				+ ", createTime=" + createTime + "]";
	}
	
}
