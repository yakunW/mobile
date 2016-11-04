<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.szh.struts2.bean.Data" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/colResizable-1.3.min.js"></script>
<style type="text/css">
<!--
*{ margin:0; padding:0;}
.box{ border:1px solid #999999; width:100%;  margin-bottom:2px; margin-top:1px; overflow:hidden;}
-->
</style>
<title>环节细分</title>
<script type="text/javascript">
   function wyzbf(i){//千万注意这个名称不能是wyzb，和其它对象重名了，因而不能执行。
	   
	   var xg=new Number($("#xzgs"+i).val());
	   var bl=new Number($("#bili"+i).val());
	   var sumvalue=new Number(xg*bl);
	   $("#wyzb"+i).attr("value",sumvalue);
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
	
		$("#xfen").colResizable({
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
     	 List<Data> dlist = new ArrayList<Data>();
         dlist = (List<Data>)request.getAttribute("xdata");
         String id=(String)session.getAttribute("id");//数据库data表中的id值
         session.setAttribute("id", id);
         int dn = 0;

         dn=dlist.size();
         int upcount=0;
         upcount=(Integer)session.getAttribute("upcount");
         
         String jdxs=(String)request.getAttribute("jdxs");
         String jishu=(String)request.getAttribute("jishu");
         String xs1=(String)request.getAttribute("xs1");
         String xs2=(String)request.getAttribute("xs2");
         String xs3=(String)request.getAttribute("xs3");
         String chanpin=(String)request.getAttribute("xfchanpin");
         String fenlei=(String)request.getAttribute("fenlei");
         
 %>  

<form action="hesuan.action" method="post">
<div class="box" >
   <input type="hidden" name="xfchanpin" value="<%=chanpin%>"></input>
   <input type="hidden" name="fenlei" value="<%=fenlei%>"></input>
   <span> 
   <table border="1" >
      <tr>
        <th width="100" ><font style="font-weight:bolder">阶段系数</font></th>
        <th width="100" ><font style="font-weight:bolder">基数</font></th>
        <th width="100" ><font style="font-weight:bolder">规模调整系数</font></th>
        <th width="100" ><font style="font-weight:bolder">技术调整系数</font></th>
        <th width="100" ><font style="font-weight:bolder">地区调整系数</font></th>
      </tr>
      <tr>
          <td><textarea rows="1" cols="10" name="jdxs" style="font-size:16px;font-weight:bold;"><%=jdxs%></textarea></td> 
          <td><textarea rows="1" cols="10" name="jishu" style="font-size:16px;font-weight:bold;"><%=jishu%></textarea></td>      
          <td><textarea rows="1" cols="10" name="xs1" style="font-size:16px;font-weight:bold;"><%=xs1%></textarea></td>
          <td><textarea rows="1" cols="10" name="xs2" style="font-size:16px;font-weight:bold;"><%=xs2%></textarea></td>
          <td><textarea rows="1" cols="10" name="xs3" style="font-size:16px;font-weight:bold;"><%=xs3%></textarea></td>          
      </tr>
   </table>
   </span>
   <span  style="position:absolute;right:5px;top:10px;magin:20px;">
   <input type="submit" name="工时合计" value="工时求和" style="width:100px;font-size:15px;font-weight:bolder;"></input>
   <input type="button" name="系数查看" value="系数查看" onclick="window.open('xishu.action?chanpin=<%=java.net.URLEncoder.encode(chanpin,"GBK")%>','newwindow', 'height=400, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;"/>
   </span>
</div>
<div class="box">
   <table id="xfen" width="100%" border="1" cellspacing="0" >
     <tr style="background-color:blue">
     <th width="80" ><font style="font-weight:bold">分类</font></th>
     <th width="150" ><font style="font-weight:bold">产品</font></th>
     <th width="120" ><font style="font-weight:bold">工作环节</font></th>
     <th width="180" ><font style="font-weight:bold">环节细分</font></th>
     <th width="60" ><font style="font-weight:bold">工时细分</font></th>
     <th width="60" ><font style="font-weight:bold">修正工时细分</font></th>
     <th width="60" ><font style="font-weight:bold">比例系数细分</font></th>
     <th width="60" ><font style="font-weight:bold">我院占比细分</font></th>
      </tr>
     <%for(int i=0;i<dn;i++){ %> 
     <tr>   
     <input type="hidden" name="xfid" value="<%=dlist.get(i).getId() %>"/>
     <td width="80" ><textarea  cols="80" name="fenlei" style="font-size:16px;"> <%=dlist.get(i).getFenlei()%></textarea> </td>  
     <td width="150" ><textarea cols="80" name="chanpin" style="font-size:16px;"> <%=dlist.get(i).getChanpin()%></textarea></td>
     <td width="120" ><textarea cols="80" name="hj" style="font-size:16px;"> <%=dlist.get(i).getHj() %></textarea></td>
     <td width="180" ><textarea cols="80" name="xf" style="font-size:16px;"> <%=dlist.get(i).getXf()%></textarea></td>
     <td width="60" ><input type="text" name="gsxf" value="<%=dlist.get(i).getGsxf() %>" readonly style="width:50px; "/></td>
     <td width="60" ><input type="text" name="xzgsxf"  id="xzgs<%=i%>"  value="<%=dlist.get(i).getXzgsxf()%>"   style="width:50px; "/></td>
     <td width="60"><input type="text" name="bili"  id="bili<%=i%>"  value="<%=dlist.get(i).getBlxsxf() %>"          onchange="wyzbf(<%=i%>);"  style="width:50px; "/></td>
     <td width="60"><input  type="text" name="wyzb"  id="wyzb<%=i%>"  value="<%=dlist.get(i).getGs()%>"   style="width:50px; "></input></td>

     </tr>
     
     <%

       }
       
        session.setAttribute("upcount", upcount);
     %>           
   </table>
</div>


</form>

</body>
</html>