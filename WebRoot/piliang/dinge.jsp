<!-- author:xuxiaoyin -->
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.xxy.struts2.dao.LiebiaoDB" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-latest.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��������</title>
<!--  <script type="text/javascript">
window.onbeforeunload=function()
{
	if(is_form_changed())
		{
		return "are you sure?";
		}
}
function is_form_changed()
{
	if(t_save.length>0)
		{
		var is_changed=false;
		jQuery("#A4 input").each(function()
				{
			var _v=jQuery(this).attr('_value');
			if(typeof(_v)=='undefined') _v='';
				
		if(_v!=jQuery(this).val()) is_changed=true;
				}
		);
		return is_changed;
		}
	return false;
}
jQuery(document).ready(function(){
	jQuery()("#A4 input").each(function(){
		jQuery(this).attr('_value',jQuery(this).val());
	});
});
</script> -->
<script type="text/javascript">
window.onbeforeunload=function(){
if($("#xid").val()!=""){
//event.returnValue = "�����뿪ҳ�棬�Ƿ���Ĳ����棿";
//if(confirm("�뿪ҳ��ǰ�뱣������")){return true;}
//else return false;
return "��ȷ�ϱ�������";
}
}
</script>

<script type="text/javascript">

var xmlHttp;
var i=0;
var delrow=0;
function createXMLHttpRequest() {
if (window.ActiveXObject) {
xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest) {
xmlHttp = new XMLHttpRequest();
}
}
function changeZhulei(){
	var zhulei = document.getElementById("zhuleiList").value;
	if(zhulei==""){
		alert("��ѡ��һ������");
		return;
	}
	
	createXMLHttpRequest();
	xmlHttp.onreadystatechange=handleZhuleiChange;
	xmlHttp.open("POST","fenleiJson.action",true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttp.send("zhulei="+encodeURI(encodeURI(zhulei,"utf-8")));
}

function handleZhuleiChange(){
	if(xmlHttp.readyState==4){
	  
	  if(xmlHttp.status==200){
		var flString = eval("("+xmlHttp.responseText+")");
		$("#fenleiList").empty();
		$("#fenleiList").append("<option><--��ѡ�����--></option>");
		for(var i=0;i<flString.lblist.length;i++){
			var fname = flString.lblist[i].name;
			$("#fenleiList").append("<option>"+fname+"</option>");
		}
	  }
	}  
	else{}
}

function changeFenlei(){
	var zhulei = document.getElementById("zhuleiList").value;
	var fenlei = document.getElementById("fenleiList").value;
	if(zhulei==""){
		alert("��ѡ��һ������");
		return;
	}
	if(fenlei==""){
		alert("��ѡ��һ������");
		return;
	}
	
	createXMLHttpRequest();
	xmlHttp.onreadystatechange=handleFenleiChange;
	xmlHttp.open("POST","xiaoleiJson.action",true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttp.send("zhulei="+encodeURI(encodeURI(zhulei,"utf-8"))+
			     "&fenlei="+encodeURI(encodeURI(fenlei,"utf-8")));
}

function handleFenleiChange(){
	if(xmlHttp.readyState==4){
	  
	  if(xmlHttp.status==200){
		
		var xlString = eval("("+xmlHttp.responseText+")");
		$("#xiaoleiList").empty();
		$("#xiaoleiList").append("<option><--��ѡ��С��--></option>");
		for(var i=0;i<xlString.lblist.length;i++){
			var fname = xlString.lblist[i].name;
			$("#xiaoleiList").append("<option>"+fname+"</option>");
		}
	  }
	}  
	else{}
}

function changeXiaolei(){
	var zhulei = document.getElementById("zhuleiList").value;
	var fenlei = document.getElementById("fenleiList").value;
	var xiaolei = document.getElementById("xiaoleiList").value;
	if(zhulei==""){
		alert("��ѡ��һ������");
		return;
	}
	if(fenlei==""){
		alert("��ѡ��һ������");
		return;
	}
	if(xiaolei==""){
		alert("��ѡ��һ��С��");
		return;
	}
	createXMLHttpRequest();
	xmlHttp.onreadystatechange=handleXiaoleiChange;
	xmlHttp.open("POST","fenxiangJson.action",true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttp.send("zhulei="+encodeURI(encodeURI(zhulei,"utf-8"))+
			     "&fenlei="+encodeURI(encodeURI(fenlei,"utf-8"))+
			     "&xiaolei="+encodeURI(encodeURI(xiaolei,"utf-8")));
}

function handleXiaoleiChange(){
	if(xmlHttp.readyState==4){
	  
	  if(xmlHttp.status==200){
		
		var fxString = eval("("+xmlHttp.responseText+")");
		$("#fenxiangList").empty();
		$("#fenxiangList").append("<option><--��ѡ�����--></option>");
		for(var i=0;i<fxString.lblist.length;i++){
			var fname = fxString.lblist[i].name;
			$("#fenxiangList").append("<option>"+fname+"</option>");
		}
	  }
	}  
	else{}
}

function changeFenxiang(){
	var zhulei = document.getElementById("zhuleiList").value;
	var fenlei = document.getElementById("fenleiList").value;
	var xiaolei = document.getElementById("xiaoleiList").value;
	var fenxiang = document.getElementById("fenxiangList").value;
	if(zhulei==""){
		alert("��ѡ��һ������");
		return;
	}
	if(fenlei==""){
		alert("��ѡ��һ������");
		return;
	}
	if(xiaolei==""){
		alert("��ѡ��һ��С��");
		return;
	}
	if(fenxiang==""){
		alert("��ѡ��һ������");
		return;
	}
	createXMLHttpRequest();
	xmlHttp.onreadystatechange=handleFenxiangChange;
	xmlHttp.open("POST","chanpinJson.action",true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttp.send("zhulei="+encodeURI(encodeURI(zhulei,"utf-8"))+
			     "&fenlei="+encodeURI(encodeURI(fenlei,"utf-8"))+
			     "&xiaolei="+encodeURI(encodeURI(xiaolei,"utf-8"))+
			     "&fenxiang="+encodeURI(encodeURI(fenxiang,"utf-8")));
}

function handleFenxiangChange(){
	if(xmlHttp.readyState==4){
	  
	  if(xmlHttp.status==200){
		  
		$("#chanpinList").empty();
		$("#chanpinList").append("<option><--��ѡ���Ʒ--></option>");
		var cpString = eval("("+xmlHttp.responseText+")");
		for(var i=0;i<cpString.lblist.length;i++){
			var fname = cpString.lblist[i].name;
			$("#chanpinList").append("<option>"+fname+"</option>");
		}
	  }
	}  
	else{}
}

function changeChanpin(){
	var chanpin = document.getElementById("chanpinList").value;
	
	if(chanpin==""){
		alert("��ѡ��һ����Ʒ");
		return;
	}
	createXMLHttpRequest();
	xmlHttp.onreadystatechange=listchanpin;
	xmlHttp.open("POST","TchanpinJson.action",true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttp.send("chanpin="+encodeURI(encodeURI(chanpin,"utf-8"))
			     );
}

function listchanpin(){
	if(xmlHttp.readyState==4){
	  
	  if(xmlHttp.status==200){
		
		var tcString = eval("("+xmlHttp.responseText+")");
		var chanpin=document.getElementById("chanpinList").value;
		var zhulei=document.getElementById("zhuleiList").value;
		var fenlei=document.getElementById("fenleiList").value;
		var xiaolei=document.getElementById("xiaoleiList").value;
		var fenxiang=document.getElementById("fenxiangList").value;
	    var jiliang = tcString.tclist[0].jiliang;
	    var zgs = tcString.tclist[0].zgs;
	    var xiangmu=document.getElementById("xid").value;
	    i++;
	   
		$("#chanpin").append("<tr id='"+i+"' name='"+i+"'><td><input type='button' name='delete' value='ɾ��' onclick='deletecp("+i+");'></td><td><input type='text' id='cp' name='cp' value='"+chanpin+"'></td><td><input type='text' id='jiliang' name='jiliang' style='width:60px;' value='"+jiliang+
				"'></td><td><input type='text' id='ji"+i+"' name='ji' value='1' style='width:50px;' onchange='changeji("+i+");'></td>"+
				
				"<td height=20><input type='button' name='xishu' id='xishu"+i+"' onclick=\"window.open('xishu.action?chanpin="+chanpin+"&fenlei="+fenlei+"','newwindow')\" style='width:20px;font-size:15px;font-weight:bold;'></td>"+
				" <td><input type='text' name='guimo' id='guimo"+i+"' value='1' onchange='changeGuimo("+i+");' style='width:50px;'></td><td><input type='text' name='jishu' id='jishu"+i+"' value='1' onchange='changeJishu("+i+");' style='width:50px;'></td><td><input type='text' name='diqu' id='diqu"+i+"' value='1' onchange='changeDiqu("+i+");' style='width:50px;''></td>"+
				"<td height=20 ><input type='button' name='xifen' id='xifen"+i+"' onclick=\"window.open('../piliang/son_xf.jsp?chanpin="+chanpin+"&line="+i+"&xid="+xiangmu+"&fenlei="+fenlei+"','newwindow')\" style='width:20px;font-size:15px;font-weight:bold;'></td>"+
				"<td><font style='font-weight:bold'></font><input type='text' id='biaozhun"+i+"' name='biaozhun' value='"+zgs+"' readonly='readonly' style='width:50px;'/></td><td><font style='font-weight:bold'> </font><input type='text' id='tiaozheng"+i+"' name='tiaozheng' value='"+zgs+"' readonly='readonly' style='width:50px;'/></td>"+
				"<td><font style='font-weight:bold'></font><input type='text' id='baojia"+i+"' name='baojia' value='"+zgs+"' style='width:50px;'/></td> <td><font style='font-weight:bold'> </font><input type='text' id='woyuan"+i+"' name='woyuan' readonly='readonly' value='"+zgs+"' style=width:50px;'/></td>"+
				"<td><font style='font-weight:bold'> </font><input type='text' id='hezuo"+i+"' name='hezuo' value='0' readonly='readonly' style='width:50px;'/></td>"+
				"<td bordercolor='white'><input type='hidden' border='0' id='zhulei' name='zhulei' value='"+zhulei+"'><input type='hidden' border='0' id='fenlei"+i+"' name='fenlei' value='"+fenlei+"'><input type='hidden' border='0' id='xiaolei' name='xiaolei' value='"+xiaolei+"'><input type='hidden' border='0' id='fenxiang' name='fenxiang' value='"+fenxiang+"'></td></tr>");
	  }
	}  
	else{}
}
function deletecp(i){
	if(confirm("ȷ��ɾ����"))
	    $("#"+i).remove();
}

function changeji(i){
	var tiaozheng = new Number($("#tiaozheng"+i).val());
	var jishu = new Number($("#jishu"+i).val());
	var diqu = new Number($("#diqu"+i).val());
	var guimo = new Number($("#guimo"+i).val());
	var jdxs = new Number($("#jdxs").val());
	var ji = new Number($("#ji"+i).val());
	var baojia = new Number(tiaozheng*jishu*diqu*guimo*jdxs*ji);

    $("#baojia"+i).attr("value",baojia);
}

function changeGuimo(i){
	
	var tiaozheng = new Number($("#tiaozheng"+i).val());alert(tiaozheng);
	
	var diqu = new Number($("#diqu"+i).val());alert(diqu);
	var jishu = new Number($("#jishu"+i).val());alert(jishu);
	var guimo = new Number($("#guimo"+i).val());alert(guimo);
	var jdxs = new Number($("#jdxs").val());alert(jdxs);
	var ji = new Number($("#ji"+i).val());alert(ji);
	var baojia = new Number(tiaozheng*jishu*diqu*guimo*jdxs*ji);alert(baojia);
    $("#baojia"+i).attr("value",baojia);
}
function changeJishu(i){
	var tiaozheng = new Number($("#tiaozheng"+i).val());
	var jishu = new Number($("#jishu"+i).val());
	var diqu = new Number($("#diqu"+i).val());
	var guimo = new Number($("#guimo"+i).val());
	var jdxs = new Number($("#jdxs").val());
	var ji = new Number($("#ji"+i).val());
	var baojia = new Number(tiaozheng*jishu*diqu*guimo*jdxs*ji);
	$("#baojia"+i).attr("value",baojia);
}
function changeDiqu(i){
	var tiaozheng = new Number($("#tiaozheng"+i).val());
	var jishu = new Number($("#jishu"+i).val());
	var diqu = new Number($("#diqu"+i).val());
	var guimo = new Number($("#guimo"+i).val());
	var jdxs = new Number($("#jdxs").val());
	var ji = new Number($("#ji"+i).val());
	var baojia = new Number(tiaozheng*jishu*diqu*guimo*jdxs*ji);
	$("#baojia"+i).attr("value",baojia);
}


function zgs(){
	var zgsum = 0;
	for(var i=0;i<document.getElementsByName("baojia").length;i++)
	    zgsum+=new Number(document.getElementsByName("baojia")[i].value);
	$("#xmzgs").attr("value",zgsum);
	
	var wyzgs=0;
	for(var i=0;i<document.getElementsByName("woyuan").length;i++)
		wyzgs+=new Number(document.getElementsByName("woyuan")[i].value);
	$("#wyzgs").attr("value",wyzgs);
	var hzzgs=zgsum-wyzgs;
	$("#hzzgs").attr("value",hzzgs);
}
</script>

</head>

<body>
<%
        List<String> zlstring=LiebiaoDB.queryzl();
       if((zlstring.size()==0)||(zlstring==null))
    	   zlstring = new ArrayList<String>();
       int zlnum = zlstring.size();
%>


<form action="newdata.action" method="post" >
<div style="height:100px;">
 <table  height=80>
 <tr height=30>
   <td>
     <input type="submit" id="save" name="save" value="����"></input>
     </td>
 </tr>
 <tr height=40>
   <td id="A4">
       <font style="font-weight:bold; color:red">* ��Ŀ��ţ�</font><input type="text" id="xid" name="xid" value=""/>
   </td>
   <td>
       <font style="font-weight:bold">��Ŀ���ƣ�</font><input type="text" id="name" name="name"/>
   </td>
   <td>
      <font style="font-weight:bold"> ��Ŀʡ����</font><input type="text" id="province" name="province"/>
   </td>
   <td>
       <font style="font-weight:bold">���赥λ��</font><input type="text" id="danwei" name="danwei"/>
   </td>
   <td>
     <font style="font-weight:bold;"> ҵ�����/��Ժ��</font><input type="text" id="yewu" name="yewu"/>
     
   </td>
   <td>
    <font style="font-weight:bold">�ƻ�����</font><input type="text" id="jmanager" name="jmanager"/>
   </td>
   <td>
   <font style="font-weight:bold"> �ͻ�����</font><input type="text" id="kmanager" name="kmanager"/>
   </td>
   <td>
    <font style="font-weight:bold">�����ˣ�</font><input type="text" id="fzr" name="fzr"/>
   </td>
 </tr>
 
 <tr height=40>

  
   <td>
    <font style="font-weight:bold">��רҵ��</font><input type="text" id="zzy" name="zzy"/>
   </td>
   
   <td>
   <font style="font-weight:bold"> ��ƽ׶Σ�</font><input type="text" id="jieduan" name="jieduan"/>
   </td>
   <td>
    <font style="font-weight:bold;color:red">* �׶�ϵ����</font><input type="text" id="jdxs" name="jdxs" value="1" />
   </td>
    <td>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="�ܹ�ʱ:" onclick="zgs();" style="margin-right:0;"/>
   </td>
   <td>
    <font color=blue style="font-weight:bold">��Ŀ�ܹ�ʱ��</font><input type="text" id="xmzgs" name="xmzgs"/>
    </td>
    <td>
    <font color=blue style="font-weight:bold">��Ժ�ܹ�ʱ��</font><input type="text" id="wyzgs" name="wyzgs"/>
    </td>
   <td>
    <font color=blue style="font-weight:bold">�����ܹ�ʱ��</font><input type="text" id="hzzgs" name="hzzgs"/>
   </td>  
   <td bordercolor="white">
    
   </td>  
 </tr>      
 </table>
</div>
<table height=30>
</table>


 <div id="lb">
  <table border="0"> 
   <tr>
    <td width="300px">
����<font style="font-weight:bold; position:absolute;top:160px; left:12px">����</font>
����<select id="zhuleiList" name="zhuleiList" onchange="changeZhulei();" style="position:absolute;top:160px; left:80px; right:0px; ">
    <option><--��ѡ������--></option>
����<% for(int i=0;i<zlnum;i++) {%>
   <option><%=zlstring.get(i)%></option><% } 
   %>
����</select>
</td>
<td width="300px">
����<font style="font-weight:bold;position:absolute;left:300px;top:160px;">����</font>
����<select id="fenleiList" style="position:absolute; left:380px; right:0px; top:160px;" name="fenleiList" onchange="changeFenlei();">
����</select>
</td>
<td  width="300px">
    <font style="font-weight:bold;position:absolute;left:600px;top:160px;">С��</font>
����<select id="xiaoleiList" style="position:absolute;top:160px; left:680px; right:0px; " name="xiaoleiList" onchange="changeXiaolei();">
����</select>
</td>
   </tr>
   <tr>
   <td  width="400px">
   <font style="font-weight:bold;left:12px;position:absolute; top:190px;">����</font>
����<select id="fenxiangList" name="fenxiangList" style="position:absolute; left:80px; right:0px;top:190px; " onchange="changeFenxiang();">
����</select>
</td>
<td  width="300px">
    <font style="font-weight:bold;left:600px;position:absolute;top:190px;">��Ʒ</font>
   <select id="chanpinList" name="chanpinList" style="left:680px;position:absolute;top:190px;" onchange="changeChanpin();">
     
��</select>
</td>
</tr>
</table>  
</div>
<br>
<br>

<div style="height:20px;width:1000px" >
&nbsp;&nbsp;<font style="font-size:12px;color:red;">ע�⣺������д�׶�ϵ������������ģϵ������������������ϵ��������޸Ļ���ϸ�֣������ܹ�ʱ�󣬵�����漴��~</font>
</div>
    <table id="chanpin" border=1>
    <tr>
    <td style='width:40px;'>ɾ��</td>
    <td style='width:180px;'>��Ʒ</td><td style='width:60px;'>����</td>
    <td style='width:60px;color:red'>* ����</td>
	
	<td height=20 style='width:40px;'>ϵ������</td>
	<td style='width:60px;color:red'>* ��ģϵ��</td>
	<td style='width:60px;color:red'>* ����ϵ��</td>
	<td style='width:60px;color:red'>* ����ϵ��</td>
	<td height=20 style='width:40px;'>����ϸ��</td>
	<td><font style='width:60px;font-weight:bold'>��׼���</font></td>
	<td><font style='width:60px;font-weight:bold'> �������</font></td>
	<td><font style='width:60px;font-weight:bold'>���۹�ʱ��</font></td> 
	<td><font style='width:60px;font-weight:bold'> ��Ժ��ʱ��</font></td>
	<td><font style='width:60px;font-weight:bold'> ������ʱ��</font></td></tr>
	</table>
</form>
</body>
</html>