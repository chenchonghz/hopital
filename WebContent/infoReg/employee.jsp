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

</head>
<script type="text/javascript" >
function confrim(){
	var employee=document.getElementById("employee");
	var userName=document.getElementById("userName");
	var department=document.getElementById("department");
	var contactNumber=document.getElementById("contactNumber");
	var authority=document.getElementById("authority");
	var inday=document.getElementById("inday");
	
	//alert(regDepartment.options[regDepartment.selectedIndex].value   +"\n"   +regDepartment.options[regDepartment.selectedIndex].text) 
	if(contactNumber.value!=""&&userName.value!=""&&authority!=""&&department.value!=""&&inday.value!=""){
		employee.submit();
    }else{
    	alert("请核对后输入");
    }
}
window.document.onkeydown=myKeyPress;
function myKeyPress(evt){
    //兼容IE和Firefox获得keyBoardEvent对象
    evt = (evt) ? evt : ((window.event) ? window.event : "") 
     //兼容IE和Firefox获得keyBoardEvent对象的键值
    var key = evt.keyCode?evt.keyCode:evt.which; 
    if( key == 13 || key == 10 ){ 
        confrim(); 
    }
}
</script>

<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
	 response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
当前登录用户的信息：（<%=session.getAttribute("username") %>） <br>
<form action="employee" method="post" id="employee">
<table>
 <tr style="display:none">
        <td>*员工编号：</td>
        <td><input type="text" name="userId" id="userId"></td>
    </tr> 
	<tr>
		<td>*员工姓名：</td>
		<td><input type="text" name="userName" id="userName"></td>
	</tr>
	<tr>
		<td>性别：</td>
		<td> <input type="radio" name="sex" id="sex" value="1" checked="checked" />男 
			 <input type="radio" name="sex" id="sex" value="2" />女
		 </td>
	</tr>
	<tr>
		<td>出生日期：</td>
		<td><input type="text" name="brithday" id="brithday" style="border:1px solid #999;" onfocus="setday(this)"   />
		 </td>
	</tr>
	<tr>
		<td>*所属部门：</td>
		<td>
			<select   id="department"   name="department"> 
				<option value="">SELECT</option>
				<option value="501">仪器检查室</option>  
				<option value="901">院长室</option>
				<option value="101">外科</option>
				<option value="102">妇科</option>
				<option value="103">牙科</option>
				<option value="104">内科</option>
				<option value="105">儿科</option>
                <option value="106">眼科</option>
                <option value="107">耳鼻喉科</option>
                <option value="108">口腔科</option>
                <option value="109">皮肤科</option>
                <option value="110">中医科</option>
				<option value="201">护士站</option>
				<option value="301">药房</option>
				<option value="401">财务室</option>
				
			</select>
		</td>
	</tr>
	<tr>
		<td>*联系方式：</td>
		<td><input type="text" name="contactNumber" id="contactNumber"></td>
	</tr>
	<tr>
		<td>现住址：</td>
		<td><input type="text" name="address" id="address"></td>
	</tr>
	<tr>
		<td>学历：</td>
		<td><input type="text" name="education" id="education"></td>
	</tr>
	<tr>
        <td>*入职日期：</td>
        <td><input type="text" name="inday" id="inday" style="border:1px solid #999;" onfocus="setday(this)"   />
         </td>
    </tr>
	<tr>
		<td>*权限设置：</td>
		<td>
			<select   id="authority"   name="authority"> 
			    <option value="2">医生</option>
				<option value="">SELECT</option>
				<option value="6">仪器检测员</option>  
				<option value="1">院长</option>
				<option value="3">护士</option>
				<option value="4">药剂师</option>
				<option value="5">财务</option>
				
			</select>
		</td>
	</tr>
	
</table>
<input type="button" class="btn blue" value="注册" onclick="confrim()" ><input class="btn blue" type="reset" value="重置">

</form>







</body>
</html>