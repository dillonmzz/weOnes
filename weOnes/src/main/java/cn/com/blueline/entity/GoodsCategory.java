package cn.com.blueline.entity;

import java.io.Serializable;

/**
 * 商品类别实体
 * @author Zhaozhi
 */

public class GoodsCategory implements Serializable {
	
	private static final long serialVersionUID = -1305675093514307457L;
	
	private Long id;//类别ID
	private String name;//类别描述
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "GoodsCategory [id=" + id + ", name=" + name + "]";
	}
}
