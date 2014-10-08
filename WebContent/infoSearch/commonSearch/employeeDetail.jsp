<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.commonSearch.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../../styles/common.css" type="text/css">
<script type="text/javascript" src="../../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript" src="../../scripts/Calendar2.js"></script>
<script type="text/javascript">
 
function confrim(){
    var employee=document.getElementById("employee");
    var userName=document.getElementById("userName");
    var contactNumber=document.getElementById("contactNumber");
    if(userName.value!=""&&contactNumber.value!=""){
    	employee.submit();
    }else{
        alert("请核对后输入");
    }
}
</script>
<title>Insert title here</title>
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
	 response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<form action="employeeModify" method="post" id="employee">
<table>
<% commonSearch com=new commonSearch();
out.print(com.userDetailSearch(request.getParameter("userId")));%>
<!--  
	<tr>
		<td>员工编号：</td>
		<td><input type="text" name="userId" id="userId" value=100001 readonly></td>
	</tr>
	<tr>
        <td>员工密码：</td>
        <td><input type="text" name="password" id="password" value=123456 ></td>
    </tr>
	<tr>
		<td>*员工姓名：</td>
		<td><input type="text" name="userName" id="userName" value="邱烨"></td>
	</tr>
	<tr>
		<td>性别：</td>
		<td> <input type="radio" name="sex" id="sex" value="1" checked="checked" />男 
			 <input type="radio" name="sex" id="sex" value="2" />女
		 </td>
	</tr>
	<tr>
		<td>出生日期：</td>
		<td><input type="text" name="brithday" id="brithday" style="border:1px solid #999;" onclick="fPopCalendar(event,this,this)" onfocus="this.select()"  value="1988-10-27" />
		 </td>
	</tr>
	<tr>
		<td>所属部门：</td>
		<td>
			<select   id="department"   name="department"> 
				<option value="102" selected>妇科</option>
				<option value="101">外科</option>
				<option value="103">牙科</option>
				<option value="104">内科</option>
				<option value="201">前台</option>
				<option value="301">药房</option>
				<option value="401">收款处</option>
				<option value="501">CT室</option>
				<option value="901" >院长室</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>*联系方式：</td>
		<td><input type="text" name="contactNumber" id="contactNumber" value="5675675678"></td>
	</tr>
	<tr>
		<td>现住址：</td>
		<td><input type="text" name="address" id="address" value="北京创新乐知信息技术有限公司"></td>
	</tr>
	<tr>
		<td>学历：</td>
		<td><input type="text" name="education" id="education" value="学士"></td>
	</tr>
	<tr>
        <td>入职日期：</td>
        <td><input type="text" name="inday" id="inday" style="border:1px solid #999;" onclick="fPopCalendar(event,this,this)" onfocus="this.select()"   />
         </td>
    </tr>
	<tr>
		<td>权限设置：</td>
		<td>
			<select   id="authority"   name="authority"> 
				<option value="1" selected>院长</option>
				<option value="2">医生</option>
				<option value="3">护士</option>
				<option value="4">药剂师</option>
				<option value="5">财务</option>
				<option value="6">CT观察员</option>  
			</select>
		</td>
	</tr>
	-->
</table>
<input type="button" value="保存" onclick="confrim()" class="btn blue" > 

</form>







</body>
</html>
</body>
</html>