package cn.com.blueline.entity;

import java.io.Serializable;

/**
 * 	产品子类别实体
 *
 * @author Zhaozhi
 *
 */
public class ProductSubType implements Serializable{

	private static final long serialVersionUID = -4687670419163756185L;
	
	private Long id;
	private Long productTypeId;//父类别
	private String  subType;//子类别
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ProductSubType [id=" + id + ", productTypeId=" + productTypeId
				+ ", subType=" + subType + ", name=" + name + "]";
	}
	

}
