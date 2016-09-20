package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 商品属性实体
 * @author Zhaozhi
 */

public class GoodsAttrKey implements Serializable {
	
	private static final long serialVersionUID = 985211492108079009L;
	
	private Long attrKeyId;//商品属性ID
	private String attrName;//属性名称
	
	private List<GoodsAttrVal> attrVals;//属性值
	public Long getAttrKeyId() {
		return attrKeyId;
	}
	public void setAttrKeyId(Long attrKeyId) {
		this.attrKeyId = attrKeyId;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public List<GoodsAttrVal> getAttrVals() {
		return attrVals;
	}
	public void setAttrVals(List<GoodsAttrVal> attrVals) {
		this.attrVals = attrVals;
	}
}
