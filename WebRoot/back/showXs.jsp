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
    
    <title>ϵ��</title>
    
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
     <td width="10%"><font style="font-weight:bold">���</font></td>
     <td width="30%"><font style="font-weight:bold">��Ŀ��ģ����ϵ��</font></td>
     <td width="30%"><font style="font-weight:bold">��Ŀ��������ϵ��</font></td>
     <td width="30%"><font style="font-weight:bold">��Ŀ��������ϵ��</font></td>
    </tr>
     <s:iterator value="standardXsList" id="i">
    <tr>
    <td><s:property value="#i.fid"/></td>
    <td><s:property value="#i.xs1"/></td>
    <td><s:property value="#i.xs2"/></td>
    <td><s:property value="#i.xs3"/></td>
    </tr>
    </s:iterator>
    </table>
  </body>
</html>
