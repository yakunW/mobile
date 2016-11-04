<!-- author:xuxiaoyin -->
<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xxy.struts2.bean.XfData" %> 
<%@ page import="com.xxy.struts2.dao.Database" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-latest.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GBK pageEncoding=GBK" >
<title>环节细分
<%
//request.setCharacterEncoding("GBK");
String cp=request.getParameter("chanpin");
String line = request.getParameter("line");
String xid = request.getParameter("xid");
String fl = request.getParameter("fenlei");
String chanpin = new String(cp.getBytes("ISO-8859-1"),"GBK");
String fenlei = new String(fl.getBytes("ISO-8859-1"),"GBK");
System.out.println("fenlei:"+fenlei);
%>

</title>
<script type="text/javascript">

function qiuhe(){
	var num = $("#num").val();
	var sum=0;
	var woyuan=0;
	var hezuo=0;
	var bili=0;
	var lineN = $("#line").val();
	for(var i=0;i<num;i++){
		var lines=new Number(document.getElementsByName("xiuzheng")[i].value);
		sum+=lines;
		bili=document.getElementsByName("bili")[i].value;
	    var linew=lines*bili;
		woyuan+=linew;
		
	}
	
	hezuo=new Number(sum-woyuan);
	var x1=eval("window.opener.document.all.guimo"+lineN+".value");
	var x2=eval("window.opener.document.all.jishu"+lineN+".value");
	var x3=eval("window.opener.document.all.diqu"+lineN+".value");
	var ji=eval("window.opener.document.all.ji"+lineN+".value");
	var x4=window.opener.document.all.jdxs.value;
	var tiaozheng=sum;
	sum=sum*x1*x2*x3*x4*ji;
	woyuan=woyuan*x1*x2*x3*x4*ji;
	hezuo=hezuo*x1*x2*x3*x4*ji;
	var st="window.opener.document.all.tiaozheng"+lineN+".value=tiaozheng";
	var ss="window.opener.document.all.baojia"+lineN+".value=sum";
	var sw="window.opener.document.all.woyuan"+lineN+".value=woyuan";
	var sh="window.opener.document.all.hezuo"+lineN+".value=hezuo";
	eval(st);
	eval(ss);
	eval(sw);
	eval(sh);
	
	var xiangmuid=eval("window.opener.document.all.xid.value");
	$("#xid").attr("value",xiangmuid);
	document.subform.action="XifenData.action";
	document.subform.submit();
	window.close();
}


   
</script>
</head>
<body>


<form name="subform" action="" method="post">
<div>
   <input type="hidden" name="xid" id="xid" >
   <table border=1 style="position:absolute;left:0px;">
     <tr style="background-color:blue">
     <td width="150" ><font style="font-weight:bold">分类</font></td>
     <td width="150" ><font style="font-weight:bold">产品</font></td>
     <td width="120" ><font style="font-weight:bold">工作环节</font></td>
     <td width="180" ><font style="font-weight:bold">环节细分</font></td>
     <td width="80" ><font style="font-weight:bold">工时</font></td>
     <td width="120" ><font style="font-weight:bold">修正工时</font></td>
     <td width="120" ><font style="font-weight:bold">比例系数</font></td>
      </tr>
     <%
     List<XfData> dlist = (List<XfData>)Database.queryXf(xid,chanpin,fenlei);
    
     System.out.println(dlist.size());
     for(int i=0;i<dlist.size();i++){ %> 
     <tr>  
     <td width="80" ><input type="text" name="fenlei" style="border-top-style:none;border-right-style:none;border-left-style:none;border-bottom-style:none;" value="<%=dlist.get(i).getFenlei()%>"></td>   
     <td width="80" ><input type="text" name="chanpin" style="border-top-style:none;border-right-style:none;border-left-style:none;border-bottom-style:none;" value="<%=dlist.get(i).getChanpin()%>"></td>
     <td width="120" ><input type="text" name="huanjie" style="border-top-style:none;border-right-style:none;border-left-style:none;border-bottom-style:none;" value="<%=dlist.get(i).getHuanjie() %>"></td>
     <td width="180" ><input type="text" name="xifen" style="border-top-style:none;border-right-style:none;border-left-style:none;border-bottom-style:none;" value="<%=dlist.get(i).getXifen()%>"></td>
     <td width="80" ><input type="text" name="biaozhun" style="border-top-style:none;border-right-style:none;border-left-style:none;border-bottom-style:none;" value="<%=dlist.get(i).getBiaozhun() %>"></td>
     <td width="80" ><input type="text" id="xiuzheng<%=i %>" name="xiuzheng"  value="<%=dlist.get(i).getXiuzheng()%>"/></td> 
     <td width="80" ><input type="text" id="bili<%=i %>" name="bili"  value="<%=dlist.get(i).getBili()%>"/></td>
     </tr>
      <%  }%>
   </table>
   
 
</div>

<div>
 <input type="submit" id="heji" name="heji" value="工时求和" onclick="qiuhe();" style="width:100px;font-size:15px;font-weight:bolder;position:absolute;bottom:0px;right:100px;"></input>
 <input type="hidden" id="num" name="num" value="<%=dlist.size() %>" ></input>
 <input type="hidden" id="line" name="line" value="<%=line %>" ></input>
</div>

</form>
</body>
</html>