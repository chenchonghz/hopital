<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.diseaseSearch.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../styles/common.css" type="text/css">
<script type="text/javascript" src="../../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../../scripts/jquery.jqprint.js"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>
<style type="text/css" media=print>
            .btn blue{display : none } .PageNext{ PAGE-BREAK-AFTER: always }
         #prt table{margin:10%;}
 .Noprint{display:none;}  .PageNext{page-break-after: always;}
 </style>
 
        <script type="text/javascript"> 
$(document).ready(function() { 
$("#print").click(function(){ 
$("#prt").jqprint(); 
  
}); 
}); 
 
</script> 
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<!--调用active-->
        <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=3></OBJECT>
  <div id="prt" >
 <center>主治医生：（<%=session.getAttribute("username") %>）</center>  
<table border="1" style="margin:20px auto;" >
 <% diseaseSearch ds=new diseaseSearch();
          out.print(ds.disDetailSearch(request.getParameter("id"))); %>
    <!--<tr>
        <td>看病日期</td>
        <td>2013-04-04</td>
    </tr>
     <tr>
        <td>患者现象</td>
        <td>发热、萎靡不振、呕吐</td>
    </tr>
     <tr>
        <td>诊断结果</td>
        <td>此病为感冒症状</td>
    </tr>
     <tr>
        <td>开具的药物</td>
        <td>板蓝根</td>
    </tr>
     <tr>
        <td>药物服用方法</td>
        <td>冲服，每日4次，1月1疗程</td>
    </tr>  -->
</table>
</div>
        <div class="Noprint">
        <input type=button name=button value="直接打印"   id="print" class="btn blue">
       </div>
</body>
</html>