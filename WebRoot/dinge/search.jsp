<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="com.szh.struts2.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<script type="text/javascript" src="../js/Calendar3.js"></script> 
<script type="text/javascript"   src="../js/chart.js "> </script> 
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/colResizable-1.3.min.js"></script>
 <script type="text/javascript" >

 function xifen1(){
	 	 
	 document.searchform.action='xifen.action?cpid='+cpid+'&i='+i;
	 
	 document.searchform.submit();
 }
</script>	
<title>�鿴���ݿ��е�����</title>
<script type="text/javascript">
$(function(){
	$("#tabledata tr").each(function(){
		var gsvalue=$("#gsvalue").text();
		var sss=$(this).children().eq(25);
		var s1=$(this).children().eq(15);
		var s2=$(this).children().eq(16);
		//alert(sss.text());
		if(sss.text()=="0.0"){
			$(this).children().eq(13).css("background-color","red");
			$(this).children().eq(15).children().css("background-color","red");
			$("#errTip").empty();
			$("#errTip").append('<font style="font-weight:bolder;color:#ff0060;">ע�⣺����������ļ�¼��������˲飡</font>');
			}
		
	});
	
});
</script>
 <script language="javascript" type="text/javascript"> 
 
$(document).ready(checknull);

  function checknull(){
	 var objTable = document.getElementById("tabledata"); 
     var objTR;
     var objTD; 
     var objInput;
     for(var i=1; i< objTable.rows.length ; i++)
     {
       objTR =  objTable.rows[i];
        for(var j=1; j<objTR.cells.length; j++)
        {
           objTD = objTR.cells[j];
           objInput = objTD.childNodes[0];                
           if(objInput.value == "")
           {
        	  document.getElementById("tabledata").rows[i].cells[j].style.borderLeftColor="red";
        	  document.getElementById("tabledata").rows[i].cells[j].style.borderRightColor="red";
        	  document.getElementById("tabledata").rows[i].cells[j].style.borderTopColor="red";
        	  document.getElementById("tabledata").rows[i].cells[j].style.borderBottomColor="red";
        	 // style.borderLeftColor="pink";
        	 // alert('��ȷ�ϱ����������д������');
             // objInput.focus(); 
             // event.returnValue = false; 
             // return; 
           }
         }
       }	
}



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
<script type="text/javascript">
$(
	function()
	{	
		var onSampleResized = function(e)
		{
			var columns = $(e.currentTarget).find("th");
		};	
	
		$("#tabledata").colResizable({
			liveDrag:true, 
			gripInnerHtml:"<div class='grip'></div>", 
			draggingClass:"dragging", 
			onResize:onSampleResized});
		
	}
);	
</script>
</head>
<body>
<%
     List<String> nameList=new ArrayList<String>();
     nameList=(List<String>)session.getAttribute("nameList");
     
     List<Data> searchList=new ArrayList<Data>();
     searchList=(List<Data>)session.getAttribute("searchList");
     session.setAttribute("searchList", searchList);

