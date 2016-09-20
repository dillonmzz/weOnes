package cn.com.blueline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 商品SKU实体
 * @author Zhaozhi
 */

public class GoodsSku implements Serializable {
	
	private static final long serialVersionUID = -29440432364015538L;
	
	private Long skuId;//商品SKU ID
	private Long goodsId;//商品ID
	private String attrSymbolPath;//属性组合路径
	private BigDecimal oldPrice;//原价
	private BigDecimal newPrice;//现价
	private BigDecimal promotionPrice;//促销价
	private Long freight;//运费
	private Long stock;//库存量
	
	
	public Long getSkuId() {
		return skuId;
	}


	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}


	public Long getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}


	public String getAttrSymbolPath() {
		return attrSymbolPath;
	}


	public void setAttrSymbolPath(String attrSymbolPath) {
		this.attrSymbolPath = attrSymbolPath;
	}


	public BigDecimal getOldPrice() {
		return oldPrice;
	}


	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}


	public BigDecimal getNewPrice() {
		return newPrice;
	}


	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}


	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}


	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}


	public Long getFreight() {
		return freight;
	}


	public void setFreight(Long freight) {
		this.freight = freight;
	}


	public Long getStock() {
		return stock;
	}


	public void setStock(Long stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "GoodsSku [skuId=" + skuId + ", goodsId=" + goodsId
				+ ", attrSymbolPath=" + attrSymbolPath + ", oldPrice="
				+ oldPrice + ", newPrice=" + newPrice + ", promotionPrice="
				+ promotionPrice + ", freight=" + freight + ", stock=" + stock
				+ "]";
	}
	
	
	
	
	
	
	
}
