package cn.com.blueline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * 微信支付订单表
*/
public class WxPayOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4359715444410413204L;
	private String transactionId; //交易单号，微信自动生成，4000482001201606137206594300
	private String outTradeNo;//商户单号,业务系统生成,20160613041825057 
	private long activityId;
	private String openId;
    private String userPhone;
    private String userName;
    private String userAddr;
    private Integer quantity;
    private BigDecimal totalFee;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date timeEnd;
    private Integer state;
    private String  remark; 
    
    //一个订单只属于一个活动,多对一
    private Activity activity;
    
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
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
	public long getActivityId() {
		return activityId;
	}
	public void setActivityId(long activityId) {
		this.activityId = activityId;
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

	public Date getTimeEnd() {
		return timeEnd;
	}
	
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
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
	
	
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	@Override
	public String toString() {
		return "WxPayOrder [transactionId=" + transactionId + ", outTradeNo="
				+ outTradeNo + ", activityId=" + activityId + ", openId="
				+ openId + ", userPhone=" + userPhone + ", userName="
				+ userName + ", userAddr=" + userAddr + ", quantity="
				+ quantity + ", totalFee=" + totalFee + ", timeEnd=" + timeEnd
				+ ", state=" + state + ", remark=" + remark + "]";
	}
	
}
