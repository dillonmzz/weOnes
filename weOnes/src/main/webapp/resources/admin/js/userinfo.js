$(function(){
	/**
	 * 商家头像上传
	 */
	//$("#uploadfile").on('change', function() {//为uploadfile input框设置绑定change事件
	  $(".uploadimgurl").on('change',"#uploadfile", function() {
		alert("aaa");
		var imgurlObj = $("#uploadfile");//获取img file对象
		var imgfile = imgurlObj.get(0).files[0];//
		var src;//window.URL.createObjectURL(imgfile);//获取img file的src属性值
		if (window.createObjectURL!=undefined) { // basic
			src = window.createObjectURL(imgfile) ;
		} else if (window.URL!=undefined) { //mozilla(firefox)
			src = window.URL.createObjectURL(imgfile) ;
		} else if (window.webkitURL!=undefined) { //webkit or chrome
			src = window.webkitURL.createObjectURL(imgfile) ;
		}
		//alert($("#uploadfile").val().substr(val.indexOf(".")));
		$.ajaxFileUpload({
			url : 'fileUpload/up',
			secureuri : false, // 一般设置为false
			fileElementId : 'uploadfile', // 文件上传id属性 <input type="file" id="uploadfile">
			type:'POST',
			dataType : 'txt', // 返回值类型txt
			success : function(data,status) // 服务器成功响应处理函数
			{
				//ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
				data = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
				data = data.replace('</pre><audio controls="controls" style="display: none;"></audio>', '');
				data = data.replace('</pre>','');
				var datas = $.parseJSON(data);
				 if (datas.errorCode == "0") {
		            console.log("上传成功,文件存放路径:"+datas.msg);
		            $(".showimg").attr('src',datas.msg);
				 }else{
					 alert("上传失败:"+datas.msg);
				 }
			},
			 error: function (data, status, e)
	         {
	             alert("上传失败服务器异常:"+data.msg);
	         }
		});
	});//上传结束
	
	/**
	 * 保存用户信息
	 */
	$("#submitId").click(function(){
	    var formdata = {id:$(".userId").val(),imgurl:$(".showimg").attr("src"),nickName:$(".nickName").val(),mobile:$(".mobile").val(),
	    				email:$(".email").val(),introduction:$(".introduction").val(),province:$(".province").find("option:selected").text(),
	    				city:$(".city").find("option:selected").text(),address:$(".address").val()};
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"/weOnes/userinfo/save",
		    data:formdata,
		    success:function(data){
				console.log("服务器相应成功data\r\n"+data);
		    	 if (data.errorCode == "0") {
		    		 $("#submitId").attr('disabled',true);
		    		 $("#submitId").unbind();
		               layer.msg("保存成功",{icon: 1});
		            } else {
		            	 layer.msg(data.msg,{icon: 2});
		            }
			},
			error: function() {
				layer.msg("系统出错了",{icon: 2});
			}
		});
	});//结束
});
