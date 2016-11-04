<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>规则模板下载</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <style type="text/css">
   	   .td{
   	   	  height:35;
   	   	  width:350;
   	   }
   </style>
  </head>
  
  <body>
    <table align="center"  border="1" cellspacing="0" cellpadding="5" width="600">
    <thead><tr><td colspan="2" align="center" height="70"><b><font size="6">模板下载</font></b></td></tr></thead>
    <tr>
    <td rowspan="3" align="center"><font size="5">设计定额工具</font></td>
    <td class="td"><a href="download.action?fileName=产品系数模板.xlsx" style="text-decoration: none;">1.产品系数模板.xlsx</a></td>
    </tr>
    <tr>
    <td class="td"><a href="download.action?fileName=细分模板.xlsx" style="text-decoration: none;">2.细分模板.xlsx</a> </td>
    </tr>
    <tr>
    <td class="td"><a href="back/instructions.jsp" target="_blank" style="text-decoration: none;">3.模板使用说明</a></td>
    </tr>
    <tr>
    <td rowspan="4" align="center"><font size="5">员工绩效考核工具</font></td>
    <td class="td"><a href="download.action?fileName=打分表模板.xlsx" style="text-decoration: none;">1.打分表模板.xlsx</a></td>
    </tr>
    <tr>
    <td class="td"><a href="download.action?fileName=工时表（旧）模板.xlsx" style="text-decoration: none;">2.工时表（旧）模板.xlsx</a> </td>
    </tr>
    <tr>
    <td class="td"><a href="download.action?fileName=工时表（新）模板.xlsx" style="text-decoration: none;">3.工时表（新）模板.xlsx</a> </td>
    </tr>
    <tr>
    <td class="td"><a href="download.action?fileName=考核表模板.xlsx" style="text-decoration: none;">4.考核表模板.xlsx</a> </td>
    </tr>
    <tr>
    <td rowspan="2" align="center"><font size="5">合作公司考核工具</font></td>
    <td class="td"><a href="download.action?fileName=动态星级评定综合&专业排名表模板.xlsx" style="text-decoration: none;">1.动态星级评定综合&专业排名表模板.xlsx</a></td>
    </tr>
    <tr>
    <td class="td"><a href="download.action?fileName=修改公司名称模板.xlsx" style="text-decoration: none;">2.修改公司名称模板.xlsx</a> </td>
    </tr>
    </table>
  </body>
</html>
