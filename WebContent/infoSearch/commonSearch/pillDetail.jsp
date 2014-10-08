<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.commonSearch.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../styles/common.css" type="text/css">
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript" >
function confrim(){
    var drug=document.getElementById("drug");
    
    var pillName=document.getElementById("pillName");
    if(pillName.value!=""){
        drug.submit();
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
<form action="drugModify" method="post" id="drug">
<table border="1">
<% commonSearch com=new commonSearch();
out.print(com.pillDetailSearch(request.getParameter("pillNum")));%>
	<!--   <tr style="display:none">
		<td>药品编号：</td>
		<td><input type="text" name="pillNum" id="pillNum" readonly value=10001></td>
	</tr> 
	<tr>
		<td>*药品名称：</td>
		<td><input type="text" name="pillName" id="pillName" value="头孢"></td>
	</tr>
	<tr>
        <td>药品编号：</td>
        <td><input type="text" name="pillId" id="pillId" readonly value=10001></td>
    </tr> 
	<tr>
		<td>规格：</td>
		<td><input type="text" name="standard" id="standard" value="100mg/瓶"></td>
	</tr>
	<tr>
		<td>单价：</td>
		<td><input type="text" name="price" id="price" value=20.00></td>
	</tr>
	<tr>
		<td>库存数量：</td>
		<td><input type="text" name="number" id="number" value=2000></td>
	</tr>
	<td><select id="pillType" name="pillType">
                        <option value="1" selected>中药</option>
                        <option value="2">西药</option>
                        <option value="3">中成药</option>
            </select></td>
	-->
</table>
<input type="button" value="保存" onclick="confrim()" class="btn blue"> 
</form>
</body>
</html>