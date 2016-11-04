<!-- author:xuxiaoyin -->
<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@page import="java.util.*" %>
<%@page import="com.xxy.struts2.bean.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>xishu</title>
</head>
<body>
<%
           List<Xishu> dlist=new ArrayList<Xishu>();
           dlist=(List<Xishu>)session.getAttribute("sdata");
           int dn=0;
           dn=dlist.size();
%>
    <table border=1>
      <tr style="background-color:blue">
         <td width="150" ><font style="font-weight:bold">分类</font></td>
         <td width="150" ><font style="font-weight:bold">产品</font></td>
         <td width="120" ><font style="font-weight:bold">系数1</font></td>
        
         <td width="120" ><font style="font-weight:bold">系数2</font></td>
   
         <td width="120" ><font style="font-weight:bold">系数3</font></td>
         
      </tr>
      
      <%for(int i=0;i<dn;i++) { %>
      <tr>
          <td width="150" ><%=dlist.get(i).getFenlei()%></td>
          <td width="150" ><%=dlist.get(i).getChanpin()%></td>
          <td width="120" ><%=dlist.get(i).getXs1() %></td>
         
          <td width="120" ><%=dlist.get(i).getXs2() %></td>
          
          <td width="120" ><%=dlist.get(i).getXs3() %></td>
          
       </tr>   
      <%} %>
    </table>
</body>
</html>