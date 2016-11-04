<?xml version="1.0" encoding="GBK" ?>
<%@ page contentType="application/vnd.ms-excel" language="java" 
import="java.util.*,com.szh.excelexport.util.*" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="com.szh.struts2.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Insert title here</title>
</head>
<body>
<%
response.setHeader("Content-Disposition","attachment;filename=test123.xlsx");//指定下载的文件名
response.setContentType("application/vnd.ms-excel");
//WriteExcel  we=new WriteExcel();
//we.getExcel("111.xls",response.getOutputStream());
List<Data> dlist=(List<Data>)session.getAttribute("searchList");
System.out.println(dlist.size());
Excelexport.exportExcel(dlist,response.getOutputStream());
out.clear();
out=pageContext.pushBody();
%>
</body>
</html>