<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.szh.struts2.bean.Data" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
<script type="text/javascript" >
 function save(cpid,i){
	 document.form1.action='jiucuo.action?cpid='+cpid+'&i='+i;
	 document.form1.submit();
 }
 
 function saveAs(cpid,i,done){
	 
	 if(done==0)
	 {document.form1.action='xifen.action?cpid='+cpid+'&i='+i;}
	 if(done==1)
     {document.form1.action='xifendone.action?cpid='+cpid+'&i='+i;}
	 
	 document.form1.submit();
 }
</script>	
</head>
<body>
<%     	
     	 List<Data> sdatadlist = new ArrayList<Data>();
         List<Data>   dlist = (List<Data>)request.getAttribute("sxlist");
         Data sdata=(Data)request.getAttribute("sdata");
         int dn = 0;
         dn=dlist.size();        
 %>  
<div id="d" >
<form action="shs.action" method="post">
<div id="d1" style="position:absolute; height:300px; overflow:auto">


   <table border="1" style="float:right;">
     <tr>
     <td width="80" ><font style="font-weight:bolder">项目编号</font></td>
     <td width="80" ><font style="font-weight:bolder">项目名称</font></td>
     <td width="80" ><font style="font-weight:bolder">项目省区</font></td>
     <td width="80" ><font style="font-weight:bolder">建设单位</font></td>
     <td width="80" ><font style="font-weight:bolder">业务大区（大区/分院）</font></td>
     <td width="80" ><font style="font-weight:bolder">计划经理</font></td>
     <td width="80" ><font style="font-weight:bolder">客户经理</font></td>
     <td width="80" ><font style="font-weight:bolder">项目负责人</font></td>
     <td width="80" ><font style="font-weight:bolder">主专业</font></td>
     <td width="80" ><font style="font-weight:bolder">设计阶段</font></td>
     <td width="80" ><font style="font-weight:bolder">主类</font></td>
     <td width="80" ><font style="font-weight:bolder">分类</font></td>
     <td width="80" ><font style="font-weight:bolder">小类</font></td>
     <td width="80" ><font style="font-weight:bolder">分项</font></td>
     <td width="80" ><font style="font-weight:bolder">产品</font></td>
        <td width="60" ><font style="font-weight:bolder">阶段系数</font></td>
        <td width="60" ><font style="font-weight:bolder">基数</font></td>
        <td width="60" ><font style="font-weight:bolder">规模调整系数</font></td>
        <td width="60" ><font style="font-weight:bolder">技术调整系数</font></td>
        <td width="60" ><font style="font-weight:bolder">地区调整系数</font></td>
     <td width="80" ><font style="font-weight:bolder">标准定额</font></td>
     <td width="80" ><font style="font-weight:bolder">调整定额</font></td>
     <td width="80" ><font style="font-weight:bolder">报价工时</font></td>
     <td width="80" ><font style="font-weight:bolder">我院工时</font></td>
     <td width="80" ><font style="font-weight:bolder">合作工时</font></td>    
      </tr>
      <tr>
          <td><textarea rows="6" cols="8" name="data.xid" style="font-size:16px;"><%=sdata.getXid() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.name" style="font-size:16px;"><%=sdata.getName() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.province" style="font-size:16px;"><%=sdata.getProvince() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.danwei" style="font-size:16px;"><%=sdata.getDanwei() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.yewu" style="font-size:16px;"><%=sdata.getYewu() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.jmanager" style="font-size:16px;"><%=sdata.getJmanager() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.kmanager" style="font-size:16px;"><%=sdata.getKmanager() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.fzr" style="font-size:16px;"><%=sdata.getFzr() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.zzy" style="font-size:16px;"><%=sdata.getZzy() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.sjjd" style="font-size:16px;"><%=sdata.getSjjd() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.zhulei" style="font-size:16px;"><%=sdata.getZhulei() %></textarea></td>
  
          <td><textarea rows="6" cols="8" name="data.fenlei" style="font-size:16px;"><%=sdata.getFenlei() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.xiaolei" style="font-size:16px;"><%=sdata.getXiaolei() %></textarea></td>
          <td><textarea rows="6" cols="8" name="data.fenxiang" style="font-size:16px;"><%=sdata.getFenxiang() %></textarea></td>
         
          <td><textarea rows="6" cols="8" name="data.chanpin" style="font-size:16px;"><%=sdata.getChanpin()%></textarea></td>
          <td><textarea rows="1" cols="5" name="data.jdxs" style="font-size:16px;font-weight:bold;"><%=sdata.getJdxs()%></textarea></td> 
          <td><textarea rows="1" cols="5" name="data.jishu" style="font-size:16px;font-weight:bold;"><%=sdata.getJishu()%></textarea></td>      
          <td><textarea rows="1" cols="5" name="data.xs1" style="font-size:16px;font-weight:bold;"><%=sdata.getXs1()%></textarea></td>
          <td><textarea rows="1" cols="5" name="data.xs2" style="font-size:16px;font-weight:bold;"><%=sdata.getXs2()%></textarea></td>
          <td><textarea rows="1" cols="5" name="data.xs3" style="font-size:16px;font-weight:bold;"><%=sdata.getXs3()%></textarea></td>   
          
          <td><%=sdata.getGs() %></td>
          <td><%=sdata.getXzgs() %></td>
          <td><%=sdata.getBjgs() %></td>
          <td><%=sdata.getWygs() %></td>
          <td><%=sdata.getHzgs() %></td>       
        </tr>
      </table>
   
