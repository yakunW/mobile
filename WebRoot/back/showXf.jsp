<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>����ϸ��</title>
    
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
    <table border="1" cellspacing="0" style="position:absolute;left:1px;">
    <tr>
     <td><font style="font-weight:bold">����</font></td>
     <td><font style="font-weight:bold">��Ʒ</font></td>
     <td><font style="font-weight:bold">��������</font></td>
     <td><font style="font-weight:bold">����ϸ��</font></td>
     <td><font style="font-weight:bold">��ʱϸ��</font></td>
    </tr>
    <s:iterator value="standardXfList" id="i">
    <tr>
    <td><s:property value="#i.fenlei"/></td>
    <td><s:property value="#i.chanpin"/></td>
    <td><s:property value="#i.hj"/></td>
    <td><s:property value="#i.xf"/></td>
    <td><s:property value="#i.gs"/></td>
    </tr>
    </s:iterator>
    </table>
  </body>
</html>
