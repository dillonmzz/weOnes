package cn.com.blueline.entity;

import java.io.Serializable;
/*作者:Dillon
 *日期:2016年6月6日
 * 微信用户基本信息
 **/
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class WeChatUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4473496777892828605L;
	private String openId;
	private String country;
	private String province;
	private String city;
	private String sex;
	private String nickName;
	private String headImgUrl;
	private String language;
	private String privilege;
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;//关注时间
	
	private String unionId;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
