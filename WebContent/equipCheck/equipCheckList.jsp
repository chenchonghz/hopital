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
<div  style="height:400px;width:60%;overflow-x:auto;">
<table border="1">
         <thead>
            <tr>
                <td>检查编号</td>
                <td>检查日期</td>
                <td>患者姓名</td>
                <td>是否缴费</td>
                <td>检查项目</td>
            </tr>
         </thead>
         <tbody>
          
         <% equipCheck equip=new equipCheck();
           out.print(equip.equipListSearch()); %>
               <!--  <tr> <td>201306071</td>
                <td>2013-03-03</td>
                <td><a href="equiCheckDetail.jsp?patientId=2013010101&equipId=3001">邱烨</a></td>
                <td>是</td>
                <td>彩超仪</td></tr> -->
            
         </tbody>
    </table>
    </div>
</body>
</html>