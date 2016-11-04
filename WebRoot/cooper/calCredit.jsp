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
  <link href="<%=basePath%>css/default.css" rel="stylesheet" type="text/css" />
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
	<h2>计算季度积分</h2>
	    <s:form action="calCredit" namespace="/cooper" method="post" onsubmit="return isValid(this);">
	
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
