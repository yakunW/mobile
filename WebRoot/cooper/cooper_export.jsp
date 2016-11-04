<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>计算年度积分</title>
  <style type="text/css">
	*{margin:0; padding:0;}
	#tablit {margin:20px;width:700px; height:393px; border:#BCE2F3 1px solid;padding-top:10px;background:#E4F2FB;}
	#tablit dl{ float:left; width:700px;}
	#tablit dl dt{float:left;border-bottom:#64B8E4 1px solid; width:15px; height:31px; line-height:30px;}
	#tablit dl dd{float:left;width:110px;height:30px; line-height:30px; text-align:center;}
	#tablit .on{border:#64B8E4 1px solid;border-bottom:#FFF 1px solid; color:#FF6600;background:#FFF;}
	#tablit .out{border:#64B8E4 1px solid; color:#000;background:#64B8E4;}
	.tabcon{width:100%; height:330px; background: #FFF; clear:both;}
	.dis{display:none;}
	</style>
  <link href="<%=basePath%>css/default.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
 function isValid()
  {	
  	if(importCooper.year.value == "")
  	{	
  		window.alert("年份不能为空!"); 
 		document.login.elements(0).focus();
  		return  false;
  	}
  	
  	importCooper.submit();
  }
</script>

  </head>
  
  <body >
   <div id="tablit">
    <dl>
        <dt></dt>
        <dd class="on"><font size="3">年度积分</font></dd>
        <dt></dt>
        <dd class="out"><font size="3">综合排名</font></dd>
        <dt></dt>
    </dl>
  <div class="tabcon">
  		<br/><br/><s:form action="yearCredit" namespace="/cooper" method="post" onsubmit="return isValid(this);">
    		<font size="3"><b>类别：</b></font>
			<input type="radio" name="class0" value=1 checked="checked" /><font size="3">综合</font>
			<input type="radio" name="class0" value=2 /><font size="3">传输</font>
			<input type="radio" name="class0" value=3 /><font size="3">无线</font>
			<input type="radio" name="class0" value=4 /><font size="3">交换</font>
			<input type="radio" name="class0" value=5 /><font size="3">数据</font>
			<input type="radio" name="class0" value=6 /><font size="3">电源</font>
			<input type="radio" name="class0" value=7 /><font size="3">土建</font>
			<input type="radio" name="class0" value=8 /><font size="3">网优</font>
			<br/><br/>
			<s:submit cssStyle="font-size: 18px;" value="确定"  />			
	   	 </s:form>
	</div>
	
    <div class="tabcon dis"><br/>
    	<s:form action="orderCredit" namespace="/cooper" method="post" onsubmit="return isValid(this);">
			<input type="radio" name="order" value=1 checked="checked" /><font size="3">综合排序</font>
			<br/><br/><input type="radio" name="order" value=2 /><font size="3">公司排序</font>
		    <br/><br/><s:submit cssStyle="font-size: 18px;" value="确定"  />		
	    </s:form></div>
   </div>
	   
  </body>
  <script type="text/javascript">

		var mDD = document.getElementById("tablit").getElementsByTagName("dd");
		var mDIV= document.getElementById("tablit").getElementsByTagName("div");		
		for (var i=0;i<mDD.length;i++){
		 (function(index) {
		  mDD[index].onmouseover = function() {
		   if (mDD[index].className == 'out') {
		    for (var j = 0; j < mDD.length; j++) {
		     mDD[j].className = 'out';
		     mDIV[j].style.display = 'none';
		    }
		    mDD[index].className = 'on';
		    mDIV[index].style.display = 'block';
		   }
		  }
		 })(i);
		}
	</script>
</html>
