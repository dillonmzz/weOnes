<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/tag.jsp" %>
<script src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
<script src="<%=request.getContextPath()%>/resources/admin/js/userinfo.js"></script>
<script src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<style type="text/css">
.required {
	font-size: 14px;
	color: #ff7760;
	font-weight: 700;
}

.sqImg {
	width: 198px;
	height: 110px;
	cursor: pointer;
	border: 1px dashed #9196a5;
	border-radius: 5px;
	position: relative;
	background: url(<%=request.getContextPath()%>/resources/admin/img/img_add.png) no-repeat center center;
}
.seeBtn{
cursor: pointer;
color: #85bfe7;
}

.seeImg{
    width: 230px;
    height: 146px;
    padding: 10px 0px 0px 20px;
    position: absolute;
    right: 60px;
    top: -100px;
    z-index: 5;
    background: url(<%=request.getContextPath()%>/resources/admin/img/sfzBg.png) no-repeat;
    display: block;
}
.seeImg img {
    border-radius: 10px;
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
<form name="thisform" id="from557982744" method="post" class="form-horizontal">
	<input type="hidden" class="userId" value="${user.id }">
	<div class="row form-group" >
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required"></span></label>
		<div class="col-sm-6 pl-0">
		</div>
	</div>

	<div class="row form-group">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required">*</span>营业执照：</label>
		<div class="col-sm-6 pl-0 uploadimgurl">
			<div class="sqImg addPic id_card_front browser" style="z-index: 10;">
				<input type="file" id="uploadfile" name="imgurl"
					style="opacity: 0; position: absolute; top: 0px; left: 0px; width: 200px; height: 100%;">
				<img name="imgurl" class="showimg" src="${user.certificateImg}" width="100%" height="100%">
			</div>
		</div>
	</div>

	<div class="row form-group" id="timeTypeDiv">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required"></span></label>
		<div class="col-sm-6 pl-0">
		<span style="color: #9fa3b0;font-size: 12px">证件图应四角完整，文字清晰，无涂改。<span class="seeBtn">查看案例</span></span>
		<div class="seeImg" style="display: none">
			<img src="<%=request.getContextPath()%>/resources/admin/img/business.png" width="200"
				height="126">
		</div>
		</div>
	</div>

	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"></label>
		<div class="col-sm-6 pl-0">
			<a class="btn btn-info" id="postapply">申请</a>
		</div>
		<div class="col-sm-3 valid-msg"></div>
	</div>
</form>
<script type="text/javascript">
	$(".nav-top").click(function(){
		var url = $(this).attr("data-url");
		$("#tinypagecontent").load(url);
	});
	$(".seeBtn").on("mouseover",function(){
		$(".seeImg").show();
	});
	$(".seeBtn").on("mouseout",function(){
		$(".seeImg").hide();
	});
	
	$("#postapply").click(function(){
	    var formdata = {id:$(".userId").val(),imgurl:$(".showimg").attr("src")};
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"<%=request.getContextPath()%>/userinfo/buAuthentication",
		    data:formdata,
		    success:function(data){
		    	 if (data.errorCode == "0") {
		    		 $("#postapply").attr('disabled',true);
		    		 $("#postapply").unbind();
		               layer.msg("申请成功",{icon: 1});
		            } else {
		            	 layer.msg(data.msg,{icon: 2});
		            }
			},
			error: function() {
				layer.msg("系统出错了",{icon: 2});
			}
		});
	});//结束
</script>
