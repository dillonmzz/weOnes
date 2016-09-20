package cn.com.blueline.web.php;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.Product;
import cn.com.blueline.service.ProductService;
import cn.com.blueline.utils.UUIDGenerate;

@Controller
@RequestMapping({ "/productphp" })
public class ProductPHP {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<Product> list(HttpServletRequest request) {
		String user = request.getParameter("user");
		String type = request.getParameter("type");
		System.out.println("type:" + type);
		System.out.println("user:" + user);
		List<Product> list = this.productService.findProductByUser(type, user);
		System.out.println("个数:" + list.size());
		if (list != null && list.size() > 0) {
			return new ResultWithJson<Product>("0", "OK", list);
		} else {
			return new ResultWithJson("1", "error");
		}
	}

	@RequestMapping(value = { "/save" }, method = {RequestMethod.POST }, 
			produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson save(HttpServletRequest request,
			Product product) {
		ResultWithJson resultWithJson;
		try {
			product.setId(UUIDGenerate.getUUID());
			String currentUser = request.getParameter("createUser");
			product.setCreateUser(currentUser);
			product.setCreateTime(new Date());
			product.setEffdt(new Date());
			product.setStatus("A");
			System.out.println("product:" + product.toString());
			int rowNum = this.productService.save(product);
			if (rowNum > 0)
				resultWithJson = new ResultWithJson("0", "success");
			else {
				resultWithJson = new ResultWithJson("1", "error");
			}
			return resultWithJson;
		} catch (Exception e) {
			resultWithJson = new ResultWithJson("1", "保存数据时出错");
		}
		return resultWithJson;
	}
	
	
	@RequestMapping(value = { "/findProductByUserAndId" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<Product> findProductByUserAndId(HttpServletRequest request) {
		String productType  = request.getParameter("productType");
		String user = request.getParameter("user");
		String id = request.getParameter("id");
		Product product = this.productService.findProductByUserAndId(productType, user, id);
		if (product != null ) {
			return new ResultWithJson<Product>("0", "OK", product);
		} else {
			return new ResultWithJson("1", "error");
		}
	}
	
	
	@RequestMapping(value = { "/update" }, method = {RequestMethod.POST }, 
			produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson update(HttpServletRequest request,
			Product product) {
		ResultWithJson resultWithJson;
		try {
			//product.setId(UUIDGenerate.getUUID());
			String currentUser = request.getParameter("createUser");
			product.setCreateUser(currentUser);
			product.setCreateTime(new Date());
			product.setEffdt(new Date());
			product.setStatus("A");
			System.out.println("product:" + product.toString());
			int rowNum = this.productService.update(product);
			if (rowNum > 0)
				resultWithJson = new ResultWithJson("0", "success");
			else {
				resultWithJson = new ResultWithJson("1", "error");
			}
			return resultWithJson;
		} catch (Exception e) {
			resultWithJson = new ResultWithJson("1", "保存数据时出错");
		}
		return resultWithJson;
	}
	
	@RequestMapping(value = { "/updateToInvalid" }, method = {RequestMethod.POST },produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson updateToInvalid(HttpServletRequest request) {
		ResultWithJson resultWithJson;
		try {
			String id = request.getParameter("id");//产品ID
			String createUser = request.getParameter("createUser");//
			System.out.println("id:"+id+" "+createUser);
			int rowNum = this.productService.updateToInvalid(id, createUser);
			if (rowNum > 0)
				resultWithJson = new ResultWithJson("0", "success");
			else {
				resultWithJson = new ResultWithJson("1", "error");
			}
			return resultWithJson;
		} catch (Exception e) {
			resultWithJson = new ResultWithJson("1", "error");
		}
		return resultWithJson;
	}

}
