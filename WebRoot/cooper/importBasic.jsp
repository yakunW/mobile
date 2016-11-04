<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<s:form action="importBasic" namespace="/cooper" method="post" theme="simple" enctype="multipart/form-data">
    				
    		<font size="3"><b>类别：</b></font>
			<input type="radio" name="class0" value=1 checked="checked" /><font size="3">综合</font>
			<input type="radio" name="class0" value=2 /><font size="3">传输</font>
			<input type="radio" name="class0" value=3 /><font size="3">无线</font>
			<input type="radio" name="class0" value=4 /><font size="3">交换</font>
			<input type="radio" name="class0" value=5 /><font size="3">数据</font>
			<input type="radio" name="class0" value=6 /><font size="3">电源</font>
			<input type="radio" name="class0" value=7 /><font size="3">土建</font>
			<input type="radio" name="class0" value=8 /><font size="3">网优</font>
			<br/><br/>
			<s:file name="file"/>	
			<s:submit cssStyle="font-size: 18px;" value="确定"  />			
	   	 </s:form>
</body>
</html>