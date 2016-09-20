package cn.com.blueline.entity;

import java.io.Serializable;

/**
 * 	产品类别实体
 *  subject 主题
 *  talent  达人
 *  place   场地
 * @author Zhaozhi
 *
 */
public class ProductType implements Serializable{

	private static final long serialVersionUID = 7276843322394654228L;
	
	private Long id;
	private String productType;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", productType=" + productType
				+ ", name=" + name + "]";
	}
	
}
