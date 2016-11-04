<!-- author:xuxiaoyin -->
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(
	function() 
	{
		$(".menuTitle").click(function(){
			$(this).next("div").slideToggle("slow")
			.siblings(".menuContent:visible").slideUp("slow");
			$(this).toggleClass("activeTitle");
			$(this).siblings(".activeTitle").removeClass("activeTitle");
		});
	});
	
</script>
<style type="text/css">
	body
	{
		margin:0;background-color:rgb(204,204,204);
		
	}
	.container
	{
		width:100%;  text-align:left;
		position:absolute;
	}
	.menuTitle
	{
		width:148px; height:25px;  margin:0 auto; line-height:25px; font-size:15px; font-weight:bold;color:#0066FF; cursor:pointer; margin-top:6px;
	}
	.activeTitle
	{
		width:148px; height:25px;  margin:0 auto; line-height:25px; font-size:15px; font-weight:bold;color:#0066FF; cursor:pointer; margin-top:6px;
	}
	.menuContent
	{
		background-color:#ccc; margin:0 auto; height:auto; width:168px; text-align:left; display:none;
	}
	li
	{
		 no-repeat:20px 6px ; list-style-type:none;padding:0px 0px 0px 0px; font-size:14px; height:20px; line-height:20px;
	}
	ul
	{
		margin:0;padding:5;
	}
	a
	{
	text-decoration:none;
	}
</style>
</head>
<body>
<div class="container" >
	<div class="menuTitle">
         设计定额工具
	</div>
	<div class="menuContent">
		<ul> 
			<li><a href="../dinge/upload.jsp"  target="_blank">批量导入</a></li>
			<li><a href="../piliang/dinge.jsp"  target="_blank">产品定额编制</a></li>
			<li><a href="../dinge/searchData.html" target="work">查看数据</a></li>
		</ul>
	</div>
	<div class="menuTitle">
         员工绩效考核
	</div>
	<div class="menuContent">
		<ul>
		<li><a href="../employee/importSc.jsp" target="work">打分表</a></li>
			<li><a href="../employee/importH_P.jsp?flag=hour" target="work">工时表</a></li>
			<li><a href="../employee/importH_P.jsp?flag=pro" target="work">考核成绩表</a></li>
			<li><a href="../employee/check_export.jsp?flag=check" target="work">匹配校验</a></li>
			<li><a href="../employee/check_export.jsp?flag=export" target="work">导出</a></li>
		</ul>
	</div>
	<div class="menuTitle">
      合作公司考核
	</div>
	<div class="menuContent">
		<ul>
			<li><a href="../cooper/importCooper.jsp" target="work"> 导入综合&专业表</a></li>
			<li><a href="../cooper/calCredit.jsp" target="work"> 计算季度积分</a></li>
	   	    <li><a href="/JHB/cooper/starLevel.action" target="work"> 动态星级评定</a></li>
	  	    <li><a href="../cooper/cooper_export.jsp" target="work"> 导出</a></li>
	  	    <li><a href="../cooper/select.jsp" target="work"> 查询</a></li>
	  	    <li><a href="../cooper/rename.jsp" target="work">更名</a></li>
	  	    <li><a href="../cooper/delete.jsp" target="work">清空数据库数据</a></li>
	  	    <li><a href="/JHB/cooper/importBasic.jsp" target="work">导入基础数据</a></li>
		</ul>
	</div>
	<div class="menuTitle">
  后台管理
	</div>
	<div class="menuContent">
		<ul>
			<li><a href="../back/standardAddUpdate.jsp" target="work">定额添加修改</a></li>
			<li><a href="showStandard.action" target="work">定额查找删除</a></li>
			<li><a href="../back/templateDownload.jsp" target="work">定额模版下载</a></li>
			<li><a href="../back/dataBackupRecovery.jsp" target="work">数据备份恢复</a></li>
			<li><a href="" target="">合作公司名录维护</a></li>
		</ul>
	</div>
	
	
	</div>
</body>
</html>