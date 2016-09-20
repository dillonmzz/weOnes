<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>我要参加 </title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<meta name="apple-moblie-web-app-capable" content="yes">
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/ratchet.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/book.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/zepto.mdatetimer.css">
<script src="<%=request.getContextPath()%>/resources/app/js/zepto.js"></script>
<script src="<%=request.getContextPath()%>/resources/app/js/zepto.mdatetimer.js"></script>


</head>

<body>
<header class="zw-enjoy-header">
	<center>
		<a class="zw-enjoy-back fleft zwback"></a>
		我要参加
		<a class="fright"></a>
	</center>
</header>

	<div class="zw-enjoy-body">  
	<form action="" method="post" id="order-form" name="order-forms">
		<!-- productCollectId -->
		<input type="hidden" name="collectId" value="${collect.id}">
		<input type="hidden" name="timeId" id="collectTimeId" value="${collect.timeId}">
		<input type="hidden" name="timeSection" id="collectTimeSection" value="1">
	     
		<div class="zw-enjoy-container ">
			<div class="zw-enjoy-active">
				<em class="zw-enjoy-active-imgs" style="background-image:url(${collect.coverPhoto})"></em>
				<div class="zw-enjoy-active-introduce">
					<div>
						<h3 class="zw-enjoy-active-title" name="tjb-title">${collect.name}</h3>
						<p class="small" name="title-small"><em class="zw-enjoy-active-icon"></em>${collect.address}</p>
					</div>
				</div>
			</div>
			<h2 class="fleft">预订信息</h2>
			<ul class="zw-enjoy-order-msg fleft">
			
			<c:choose>
				<%-- 如果是单一多次时间,则以下拉框 的形式下拉框仅显示当前日期  --%>
				<c:when test="${collect.timeType =='sin' && collect.timeSection=='1' }">
					<li id="sAnytime" style="display:block">
						<span>日期</span>
						<div class="zw-enjoy-active-date" name="tjb-data-shijian" style="padding:5px 27px 3px 40px;line-height:45px;display:-webkit-flex;display:flex;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-flex-direction:row-reverse;flex-direction: row-reverse;color:#6b6b6b !important;">
							${collect.timeDesc}
						</div>
						<!-- <input type="hidden" name="timeSection" value="${collect.timeSection }">  -->
					</li>
				</c:when>
				<%-- 如果是单一多次时间,则以下拉框 的形式下拉框仅显示当前日期  --%>
				<c:when test="${collect.timeType =='sin' && collect.timeSection>1 }">
					<li id="sAnytime" style="display: block">
						<em class="zw-enjoy-rightarrow fright"></em><span>日期</span> 
						<label class="zw-enjoy-active-date">
							<!-- <div class="zw-dib" id="selectTimes">时间选择</div>  -->
							<input type="text" readonly id="selectTimes" name="schedule_id" placeholder="选择时间">
						</label>
					</li>
				</c:when>
				
				<%-- 如果是连续单一期次,首先选择日期,根据日期去查询有哪些期次 --%>
				<c:when test="${collect.timeType =='con'}">
					<li id="sDate" style="display:block">
						<em class="zw-enjoy-rightarrow fright"></em>
						<span>日期</span>
						<label class="zw-enjoy-active-date ">
							<input type="text" id="picktime" name="picktime" placeholder="选择日期" readonly ">
						</label>
					</li>
					
					<li id="sAnytime" style="display: block">
						<em class="zw-enjoy-rightarrow fright"></em><span>时间</span> 
						<label class="zw-enjoy-active-date">
							<!-- <div class="zw-dib" id="selectTimes">时间选择</div>  -->
							<input type="text" readonly id="selectTimes" name="schedule_id" placeholder="选择时间">
						</label>
					</li>
				</c:when>
			</c:choose>
			
				<li id="sPrice">
					<em class="zw-enjoy-rightarrow fright"></em>
					<span>价格</span>
					<label class="zw-enjoy-active-date">
						<input type="text" name="ticket_id" disabled="disabled" value="${collect.price}">
					</label>
				</li>
				<li>
					<span>数量</span>
					<div class="col-6 fright">
						<div class="numControl numMinus">
							<i style="margin:0;" class="zw-enjoy-order-msg-minus" onclick="" disabled="1"></i>
						</div>
						<input class="zw-enjoy-order-msg-num" name="quantity" style="line-height:1;" type="tel" value="1">
						<div class="numControl numPlus" style="padding-right:0;">
							<i style="margin:0;" class="zw-enjoy-order-msg-plus" onclick="" disabled="1"></i>
						</div>
					</div>
				</li>
			</ul>
			<h2 class="fleft">预订人</h2>       
			<ul class="list-none zw-enjoy-order-person fleft">
				<li class="shared-info">
					<span>手机</span>
					<div class="zw-enjoy-order-person-input">
						<input class="required" type="tel" value="" name="phone" placeholder="预订短信">
					</div>
				</li>
			</ul> 
			<!--add fuxiaolin end  -->
			<h2 class="fleft">支付方式</h2>
			<ul class="zw-enjoy-order-msg payways fleft">
				<li class="zw-weixin-payway zw-choose-payway clearfix ">
					<span class="wx-icon-imgs fleft">
						<img src="<%=request.getContextPath()%>/resources/app/images/wechat_48px_1164480_easyicon.net.png">
					</span>
					<div class="wx-tip-online fleft">
						<p>微信支付 <em></em></p>
					</div>
					<i class="zw-enjoy-payway fright choosed" pay_type="1"></i>
				</li>
			</ul>
		</div>
	</div>
	<footer>
		<span class="payfor" name="payfor">￥${collect.price}</span>
		<!-- <button class="zw-enjoy-msg-submit"  value="确认"> 
		<button class="zw-enjoy-msg-submit" >确认</button> -->
		<input type="button"  class="zw-enjoy-msg-submit" value="确认"/>
	</footer>
