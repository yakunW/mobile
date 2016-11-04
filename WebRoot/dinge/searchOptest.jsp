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
     	 List<Data> sdatadlist = new ArrayList<Data>();
         List<Data>   dlist = (List<Data>)request.getAttribute("sxlist");
         Data sdata=(Data)request.getAttribute("sdata");
         int dn = 0;
         dn=dlist.size();        
 %>  
 <form action="sxf.action" method="post" name="searchform">
  <table border="1" >
     <tr>
      <td  ><font style="font-weight:bolder">项目编号</font></td>
        <td><textarea rows="2" cols="8" name="data.xid" style="font-size:16px;"><%=sdata.getXid() %></textarea></td>         
      <td  ><font style="font-weight:bolder">项目名称</font></td>
        <td><textarea rows="2" cols="8" name="data.name" style="font-size:16px;"><%=sdata.getName() %></textarea></td>
      <td  ><font style="font-weight:bolder">项目省区</font></td>
        <td><textarea rows="2" cols="8" name="data.province" style="font-size:16px;"><%=sdata.getProvince() %></textarea></td>        
      <td  ><font style="font-weight:bolder">建设单位</font></td>
        <td><textarea rows="2" cols="8" name="data.danwei" style="font-size:16px;"><%=sdata.getDanwei() %></textarea></td>    
      <td  ><font style="font-weight:bolder">业务大区（大区/分院）</font></td>
        <td><textarea rows="2" cols="8" name="data.yewu" style="font-size:16px;"><%=sdata.getYewu() %></textarea></td>    
     </tr>
     <tr>
      <td width="80" ><font style="font-weight:bolder">计划经理</font></td>
         <td><textarea rows="2" cols="8" name="data.jmanager" style="font-size:16px;"><%=sdata.getJmanager() %></textarea></td> 
      <td width="80" ><font style="font-weight:bolder">客户经理</font></td>
         <td><textarea rows="2" cols="8" name="data.kmanager" style="font-size:16px;"><%=sdata.getKmanager() %></textarea></td>        
      <td width="80" ><font style="font-weight:bolder">项目负责人</font></td>
         <td><textarea rows="2" cols="8" name="data.fzr" style="font-size:16px;"><%=sdata.getFzr() %></textarea></td>        
      <td width="80" ><font style="font-weight:bolder">主专业</font></td>
         <td><textarea rows="2" cols="8" name="data.zzy" style="font-size:16px;"><%=sdata.getZzy() %></textarea></td>         
      <td width="80" ><font style="font-weight:bolder">设计阶段</font></td>
         <td><textarea rows="2" cols="8" name="data.sjjd" style="font-size:16px;"><%=sdata.getSjjd() %></textarea></td>       
     </tr>
     <tr>
      <td width="80" ><font style="font-weight:bolder">主类</font></td>
        <td><textarea rows="2" cols="8" name="data.zhulei" style="font-size:16px;"><%=sdata.getZhulei() %></textarea></td>  
      <td width="80" ><font style="font-weight:bolder">分类</font></td>
        <td><textarea rows="2" cols="8" name="data.fenlei" style="font-size:16px;"><%=sdata.getFenlei() %></textarea></td>         
      <td width="80" ><font style="font-weight:bolder">小类</font></td>
        <td><textarea rows="2" cols="8" name="data.xiaolei" style="font-size:16px;"><%=sdata.getXiaolei() %></textarea></td>       
      <td width="80" ><font style="font-weight:bolder">分项</font></td>
        <td><textarea rows="2" cols="8" name="data.fenxiang" style="font-size:16px;"><%=sdata.getFenxiang() %></textarea></td>
      <td width="80" ><font style="font-weight:bolder">产品</font></td>
        <td><textarea rows="2" cols="8" name="data.chanpin" style="font-size:16px;"><%=sdata.getChanpin()%></textarea></td>         
     </tr>
     <tr>
       <td width="60" ><font style="font-weight:bolder">阶段系数</font></td>
         <td><textarea rows="2" cols="8" name="data.jdxs" style="font-size:16px;font-weight:bold;"><%=sdata.getJdxs()%></textarea></td>         
       <td width="60" ><font style="font-weight:bolder">基数</font></td>
         <td><textarea rows="2" cols="8" name="data.jishu" style="font-size:16px;font-weight:bold;"><%=sdata.getJishu()%></textarea></td>                
       <td width="60" ><font style="font-weight:bolder">规模调整系数</font></td>
        <td><textarea rows="2" cols="8" name="data.xs1" style="font-size:16px;font-weight:bold;"><%=sdata.getXs1()%></textarea></td>          
       <td width="60" ><font style="font-weight:bolder">技术调整系数</font></td>
        <td><textarea rows="2" cols="8" name="data.xs2" style="font-size:16px;font-weight:bold;"><%=sdata.getXs2()%></textarea></td>        
       <td width="60" ><font style="font-weight:bolder">地区调整系数</font></td>
         <td><textarea rows="2" cols="8" name="data.xs3" style="font-size:16px;font-weight:bold;"><%=sdata.getXs3()%></textarea></td>   
    </tr>
    <tr>               
       <td width="80" ><font style="font-weight:bolder">标准定额</font></td>
         <td><%=sdata.getGs() %></td>         
       <td width="80" ><font style="font-weight:bolder">调整定额</font></td>
         <td><%=sdata.getXzgs() %></td>         
       <td width="80" ><font style="font-weight:bolder">报价工时</font></td>
         <td><%=sdata.getBjgs() %></td>         
       <td width="80" ><font style="font-weight:bolder">我院工时</font></td>
         <td><%=sdata.getWygs() %></td>         
       <td width="80" ><font style="font-weight:bolder">合作工时</font></td>    
         <td><%=sdata.getHzgs() %></td>  
                        
     </tr>
     <tr>
       <td><input type="hidden" name="id"   value="<%=sdata.getId()%>"></td>
       <td><input type="hidden" name="done" value="<%=sdata.getDone()%>"></td>
     </tr>
   </table>
   <table border="1" >
      <tr>
       <td>
        <font style="font-weight:bolder">修改定额</font>
       </td>
       <td>
        <font style="font-weight:bolder">另存为新定额</font>
       </td>
     </tr>
     <tr>
       <td>
         <input type="submit" name="环节细分" value="环节细分" style="font-weight:bold;font-size:18px;"/>
       </td>
       <td>
         <input type="submit" name="环节细分" value="环节细分" style="font-weight:bold;font-size:18px;"/>
       </td>
     </tr>
   </table>
   <div style="padding:10px;">
  
   </div>
 </form>  
</body>
</html>