package cn.com.blueline.service;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.utils.UUIDGenerate;


/**
 * @author Zhaozhi
 */
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class TimeScheduleServiceTest {
	 
	 @Autowired
	 private TimeScheduleService service;
	 
	 
	 
	 
	 @Test
	 public void queryTimeByDate() throws Exception{
		List<TimeSchedule> result = service.queryTimeByDate("11", "2016-07-25");
		System.out.println(result.size());
		for (TimeSchedule timeSchedule : result) {
			System.out.println(result.toString());
		}
	}
	 
	 /*
	 @Test
	 public void save() throws Exception{
		 //连续日期
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 //Date myDate1 = format.parse("2009-06-01");
		 List<TimeSchedule> list = new ArrayList<TimeSchedule>();
		 TimeSchedule timeManagement = new TimeSchedule();
		 timeManagement.setId(UUIDGenerate.getUUID());
		 //timeManagement.setActivityId(1115L);
		 timeManagement.setEffdt(new Date());
		 timeManagement.setStatus("A");
		 timeManagement.setTimeType("con");//创建联系时间表
		 timeManagement.setSection(1);
		 timeManagement.setStartDate(format.parse("2016-07-01"));
		 timeManagement.setEndDate(format.parse("2016-07-31"));
		 timeManagement.setStartTime("08:00");
		 timeManagement.setEndTime("10:00");
		 timeManagement.setCreateUser("dillon");//TODO 当前用户ID
		 timeManagement.setCreateTime(new Date());
		 
		 TimeSchedule timeManagement1 = new TimeSchedule();
		 timeManagement1.setId(timeManagement.getId());
		 //timeManagement1.setActivityId(1115L);
		 timeManagement1.setEffdt(new Date());
		 timeManagement1.setStatus("A");
		 timeManagement1.setTimeType("con");//创建联系时间表
		 timeManagement1.setSection(1);
		 timeManagement1.setStartDate(format.parse("2016-09-01"));
		 timeManagement1.setEndDate(format.parse("2016-09-30"));
		 timeManagement1.setStartTime("18:00");
		 timeManagement1.setEndTime("20:00");
		 timeManagement1.setCreateUser("dillon");//TODO 当前用户ID
		 timeManagement1.setCreateTime(new Date());
		 
		 list.add(timeManagement);
		 list.add(timeManagement1);
		 
		 //单一日期
		 TimeSchedule timeManagement2 = new TimeSchedule();
		 timeManagement2.setId(UUIDGenerate.getUUID());
		// timeManagement2.setActivityId(1115L);
		 timeManagement2.setEffdt(new Date());
		 timeManagement2.setStatus("A");
		 timeManagement2.setTimeType("sin");
		 timeManagement2.setSection(1);
		 timeManagement2.setStartDate(format.parse("2016-07-01"));
		// timeManagement2.setEndDate(format.parse("2016-07-31"));
		 timeManagement2.setStartTime("08:00");
		 timeManagement2.setEndTime("10:00");
		 
		 TimeSchedule timeManagement3 = new TimeSchedule();
		 timeManagement3.setId(timeManagement2.getId());
		// timeManagement3.setActivityId(1115L);
		 timeManagement3.setEffdt(new Date());
		 timeManagement3.setStatus("A");
		 timeManagement3.setTimeType("sin");
		 timeManagement3.setSection(2);
		 timeManagement3.setStartDate(format.parse("2016-09-01"));
		// timeManagement3.setEndDate(format.parse("2016-09-30"));
		 timeManagement3.setStartTime("18:00");
		 timeManagement3.setEndTime("20:00");
		// list.add(timeManagement2);
		// list.add(timeManagement3);
		 
		 
		 System.out.println(service.save(list));
	 } 
	  
	 
	 @Test
	 public void updateToInvalidTest(){
		 int rowNum = service.updateToInvalid("I", "f14094306c1f4676a2c42b4b281010d4", 1115l);
		 System.out.println(rowNum);
	 } */
	 
	 /*
	 @Test
	 public void updateActivityAddressTest() throws Exception{
		 //连续日期
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 //Date myDate1 = format.parse("2009-06-01");
		 List<TimeManagement> list = new ArrayList<TimeManagement>();
		 TimeManagement timeManagement = new TimeManagement();
		 timeManagement.setId("f14094306c1f4676a2c42b4b281010d4");
		 timeManagement.setActivityId(1115L);
		 timeManagement.setEffdt(new Date());
		 timeManagement.setStatus("A");
		 timeManagement.setTimeType("con");//创建联系时间表
		 timeManagement.setSection(1);
		 timeManagement.setStartDate(format.parse("2016-07-01"));
		 timeManagement.setEndDate(format.parse("2016-07-20"));
		 timeManagement.setStartTime("09:00");
		 timeManagement.setEndTime("11:00");
		 
		 TimeManagement timeManagement1 = new TimeManagement();
		 timeManagement1.setId(timeManagement.getId());
		 timeManagement1.setActivityId(1115L);
		 timeManagement1.setEffdt(new Date());
		 timeManagement1.setStatus("A");
		 timeManagement1.setTimeType("con");//创建联系时间表
		 timeManagement1.setSection(2);
		 timeManagement1.setStartDate(format.parse("2016-09-01"));
		 timeManagement1.setEndDate(format.parse("2016-09-20"));
		 timeManagement1.setStartTime("19:00");
		 timeManagement1.setEndTime("21:00");
		 
		 list.add(timeManagement);
		 list.add(timeManagement1);
		 
		 System.out.println(service.updateTimeManagement(list));
	 }
	 
	 @Test
	 public void queryTimeManagementByActivityId(){
		 List<TimeManagement> list = service.queryTimeManagementByActivityId(1115L);
		 System.out.println(list.size());
		 for (TimeManagement timeManagement : list) {
			System.out.println(timeManagement.toString());
		}
	 }*/
	 
	 
	
	

}
