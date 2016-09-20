package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品评论表
 * @author Zhaozhi
 *
 */
public class ProductCollectComment implements Serializable{
	
	private static final long serialVersionUID = 7153932065431549494L;
	
	private Long id;//评论ID
	private String openId;//微信用户ID
	private Long productCollectId;//产品ID
	private String commentText;//评论内容
	private Long parentId;//评论父节点ID
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;//评论时间
    
    private String relativeDateFormat;//时间显示格式
    
    private WeChatUser weChatUser;//一条评论对应一个微信用户
    
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
	public Long getProductCollectId() {
		return productCollectId;
	}
	public void setProductCollectId(Long productCollectId) {
		this.productCollectId = productCollectId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public WeChatUser getWeChatUser() {
		return weChatUser;
	}
	public void setWeChatUser(WeChatUser weChatUser) {
		this.weChatUser = weChatUser;
	}
	
	public String getRelativeDateFormat() {
		return relativeDateFormat;
	}
	public void setRelativeDateFormat(String relativeDateFormat) {
		this.relativeDateFormat = relativeDateFormat;
	}
	@Override
	public String toString() {
		return "ProductCollectComment [id=" + id + ", openId=" + openId
				+ ", productCollectId=" + productCollectId + ", commentText="
				+ commentText + ", parentId=" + parentId + ", createTime="
				+ createTime + ", relativeDateFormat=" + relativeDateFormat
				+ "]";
	}
    
}
