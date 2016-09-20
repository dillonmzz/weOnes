package cn.com.blueline.utils;

import java.util.UUID;

public class UUIDGenerate {
	
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
        //去掉“-”符号 
		uuid = uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24); 
        return uuid;
	}

}
