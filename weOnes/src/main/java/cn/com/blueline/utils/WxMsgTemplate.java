package cn.com.blueline.utils;

import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

public class WxMsgTemplate {
	
	//客户预约通知ID
	public static final String CUSTOMERORDER_TEMPLATEID="LuSE2OjA1ePI0PD3JDKfRTnZS7l_-47w5-pBgxyQCy8";
	//审核提醒ID
	public static final String APPROVAL_TEMPLATEID="yzAqYiEufGQsJjtRBHLo5L8df5S6rdaYWquC7LuQIaY";
	//报名成功通知ID
	public static final String REGIST_TEMPLATEID="nBbi6FW__hf_ZGy98Q1uP8cA8HgJgEbSqI3gDZH3fWI";
	//点击消息后跳转的URL
	public static final String CLICKURL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2ed9d729a4841027&redirect_uri=http%3a%2f%2fwww.fusingpoint.com%2fweOnes%2fweChatProcessController%2fweChatUserInfov2&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	
	
	/**
	 * 审核通过消息
	 * @param first 标题消息内容
	 * @param keyword1 消息类别
	 * @param keyword2 发送用户
	 * @param keyword3 通知内容
	 * @param keyword4 通知时间
	 * @param remark 说明
	 * @return 消息json对象
	 */
	public static JSONObject approvalJsonMsg(String first, String keyword1, String keyword2,String keyword3,String keyword4,String remark){
        JSONObject json = new JSONObject();
        try {
            JSONObject jsonFirst = new JSONObject();
            jsonFirst.put("value", first);
            jsonFirst.put("color", "#173177");
            json.put("first", jsonFirst);
            JSONObject jsonKeyword1 = new JSONObject();
            jsonKeyword1.put("value", keyword1);
            jsonKeyword1.put("color", "#173177");
            json.put("keyword1", jsonKeyword1);
            JSONObject jsonKeyword2 = new JSONObject();
            jsonKeyword2.put("value", keyword2);
            jsonKeyword2.put("color", "#173177");
            json.put("keyword2", jsonKeyword2);
            JSONObject jsonKeyword3 = new JSONObject();
            jsonKeyword3.put("value", keyword3);
            jsonKeyword3.put("color", "#173177");
            json.put("keyword3", jsonKeyword3);
            JSONObject jsonKeyword4 = new JSONObject();
            jsonKeyword4.put("value", keyword4);
            jsonKeyword4.put("color", "#173177");
            json.put("keyword4", jsonKeyword4);
            JSONObject jsonRemark = new JSONObject();
            jsonRemark.put("value", remark);
            jsonRemark.put("color", "#173177");
            json.put("remark", jsonRemark);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
	
	
	/**
	 * 客户预约通知
	 * @param first 标题消息内容
	 * @param keyword1 客户姓名
	 * @param keyword2 客户手机
	 * @param keyword3 预约时间
	 * @param keyword4 预约内容
	 * @param remark 说明
	 * @return
	 */
	public static JSONObject customerOrderJsonMsg(String first, String keyword1, String keyword2,String keyword3,String keyword4,String remark){
        JSONObject json = new JSONObject();
        try {
            JSONObject jsonFirst = new JSONObject();
            jsonFirst.put("value", first);
            jsonFirst.put("color", "#173177");
            json.put("first", jsonFirst);
            JSONObject jsonKeyword1 = new JSONObject();
            jsonKeyword1.put("value", keyword1);
            jsonKeyword1.put("color", "#173177");
            json.put("keyword1", jsonKeyword1);
            JSONObject jsonKeyword2 = new JSONObject();
            jsonKeyword2.put("value", keyword2);
            jsonKeyword2.put("color", "#173177");
            json.put("keyword2", jsonKeyword2);
            JSONObject jsonKeyword3 = new JSONObject();
            jsonKeyword3.put("value", keyword3);
            jsonKeyword3.put("color", "#173177");
            json.put("keyword3", jsonKeyword3);
            JSONObject jsonKeyword4 = new JSONObject();
            jsonKeyword4.put("value", keyword4);
            jsonKeyword4.put("color", "#173177");
            json.put("keyword4", jsonKeyword4);
            JSONObject jsonRemark = new JSONObject();
            jsonRemark.put("value", remark);
            jsonRemark.put("color", "#173177");
            json.put("remark", jsonRemark);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
	
	/**
	 * 发送模板信息给用户
	 * @param touser 用户的openid
	 * @param templat_id 消息模版ID
	 * @param access_token 发送模版消息时的access_token
	 * @param topcolor 模板字体的颜色
	 * @param data 模板详情变量 Json格式
	 * @return
	 * @throws Exception 
	 */
	public static String sendWechatmsgToUser(String touser, String templat_id, String access_token, String topcolor, JSONObject data) throws Exception{
	        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
			String templateUrl = tmpurl.replace("ACCESS_TOKEN", access_token);
	        JSONObject json = new JSONObject();
	        try {
	            json.put("touser", touser);
	            json.put("template_id", templat_id);
	            json.put("url", CLICKURL);
	            json.put("topcolor", topcolor);
	            json.put("data", data);
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        System.out.println("发给微信模版消息的json数据:"+json.toString());
	        String sendTemplateMsgResult = CommonUtils.sendPostRequest(templateUrl, json.toString(),"utf-8");
	        try {
	            JSONObject resultJson = new JSONObject(sendTemplateMsgResult);
	            String errmsg = (String) resultJson.get("errmsg");
	            if(!"ok".equals(errmsg)){  //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
	                return "error";
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return "success";
	    }
	
	
	/**
	 * 获取AccessToken
	 * @return
	 */
	public static String getAccessToken(){
		 //获取access_token
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WxPayConfig.APPID+"&secret="+WxPayConfig.APPSECRET;
        InputStream is;
		try {
			is = CommonUtils.doHttpGetRequest(url, "");
			String result = CommonUtils.convertStreamToString(is);
			JSONObject jsonObj = new JSONObject(result);
			String access_token = (String) jsonObj.get("access_token");
			return access_token;
		} catch (Exception e) {
			return null;
		}
		
	}
	

}
