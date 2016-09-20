var indexLastScroll = 0;

$(function() {
    FastClick.attach(document.body);
});

function _json(url,data, succ, error){
    $.ajax({
        type: "get",
        data:data,
        url: url,//这里不能跨域，所以需要在m上做rest的跳转
        dataType: "json",
        beforeSend: function(xhr){
            $("#loading").show();
        },
        success: function (data) {
            $("#loading").hide();
            succ(data);
        },
        error: function (e) {
            $("#loading").hide();
            error(e);
        }
    });
}
function _json_post(url,data, succ, error){
    $.ajax({
        type: "post",
        data:data,
        url: url,//这里不能跨域，所以需要在m上做rest的跳转
        dataType: "json",
        beforeSend: function(xhr){
            $("#loading").show();
        },
        success: function (data) {
            $("#loading").hide();
            succ(data);
        },
        error: function (e) {
            $("#loading").hide();
            error(e);
        }
    });
}

function _load(id,method,url,data,callback){
    $.ajax({
        type: method,
        data:data,
        url: url,//这里不能跨域，所以需要在m上做rest的跳转
        dataType: "html",
        beforeSend: function(xhr){
            $("#loading").show();
        },
        success: function (data) {
            $("#loading").hide();
            $(id).html(data);

            if(callback){
                callback(true);
            }
        },
        error: function (e) {
            $("#loading").hide();
            $(id).html(e);

            if(callback){
                callback(false);
            }
        }
    });
}

function _ajaxPageMore(containerID,url){
    $("#" + containerID + " .list_pager").attr("class", "list_pager list_pager_loading");

    $.ajax({
        type: "get",
        url: url,//这里不能跨域，所以需要在m上做rest的跳转
        dataType: "html",
        beforeSend: function(xhr){
        },
        success: function (d) {
            $("#" + containerID + " .list_pager").remove();
            $("#" + containerID).append(d);
            setTimeout(function() {$(window).lazyLoadXT();}, 50);
        },
        error: function (e) {
            $("#" + containerID + " .list_pager").attr("class", "list_pager list_pager_fail");
        }
    });
}


function submitOrder(){
    var mobile = $("#mobile").val();
    if(mobile == ""){
        $("#error_message").html("手机号码不能为空").show();
        return;
    }
    var count = $("#goodsCount").html();
    var goodsID = $("#goodsID").val();
    var couponID = $("#couponID").val();

    _json("/mob/order/create", {"buyerPhone":mobile, "goodsID":goodsID, "goodsCount": count, "couponID":couponID},function(d){
        if(d.code == 0){
            location.href="/mob/order/confirm?showwxpaytitle=1&orderID=" + d.data.order.id;
        }else{
            $("#error_message").html(d.desc).show();
        }
    }, function(e){
        $("#error_message").html("连接服务器错误,请重试").show();		
    });
}

function cancelOrder(id){
    _json("/mob/order/cancel", {"orderID":id},function(d){
        if(d.code == 0){
            _load("#pageContainer", "get", "/mob/order/view", {"orderID":id});
        }else{
            $("#error_message").html(d.desc).show();
        }
    }, function(e){
        $("#error_message").html("连接服务器错误,请重试").show();		
    });
}

/**
 * fromOrderView已经失效了，所有订单全部从支付页进入了
 * */
function pay(orderID,fromOrderView){
    //	if(orderID=="201603021503127703"){
    //		payWithQRCode(orderID);
    //		return;
    //	}
    $("#loading").show();
    _json("/mob/order/wxPrepay", {"orderID": orderID},function(d){
        $("#loading").hide();


        if(d.code == 0){
            wx.chooseWXPay({
                timestamp:d.data.timeStamp,
                nonceStr:d.data.nonceStr,
                package:d.data.package_,
                signType:d.data.signType,
                paySign:d.data.paySign,
                success: function (res) {
                    $("#confirmView").hide();
                    $("#successView").show();
                },
                fail:function(e){
                    $("#error_message").html(JSON.stringify(e)).show();
                    payWithQRCode(orderID);
                }
            });
        }else
        {
            $("#error_message").html(d.desc).show();
        }
    }, function(e){
        $("#loading").hide();
        $("#error_message").html("连接服务器错误,请重试").show();
        payWithQRCode(orderID);
    });
}

