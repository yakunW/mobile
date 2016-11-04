<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title>上传成功</title>

	</head>

	<body>
		<%=session.getAttribute("news") %>
		<br>
		<div id="go"></div>
<script>   
  var   n=10;   
  function   go()   
  {   
        if(n==0){   
        	window.history.go(-1);  
         	return;   
        }   
        n--;   
        document.getElementById("go").innerText="还有"+n+"秒后跳转到上传页面";   
        setTimeout("go()",1000);   
  }   
  go();   
</script>
	</body>
</html>
