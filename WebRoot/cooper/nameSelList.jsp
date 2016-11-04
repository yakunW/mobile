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
			<th>年份</th>
			<th>季度</th>
			<th>公司名称</th>
			<th>综合省</th>
			<th>综合成绩</th>
			<th>传输省</th>
			<th>传输成绩</th>
			<th>无线省</th>
			<th>无线成绩</th>
			<th>交换省</th>
			<th>交换成绩</th>
			<th>数据省</th>
			<th>数据成绩</th>
			<th>电源省</th>
			<th>电源成绩</th>
			<th>土建省</th>
			<th>土建成绩</th>
			<th>网优省</th>
			<th>网优成绩</th>
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
				<div class="grayr"><a href="/test/pageQuery.action?nowPage=0&flag=${flag}">首　页</a>
				<a href="/test/pageQuery.action?nowPage=${nowPage-1}&flag=${flag}">上一页</a>
				<a href="/test/pageQuery.action?nowPage=${nowPage+1}&flag=${flag}">下一页
				</a><a href="/test/pageQuery.action?nowPage=${lastPage}&flag=${flag}">末　页</a></div>
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