package cn.com.blueline.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
/*作者:Dillon
 *日期:2016年6月6日
 **/
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class CommonUtils {
	
	
	public static InputStream doHttpGetRequest(String url,String method) throws Exception{      

		 HttpClient httpclient = new DefaultHttpClient();
		 HttpGet httpgets = new HttpGet(url);
		 HttpResponse resData = httpclient.execute(httpgets);
		 HttpEntity entity = resData.getEntity();
		 InputStream instreams = null;
		 if (entity != null) {
			  instreams = entity.getContent();
			  httpgets.abort();
		 }
	     
        return instreams;
    }  
	
	
	public static String convertStreamToString(InputStream is) throws IOException {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n"); 
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }
	
	
	/** 
     * 发送HTTP_POST请求 
     * @see 1)该方法允许自定义任何格式和内容的HTTP请求报文体 
     * @see 2)该方法会自动关闭连接,释放资源 
     * @see 3)方法内设置了连接和读取超时时间,单位为毫秒,超时或发生其它异常时方法会自动返回"通信失败"字符串 
     * @see 4)请求参数含中文等特殊字符时,可直接传入本方法,并指明其编码字符集encodeCharset参数,方法内部会自动对其转码 
     * @see 5)该方法在解码响应报文时所采用的编码,取自响应消息头中的[Content-Type:text/html; charset=GBK]的charset值 
     * @see   若响应消息头中未指定Content-Type属性,则会使用HttpClient内部默认的ISO-8859-1 
     * @param reqURL        请求地址 
     * @param reqData       请求参数,若有多个参数则应拼接为param11=value11&22=value22&33=value33的形式 
     * @param encodeCharset 编码字符集,编码请求数据时用之,此参数为必填项(不能为""或null) 
     * @return 远程主机响应正文 
     */  
    public static String sendPostRequest(String reqURL, String reqData, String encodeCharset){  
        String reseContent = "通信失败";  
        HttpClient httpClient = new DefaultHttpClient();  
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);  
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);  
        HttpPost httpPost = new HttpPost(reqURL);  
        //由于下面使用的是new StringEntity(....),所以默认发出去的请求报文头中CONTENT_TYPE值为text/plain; charset=ISO-8859-1  
        //这就有可能会导致服务端接收不到POST过去的参数,比如运行在Tomcat6.0.36中的Servlet,所以我们手工指定CONTENT_TYPE头消息  
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + encodeCharset);  
        try{  
            httpPost.setEntity(new StringEntity(reqData==null?"":reqData, encodeCharset));
            HttpResponse response = httpClient.execute(httpPost);  
            HttpEntity entity = response.getEntity();  
            if (null != entity) {  
               // reseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
                reseContent = EntityUtils.toString(entity);
                EntityUtils.consume(entity);  
            }  
        } catch (ConnectTimeoutException cte){  
            System.out.println("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下"+cte);
        } catch (SocketTimeoutException ste){  
        	System.out.println("请求通信[" + reqURL + "]时读取超时,堆栈轨迹如下"+ste);  
        }catch(Exception e){  
        	System.out.println("请求通信[" + reqURL + "]时偶遇异常,堆栈轨迹如下"+e);  
        }finally{  
            httpClient.getConnectionManager().shutdown();  
        }  
        return reseContent;  
    } 
	
	public static String getCurrentTimetoString(){
		 DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
	     String currentTime = dateFormat.format(new Date(System.currentTimeMillis()));
	     return currentTime;
	}
	
	
	
	public static Long getCurrentTimetoLong(){
		 DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
	     Long currentTime = Long.parseLong(dateFormat.format(new Date(System.currentTimeMillis())));
	     return currentTime;
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentTimetoString());
	}

	
}
