<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
<style type="text/css">
.resizeDivClass
{
position:relative;
background-color:red;
width:2;
z-index:1;
left:expression(this.parentElement.offsetWidth-1);
cursor:e-resize;
}
</style>
<script type="text/javascript">
function MouseDownToResize(obj){
obj.mouseDownX=event.clientX;
objobj.pareneTdW=obj.parentElement.offsetWidth;
obj.pareneTableW=theObjTable.offsetWidth;
obj.setCapture();
}
function MouseMoveToResize(obj){
if(!obj.mouseDownX) return false;
var newWidth=obj.pareneTdW*1+event.clientX*1-obj.mouseDownX;
if(newWidth>0)
{
obj.parentElement.style.width =newWidth;
theObjTable.style.width=obj.pareneTableW*1+event.clientX*1-obj.mouseDownX;
}
}
function MouseUpToResize(obj){
obj.releaseCapture();
obj.mouseDownX=0;
}
</script>
</head>
<body>
改变表的宽度
<table id=theObjTable style="table-layout:fixed;">
<tr bgcolor=cccccc>
<td valign=top>　
<div class="resizeDivClass" onMouseDown="MouseDownToResize(this);" onmousemove="MouseMoveToResize(this);" onmouseup="MouseUpToResize(this);">aaa</div>　　
</td>
<td valign=top>
<div class="resizeDivClass" onmousedown="MouseDownToResize(this);" onmousemove="MouseMoveToResize(this);" onmouseup="MouseUpToResize(this);">ddd</div>　　
</td>
<td valign=top>
<div class="resizeDivClass" onmousedown="MouseDownToResize(this);" onmousemove="MouseMoveToResize(this);" onmouseup="MouseUpToResize(this);">ssss</div>　　
</td>
</tr>
<tr>
<td>呆呆呆呆呆呆呆呆呆呆呆呆呆呆</td><td>bbbb</td><td>dddd</td>
</tr>
</table>
</body>
</html>
