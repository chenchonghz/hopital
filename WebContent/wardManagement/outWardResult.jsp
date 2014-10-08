<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.wardManagement.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
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
         var url="searchOWR?seachText="+anything;
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
    var i=$("#freash tr").length;
    function del(e){
    	$("#"+e).remove();
    	 var url="deleOWR?id="+e.substring(1);
         if(req){
             //调用XMLHttpRequest的open方法
             req.open("get",url,true);
             
             //指定回调函数
             req.onreadystatechange=callbackDele;
             req.send(null);
         }
    	
    }
    function callbackDele(){
        if(req.readyState==4){
            var info=req.responseText;
            if(info==""){
                alert("没有搜索到任何结果，请重新输入");
            }
        }
    }
    
    function add(){
    	$("#freash").append(" <tr id=\"f"+i+1+"\"><td style=\"width:40%\"><input type=\"text\" value=\"2\" name=\"status\" id=\"status\" style=\"display:none\"><input type=\"text\" class=\"myInput\" name=\"outWardDiagnosis\" id=\"outWardDiagnosis\"></td><td><select  name=\"inWardStatus\" id=\"inWardStatus\"><option value=\"1\" selected >有</option><option  value=\"2\" >临床未确定</option><option value=\"3\">情况不明</option><option value=\"4\">无</option></select></td><td><select name=\"outWardStatus\" id=\"outWardStatus\"><option value=\"1\" selected >治愈</option><option  value=\"2\" >好转</option><option value=\"3\">未愈</option><option value=\"4\">死亡</option><option value=\"5\">其他</option></select></td><td><input type=\"button\" value=\"删除诊断结果\" onclick=\"del('f"+i+1+"')\" class=\"btn blue\"></td></tr>");
    }
</script>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<form action="outWardres" method="post"  >
患者编号： <input type="text" name="seachText" id="seachText">
<input class="btn blue" type="button" value="搜索" onclick="search()"><br><br>
 
<div  style="height:400px;width:95%;overflow-x:auto;">

    <table >
         <thead>
            <tr>
                <td>出院诊断</td>
                <td>入院病情</td>
                <td>出院情况</td>
                <td>添加诊断结果</td>
            </tr>
         </thead>
         <tbody id="freash">
          <!--   <tr id="f1">
               <td style="width:40%"><input type="text" name="outWardDiagnosis" id="outWardDiagnosis"></td>
                <td>
                    <select  name="inWardStatus" id="inWardStatus">
                        <option value="1" selected >有</option>
                        <option  value="2" >临床未确定</option>
                        <option value="3">情况不明</option>
                        <option value="4">无</option>
                    </select>
				</td>
                <td>
                     <select name="outWardStatus" id="outWardStatus">
                        <option value="1" selected >治愈</option>
                        <option  value="2" >好转</option>
                        <option value="3">未愈</option>
                        <option value="4">死亡</option>
                        <option value="5">其他</option>
                    </select>
                </td>
                <td><input type="button" value="删除诊断结果" onclick="del('f1')" class="btn blue"></td>
            </tr>--> 
         </tbody>
    </table>
    <br>
    <input type="button" value="添加诊断结果" onclick="add()"  class="btn blue">
    <input type="submit" value="保存诊断结果"   class="btn blue">
    
    </div>
    </form>
</body>
</html>