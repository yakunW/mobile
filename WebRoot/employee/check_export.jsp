<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
 function isValid()
  {	
  	if(check_export.pc.value == "")
  	{	
  		window.alert("���β���Ϊ��!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	if(check_export.file.value == "")
  	{	
  		window.alert("����ѡ���ļ�!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	check_export.submit();
  }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>��Ա���˹���</title>
</head>
<body >
	<div id="content" style="width:600"  align="center">
    	<%
			String flag = request.getParameter("flag");
		%>	
		<h2><%
			if(flag.equals("check")) 
				out.println("ƥ��У��");
			else 
				out.println("������Ϣ����");
		 %></h2>
		 
		<s:fielderror cssStyle="color: red">
			<s:param>error</s:param>
		</s:fielderror>
			
		<s:form action="check_export" namespace="/employee" method="post" theme="simple" >
			<input type="hidden" name="flag" value="<%=flag %>">
			<table>
			<tr>
				<td align="right" >���Σ�</td>
				<td align="left" ><s:textfield name="pc" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
				<s:submit cssStyle="font-size: 18px;" value="ȷ��"  onclick="isValid()"/></td>
			</tr>
			</table>
		
		</s:form>
		
	</div>
	
</body>
</html>