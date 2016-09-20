// city
$(function() {
	var provinceHtml = '';
	$.each(Cities.child, function(n, value) {
		var selected = '';
		if (value.name == $("#provinceSec").val()) {
			selected = 'selected';
		}
		var option = '<option ' + selected + ' value="' + value.id + '">'
				+ value.name + '</option>';
		provinceHtml += option;
	});
	$("#province").html(provinceHtml);
	setTimeout(function() {
		$("#province").trigger('change');
	}, 0);

	$("#province").change(function() {
		var id = $(this).val();
		var cityHtml = '';
		$.each(Cities.child, function(n, value) {//省
			//console.log("n="+n+" v="+value.id);
			if (id == value.id) {
				$.each(value.data.child, function(i, v) {//市
					var selected = '';
					if (v.name == $("#citySec").val()) {
						selected = 'selected';
					}
					var option = '<option ' + selected + ' value="' + v.id + '">' + v.name
							+ '</option>';
					cityHtml += option;
				});
			}
		});
		$('#city').html(cityHtml);
		setTimeout(function() {
			$("#city").trigger('change');
		}, 0);
	});
	
	
	
	$("#city").change(function() {
		var id = $(this).val();
		//console.log("当前选中城市ID"+id);
		var areaHtml = '';
		$.each(Cities.child, function(n, value) {
			//console.log("1、n="+n+" v="+value.name);
				$.each(value.data.child, function(i, v) {
					if (id == v.id) {//当前选择的市与当前循环的市ID相等的话
						//console.log("2、i="+i+" v="+v.name);
						$.each(v.data.child,function(key,va){//遍历出当前市下面的所有区
							var selected = '';
							if (v.name == $("#citySec").val()) {
								selected = 'selected';
							}
							var option = '<option ' + selected + '>' + va.name
									+ '</option>';
							areaHtml += option;
						});
					}
				});
			
		});
		$('#area').html(areaHtml);
	});
	
});