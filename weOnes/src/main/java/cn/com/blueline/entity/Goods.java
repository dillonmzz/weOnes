package cn.com.blueline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商品发布实体
 * @author Zhaozhi
 */

public class Goods implements Serializable {
	
	private static final long serialVersionUID = 7760863293592063413L;
	
	private Long id;//商品ID
	private Long categoryId;//父种类ID
	private Long categoryChildId;//子种类ID
	private String name;//名称
	private String title;//标题
	private String goodsPhotos;//图片地址
	private String goodsPreviewPhoto;//封面图地址
	private String province;//省份
	private String city;//市
	private String district;//区
	private String address;//详细地点
	private BigDecimal price;//一口价
	private String isJoinPromotion;//是否参与促销活动 Y:参加 N:不参加(默认)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date promotionStartTime;//促销开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date promotionEndTime;//促销结束时间
	private String isPurchase;//是否限制购买量 N:限制(默认) Y:不限制
	private Long purchaseQuantity;//限购数量
	private String isNewGoods;//是否新品 Y:是(默认) N:不是
	private String isHotGoods;//是否热门 Y:是  N:不是(默认)
	private String isBookGoods;//是否预约 Y:必须预约  N:无需预约(默认)
	private String introduction;//详情介绍
	private String createUser;//创建人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	private Long stateCode;//商品状态码 100:未发布(默认) 200:已发布/上架 300:下架 900:用户删除
	
	private List<GoodsAttrVal> goodsAttrs;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCategoryChildId() {
		return categoryChildId;
	}

	public void setCategoryChildId(Long categoryChildId) {
		this.categoryChildId = categoryChildId;
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

	public String getGoodsPhotos() {
		return goodsPhotos;
	}

	public void setGoodsPhotos(String goodsPhotos) {
		this.goodsPhotos = goodsPhotos;
	}

	public String getGoodsPreviewPhoto() {
		return goodsPreviewPhoto;
	}

	public void setGoodsPreviewPhoto(String goodsPreviewPhoto) {
		this.goodsPreviewPhoto = goodsPreviewPhoto;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getIsJoinPromotion() {
		return isJoinPromotion;
	}

	public void setIsJoinPromotion(String isJoinPromotion) {
		this.isJoinPromotion = isJoinPromotion;
	}

	public Date getPromotionStartTime() {
		return promotionStartTime;
	}

	public void setPromotionStartTime(Date promotionStartTime) {
		this.promotionStartTime = promotionStartTime;
	}

	public Date getPromotionEndTime() {
		return promotionEndTime;
	}

	public void setPromotionEndTime(Date promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}

	public Long getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Long purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(String isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	public String getIsHotGoods() {
		return isHotGoods;
	}

	public void setIsHotGoods(String isHotGoods) {
		this.isHotGoods = isHotGoods;
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

	public Long getStateCode() {
		return stateCode;
	}

	public void setStateCode(Long stateCode) {
		this.stateCode = stateCode;
	}

	public String getIsBookGoods() {
		return isBookGoods;
	}

	public void setIsBookGoods(String isBookGoods) {
		this.isBookGoods = isBookGoods;
	}
	public String getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(String isPurchase) {
		this.isPurchase = isPurchase;
	}
	public List<GoodsAttrVal> getGoodsAttrs() {
		return goodsAttrs;
	}
	public void setGoodsAttrs(List<GoodsAttrVal> goodsAttrs) {
		this.goodsAttrs = goodsAttrs;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", categoryId=" + categoryId
				+ ", categoryChildId=" + categoryChildId + ", name=" + name
				+ ", title=" + title + ", goodsPhotos=" + goodsPhotos
				+ ", goodsPreviewPhoto=" + goodsPreviewPhoto + ", province="
				+ province + ", city=" + city + ", district=" + district
				+ ", address=" + address + ", price=" + price
				+ ", isJoinPromotion=" + isJoinPromotion
				+ ", promotionStartTime=" + promotionStartTime
				+ ", promotionEndTime=" + promotionEndTime + ", isPurchase="
				+ isPurchase + ", purchaseQuantity=" + purchaseQuantity
				+ ", isNewGoods=" + isNewGoods + ", isHotGoods=" + isHotGoods
				+ ", isBookGoods=" + isBookGoods + ", introduction="
				+ introduction + ", createUser=" + createUser + ", createTime="
				+ createTime + ", stateCode=" + stateCode + "]";
	}

}
