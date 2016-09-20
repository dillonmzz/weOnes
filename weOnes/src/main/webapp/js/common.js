function ajaxFileUploadPic(params) {
	$.ajaxFileUpload({
		url : 'fileUpload/upload',
		secureuri : false, // 一般设置为false
		fileElementId : 'fid1739669697', // 文件上传id属性 <input type="file" id="fid1739669697">
		type:'POST',
		data:params,
		dataType : 'txt', // 返回值类型txt
		success : function(data,status) // 服务器成功响应处理函数
		{
			//ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			data = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
			data = data.replace('</pre><audio controls="controls" style="display: none;"></audio>', '');
			data = data.replace('</pre>','');
			console.log("照片上传成功,返回json"+data);
			var datas = $.parseJSON(data);
			 if (datas.errorCode == "0") {
	             //$.tooltip('OK, 操作成功', 4000, true);
	            alert("上传图片成功&响应成功,进行数据保存......");
	            console.log("上传成功&响应成功,文件存放路径:"+datas.msg);
	            var thumbnails = encodeURI(datas.msg);
	    		//图片上传结束
	    		return;
	    		//获取编辑器HTML
	    		//var allHtml = ue.getAllHtml();
	    		//var detailsHtml = encodeURI(allHtml);
	    		//获取编辑器Content
	    		var allContent = ue.getContent();
	    		var detailsContent = encodeURI(allContent);
	    		//params = params+"&detailsHtml="+detailsHtml+"&detailsContent="+detailsContent+"&thumbnails="+thumbnails;
	    		params = params+"&detailsContent="+detailsContent+"&thumbnails="+thumbnails;
	    		console.log("完整参数:"+params);
	    		$.ajax({
	    			type:"POST",
	    			dataType : "json",
	    			url:"activity/saveActivity",
	                data:params,
	                success:function(data){
	    				console.log("传回成功JSON数据"+data);
	                	//var jsonObj = $.parseJSON(data);//此方法是将json字符串转换为js对象,若后端返回的是JSON对象,则不需要再转换成对象
	                	console.log("请求成功,返回的json:"+data.errorCode);
	                	 if (data.errorCode == "0") {
	                           alert("save ok");
	                        } else {
	                        	 alert(data.msg);
	                        }
	    			}
	    		});
	         } else {
	             alert(datas.msg);
	         }
		},
		 error: function (data, status, e)
         {
             alert("上传失败服务器异常:"+data.msg);
         }
	});
}

function ajaxFileUploadPicCut(picData) {
	alert($('#fid1739669697_input').val());
	$.ajaxFileUpload({
		url : 'fileUpload/upload',
		secureuri : false, // 一般设置为false
		fileElementId : 'fid1739669697', // 文件上传id属性 <input type="file" id="fid1739669697">
		type:'POST',
		data:picData,
		dataType : 'txt', // 返回值类型txt
		success : function(data,status) // 服务器成功响应处理函数
		{
			//ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			data = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
			data = data.replace('</pre><audio controls="controls" style="display: none;"></audio>', '');
			data = data.replace('</pre>','');
			console.log("照片上传成功,返回json"+data);
			var datas = $.parseJSON(data);
			 if (datas.errorCode == "0") {
	             //$.tooltip('OK, 操作成功', 4000, true);
	            //alert("上传图片成功");
	            console.log("上传成功&响应成功,文件存放路径:"+datas.msg);
	           //var thumbnails = encodeURI(datas.msg);
	    		//图片上传结束
	          //设置file value 
	            alert(datas.msg);
		        $('#thumbnailsPath').val(encodeURI(datas.msg));
		        alert($('#thumbnailsPath').val());
	    		return;
	         } else {
	        	 alert("上传图片失败");
	         }
		},
		 error: function (data, status, e)
         {
             alert("上传失败服务器异常:"+data.msg);
         }
	});
}


//上传原图 并在后台进行裁剪
function ajaxFileUploadPicCut1(picData) {
	$.ajaxFileUpload({
		url : 'fileUpload/upload',
		secureuri : false, // 一般设置为false
		fileElementId : 'fid1739669697', // 文件上传id属性 <input type="file" id="fid1739669697">
		type:'POST',
		data:picData,
		dataType : 'txt', // 返回值类型txt
		success : function(data,status) // 服务器成功响应处理函数
		{
			console.log("照片上传成功,返回json"+data);
			//ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			/*data = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
			data = data.replace('</pre><audio controls="controls" style="display: none;"></audio>', '');
			data = data.replace('</pre>','');
			var datas = $.parseJSON(data);
			 if (data.errorCode == "0") {
	            	   console.log("上传成功&响应成功,文件存放路径:"+datas.msg);
	                   alert(data.msg);
		           $('#thumbnailsPath').val(encodeURI(data.msg));
		           alert($('#thumbnailsPath').val());
	    		   return;
	        	 } else {
	        	    alert("上传图片失败");
	        	 }*/
			  alert("上传图片成功"+data);
		},
		 error: function (data, status, e)
         	{
             		alert("new上传失败服务器异常:"+data.msg);
        	 }
	});
}

function saveActivity(params){
//获取编辑器Content
var allContent = ue.getContent();
var detailsContent = encodeURI(allContent);
params = params+"&activity.detailsContent="+detailsContent;
$.ajax({
	type:"POST",
	dataType : "json",
	url:"activity/saveActivity",
    data:params,
    success:function(data){
		console.log("传回成功JSON数据"+data);
    	//var jsonObj = $.parseJSON(data);//此方法是将json字符串转换为js对象,若后端返回的是JSON对象,则不需要再转换成对象
    	console.log("请求成功,返回的json:"+data.errorCode);
    	 if (data.errorCode == "0") {
               alert("save ok");
               layer.msg("保存成功",{icon: 1});
            } else {
            	 alert(data.msg);
            	 layer.msg(data.msg,{icon: 2});
            }
	},
	error: function() {
		layer.msg("系统出错了",{icon: 2});
	}
});
}

function updateActivityById(params){
	$.ajax({
		type:"POST",
		dataType : "json",
		url:"activity/updateActivityById",
	    data:params,
	    success:function(data){
			console.log("传回成功JSON数据"+data);
	    	//var jsonObj = $.parseJSON(data);//此方法是将json字符串转换为js对象,若后端返回的是JSON对象,则不需要再转换成对象
	    	console.log("请求成功,返回的json:"+data.errorCode);
	    	 if (data.errorCode == "0") {
	               alert("save ok");
	               layer.msg("保存成功",{icon: 1});
	            } else {
	            	 alert(data.msg);
	            	 layer.msg(data.msg,{icon: 2});
	            }
		},
		error: function() {
			layer.msg("系统出错了",{icon: 2});
		}
	});
	}

function saveProduct(params){
	//获取编辑器Content
	//var allContent = ue.getContent();
	//var introduction = encodeURI(allContent);
	//var introduction = allContent;
	//params = params+"&introduction="+introduction;
	//console.log("introduction:"+introduction);
	$.ajax({
		type:"POST",
		dataType : "json",
		url:"product/save",
	    data:params,
	    success:function(data){
			console.log("服务器相应成功data\r\n"+data);
	    	 if (data.errorCode == "0") {
	    		 $("#submitId").attr('disabled',true);
	    		 $("#submitId").unbind();
	               layer.msg("保存成功",{icon: 1});
	            } else {
	            	 alert(data.msg);
	            	 layer.msg(data.msg,{icon: 2});
	            }
		},
		error: function() {
			layer.msg("系统出错了",{icon: 2});
		}
	});
	}