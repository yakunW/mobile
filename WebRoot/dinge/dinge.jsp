<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Dinge</title>
</head>

<body>
<div style="height:50px;padding:10px">
<font style="height: 1; font-size: 24pt;font-weight:bolder; color: #0000ff; filter: glow(color = #1000a3, strength =10)">数据批量导入</font>
</div>
<hr>
<hr>
<form action="produce.action" method="post">
<div>
 <table height=100>
 <tr height=50>
   <td>
       <font style="font-weight:bold">项目编号：</font><input type="text" id="name" name="name"/>
   </td>
   <td>
       <font style="font-weight:bold">项目名称：</font><input type="text" id="name" name="name"/>
   </td>
   <td>
      <font style="font-weight:bold"> 项目省区：</font><input type="text" id="name" name="name"/>
   </td>
   <td>
       <font style="font-weight:bold">建设单位：</font><input type="text" id="name" name="name"/>
   </td>
   <td>
     <font style="font-weight:bold"> 业务大区（大区/分院）：</font><input type="text" id="name" name="name"/>
     
   </td>
   <td>
    <font style="font-weight:bold">计划经理：</font><input type="text" id="name" name="name"/>
   </td>
 </tr>
 
 <tr height=50>

  <td>
   <font style="font-weight:bold"> 客户经理：</font><input type="text" id="name" name="name"/>
   </td>
  <td>
    <font style="font-weight:bold">负责人：</font><input type="text" id="name" name="name"/>
   </td>
   <td>
    <font style="font-weight:bold">主专业：</font><input type="text" id="name" name="name"/>
   </td>
 </tr>      
 </table>
</div>
<table height=30>
</table>
 <hr>
 <hr>

 <div style="height:30px;font-weight:bold">
 主类：传统业务
 </div>
 <div>
  <table>
   <tr>
   <td height="20"><font style="font-weight:bold">分类：</font></td>
   <td><input type="checkbox" name="" value="os">工程设计</td>
   <td><input type="checkbox" name="" value="os">综合咨询</td>
   </tr>
   <tr>
   <td height="20"><font style="font-weight:bold">小类：</font></td>
   <td><input type="checkbox" name="" value="os">通信设备电源</td>
   </tr>
   <tr>
   <td height="20"><font style="font-weight:bold">分项：</font></td>
   <td><input type="checkbox" name="" value="os">机房电源</td>
   <td><input type="checkbox" name="" value="os">基站电源</td>
   <td><input type="checkbox" name="" value="os">干线传输站电源</td>
   <td><input type="checkbox" name="" value="os">变配电</td>
   <td><input type="checkbox" name="" value="os">动力环境监控</td>
   <td><input type="checkbox" name="" value="os">节能减排和新能源</td>
   </tr>
   <tr>
     <td>
     
      </td>
   </tr>
   <tr>
   <td></td>
   </tr>
 </table>
 <hr>
  <table  >
   <tr>
   <td height="20"><font style="font-weight:bold">产品：</font></td>
   <td><input type="checkbox" name="" value="os">直流48V/220V/330V</td>
   <td><input type="checkbox" name="" value="os">交流UPS</td>
   <td><input type="checkbox" name="" value="os">基站电源</td>
   <td><input type="checkbox" name="" value="os">干线传输电源</td>
   <td><input type="checkbox" name="" value="os">高低压、变配电系统</td>
   <td><input type="checkbox" name="" value="os">动力环节监控</td>
   <td><input type="checkbox" name="" value="os">新能源</td>
   </tr>
 
     <tr>
         <td height=20>
         <input type="button" name="环节细分查看" value="环节细分" onclick="window.open('xifen.action','newwindow', 'height=400, width=700,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;">
         </td>
         <td height=20>
         <input type="button" name="系数查看" value="系数查看" onclick="window.open('xishu.action','newwindow', 'height=400, width=600,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;">
         </td>
    </tr>
   
   </table>
   <hr>
   <table>
   <tr>
   <td>
       <font style="font-weight:bold">标准工时：</font><input type="text" id="name" name="name"/>
   </td>
   <td>
      <font style="font-weight:bold"> 修正工时：</font><input type="text" id="name" name="name"/>
   </td>
   
   <td>
       <font style="font-weight:bold">报价工时：</font><input type="text" id="name" name="name"/>
   </td>
   </tr>
   <tr>
   <td>
   </td>
   <td>
   </td>
   <td>
   <input type="button" name="获取报价工时" value="获取报价工时" onclick="window.open('gs.jsp','newwindow', 'height=400, width=600,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:120px;font-size:15px;font-weight:bolder;">
   </td>
   </tr>
   </table>
</div>
   <input type="submit" name="批量导入" valve="批量导入" style="width:120px;font-size:15px;font-weight:bolder;">
</form>
</body>
</html>