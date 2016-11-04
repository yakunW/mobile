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
<script type="text/javascript" src="../js/Calendar3.js"></script> <!--用于日历显示  -->
<script type="text/javascript"   src="../js/chart.js "> </script> <!--用于日历显示 -->
<script type="text/javascript" src="../js/jquery.js"></script><!--用于日历显示、表格拖动  -->
<script type="text/javascript" src="../js/colResizable-1.3.min.js"></script><!--用于表格拖动  -->
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
			$("#errTip").append('<font style="font-weight:bolder;color:#ff0060;">注意：存在有问题的记录，请认真核查！</font>');
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
<title>上传数据定额</title>
</head>
<body>
<%     	
     	 List<Data> dlist = new ArrayList<Data>();
         dlist = (List<Data>)session.getAttribute("data");
         int dn = 0,x=0,upcount=0;
         dn=dlist.size();
         int count=0;//记录出错条数        
         upcount=(Integer)session.getAttribute("upcount");
 %>  
 <div>	
 
 <!-- 
  <form action="chazhao.action" method="post">
   <table width="100%" border="0" >
    <tr>
     <td width="80" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">起始日期</font></td>   
     <td>
       <input name="date1" type="text" id="date" onclick="calendar.show(this);" size="10" maxlength="10" readonly="readonly" value=""/>
     </td> 
     <td width="80" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">截止日期</font></td> 
     <td>
      <input name="date2" type="text" id="date" onclick="calendar.show(this);" size="10" maxlength="10" readonly="readonly" value=""/>
     </td>   
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">主类</font></td> 
        <td width="150">
        <select size="1"  name="etype"  class="Road" id="roadNames" >
        <option></option>
        <option>传统业务</option>
        <option>综合咨询</option>
        </select>
        </td>
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">分类</font></td> 
        <td width="150">
        <select size="1"  name="etype"  class="Road" id="roadNames" >
        <option></option>
        <option>工程设计</option>
        </select>
        </td>
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">分项</font></td> 
        <td width="180">
        <select size="1"  name="etype"  class="Road" id="roadNames" >
        <option></option>
        <option>机房电源</option>
        <option>基站电源</option>
        <option>干线传输站电源</option>
        <option>动力环境监控</option>
        </select>
        </td>
           
        <td width="100">       
        </td>        
    </tr>
    <tr>    
        <td width="50" height="20" bordercolor="#FFFFFF"><font style="font-weight:bolder">产品类型</font></td>
        <td width="180">
        <select size="1"  name="etype"  class="Road" id="roadNames" >
        <option></option>
        <option>直流48V/220V/330V</option>
        <option>基站电源</option>
        <option>干线传输电源</option>
        <option>高低压、变配电系统</option>
        <option>动力环节监控</option>
        <option>新能源（太阳能、风能）</option>
        <option>交流UPS</option>
        <option>油机机组</option>
        </select>
        </td>
        <td><input type="submit" name="submit" value="查找" style="width:50px;font-size:15px;font-weight:bolder;"/></td>        
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
          <input type="button" name="jiucuo" value="数据纠错" style="width:100px;font-size:15px;font-weight:bolder;"/>
        </td>
          <td height=20>
         <input type="button" name="导出Excel" value="导出Excel" onclick="window.open('downloadup.jsp','newwindow', 'height=400, width=600,, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;"/>
         </td>
    </tr>
   </table>
 </form>
 </div>
 
<div>
<form name="form1" action="" method="post"> 
 <table id="tabledata" width="100%" border="2" cellpadding="0" cellspacing="0" >
    <tr>     
     <th width="80" class="tdTitle"><font style="font-weight:bolder">项目编号</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">项目名称</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">项目省区</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">建设单位</font></th>     
     <th width="80" class="tdTitle"><font style="font-weight:bolder">业务大区（大区/分院）</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">计划经理</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">客户经理</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">项目负责人</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">主专业</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">设计阶段</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">主类</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">分类</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">小类</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">分项</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">产品</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">查看环节细分与系数</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">产品包含的常规专业</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">计量单位</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">阶段系数</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">基数</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">规模调整系数</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">技术调整系数</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">地区调整系数</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">标准定额</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">调整定额</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">报价工时</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">我院工时</font></th>
     <th width="80" class="tdTitle"><font style="font-weight:bolder">合作工时</font></th>
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
             <input type="button" name="数据纠错"     value="数据纠错"     onclick="jiucuo(<%=dlist.get(i).getId() %>,<%=i %>)" style="font-weight:bold;"/>
             <input type="button" name="环节细分查看" value="环节细分查看" onclick="xifen(<%=dlist.get(i).getId() %>,<%=i %>,<%=dlist.get(i).getDone() %>)" style="font-weight:bold;"/>
             <input type="button" name="系数查看"     value="系数查看"     onclick="window.open('xishu.action?chanpin=<%=java.net.URLEncoder.encode(dlist.get(i).getChanpin(),"GBK")%>&fenlei=<%=java.net.URLEncoder.encode(dlist.get(i).getFenlei(),"GBK")%>','newwindow', 'height=400, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;"/>
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