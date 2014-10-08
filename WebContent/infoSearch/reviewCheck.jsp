<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ page import="hospital.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<div>复查提醒患者名单</div>
<div  style="height:400px;width:60%;overflow-x:auto;">
<table border="1">
    <tr>
        <td>患者姓名</td>
        <td>联系方式</td>
        <td>看病日期</td>
        <td>复查日期</td>
    </tr>
     <% reviewCheck rc=new reviewCheck();
           out.print(rc.reviewSearch()); %>
   <!--  <tr>
        <td>小逃</td>
        <td>0319-90909</td>
        <td>2013-04-04</td>
        <td style="color:red">2013-06-29</td>
    </tr> -->
</table>
</div>
</body>
</html>