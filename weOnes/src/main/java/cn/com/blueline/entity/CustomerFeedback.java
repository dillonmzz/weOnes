package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客户反馈实体
 * @author Zhaozhi
 *
 */
public class CustomerFeedback implements Serializable{
	
	private static final long serialVersionUID = 4746328252871830321L;
	
	private Long id;//反馈ID
	private String openId;//微信用户ID
	private String text;//反馈内容
	private String contacts;//联系方式
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;//反馈时间
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "CustomerFeedback [id=" + id + ", openId=" + openId + ", text="
				+ text + ", contacts=" + contacts + ", createTime="
				+ createTime + "]";
	}
    
}