</form>
<div class="blackback displaynone" id="backdrop"></div>
<div id="spinnerWrap" class="displaynone">
	<span class="spinner show"></span>
</div>
<!-- add start -->
<div class="zw-order-bg" id="zw-order-bg"></div>

<!-- 弹出层开始 -->
	<div class="zw-order-bg" id="zw-order-bg"></div>
	<div class="zw-order-date zw-enjoy-slide bottom" id="zw-order-date">
		<div class="zw-order-data-list">
			<h2>请选择</h2>
			<ul class="data-list">
			</ul>
		</div>
		<div class="zw-order-date-close">关闭</div>
	</div>
	<div class="zw-order-time zw-enjoy-slide bottom" id="zw-order-time">
		<div class="zw-order-time-list">
			<h2>选择期次</h2>
			<ul class="time-list">
				<li data-end-time="22:00" data-start-time="10:00" class="cur">
					<div class="zw-time-icon"></div>
					<div class="zw-time-fw">10:00-22:00</div>
				</li>
			</ul>
		</div>
		<div class="zw-order-time-close">关闭</div>
	</div>
	<!-- 弹出层结束 -->


<script>
	$(function(){
		$('#picktime').mdatetimer({
			mode : 1, //时间选择器模式：1：年月日，2：年月日时分（24小时），3：年月日时分（12小时），4：年月日时分秒。默认：1
			format : 2, //时间格式化方式：1：2015年06月10日 17时30分46秒，2：2015-05-10  17:30:46。默认：2
			years : [2000, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017,2018,2019,2020], //年份数组
			nowbtn : true, //是否显示现在按钮
			onOk : function(){
				//alert('OK');
			},  //点击确定时添加额外的执行函数 默认null
			onCancel : function(){	
			}, //点击取消时添加额外的执行函数 默认null
		});	
	});
</script>
<!-- 
<script>
	$(function(){
		var valJao = $(".required").val();
		if(!(/^1[3|4|5|7|8]\d{9}$/.test(valJao))){
		   	return false;
		   	}else{
		   	return true;
			}
			$.ajax({
		   	type: "Post",
		   	url: " ",
		   	async: false,
		   	//方法传参的写法一定要对，与后台一致，区分大小写，不能为数组等，str1为形参的名字,str2为第二个形参的名字 
		   	data: $('#order-form').serialize(),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
			//返回的数据用data.d获取内容 
				if(data== ){
					$("#sDate").attr("display","block");
					$("#sAnytime").attr("display","none");
					$("ssTime").attr("display","block");
					$("sTime").attr("display","none");
				}else{
					$("#sDate").attr("display","none");
					$("#sAnytime").attr("display","block");
					$("ssTime").attr("display","none");
					$("sTime").attr("display","block");
					}
				}
			})
	})
</script> -->

