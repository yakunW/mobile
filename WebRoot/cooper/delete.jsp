<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>��Ա���˹���</title>

</head>
<body>
	<font size="4" color="red"><strong>�����ݿ��е����ݽ������!!!</strong></font>
	<s:form action="delete" namespace="/cooper" method="post" >
	<br/>
		<s:submit cssStyle="font-size: 18px;" value="ȷ��"/>
		<input style="font-size: 18px;" type="button" onclick="window.location.href='/JHB/cooper/cancel.jsp'" value="ȡ��"/>
	</s:form>
</body>
</html>