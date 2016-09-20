package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 产品收藏/取消 实体
 * @author Zhaozhi
 *
 */
public class ProductCollectFavorite implements Serializable{
	
	
	
	private static final long serialVersionUID = 6831806493189944430L;
	
	private Long id;
	private String openId;//微信用户ID
	private Long productCollectId;//产品ID
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;
    private Integer state;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ProductCollectFavorite [id=" + id + ", openId=" + openId
				+ ", productCollectId=" + productCollectId + ", createTime="
				+ createTime + ", state=" + state + "]";
	}
    
}
