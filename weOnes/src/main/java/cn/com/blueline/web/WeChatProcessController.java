package cn.com.blueline.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.WeChatUser;
import cn.com.blueline.service.WeChatUserService;
import cn.com.blueline.utils.CommonUtils;

/*作者:Dillon
 *日期:2016年6月3日
 **/
@Controller
@RequestMapping(value = "/weChatProcessController")
public class WeChatProcessController {

	private static final String TOKEN = "weones";
	private static final String APPID = "wx2ed9d729a4841027";
	private static final String APPSECRET = "e58dade93531df75ce76de90ae6b5abc";
	private static final String AESKEY = "jzvz9Y3JwLuTxUF4azYb3IKH97ckerJvFT81tg0wXoK";

	@Autowired
	private WeChatUserService weChatUserService;

	// 微信URL验证
	@RequestMapping(value = "/validateAuthor", method = RequestMethod.GET)
	public void validateAuthor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("weChat process...");
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		System.out.println("signature:" + signature);
		System.out.println("timestamp:" + timestamp);
		System.out.println("nonce:" + nonce);

		String[] str = { TOKEN, timestamp, nonce };
		// 字典序排序
		Arrays.sort(str);
		String bigStr = str[0] + str[1] + str[2];
		System.out.println("排序后的字符串:" + bigStr);
		// SHA1加密
		// String digest = new
		// SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		String digest = DigestUtils.shaHex(bigStr);

