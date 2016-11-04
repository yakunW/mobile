<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>模板使用说明</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <p>1.	产品系数模板</p>
  <p>若某个单元格没有内容，将自动填充为“空”。如图：</p>
   <img alt="" src="img/1.png">
  <p>2.	工时模板</p>
  <p>分类、产品和工作环节的重复内容可以为空，由程序自动补充完整，工时内容没有值可以为空，由程序自动置为0。如图：</p>
   <img alt="" src="img/2.png">
  </body>
</html>
