<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>文档上传</title>
  <link href="<%=basePath%>css/default.css" rel="stylesheet" type="text/css" />
     
   	<script type="text/javascript" src="<%=basePath%>js/swfupload/swfupload.js" ></script>
   	<script type="text/javascript" src="<%=basePath%>js/swfupload/swfupload.queue.js" ></script>
    <script type="text/javascript" src="<%=basePath%>js/swfupload/fileprogress.js" ></script>
    <script type="text/javascript" src="<%=basePath%>js/swfupload/handlers.js"></script>
   	<!-- 初始化swfupload 对象-->
   <script type="text/javascript">
		var upload1;
		window.onload = function() {
		
			upload1 = new SWFUpload({
				// Backend Settings
				
				upload_url: "cooper/cooperPaths.action", //这里的action不可以删除
				post_params: {"picSESSID" : "songhao"},
				file_post_name: "file",
				// File Upload Settings
				file_size_limit : "0",	// 100MB  //文件大小设定
				file_types : "*.xlsx",//文件上传类型现在
				file_types_description : "All Files",
				file_upload_limit : "0",
				file_queue_limit : "0",

				// Event Handler Settings (all my handlers are in the Handler.js file)
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings   button的图片
				button_image_url : "../images/XPButtonUploadText_61x22.png",
				button_placeholder_id : "spanButtonPlaceholder1",
				button_width: 61,
				button_height: 22,
				
				// Flash Settings
				flash_url : "../js/swfupload/swfupload.swf",
				

				custom_settings : {
					progressTarget : "fsUploadProgress1"
				},
				
				// Debug Settings
				debug: false
				
				
				
			});
	     }
	    
	</script>
	<script type="text/javascript">
 function isValid()
  {	
  	if(importCooper.year.value == "")
  	{	
  		window.alert("年份不能为空!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	
  	importCooper.submit();
  }
</script>
  </head>
  
  <body >

  <div id="content" style="width:600">
	<h2>上传附件二</h2>
		<s:form action="cooperPaths" namespace="/cooper" method="post" enctype="multipart/form-data">
			<div class="fieldset flash" id="fsUploadProgress1">
				<span class="legend">文件上传</span>
			</div>
		
			<div style="padding-left: 5px;">
				<span id="spanButtonPlaceholder1"></span>
				<input id="btnUpload1" type="button" value="开始上传" onclick="upload1.startUpload()"  
					style="margin-left: 2px; height: 22px; font-size: 8pt;" />					
			</div>	
		</s:form>
        <br/>
	    <s:form action="importCooper" namespace="/cooper" method="post" onsubmit="return isValid(this);">
			
			<font size="3"><b>年份：</b></font><input type="text" name="year"/><br/><br/>
			<font size="3"><b>季度：</b></font>
			<input type="radio" name="quarter" value=1 checked="checked" /><font size="3">一季度</font>
			<input type="radio" name="quarter" value=2 /><font size="3">二季度</font>
			<input type="radio" name="quarter" value=3 /><font size="3">三季度</font>
			<input type="radio" name="quarter" value=4 /><font size="3">四季度</font>
			<br/><br/>
			<s:submit cssStyle="font-size: 18px;" value="确定"  />		
	   	 </s:form>
    </div>
  </body>
</html>
