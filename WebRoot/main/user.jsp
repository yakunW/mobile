<!-- author:xuxiaoyin -->
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script type="text/javascript" src="../js/jquery-latest.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">

function chongzhi(){
	
	$("#user").val("");
	$("#psd").val("");
}
</script>
</head>
<body>
<div align="center" style="margin-top:160px">
 <form action="login.action" method="post">
     <table border="0">
        <tr>
            <td> 用户名：</td><td> <input type="text" name="user" id="user"></td>
        </tr>
        <tr>
            <td> 密码：</td><td> <input type="password" name="psd" id="psd"></td>
        </tr>
        <tr>
            <td align="center"> <input style="margin-left:50px" type="submit" value="登录"></td>
            <td align="center"> <input style="margin-left:50px" type="button" onclick="chongzhi()" value="重置"></td>
        </tr>
     </table>
 </form>
</div>
</body>
</html>