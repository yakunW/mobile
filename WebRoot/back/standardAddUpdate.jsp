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
    
    <title>��������޸�</title>
    
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
      <form action="upload.action" method="post" enctype="multipart/form-data">
         <table align="center">
         <tr>
         <td align="left" height="140"><font size="5" style="font-weight:bolder">��������޸�</font></td>  
         </tr>
         <tr><td></td></tr>
         <tr>
         <td>��Ʒϵ��ģ�壺<input type="file" id="file1" name="upload"/></td>
         </tr>
         <tr>
         <td align="right">ϸ��ģ�壺<input type="file" id="file2" name="upload"/></td>
         </tr>
         <tr>
         <td align="center">
         <s:submit name="operation" value="���" ></s:submit>
    	<s:submit name="operation" value="�޸�" ></s:submit>
    	</td>
         </tr>
         </table>
         </form>
     
  </body>
</html>
