$(function(){
	var getParam = function(name){
	        var search = document.location.search;
	        var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
	        var matcher = pattern.exec(search);
	        var items = null;
	        if(null != matcher){
	                try{
	                        items = decodeURIComponent(decodeURIComponent(matcher[1]));
	                }catch(e){
	                        try{
	                                items = decodeURIComponent(matcher[1]);
	                        }catch(e){
	                                items = matcher[1];
	                        }
	                }
	        }
	        return items;
	};
	var goodsId=getParam('goodsId');
	var goodsName=getParam('goodsName');
	var goodsPrice=getParam('goodsPrice');
	var goodsImg=getParam('goodsImg');
	var positions=getParam('positions');
	var attr_value=getParam('attr_value');
	var symbol=getParam('symbol');
	document.getElementById("attr_value").innerHTML=attr_value;
	document.getElementById("goodsName").innerHTML=goodsName;
	document.getElementById("positions").innerHTML=positions;
	document.getElementById("goodsPrice").innerHTML="￥"+goodsPrice;
	document.getElementById("goodsImg").src=goodsImg;
})
function confirmOrder(){
	var params = $("#form").serialize();
	console.log("params="+params);
	//console.log("params:" + params);
	$.ajax({
		type:"POST",
		dataType : "json",
		data:params,
		url:"/weOnes/goods/confirmOrder",
        success:function(data){
        	/*if(data.errorCode==0){
 			   //跳转至支付页面
 			   console.log("预定产品成功,返回的json数据"+data.datas.outTradeNo);
 			   window.location.href="<%=request.getContextPath()%>/collect/topay/"+data.datas.outTradeNo;
 			   return false;
 		   }else{
 			   alert("预定失败");
 		   }*/
		}
	});
}