function payWithQRCode(orderID){
    $.modal({
        title:"长按二维码 -&gt; 识别二维码 -&gt; 完成支付",
        text: "<img alt='正在加载支付二维码' width='180' height='180' src='http://m.imeetin.com/mob/wxPayCode2?orderID=" + orderID + "' />",
        buttons: [{ text: "暂不支付",className: "default", onClick: function(){
            $.closeModal();
        }},{ text: "我已完成支付", onClick: function(){
            location.reload();
        }}
                 ]
    });
}

function smsTimer(){
    var tgt = $("#smsCode");
    if(tgt.length>0 && tgt.hasClass("disabled")){
        var v = parseInt(tgt.attr("timer"));
        v--;
        if(v==0)
        {
            tgt.removeClass("disabled");
            tgt.html("发送验证码");
        }else
        {
            tgt.attr("timer",v);
            tgt.html("正在发送(" + v + "s)");
            setTimeout(smsTimer,1000);
        }
    }
}

function smsCode(){
    var tgt = $("#smsCode");
    if(!tgt.hasClass("disabled"))
    {
        tgt.addClass("disabled");

        var mobile = $("#mobile").val();
        if(mobile == ""){
            tgt.removeClass("disabled");
            $("#error_message").html("手机号码不能为空").show();
            return;
        }
        $("#smsCode").attr("timer",61);
        _json_post("/rest/user/sendRegSms",{"mobile":mobile},function(d){
            if(d.code==0){
                smsTimer();
            }else
            {
                tgt.removeClass("disabled");
                $("#error_message").html(d.desc).show();
            }
        },function(e){
            tgt.removeClass("disabled");
            $("#error_message").html("发送验证码错误,请重试").show();
        });
    }
}

function checkCode(url)
{
    var mobile = $("#mobile").val();
    var code = $("#code").val();
    var inviterCode = $("#inviterCode").val();

    if(mobile == "" || mobile == undefined){
        $("#error_message").html("手机号码不能为空").show();
        return;
    }

    if(code == "" || code == undefined)
    {
        $("#error_message").html("验证码不能为空").show();
        return;
    }

    if(!inviterCode || inviterCode == undefined)
    {
        inviterCode = "";
    }

    _json_post("/mob/bindMobile",{"mobile":mobile,"code":code, "inviterCode": inviterCode}, function(d){
        if(d.code == 0){
            if(typeof(url) == "string")
            {
                location.href=url;
            }else
            {
                url();
            }
        }else
        {
            $("#error_message").html(d.desc).show();
        }
    },function(e){
        $("#error_message").html("验证码校验错误,请重试").show();
    });
}

function consumePayCode(id){
    _json_post("/mob/order/consumePayCode",{"payCode":id}, function(d){
        if(d.code == 0){
            location.reload();
        }else{
            $("#error_message").html(d.desc).show();
        }
    },function(e){
        $("#error_message").html("连接服务器错误,请重试").show();
    });
}

function showShare(text, blockUI)
{
    if(!text || text == "" || text==undefined){
        text = "";
    }

    if(blockUI == "" || blockUI==undefined){
        blockUI = false;
    }

    var obj = $("<div id='shareTipsDialog' style='position:fixed; width:100%; height:100%; top:0px; left:0px; z-index:999999; padding-top:80px; background:url(http://cdn.imeetin.com/images/weixin_share.png) right top no-repeat; background-color: #000; background-size:164px 108px; opacity:0.85;'><p style='color:#fff; font-size:18px; text-align:center;'>" + text + "</p></div>").appendTo($("body"));
    if(!blockUI){
        obj.click(function(){
            $(this).remove();
        });		
    }
}

function goTo(url,title)
{
    if(title && title !=undefined && title != "")
    {
        nativeGotoURL(title, url);
    }else
    {
        location.href=url;
    }
}

function toast(msg)
{
    $("#toast").text(msg);
    var marginLeft = ($(window).width()-200)/2;
    $("#toast").attr("style","left:"+marginLeft+"px");
    $("#toast").show();	
    $("#toast").fadeOut(3000);
}

function logAndOpenURL(url,logEvent)
{
    $.ajax({
        type: "get",
        url: "/STAT_CLICK_EVENT?event=" + logEvent,
        dataType: "html"
    });
    location.href=url;
}