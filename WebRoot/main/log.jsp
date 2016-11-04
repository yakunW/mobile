<!-- author:xuxiaoyin -->
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>计划部</title>
</head>
<frameset id="main" rows="150,*,30">
    <frameset id="top" rows="30,*" >
        <frame src="main/banner.jsp" noresize="noresize" frameborder="0" scrolling="no"/>
        <frame src="main/top.jsp" noresize="noresize" frameborder="0" scrolling="no"/>
    </frameset>
    <frameset id="link" cols="200,*">
        <frame src="main/link.jsp" noresize="noresize" frameborder="0" scrolling="no"/>
        <frame name="work" src="main/test.jsp" noresize="noresize" frameborder="0" scrolling="yes"/>
    </frameset>
    <frameset id="bottom">
        <frame src="main/bottom.jsp" noresize="noresize" frameborder="0" scrolling="no"/>
    </frameset>
</frameset>
<body>        
</body>
</html>