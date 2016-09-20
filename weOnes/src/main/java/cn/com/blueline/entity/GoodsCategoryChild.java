package cn.com.blueline.entity;

import java.io.Serializable;

/**
 *商品子类别实体
 * @author Zhaozhi
 */

public class GoodsCategoryChild extends GoodsCategory implements Serializable {
	
	private static final long serialVersionUID = 6325016937690814304L;
	
	private Long parentId;//父类别ID

	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}
