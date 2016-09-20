package cn.com.blueline.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

/**
 * 公共服务类
 *  功能:
 *         1、获取微信参数签名
 *         2、获取当前随机字符串并MD5
 *         3、获取当前时间戳
 *         
 * 
 * @author Dillon
 *
 */
public class WxSign {

	private static String characterEncoding = "UTF-8";

	
	public static String createSign(SortedMap<Object, Object> parameters,String key) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				System.out.println(k+"="+v);
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		System.out.println("拼接API密钥:"+sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}
	
	public static String getNonceStr() {
	    Random random = new Random();
	    return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	  }
	
	public static String getTimeStamp() {
	    return String.valueOf(System.currentTimeMillis() / 1000);
	  }
	
	public static void main(String[] args) {
		System.out.println(getTimeStamp());
	}

}
