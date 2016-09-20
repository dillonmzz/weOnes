package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 商品属性值实体
 * @author Zhaozhi
 */

public class GoodsAttrVal implements Serializable {
	
	private static final long serialVersionUID = -6782485583824535933L;
	
	private Long id;//商品属性值ID
	private Long attrKeyId;//商品属性ID
	private Long goodsId;//商品ID
	private Long symbol;//属性序号
	private String attrValue;//属性值
	
	private List<GoodsSku> goodsSkus;//商品sku
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAttrKeyId() {
		return attrKeyId;
	}
	public void setAttrKeyId(Long attrKeyId) {
		this.attrKeyId = attrKeyId;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getSymbol() {
		return symbol;
	}
	public void setSymbol(Long symbol) {
		this.symbol = symbol;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public List<GoodsSku> getGoodsSkus() {
		return goodsSkus;
	}
	public void setGoodsSkus(List<GoodsSku> goodsSkus) {
		this.goodsSkus = goodsSkus;
	}
	@Override
	public String toString() {
		return "GoodsAttrVal [id=" + id + ", attrKeyId=" + attrKeyId
				+ ", goodsId=" + goodsId + ", symbol=" + symbol
				+ ", attrValue=" + attrValue + "]";
	}
	
	
}