%>
<form  action="schagain.action" method="post" name="searchform">
   <table width="100%" border="0" >
    <tr>
     <td width="80" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">��ʼ����</font></td>   
     <td>
       <input name="date1" type="text" id="date" onclick="calendar.show(this);" size="10" maxlength="10" readonly="readonly" value=""/>
     </td> 
     <td width="80" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">��ֹ����</font></td> 
     <td>
      <input name="date2" type="text" id="date" onclick="calendar.show(this);" size="10" maxlength="10" readonly="readonly" value=""/>
     </td>   
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">����</font></td> 
        <td width="150">
        <select size="1"  name="zhulei"  class="zhulei" id="zhulei" >
        <option></option>
        <option>��ͳҵ��</option>
        <option>�ۺ���ѯ</option>
        </select>
        </td>
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">����</font></td> 
        <td width="150">
        <select size="1"  name="fenlei"  class="fenlei" id="fenlei" >
        <option></option>
        <option>�������</option>
        <option>�ۺ���ѯ</option>
        </select>
        </td>
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">����</font></td> 
        <td width="180">
        <select size="1"  name="fenxiang"  class="fenxiang" id="fenxiang" >
        <option></option>
        <option>������Դ</option>
        <option>��վ��Դ</option>
        <option>���ߴ���վ��Դ</option>
        <option>�����������</option>
        </select>
        </td>         
        <td width="100">       
        </td>        
    </tr>
    <tr>    
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">��Ʒ</font></td>
        <td width="180">
        <select size="1"  name="chanpin"  class="chanpin" id="chanpin" >
        <option></option>                                  
	   <%for(int i=0;i<nameList.size();i++){%>   
	    <option><%= nameList.get(i) %></option>
		<%} %>
        </select>
        </td>
        <td><input type="submit" name="submit" value="����" style="width:50px;font-size:15px;font-weight:bolder;"/>
        </td>   
         <td height=20>
         <input type="button" name="����Excel" value="����Excel" onclick="window.open('download.jsp?exportname=searchList','newwindow', 'height=400, width=600,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;"/>
         </td>     
        <td></td>       
   </tr>
  
  </table>
 </form> 
 
 
 <form name="common_delete" action="deleteData.action" method="post">
 <div>
 <table>
  <tr> 
    <td  align="center"><input type="button" name= "selall" value= "ȫѡ/��ѡ " onclick= "selectAll();" style="width:100px;font-size:15px;font-weight:bolder;"/></td>  
    <td > <input type="submit" value="ɾ    ��" onclick="return confirm('��ȷ��Ҫɾ����ѡ�� ��')" style="width:80px;font-size:15px;font-weight:bolder;"/></td> 
  </tr>
 </table>
 </div>
 <table id="tabledata" width="100%" border="2" cellpadding="0" cellspacing="0">
    <tr>
     <th width="20" ><font style="font-weight:bolder">��������</font></th>
     <th width="80" ><font style="font-weight:bolder">��Ŀ���</font></th>
     <th width="80" ><font style="font-weight:bolder">��Ŀ����</font></th>
     <th width="80" ><font style="font-weight:bolder">��Ŀʡ��</font></th>
     <th width="80" ><font style="font-weight:bolder">���赥λ</font></th>
     <th width="80" ><font style="font-weight:bolder">ҵ�����������/��Ժ��</font></th>
     <th width="80" ><font style="font-weight:bolder">�ƻ�����</font></th>
     <th width="80" ><font style="font-weight:bolder">�ͻ�����</font></th>
     <th width="80" ><font style="font-weight:bolder">��Ŀ������</font></th>
     <th width="80" ><font style="font-weight:bolder">��רҵ</font></th>
     <th width="80" ><font style="font-weight:bolder">��ƽ׶�</font></th>
     <th width="80" ><font style="font-weight:bolder">����</font></th>
     <th width="80" ><font style="font-weight:bolder">����</font></th>
     <th width="80" ><font style="font-weight:bolder">С��</font></th>
     <th width="80" ><font style="font-weight:bolder">����</font></th>
     <th width="120" ><font style="font-weight:bolder">��Ʒ</font></th>
     <th width="80" ><font style="font-weight:bolder">�鿴����ϸ��</font></th>
     
     

     <th width="80" ><font style="font-weight:bolder">��Ʒ�����ĳ���רҵ</font></th>
     <th width="80" ><font style="font-weight:bolder">������λ</font></th>
     <th width="80" ><font style="font-weight:bolder">�׶�ϵ��</font></th>
     <th width="80" ><font style="font-weight:bolder">����</font></th>
     <th width="80" ><font style="font-weight:bolder">��ģ����ϵ��</font></th>
     <th width="80" ><font style="font-weight:bolder">��������ϵ��</font></th>
     <th width="80" ><font style="font-weight:bolder">��������ϵ��</font></th>
     <th width="80" ><font style="font-weight:bolder">��׼��ʱ</font></th>
     <th width="80" ><font style="font-weight:bolder">������ʱ</font></th>
     <th width="80" ><font style="font-weight:bolder">���۹�ʱ</font></th>
     <th width="80" ><font style="font-weight:bolder">��Ժ��ʱ</font></th>
     <th width="80" ><font style="font-weight:bolder">������ʱ</font></th>
     <th width="80" ><font style="font-weight:bolder">����ʱ��</font></th>
     </tr>
     
     <%     
     for(int i=0;i<searchList.size();i++){
    	 
    %>
        <tr>
          <td> <input type="checkbox" name="selectdelete" value="<%=searchList.get(i).getId() %>"/></td>       
          <td><%=searchList.get(i).getXid() %></td>
          <td><%=searchList.get(i).getName() %></td>
          <td><%=searchList.get(i).getProvince() %></td>
          <td><%=searchList.get(i).getDanwei() %></td>
          <td><%=searchList.get(i).getYewu() %></td>
          <td><%=searchList.get(i).getJmanager() %></td>
          <td><%=searchList.get(i).getKmanager() %></td>
          <td><%=searchList.get(i).getFzr() %></td>
          <td><%=searchList.get(i).getZzy() %></td>
          <td><%=searchList.get(i).getSjjd() %></td>
          <td><%=searchList.get(i).getZhulei() %></td>
          <td><%=searchList.get(i).getFenlei() %></td>
          <td><%=searchList.get(i).getXiaolei() %></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=searchList.get(i).getFenxiang() %></textarea></td>
          <td><textarea rows="6" cols="50" name="chanpin" style="font-size:16px;"><%=searchList.get(i).getChanpin()%></textarea></td>   
          <td>
              <input type="button" name="�鿴����ϸ��" value="�鿴����ϸ��" onclick="window.open('searchXf.action?id=<%=searchList.get(i).getId()%>','newwindow')"/>             
              <input type="button" name="�洢" value="�洢"        onclick="window.open('save.action?id=<%=searchList.get(i).getId()%>','newwindow')"/>
              <input type="button" name="���Ϊ" value="���Ϊ"        onclick="window.open('saveAs.action?id=<%=searchList.get(i).getId()%>','newwindow')"/>
          </td>
               
          <td id="zhuanye"><textarea rows="6" cols="80" name="" style="font-size:16px;"><%=searchList.get(i).getZhuanye() %></textarea></td>
          <td><%=searchList.get(i).getJiliang() %></td>
          <td><textarea rows="1" cols="50" name="xs1" style="font-size:16px;font-weight:bold;"><%=searchList.get(i).getJdxs() %></textarea></td>
          <td><textarea rows="1" cols="50" name="xs1" style="font-size:16px;font-weight:bold;"><%=searchList.get(i).getJishu() %></textarea></td>
          <td><textarea rows="1" cols="50" name="xs1" style="font-size:16px;font-weight:bold;"><%=searchList.get(i).getXs1() %></textarea></td>
          <td><textarea rows="1" cols="50" name="xs2" style="font-size:16px;font-weight:bold;"><%=searchList.get(i).getXs2() %></textarea></td>
          <td><textarea rows="1" cols="50" name="xs3" style="font-size:16px;font-weight:bold;"><%=searchList.get(i).getXs3() %></textarea></td>
          <td><%=searchList.get(i).getGs() %></td>
          <td><%=searchList.get(i).getXzgs() %></td>
          <td><%=searchList.get(i).getBjgs() %></td>
          <td><%=searchList.get(i).getWygs() %></td>
          <td><%=searchList.get(i).getHzgs() %></td>
          <td><%=searchList.get(i).getDate() %></td>
                  
      </tr>
          <%} %>
  </table>
  </form>
</body>
</html>