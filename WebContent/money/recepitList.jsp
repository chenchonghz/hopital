<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.money.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
    
    var req; //异步传输对象
    if(window.XMLHttpRequest){
        
        req=new XMLHttpRequest();
        
    }else if(window.ActiveXObject){
         
        req=new ActiveXObject("Microsoft.XMLHTTP");
    }
    function search(){
        var recepitId=document.getElementById("recepitId").value;
         var url="recepitSearch?recepitId="+recepitId ;
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
            $("#search").html(info);
            
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
%><div style="height:400px;width:90%;overflow-x:auto;">
    <table border="1" style=" width:90% ">
         <thead>
            <tr>
                <td>收据编号</td>
                <td>交款人姓名</td>
                <td>是否收讫</td>
                <td>收费类型</td>
            </tr>
         </thead>
         <tbody id="search">
         <% recepit rec=new recepit();
           out.print(rec.recepitListSearch()); %>
            <!--  <tr> 
                <td>201306071</td>
                <td><a href="recepitDetail.jsp?patientId=201304043&poe=1">邱烨</a></td>
                <td>是</td>
            </tr>-->
         </tbody>
    </table>
    </div>
    
    <br><br>

按收据编号搜索<input type="text" id="recepitId" name="recepitId"> <br><br>
<input type="button" value="搜索" onclick="search()" class="btn blue">
</body>
</html>