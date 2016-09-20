package cn.com.blueline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品发布实体
 * 
 * @author Zhaozhi
 *
 */
public class ProductCollect implements Serializable {
	
	private static final long serialVersionUID = 1289126185731750713L;
	
	private Long id;
	private String timeId;//时间ID
	private BigDecimal price;//价格
	private String subjectId;//主题ID
	private String talentId;//达人ID
	private String placeId;//场地ID
	private String province;//省
	private String city;//市
	private String district;//区
	private String address;//详情地址
	private Integer minPeople;//最少人数
	private Integer maxPeople;//最多人数
	private String state;//状态,0:未审核  1:已拒绝  2:已审核  9:已删除

	private String createUser;// 创建人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;// 创建时间
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTimeId() {
		return timeId;
	}
	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getTalentId() {
		return talentId;
	}
	public void setTalentId(String talentId) {
		this.talentId = talentId;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public Integer getMinPeople() {
		return minPeople;
	}
	public void setMinPeople(Integer minPeople) {
		this.minPeople = minPeople;
	}
	public Integer getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ProductCollect [id=" + id + ", timeId=" + timeId + ", price="
				+ price + ", subjectId=" + subjectId + ", talentId=" + talentId
				+ ", placeId=" + placeId + ", province=" + province + ", city="
				+ city + ", district=" + district + ", address=" + address
				+ ", minPeople=" + minPeople + ", maxPeople=" + maxPeople
				+ ", state=" + state + ", createUser=" + createUser
				+ ", createTime=" + createTime + "]";
	}
	
}
