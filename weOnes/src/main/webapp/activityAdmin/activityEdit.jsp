<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<script src="./js/common.js"></script>
<script src="./js/jquery.Jcrop.js" type="text/javascript"></script>  
<link rel="stylesheet" href="./css/jquery.Jcrop.css" type="text/css" />
<legend>活动发布</legend>

<script type="text/javascript">

</script>


<form name="thisform" id="from557982744" method="post" class="form-horizontal" >
<input type="hidden" name="activity.activityId" value="">
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5">活动标题：</label>
		<div class="col-sm-6 pl-0">
			<input type="text"  value=""
				class="form-control input-text span2" 
				placeholder="活动标题" id="activityTips" name="activity.title">
		</div>
	</div> 
	
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5">副标题：</label>
		<div class="col-sm-6 pl-0">
			<input type="text"  value="${activity.subhead}"
				class="form-control input-text span2" 
				placeholder="副标题" id="activityTips" name="activity.subhead">
		</div>
	</div>
	
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5">缩略图：</label>
		<div class="col-sm-6 pl-0">
			<div class="input-group">
				<span class="input-group-addon input-file-preview"id="previewfid1739669697">预览</span>
				<input type="file" id="fid1739669697"  class="input-file" name="uploadFile">
				<input type="hidden" name="activity.thumbnails" value="" id="thumbnailsPath">
				<input id="fid1739669697_input"  placeholder="" type="text" class="form-control" preview="true">
				<span class="input-group-addon"><i class="fa fa-cloud-upload"></i>上传</span>
			</div>
			
		</div>
	</div>
