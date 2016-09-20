package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动地点
 * @author Zhaozhi
 *
 */
public class ActivityAddress implements Serializable{
	private static final long serialVersionUID = 234360660960706031L;

	private String addressId;
	private Date effdt;
	private String status;
	//地点标题
	private String title;
	private String province;
	private String city;
	private String addressDesc;
	private Integer maxPeople;
	//场地设备
	private String device;
	//创建人
	private String createUser;
	//创建时间
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAddressDesc() {
		return addressDesc;
	}
	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}
	public Integer getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ActivityAddress [addressId=" + addressId + ", effdt=" + effdt
				+ ", status=" + status + ", title=" + title + ", province="
				+ province + ", city=" + city + ", addressDesc=" + addressDesc
				+ ", maxPeople=" + maxPeople + ", device=" + device
				+ ", createUser=" + createUser + ", createTime=" + createTime
				+ "]";
	}
	
}
