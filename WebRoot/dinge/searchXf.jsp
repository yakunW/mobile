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
<script type="text/javascript" >

 function xifen1(){
	 	 
	 document.searchform.action='xifen.action?cpid='+cpid+'&i='+i;
	 
	 document.searchform.submit();
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
	
		$("#xf1").colResizable({
			liveDrag:true, 
			gripInnerHtml:"<div class='grip'></div>", 
			draggingClass:"dragging", 
			onResize:onSampleResized});
		
	}
);	
</script>
<title>环节细分</title>

</head>


<body>
<%     	
     	 List<Data> dlist = new ArrayList<Data>();
         dlist = (List<Data>)request.getAttribute("sxlist");

         int dn = 0;

         dn=dlist.size();
 %>  

<form action="hesuan.action" method="post">
<div>
   <table id="xf1" width="100%" border="1" cellspacing="0" style="position:absolute;left:0px;">
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
       
     %>           
   </table>

</div>

<div>
 <input type="button" name="关闭" value="关闭" onclick="window.close()" style="width:100px;font-size:15px;font-weight:bolder;position:absolute;bottom:0px;right:100px;"></input>
 
</div>

</form>

</body>
</html>