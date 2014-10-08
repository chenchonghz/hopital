<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.equipCheck.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/common.js"></script>
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<form action="equipCheck" method="post" >
设备检查具体内容：
    <table border="2">
    <tr>
        <td>检查项目：</td>
        <td>患者姓名：</td>
        <td>是否检查：</td>
    </tr>
    <% equipCheck equip=new equipCheck();
           out.print(equip.equipDetailSearch(request.getParameter("patientId"),request.getParameter("equipId"))); %>
     <!--  <tr>
        <td>CF检查机</td>
        <td>杜亮</td>
        <td>否</td>
    </tr>-->
    
</table>
<input type="submit" class="btn blue" value="保存" >

</form>
</body>
</html>