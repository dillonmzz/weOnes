package cn.com.blueline.utils;

/*作者:Dillon
 *日期:2016年6月12日
 **/

public class WxPayConfig {

	public static final String CHARSET = "UTF-8";
	public static final String TOKEN = "weones";
	public static final String APPID = "wx2ed9d729a4841027";
	public static final String APPSECRET = "e58dade93531df75ce76de90ae6b5abc";
	public static final String KEY = "2gyjGcxk5YJphMeDwrKNktuX4JxLdWKv";
	public static final String MCHID = "1352450402";
	public static final String NOTIFYURL = "http://www.fusingpoint.com/weOnes/wxpay/successfulPayProcess";
	public static final String UNIFIEDREQURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String OAUTHURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2ed9d729a4841027&redirect_uri=http%3a%2f%2fwww.fusingpoint.com%2fweOnes%2fweChatProcessController%2fweChatUserInfo&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	public static final int WXAMOUNTCONVERSION = 100;// 微信金额元转分比例
	public static final String ACCESSUPLOADFILEPATH = "http://localhost:8080/weOnes/uploads/";
	public static final int MAX_FILEHEIGHT = 400;
	public static final int MAX_FILEWIDTH = 640;
}