<script>

    $(function(){
    	 //单一时间点击日期div触发事件
      $("#selectTimes").on("click",function(){
    	  alert("as")
    	var timeId = $("#collectTimeId").val();
    	var selectDate = $("#picktime").val();
    	if(typeof(selectDate) == "undefined"){
    		var postData = {timeId:timeId};
    	}else{
    		var postData = {timeId:timeId,selectDate:selectDate};
    	}
    	//alert(selectDate);
    	 $(".cur").remove();
    	  $.ajax({
    			url:"<%=request.getContextPath()%>/collect/queryTimeByDate",
    			data:postData,
    			type:"POST",
    			dataType : "json",
    			success:function(data){
    				var timeScheduleList = data.data;
    				var timeSchedule;
    				var appendHtml ="";
    				for(var i = 0;i<timeScheduleList.length;i++){
    					timeSchedule = timeScheduleList[i];	
    					appendHtml = appendHtml+"<li class='cur'>"+
    					"<div class='zw-data-icon'></div>"+
    					"<div class='zw-data-fw' id='"+timeSchedule.section+"'>"+timeSchedule.timeDesc+"</div>"+
    					"</li>";
    				}// ~ end for
    				$(".data-list").append(appendHtml);
    			},
    			error:function(){
    			}
    		});//ajax end
        $("#backdrop").removeClass("displaynone");
        $("#zw-order-date").removeClass("bottom");
      });
      $(".zw-order-date-close").on("click",function(){
        $("#zw-order-date").addClass("bottom");
        $("#backdrop").addClass("displaynone");
      })
      $("#sTime").on("click",function(){
        $("#backdrop").removeClass("displaynone");
        $("#zw-order-time").removeClass("bottom")
      })
      $(".zw-order-time-close").on("click",function(){
        $("#zw-order-time").addClass("bottom");
        $("#backdrop").addClass("displaynone")
      })
     
      $(".data-list").on("click",".cur",function(){
        var that=this;
        var timeDesc =$(that).children(".zw-data-fw").text();
       // $("#selectTimes").hide(); 
       // $('[name="schedule_id"]').attr("type","text");
        $('[name="schedule_id"]').val(timeDesc);
        $('#collectTimeSection').val($(that).children(".zw-data-fw").attr("id"));
        $("#zw-order-date").addClass("bottom");
        $("#backdrop").addClass("displaynone")
      })
      
      $("#picktime").change(function(){
        var selecDate = $(this).val();
      })
    })
</script>

<script>
	$(function(){
		$(".numMinus").on("click",function(){
			var numminus = $("input[name='quantity']");
			var VIalue = parseInt(numminus.val());
			if(numminus.val()>1){
			 numminus.val(VIalue - 1);
				$(".payfor").html('￥' + formatCurrency($("[name='ticket_id']").val() * numminus.val()));	
			}
		})
		$(".numPlus").on("click",function(){
			var numplus = $("input[name='quantity']");
			var VIalue1 = parseInt(numplus.val());
			numplus.val(VIalue1+1);
			if(numplus.val() < 50000){
				numplus.val(VIalue1 + 1);
				$(".payfor").html('￥' + formatCurrency($("[name='ticket_id']").val() * numplus.val()));
			}
		})
		
		function formatCurrency(num) {
			num = num.toString().replace(/\$|\,/g, '');
			if (isNaN(num))
				num = "0";
			sign = (num == (num = Math.abs(num)));
			num = Math.floor(num * 100 + 0.50000000001);
			cents = num % 100;
			num = Math.floor(num / 100).toString();
			if (cents < 10)
				cents = "0" + cents;
			for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
				num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
			return (((sign) ? '' : '-') + num + '.' + cents);
		}
	})
</script>
</body>
</html>
<script>
$(".zw-enjoy-msg-submit").click(function() {
	var params = $("#order-form").serialize();
	console.log("params:" + params);
	$.ajax({
		type:"POST",
		dataType : "json",
		data:params,
		url:"<%=request.getContextPath()%>/collect/bookproduct",
        success:function(data){
        	if(data.errorCode==0){
 			   //跳转至支付页面
 			   console.log("预定产品成功,返回的json数据"+data.datas.outTradeNo);
 			   window.location.href="<%=request.getContextPath()%>/collect/topay/"+data.datas.outTradeNo;
 			   return false;
 		   }else{
 			   alert("预定失败");
 		   }
		}
		});
	
});
</script>