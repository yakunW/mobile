<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
    <h3>��׼���ݵ���Ӻ��޸�</h3>
    <form action="upload.action" method="post" enctype="multipart/form-data">
    	��Ʒϵ��ģ�壺<input type="file" id="file1" name="upload"/><br/>
    	&nbsp;&nbsp;&nbsp;&nbsp;��ʱģ�壺<input type="file" id="file2" name="upload"/><br/>
    	<s:submit name="operation" value="���" ></s:submit>
    	<s:submit name="operation" value="�޸�" ></s:submit>
    	
     </form>
    <br/>
    <br/>
    <form action="showStandard.action" method="post" target="_blank">
    <h3>��׼���ݵĲ��Һ�ɾ��</h3>
    <input type="submit" value="��ʾ" />
    </form>
    <br/>
    <br/>
    <form   action="backUp.action"  method="post">
    <h3>���ݵı��ݺͻָ�</h3>
    <s:submit value="����" method="backUp"  ></s:submit>
    </form>
    <form action="recoveryUpload.action" method="post" enctype="multipart/form-data">
    ѡ��Ҫ�ָ����ļ��� <input type="file" id="file3" name="sql"/><br/>
    <input type="submit" value="�ָ�"/>
    </form>
    <br/>	
    <h3>ģ������</h3>
        <ul>
    <li>
       <a href="download.action?fileName=��Ʒϵ��ģ��.xlsx">��Ʒϵ��ģ��.xlsx</a>
    </li>
    <li>
       <a href="download.action?fileName=��ʱģ��.xlsx">��ʱģ��.xlsx</a><br/>
    </li> 
    </ul>
    <h3>ģ��ʹ��˵��</h3>
    <ul>
    <li>
        <a href="instructions.jsp" target="_blank">ģ��ʹ��˵���鿴</a> 
    </li>
    </ul>
   
  </body>
</html>
