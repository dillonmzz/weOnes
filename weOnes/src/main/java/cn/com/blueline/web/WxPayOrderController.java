package cn.com.blueline.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




import org.springframework.web.servlet.ModelAndView;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.WxPayOrder;
import cn.com.blueline.service.UnifiedOrderService;

@Controller
@RequestMapping("/wxPayOrder")
public class WxPayOrderController {

	private Log LOG = LogFactory.getLog(this.getClass());

	@Autowired
	private UnifiedOrderService unifiedOrderService;

	/* 移动端
	 * 根据当前用户的openid 查询订单记录
	 */
	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public String orderList(Model model, HttpServletRequest request) {

		String openId = (String) request.getSession().getAttribute("openId");
		//openId = "o9bmjs8b90Cr-_YTC0R6QPdgGMzw";
		List<WxPayOrder> orders = unifiedOrderService
				.queryOrdersByOpenId(openId);
		if (orders == null) {
			model.addAttribute("nullInfo", "您还没有订单");
		} else {
			model.addAttribute("list", orders);
		}
		return "myOrders";
	}

	/*
	 * 根据 微信订单交易号 删除订单记录
	 */
	@RequestMapping(value = "/delOrderByTransactionId", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson delOrderByTransactionId(HttpServletRequest request) {
		ResultWithJson json = null;
		try {
			String openId = (String) request.getSession().getAttribute("openId"); 
			String transactionId = request.getParameter("transactionId");
			System.out.println("当前用户("+openId+")正在删除订单("+transactionId+")");
			if(openId ==null || transactionId == null){
				json = new ResultWithJson();
				json.setErrorCode("1");
				json.setMsg("SUCCESS");
				return json;
			}
			int rowNum = unifiedOrderService.deleteOrderByOpenId(transactionId,openId);
			if (rowNum > 0) {
				json = new ResultWithJson();
				json.setErrorCode("0");
				json.setMsg("SUCCESS");
			} else {
				json = new ResultWithJson();
				json.setErrorCode("1");
				json.setMsg("FAIL");
			}
			return json;
		} catch (Exception e) {
			System.out.println("删除过程出现异常,可能是当前会话中(session)无openId");
			json = new ResultWithJson();
			json.setErrorCode("1");
			json.setMsg("FAIL");
			return json;
		}
		
	}
	
	
	
	/* 后台管理系统
	 * 查询所有订单记录
	 */
	@RequestMapping(value = "/adminList", method = RequestMethod.GET)
	public ModelAndView adminList(Model model, HttpServletRequest request) {
		List<WxPayOrder> list = unifiedOrderService.queryOrders();
		if (list == null) {
			model.addAttribute("nullInfo", "您还没有订单");
		} else {
			model.addAttribute("list", list);
			System.out.println("总订单记录数:"+list.size());
		}
		request.getSession().setAttribute("list", list);
		return new ModelAndView("redirect:/userOrderAdmin/orderList.jsp");
	}
	
	/* 
	 * PHP调用后台管理系统
	 * 查询所有订单记录
	 */
	@RequestMapping(value = "/adminList", method = RequestMethod.POST,
					produces = { "application/json;charset=UTF-8"} )
	public @ResponseBody ResultWithJson<WxPayOrder> adminListPhp(Model model, HttpServletRequest request) {
		ResultWithJson<WxPayOrder> resultWithJson;
		List<WxPayOrder> list = unifiedOrderService.queryOrders();
		System.out.println("PHP前台查询订单列表"+list.size());
		if (list == null) {
			resultWithJson = new ResultWithJson<WxPayOrder>("1", "无订单数据");
		} else {
			resultWithJson = new ResultWithJson<WxPayOrder>("0", "success",list);
		}
		return resultWithJson;
	}

}
