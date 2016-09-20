$(function(){
	$(".detail .header .place-banner .place-summary .summary-item span.package").on("click",function(){
		$(".detail .header .place-banner .place-summary .summary-item span.package").eq($(this).index()).addClass("charset").siblings().removeClass('charset');
		var index=$(this).index();
		$(".tab_box > div").eq(index).show().siblings().hide();
		$(".tab_price > div").eq(index).show().siblings().hide();
	})
})