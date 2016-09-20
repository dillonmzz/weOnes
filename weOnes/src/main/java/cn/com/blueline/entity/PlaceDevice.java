package cn.com.blueline.entity;

import java.io.Serializable;

/**
 * 	场地设备实体
 * @author Zhaozhi
 *
 */
public class PlaceDevice implements Serializable{

	private static final long serialVersionUID = 197792058944106476L;
	
	private Long id;
	private String placeDevice;//场地设备代码
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaceDevice() {
		return placeDevice;
	}
	public void setPlaceDevice(String placeDevice) {
		this.placeDevice = placeDevice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "PlaceDevice [id=" + id + ", placeDevice=" + placeDevice
				+ ", name=" + name + "]";
	}
	
}
