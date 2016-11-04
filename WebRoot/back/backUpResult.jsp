<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.opensymphony.xwork2.ActionContext" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>备份恢复结果显示</title>
    
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
  数据<%=ActionContext.getContext().get("result") %><br/>
  备份文件是<a href="download.action?fileName=<s:property value="filename"/>&type=2"><s:property value="filename"/></a>点击下载
  <br/>服务器端的备份文件路径D:\database\
  <br/><a href="back/dataBackupRecovery.jsp" style="text-decoration: none;">点此返回</a>
  
  </body>
</html>
