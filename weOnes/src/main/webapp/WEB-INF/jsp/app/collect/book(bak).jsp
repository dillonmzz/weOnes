<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- -->
<!DOCTYPE html>
<html lang="zh-CN"><head>
	<title>我要参加</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<meta name="apple-moblie-web-app-capable" content="yes">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/ratchet.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/book.css">
	<script src="<%=request.getContextPath()%>/resources/app/js/bundle-ju.js"></script>
	<script src="<%=request.getContextPath()%>/resources/app/js/cities.js"></script>

	</head>
	<body class="">
		<header class="zw-enjoy-header">
			<center>
				<a class="zw-enjoy-back fleft zwback"></a>
				我要参加
				<a class="fright"></a>
			</center>
		</header>
		<div class="zw-enjoy-body">       
			<input type="hidden" name="pay_type" id="pay_type" value="1">
			<input type="hidden" name="activity_id" value="78201">
			<div class="zw-enjoy-container ">
				<div class="zw-enjoy-active">
					<em class="zw-enjoy-active-imgs" style="background-image:url()"></em>
					<div class="zw-enjoy-active-introduce">         
					<!--活动标题-->
						<div>
							<h3 class="zw-enjoy-active-title">${collect.name}</h3>
							<p class="small"><em class="zw-enjoy-active-icon"></em>${collect.address}</p>
						</div>
					</div>
				</div>
				<h2 class="fleft">预订信息</h2>
				<ul class="zw-enjoy-order-msg fleft">
					<!--年月时间1-->
					<li id="sAnytime" style="display:none">
						<span>日期</span>
						
						<div class="zw-enjoy-active-date" style="padding:5px 15px 3px 40px;line-height:1.2;display:-webkit-flex;display:flex;
						-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-flex-direction:row-reverse;
						flex-direction: row-reverse;color:#6b6b6b !important;">
						</div>
					</li>

				<c:choose>
					<c:when test="${collect.timeType=='sin'&& collect.timeSection=='1' }">
						<li id=""> <!-- <li id="sDate">  -->
							<em class="zw-enjoy-rightarrow fright"></em>
							<span>日期</span>
							<label class="zw-enjoy-active-date ">
							<div class="zw-dib selected" id="selectDate" style="margin-left:0">
							${collect.timeDesc}
							</div>
							</label>
						</li>
						
						<!--场次时间-->
						<li id="sTime">
							<em class="zw-enjoy-rightarrow fright"></em>
							<span>时间</span>
							<label class="zw-enjoy-active-date">
							<div class="zw-dib selected" id="selectTime"></div>
							<input type="hidden" name="schedule_id" value="1198448">
							</label>
						</li>
					</c:when>
					<c:when test="${collect.timeType=='sin'&& collect.timeSection>1 }">
						
					</c:when>
					<c:otherwise>
						<li id="sDate">
							<em class="zw-enjoy-rightarrow fright"></em>
							<span>日期</span>
							<label class="zw-enjoy-active-date ">
							<div class="zw-dib selected" id="selectDate" style="margin-left:0">
							</div>
							</label>
						</li>
					</c:otherwise>
				</c:choose>

					<!--场次时间-->
					<li id="sTime">
						<em class="zw-enjoy-rightarrow fright"></em>
						<span>时间</span>
						<label class="zw-enjoy-active-date">
							<div class="zw-dib selected" id="selectTime"></div>
							<input type="hidden" name="schedule_id" value="1198448">
						</label>
					</li>
					<!--价格-->
					<li id="sPrice">
						<em class="zw-enjoy-rightarrow fright"></em>
						<span>价格</span>
						<label class="zw-enjoy-active-date">
							<input type="hidden" name="ticket_id" value="3417979">
							<div name="cost" class="zw-dib selected" id="selectPrices">￥${collect.price}</div>
						</label>
					</li>
					<li>
						<span>数量</span>
						<div class="col-6 fright">
							<div class="numControl numMinus">
								<i style="margin:0;" class="zw-enjoy-order-msg-minus" onclick="" disabled="disabled"></i>
							</div>
							<input class="zw-enjoy-order-msg-num" name="num" style="line-height:1;" type="tel" value="1">
							<div class="numControl numPlus" style="padding-right:0;">
								<i style="margin:0;" class="zw-enjoy-order-msg-plus" onclick=""></i>
							</div>
						</div>
					</li>
				</ul>
				<h2 class="fleft">预订人</h2>       
				<ul class="list-none zw-enjoy-order-person fleft">
					<li class="shared-info">
						<span>手机</span>
						<div class="zw-enjoy-order-person-input">
							<input class="required" type="tel" value="18810886688" id="phone" name="phone" placeholder="接收预订成功短信">
						</div>
					</li>
				</ul>    
				<!--add fuxiaolin end  -->
				<h2 class="fleft">支付方式</h2>
				<ul class="zw-enjoy-order-msg payways fleft">
					<!-- add fxl start -->
					<li class="zw-weixin-payway zw-choose-payway clearfix ">
						<span class="wx-icon-imgs fleft">
							<img src="images/wechat_48px_1164480_easyicon.net.png">
						</span>
						<div class="wx-tip-online fleft">
							<p>微信支付 <em></em></p>
						</div>
						<i class="zw-enjoy-payway fright" pay_type="1"></i>
					</li>
				</ul>
				<div style="clear:both;color:#00d5e5;font-size:13px;padding:10px 0 0 10px;"></div>
			</div>
		</div>
		<footer>
			<span class="payfor"><span style="color:#6b6b6b;">合计</span>￥${collect.price }</span>
			<input class="zw-enjoy-msg-submit" type="button" value="确认">
		</footer>
		<div class="blackback displaynone" id="backdrop"></div>
		<div id="spinnerWrap" class="displaynone">
			<span class="spinner show"></span>
		</div>
		<!-- add start -->
		<div class="zw-pay-preferential zw-enjoy-slide right" id="zw-pay-preferential">
			<div class="zw-pay-preferential-top">
				<center class="clearfix">
					<span class="fleft close"></span>
					使用优惠
				</center>
			</div>
			<div class="zw-pay-preferential-list">
				<ul>
				</ul>
			</div>
		</div>
		<div class="zw-order-bg" id="zw-order-bg"></div>
		<div class="zw-order-date zw-enjoy-slide bottom" id="zw-order-date">
			<div class="zw-order-date-list">
				<h2>选择日期</h2>
				<div class="zw-order-week">
					<ul>
						<li>日</li>
						<li>一</li>
						<li>二</li>
						<li>三</li>
						<li>四</li>
						<li>五</li>
						<li>六</li>
					</ul>
				</div>
				<div class="zw-order-month-wrap">
					<div class="zw-order-date-close">
						关闭
					</div>
				</div>
			</div>
		</div>
		<!-- 
		<div class="zw-order-price zw-enjoy-slide bottom" id="zw-order-price">
			<div class="zw-order-price-list">
				<h2>价格</h2>
				<ul class="price-list">
					<li ticket-name="" ticket-id="3417979" data-avail="9999" data-val="49" class="cur">
						<div class="zw-price-icon"></div>
							<div class="con">
								<h5>价格名称</h5>
								<p>拉坯体验2小时，2块景德镇高白泥,每块约500g,可做两件作品</p>
							</div>
							<div class="fright price">￥88.99</div>
					</li>
				</ul>
			</div>
			<div class="zw-order-price-close">
				关闭
			</div>
		</div> -->
		<div class="zw-order-time zw-enjoy-slide bottom" id="zw-order-time">
			<div class="zw-order-time-list">
				<h2>选择场次</h2>
				<ul class="time-list"><li data-end-time="21:00" data-start-time="11:00" data-val="1198448" class="cur"><div class="zw-time-icon"></div><div class="zw-time-fw">11:00—21:00</div></li></ul>
			</div>
			<div class="zw-order-time-close">
				关闭
			</div>
		</div>
		<script src="<%=request.getContextPath()%>/resources/app/js/common.js"></script>
		<script>
			var any_time, is_weixin, data, sticky, id, raw, hasMarketing, needCheckMarketing, voucher_type, voucher, vouchers, coupon, phone, convert_code, use_coupon;
			try{
				any_time = '0';
				is_weixin = Browser.app == 'wechat' ? '1' : '0';//微信1 否则0
				data  =  '';
				data = data.split(',');
				id = '78201';
				sticky = ''
				raw = {"status":"success","result":{"date":["2016-07-27","2016-07-28","2016-07-29","2016-07-30","2016-07-31","2016-08-01","2016-08-02","2016-08-03","2016-08-04","2016-08-05","2016-08-06","2016-08-07","2016-08-08","2016-08-09","2016-08-10","2016-08-11","2016-08-12","2016-08-13","2016-08-14","2016-08-15","2016-08-16","2016-08-17","2016-08-18","2016-08-19","2016-08-20","2016-08-21","2016-08-22","2016-08-23","2016-08-24","2016-08-25","2016-08-26","2016-08-27","2016-08-28","2016-08-29","2016-08-30","2016-08-31","2016-09-01","2016-09-02","2016-09-03","2016-09-04","2016-09-05","2016-09-06","2016-09-07","2016-09-08","2016-09-09","2016-09-10","2016-09-11","2016-09-12","2016-09-13","2016-09-14","2016-09-15","2016-09-16","2016-09-17","2016-09-18","2016-09-19","2016-09-20","2016-09-21","2016-09-22","2016-09-23","2016-09-24","2016-09-25","2016-09-26","2016-09-27","2016-09-28","2016-09-29","2016-09-30","2016-10-01","2016-10-02","2016-10-03","2016-10-04","2016-10-05","2016-10-06","2016-10-07","2016-10-08","2016-10-09","2016-10-10","2016-10-11","2016-10-12","2016-10-13","2016-10-14","2016-10-15","2016-10-16","2016-10-17","2016-10-18","2016-10-19","2016-10-20","2016-10-21","2016-10-22","2016-10-23","2016-10-24","2016-10-25","2016-10-26","2016-10-27","2016-10-28","2016-10-29","2016-10-30","2016-10-31","2016-11-01","2016-11-02","2016-11-03","2016-11-04","2016-11-05","2016-11-06","2016-11-07","2016-11-08","2016-11-09","2016-11-10","2016-11-11","2016-11-12","2016-11-13","2016-11-14","2016-11-15","2016-11-16","2016-11-17","2016-11-18","2016-11-19","2016-11-20","2016-11-21","2016-11-22","2016-11-23","2016-11-24","2016-11-25","2016-11-26","2016-11-27","2016-11-28","2016-11-29","2016-11-30","2016-12-01","2016-12-02","2016-12-03","2016-12-04","2016-12-05","2016-12-06","2016-12-07","2016-12-08","2016-12-09","2016-12-10","2016-12-11","2016-12-12","2016-12-13","2016-12-14","2016-12-15","2016-12-16","2016-12-17","2016-12-18","2016-12-19","2016-12-20","2016-12-21","2016-12-22","2016-12-23","2016-12-24","2016-12-25","2016-12-26","2016-12-27","2016-12-28","2016-12-29","2016-12-30","2016-12-31"],"marketing":{"count":0,"list":[]},"cost":"49-49","activity":{"cover":"http://cdn.wanzhoumo.com/data/public/activity/1461653751.14093c.jpg!720x500","title_vice":"","unsubscribe":1,"mapurl":"http://restapi.amap.com/v3/staticmap?size=700*500&scale=1&location=116.393705,39.942299&zoom=12&markers=-1,http://www.wanzhoumo.com/assets/app/releasecator.png,0:116.393705,39.942299&key=48874191750929ccfd1c9802c2e56846","anytime":0,"id":"78201","time":"03月16日-12月31日 11:00-21:00","poi":{"address":"北京市西城区旧鼓楼大街清秀巷 1 号","lon":"116.393705","id":"24957","title":"清秀阁陶艺吧（鼓楼店）","lat":"39.942299"},"title":"爱他就带他来鼓楼一起体验陶艺吧"},"feature":[{"icon":"http://cdn.wanzhoumo.com/icons/app/suishitui.png","title":"随时退"}],"voucher":[{"type":1,"title":"验证码","dis":"直接出示周末码消费","info":[]}],"is_free":2,"user":{"phone":"","is_auth":0},"time_txt":"03月16日-12月31日 11:00-21:00","pay_types":[{"title":"支付宝","desc":"","allow":1,"pay_type":1,"vice_title":""},{"title":"使用微信支付？","desc":"请在微信中打开此页面","allow":1,"pay_type":2,"vice_title":""},{"title":"银联支付","desc":"","allow":1,"pay_type":3,"vice_title":""},{"title":"招商银行","desc":"","allow":1,"pay_type":4,"vice_title":""}],"info":[{"key":"phone","name":"手机","required":1,"type":"number","length":11,"tip":"接收预订成功短信","regex":"^1(3|4|5|7|8)\\d{9}$","subtitle":"","private":0,"tips_extra":""}]}};
				hasMarketing = needCheckMarketing = '0';
				voucher_type = raw && raw.result && raw.result.voucher ? raw.result.voucher[0].type : 0;
				vouchers = raw && raw.result && raw.result.voucher;
				voucher = vouchers && vouchers.length > 0 ? vouchers[0] : null; 
				phone = '0';
				convert_code = '0';
				coupon = {"status":"error"};
				use_coupon = '0';
				errorMsg = '';

			}catch(error){
				console.log(error);
			}
			var choseCityJs = "<%=request.getContextPath()%>/resources/app/js/choseCity.js";
		</script>
		<script src="<%=request.getContextPath()%>/resources/app/js/book.js"></script>
		<script>
			$('body').on('tap', '.zwback', function(e){
				e.preventDefault();
				e.stopPropagation();
				if(typeof zwBack === 'function')
					zwBack(150);
			});
			$('body').on('tap', '.downApp', function(e){
				try{
					var id = activity_id || id || '';
					var track_tag = track_tag || '';
					trackEvent(['_trackEvent', track_tag, '点击', id]);
				}catch(err){

				}
				openApp();
			});

			var canjia = $('.book[data-id], .zw-book[data-id]');
			if(canjia.length > 0){
				canjia.each(function(){
					var activityId = $(this).attr('data-id');
					if(!activityId){
						return
					}
					$(this).on('tap', function(e){
						var id = activity_id || id || '';
						trackEvent(['_trackEvent', 'mPay我要参加', '点击', id]);
						var gs = typeof sticky !== 'undefined' && sticky != '' ? '?' + sticky : '';
						location.href = '/book/' + activityId + gs;
					});
				});
			}
			var inappLinks = $('a[href^=inapp]:not(.book,.zw-book)');
			if(inappLinks.length > 0 && !navigator.userAgent.toLowerCase().match(/com\.xisue\.zhoumo/)){
				inappLinks.each(function(){
					var r = '', qs, link;
					link = decodeURIComponent($(this).attr('href'));
					qs = utils.parseQuery(link);  
					if(/^inapp:\/\/actlist/.test(link)){
						r = '/category' + utils.obj2qs(qs);
					}else if(/^inapp:\/\/main/.test(link)){
						r = '/search';
					}else if(/^inapp:\/\/act/.test(link) && /\d+/.test(qs.id)){
						r = '/huodong/' + qs.id;  
					}else if(/^inapp:\/\/topic/.test(link) && /\d+/.test(qs.id)){
						r = '/zhuanti/' + qs.id;  
					}else if(/^inapp:\/\/marketing/.test(link) && /\d+/.test(qs.id)){
						r = '/marketing/' + qs.id;  
					}else if(/^inapp:\/\/(user|order|shop|login|topiclist|calendar|dailylist|nearby)/.test(link)){
						r = link;
					}else{
						r = link.replace(/^inapp/, 'http'); 
						if(!/^http/.test(r)){
							r = 'http://' + r;  
						}
					}
					if(typeof sticky !== 'undefined' && sticky != ''){
						if(r.indexOf('?') > -1){
							r += '&' + sticky;
						}else{
							r += '?' + sticky;
						}
					}
					$(this).attr('href',r);
				});
			};
		</script>
		<div id="qq-sendUrl-btn"></div>
	</body>
</html> 
<script>
$(".zw-enjoy-msg-submit").click(function() {
	$.post("<%=request.getContextPath()%>/collect/bookproduct",
	  function(data,status){
		   if(data.errorCode==0){
			   //跳转至支付页面
			   console.log("预定产品成功,返回的json数据"+data.datas.outTradeNo);
			   window.location.href="<%=request.getContextPath()%>/collect/topay/"+data.datas.outTradeNo;
			   return false;
		   }
	  });
});
</script>