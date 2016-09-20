//JIAOYAN
var data = {};
//分类选择tab
var categoryId = $("#categorySelect").children('option:selected').val();//提取父类别数据
var categoryChildId = $("#categoryChildSelect").children('option:selected').val();//提取子类别数据
var name = $("input[name='name']").val();//名称
if(categoryId==300){
	var title ='';//设备
	$("input[name='title']:checked").each(function(i){
		if(0==i){
			title = $(this).val();
		}else{
			title += (","+$(this).val());
		}
	});
	//判断勾选的计价方式
	var hourAndDayCheck ='';//计价方式
	var goodsAttrs ={};
	goodsAttrs.attrKey="7";
	goodsAttrs.attrVal={};

	$("input[name='hourAndDayCheck']:checked").each(function(i){
//		var attrVals =[];
		hourAndDayCheck = $(this).val();
		attrVals.symbol = i+1;
		attrVals.attrVal = hourAndDayCheck;
		attrVals.price = $("input[name='"+hourAndDayCheck+"Price']").val();
//		goodsAttrs.attrVals = attrVals;
	});
	goodsAttrs.attrVals = attrVals;
	data.goodsAttrs = goodsAttrs;