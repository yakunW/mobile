<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>upload test</title>
</head>
<body>
<div>
<hr/>
<hr/>
<hr/>
</div>
<div style="height:50px;padding-top:10px">
<font style="height: 1; font-size: 24pt;font-weight:bold; color: #0000ff; filter: glow(color = #1000a3, strength =10)">导入Excel数据：</font>
</div>
<div>
<form  action="up.action" method="post" enctype="multipart/form-data">
 <table style="z-index:-1">
  <tr>
   <td style="height:30px;"><font style="font-weight:bold;">请上传要导入的Excel文件：</font></td>
  </tr>
  <tr>    
     <td>  
      <font style="font-weight:bold;">选择文件：</font><input type="file"  name="upload"/>
     </td>
  </tr>
  <tr>     
      <td >
      <input type="submit" value="上传" style="width:100px;height:30px;font-size:16px;font-weight:bolder"/>
     </td>           
  </tr> 
 </table>      
</form>
   <!-- 
   <table style="position:relative;left:300px;top:0px;z-index:30">
      <tr>
        <td>
        <input type="button" name="查看数据" value="查看数据" onclick="window.open('search.action')" style="width:100px;height:30px;font-size:16px;font-weight:bolder"/>
        </td>
      </tr>
   </table>
    -->
</div>
<div>
<hr/>
<hr/>
</div>
<!--  
<div style="height:50px;padding-top:10px">
<font style="height: 1; font-size: 24pt;font-weight:bold; color: #0000ff; filter: glow(color = #1000a3, strength =20)">功能二：批量导入数据：</font>
</div>
<table>
   <tr>
     <td>
     <input type="button" name="批量处理" value="批量处理" onclick="window.open('dinge.jsp')" style="width:100px;height:30px;font-size:16px;font-weight:bolder"/>
     </td>
   </tr>
</table>
 -->
</body>
</html>