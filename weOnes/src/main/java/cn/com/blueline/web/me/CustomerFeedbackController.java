package cn.com.blueline.web.me;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.CustomerFeedback;
import cn.com.blueline.service.CustomerFeedbackService;

@Controller
@RequestMapping("/feedbackController")
public class CustomerFeedbackController {

	@Autowired
	private CustomerFeedbackService feedbackService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "app/me/feedback";
	}

	@RequestMapping(value = { "/save" }, method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson save(HttpServletRequest request,CustomerFeedback feedback) {
		try {
			String openId = (String)request.getSession().getAttribute("openId");
			//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
			feedback.setOpenId(openId);
			feedback.setCreateTime(new Date());
			//System.out.println(feedback);
			int rowNum = feedbackService.save(feedback);
			if(rowNum>0){
				return new ResultWithJson("0", "ok");
			}else{
				return new ResultWithJson("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson("1", "error");
		}
		
		

	}
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<CustomerFeedback> list(HttpServletRequest request) {
		try {
			List<CustomerFeedback> list = feedbackService.queryAll();
			if(list!=null &&list.size()>0){
				return new ResultWithJson<CustomerFeedback>("0", ""+list.size(),list);
			}else{
				return new ResultWithJson("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson("1", "error");
		}
		

	}

}
