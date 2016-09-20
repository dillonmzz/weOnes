package cn.com.blueline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * 活动实体
*/
public class Activity implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 2365539461094208602L;

private long activityId;

//标题
private String title;
//副标题
private String subhead;
//活动类别
private String type;

//活动略缩图地址
private String thumbnails;

//活动人数(最少)
private Integer minPeople;
//活动人数(最多)
private Integer maxPeople;

//活动地址
private String province;
private String city;
private String district;
private String address;

//活动开始时间
//@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
//private Date startTime;
//活动介绍时间
//@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
//private Date endTime;

//活动详情HTML
private String detailsHtml;
//活动详情CONTENT
private String detailsContent;

//现价
private BigDecimal newPrice;
//原价
private BigDecimal oldPrice;

//创建/修改时间
private Date createTime;

//发布状态  0:未发布  1:已发布   9:管理员已删除
private String state;

//活动排序
private Integer activityOrder;

//是否推荐至首页
private String  isRecommend;

public Activity() {
	super();
	
}




public long getActivityId() {
	return activityId;
}

public void setActivityId(long activityId) {
	this.activityId = activityId;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getSubhead() {
	return subhead;
}

public void setSubhead(String subhead) {
	this.subhead = subhead;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getThumbnails() {
	return thumbnails;
}

public void setThumbnails(String thumbnails) {
	this.thumbnails = thumbnails;
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

/*
public int getMinPeople() {
	return minPeople;
}*/

public Integer getMinPeople() {
	return minPeople;
}


public void setMinPeople(Integer minPeople) {
	this.minPeople = minPeople;
}

/*
public int getMaxPeople() {
	return maxPeople;
}*/

public Integer getMaxPeople() {
	return maxPeople;
}

public void setMaxPeople(Integer maxPeople) {
	this.maxPeople = maxPeople;
}

public Date getCreateTime() {
	return createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

public String getDetailsHtml() {
	return detailsHtml;
}

public void setDetailsHtml(String detailsHtml) {
	this.detailsHtml = detailsHtml;
}


public String getDetailsContent() {
	return detailsContent;
}


public void setDetailsContent(String detailsContent) {
	this.detailsContent = detailsContent;
}



public BigDecimal getNewPrice() {
	return newPrice;
}


public void setNewPrice(BigDecimal newPrice) {
	this.newPrice = newPrice;
}




public BigDecimal getOldPrice() {
	return oldPrice;
}




public void setOldPrice(BigDecimal oldPrice) {
	this.oldPrice = oldPrice;
}




public String getState() {
	return state;
}




public void setState(String state) {
	this.state = state;
}



public Integer getActivityOrder() {
	return activityOrder;
}




public void setActivityOrder(Integer activityOrder) {
	this.activityOrder = activityOrder;
}




public String getIsRecommend() {
	return isRecommend;
}




public void setIsRecommend(String isRecommend) {
	this.isRecommend = isRecommend;
}




@Override
public String toString() {
	return "Activity [activityId=" + activityId + ", title=" + title
			+ ", subhead=" + subhead + ", type=" + type + ", thumbnails="
			+ thumbnails + ", minPeople=" + minPeople + ", maxPeople="
			+ maxPeople + ", province=" + province + ", city=" + city
			+ ", district=" + district + ", address=" + address
			+ ", detailsHtml=" + detailsHtml + ", detailsContent="
			+ detailsContent + ", newPrice=" + newPrice + ", oldPrice="
			+ oldPrice + ", createTime=" + createTime + ", state=" + state
			+ ", activityOrder=" + activityOrder + ", isRecommend="
			+ isRecommend + "]";
}

}
