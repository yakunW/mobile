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
<font style="height: 1; font-size: 24pt;font-weight:bolder; color: #0000ff; filter: glow(color = #1000a3, strength =10)">������������</font>
</div>
<hr>
<hr>
<form action="produce.action" method="post">
<div>
 <table height=100>
 <tr height=50>
   <td>
       <font style="font-weight:bold">��Ŀ��ţ�</font><input type="text" id="name" name="name"/>
   </td>
   <td>
       <font style="font-weight:bold">��Ŀ���ƣ�</font><input type="text" id="name" name="name"/>
   </td>
   <td>
      <font style="font-weight:bold"> ��Ŀʡ����</font><input type="text" id="name" name="name"/>
   </td>
   <td>
       <font style="font-weight:bold">���赥λ��</font><input type="text" id="name" name="name"/>
   </td>
   <td>
     <font style="font-weight:bold"> ҵ�����������/��Ժ����</font><input type="text" id="name" name="name"/>
     
   </td>
   <td>
    <font style="font-weight:bold">�ƻ�����</font><input type="text" id="name" name="name"/>
   </td>
 </tr>
 
 <tr height=50>

  <td>
   <font style="font-weight:bold"> �ͻ�����</font><input type="text" id="name" name="name"/>
   </td>
  <td>
    <font style="font-weight:bold">�����ˣ�</font><input type="text" id="name" name="name"/>
   </td>
   <td>
    <font style="font-weight:bold">��רҵ��</font><input type="text" id="name" name="name"/>
   </td>
 </tr>      
 </table>
</div>
<table height=30>
</table>
 <hr>
 <hr>

 <div style="height:30px;font-weight:bold">
 ���ࣺ��ͳҵ��
 </div>
 <div>
  <table>
   <tr>
   <td height="20"><font style="font-weight:bold">���ࣺ</font></td>
   <td><input type="checkbox" name="" value="os">�������</td>
   <td><input type="checkbox" name="" value="os">�ۺ���ѯ</td>
   </tr>
   <tr>
   <td height="20"><font style="font-weight:bold">С�ࣺ</font></td>
   <td><input type="checkbox" name="" value="os">ͨ���豸��Դ</td>
   </tr>
   <tr>
   <td height="20"><font style="font-weight:bold">���</font></td>
   <td><input type="checkbox" name="" value="os">������Դ</td>
   <td><input type="checkbox" name="" value="os">��վ��Դ</td>
   <td><input type="checkbox" name="" value="os">���ߴ���վ��Դ</td>
   <td><input type="checkbox" name="" value="os">�����</td>
   <td><input type="checkbox" name="" value="os">�����������</td>
   <td><input type="checkbox" name="" value="os">���ܼ��ź�����Դ</td>
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
   <td height="20"><font style="font-weight:bold">��Ʒ��</font></td>
   <td><input type="checkbox" name="" value="os">ֱ��48V/220V/330V</td>
   <td><input type="checkbox" name="" value="os">����UPS</td>
   <td><input type="checkbox" name="" value="os">��վ��Դ</td>
   <td><input type="checkbox" name="" value="os">���ߴ����Դ</td>
   <td><input type="checkbox" name="" value="os">�ߵ�ѹ�������ϵͳ</td>
   <td><input type="checkbox" name="" value="os">�������ڼ��</td>
   <td><input type="checkbox" name="" value="os">����Դ</td>
   </tr>
 
     <tr>
         <td height=20>
         <input type="button" name="����ϸ�ֲ鿴" value="����ϸ��" onclick="window.open('xifen.action','newwindow', 'height=400, width=700,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;">
         </td>
         <td height=20>
         <input type="button" name="ϵ���鿴" value="ϵ���鿴" onclick="window.open('xishu.action','newwindow', 'height=400, width=600,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;">
         </td>
    </tr>
   
   </table>
   <hr>
   <table>
   <tr>
   <td>
       <font style="font-weight:bold">��׼��ʱ��</font><input type="text" id="name" name="name"/>
   </td>
   <td>
      <font style="font-weight:bold"> ������ʱ��</font><input type="text" id="name" name="name"/>
   </td>
   
   <td>
       <font style="font-weight:bold">���۹�ʱ��</font><input type="text" id="name" name="name"/>
   </td>
   </tr>
   <tr>
   <td>
   </td>
   <td>
   </td>
   <td>
   <input type="button" name="��ȡ���۹�ʱ" value="��ȡ���۹�ʱ" onclick="window.open('gs.jsp','newwindow', 'height=400, width=600,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:120px;font-size:15px;font-weight:bolder;">
   </td>
   </tr>
   </table>
</div>
   <input type="submit" name="��������" valve="��������" style="width:120px;font-size:15px;font-weight:bolder;">
</form>
</body>
</html>