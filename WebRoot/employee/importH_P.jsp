<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
 function isValid()
  {	
  	if(importSingle.pc.value == "")
  	{	
  		window.alert("���β���Ϊ��!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	if(importSingle.file.value == "")
  	{	
  		window.alert("����ѡ���ļ�!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	importSingle.submit();
  }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>��Ա���˹���</title>
</head>
<body>
	<div id="content" style="width:600" align="center">
    	<%
			String flag = request.getParameter("flag");
		%>	
		<h2><%
			if(flag.equals("hour")) 
				out.println("�ϴ���ʱ�ĵ�");
			else 
				out.println("�ϴ����˳ɼ��ĵ�");
		 %></h2>
		 <s:fielderror cssStyle="color: red">
			<s:param>error</s:param>
		</s:fielderror>
		 <s:form action="importSingle" namespace="/employee" method="post" theme="simple" enctype="multipart/form-data">
			<input type="hidden" name="flag" value="<%=flag %>">
			<table>
			<tr>
				<td>���Σ�</td>
				<td><s:textfield name="pc"  /></td>
			</tr>
			<tr>
				<td colspan="2"><s:file name="file"/></td>
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