package cn.com.blueline.web;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.Product;
import cn.com.blueline.entity.ProductSubType;
import cn.com.blueline.entity.ProductType;
import cn.com.blueline.entity.SystemUser;
import cn.com.blueline.service.ProductService;
import cn.com.blueline.service.ProductTypeService;
import cn.com.blueline.utils.UUIDGenerate;

import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/product"})
public class ProductController
{

  @Autowired
  private ProductService service;

  @Autowired
  private ProductTypeService typeService;

  @RequestMapping(value={"/adminList"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json;charset=UTF-8"})
  public String adminListGet(Model model, HttpServletRequest request)
  {
    String currentUser = "dillon";
    SystemUser user = (SystemUser)request.getSession().getAttribute("user");
    currentUser = user.getUserName();
    String type = request.getParameter("type");
    System.out.println("type:" + type);
    List<Product> list = this.service.findProductByUser(type, currentUser);
    System.out.println("主题个数:" + list.size());
    ProductType productType = this.typeService.findByType(type);

    for (Product product : list) {
      ProductSubType subType = this.typeService.findByTypeAndSubtype(productType.getId(), product.getSubType());
      product.setSubType(subType.getName());
      if (type.equals("place")) {
        Map deviceMap = (Map)request.getServletContext().getAttribute("device");
        String temp = product.getTitle();
        String[] strArray = temp.split(",");
        StringBuffer tempBuffer = new StringBuffer();
        for (String string : strArray) {
          tempBuffer.append(deviceMap.get(string) + ",");
        }
        product.setTitle(tempBuffer.toString());
      }
    }

    model.addAttribute("list", list);
    model.addAttribute("productType", productType);
    return "admin/product/" + type + "/list";
  }
  @RequestMapping(value={"/toadd"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json;charset=UTF-8"})
  public String toadd(Model model, HttpServletRequest request) {
    String type = request.getParameter("type");
    ProductType productType = this.typeService.findByType(type);
    Long productTypeId = productType.getId();
    System.out.println("productTypeId:" + productTypeId);
    List subTypes = this.typeService.findAllByType(productType.getId());
    model.addAttribute("productType", productType);
    model.addAttribute("subTypes", subTypes);
    if (type.equals("place")) {
      List list = this.service.findAllPlaceDevice();
      model.addAttribute("devices", list);
    }
    return "admin/product/" + type + "/add";
  }

  @RequestMapping(value={"/save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
  @ResponseBody
  public ResultWithJson saveActivity(HttpServletRequest request, Product product)
  {
	product.setIntroduction(request.getParameter("content"));
    ResultWithJson resultWithJson;
    try
    {
      product.setId(UUIDGenerate.getUUID());
      SystemUser user = (SystemUser)request.getSession().getAttribute("user");
      String currentUser = user.getUserName();
      product.setCreateUser(currentUser);
      product.setCreateTime(new Date());
      product.setEffdt(new Date());
      product.setStatus("A");
      System.out.println("product:" + product.toString());
      int rowNum = this.service.save(product);
      if (rowNum > 0)
        resultWithJson = new ResultWithJson("0", "success");
      else {
        resultWithJson = new ResultWithJson("1", "error");
      }
      return resultWithJson;
    } catch (Exception e) {
      resultWithJson = new ResultWithJson("1", "保存数据时出错");
    }return resultWithJson;
  }
}