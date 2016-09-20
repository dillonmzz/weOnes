package cn.com.blueline.entity;

import java.io.Serializable;
/*作者:Dillon
 *日期:2016年6月6日
 * 微信用户基本信息
 **/
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 后台系统用户信息
 * @author Zhaozhi
 *
 */
public class SystemUser implements Serializable{
	
	private static final long serialVersionUID = -2826650798294574159L;

	private Long id;//用户编号
	private String userName;//用户名
	private String password;//密码
	private String unionid;
	private String openid;
	private String imgurl;//头像logo
	private String nickName;//昵称
	private String realName;//真实姓名
	private String idCard;//身份证号码
	
	private String mobile;
	private String email;
	private String province;
	private String city;
	private String address;
	private String isAuthentication;//是否认证过,Y已认证/N未认证 新用户默认为N
	private String certificateImg;//认证的图片地址
	private String introduction;//简介
	private String roleId;//角色ID
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;//关注时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsAuthentication() {
		return isAuthentication;
	}

	public void setIsAuthentication(String isAuthentication) {
		this.isAuthentication = isAuthentication;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCertificateImg() {
		return certificateImg;
	}

	public void setCertificateImg(String certificateImg) {
		this.certificateImg = certificateImg;
	}

	@Override
	public String toString() {
		return "SystemUser [id=" + id + ", userName=" + userName
				+ ", password=" + password + ", unionid=" + unionid
				+ ", openid=" + openid + ", imgurl=" + imgurl + ", nickName="
				+ nickName + ", realName=" + realName + ", idCard=" + idCard
				+ ", mobile=" + mobile + ", email=" + email + ", province="
				+ province + ", city=" + city + ", address=" + address
				+ ", isAuthentication=" + isAuthentication
				+ ", certificateImg=" + certificateImg
				+ ", introduction=" + introduction + ", roleId=" + roleId
				+ ", createTime=" + createTime + "]";
	}

}
