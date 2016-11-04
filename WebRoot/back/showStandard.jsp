<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="com.wyk.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>标准数据显示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
	List<Standard> standardList=new ArrayList<Standard>();
    standardList=(List<Standard>)session.getAttribute("standardList");
    List<String> zhuleiList=new ArrayList<String>();
    zhuleiList=(List<String>)session.getAttribute("zhuleiList");
    List<String> fenleiList=new ArrayList<String>();
    fenleiList=(List<String>)session.getAttribute("fenleiList");
    List<String> xiaoleiList=new ArrayList<String>(); 
    xiaoleiList=(List<String>)session.getAttribute("xiaoleiList");
    List<String> fenxiangList=new ArrayList<String>();
    fenxiangList=(List<String>)session.getAttribute("fenxiangList");
    List<String> chanpinList=new ArrayList<String>(); 
    chanpinList=(List<String>)session.getAttribute("chanpinList");    
    
	 %>
	
	
   <script language="javascript" type="text/javascript"> 
var   flag=1; 
function   selectAll(){ 
if(flag==1){ 
if(common_delete.selectdelete.length!=undefined){ 
for   (var   i=0;i <common_delete.selectdelete.length;i++) 
common_delete.selectdelete[i].checked=true; 
}else{ 
common_delete.selectdelete.checked=true; 
} 
flag=0; 
}else{ 
if(common_delete.selectdelete.length!=undefined){ 
for   (var i=0;i <common_delete.selectdelete.length;i++) 
common_delete.selectdelete[i].checked=false; 
}else{ 
common_delete.selectdelete.checked=false; 
} 
flag=1; 
} 
} 
     </script>
  </head>
  
  <body>
  
   <form name="search" action="searchStandard.action" method="post" >
   <table  border=1>
   <tr>
   <td width="10%" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">主类</font>    
        <select name="zhulei">
        <option></option>
       <%for(int i=0;i<zhuleiList.size();i++){ %>
       <option><%=zhuleiList.get(i)%></option>
       <%} %>
        </select> 
   </td>
   <td width="10%" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">分类</font>
       <select name="fenlei">
       <option></option>
      <%for(int i=0;i<fenleiList.size();i++){ %>
      <option><%=fenleiList.get(i) %></option>
      <%} %>
       </select>
   </td>
   <td width="12%" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">小类</font>
   
       <select name="xiaolei">
       <option></option> 
       <%for(int i=0;i<xiaoleiList.size();i++){%>
       <option><%=xiaoleiList.get(i)%></option>
       <%} %>
       </select>
   </td>
   <td width="25%" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">分项</font>
   
       <select name="fenxiang">
       <option></option>
       <%for(int i=0;i<fenxiangList.size();i++){%>
       <option><%=fenxiangList.get(i)%></option>
       <%} %>
       </select>
   </td>
   <td width="25%" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">产品</font>
   
   <select name="chanpin">
   <option></option>
   <%for(int i=0;i<chanpinList.size();i++){%>
       <option><%=chanpinList.get(i)%></option>
       <%} %>
   </select>
   </td >
   <td align="center"  width="5%" bordercolor="#FFFFFF"> <input type="submit" value="查询" style="width:70px;height:35px"/></td>
   </tr>
   </table>
   </form>
   
   <form name="common_delete" action="deleteStandard.action" method="post" >
   <table>
   <tr>
   <td><input type="button" name= "selall" value= "全选/反选 " onclick= "selectAll();" /></td>  
    <td> <input type="submit" value="删    除" onclick="return confirm('你确定要删除所选吗 ？')"   /></td> 
    <td><s:submit value="显示全部" action="showStandard"  ></s:submit></td> 
    
   </tr>
   </table>
  
 
   <table id="tableStandard" border="2" >
   <tr>
     <td width="5%" ><font style="font-weight:bolder">批量操作</font></td>
     <td width="10%" ><font style="font-weight:bolder">序号</font></td>
     <td width="10%" ><font style="font-weight:bolder">主类</font></td>
     <td width="10%" ><font style="font-weight:bolder">分类</font></td>
     <td width="10%" ><font style="font-weight:bolder">小类</font></td>
     <td width="10%" ><font style="font-weight:bolder">分项</font></td>
     <td width="10%" ><font style="font-weight:bolder">产品</font></td>
     <td width="10%" ><font style="font-weight:bolder">查看环节细分和系数</font></td>
     <td width="10%" ><font style="font-weight:bolder">产品包含的常规专业</font></td>
     <td width="10%" ><font style="font-weight:bolder">计量单位</font></td>
     <td width="10%" ><font style="font-weight:bolder">总工时</font></td>
     <td width="10%" ><font style="font-weight:bolder">基数</font></td> 
   </tr>
    
     <%for(int i=0;i<standardList.size();i++){ %>
     <tr>
     <td><input type="checkBox" name="selectdelete" value=<%=standardList.get(i).getId()%>></td>
     <td><%=standardList.get(i).getId() %></td>
     <td><%=standardList.get(i).getZhulei() %></td>
     <td><%=standardList.get(i).getFenlei() %></td>
     <td><%=standardList.get(i).getXiaolei() %></td>
     <td><%=standardList.get(i).getFenxiang() %></td>
     <td><%=standardList.get(i).getChanpin()%></td>
     <td>
     <input type="button" value="环节细分查看"   onclick="window.open('showXf.action?id=<%=java.net.URLEncoder.encode(String.valueOf(standardList.get(i).getId()),"GBK")%>','<%=standardList.get(i).getId() %>', 'height=400, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="font-weight:bold;"/> 
     <input type="button" value="系数查看"  onclick="window.open('showXs.action?id=<%=java.net.URLEncoder.encode(String.valueOf(standardList.get(i).getId()),"GBK")%>','<%=standardList.get(i).getId() %>','height=250, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')"  style="width:100px;font-size:15px;font-weight:bold;"/>
     </td>
     <td><%=standardList.get(i).getZhuanye() %></td>
     <td><%=standardList.get(i).getJiliang() %></td>
     <td><%=standardList.get(i).getZgs() %></td>
     <td><%=standardList.get(i).getGuimo() %></td>
     </tr>
    <%} %>
   </table>
   </form>
    
  </body>
</html>
