package cn.com.blueline.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.blueline.entity.Activity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ActivityDaoTest {


	//注入Dao实现类依赖
    @Resource
    private ActivityDao activituDao;

    @Test
    public void testQueryById() throws Exception {
        long id = 1011;
        Activity activity = activituDao.queryById(id);
        System.out.println(activity);
    }
    
     @Test
    public void testInsertActivity() throws Exception {
        Activity activity = new Activity();
        activity.setTitle("test");
        activity.setSubhead("test");
        activity.setType("food");
        activity.setThumbnails("http://img5.imgtn.bdimg.com/it/u=2356341204,1962240237&fm=21&gp=0.jpg");
        activity.setMinPeople(10);
        activity.setMaxPeople(100);
        activity.setProvince("安徽");
        activity.setCity("合肥");
        activity.setDistrict("包河");
        activity.setAddress("某某街道");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2016-06-01 13:00:01");
       // activity.setStartTime(date);
       // activity.setEndTime(dateFormat.parse("2016-07-01 00:00:01")); 
       // activity.setDetails("this is test");   
       int rowNum = activituDao.insertActivity(activity);
        System.out.println(rowNum);
    }
    
     @Test
    public void testUpdateActivity() throws Exception {

        Activity activity = activituDao.queryById(1013L);
        activity.setThumbnails("http://img5.imgtn.bdimg.com/it/u=2356341204,1962240237&fm=21&gp=0.jpg");
       activituDao.updateActivity(activity);
       System.out.println("修改后的结果="+activituDao.queryById(1013L));
    }
	

}
