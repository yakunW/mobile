<!-- author:xuxiaoyin -->
<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xxy.struts2.bean.Data" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>����ϸ��</title>
</head>


<body>
<%     	
     	 List<Data> dlist = new ArrayList<Data>();
         dlist = (List<Data>)session.getAttribute("xdata");
         String id=(String)session.getAttribute("id");//���ݿ�data���е�idֵ
         session.setAttribute("id", id);
         int dn = 0;

         dn=dlist.size();
         int upcount=0;
         upcount=(Integer)session.getAttribute("upcount");
         
         String xs1=(String)session.getAttribute("xs1");
         String xs2=(String)session.getAttribute("xs2");
         String xs3=(String)session.getAttribute("xs3");
         String chanpin=(String)session.getAttribute("xfchanpin");
 %>  
<form action="hesuan.action" method="post">
<div>
   <table border=1 style="position:absolute;left:0px;">
     <tr style="background-color:blue">
     <td width="150" ><font style="font-weight:bold">��Ʒ</font></td>
     <td width="120" ><font style="font-weight:bold">��������</font></td>
     <td width="180" ><font style="font-weight:bold">����ϸ��</font></td>
     <td width="80" ><font style="font-weight:bold">��ʱ</font></td>
     <td width="120" ><font style="font-weight:bold">������ʱѡ��</font></td>
     
      </tr>
     <%for(int i=0;i<dn;i++){ %> 
     <tr>     
     <td width="80" ><%=dlist.get(i).getChanpin()%></td>
     <td width="120" ><%=dlist.get(i).getHj() %></td>
     <td width="180" ><%=dlist.get(i).getXf()%></td>
     <td width="80" ><%=dlist.get(i).getGs() %></td>
     <td width="80" ><input type="text" name="<%=dlist.get(i).getId()%>"  value="<%=dlist.get(i).getGs()%>"/></td>
    
      </tr>
       <%
    //      try{
   // 	       sum+=Double.parseDouble((String)request.getParameter("xzgs"));
   // 	       }catch(NumberFormatException e) {
    	        // �ַ�������ת��Ϊ�Ϸ���double����ֵʱ,�쳣����
  //  	     }
       }

        session.setAttribute("upcount", upcount);
        %>           
   </table>
   <table border="1" style="float:right;">
      <tr>
        <td width="80" ><font style="font-weight:bolder">��׼��ʱ</font></td>
        <td width="80" ><font style="font-weight:bolder">������ʱ</font></td>
        <td width="80" ><font style="font-weight:bolder">���۹�ʱ</font></td>
      </tr>
      <tr>
      
          <td><textarea rows="1" cols="5" name="xs1" style="font-size:16px;font-weight:bold;"><%=xs1%></textarea></td>
          <td><textarea rows="1" cols="5" name="xs2" style="font-size:16px;font-weight:bold;"><%=xs2%></textarea></td>
          <td><textarea rows="1" cols="5" name="xs3" style="font-size:16px;font-weight:bold;"><%=xs3%></textarea></td>
          
      </tr>
   </table>
   
 
</div>

<div>
 <input type="submit" name="��ʱ�ϼ�" value="��ʱ���" style="width:100px;font-size:15px;font-weight:bolder;position:absolute;bottom:0px;right:100px;"></input>
 <input type="button" name="ϵ���鿴" value="ϵ���鿴" onclick="window.open('xishu.action?chanpin=<%=java.net.URLEncoder.encode(chanpin,"GBK")%>','newwindow', 'height=400, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=true, resizable=no,location=no, status=no ')" style="width:100px;font-size:15px;font-weight:bolder;position:absolute;bottom:60px;right:100px;"/>

</div>

</form>

</body>
</html>