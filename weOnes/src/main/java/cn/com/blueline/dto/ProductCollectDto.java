package cn.com.blueline.dto;

import java.io.Serializable;


public class ProductCollectDto implements Serializable{

	private static final long serialVersionUID = -6608796735237598479L;
	
	//productCollectId
	private Long id;
	//时间ID 和期次确定具体的时间表
	private String timeId;//时间ID
	private String timeType;//时间类型
	private Integer timeSection;//时间期次 单期次=1 多期次>=1
	private String timeDesc;//时间显示
	
	private String city;//城市显示
	private String district;//区域显示
	private String address;//详细地址显示
	private String price;//价格显示
	
	private String name;
	private String title;
	private String coverPhoto;
	
	private String subjectId;//主题ID
	private String subjectName;//主题名称
	private String subjectTitle;//主题title
	private String subjectPhoto;//主题封面
	private String subjectDesc;//主题描述
	
	private String talentId;//达人ID
	private String talentName;//达人名称
	private String talentTitle;//达人title
	private String talentPhoto;//达人封面
	private String talentDesc;//达人描述
	
	private String placeId;//场地ID
	private String placeName;//场地名称
	private String placeTitle;//场地设备
	private String placePhoto;//场地封面
	private String placeDesc;//场地描述
	
	private Integer minPeople;
	private Integer maxPeople;
	
	private String createUser;
	
	private Integer state;//状态
	
	private String phone;//预定的电话
	private Integer quantity;//预定的数量
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTimeId() {
		return timeId;
	}
	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}
	public String getTimeDesc() {
		return timeDesc;
	}
	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public String getTalentDesc() {
		return talentDesc;
	}
	public void setTalentDesc(String talentDesc) {
		this.talentDesc = talentDesc;
	}
	public String getPlaceDesc() {
		return placeDesc;
	}
	public void setPlaceDesc(String placeDesc) {
		this.placeDesc = placeDesc;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getTalentId() {
		return talentId;
	}
	public void setTalentId(String talentId) {
		this.talentId = talentId;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSubjectPhoto() {
		return subjectPhoto;
	}
	public void setSubjectPhoto(String subjectPhoto) {
		this.subjectPhoto = subjectPhoto;
	}
	public String getTalentPhoto() {
		return talentPhoto;
	}
	public void setTalentPhoto(String talentPhoto) {
		this.talentPhoto = talentPhoto;
	}
	public String getPlacePhoto() {
		return placePhoto;
	}
	public void setPlacePhoto(String placePhoto) {
		this.placePhoto = placePhoto;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public String getTalentName() {
		return talentName;
	}
	public void setTalentName(String talentName) {
		this.talentName = talentName;
	}
	public String getTalentTitle() {
		return talentTitle;
	}
	public void setTalentTitle(String talentTitle) {
		this.talentTitle = talentTitle;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceTitle() {
		return placeTitle;
	}
	public void setPlaceTitle(String placeTitle) {
		this.placeTitle = placeTitle;
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
	public String getCoverPhoto() {
		return coverPhoto;
	}
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}
	
	public Integer getMinPeople() {
		return minPeople;
	}
	public void setMinPeople(Integer minPeople) {
		this.minPeople = minPeople;
	}
	public Integer getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public Integer getTimeSection() {
		return timeSection;
	}
	public void setTimeSection(Integer timeSection) {
		this.timeSection = timeSection;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductCollectDto [id=" + id + ", timeId=" + timeId
				+ ", timeType=" + timeType + ", timeSection=" + timeSection
				+ ", timeDesc=" + timeDesc + ", city=" + city + ", district="
				+ district + ", address=" + address + ", price=" + price
				+ ", name=" + name + ", title=" + title + ", coverPhoto="
				+ coverPhoto + ", subjectId=" + subjectId + ", subjectName="
				+ subjectName + ", subjectTitle=" + subjectTitle
				+ ", subjectPhoto=" + subjectPhoto + ", subjectDesc="
				+ subjectDesc + ", talentId=" + talentId + ", talentName="
				+ talentName + ", talentTitle=" + talentTitle
				+ ", talentPhoto=" + talentPhoto + ", talentDesc=" + talentDesc
				+ ", placeId=" + placeId + ", placeName=" + placeName
				+ ", placeTitle=" + placeTitle + ", placePhoto=" + placePhoto
				+ ", placeDesc=" + placeDesc + ", minPeople=" + minPeople
				+ ", maxPeople=" + maxPeople + ", createUser=" + createUser
				+ ", state=" + state + ", phone=" + phone + ", quantity="
				+ quantity + "]";
	}
	
}
