<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
    <h3>标准数据的添加和修改</h3>
    <form action="upload.action" method="post" enctype="multipart/form-data">
    	产品系数模板：<input type="file" id="file1" name="upload"/><br/>
    	&nbsp;&nbsp;&nbsp;&nbsp;工时模板：<input type="file" id="file2" name="upload"/><br/>
    	<s:submit name="operation" value="添加" ></s:submit>
    	<s:submit name="operation" value="修改" ></s:submit>
    	
     </form>
    <br/>
    <br/>
    <form action="showStandard.action" method="post" target="_blank">
    <h3>标准数据的查找和删除</h3>
    <input type="submit" value="显示" />
    </form>
    <br/>
    <br/>
    <form   action="backUp.action"  method="post">
    <h3>数据的备份和恢复</h3>
    <s:submit value="备份" method="backUp"  ></s:submit>
    </form>
    <form action="recoveryUpload.action" method="post" enctype="multipart/form-data">
    选择要恢复的文件： <input type="file" id="file3" name="sql"/><br/>
    <input type="submit" value="恢复"/>
    </form>
    <br/>	
    <h3>模板下载</h3>
        <ul>
    <li>
       <a href="download.action?fileName=产品系数模板.xlsx">产品系数模板.xlsx</a>
    </li>
    <li>
       <a href="download.action?fileName=工时模板.xlsx">工时模板.xlsx</a><br/>
    </li> 
    </ul>
    <h3>模板使用说明</h3>
    <ul>
    <li>
        <a href="instructions.jsp" target="_blank">模板使用说明查看</a> 
    </li>
    </ul>
   
  </body>
</html>
