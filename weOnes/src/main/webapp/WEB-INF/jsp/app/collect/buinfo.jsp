<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>商家介绍</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/common.css">
	<script src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
</head>
<style>
	.timeoutSide {width: 100%;margin: 0 auto}
	.listTop {background: #4989af url(images/timelineBanner_wap3.jpg) no-repeat bottom;overflow: hidden;position: relative;z-index: 100}
	.listTop .default_img {width: 100%;height: 54px;position: absolute;left: 0;top: 18px;z-index: 103;text-align: center}
	.listTop .default_img p {width: 52px;height: 52px;border-radius: 52px;margin: 0 auto}
	.listTop .default_img img {width: 50px;height: 50px;border-radius: 50px;margin: 1px}
	.focus_Cz #attent_Btn.wgz {background-color: #eb6409;}
	.focus_Cz #attent_Btn {display: block;width: 62px;height: 24px;line-height: 24px;text-align: center;font-size: 12px;position: absolute;top: 10px;right: 10px;background-color: #c8c8c8;color: #FFF;border-radius: 3px;z-index: 104}
	.listTop span#tl_nick {display: block;font-size: 16px;color: #FFF;position: absolute;height: 18px;line-height: 18px;z-index: 103;width: 100%;overflow: hidden;text-align: center;left: 0;top: 75px}
	.timeline_K_tit {width: 100%;position: absolute;left: 0;top: 100px;z-index: 103}
	.timeline_K_tit p.l {padding-right: 4%;text-align: right}
	.timeline_K_tit p {display: block;width: 46%;float: left;overflow: hidden;text-align: center;font-size: 12px;color: #FFF}
	.timeline_K_tit span {color: #FFF;font-size: 16px}
	.timeline_K_tit p.r {padding-left: 4%;text-align: left}
	.list_jj {overflow: hidden;margin: 125px auto 10px;width: 100%}
	.list_jj p {padding: 0 15px;line-height: 16px;font-size: 12px;color: #FFF;text-align: center}
	#timelineNav_b {height: 40px}
	#timelineNav_b a, #timelineNav_t a {display: block;width: 50%;height: 39px;float: left;border-bottom: 1px solid #d9d9d9}
	#timelineNav_b a.thisOver, #timelineNav_t a.thisOver {border-bottom: 1px solid #eb6409}
	#timelineNav_b a span, #timelineNav_t a span {display: block;width: 100%;height: 38px;line-height: 38px;border-bottom: 1px solid #FFF;font-size: 14px;color: #666;text-align: center}
	#timelineNav_b a.thisOver span, #timelineNav_t a.thisOver span {border-bottom: 1px solid #eb6409;color: #eb6409}
	#act_aremaking {overflow: hidden;background-color: #efeff4}
	.act_aremakingUl {overflow: hidden}
	.act_aremakingUl li {overflow: hidden;margin-top: 10px}
	.act_aremakingUl li a {display: block;background-color: #FFF;padding: 12px;position: relative;overflow: hidden}
	.act_aremakingUl li a .act_aremakingTitle {font-weight: normal;font-size: 14px;color: #000;line-height: 18px;text-align: left;word-break: break-all;display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;overflow: hidden;position: absolute;left: 161px;top: 10px}
	.act_aremakingUl li a .act_aremakingNr {height: 76px;position: relative;padding-bottom: 5px}
	.act_aremakingUl li a .act_aremakingNr img {width: 137px;height: 80px;position: absolute;left: 0px;top: 0px}
	.act_aremakingUl li a .act_aremakingNr p {height: 18px;line-height: 18px;text-align: left;font-size: 12px;color: #aaa;padding: 44px 0 0 149px}
	.act_aremakingUl li a .act_aremakingNr .wrap .address {padding: 0 0 0 149px;float: left;display: block;text-overflow: ellipsis;white-space: nowrap;-o-text-overflow: ellipsis;overflow: hidden}
	.act_aremakingUl li a .act_aremakingNr .wrap .price {font-size: 14px;color: #eb6409;float: right;padding-top: 0}
	.act_aremakingUl li a .act_aremakingNr .wrap .price span {font-size: 12px;padding: 0}
	.act_aremakingUl li a .act_aremakingNr .wrap .price b {font-size: 16px;padding: 0}
	#tjb-all {overflow: hidden;background-color: #efeff4}
	.List-record {overflow: hidden}
	.List-record li {overflow: hidden}
	.List-record li span.dian {display: none}
	.List-record li.listDay .listZu {overflow: hidden}
	.tjb-A {display: block;background-color: #FFF;overflow: hidden;margin-top: 10px;padding: 12px;position: relative}
	.tjb-A .md-kj_P {font-weight: normal;font-size: 14px;color: #000;line-height: 18px;text-align: left;word-break: break-all;display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;overflow: hidden;position: absolute;left: 161px;top: 10px}
	.tjb-A .tjb-B {height: 76px;position: relative;padding-bottom: 5px}
	.tjb-A .tjb-B .md-kj_I {position: absolute;width: 137px;height: 80px;left: 0px;top: 0px}
	.tjb-A .tjb-B .tjb-BP {height: 18px;line-height: 18px;text-align: left;font-size: 12px;color: #aaa;padding: 46px 0 0 149px}
	.tjb-A .tjb-B .wrap .address {height: 12px;padding: 3px 0 0 149px;float: left;font-size: 12px;color: #aaa;overflow: hidden;word-break: break-all;display: -webkit-box;-webkit-line-clamp: 1;-webkit-box-orient: vertical;overflow: hidden}
	.tjb-A .tjb-B .wrap .price {font-size: 14px;color: #eb6409;float: right;padding-top:2px;}
	.tjb-A .tjb-B .wrap .price span {font-size: 12px;padding-right:4px}
	.tjb-A .tjb-B .wrap .price b {font-size: 16px;padding:0}
</style>
<body>
	<div class="timeoutSide">
		<div class="listTop" style="background-size: 483px 376.74px">
			<div class="default_img">
				<p>
					<img src="${buinfo.imgurl }">
				</p>
			</div>
			<div class="focus_Cz">
				<a id="attent_Btn" class="wgz" href="javascript:void(0)" style="display: inline-block;">关注Ta</a>
			</div>
			<span id="tl_nick">${buinfo.nickName }</span>
			<div class="timeline_K_tit">
				<p class="l">
					<span>手机：</span>
					${buinfo.mobile }
				</p>
				<p class="r">
					<span>地址：</span>
					${buinfo.province}.${buinfo.city}
				</p>
			</div>
			<div class="list_jj">
				<p>${buinfo.introduction}</p>
			</div>
		</div>
		<div id="timelineNav_b">
			<a id="t_nav_b_l" class="thisOver" name="1" href="javascript:void(0)">
				<span>进行中的活动</span>
			</a>
			<a id="t_nav_b_r" name="5" href="javascript:void(0)" class="">
				<span>全部活动</span>
			</a>
		</div>
		<!--进行中的活动-->
		<div id="act_aremaking" class="list-over">
			<!--  <ul class="act_aremakingUl">
				<li class="act_aremakingLi" data-repeat-rendered="repeatCallback">
					<a class="act_aremakingA" ontouchstart="" href="/party/4gimu">
						<h3 class="act_aremakingTitle">来这里享受异域风情&nbsp;威海巴厘林海度假村会员招募</h3>
						<div class="act_aremakingNr">
							<img src=" " alt="">
							<p class="act_aremakingP">08-15 00:00开始</p>
							<div class="wrap"> 
								<p class="address auto_w" style="width: 255px;">山东威海荣成市成山镇河口</p>
								<span class="price">
									<span>¥</span>
									<b>9880</b>
								</span>
							</div>
						</div>
					</a>
				</li>
			</ul>-->
		</div>
		<!--进行中的活动end-->
		<!--全部活动 start-->
		<div id="tjb-all" class="list-over" style="display: none;">
			<!-- <ul class="List-record" id="ul_time_line" style="min-height: 184px;">
				<li class="listDay">
					<span class="dian"></span>
					<div class="listZu">
						<a class="tjb-A" href="跳转至本活动详情页">
							<div class="md-kj_P">来这里享受异域风情&nbsp;威海巴厘林海度假村会员招募</div>
							<div class="tjb-B">
								<img class="md-kj_I" alt="" src=" ">
								<p class="tjb-BP">08-15 18:00开始</p>
								<div class="wrap"> 
									<p class="address auto_w" style="width: 255px;">山东威海荣成市成山镇河口</p>
									<span class="price"><span>¥</span><b>9880</b></span>
								</div>
							</div>
						</a>
					</div>
				</li>
			</ul> -->
		</div>
	</div>
	<script>
		$(document).ready(function(){
			$("#timelineNav_b a").click(function(){
				$("#timelineNav_b a").eq($(this).index()).addClass("thisOver").siblings().removeClass('thisOver');
				$("div.list-over").hide().eq($(this).index()).show();
			});
		});
	</script>
</body>
</html>