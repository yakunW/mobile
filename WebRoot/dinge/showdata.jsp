<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="com.szh.struts2.bean.*" %>
<%request.setCharacterEncoding("GBK");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<script type="text/javascript" src="../js/Calendar3.js"></script> <!--����������ʾ  -->
<script type="text/javascript"   src="../js/chart.js "> </script> <!--����������ʾ -->
<script type="text/javascript" src="../js/jquery.js"></script><!--����������ʾ������϶�  -->
<script type="text/javascript" src="../js/colResizable-1.3.min.js"></script><!--���ڱ���϶�  -->
<script type="text/javascript" >
 function jiucuo(cpid,i){
	 document.form1.action='jiucuo.action?cpid='+cpid+'&i='+i;
	 document.form1.submit();
 }
 
 function xifen(cpid,i,done){
	 
	 if(done==0)
	 {document.form1.action='xifen.action?cpid='+cpid+'&i='+i;}
	 if(done==1)
     {document.form1.action='xifendone.action?cpid='+cpid+'&i='+i;}
	 document.form1.submit();
 }
</script>	


<script type="text/javascript">
$(function(){
	$("#tabledata tr").each(function(){
		var gsvalue=$("#gsvalue").text();
		var sss=$(this).children().eq(25);
		var s1=$(this).children().eq(15);
		var s2=$(this).children().eq(16);
		//alert(sss.text());
		if(sss.text()=="0.0"){
			$(this).children().eq(12).children().css("background-color","red");
			$(this).children().eq(15).children().css("background-color","red");
			$("#errTip").empty();
			$("#errTip").append('<font style="font-weight:bolder;color:#ff0060;">ע�⣺����������ļ�¼��������˲飡</font>');
			}
	});
});
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
<title>�ϴ����ݶ���</title>
</head>
<body>
<%     	
     	 List<Data> dlist = new ArrayList<Data>();
         dlist = (List<Data>)session.getAttribute("data");
         int dn = 0,x=0,upcount=0;
         dn=dlist.size();
         int count=0;//��¼��������        
         upcount=(Integer)session.getAttribute("upcount");
 %>  
 <div>	
 
 <!-- 
  <form action="chazhao.action" method="post">
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
        <select size="1"  name="etype"  class="Road" id="roadNames" >
        <option></option>
        <option>��ͳҵ��</option>
        <option>�ۺ���ѯ</option>
        </select>
        </td>
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">����</font></td> 
        <td width="150">
        <select size="1"  name="etype"  class="Road" id="roadNames" >
        <option></option>
        <option>�������</option>
        </select>
        </td>
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">����</font></td> 
        <td width="180">
        <select size="1"  name="etype"  class="Road" id="roadNames" >
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
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">��Ʒ����</font></td>
        <td width="180">
        <select size="1"  name="etype"  class="Road" id="roadNames" >
        <option></option>
        <option>ֱ��48V/220V/330V</option>
        <option>��վ��Դ</option>
        <option>���ߴ����Դ</option>
        <option>�ߵ�ѹ�������ϵͳ</option>
        <option>�������ڼ��</option>
        <option>����Դ��̫���ܡ����ܣ�</option>
        <option>����UPS</option>
        <option>�ͻ�����</option>
        </select>
        </td>
        <td><input type="submit" name="submit" value="����" style="width:50px;font-size:15px;font-weight:bolder;"/></td>        
        <td></td>       
   </tr>
  </table>
 </form> 
  -->
 </div>
 
 <div id="errTip">
 </div>
 
 <div>
 <form action="jiucuo.action" method="post">
    <table>
    <tr>
        <td>
          <input type="button" name="jiucuo" value="���ݾ���" style="width:100px;font-size:15px;font-weight:bolder;"/>
        </td>
          <td height=20>
         <input type="button" name="����Excel" value="����Excel" onclick="window.open('downloadup.jsp','newwindow', 'height=400, width=600,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;"/>
         </td>
    </tr>
   </table>
 </form>
 </div>
 
