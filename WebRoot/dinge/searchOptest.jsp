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
      <td  ><font style="font-weight:bolder">��Ŀ���</font></td>
        <td><textarea rows="2" cols="8" name="data.xid" style="font-size:16px;"><%=sdata.getXid() %></textarea></td>         
      <td  ><font style="font-weight:bolder">��Ŀ����</font></td>
        <td><textarea rows="2" cols="8" name="data.name" style="font-size:16px;"><%=sdata.getName() %></textarea></td>
      <td  ><font style="font-weight:bolder">��Ŀʡ��</font></td>
        <td><textarea rows="2" cols="8" name="data.province" style="font-size:16px;"><%=sdata.getProvince() %></textarea></td>        
      <td  ><font style="font-weight:bolder">���赥λ</font></td>
        <td><textarea rows="2" cols="8" name="data.danwei" style="font-size:16px;"><%=sdata.getDanwei() %></textarea></td>    
      <td  ><font style="font-weight:bolder">ҵ�����������/��Ժ��</font></td>
        <td><textarea rows="2" cols="8" name="data.yewu" style="font-size:16px;"><%=sdata.getYewu() %></textarea></td>    
     </tr>
     <tr>
      <td width="80" ><font style="font-weight:bolder">�ƻ�����</font></td>
         <td><textarea rows="2" cols="8" name="data.jmanager" style="font-size:16px;"><%=sdata.getJmanager() %></textarea></td> 
      <td width="80" ><font style="font-weight:bolder">�ͻ�����</font></td>
         <td><textarea rows="2" cols="8" name="data.kmanager" style="font-size:16px;"><%=sdata.getKmanager() %></textarea></td>        
      <td width="80" ><font style="font-weight:bolder">��Ŀ������</font></td>
         <td><textarea rows="2" cols="8" name="data.fzr" style="font-size:16px;"><%=sdata.getFzr() %></textarea></td>        
      <td width="80" ><font style="font-weight:bolder">��רҵ</font></td>
         <td><textarea rows="2" cols="8" name="data.zzy" style="font-size:16px;"><%=sdata.getZzy() %></textarea></td>         
      <td width="80" ><font style="font-weight:bolder">��ƽ׶�</font></td>
         <td><textarea rows="2" cols="8" name="data.sjjd" style="font-size:16px;"><%=sdata.getSjjd() %></textarea></td>       
     </tr>
     <tr>
      <td width="80" ><font style="font-weight:bolder">����</font></td>
        <td><textarea rows="2" cols="8" name="data.zhulei" style="font-size:16px;"><%=sdata.getZhulei() %></textarea></td>  
      <td width="80" ><font style="font-weight:bolder">����</font></td>
        <td><textarea rows="2" cols="8" name="data.fenlei" style="font-size:16px;"><%=sdata.getFenlei() %></textarea></td>         
      <td width="80" ><font style="font-weight:bolder">С��</font></td>
        <td><textarea rows="2" cols="8" name="data.xiaolei" style="font-size:16px;"><%=sdata.getXiaolei() %></textarea></td>       
      <td width="80" ><font style="font-weight:bolder">����</font></td>
        <td><textarea rows="2" cols="8" name="data.fenxiang" style="font-size:16px;"><%=sdata.getFenxiang() %></textarea></td>
      <td width="80" ><font style="font-weight:bolder">��Ʒ</font></td>
        <td><textarea rows="2" cols="8" name="data.chanpin" style="font-size:16px;"><%=sdata.getChanpin()%></textarea></td>         
     </tr>
     <tr>
       <td width="60" ><font style="font-weight:bolder">�׶�ϵ��</font></td>
         <td><textarea rows="2" cols="8" name="data.jdxs" style="font-size:16px;font-weight:bold;"><%=sdata.getJdxs()%></textarea></td>         
       <td width="60" ><font style="font-weight:bolder">����</font></td>
         <td><textarea rows="2" cols="8" name="data.jishu" style="font-size:16px;font-weight:bold;"><%=sdata.getJishu()%></textarea></td>                
       <td width="60" ><font style="font-weight:bolder">��ģ����ϵ��</font></td>
        <td><textarea rows="2" cols="8" name="data.xs1" style="font-size:16px;font-weight:bold;"><%=sdata.getXs1()%></textarea></td>          
       <td width="60" ><font style="font-weight:bolder">��������ϵ��</font></td>
        <td><textarea rows="2" cols="8" name="data.xs2" style="font-size:16px;font-weight:bold;"><%=sdata.getXs2()%></textarea></td>        
       <td width="60" ><font style="font-weight:bolder">��������ϵ��</font></td>
         <td><textarea rows="2" cols="8" name="data.xs3" style="font-size:16px;font-weight:bold;"><%=sdata.getXs3()%></textarea></td>   
    </tr>
    <tr>               
       <td width="80" ><font style="font-weight:bolder">��׼����</font></td>
         <td><%=sdata.getGs() %></td>         
       <td width="80" ><font style="font-weight:bolder">��������</font></td>
         <td><%=sdata.getXzgs() %></td>         
       <td width="80" ><font style="font-weight:bolder">���۹�ʱ</font></td>
         <td><%=sdata.getBjgs() %></td>         
       <td width="80" ><font style="font-weight:bolder">��Ժ��ʱ</font></td>
         <td><%=sdata.getWygs() %></td>         
       <td width="80" ><font style="font-weight:bolder">������ʱ</font></td>    
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
        <font style="font-weight:bolder">�޸Ķ���</font>
       </td>
       <td>
        <font style="font-weight:bolder">���Ϊ�¶���</font>
       </td>
     </tr>
     <tr>
       <td>
         <input type="submit" name="����ϸ��" value="����ϸ��" style="font-weight:bold;font-size:18px;"/>
       </td>
       <td>
         <input type="submit" name="����ϸ��" value="����ϸ��" style="font-weight:bold;font-size:18px;"/>
       </td>
     </tr>
   </table>
   <div style="padding:10px;">
  
   </div>
 </form>  
</body>
</html>