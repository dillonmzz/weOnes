package cn.com.blueline.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.SystemUser;
import cn.com.blueline.entity.WeChatUser;
import cn.com.blueline.service.SystemUserService;
import cn.com.blueline.service.WeChatUserService;
import cn.com.blueline.utils.WxMsgTemplate;


/**
 * 后台用户控制器
 * @author Zhaozhi
 *
 */
@Controller
@RequestMapping("/userinfo")
public class SystemUserController {
	
	
	@Autowired private SystemUserService userService;
	
	@Autowired private WeChatUserService weChatUserService;
	
	
	/**
	 * 跳转至商家信息页面
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model){
		SystemUser  user = (SystemUser) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		return "admin/user/info";
	}
	
	
	/**
	 * 保存修改商家信息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST,
			produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<SystemUser> save(SystemUser user,HttpServletRequest request){
		int rowNum;
		if(user.getId()!=null){
			System.out.println(user.toString());
			rowNum = userService.updateById(user);
			if(rowNum>0){
				SystemUser sessionUser = (SystemUser) request.getSession().getAttribute("user");
				SystemUser newUser = userService.queryByUserName(sessionUser.getUserName());
				System.out.println(newUser.toString());
				request.getSession().setAttribute("user", newUser);//覆盖当前user session的值
				return new ResultWithJson<SystemUser>("0", "ok");
			}else{
				return new ResultWithJson<SystemUser>("1", "error");
			}
		}else{
			user.setCreateTime(new Date());
			user.setIsAuthentication("N");
			System.out.println(user);
			rowNum = userService.save(user);
			if(rowNum>0){
				return new ResultWithJson<SystemUser>("0", "ok");
			}else{
				return new ResultWithJson<SystemUser>("1", "error");
			}
		}
		
	}
	
	
	/**
	 * 跳转至商家认证页面
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/certificate", method = RequestMethod.GET)
	public String certificate(HttpServletRequest request,Model model){
		SystemUser  user = (SystemUser) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		return "admin/user/certificate";
	}
	
	/**
	 * 商家发起认证审批
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/buAuthentication", method = RequestMethod.POST,
			produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<SystemUser> buAuthentication(HttpServletRequest request){
		int rowNum;
		try {
			Long userId =((SystemUser)request.getSession().getAttribute("user")).getId();
			String certificateImg = request.getParameter("imgurl");
			System.out.println("userId="+userId+" certificateImg="+certificateImg);
			rowNum = userService.certificate(userId,certificateImg);
			if(rowNum>0){
				SystemUser sessionUser = (SystemUser) request.getSession().getAttribute("user");
				SystemUser newUser = userService.queryByUserName(sessionUser.getUserName());
				request.getSession().setAttribute("user", newUser);//覆盖当前user session的值
				return new ResultWithJson<SystemUser>("0", "ok");
			}else{
				return new ResultWithJson<SystemUser>("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson<SystemUser>("1", "error");
		}
	
	}
	
	
	
	/**
	 * 管理员查看商家发起认证列表
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/certificatelist", method = RequestMethod.GET)
	public String certificatelist(Model model){
		List<SystemUser> users = userService.queryCertificateUsers();
		model.addAttribute("users", users);
		return "admin/user/certificatelist";
	}
	
	/**
	 * 管理员对商家发起的认证进行审批
	 * @return
	 */
	@RequestMapping(value = "/approval", method = RequestMethod.POST,
			produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<SystemUser> approval(HttpServletRequest request){
		int rowNum;
		try {
			String state = request.getParameter("state");
			Long userId = Long.parseLong(request.getParameter("id"));
			if(state.equals("Y")){
				rowNum = userService.changeIsAuthenticatioin(userId);
				if(rowNum>0){
//					List<WeChatUser> tousrs = weChatUserService.getWeChatUserList();
//					//获取accessToken
//					String accessToken = WxMsgTemplate.getAccessToken();
//					/**/
//					for (WeChatUser weChatUser : tousrs) {
//						JSONObject jsonData = WxMsgTemplate.approvalJsonMsg("平台测试,非广告", "商家认证申请", "微信用户",
//							"您申请的认证已通过审核", "2016-08-17 19:03", "\r\n感谢您的使用,审核已经通过,如有疑问,请联系客服,客服热线:010-10086");
//						JSONObject jsonData = WxMsgTemplate.customerOrderJsonMsg("您有新的客户预约，请及时确认", "张三", "18810886688","2016-08-20 10：00", "活动订单", "\r\n点击确认预约时间，或修改预约时间。如有疑问,请联系客服,客服热线:010-10086");
//						WxMsgTemplate.sendWechatmsgToUser(weChatUser.getOpenId(), WxMsgTemplate.CUSTOMERORDER_TEMPLATEID,
//								accessToken, "#173177", jsonData);
//						JSONObject jsonData = WxMsgTemplate.customerOrderJsonMsg("您有新的客户订单,请及时确认【测试】", "张三", "18810886688","2016-08-20 10:00", "活动订单", "\r\n点击确认预约时间，或修改预约时间。如有疑问,请联系客服,客服热线:010-10086");
//						WxMsgTemplate.sendWechatmsgToUser(weChatUser.getOpenId(), WxMsgTemplate.CUSTOMERORDER_TEMPLATEID,
//								accessToken, "#173177", jsonData);
//					}
					return new ResultWithJson<SystemUser>("0", "ok");
				}else{
					return new ResultWithJson<SystemUser>("1", "error");
				}
			}else if(state.equals("N")){
				return new ResultWithJson<SystemUser>("0", "ok");
			}else{
				return new ResultWithJson<SystemUser>("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson<SystemUser>("1", "error");
		}
	
	}
	
	
	/**
	 * 商品详情页面查看商家信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showbuinfo", method = RequestMethod.GET,
			produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<SystemUser> showbuinfo(HttpServletRequest request){
		try {
			String userName = request.getParameter("userName");
			SystemUser user = userService.queryByUserName(userName);
			SystemUser json = new SystemUser();
			json.setImgurl(user.getImgurl());
			json.setNickName(user.getNickName());
			json.setIntroduction(user.getIntroduction());
			json.setMobile(user.getMobile());
			return new ResultWithJson<SystemUser>("0", "ok",user);
		} catch (Exception e) {
			return new ResultWithJson<SystemUser>("1", "error");
		}
	
	}
	
	
	/**
	 * 商品详情页面查看商家信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotobuinfo", method = RequestMethod.GET)
	public String gotobuinfo(HttpServletRequest request,Model model) {
		String userName = request.getParameter("userName");
		SystemUser user = userService.queryByUserName(userName);
		SystemUser json = new SystemUser();
		json.setImgurl(user.getImgurl());
		json.setNickName(user.getNickName());
		json.setIntroduction(user.getIntroduction());
		json.setMobile(user.getMobile());
		json.setProvince(user.getProvince());
		json.setCity(user.getCity());
		model.addAttribute("buinfo", json);
		return "app/collect/buinfo";
	}

}
