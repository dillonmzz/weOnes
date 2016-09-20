<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN" class="pxajs">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>WeOnes</title>
<script>
	var contextPath = "/weOnes";
	//var UPLOAD_ACTION_URL = '/tinyuiweb2/action/savepic.pagelet';
</script>

<link rel="shortcut icon" href="http://www.tinygroup.org/tinyuiweb2/img/tiny32.png">
<link href="./css/uiengine.uicss" rel="stylesheet">
<script src="./js/uiengine.uijs"></script>  
<script src="./js/common.js"></script>
<link rel="stylesheet"	href="./css/layer.ext.css"	id="layui_layer_skinlayerextcss">
<link href="./css/tinyui.css" rel="stylesheet">
<script type="text/javascript" src="./js/jquery.lazylinepainter-1.5.0.js"></script>


</head>
<body
	class="main-menu-animated main-navbar-fixed theme-clean dont-animate-mm-content-sm animate-mm-md animate-mm-lg">
	<script>
		var init = [];
	</script>
	<script src="./js/setting.js"></script>
	<div id="main-wrapper">
		<!-- main-navbar 开始 -->
		<div id="main-navbar" class="navbar navbar-inverse" role="navigation">
			<button type="button" id="main-menu-toggle">
				<i class="navbar-icon fa fa-bars icon"></i><span
					class="hide-menu-text"></span>
			</button>
			<!--  navbar-innser 开始 -->
			<div class="navbar-inner">
				<div class="navbar-header">
					<a href="javascript:void(0);" class="navbar-brand">
						<div>WeOnes</div>
					</a>
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#main-navbar-collapse">
						<i class="navbar-icon fa fa-bars"></i>
					</button>
				</div>
				<div id="main-navbar-collapse"
					class="collapse navbar-collapse main-navbar-collapse">
					<div>
						<div class="right clearfix">
							<ul class="nav navbar-nav pull-right right-navbar-nav">
								
								<li class="fullscreen"><a href="javascript:void(0);"
									onclick="launchFullScreen(document.documentElement);"
									title="全屏"><i class="fa fa-arrows-alt"></i></a></li>
								<li>
									<form class="navbar-form pull-left">
										<input type="text" class="form-control" value=""
											placeholder="查找更多..."> <span
											style="left: -27px; top: 3px; position: relative;"><i
											class="fa fa-search"></i></span>
									</form>
								</li>
								<li class="dropdown">
									<a href="javascript:void(0);" class="dropdown-toggle user-menu" data-toggle="dropdown">
									<img src="./images/blueline_logo.jpg" alt=""><span>BlueLine</span>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- main-navbar-collapse end -->
			</div>
			<!--  navbar-innser 结束 -->
			
		</div>
		<!-- main-navbar 结束 -->
		
		<!--  main-menu 左边菜单开始 -->
		<div id="main-menu" role="navigation">
			<div id="topmenurap" style="display: none">
				<ul class="navigation">
				</ul>
			</div>
			<div class="slimScrollDiv"	style="position: relative; overflow: hidden; width: auto; height: 100%;">
				<div id="main-menu-inner" style="overflow: hidden; width: auto; height: 100%;">

				<ul class="navigation">
				
						<li class="">
							<a href="javascript:void(0);" class="navitem" title="控制台" data-treeid="0">
								<i class="menu-icon fa fa-home"></i>
								<span class="mm-text mmc-dropdown-delay animated fadeIn">控制台</span>
							</a>
						</li>
						
						<li class="mm-dropdown  mm-dropdown-root">
							<a href="javascript:void(0);" class="navitem" data-treeid="0">
							    <i class="menu-icon fa fa-home"></i>
							    <span class="mm-text mmc-dropdown-delay animated fadeIn">用户中心</span>
							</a>
							<ul class="mmc-dropdown-delay animated" style="">
								<li >
									<a href="#userinfo/index" class="navitem" data-treeid="2.1">
									<span class="mm-text">商家信息</span>
									</a>
								</li>
								
							</ul>
					    </li>
					    
					    <!-- 基础设置开始 -->
						<li class="mm-dropdown  mm-dropdown-root">
							<a href="javascript:void(0);" data-treeid="2">
								<i class="menu-icon fa fa-th"></i>
								<span class="mm-text mmc-dropdown-delay animated fadeIn">基础设置</span>
							</a>
							<ul class="mmc-dropdown-delay animated" style="">
								<li >
									<a href="#product/adminList?type=subject" class="navitem" data-treeid="2.1">
									<span class="mm-text">主题管理</span>
									</a>
								</li>
								<li class="">
									<a href="#product/adminList?type=talent" class="navitem" data-treeid="2.2">
									<span class="mm-text">达人管理</span>
									</a>
								</li>
								<li class="">
									<a href="#product/adminList?type=place" class="navitem" data-treeid="2.3">
									<span class="mm-text">场地管理</span>
									</a>
								</li>
								<li class="">
									<a href="#timemgr/adminList" class="navitem" data-treeid="2.4">
									<span class="mm-text">时间管理</span>
									</a>
								</li>
							</ul>
						</li>
						<!-- 基础设置结束 -->
						
						<!-- 产品发布开始 -->
						<li class="mm-dropdown  mm-dropdown-root">
							<a href="javascript:void(0);" data-treeid="2">
								<i class="menu-icon fa fa-th"></i>
								<span class="mm-text mmc-dropdown-delay animated fadeIn">发布管理</span>
							</a>
							<ul class="mmc-dropdown-delay animated" style="">
								<li >
									<a href="#collect/myApproval?state=0" class="navitem" data-treeid="2.1">
									<span class="mm-text">我的审批</span>
									</a>
								</li>
								<li class="">
									<a href="#collect/toadd" class="navitem" data-treeid="2.2">
									<span class="mm-text">发布产品</span>
									</a>
								</li>
								<li class="">
									<a href="#collect/list/1" class="navitem" data-treeid="2.2">
									<span class="mm-text">产品浏览</span>
									</a>
								</li>
							</ul>
						</li>
						<!-- 产品发布开始 -->
					    
					    <!-- 活动管理开始 -->
						<li class="mm-dropdown  mm-dropdown-root">
							<a href="javascript:void(0);" data-treeid="2">
								<i class="menu-icon fa fa-th"></i>
								<span class="mm-text mmc-dropdown-delay animated fadeIn">活动管理</span>
							</a>
							<ul class="mmc-dropdown-delay animated" style="">
								<li >
									<a href="#activity/adminList" class="navitem" data-treeid="2.1">
									<span class="mm-text">活动发布</span>
									</a>
								</li>
								<li class="">
									<a href="#" class="navitem" data-treeid="2.2">
									<span class="mm-text">活动审批</span>
									</a>
								</li>
							</ul>
						</li>
						<!-- 活动管理结束 -->

						<li class="mm-dropdown  mm-dropdown-root">
							<a href="javascript:void(0);" data-treeid="2">
								<i class="menu-icon fa fa-th"></i>
								<span class="mm-text mmc-dropdown-delay animated fadeIn">订单管理</span>
							</a>
							<ul class="mmc-dropdown-delay animated" style="">
								<li >
									<a href="#wxPayOrder/adminList" class="navitem" data-treeid="2.1">
									<span class="mm-text">订单查询</span>
									</a>
								</li>
								<li class="">
									<a href="#" class="navitem" data-treeid="2.2">
									<span class="mm-text">未完成管理</span>
									</a>
								</li>
								<li class="">
									<a href="#" class="navitem"  data-treeid="2.3">
									<span class="mm-text">已完成订单</span>
									</a>
								</li>
							</ul>
						</li>
						
						<li class="mm-dropdown  mm-dropdown-root">
							<a href="javascript:void(0);" data-treeid="2">
								<i class="menu-icon fa fa-th"></i>
								<span class="mm-text mmc-dropdown-delay animated fadeIn">审批管理</span>
							</a>
							<ul class="mmc-dropdown-delay animated" style="">
								<li >
									<a href="#userinfo/certificatelist" class="navitem" data-treeid="2.1">
									<span class="mm-text">商家认证</span>
									</a>
								</li>
								
							</ul>
						</li>
						<!-- navigation end -->
					</ul>
				</div>
			</div>
		</div>
		<!--  main-menu 左边菜单结束 -->
		
		<div id="main-menu-bg"></div>

		<div class="content-wrapper-bg"></div>
		<div id="content-wrapper">

			<div id="tinypagecontent" class="row" style="min-height: 494px; margin: 0px;">
				
			</div>

		</div>
	</div>

	<script type="text/javascript">
	
	//默认加载页面或刷新页面 定位
	$(function(){
		var refreshURL = window.location.href;
		//alert(refreshURL);
		//如果 refreshURL 包含"#",截取# 后的参数并ajax跳转
		var isExist = refreshURL.indexOf("#");
		
		if(isExist!=-1){
			//http://localhost:8080/tinyui/iframe.html#orderSearch.html
			//截取后orderSearch.html
			refreshURL = refreshURL.substring(isExist+1);
			$.ajax({
				url:refreshURL,
				//dataType:"html",
				type:"GET",
				cache:true,
				success:function(data){
					//alert("请求正常");
					$("#tinypagecontent").html(data);
				},
				error:function(){
					alert("请求异常");
				}
			});
		}
	});
	
	//点击菜单连接异步加载数据
	$(".mmc-dropdown-delay li").click(function(){
		var _clickTab = $(this).find("a").attr("href");
		_clickTab = _clickTab.substring(1);
		if(_clickTab=="") return false;
		// 赋予li class = active open
		$(".mmc-dropdown-delay li").attr("class","");
		$(this).attr("class","active open");
		
		//获取<a> href 超链接，发送ajax异步请求
		$.ajax({
			url:_clickTab,
			dataType:"html",
			type:"GET",
			cache:true,
			success:function(data){
				//alert("请求正常");
				$("#tinypagecontent").html(data);
			},
			error:function(){
				alert("请求异常");
			}
		});
	});
	
	
	
	
		init
				.push(function() {
					var menucount = $(".navigation:first>li").size();
					$("#topmenurap .navigation>li").css("width",
							100 / menucount + "%");
					var setFooterPostion = function() {
						$("#tinypagecontent").css(
								"min-height",
								$(window).height()
										- $("#tinypagecontent").offset().top
										- $("#tinyfooter").height()
										+ $(window).scrollTop() - 20 + "px");
					}
					$(window).on("scroll resize", function() {
						setFooterPostion();
					});
					setFooterPostion();
				});

		$(function() {




			function initPageOtherInfo(e) {
				$("#main-menu-inner li").removeClass("active open");
				$(e).parents("li").addClass("active open");
				var href = $(e).attr("href");
				var setNavPos = false, currenttxt = "";
				$("#main-menu-inner a")
						.each(
								function() {
									if (!setNavPos
											&& $(this).attr("href") == href) {
										$('.postion-nav>.breadcrumb').html(
												'<li>所在位置： </li><li>首页</li>');
										$(this)
												.parents("li")
												.find("span:first")
												.each(
														function() {
															$(
																	'.postion-nav>.breadcrumb')
																	.append(
																			"<li>"
																					+ $(
																							this)
																							.html()
																					+ "</li>");
															currenttxt = $(this)
																	.html()
																	+ "-"
																	+ currenttxt;
														});
										$(this).closest("li")
												.addClass("active");
										setNavPos = true;
									} else {
										$(this).closest("li").removeClass(
												"active");
									}
								})
				$(document).attr("title", currenttxt + " TinyUiEnterprise展示");
			}
			function setPageInfo(e) {
				initPageOtherInfo(e);
				var href = $(e).attr("href");
				$.pjax({
					url : href.replace(/\.pagelet/, ".page").replace(/\.page/,
							".pagelet"),
					container : '#tinypagecontent',
					replaceStr : ".page"
				});
			}
			$(document)
					.on(
							"pjax:end",
							function() {
								var hash = location.pathname;
								$("#main-menu-inner .navigation li a[href]")
										.each(
												function() {
													if ($(this).attr("href")
															.indexOf(".page") == "-1")
														return true;
													if (hash == $(this).attr(
															"href")) {
														initPageOtherInfo(this);
														return false;
													}
												});
								SyntaxHighlighter.highlight();
							})
					.on(
							"pjax:error",
							function(e) {
								$("#tinypagecontent")
										.html(
												'<h4 class="ajax-loading-error"><i class="fa fa-warning txt-color-orangeDark"></i> Error 404! Page not found.</h4>')
							})
					.on(
							"click",
							"a:not([data-toggle=modal])[href],[data-pjax]",
							function() {
								if ($(this).attr("href").indexOf(".page") == "-1")
									return;
								setPageInfo(this);
								return false;
							});

			init.push(function() {
				var hash = location.pathname;
				$("#main-menu-inner .navigation li a[href]").each(function() {
					if ($(this).attr("href").indexOf(".page") == "-1")
						return true;
					if (!hash || hash == $(this).attr("href")) {
						setPageInfo(this);
						return false;
					}
				});
			});





		});

		function TinyAjaxloadContent(a, b) {
			var time = "";
			a = a.replace(/\.pagelet/, ".page").replace(/\.page/, ".pagelet");
			$
					.ajax({
						type : "GET",
						url : a,
						dataType : "html",
						beforeSend : function() {
							b[0] == $("#tinypagecontent")[0]
									&& ($("html").animate({
										scrollTop : 0
									}, "fast"));
							time = setTimeout(
									function() {
										$("#tinypagecontent")
												.append(
														'<h1 class="ajax-loading-animation tinyLoadingContent" ><i class="fa fa-cog fa-spin"></i> 加载...</h1>');
									}, 150);
						},
						success : function(a) {
							if (time)
								clearTimeout(time);
							b.html(a);

							SyntaxHighlighter.highlight();
						},
						error : function() {
							b
									.html('<h4 class="ajax-loading-error"><i class="fa fa-warning txt-color-orangeDark"></i> Error 404! Page not found.</h4>')
						},
						cache : false
					})
		}
	</script>



	<a href="javascript:void(0)" class="toTop" title="返回顶部"
		alt="backToTopTxt" style="display: none;">返回顶部</a>
	<audio controls="controls" style="display: none;"></audio>
	<div id="small-screen-width-point"
		style="position: absolute; top: -10000px; width: 10px; height: 10px; background: #fff;"></div>
	<div id="tablet-screen-width-point"
		style="position: absolute; top: -10000px; width: 10px; height: 10px; background: #fff;"></div>
	<div id="mm" class="m-menu">
		<div class="menu-line"></div>
		<div class="menu-item" id="mm-tabupdate">
			<div class="menu-text">刷新</div>
		</div>
		<div class="menu-sep"></div>
		<div class="menu-item" id="mm-tabclose">
			<div class="menu-text">关闭</div>
		</div>
		<div class="menu-item" id="mm-tabcloseother">
			<div class="menu-text">关闭其他</div>
		</div>
		<div class="menu-item" id="mm-tabcloseall">
			<div class="menu-text">关闭全部</div>
		</div>
	</div>
</body>
</html>