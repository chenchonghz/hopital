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
</head>
<script type="text/javascript">
    
    
    var req; //异步传输对象
     if(window.XMLHttpRequest){
         
         req=new XMLHttpRequest();
         
     }else if(window.ActiveXObject){
          
         req=new ActiveXObject("Microsoft.XMLHTTP");
     }
 function search(){
        var anything=document.getElementById("seachText").value;
         var url="searchOW?seachText="+anything;
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
        evt = (evt) ? evt : ((window.event) ? window.event : ""); 
         //兼容IE和Firefox获得keyBoardEvent对象的键值
        var key = evt.keyCode?evt.keyCode:evt.which; 
        if( key == 13 || key == 10 ){ 
            search(); 
        }
    }
</script>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
患者编号： <input type="text" name="seachText" id="seachText">
<input class="btn blue" type="button" value="搜索" onclick="search()"><br><br>
 
<div  style="height:400px;width:70%;overflow-x:auto;">
    <table >
         <thead>
            <tr>
                <td>患者编号</td>
                <td>患者姓名</td>
                <td>明细</td>
                <td>价格</td>
                <td>缴费状态</td>
                <td>收据编号</td>
            </tr>
         </thead>
         <tbody id="freash">
             <!-- <tr>
                <td><a href="outWardDetail.jsp?patientId=201306061">201306061</a></td>
                <td>qiuye</td>
                <td>中药</td>
                <td>20.00元</td>
                <td>未交费</td>
                <td>收据编号</td>
            </tr>      
            <tr><td colspan="6">总价：500元</td></tr> --> 
         </tbody>
    </table>
    </div>
</body>
</html>