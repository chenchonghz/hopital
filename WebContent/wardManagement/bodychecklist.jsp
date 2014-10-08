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
         var url="searchPc?seachText="+anything;
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
        evt = (evt) ? evt : ((window.event) ? window.event : "") ;
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
<div  style="height:400px;width:60%;overflow-x:auto;">
    <table border="1">
         <thead>
            <tr>
                <td>患者编号</td>
                <td>患者姓名</td>
                <td>检查状态</td>
            </tr>
         </thead>
         <tbody id="freash">
           <% search com=new search();
out.print(com.patientCheckSearch());%>
            
            <!--<tr>
                <td><a href="bodycheckdetial.jsp?patientId=201306061">201306061</a></td>
                <td>邱烨</td>
                <td>未检查</td>
            </tr>    -->   
         </tbody>
    </table>
    </div>
      <div id="seachText2" style="margin:10px 0 0 0 ">
      <input class="btn blue" type="button" value="搜索患者姓名" onclick="search()">
    <input type="text" name="seachText" id="seachText">
</div>
</body>
</html>