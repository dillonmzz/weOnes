<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html ng-app="app" ng-controller="detail" class="ng-scope">
	<head>
		<style type="text/css">
			@charset "UTF-8";
			[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide {
				display: none !important;
			}
			ng\:form {display: block;}
			.ng-animate-block-transitions {transition: 0s all!important;-webkit-transition: 0s all!important;}
			.ng-hide-add-active,.ng-hide-remove {display: block!important;}
		</style>
		<title ng-bind="head.title" class="ng-binding"></title>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<meta name="viewport" content="minimal-ui,width=device-width,initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
		<meta charset="UTF-8">
		<link href="css/hobbitweb.css" rel="stylesheet" type="text/css">
		<link href="css/components.css" rel="stylesheet" type="text/css">
		<link href="css/app3.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/MD_footer.css">
		<link rel="stylesheet" type="text/css" href="css/css_detail_activity/activity_add.css">
		<script src="js/jquery-1.8.3.js"></script>
	</head>
	
	<body class="page flex-container" style="visibility: visible;">
		<div class="detail">
			<div class="navbar">
				<div class="container">
					<a class="brand" href="/">
						<img class="brand-logo" src="resources/images/logo.png" alt=""> 
						<i class="iconfont icon-brandname brand-name"></i> 
						<i class="iconfont icon-slogan brand-slogan"></i>
					</a> 
					<span class="navbar-right">
						<div class="navbar-item nick-name ng-scope">
							<a class="ng-isolate-scope">用户登录</a>
						</div>
						<a class="navbar-item c-999" href="tel:01058693707">
						</a> 
						<a class="navbar-item" href="#">
							<i class="iconfont icon-mobilephone"></i> 
							手机WEONS
						</a>
					</span>
				</div>
			</div>
			<div class="header">
				<div class="container">
					<div class="place-name ng-binding">
						
						<span class="place-phone ng-binding">
						</span>
					</div>
					<div class="place-banner">
						<div style="height: 100%;width: 65%;" class="ui-frame ng-isolate-scope">
							<img class='goodsPreviewPhoto' src="" style="opacity: 1; width: 100%; left: 0px; position: absolute; height: 515px; top: 0px;">
						</div>	
						<div class="place-summary" style="width: 40%; left: 62%;">
							<div class="summary-item functionalities ng-scope">
								
							</div>
							<div class="summary-item ng-binding ng-scope positions">
								
							</div>
							
							<div class="detail-cuetips-btm">
								<span class="btm-item">
									<span class="btm-item1">
										<i class="iconfont icon-dianzanbefore" style=""></i>
									</span>
									<span class="btm-item1 zan">150点赞</span>
								</span>
								<span class="btm-item">
									<span class="btm-item1">
										<i class="iconfont icon-favorite"></i>
									</span>
									<span class="btm-item1 ng-binding">收藏</span>
								</span>
							</div>
							
							<div class="summary-item ng-scope" style="padding:20px 0 20px 20px;font-size:18px;">
								<span>价格：</span>
								<span class="price-ccy" style="color:red">￥</span><span class="ng-binding price" style="color:red"></span>
							</div>
							<div class="summary-item ng-scope" style="padding:10px;">
								<a class="payment" href="firm-order.html">立即参加</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="detail-main">
				<div class="container">
					<div class="detail-info">
						<div class="filter-margin"></div>
                        <div class="place-info ng-scope" id="history">
							<span class="place-title" style="font-size: 18px;">其他活动</span>
							<div class="place-events">
								<ul>
									<li class="ng-scope">
										<span class="ui-frame event-img ng-isolate-scope">
											<img  src="http://7xlh86.com2.z0.glb.qiniucdn.com/EVENTIMG/R92ZP4/087f4c75-7bbe-4d9d-a024-7c7cce9799b8-s1" style="opacity: 1; height: 261px;width:100%; left: 0px; top: -30.5px; position: absolute;">
										</span>
										<div class="event-title">
											<div>
												<h2 class="ng-binding">开学前的最后一次相聚</h2>
												<span class="ng-binding">2016-02-17</span>
												<span class="event-content ng-binding">
													今天来大班别墅聚会的，是一群大一的学生。他们笑厣如花，清纯礼貌，明媚的让我猝不及防。
												</span>
											</div>
											<b class="ng-binding">6张</b>
										</div>
									</li>
									<li class="ng-scope">
										<span class="ui-frame event-img ng-isolate-scope">
											<img  src="http://7xlh86.com2.z0.glb.qiniucdn.com/EVENTIMG/29EO74/3d862ff4-3479-468b-b8f6-6057f73db097-s1" style="opacity: 1; height: 463.819px;width:100%; left: 0px; top: -131.909px; position: absolute;">
										</span>
										<div class="event-title">
											<div>
												<h2 class="ng-binding">除夕夜，“兄弟姐妹”在一起。</h2>
												<span class="ng-binding">2016-02-07</span>
												<span class="event-content ng-binding">
													昨夜，来大班别墅过年的客人们是专业做披萨的店—pizza family团队！他们“一家人”共同协作做了一桌子饭菜，年味儿十足。365天在北京付出辛苦的人们，也许路途遥远春节回不到父母身边，其实只要有“兄弟姐妹”陪着，北京就是家，大班就是家！2016年，大班别墅会一如既往的接待公司团队聚会 朋友聚会 生日聚会 家庭聚会…我会更努力为大家提供更舒适更美观的别墅家。
												</span>
											</div>
											<b class="ng-binding">6张</b>
										</div>
									</li>
								</ul>
								<ul>
									<li class="ng-scope">
										<span class="ui-frame event-img ng-isolate-scope">
											<img src="http://7xlh86.com2.z0.glb.qiniucdn.com/EVENTIMG/54YO89/0aff978e-c2a4-428d-8c16-45eed12a0cfc-s1" style="opacity: 1; height: 463.819px;width:100%; left: 0px; top: -131.909px; position: absolute;">
										</span>
										<div class="event-title">
											<div>
												<h2 class="ng-binding">给老公特别的生日惊喜</h2>
												<span class="ng-binding">2016-02-12</span>
												<span class="event-content ng-binding">
													今天的客人们来大班别墅过生日！要不要这么会玩儿，要不要这么会吃啊…！口水流满了地面哦。
												</span>
											</div>
											<b class="ng-binding">8张</b>
										</div>
									</li>
									<li class="ng-scope">
										<span class="ui-frame event-img ng-isolate-scope">
											<img src="http://7xlh86.com2.z0.glb.qiniucdn.com/EVENTIMG/K9D529/f072442a-5d57-4587-af66-66393e5c7048-s1" style="opacity: 1; height: 463.819px;width:100%; left: 0px; top: -131.909px; position: absolute;">
										</span>
										<div class="event-title">
											<div>
												<h2 class="ng-binding">4只比熊的生日party</h2>
												<span class="ng-binding">2016-01-20</span>
												<span class="event-content ng-binding">
													大班轰趴馆今天来的客人是四只比熊狗狗！6个美女一起花千元包场给狗狗过生日！我活了几十年头次接触给狗狗过生日，而且场面恢宏，精心布置…看着满屋子奔跑开心极点的狗狗们，感觉别墅内飘满了爱！终于感叹：我的生活还不如狗！你们也来感受下…
												</span>
											</div>
											<b class="ng-binding">12张</b>
										</div>
									</li>
								</ul>
							</div>
						</div>
                    
				        <div class="goods-info">
                            <div class="place-info ng-scope" style="padding-top: 65px;">
                                
                            </div>
                        </div>
						
						<span class="place-title" style="font-size:14px">位置信息</span>
						<div id="place-map" class="place-map ng-isolate-scope"></div>
						<div class="place-info" id="review-list">
							<span class="place-title">活动评论</span>
							<div class="place-reviews-rate ng-scope">
								<span class="review-count ng-binding">来自1条客户评论</span>
							</div>
							<button class="ui-btn btn-review-post">我来评价</button>
							<div class="ng-scope">
								<div class="place-reviews-tabs">
								</div>
								<div class="place-reviews">
									<div class="review-item ng-scope">
										<div class="review-user">
											<div class="avatar" style="background-image: url(http://metadata.qiniudn.com/avatar/female2.png)"></div>
											<span class="user-name ng-binding">莎莎</span>
										</div>
										<span class="review-content ng-binding">不错</span> 
										<span class="review-tips ng-binding">
											2016-08-28 大班别墅私房聚会
										</span>
										<div class="review-images ng-scope">
											<div class="ui-frame review-img ng-scope ng-isolate-scope">
												<img class="ng-isolate-scope" src="http://7xp070.com2.z0.glb.qiniucdn.com/PLACEREVIEWIMG/9Q15QG/e2afdda9-19c3-4379-853c-c27c25848697-s2" style="opacity: 1; height: 80px; width: 80px; left: 0px; top: 0px; position: absolute;">
											</div>
											<div class="ui-frame review-img ng-scope ng-isolate-scope">
												<img class="ng-isolate-scope" src="http://7xp070.com2.z0.glb.qiniucdn.com/PLACEREVIEWIMG/9Q15QG/ec6d1bff-207e-4443-b796-8e0361521438-s2" style="opacity: 1; height: 80px; width: 80px; left: 0px; top: 0px; position: absolute;">
											</div>
											<div class="ui-frame review-img ng-scope ng-isolate-scope">
												<img class="ng-isolate-scope" src="http://7xp070.com2.z0.glb.qiniucdn.com/PLACEREVIEWIMG/9Q15QG/6f89406d-c15d-4466-9e24-14dd86af2367-s2" style="opacity: 1; height: 80px; width: 80px; left: 0px; top: 0px; position: absolute;">
											</div>
										</div>
									</div>
									<div class="pager ng-isolate-scope" style="display: none;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<iframe src="./common/footer.html" frameborder="0" style="width: 100%; height: auto;"></iframe>
			
		</div>
		<div id="qq-sendUrl-btn" style="bottom: 24px; right: -177px;"></div>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=C66qwSVke9ytGPhDZWuimFRij6MuGlUR"></script>
		<script>
			$(function(){
			  var rumen = $(".positions").text();
				// 百度地图API功能
				var map = new BMap.Map("place-map");
				var point = new BMap.Point(116.331398,39.897445);
				map.centerAndZoom(point,12);
				// 创建地址解析器实例
				var myGeo = new BMap.Geocoder();
				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint(rumen, function(point){
					if (point) {
						map.centerAndZoom(point, 16);
						map.addOverlay(new BMap.Marker(point));
					}else{
						alert("您选择地址没有解析到结果!");
					}
				}, "北京市");
			});
		</script>
		<script type="text/javascript">
			var goodsId = '<%=request.getParameter("goodsId")%>';
		</script>
		<script src="js/data/activityDetailGoodsInfo.js"></script>
	</body>
</html>