</div>
<div id="d2" style="position:absolute;top:220px;left:0px;">
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
     <input type="hidden" name="xdata.xfid" value="<%=dlist.get(i).getId() %>"/>
     <td width="80" ><textarea  cols="8" name="xdata.fenlei" style="font-size:16px;"> <%=dlist.get(i).getFenlei()%></textarea> </td>  
     <td width="150" ><textarea cols="8" name="xdata.chanpin" style="font-size:16px;"> <%=dlist.get(i).getChanpin()%></textarea></td>
     <td width="120" ><textarea cols="8" name="xdata.hj" style="font-size:16px;"> <%=dlist.get(i).getHj() %></textarea></td>
     <td width="180" ><textarea cols="8" name="xdata.xf" style="font-size:16px;"> <%=dlist.get(i).getXf()%></textarea></td>
     <td width="60" ><input type="text"  name="xdata.gsxf" value="<%=dlist.get(i).getGsxf() %>" readonly style="width:50px; "/></td>
     <td width="60" ><input type="text"  name="xdata.xzgsxf"  id="xzgs<%=i%>"  value="<%=dlist.get(i).getXzgsxf()%>"   style="width:50px; "/></td>
     <td width="60"><input type="text"   name="xdata.bili"  id="bili<%=i%>"  value="<%=dlist.get(i).getBlxsxf() %>"          onchange="wyzbf(<%=i%>);"  style="width:50px; "/></td>
     <td width="60"><input  type="text"  name="xdata.wyzb"  id="wyzb<%=i%>"  value="<%=dlist.get(i).getGs()%>"   style="width:50px; "></input></td>

     </tr>
     
     <%
       }       
     %>           
   </table>

</div>

<div id="d3">
 <input type="button" name="关闭"         value="关闭"        onclick="window.close()" style="width:110px;font-size:15px;font-weight:bolder;position:absolute;bottom:30px;right:100px;"></input>
 <input type="button" name="定额修改"     value="定额修改"     onclick=""           style="width:110px;font-size:15px;font-weight:bolder;position:absolute;bottom:90px;right:100px;"/>
 <input type="button" name="另存为新定额" value="另存为新定额"  onclick=" "        style="width:110px;font-size:15px;font-weight:bolder;position:absolute;bottom:140px;right:100px;"/>

</div>

</form>
</div>
</body>
</html>