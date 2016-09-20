<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人信息</title>
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="HandheldFriendly" content="true">
	<link rel="stylesheet" href="http://g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
	<script src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
	<script type='text/javascript' src="http://g.alicdn.com/sj/lib/zepto/zepto.min.js" charset='utf-8'></script>
	<script type='text/javascript' src="http://g.alicdn.com/msui/sm/0.6.2/js/sm.min.js" charset='utf-8'></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/resources/app/js/myinfo.js"></script>
	
<style>
	.grid-demo .row {
		text-align: center;
	}
	.grid-demo .row + .row {
		margin-top: 0.75rem;
	}
	.grid-demo .row > [class*=col-] {
		border: 1px solid #ddd;
	}
	.icons-demo .icon {
		width: 2.5rem;
		height: 2.5rem;
		margin: 0.15rem;
		font-size: 1.2rem;
		line-height: 2.5rem;
		text-align: center;
		background-color: #fff;
		border: 1px solid #ddd;
		border-radius: 1.25rem;
		display: inline-block;
	}

	.icon.icon-form-password {
		width: 1.45rem;
		height: 1.45rem;
		background-image: url("<%=request.getContextPath()%>/resources/app/images/myinfo/i-form-password.png");
	}
	.icon.icon-form-email {
		width: 1.45rem;
		height: 1.45rem;
		background-image: url("<%=request.getContextPath()%>/resources/app/images/myinfo/i-form-email.png");
	}
	.icon.icon-form-calendar {
		width: 1.45rem;
		height: 1.45rem;
		background-image: url("<%=request.getContextPath()%>/resources/app/images/myinfo/i-form-calendar.png");
	}

	.icon.icon-form-gender {
		width: 1.45rem;
		height: 1.45rem;
		background-image: url("<%=request.getContextPath()%>/resources/app/images/myinfo/i-form-gender.png");
	}
	.icon.icon-form-comment {
		width: 1.45rem;
		height: 1.45rem;
		background-image: url("<%=request.getContextPath()%>/resources/app/images/myinfo/i-form-comment.png");
	}
	.icon.icon-form-name {
		width: 1.45rem;
		height: 1.45rem;
		background-image: url("<%=request.getContextPath()%>/resources/app/images/myinfo/myinfo_city.png");	
	}
	.icon.icon-form-toggle{
		width: 1.45rem;
		height: 1.45rem;
		background-image: url("<%=request.getContextPath()%>/resources/app/images/myinfo/myinfo_province.png");	
	}
</style>
</head>
	<body>
		<header class="bar bar-nav">
			<h1 class="title">个人信息</h1>
		</header>
		<div class="list-block" style="padding-top:10px">
			<ul>
				<!-- Text inputs -->
				<li>
					<div class="item-content">
						
						<div class="item-inner">
							<div class="item-title label">头像</div>
							<div class="item-input">
								<img src="" id="myinfo_headImgUrl" style="width:100px;height:100px;border-radius:50%;padding-top:6px;">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-inner">
							<div class="item-title label">昵称</div>
							<div class="item-input">
								<input type="text" id="myinfo_nickname" disabled="disabled">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-inner">
							<div class="item-title label">关注时间</div>
							<div class="item-input">
								<input type="text" id="myinfo_createtime" disabled="disabled">
							</div>
						</div>
					</div>
				</li>
			</ul>
			<ul style="margin-top:10px">
				<!-- Text inputs -->
				<li>
					<div class="item-content">
						<div class="item-media"><i class="icon icon-form-toggle"></i></div>
						<div class="item-inner">
							<div class="item-title label">省份</div>
							<div class="item-input">
								<select id="myinfo_province">
									
								</select>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-media"><i class="icon icon-form-name"></i></div>
						<div class="item-inner">
							<div class="item-title label">城市</div>
							<div class="item-input">
								<!-- <input type="text" placeholder="北京" class="">  --> 
								<select id="myinfo_city">
								</select>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-media"><i class="icon icon-form-gender"></i></div>
						<div class="item-inner">
							<div class="item-title label">性别</div>
							<div class="item-input">
								<select id="myinfo_sex">
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-media"><i class="icon icon-form-comment"></i></div>
						<div class="item-inner">
							<div class="item-title label">手机</div>
							<div class="item-input">
								<input type="text" id="myinfo_phone" placeholder="手机号">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-media"><i class="icon icon-form-email"></i></div>
						<div class="item-inner">
							<div class="item-title label">邮箱</div>
							<div class="item-input">
								<input type="email" id="myinfo_email" placeholder="E-mail">
							</div>
						</div>
					</div>
				</li>
				<!-- Date -->
				<li>
					<div class="item-content">
						<div class="item-media"><i class="icon icon-form-calendar"></i></div>
						<div class="item-inner">
							<div class="item-title label">生日</div>
							<div class="item-input">
								<input type="date" id="myinfo_birthday" placeholder="Birth day" >
							</div>
						</div>
					</div>
				</li>
				
				<!-- Switch (Checkbox) -->
			</ul>
			<span class="characters" style="color:#eb6409;font-size:14px;padding:4px;background:#fff">请您尽量完整填写您的个人信息,已便您更方便参加活动</span>
		</div>
		<div id="qq-sendUrl-btn"></div>
	</body>
</html>
