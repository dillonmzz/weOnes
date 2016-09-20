package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品管理(主题、达人、场地)
 * 
 * @author Zhaozhi
 *
 */
public class Product implements Serializable {

	private static final long serialVersionUID = -1402204168483878022L;

	private String id;
	private String productType;// 产品类型 主题:subject、达人:Talent、场地:place
	private String subType;//产品子类型
	private Date effdt;
	private String status;
	private String name;
	private String title;
	private String productPhoto;
	private String city;
	private String address;// 详细地点
	private String introduction;// 详情介绍

	private String createUser;// 创建人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;// 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productType=" + productType
				+ ", subType=" + subType + ", effdt=" + effdt + ", status="
				+ status + ", name=" + name + ", title=" + title
				+ ", productPhoto=" + productPhoto + ", city=" + city
				+ ", address=" + address + ", introduction=" + introduction
				+ ", createUser=" + createUser + ", createTime=" + createTime
				+ "]";
	}

}
