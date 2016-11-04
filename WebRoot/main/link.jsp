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
         ��ƶ����
	</div>
	<div class="menuContent">
		<ul> 
			<li><a href="../dinge/upload.jsp"  target="_blank">��������</a></li>
			<li><a href="../piliang/dinge.jsp"  target="_blank">��Ʒ�������</a></li>
			<li><a href="../dinge/searchData.html" target="work">�鿴����</a></li>
		</ul>
	</div>
	<div class="menuTitle">
         Ա����Ч����
	</div>
	<div class="menuContent">
		<ul>
		<li><a href="../employee/importSc.jsp" target="work">��ֱ�</a></li>
			<li><a href="../employee/importH_P.jsp?flag=hour" target="work">��ʱ��</a></li>
			<li><a href="../employee/importH_P.jsp?flag=pro" target="work">���˳ɼ���</a></li>
			<li><a href="../employee/check_export.jsp?flag=check" target="work">ƥ��У��</a></li>
			<li><a href="../employee/check_export.jsp?flag=export" target="work">����</a></li>
		</ul>
	</div>
	<div class="menuTitle">
      ������˾����
	</div>
	<div class="menuContent">
		<ul>
			<li><a href="../cooper/importCooper.jsp" target="work"> �����ۺ�&רҵ��</a></li>
			<li><a href="../cooper/calCredit.jsp" target="work"> ���㼾�Ȼ���</a></li>
	   	    <li><a href="/JHB/cooper/starLevel.action" target="work"> ��̬�Ǽ�����</a></li>
	  	    <li><a href="../cooper/cooper_export.jsp" target="work"> ����</a></li>
	  	    <li><a href="../cooper/select.jsp" target="work"> ��ѯ</a></li>
	  	    <li><a href="../cooper/rename.jsp" target="work">����</a></li>
	  	    <li><a href="../cooper/delete.jsp" target="work">������ݿ�����</a></li>
	  	    <li><a href="/JHB/cooper/importBasic.jsp" target="work">�����������</a></li>
		</ul>
	</div>
	<div class="menuTitle">
  ��̨����
	</div>
	<div class="menuContent">
		<ul>
			<li><a href="../back/standardAddUpdate.jsp" target="work">��������޸�</a></li>
			<li><a href="showStandard.action" target="work">�������ɾ��</a></li>
			<li><a href="../back/templateDownload.jsp" target="work">����ģ������</a></li>
			<li><a href="../back/dataBackupRecovery.jsp" target="work">���ݱ��ݻָ�</a></li>
			<li><a href="" target="">������˾��¼ά��</a></li>
		</ul>
	</div>
	
	
	</div>
</body>
</html>