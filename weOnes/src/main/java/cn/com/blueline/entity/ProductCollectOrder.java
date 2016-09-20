package cn.com.blueline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * 微信下订单&支付订单表
 */
public class ProductCollectOrder implements Serializable {

	private static final long serialVersionUID = -36574967557286907L;

	private String transactionId; // 交易单号，微信自动生成，4000482001201606137206594300
	private String outTradeNo;// 商户单号,业务系统生成,20160613041825057
	private long collectId;// 产品ID
	private String timeId;// 时间ID
	private Integer section;// 时间期次
	private String openId;
	private String userPhone;
	private String userName;
	private String userAddr;
	private BigDecimal price;// 价格(单位:分)
	private Integer quantity;// 购买数量
	private BigDecimal totalFee;// 总计费用(单位:分)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;// 预定时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;// 支付时间
	private Integer state;// 状态标识, 0:未支付但用户预定了 1:预定并且已付款 2:已发货 9:用户已删除
	private String remark;

	// 一个订单只属于一个产品,多对一
	private ProductCollect collect;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public long getCollectId() {
		return collectId;
	}

	public void setCollectId(long collectId) {
		this.collectId = collectId;
	}

	public String getTimeId() {
		return timeId;
	}

	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ProductCollect getCollect() {
		return collect;
	}

	public void setCollect(ProductCollect collect) {
		this.collect = collect;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductCollectOrder [transactionId=" + transactionId
				+ ", outTradeNo=" + outTradeNo + ", collectId=" + collectId
				+ ", timeId=" + timeId + ", section=" + section + ", openId="
				+ openId + ", userPhone=" + userPhone + ", userName="
				+ userName + ", userAddr=" + userAddr + ", price=" + price
				+ ", quantity=" + quantity + ", totalFee=" + totalFee
				+ ", createTime=" + createTime + ", payTime=" + payTime
				+ ", state=" + state + ", remark=" + remark + ", collect="
				+ collect + "]";
	}

}