<div>
<form name="form1" action="" method="post"> 
 <table id="tabledata" width="100%" border="2" cellpadding="0" cellspacing="0" >
    <tr>     
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��Ŀ���</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��Ŀ����</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��Ŀʡ��</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">���赥λ</font></th>     
     <th width="80" class="tdTitle"><font style="font-weight:bolder">ҵ�����������/��Ժ��</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">�ƻ�����</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">�ͻ�����</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��Ŀ������</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��רҵ</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��ƽ׶�</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">����</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">����</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">С��</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">����</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��Ʒ</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">�鿴����ϸ����ϵ��</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��Ʒ�����ĳ���רҵ</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">������λ</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">�׶�ϵ��</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">����</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��ģ����ϵ��</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��������ϵ��</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��������ϵ��</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��׼����</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��������</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">���۹�ʱ</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">��Ժ��ʱ</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">������ʱ</font></th>
     </tr>
     
     <%     
     for(int i=0;i<dn;i++){
    	
    %>
     <tr > 
        
           <input type="hidden" name="id" value="<%=dlist.get(i).getId()%>"></input>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getXid() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getName() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getProvince() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getDanwei() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getYewu() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getJmanager() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getKmanager() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getFzr() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getZzy() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getSjjd() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getZhulei() %></textarea></td>
  
          <td><textarea class="turnRed" rows="6" cols="50" name="fenlei" style="font-size:16px;"><%=dlist.get(i).getFenlei() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getXiaolei() %></textarea></td>
          <td><textarea rows="6" cols="50" name="" style="font-size:16px;"><%=dlist.get(i).getFenxiang() %></textarea></td>          
          <td><textarea class="turnRed" rows="6" cols="80" name="chanpin" style="font-size:16px;"><%=dlist.get(i).getChanpin()%></textarea></td>
          
          <input type="hidden" name="upcount" value="<%=upcount%>"></input>
          <td>
             <input type="button" name="���ݾ���"     value="���ݾ���"     onclick="jiucuo(<%=dlist.get(i).getId() %>,<%=i %>)" style="font-weight:bold;"/>
             <input type="button" name="����ϸ�ֲ鿴" value="����ϸ�ֲ鿴" onclick="xifen(<%=dlist.get(i).getId() %>,<%=i %>,<%=dlist.get(i).getDone() %>)" style="font-weight:bold;"/>
             <input type="button" name="ϵ���鿴"     value="ϵ���鿴"     onclick="window.open('xishu.action?chanpin=<%=java.net.URLEncoder.encode(dlist.get(i).getChanpin(),"GBK")%>&fenlei=<%=java.net.URLEncoder.encode(dlist.get(i).getFenlei(),"GBK")%>','newwindow', 'height=400, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;"/>
          </td>
       
          <td><textarea rows="6" cols="80" name="" style="font-size:16px;"><%=dlist.get(i).getZhuanye() %></textarea></td>
          <td><%=dlist.get(i).getJiliang() %></td>
          <td><textarea rows="1" cols="50" name="jdxs" style="font-size:16px;font-weight:bold;"><%=dlist.get(i).getJdxs() %></textarea></td>
          <td><textarea rows="1" cols="50" name="jishu" style="font-size:16px;font-weight:bold;"><%=dlist.get(i).getJishu() %></textarea></td>
          <td><textarea rows="1" cols="50" name="xs1" style="font-size:16px;font-weight:bold;"><%=dlist.get(i).getXs1() %></textarea></td>
          <td><textarea rows="1" cols="50" name="xs2" style="font-size:16px;font-weight:bold;"><%=dlist.get(i).getXs2() %></textarea></td>
          <td><textarea rows="1" cols="50" name="xs3" style="font-size:16px;font-weight:bold;"><%=dlist.get(i).getXs3() %></textarea></td>
       
          <td id="gsvalue"><%=dlist.get(i).getGs() %></td>
          <td><%=dlist.get(i).getXzgs() %></td>
          <td><%=dlist.get(i).getBjgs() %></td>
          <td><%=dlist.get(i).getWygs() %></td>
          <td><%=dlist.get(i).getHzgs() %></td>
                  
      </tr>
          <%} %>
   </table>
</form>
</div>
</body>
</html>