package cn.com.blueline.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * 用户关注收藏实体
*/
public class MyFavorite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -929456159618271934L;
	private long activityId;
	private String openId;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;
    private Integer state;
    
    //一个活动对应多个关注
    private Activity activity;

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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "MyFavorite [activityId=" + activityId + ", openId=" + openId
				+ ", createTime=" + createTime + ", state=" + state + "]";
	}
    
	
}
