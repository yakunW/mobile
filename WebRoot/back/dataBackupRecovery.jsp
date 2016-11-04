<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据备份恢复</title>
    
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
    <br/><br/><br/><br/><br/><br/>
    <table align="center"  border="1" cellspacing="0" cellpadding="5" width="700">
    <tr>
    <td align="center"><font size="4"><b>数据备份</b></font></td>
    <td><form action="backUp.action"  method="post">
    	备份:<s:submit value="点击备份" cssStyle="font-size: 18px;" method="backUp"  ></s:submit></form>
    	注：此处备份内容为三个工具和后台管理规则两部分内容
    	</td>
    </tr>
    <tr>
    <td align="center"><font size="4"><b>数据恢复</b></font></td>
    <td> <form action="recoveryUpload.action" method="post" enctype="multipart/form-data">
    	恢复:<input type="file" id="file3" name="sql"/><br/><br/>
    	<s:submit value="恢复" cssStyle="font-size: 18px;"></s:submit></form></td>
    </tr>
    </table>
    <!--   <form   action="backUp.action"  method="post">
    <table align="left"  width="50%" >
    <tr> 
    <td align="center" height="70" valign="bottom"> 备份:<s:submit value="点击备份" method="backUp"  ></s:submit></td>
    </tr>
    <tr>
    <td align="center">注：此处备份内容为设计定额工具和后台管理规则两部分内容</td>
    </tr>
    </table>
    </form>
     <form action="recoveryUpload.action" method="post" enctype="multipart/form-data">
     <table align="left" >
     <tr>
     <td align="center" height="70" valign="bottom"> 恢复:<input type="file" id="file3" name="sql"/></td>
     </tr>
     <tr align="center"> <td><input type="submit" value="恢复"/></td>
     </tr>
     
     </table>
     </form>-->
  </body>
</html>
