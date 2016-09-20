package cn.com.blueline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品订单实体
 * @author Zhaozhi 
 *
 */
public class GoodsOrder implements Serializable{

	private static final long serialVersionUID = 3444763560215430345L;
	
	private Long id;
	private String orderNo;//产品订单号(当前时间戳:20160728081138501)
	private Long goodsId;//商品ID
	private Long skuId;//SKU ID
	private String userName;//联系人姓名
	private String userPhone;//联系人手机
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;//开始日期：2016-07-18
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;//结束日期：2016-08-01
	private String startTime;//开始时间 如:08:00
	private String endTime;//结束时间13:20
	private String province;//省份
	private String city;//市
	private String district;//区
	private String address;//详细地点
	private BigDecimal price;//产品单价
	private Integer quantity;//购买数量
    private BigDecimal totalFee;//总计费用
    private BigDecimal freight;//运费
    private Integer orderState;//状态标识,10:预定了但未支付  20:已付款 30:退款中  40:已退款 50:已发货 60:已完成 
    private String deleteState;//删除状态 N:未删除(默认) Y:已删除
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date bookTime;//预定时间
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date payTime;//支付时间
    private String remark;//备注
    private String transactionId;//交易单号
    private String createUser;//创建人
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public String getDeleteState() {
		return deleteState;
	}
	public void setDeleteState(String deleteState) {
		this.deleteState = deleteState;
	}
	public Date getBookTime() {
		return bookTime;
	}
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@Override
	public String toString() {
		return "GoodsOrder [id=" + id + ", orderNo=" + orderNo + ", goodsId="
				+ goodsId + ", skuId=" + skuId + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", province=" + province + ", city="
				+ city + ", district=" + district + ", address=" + address
				+ ", price=" + price + ", quantity=" + quantity + ", totalFee="
				+ totalFee + ", freight=" + freight + ", orderState="
				+ orderState + ", deleteState=" + deleteState + ", bookTime="
				+ bookTime + ", payTime=" + payTime + ", remark=" + remark
				+ ", transactionId=" + transactionId + ", createUser="
				+ createUser + "]";
	}
}