<script>
 //获取图片名称到input框 中
	$(function() {
	$("#fid1739669697").on("change", function() {
		$("#fid1739669697_input").val($(this).val())
	});
	

$("#previewfid1739669697").on("click",function() {
		var $file = $("#fid1739669697");
		var allowExtention = ".jpg,.bmp,.gif,.png";// 允许上传文件的后缀名
		var fileObj = document.getElementById("fid1739669697");//获取图片对象(type="file")
		var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();//获取图片的后缀名
		var browserVersion = window.navigator.userAgent.toUpperCase();//获取浏览器版本
		//alert("extention:"+extention+"\rbrowserVersion:"+browserVersion);
		if (allowExtention.indexOf(extention) > -1) {
			if (fileObj.files) {// HTML5实现预览，兼容chrome、火狐7+等
				if (window.FileReader) {
					var reader = new FileReader();
					reader.onload = function(e) {
						//设置预览窗口图片最大宽度,高度等比例缩放
						var preViewPicMaxWidth = 500;
						
						//判断图片大小开始
						var input = document.getElementById("fid1739669697");
						if(input.files){
						  //读取图片数据
						  var f = input.files[0];
						  var reader = new FileReader();
						  reader.onload = function (e) {
						      var data = e.target.result;
						      //加载图片获取图片真实宽度和高度
						      var image = new Image();
						      image.onload=function(){
						            width = image.width;
						            height = image.height;
						         	alert("files"+width+'======'+height+"====="+f.size);
						          if(width<640 ||height <400){
						        	  alert("图片宽高不能小于640*400,请重新上传");
						        	  return;
						          }
						      };
						      image.src= data;
						  };
						  reader.readAsDataURL(f);
						  }else{
						  var image = new Image(); 
						  image.onload =function(){
						        width = image.width;
						        height = image.height;
						        fileSize = image.fileSize;
						      alert("not files"+width+'======'+height+"====="+fileSize);
						      if(width<640 ||height <400){
					        	  alert("图片宽高不能小于640*400,请重新上传");
					        	  return;
					          }
						 	 }
						  image.src = input.value;

						  }
						//判断图片大小结束
						
						
						layer.open({
									title : "修改尺寸",
									type : 1,
									content : "<div style='text-align:center'><img src='"
											+ e.target.result
											+ "'style='max-width:"+preViewPicMaxWidth+"px' id='previewImg'></div>"
											+ "<div style='width:500px;height:50px'> "
											+ "<label>X1<input type='text' size='4' id='x1' name='x1' /></label> "
											+ "<label>Y1<input type='text' size='4' id='y1' name='y1' /></label> "
											//+ "<label>X2<input type='text' size='4' id='x2' name='x2' /></label> "
											//+ "<label>Y2<input type='text' size='4' id='y2' name='y2' /></label> "
											+ "<label>W<input type='text'  size='4' id='w'  name='w' /></label> "
											+ "<label>H<input type='text'  size='4' id='h'  name='h' /></label></div> ",
									area : [ 'auto', 'auto' ],
									btn: ['保存', '取消'],
									yes: function(){
								        //alert("保存");
								        //存储到数据库中
								        var picData = {x1:$('#x1').val(),y1:$('#y1').val(),
								        			   w:$('#w').val(),h:$('#h').val(),
								        			   divWidth:preViewPicMaxWidth};
								       // alert("picData:"+picData);
								       	//ajaxFileUploadPicCut1(picData);
								       	ajaxFileUploadPicCut(picData);
								        
								    }, 
								    no: function(){
								       
								    }
								});
			   
			   var jcrop_api;
			   // Most basic attachment example
			   $('#previewImg').Jcrop({
			     //boxWidth: 400,    // 设置框的最大宽度
			     aspectRatio:1.6,// 宽/高比例
			     maxSize:[640,400],//最大尺寸
			     minSize:[400,266],//最小尺寸
			     setSelect: [ 175, 100, 400, 300 ],//
			     onChange: showCoords,
			     onSelect: showCoords
			   },function(){
				   jcrop_api = this;
			   });
			   
			   
			   function showCoords(c)
			   {
			     $('#x1').val(Math.round(c.x));
			     $('#y1').val(Math.round(c.y));
			     //$('#x2').val(Math.round(c.x2));
			     //$('#y2').val(Math.round(c.y2));
			     $('#w').val(Math.round(c.w));
			     $('#h').val(Math.round(c.h));
			   };
			   
		  /*  function clearCoords()
			   {
			     $('#coords input').val('');
			   };
			   
			   $('#text-inputs').on('change','input',function(e){
				   alert("这是");
			     $('#target').Jcrop('api').animateTo([
			       parseInt(d[ge]('crop-x').value),
			       parseInt(d[ge]('crop-y').value),
			       parseInt(d[ge]('crop-w').value),
			       parseInt(d[ge]('crop-h').value)
			     ]);
			   });*/
			//裁剪结束
			};
					try {
						reader.readAsDataURL(fileObj.files[0]);
					} catch (e) {
						layer.msg("地址出错或浏览器不支持");
					}
				} else if (browserVersion.indexOf("SAFARI") > -1) {
					layer.msg("不支持Safari6.0以下浏览器的图片预览!");
				}
			} else {
				layer.open({
							title : "预览",
							type : 1,
							content : "<div style='text-align:center'><img src='"
									+ e.target.result
									+ "' style='mix-width:400px'></div>",
							area : [ '500px', '500px' ],
						});
			}
		} else {
			layer.msg("图片类型有误");
		}
	});
})
	</script>
	
	<div class="row form-group">
		<label class="col-sm-2 control-label  pl-0 pr-5">活动分类：</label>
		<div class="col-sm-6 pl-0">
			<select class="form-control" name="activity.type">
				<option value="sport">体育</option>
				<option value="travel">户外</option>
				<option value="foods">美食</option>
			</select>
		</div>
		<!-- <div class="col-xs-3">
			<input type="text" class="form-control" placeholder="Tag 标签">
		</div>-->
	</div>

	<div class="row form-group">
		<label class="col-sm-2 control-label  pl-0 pr-5">人数限制：</label>
		<div class="col-xs-3  pl-0">
			<input type="text" class="form-control" placeholder="最少人数" name="activity.minPeople">
		</div>
		<div class="col-xs-3">
			<input type="text" class="form-control" placeholder="最多人数" name="activity.maxPeople">
		</div>
	</div>
	
	<div class="form-group normal-form">
		
		<label class="col-sm-2 control-label  pl-0 pr-5">活动地址：</label>
		<div class="col-xs-2 pl-0">
		   <input type="text" class="form-control" placeholder="">
		</div>
		<div class="col-xs-4">
			<input type="text" class="form-control" placeholder="具体地址" name="activity.address">
		</div>
	</div>
	
	<!--  -->
	<div class="form-group nomal-form">
	<label class="col-sm-2 control-label  pl-0 pr-5">活动时间：</label>
	<div class="input-group form-inline active" id="sid535267215-rap">
			<span class="input-group-addon">
				<i class="fa fa-calendar"></i>
			</span>
			<input type="text" name="startTime" id="sid535267215" class="form-control" value="" datatype="*" dateformat="YYYY-MM-DD HH:mm:ss" style="width: 200px"><span style="padding: 6px; float: left">
			结束
			</span>
			<input type="text" name="endTime" id="sid535267215-2" class="form-control" value="" nullmsg="日期不能为空" datatype="*" dateformat="YYYY-MM-DD HH:mm:ss" style="width: 200px">
		</div>
	</div>
	
	<div class="form-group nomal-form">
	<label class="col-sm-2 control-label  pl-0 pr-5">活动价格：</label>
         <div class="col-xs-5 pl-0 input-group form-inline">
            <input class="form-control" type="text" name="activity.newPrice" placeholder="现价">
               <span class="input-group-addon">
               <i class="fa fa-rmb"></i></span>
               
               <input class="form-control" type="text" name="activity.oldPrice" placeholder="原价">
               <span class="input-group-addon">
               <i class="fa fa-rmb"></i></span>
          </div>
      
	</div>
	
	<script type="text/javascript">
			$(function() {
				var dateFormat = 'YYYY-MM-DD HH:mm:ss';
				dateFormat = dateFormat ? dateFormat : 'YYYY-MM-DD';
				showTime = false;
				var s = dateFormat.toLowerCase();
				if (s.indexOf("h") > -1) {
					showTime = true;
				}
				var datestr = "" ? "" : moment().format(dateFormat);
				var datestr2 = "" ? "" : moment().format(dateFormat);
				$("#sid535267215-rap").daterangepicker({
					timePicker : showTime,
					timePickerIncrement : 1,
					timePicker12Hour : false,
					timePickerSeconds : true,
					startDate : datestr,
					endDate : datestr2,
					format : dateFormat
				}).on(
						"apply.daterangepicker",
						function(e, _this) {
							$("#sid535267215").val(
									_this.startDate.format(dateFormat))
									.trigger("blur change").siblings(
											"input:first").val(
											_this.endDate.format(dateFormat))
									.trigger("blur change");
						});
			});
		</script>


	<!--  嵌套UEditor插件 开始 -->
	<div class="form-group normal-form" >
	    <label class="col-sm-2 control-label  pl-0 pr-5">活动详情：</label>
		<div class="col-sm-6 pl-0" >
			<!-- 加载编辑器的容器 -->
			<script id="container" name="content" type="text/plain"></script> 
			<!-- 配置文件 -->
			<script type="text/javascript" src="./ueditor/ueditor.config.js"></script>
			<!-- 编辑器源码文件 -->
			<script type="text/javascript" src="./ueditor/ueditor.all.js"></script>
			<!-- 实例化编辑器 -->
			<script type="text/javascript">
				 
				 $(function(){
						var activityId = "<%=request.getParameter("activityId")%>";
						//alert(activityId);
						$.ajax({
							url:"<%=request.getContextPath()%>/activity/toUpdateActivityById",
							data:{activityId:activityId},
							//dataType:"html",
							type:"POST",
							//cache:true,
							success:function(data){
								var activity = data.data[0];
								$("input[name='activity.activityId']").val(activity.activityId);
								$("input[name='activity.title']").val(activity.title);
								$("input[name='activity.subhead']").val(activity.subhead);
								$("input[name='activity.thumbnails']").val(activity.thumbnails);
								$("input[name='activity.type']").val(activity.type);
								$("input[name='activity.minPeople']").val(activity.minPeople);
								$("input[name='activity.maxPeople']").val(activity.maxPeople);
								
								$("input[name='activity.address']").val(activity.address);
								$("input[name='startTime']").val(activity.startTime);
								$("input[name='endTime']").val(activity.endTime);
								
								$("input[name='activity.newPrice']").val(activity.newPrice);
								$("input[name='activity.oldPrice']").val(activity.oldPrice);
								
								/******************ueditor设置 开始 **********************/
								 window.UEDITOR_HOME_URL = "./ueditor/";
							    //初始化ueditor参数设置
								var ue = UE.getEditor('container',{
					                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个  
					                toolbars:[['FullScreen', 'Source','undo', 'redo', 'forecolor','bold',
					                           'simpleupload','insertimage','map','insertvideo','spechars','justifycenter']],  
					                //关闭字数统计  
					                wordCount:false,
					                //关闭elementPath  
					                elementPathEnabled:false,  
					                //默认的编辑区域高度  
					                initialFrameHeight:300  
					                //更多其他参数，请参考ueditor.config.js中的配置项  
					            });
								 ue.ready(function() {
									//设置编辑器的内容
									//var content = "<p style='text-align: center;'><span style='color: rgb(255, 192, 0);'><strong class='activity_title'>项目详情</strong></span></p><hr style='width: 95%;display: block;border: 1px solid #F0EFF5;margin: 10px auto;'><p style='text-align: left;'><span style='color: rgb(255, 192, 0);'><strong></strong></span><br></p><p style='text-align: center;'><span style='color: rgb(255, 192, 0);'><strong class='activity_title'>注意事项</strong></span></p><hr style='width: 95%;display: block;border: 1px solid #F0EFF5;margin: 10px auto;'><p style='text-align: left;'><span style='color: rgb(255, 192, 0);'><strong></strong></span><br></p><p style='text-align: center;'><span style='color: rgb(255, 192, 0);'><strong class='activity_title'>咨询评论</strong></span></p><hr style='width: 95%;display: block;border: 1px solid #F0EFF5;margin: 10px auto;'><p style='text-align: left;'>​</p>";
									var content = activity.detailsContent;
									 ue.setContent(content);
							    });
							 /******************ueditor设置结束 **********************/

							},
							error:function(){

							}
						});//ajax end
						
						
					});
			</script>
			
		</div>
	</div>
	<!--  嵌套UEditor插件 结束 -->



	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"></label>
		<div class="col-sm-6 pl-0">
			<!-- <button class="btn btn-info" id="submitId">提交</button>
			<button class="btn btn-info" type="button" id="submitId" value="submit">-->
			<a class="btn btn-info" id="submitId" >修改<a>
			<button class="btn btn-primary" id="goPreview">预览</button>
		</div>
		<div class="col-sm-3 valid-msg"></div>
	</div>
<script type="text/javascript">
	$("#submitId").click(function(){
		var params = $("#from557982744").serialize();
		console.log("params:"+params);
		//获取编辑器Content
		//var allContent = ue.getContent();
		//var detailsContent = encodeURI(allContent);
		//params = params+"&activity.detailsContent="+detailsContent;
		updateActivityById(params);
	});
	
	$("#goBackId").click(function(){
		alert("返回");
		//window.history.back(-1);
		window.location.href=document.referrer;
	});
	
	$("#goPreview").click(function(){
		//alert("预览");
		//获取编辑器内容
		var allHtml = ue.getAllHtml();
		//alert(allHtml);
		try{
			var w = window.open();
			w.document.open();
			w.document.write('<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">');
			w.document.write(allHtml);
			console.log("预览success");
		}catch (e) {
			console.log("预览error");
		}
		
		return false;
	});
	
	
</script>
</form>
