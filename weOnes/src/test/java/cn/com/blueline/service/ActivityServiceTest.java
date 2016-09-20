package cn.com.blueline.service;
/*作者:Dillon
 *日期:2016年5月31日
 **/
 
 import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.blueline.entity.Activity;
import cn.com.blueline.utils.WxPayConfig;

 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class ActivityServiceTest {
	 
	 @Autowired
	 private ActivityService activityService;
	 
	 
	 @Test
	 public void testGetActivityList(){
		 List<Activity> activities = activityService.getActivityList();
		 System.out.println("总记录数:"+activities.size());
		 for (Activity activity : activities) {
			 BigDecimal newPrice = activity.getNewPrice();
			System.out.println(newPrice.multiply(new BigDecimal(WxPayConfig.WXAMOUNTCONVERSION)).multiply(new BigDecimal(3)).intValue());
		}
		 
	 } 
	 /*
	 
	 
	 @Test
	 public void testFavoriteActivity(){
		 Long activityId = 1071L;
		 String openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		 Integer state = 1;
		 activityService.favoriteOrCancelActivity(openId,activityId, state);
	 }
	 
	 @Test
	 public void testSaveActivity(){
	        Activity activity = new Activity();
	        activity.setTitle("美味食物");
	        activity.setSubhead("美味食物");
	        activity.setType("food");
	        activity.setThumbnails("http://img5.imgtn.bdimg.com/it/u=2356341204,1962240237&fm=21&gp=0.jpg");
	        activity.setMinPeople(10);
	        activity.setMaxPeople(100);
	        activity.setProvince("安徽1");
	        activity.setCity("合肥1");
	        activity.setDistrict("包河1");
	        activity.setAddress("某某街道1");
	        Date date = new Date();
	        activity.setStartTime(date);
	        activity.setEndTime(date);
	        activity.setDetails("this is test");  
	        int insertCount = activityService.saveActivity(activity);
	        System.out.println("insertCount: " + insertCount);
	    }
	 */

	 
	 
	 
	 
	
	

}
