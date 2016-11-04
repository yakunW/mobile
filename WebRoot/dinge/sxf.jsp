<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.szh.struts2.bean.Data" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<%     	
     	 List<Data> dlist = new ArrayList<Data>();
         dlist = (List<Data>)request.getAttribute("sxlist");
         String id=(String)request.getAttribute("cpid");
         

         int dn = 0;

         dn=dlist.size();
                
 %>  

<form action="hesuan.action" method="post">
<div>
   <table border="1" cellspacing="0" style="position:absolute;left:0px;">
     <tr style="background-color:blue">
     <td width="80" ><font style="font-weight:bold">分类</font></td>
     <td width="150" ><font style="font-weight:bold">产品</font></td>
     <td width="120" ><font style="font-weight:bold">工作环节</font></td>
     <td width="180" ><font style="font-weight:bold">环节细分</font></td>
     <td width="60" ><font style="font-weight:bold">工时细分</font></td>
     <td width="60" ><font style="font-weight:bold">修正工时细分</font></td>
     <td width="60" ><font style="font-weight:bold">比例系数细分</font></td>
     <td width="60" ><font style="font-weight:bold">我院占比细分</font></td>
      </tr>
     <%for(int i=0;i<dn;i++){ %> 
     <tr>   
     <input type="hidden" name="id" value="<%=id %>"/>
     <input type="hidden" name="xfid" value="<%=dlist.get(i).getId() %>"/>
     <td width="80" ><textarea  cols="8" name="fenlei" style="font-size:16px;"> <%=dlist.get(i).getFenlei()%></textarea> </td>  
     <td width="150" ><textarea cols="8" name="chanpin" style="font-size:16px;"> <%=dlist.get(i).getChanpin()%></textarea></td>
     <td width="120" ><textarea cols="8" name="hj" style="font-size:16px;"> <%=dlist.get(i).getHj() %></textarea></td>
     <td width="180" ><textarea cols="8" name="xf" style="font-size:16px;"> <%=dlist.get(i).getXf()%></textarea></td>
     <td width="60" ><input type="text" name="gsxf" value="<%=dlist.get(i).getGsxf() %>" readonly style="width:50px; "/></td>
     <td width="60" ><input type="text" name="xzgsxf"  id="xzgs<%=i%>"  value="<%=dlist.get(i).getGsxf() %>"   style="width:50px; "/></td>
     <td width="60"><input type="text" name="bili"  id="bili<%=i%>"  value="1"          onchange="wyzbf(<%=i%>);"  style="width:50px; "/></td>
     <td width="60"><input  type="text" name="wyzb"  id="wyzb<%=i%>"  value="<%=dlist.get(i).getGs()%>"   style="width:50px; "></input></td>

     </tr>
     
     <%

       }
       
     %>           
   </table>

</div>

<div>
 <input type="submit" name="提交" value="提交"  style="width:100px;font-size:15px;font-weight:bolder;position:absolute;bottom:0px;right:100px;"></input>
 
</div>

</form>


</body>
</html>