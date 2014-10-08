<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ page import="hospital.service.diseaseSearch.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../../styles/common.css" type="text/css">
<script type="text/javascript" src="../../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript" src="../../scripts/Calendar2.js"></script>

<script type="text/javascript">
 
var req; //异步传输对象
if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}
function search(){
    var startDay=document.getElementById("startDay").value;
     var endDay=document.getElementById("endDay").value;
     var pname=document.getElementById("pname").value;
     var docname=document.getElementById("docname").value;
     
     var url="disCaseSearch?startDay="+startDay+"&endDay="+endDay+"&pname="+pname+"&docname="+docname;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callback;
         req.send(null);
     }
}
function callback(){
	 if(req.readyState==4){
         var info=req.responseText;
         if(info==""){
             alert("没有搜索到任何结果，请重新输入");
         }
         $("#search").html("");
         $("#search").append(info);
         
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
<div  style="height:200px;width:60%;overflow-x:auto;">
    <table border="1">
         <thead>
            <tr>
                <td>病历日期</td>
                <td>治疗科目</td>
                <td>患者姓名</td>
                 <td>主治医生</td>
            </tr>
         </thead>
         <tbody id="search">
                   <% diseaseSearch ds=new diseaseSearch();
          out.print(ds.disListSearch()); %>
            <!--  <tr> 
                <td>2013-03-03</td>
                <td>外科</td>
                <td><a href="diseaseDetail.jsp?patientId=201304041">邱烨</a></td>
                <td>王浩</td>
            </tr>  --> 
         </tbody>
    </table>
    </div>
    <br>搜索条件<br>
    按日期：<input type="text" name="startDay" id="startDay" style="border:1px solid #999;"    onfocus="setday(this)"   />
    ----<input type="text" name="endDay" id="endDay" style="border:1px solid #999;"    onfocus="setday(this)"   /> <br><br>
  患者姓名 <input type="text" id="pname" id="pname"><br><br>
    主治医生 <input type="text" id="docname" id="docname"><br> <br>
    <input type="button" value="搜索" onclick="search()" class="btn blue">
</body>
</html>