<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/Calendar2.js"></script>
<script type="text/javascript" >
function confrim(){
	var regEquip=document.getElementById("regEquip");
	
	var equipId=document.getElementById("equipId");
	var equipName=document.getElementById("equipName");
	//alert(regDepartment.options[regDepartment.selectedIndex].value   +"\n"   +regDepartment.options[regDepartment.selectedIndex].text) 
	if(equipName.value!=""){
		regEquip.submit();
    }else{
    	alert("请核对后输入");
    }
}
window.document.onkeydown=myKeyPress;
function myKeyPress(evt){
    //兼容IE和Firefox获得keyBoardEvent对象
    evt = (evt) ? evt : ((window.event) ? window.event : ""); 
     //兼容IE和Firefox获得keyBoardEvent对象的键值
    var key = evt.keyCode?evt.keyCode:evt.which; 
    if( key == 13 || key == 10 ){ 
        confrim(); 
    }
}
</script>
 
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
	 response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
当前登录用户的信息：（<%=session.getAttribute("username") %>） <br>
<form action="regEquip" method="post" id="regEquip">
<table>
	  <tr style="display:none">
		<td>*设备编号：</td>
		<td><input type="text" name="equipId" id="equipId"></td>
	</tr>
	<tr>
		<td>*设备名称：</td>
		<td><input type="text" name="equipName" id="equipName"></td>
	</tr>
	<tr>
		<td>设备价格：</td>
		<td><input type="text" name="price" id="price" onBlur="ischeckNum('price');">元</td>
	</tr>
	<tr>
		<td>单次检查价格：</td>
		<td><input type="text" name="checkPrice" id="checkPrice" onBlur="ischeckNum('price');">元</td>
	</tr>
	<tr>
		<td>设备数量：</td>
		<td><input type="text" name="number" id="number" onBlur="ischeckNum('number');">台</td>
	</tr>
	<tr>
        <td>购买日期：</td>
        <td><input type="text" name="buyate" id="buyate" style="border:1px solid #999;" onfocus="setday(this)"  ></td>
    </tr>
    <tr>
        <td>校正/检定日期：</td>
        <td><input type="text" name="checkedate" id="checkedate" style="border:1px solid #999;" onfocus="setday(this)"  ></td>
    </tr>
    <tr>
        <td>检定周期：</td>
        <td><input type="text" name="checkCycle" id="checkCycle" onBlur="ischeckNum('checkCycle');">年</td>
        
    </tr>
</table>
<input type="button" class="btn blue" value="注册" onclick="confrim()" ><input class="btn blue" type="reset" value="重置">
</form>
</body>
</html>