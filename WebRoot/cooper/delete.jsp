<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成员考核工具</title>

</head>
<body>
	<font size="4" color="red"><strong>对数据库中的数据进行清除!!!</strong></font>
	<s:form action="delete" namespace="/cooper" method="post" >
	<br/>
		<s:submit cssStyle="font-size: 18px;" value="确定"/>
		<input style="font-size: 18px;" type="button" onclick="window.location.href='/JHB/cooper/cancel.jsp'" value="取消"/>
	</s:form>
</body>
</html>