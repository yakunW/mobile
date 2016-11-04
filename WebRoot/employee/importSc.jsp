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
        function getValue(){
        var flag = document.getElementById("flag");
				flag.value = value;
				var pc = document.getElementById("pc");
				pc.value = value;
        }
		window.onload = function() {
		
			upload1 = new SWFUpload({
				// Backend Settings
				
				upload_url: "employee/scorePaths.action", //这里的action不可以删除
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
  	if(importSc.pc.value == "")
  	{	
  		window.alert("批次不能为空!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	if(importSc.file.value == "")
  	{	
  		window.alert("必须选择文件!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	importSc.submit();
  }
</script>
  </head>
  
  <body >

  <div id="content" style="width:600">
	<h2>上传打分文档</h2>
    	<%
			String flag = request.getParameter("flag");
		%>
		<s:form action="scorePaths" namespace="/employee" method="post" enctype="multipart/form-data">
			<div class="fieldset flash" id="fsUploadProgress1">
				<span class="legend">文件上传</span>
			</div>
		
			<div style="padding-left: 5px;">
				<span id="spanButtonPlaceholder1"></span>
				<input id="btnUpload1" type="button" value="开始上传" onclick="upload1.startUpload()"  
					style="margin-left: 2px; height: 22px; font-size: 8pt;" />					
			</div>	
		<br/>
		</s:form>
    
	    <s:form action="importSc" namespace="/employee" method="post" onsubmit="return isValid(this);">
			<font size="3">批次:</font><input type="text" name='pc' width="15">
			<s:hidden name="flag" value="%{#request.flag}" /><br/><br/>
			<s:submit cssStyle="font-size: 18px;" value="确定" />			
	   </s:form>
	   
    </div>
  </body>
</html>
