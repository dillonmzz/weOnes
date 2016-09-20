<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
        <meta name="description" content="玩天下 | 精选 · 美食 · 玩法" />
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">
        <meta name="robots" content="noodp,noydir">
        <title>玩天下</title>
        <script src="/weOnes/js/jquery-2.2.0.min.js"></script>
        <link href="/weOnes/css/common.css"  rel="stylesheet" type="text/css" />
        <link href="/weOnes/css/style.css" rel="stylesheet" type="text/css" />
    </head>
<body>
    <div id="page">
        <header class="no-green">
            <a href="/order.html" class="head-button head-button-left icon-back back-green"></a>
            <h1 class="title">付款</h1>
        </header>
        <div class="wrap">
                <form  method="post" id="cfmbuy">
                    <div class="buy_list confirmorder_list">
                        <ul>
                            <li>
                                <span class="tit">活动编号：</span>
                                <input class="txt" name="activityId" value="${activity.activityId}">
                            </li>
                            <li>
                                <span class="tit">活动名称：</span>
                                <input class="txt" name="title" value="${activity.title}">
                            </li>
                            <li>
                                <span class="tit">出发日期：</span>
                                <input class="txt" name="startTime" value="${activity.startTime}">
                            </li>
                            <li>
                                <span class="tit">单　　价：</span>
                                <input class="txt" id="danjia" name="newPrice" value="${activity.newPrice}">
                            </li>
                            <li>
                                <span class="tit">数　　量：</span>
                                <div class="txt txt_circle">
                                    <input type="button" style="width: 32px;margin-top:6px;" value="-" class="btn inp"/>
                                    <input type="text" id="shuliang" name="shuliang" value="1" class="inp nomargin" readonly="readonly"/>
                                    <input type="button" value="+" class="btn inp" style="width: 32px;margin-top:6px;" />
                                </div>
                            </li>
                            <div style="height:10px;"></div>
                        </ul>
                    </div>
                    <div class="buy_list confirmorder_list2">
                        <ul>
                            <li><h2>联系人</h2></li>
                            <li>
                                <input name="userName" id="ct1_name" class="inp inp1" placeholder="您的姓名" data_valmsg_for="#ct1_name_info" value="" data_val="" data_val_msg="" type="text" />
                            </li>
                            <li>
                            <input name="userPhone" id="ct1_phone" class="inp inp1" placeholder="请填写联系电话" data_valmsg_for="#ct1_phone_info" value="" data_val="" data_val_msg="" type="text" />
                            </li>
                            <li>
                                <input name="userAddr" id="ct1_email" class="inp inp1" placeholder="请填写邮箱(选填)" data_valmsg_for="#ct1_email_info" data_val="" data_val_msg="" type="text" />
                            </li>
                            <li>
                                <textarea name="remarks" id="remarks" class="form-control textarea" placeholder="备注（选填）" calss="inp inp_textarea" ></textarea>
                            </li>
                        </ul>
                        <ul>
                            <li>
                                <span class="tit">优惠券：</span>    
                                <div class="txt coupon_log_id"> 
                                    <select id="coupon_log_id" name="coupon_log_id">
                                        <option value="0">暂无可使用优惠券</option>
                                    </select>
                                </div>
                            </li>
                            <li>
                                <h2>支付方式：</h2>
                                <div class="txt">
                                    <label>
                                        <input type="radio" class="inputval" name="way" data-val="微信支付" value="wxpay" checked>微信支付
                                    </label>
                                    <label>
                                        <input name="way" disabled="disabled" class="inputval" value="below" data-val="查看付款方式" type="radio">线下支付
                                    </label>
                                    <div class="clear"></div>
                                </div>
                            </li>
                        </ul>
                        <dd>请尽快完成支付，如未及时支付，我们将不能为您保留位置</dd>
                    </div>
                <div class="global_con-total global_con-total-union global_con-total-mb">
                    <span id="price" class="price_union">总金额:    
                       <span class="red need_topay" id="need_topay">${activity.newPrice}</span> 元
                    </span>
                    <span class="buy-button">
                        <!--  <a href="" onclick="$('#payform').trigger('submit');return false;" class="uporder">去微信支付 -->
                        <a href="javascript:callPay();" class="uporder">去微信支付
                        </a>
                    </span>
                    <a href="tel:" class="tel">
                        <img src="/weOnes/images/icon-teln-1-1.png" height="32">
                    </a>
                </div>
            </form>
        </div>
        <script>
            $(function(){     
                $(".inputval").on("click",function(){
                    var _that = $(this);   
                    $(".buy-button").find("a").html(_that.attr("data-val"));
                    });
                    $("#coupon_log_id").on("change",function(){
                        var box = $(this).children('option:selected');
                        var coupon = box.attr('rel');
                        var need_topay=8900.00-coupon;
                        $("#price span.red").html("￥"+need_topay.toFixed(2));
                    });

                }) 
                if($("#coupon_log_id option").val()=="0"){
                    $("#coupon_log_id").attr("disabled","disabled");

                }
        </script>
        <script>
            /*加减数量处理*/
          $(function () {
                $(".btn").on("click", function () {
                    var _that = $(this);
                    if (_that.attr("value") == '-') {
                        if ((parseInt($("#shuliang").val()) - 1) > 0){
                            $("#shuliang").val(parseInt($("#shuliang").val()) - 1);
                            $(".need_topay").html('￥' + formatCurrency($("#danjia").attr("value") * parseInt($("#shuliang").val())));
                        }
                    }
                    if (_that.attr("value") == '+') {
                        if ((parseInt($("#shuliang").val()) + 1) <= 99) {
                            $("#shuliang").val(parseInt($("#shuliang").val()) + 1);
                            $(".need_topay").html('￥' + formatCurrency($("#danjia").attr("value") * parseInt($("#shuliang").val())));
                        }
                    }
                });
            });
            /*数字值四舍五入(保留2位小数)后格式化成金额形式
                * @param num 数值(Number或者String)
                * @return 金额格式的字符串,如'1,234,567.45'
                */
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
        </script>
        <div class="clear"></div>
    </div>
