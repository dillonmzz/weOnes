package cn.com.blueline.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.ProductCollectComment;
import cn.com.blueline.service.ProductCollectConmmentService;
import cn.com.blueline.utils.RelativeDateFormat;

@Controller
@RequestMapping("/comment")
public class ProductCollectConmmentController {
	
	
	@Autowired private ProductCollectConmmentService service;
	
	/**
	 * 根据产品ID 查询评论内容
	 * @param productCollectId 产品ID
	 * @return
	 */
	@RequestMapping(value = "/list/{productCollectId}", method = RequestMethod.GET)
	@ResponseBody
	public ResultWithJson<ProductCollectComment> list(@PathVariable("productCollectId")Long productCollectId){
		List<ProductCollectComment> list = null;
		try {
			list = service.queryCommentByProductCollectId(productCollectId);
			if (list!=null && list.size()>0){
				//处理时间显示格式
				List<ProductCollectComment> tempList = new ArrayList<ProductCollectComment>();
				for (ProductCollectComment productCollectComment : list) {
					String showTime = RelativeDateFormat.format(productCollectComment.getCreateTime());
					productCollectComment.setRelativeDateFormat(showTime);
					tempList.add(productCollectComment);
				}
				return new ResultWithJson("0", ""+tempList.size(),tempList);
			}else{
				return new ResultWithJson("1", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultWithJson("1", "error");
		}
	}
	
	
	/**
	 * 保存评论
	 * @param productCollectId 产品ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save/{productCollectId}", 
					method = RequestMethod.POST,
					produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson save(@PathVariable("productCollectId")Long productCollectId,HttpServletRequest request){
		try {
			System.out.println("评论的productCollectId="+productCollectId);
			String openId = (String)request.getSession().getAttribute("openId");
			//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
			String headimgurl = (String)request.getSession().getAttribute("headimgurl");
			//headimgurl = "http://wx.qlogo.cn/mmopen/PiajxSqBRaEIyNnDSXRm1TgtA5HUibda9vmzdjd6QqaxgZxahOTTUnHR8SMfxpfnCQw9Q78eOXeeLA2lBOoXkWcw/0";
			String nickname = (String)request.getSession().getAttribute("nickname");
			//nickname = "dillons";
			String commentText = request.getParameter("commentText");
			ProductCollectComment comment = new ProductCollectComment();
			comment.setProductCollectId(productCollectId);//评论的产品ID
			comment.setOpenId(openId);//评论人的微信openId
			comment.setCommentText(commentText);//评论的内容
			comment.setParentId(null);//TODO 评论父节点ID
			comment.setCreateTime(new Date());
			int rowNum = 0;
			rowNum = service.save(comment);
			if(rowNum>0){
				String[] info = {nickname,headimgurl,"刚刚"};
				return new ResultWithJson("0","ok",info);
			}else{
				return new ResultWithJson("1","error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultWithJson("1","error");
		}
		
		
	}
	

}
