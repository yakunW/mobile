<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>������Ȼ���</title>
 
	  <style type="text/css">
	*{margin:0; padding:0;}
	#tablit {margin:20px;width:700px; height:393px; border:#BCE2F3 1px solid;padding-top:10px;background:#E4F2FB;}
	#tablit dl{ float:left; width:700px;}
	#tablit dl dt{float:left;border-bottom:#64B8E4 1px solid; width:15px; height:31px; line-height:30px;}
	#tablit dl dd{float:left;width:180px;height:30px; line-height:30px; text-align:center;}
	#tablit .on{border:#64B8E4 1px solid;border-bottom:#FFF 1px solid; color:#FF6600;background:#FFF;}
	#tablit .out{border:#64B8E4 1px solid; color:#000;background:#64B8E4;}
	.tabcon{width:100%; height:330px; background: #FFF; clear:both;}
	.dis{display:none;}
	</style>
  <link href="<%=basePath%>css/default.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
 function isValid()
  {	
  	if(importCooper.gsname.value == "")
  	{	
  		window.alert("��˾���Ʋ���Ϊ��!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	
  	importCooper.submit();
  }
</script>
  </head>
  
  <body >
<div id="tablit">
    <dl>
        <dt></dt>
        <dd class="on"><font size="3">����˾���Ʋ�ѯ</font></dd>
        <dt></dt>
        <dd class="out"><font size="3">�����Ȳ�ѯ</font></dd>
        <dt></dt>
    </dl>
  <div class="tabcon">
	    <s:form action="nameSelect" namespace="/cooper" method="post" onsubmit="return isValid(this);">
			<br/>
			<font size="3"><b>��˾���ƣ�</b></font><input type="text" name="gsname"/><br/><br/>
	
			<s:submit cssStyle="font-size: 18px;" value="ȷ��"  />		
	   	 </s:form>
	</div>
	
    <div class="tabcon dis"><br/>
    	<s:form action="exportAny" namespace="/cooper" method="post" onsubmit="return isValid(this);">
			<font size="3"><b>��ݣ�</b></font><input type="text" name="year"/><br/><br/>
			<font size="3"><b>���ȣ�</b></font>
			<input type="radio" name="quarter" value=1 checked="checked" /><font size="3">һ����</font>
			<input type="radio" name="quarter" value=2 /><font size="3">������</font>
			<input type="radio" name="quarter" value=3 /><font size="3">������</font>
			<input type="radio" name="quarter" value=4 /><font size="3">�ļ���</font>
			<br/><br/>
			<s:submit cssStyle="font-size: 18px;" value="ȷ��"  />		
	    </s:form></div>
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
