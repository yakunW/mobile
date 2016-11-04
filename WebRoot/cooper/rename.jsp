<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>计算年度积分</title>
  <style type="text/css">
	*{margin:0; padding:0;}
	#tablit {margin:20px;width:700px; height:393px; border:#BCE2F3 1px solid;padding-top:10px;background:#E4F2FB;}
	#tablit dl{ float:left; width:700px;}
	#tablit dl dt{float:left;border-bottom:#64B8E4 1px solid; width:15px; height:31px; line-height:30px;}
	#tablit dl dd{float:left;width:130px;height:30px; line-height:30px; text-align:center;}
	#tablit .on{border:#64B8E4 1px solid;border-bottom:#FFF 1px solid; color:#FF6600;background:#FFF;}
	#tablit .out{border:#64B8E4 1px solid; color:#000;background:#64B8E4;}
	.tabcon{width:100%; height:330px; background: #FFF; clear:both;}
	.dis{display:none;}
	</style>
  <link href="<%=basePath%>css/default.css" rel="stylesheet" type="text/css" />
	<!--<script type="text/javascript">
 function isValid()
  {	
  	if(rename2.oldname.value == "")
  	{	
  		window.alert("曾用名不能为空!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	
  	if(rename2.newname.value == "")
  	{	
  		window.alert("新名称不能为空!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	
  	rename2.submit();
  }
</script>
-->
  </head>
  
  <body >
   <div id="tablit">
    <dl>
        <dt></dt>
        <dd class="on"><font size="3">Excel表更新</font></dd>
        <dt></dt>
       <!--   <dd class="out"><font size="3">手动更新</font></dd>
        <dt></dt>-->
        <dd class="out"><font size="3">导出txt格式公司名</font></dd>
        <dt></dt>
    </dl>
  <div class="tabcon">
	    <s:form action="rename" namespace="/cooper" method="post" theme="simple" enctype="multipart/form-data" >
			<br/>
			<font size="3"><b>公司名称：</b></font>
				<s:file name="file"/>
			<br/><br/>
			<s:submit cssStyle="font-size: 18px;" value="确定"  />
	   	 </s:form>
	</div>
	
    <!--  <div class="tabcon dis"><br/>
    	<s:form action="rename2" namespace="/cooper" method="post" onsubmit="return isValid(this);">
			<font size="3"><b>公司曾用名:</b></font><input type="text" name="oldname"/>
			<br/><br/>
			<font size="3"><b>公司新名称:</b></font><input type="text" name="newname"/>
		    <br/><br/><font size="3"><s:submit cssStyle="font-size: 18px;" value="确定"  />	</font>	
	    </s:form></div> 
   </div>-->
	  
	<div class="tabcon dis"><br/>
	<font size="3">点击下面的公司新名称进行下载</font><br/><br/>
    	<a href="../download.action?fileName=CompList&type=1" style="text-decoration: none;"><font size="3">公司新名称下载</font></a>
        <br/><br/><font color="red">温馨提示：要在对公司的名单修改后才能下载新公司名单，这样才能保证下载的是最新名单。<br/>
                   如果提示错误，请进行通过Excel更新操作，然后再来下载公司新名称。</font>
   </div>
   
  </body>
  <script type="text/javascript">

		var mDD = document.getElementById("tablit").getElementsByTagName("dd");
		var mDIV= document.getElementById("tablit").getElementsByTagName("div");		
		for (var i=0;i<mDD.length;i++){
		 (function(index) {
		  mDD[index].onmouseover = function() {
		   if (mDD[index].className == 'out') {
		    for (var j = 0; j < mDD.length; j++) {
		     mDD[j].className = 'out';
		     mDIV[j].style.display = 'none';
		    }
		    mDD[index].className = 'on';
		    mDIV[index].style.display = 'block';
		   }
		  }
		 })(i);
		}
	</script>
</html>