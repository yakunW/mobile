<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'nameSelList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/table.css">
</head>
<body>
<table>
	<thead>
		<tr>
			<th>���</th>
			<th>����</th>
			<th>��˾����</th>
			<th>�ۺ�ʡ</th>
			<th>�ۺϳɼ�</th>
			<th>����ʡ</th>
			<th>����ɼ�</th>
			<th>����ʡ</th>
			<th>���߳ɼ�</th>
			<th>����ʡ</th>
			<th>�����ɼ�</th>
			<th>����ʡ</th>
			<th>���ݳɼ�</th>
			<th>��Դʡ</th>
			<th>��Դ�ɼ�</th>
			<th>����ʡ</th>
			<th>�����ɼ�</th>
			<th>����ʡ</th>
			<th>���ųɼ�</th>
		</tr>
	</thead>
	<tbody id="tab">
		<s:iterator value="#request.nameSel" >
			<tr>
				<td><s:property value="year" /></td>
				<td><s:property value="quarter" /></td>
				<td><s:property value="name" /></td>
				<td><s:property value="sumProvinces" /></td>
				<td><s:property value="sum" /></td>
				<td><s:property value="transProvinces" /></td>
				<td><s:property value="trans" /></td>
				<td><s:property value="wirelessProvinces" /></td>
				<td><s:property value="wireless" /></td>
				<td><s:property value="switchxProvinces" /></td>
				<td><s:property value="switchx" /></td>
				<td><s:property value="dataProvinces" /></td>
				<td><s:property value="data" /></td>
				<td><s:property value="powerProvinces" /></td>
				<td><s:property value="power" /></td>
				<td><s:property value="civilProvinces" /></td>
				<td><s:property value="civil" /></td>
				<td><s:property value="networkProvinces" /></td>
				<td><s:property value="network" /></td>
			</tr>
		</s:iterator>
	</tbody>
	<!--  <tfoot>
		<tr>
			<td colspan="19">
				<div class="grayr"><a href="/test/pageQuery.action?nowPage=0&flag=${flag}">�ס�ҳ</a>
				<a href="/test/pageQuery.action?nowPage=${nowPage-1}&flag=${flag}">��һҳ</a>
				<a href="/test/pageQuery.action?nowPage=${nowPage+1}&flag=${flag}">��һҳ
				</a><a href="/test/pageQuery.action?nowPage=${lastPage}&flag=${flag}">ĩ��ҳ</a></div>
			</td>
		</tr>
	</tfoot>-->
</table>
<script type="text/javascript">
<!--
var Ptr=document.getElementById("tab").getElementsByTagName("tr");
function $() {
    for (i=1;i<Ptr.length+1;i++) { 
    Ptr[i-1].className = (i%2>0)?"t1":"t2"; 
    }
}
window.onload=$;
for(var i=0;i<Ptr.length;i++) {
    Ptr[i].onmouseover=function(){
    this.tmpClass=this.className;
    this.className = "t3";
    
    };
    Ptr[i].onmouseout=function(){
    this.className=this.tmpClass;
    };
}
//-->
</script>
</body>
</html>