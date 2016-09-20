package cn.com.blueline.dto;

import java.util.List;

import cn.com.blueline.entity.Goods;
import cn.com.blueline.entity.GoodsAttrKey;

public class GoodsInfo extends Goods{

	private static final long serialVersionUID = -3671060388732750136L;
	
	private String categoryChildName;
	
	private List<GoodsAttrKey> attrKeys;//商品属性key
	

	public String getCategoryChildName() {
		return categoryChildName;
	}

	public void setCategoryChildName(String categoryChildName) {
		this.categoryChildName = categoryChildName;
	}


	public List<GoodsAttrKey> getAttrKeys() {
		return attrKeys;
	}
	public void setAttrKeys(List<GoodsAttrKey> attrKeys) {
		this.attrKeys = attrKeys;
	}

}
