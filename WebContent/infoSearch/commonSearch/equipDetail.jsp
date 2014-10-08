<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.commonSearch.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../styles/common.css" type="text/css">
<script type="text/javascript" src="../../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript" src="../../scripts/Calendar2.js"></script>
<script type="text/javascript" >
function confrim(){
	var regEquip=document.getElementById("regEquip");
	
	var equipName=document.getElementById("equipName");
	if(equipName.value!=""){
		regEquip.submit();
    }else{
    	alert("请核对后输入");
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
<form action="regEquipModify" method="post" id="regEquip">
<table border="1">
 <% commonSearch com=new commonSearch();
out.print(com.equipDetailSearch(request.getParameter("equipId")));%>
	 <!--    <tr>
		<td>设备编号：</td>
		<td><input type="text" name="equipId" id="equipId" value="3001" readonly></td>
	</tr> 
	<tr>
		<td>*设备名称：</td>
		<td><input type="text" name="equipName" id="equipName" value="CT检查机"></td>
	</tr>
	<tr>
		<td>设备价格：</td>
		<td><input type="text" name="price" id="price" value="60000.00"></td>
	</tr>
	<tr>
		<td>单次检查价格：</td>
		<td><input type="text" name="checkPrice" id="checkPrice" value="120.00"></td>
	</tr>
	<tr>
		<td>设备数量：</td>
		<td><input type="text" name="number" id="number" value="2"></td>
	</tr>
	<tr>
        <td>购买日期：</td>
        <td><input type="text" name="buyate" id="buyate" style="border:1px solid #999;" onclick="fPopCalendar(event,this,this)" onfocus="this.select()" value="2013-06-06" ></td>
    </tr>
    <tr>
        <td>校正/检定日期：</td>
        <td><input type="text" value="2013-06-06"  name="checkedate" id="checkedate" style="border:1px solid #999;" onclick="fPopCalendar(event,this,this)" onfocus="this.select()"  ></td>
    </tr>
    <tr>
        <td>检定周期：</td>
        <td><input type="text" name="checkCycle" id="checkCycle" value="2">年</td>
        
    </tr>
    -->
</table>
<input type="button" class="btn blue" value="保存" onclick="confrim()" > 
</form>
</body>
</html>