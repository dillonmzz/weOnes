<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/tag.jsp" %>
<script src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
<script src="<%=request.getContextPath()%>/resources/admin/js/citychoose.js"></script>
<script src="<%=request.getContextPath()%>/resources/admin/js/userinfo.js"></script>
<script src="<%=request.getContextPath()%>/resources/admin/js/citymini.js"></script>
<script src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<style type="text/css">
.required {
	font-size: 14px;
	color: #ff7760;
	font-weight: 700;
}
legend ul{
line-height: 22px;
}
legend ul li{
	float: left;
	width: 14%;
	margin-right: 1px;
    text-align: center;
    
}
legend ul li a{
	padding: 0 20px;
    white-space: nowrap;
    min-width: 60px;
    font-size: 18px;
    color: #333;
}
</style>
<legend style="overflow: hidden;">
	<ul>
	    <li><a href="javascript:void(0);" class="nav-top" data-url="<%=path%>/userinfo/index">商家资料</a></li>
	    <li><a href="javascript:void(0);" class="nav-top" data-url="<%=path%>/userinfo/certificate">资质认证</a></li>
	</ul>
</legend>
<script type="text/javascript">
		$(".nav-top").click(function(){
			var url = $(this).attr("data-url");
			$("#tinypagecontent").load(url);
		});
</script>
<form name="thisform" id="from557982744" method="post" class="form-horizontal">
<input type="hidden" class="userId" value="${user.id }">
	<div class="row form-group" >
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required"></span></label>
		<div class="col-sm-6 pl-0">
		</div>
	</div>

	<div class="row form-group" >
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required">*</span>商家头像：</label>
		<div class="col-sm-6 pl-0 uploadimgurl">
			<img name="imgurl" class="showimg" src="${user.imgurl }" 
			width="60" height="60" style="border-radius:50px;">
			<input type="file" id="uploadfile" name="imgurl"
			style="opacity: 0; position: absolute; top: 0px; left: 0px;width: 60px; height: 100%;">
		</div>
	</div>
	
	<div class="row form-group" id="timeTypeDiv">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required"></span></label>
		<div class="col-sm-6 pl-0">
		<span style="color: #9fa3b0;font-size: 12px">建议您使用店铺LOGO(图片大小不能超过4M)</span>
		</div>
	</div>


	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required">*</span>商家名称：</label>
		<div class="col-sm-4 pl-0">
			<input type="text"  value="${user.nickName }"
				class="form-control input-text span2 nickName" 
				placeholder="建议您使用店铺名称或官方昵称，20字以内"  name="nickName">
		</div>
	</div>
	
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required">*</span>联系电话：</label>
		<div class="col-sm-4 pl-0">
			<input type="text"  value="${user.mobile }"
				class="form-control input-text span2 mobile" 
				placeholder="座机前请加入区号，多个号码请用“:”隔开"  name="mobile">
		</div>
	</div>
	
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5">电子邮箱：</label>
		<div class="col-sm-4 pl-0">
			<input type="text"
				class="form-control input-text span2 email" value="${user.email}"
				placeholder="用于日常事务联络，可留空不填"  name="email">
		</div>
	</div>  
	
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5">
		<span class="required">*</span>商家简介：</label>
		<div class="col-sm-4 pl-0">
			<textarea class="form-control introduction" name="introduction"  placeholder="一句话介绍下你的店铺特色，不超过28个字" row="10">${user.introduction }</textarea>
		</div>
	</div>


	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required">*</span>商家城市：</label>
		<div class="col-xs-12 col-sm-6 pl-0">
			<div class="form-inline selectList">
				<select name="province" class="form-control province" id="province">
				</select>
				<select name="city" class="form-control city" id="city">
				</select>
			</div>
		</div>
	</div>
	<input type="hidden" id="provinceSec" value="${user.province }">
	<input type="hidden" id="citySec" value="${user.city }">
	<div class="row form-group">
		 <label class="col-sm-2 control-label  pl-0 pr-5">
		<span class="required">*</span>详细地址：</label> 
		<div class="col-xs-4 pl-0">
			<input type="text" class="form-control address" value="${user.address}" placeholder="详细地址" name="address">
		</div>
	</div>


	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"></label>
		<div class="col-sm-6 pl-0">
			<a class="btn btn-info" id="submitId">保存</a>
		</div>
		<div class="col-sm-3 valid-msg"></div>
	</div>
</form>
