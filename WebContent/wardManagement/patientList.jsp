<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.wardManagement.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
        var anything=document.getElementById("seachText").value;
         var url="searchW?seachText="+anything;
         if(req){
             //调用XMLHttpRequest的open方法
             req.open("get",url,true);
             
             //指定回调函数
             req.onreadystatechange=callbackSearch;
             req.send(null);
         }
    }
    function callbackSearch(){
        if(req.readyState==4){
            var info=req.responseText;
            if(info==""){
                alert("没有搜索到任何结果，请重新输入");
            }
            $("#freash").html("");
            $("#freash").append(info);
        }
    }
   
    
    
    window.document.onkeydown=myKeyPress;
    function myKeyPress(evt){
        //兼容IE和Firefox获得keyBoardEvent对象
        evt = (evt) ? evt : ((window.event) ? window.event : "") 
         //兼容IE和Firefox获得keyBoardEvent对象的键值
        var key = evt.keyCode?evt.keyCode:evt.which; 
        if( key == 13 || key == 10 ){ 
        	search();  
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
<div  style="height:400px;width:90%;overflow-x:auto;">
    <table border="1" style=" width:90% ">
         <thead>
            <tr>
                <td>患者编号</td>
                <td>患者姓名</td>
                <td>注册日期</td>
                <td>入院状态</td>
                <td>医嘱录入</td>
            </tr>
         </thead>
         <tbody id="freash">
           <% search com=new search();
out.print(com.patientSearch());%>
            
           <!-- <tr>
                <td><a href="wardDetail.jsp?patientId=201306061">201306061</a></td>
                <td>qiuye</td>
                <td>2013-06-06</td>
                <td>未办理入院</td>
                <td><a href="docTell.jsp?patientId=201306061">医嘱录入</a></td>
            </tr>  --> 
         </tbody>
    </table>
    </div>
      <div id="seachText2" style="margin:20px 0 0 0 ">
    <input type="text" name="seachText" id="seachText"><input class="btn blue" type="button" value="搜索" onclick="search()">
</div>
</body>
</html>