		// 确认请求来至微信
		if (digest.equals(signature)) {
			response.getWriter().print(echostr);
		}
	}

	/*
	 * 获取微信用户的基本信息,然后跳转至首页 用户若同意,获取用户基本信息 流程: 
	 * 1、获取code 
	 * 2、根据code获取access_token
	 * 3、根据access_token获取用户信息
	 */
	@RequestMapping(value = "/weChatUserInfo", method = RequestMethod.GET)
	public String getWechatUserInfo(Model model, HttpServletRequest request,
			RedirectAttributes attr) throws Exception {
		System.out.println("正在获取微信用户信息...");
		// 1、获取code
		String code = request.getParameter("code");
		// 2、根据code，请求接口并获取access_token
		String doProcessurl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ APPID
				+ "&secret="
				+ APPSECRET
				+ "&code="
				+ code
				+ "&grant_type=authorization_code";
		InputStream is = CommonUtils.doHttpGetRequest(doProcessurl, "");
		// 获取并处理 响应的数据,将string 转换为JSON对象
		String result = CommonUtils.convertStreamToString(is);
		JSONObject jsonObj = new JSONObject(result);
		String access_token = (String) jsonObj.get("access_token");
		String openid = (String) jsonObj.get("openid");
		System.out.println("code:" + code);
		System.out.println("access_token:" + access_token);

		// 3、根据access_token获取用户信息
		doProcessurl = "https://api.weixin.qq.com/sns/userinfo?access_token="
				+ access_token + "&openid=" + openid + "&lang=zh_CN";
		is = CommonUtils.doHttpGetRequest(doProcessurl, "");
		result = CommonUtils.convertStreamToString(is);
		jsonObj = new JSONObject(result);
		WeChatUser chatUserInfo = new WeChatUser();
		chatUserInfo.setCountry(jsonObj.getString("country"));
		chatUserInfo.setProvince(jsonObj.getString("province"));
		chatUserInfo.setCity(jsonObj.getString("city"));
		chatUserInfo.setOpenId(jsonObj.getString("openid"));
		chatUserInfo.setSex(String.valueOf(jsonObj.getInt("sex")));
		chatUserInfo.setNickName(jsonObj.getString("nickname"));
		chatUserInfo.setHeadImgUrl(jsonObj.getString("headimgurl"));
		chatUserInfo.setLanguage(jsonObj.getString("language"));
		// chatUserInfo.setUnionId(jsonObj.getString("unionid"));
		System.out.println("code:" + code);
		System.out.println("access_token:" + access_token);
		System.out.println("jsonObj" + jsonObj);
		// System.out.println("openId:"+chatUserInfo.getOpenId()+"unionId:"+chatUserInfo.getUnionId());
		// 将weChat用户信息存入数据库中
		int rowNum = weChatUserService.saveWeChatUser(chatUserInfo);
		if (rowNum > 0) {
			System.out.println("weChat用户数据保存成功");
		}
		// 保存用户成功,将openId,headimgurl,nickname放入session中
		request.getSession().setAttribute("openId", chatUserInfo.getOpenId());
		request.getSession().setAttribute("headimgurl",
				chatUserInfo.getHeadImgUrl());
		request.getSession().setAttribute("nickname",
				chatUserInfo.getNickName());
		return "redirect:/activity/list";
	}
	
	/**
	 * 获取用户信息的第二版
	 * @param model
	 * @param request
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/weChatUserInfov2", method = RequestMethod.GET)
	public String getWechatUserInfov2(Model model, HttpServletRequest request,
			RedirectAttributes attr) throws Exception {
		System.out.println("正在获取微信用户信息...");
		// 1、获取code
		String code = request.getParameter("code");
		// 2、根据code，请求接口并获取access_token
		String doProcessurl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ APPID
				+ "&secret="
				+ APPSECRET
				+ "&code="
				+ code
				+ "&grant_type=authorization_code";
		InputStream is = CommonUtils.doHttpGetRequest(doProcessurl, "");
		// 获取并处理 响应的数据,将string 转换为JSON对象
		String result = CommonUtils.convertStreamToString(is);
		JSONObject jsonObj = new JSONObject(result);
		String access_token = (String) jsonObj.get("access_token");
		String openid = (String) jsonObj.get("openid");
		System.out.println("code:" + code);
		System.out.println("access_token:" + access_token);

		// 3、根据access_token获取用户信息
		doProcessurl = "https://api.weixin.qq.com/sns/userinfo?access_token="
				+ access_token + "&openid=" + openid + "&lang=zh_CN";
		is = CommonUtils.doHttpGetRequest(doProcessurl, "");
		result = CommonUtils.convertStreamToString(is);
		jsonObj = new JSONObject(result);
		WeChatUser chatUserInfo = new WeChatUser();
		chatUserInfo.setCountry(jsonObj.getString("country"));
		chatUserInfo.setProvince(jsonObj.getString("province"));
		chatUserInfo.setCity(jsonObj.getString("city"));
		chatUserInfo.setOpenId(jsonObj.getString("openid"));
		chatUserInfo.setSex(String.valueOf(jsonObj.getInt("sex")));
		chatUserInfo.setNickName(jsonObj.getString("nickname"));
		chatUserInfo.setHeadImgUrl(jsonObj.getString("headimgurl"));
		chatUserInfo.setLanguage(jsonObj.getString("language"));
		// chatUserInfo.setUnionId(jsonObj.getString("unionid"));
		System.out.println("code:" + code);
		System.out.println("access_token:" + access_token);
		System.out.println("jsonObj" + jsonObj);
		// System.out.println("openId:"+chatUserInfo.getOpenId()+"unionId:"+chatUserInfo.getUnionId());
		// 将weChat用户信息存入数据库中
		int rowNum = weChatUserService.saveWeChatUser(chatUserInfo);
		if (rowNum > 0) {
			System.out.println("weChat用户数据保存成功");
		}
		// 保存用户成功,将openId,headimgurl,nickname放入session中
		request.getSession().setAttribute("openId", chatUserInfo.getOpenId());
		request.getSession().setAttribute("headimgurl",
				chatUserInfo.getHeadImgUrl());
		request.getSession().setAttribute("nickname",
				chatUserInfo.getNickName());
		return "redirect:/collect/list/1";
	}

}
