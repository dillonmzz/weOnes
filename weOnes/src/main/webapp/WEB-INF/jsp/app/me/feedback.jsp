<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<title>小面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<script
	src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
</head>
<style>
div.tjb-bt-title {
	width: 100%;
	height: auto;
	background: #F1F0F5;
	color: #eb6409;
}

div.tjb-bt-title span {
	display: block;
	width: 60%;
	height: 40px;
	line-height: 40px;
	margin-left: 8px;
	font-size: 16px;
}

#tjb-neirong {
	width: 100%;
	border: 0;
	padding-left: 15px;
	height: 95px;
	margin-top: 5px;
	font-size: 16px;
	border-bottom: 1px solid #F0EFF5;
}

.anniu {
	width: 90%;
	height: 40px;
	font-size: 18px;
	border-radius: 10px 10px 10px 10px;
	color: #FFF;
	border: none;
	background: #eb6409;
	margin: 120px 0 0 20px;
}

.cellphone {
	width: 100%;
	border: none;
	height: 40px;
}
</style>
<body>
	<div id="page">
		<header class="no-green">
			<a href="<%=request.getContextPath()%>/myController/v2"
				class="head-button head-button-left icon-back back-green"></a>
			<h1 class="title" style="color: #eb6409">意见反馈</h1>
		</header>
		<div class="wrap" style="padding-top: 48px;">
			<form action="" method="" id="tjb-yjfk">
				<div class="tjb-bt-title">
					<span>问题和意见</span>
				</div>
				<textarea placeholder="请简要描述你的问题和意见" name="text" id="tjb-neirong"></textarea>
				<div class="tjb-bt-title">
					<span>联系方式</span>
				</div>
				<input type="text" class="cellphone" name="contacts"
					placeholder="请填写联系方式,邮箱或手机号"> <input type="button"
					class="anniu" value="提交">
			</form>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>
<script>
$(function(){
$(".anniu").click(function() {
		alert("aaa");
		var params = $("#tjb-yjfk").serialize();
		console.log("params:" + params);
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"<%=request.getContextPath()%>/feedbackController/save",
		    data:params,
		    success:function(data){
		    	 if (data.errorCode == "0") {
		    		 alert("保存成功");
		    		 window.location.href="<%=request.getContextPath()%>/myController/v2";
		 			   return false;
		            } else {
		            	alert("保存出错");
		            }
			},
			error: function() {
				alert("保存出错");
			}
		});// ajax end
	
	
	});
});
</script>