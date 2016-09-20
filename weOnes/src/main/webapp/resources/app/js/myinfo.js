$(function(){//获取后台数据
		//alert("aaa");
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"/weOnes/myController/myinfo",
            success:function(data){
            	if(data.errorCode == "0"){
            		var data = data.datas;
            		//设置列表数据
            		$("#myinfo_headImgUrl").attr('src',data.headImgUrl);//头像
            		$("#myinfo_nickname").val(data.nickName);//昵称
            		var provinceObj = $("#myinfo_province");
            		data.province.length==0?provinceObj.prepend("<option value='0' selected='selected'>请选择省份</option>"):provinceObj.prepend("<option value='"+data.province+"' selected='selected'>"+data.province+"</option>");//省份
            		var cityObj = $("#myinfo_city");
            		data.city.length==0?cityObj.prepend("<option value='0' selected='selected'>请选择城市</option>"):cityObj.prepend("<option value='"+data.city+"' selected='selected'>"+data.city+"</option>");//城市
            		$("#myinfo_sex").val(data.sex);//性别
            		$("#myinfo_phone").val(typeof(data.phone)=="undefined"?"":data.phone);//手机号
            		$("#myinfo_email").val(typeof(data.email)=="undefined"?"":data.email);//邮箱
            		alert(typeof data.birthday);
            		$("#myinfo_birthday").attr('value',typeof(data.birthday)=="undefined"?"":data.birthday);// 生日
            		var createTime =  new Date(data.createTime);
            		var timeStr = createTime.getFullYear()+'-'+(createTime.getMonth()+1)+'-'+createTime.getDate();
            		$("#myinfo_createtime").val(timeStr);//关注时间
            	}else{
            		alert("获取数据失败");
            	}
            },
            error:function(data, status, e){
            	alert("获取数据失败");
            }
		});//ajax end;
		
		//监听省数据
		$("#myinfo_province").on("click",function(){
			//如果用户点击省份select框,则输出所有的省份数据
			var provinceObj = $("#myinfo_province");
			provinceObj.empty()//清空数据
			provinceObj.append("<option value='请选择省份'>请选择省份</option>");
			provinceObj.append("<option value='北京市'>北京市</option>");
			provinceObj.append("<option value='天津市'>天津市</option>");
			provinceObj.append("<option value='河北省'>河北省</option>");
			provinceObj.append("<option value='山西省'>山西省</option>");
			provinceObj.append("<option value='内蒙古自治区'>内蒙古自治区</option>");
			provinceObj.append("<option value='辽宁省'>辽宁省</option>");
			provinceObj.append("<option value='吉林省'>吉林省</option>");
			provinceObj.append("<option value='黑龙江省'>黑龙江省</option>");
			provinceObj.append("<option value='上海市'>上海市</option>");
			provinceObj.append("<option value='江苏省'>江苏省</option>");
			provinceObj.append("<option value='浙江省'>浙江省</option>");
			provinceObj.append("<option value='安徽省'>安徽省</option>");
			provinceObj.append("<option value='福建省'>福建省</option>");
			provinceObj.append("<option value='江西省'>江西省</option>");
			provinceObj.append("<option value='山东省'>山东省</option>");
			provinceObj.append("<option value='河南省'>河南省</option>");
			provinceObj.append("<option value='湖北省'>湖北省</option>");
			provinceObj.append("<option value='湖南省'>湖南省</option>");
			provinceObj.append("<option value='广东省'>广东省</option>");
			provinceObj.append("<option value='广西壮族自治区'>广西壮族自治区</option>");
			provinceObj.append("<option value='海南省'>海南省</option>");
			provinceObj.append("<option value='重庆市'>重庆市</option>");
			provinceObj.append("<option value='四川省'>四川省</option>");
			provinceObj.append("<option value='贵州省'>贵州省</option>");
			provinceObj.append("<option value='云南省'>云南省</option>");
			provinceObj.append("<option value='西藏自治区'>西藏自治区</option>");
			provinceObj.append("<option value='陕西省'>陕西省</option>");
			provinceObj.append("<option value='甘肃省'>甘肃省</option>");
			provinceObj.append("<option value='青海省'>青海省</option>");
			provinceObj.append("<option value='宁夏回族自治区'>宁夏回族自治区</option>");
			provinceObj.append("<option value='新疆维吾尔自治区'>新疆维吾尔自治区</option>");
			provinceObj.append("<option value='香港特别行政区'>香港特别行政区</option>");
			provinceObj.append("<option value='澳门特别行政区'>澳门特别行政区</option>");
			provinceObj.append("<option value='台湾省'>台湾省</option>");
			var option = provinceObj.find("option:selected").text();
			//alert(option);
			//var option = provinceObj.val();//获取值
			//var value=$('#myinfo_province option:selected').val();//获取Select选中项的Value
			//alert(value);
		});//省份数据
		
		//监听市数据
		
	});