</body>
</html>

	<!--<span>金额:0.01元</span>
	<br>
	 <a href="javascript:callPay();" id="btnOrder">点击支付</a>${resData.appid} -->
	<script type="text/javascript" >
		function onBridgeReady(data) {
			
			var appId = data.appid.toString();
			var timeStamp = data.timeStamp.toString();
			var nonceStr = data.nonce_str.toString();
			var prepay_id = "prepay_id="+data.prepay_id.toString();
			var signType = "MD5".toString();
			var paySign = data.sign.toString();
			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				"appId" : appId,//"wx2421b1c4370ec43b", //公众号名称，由商户传入   
				"timeStamp" : timeStamp,//"1395712654", //时间戳，自1970年以来的秒数  
				"nonceStr" : nonceStr,// 随机串   
				"package" : prepay_id,//"prepay_id=u802345jgfjsdfgsdg888",
				"signType" : signType,//微信签名方式  "MD5"
				"paySign" : paySign,//微信签名 
			}, function(res) {
				if (res.err_msg == "get_brand_wcpay_request:ok") {
					alert("支付成功o(∩_∩)o");
					//支付成功后，用户点击完成按钮后，会触发后台业务逻辑代码
					
				}
				if (res.err_msg == "get_brand_wcpay_request:cancel") {
					alert("交易取消");
					//若用户没有输入密码，取消支付，会出发后台业务逻辑代码
				}
				if (res.err_msg == "get_brand_wcpay_request:fail") {
					alert("支付失败");
				}
			});
		}

		
		
		function callPay() {
			//ajax请求，参数是商品ID,购买数量
			//alert("购买数量"+$("#shuliang").val());
			var params = $("#cfmbuy").serialize();
			//alert(params);
			$.ajax({
    			type:"POST",
    			dataType : "json",
    			url:"/weOnes/wxpay/orderBeforProcess",
                data:params,
                success:function(data){
                	//var jsonObj = $.parseJSON(data);//此方法是将json字符串转换为js对象,若后端返回的是JSON对象,则不需要再转换成对象
                	 if (data.return_msg == "OK") {
                		 //alert("wxpay data1:"+data.return_msg);
                           if (typeof WeixinJSBridge == "undefined") {
               				if (document.addEventListener) {
               					document.addEventListener('WeixinJSBridgeReady',
               							onBridgeReady, false);
               				} else if (document.attachEvent) {
               					document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
               					document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
               				}
               			} else {
               				//alert("wxpay data2:"+data.sign);
               				onBridgeReady(data);
               			}
                        } else {
                           alert("下单失败");
                        }
    			}
    		});
			
		}
	</script>