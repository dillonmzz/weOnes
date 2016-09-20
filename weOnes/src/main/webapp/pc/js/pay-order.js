$(function(){
	$(".numMinus").on("click",function(){
		var numminus = $("input[name='num']");
		var VIalue = parseInt(numminus.val());
		if(numminus.val()>1){
			numminus.val(VIalue - 1);
			$(".payfor").html('￥' + formatCurrency($("[name='ticket_id']").val() * numminus.val()));
		}
	});
	$(".numPlus").on("click",function(){
		var numminus1 = $("input[name='num']");
		var VIalue1 = parseInt(numminus1.val());
		if(numminus1.val() < 50000){
			numminus1.val(VIalue1 + 1);
			$(".payfor").html('￥' + formatCurrency($("[name='ticket_id']").val() * numminus1.val()));
		}
	});

	function formatCurrency(num){
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
