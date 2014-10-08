<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.wardManagement.*" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/Calendar2.js"></script>
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
         var url="searchDRL?seachText="+anything;
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
            }else{
            	$("#freash").html("");
            	$("#freash").append(info);
            }
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
         var url="deleDRL?id="+e.substring(1);
         if(req){
             //调用XMLHttpRequest的open方法
             req.open("get",url,true);
             
             //指定回调函数
             req.onreadystatechange=callbackDele;
             req.send(null);
         }
         $("#"+e).remove();
        
    }
    function callbackDele(){
        if(req.readyState==4){
            var info=req.responseText;
        }
        if(info){alert("删除成功");}
        
    }
    
    function add(){
        $("#freash").append(" <tr id=\"f0"+i+1+"\"><td style=\"width:130px\"> 日期<input type=\"text\" name=\"inspectionDate\" id=\"inspectionDate\" style=\"border: 1px solid #999;width:100px\" onfocus=\"setday(this)\"> <br> 时间<input type=\"text\" name=\"inspectionTime\" id=\"inspectionTime\" style=\"border: 1px solid #999;width:100px\"></td> <td colspan=2><textarea rows=\"6\" cols=\"60\" name=\"inspectionContents\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"inspectionContents\"></textarea> </td> <td><input type=\"button\" value=\"删除诊断结果\" onclick=\"del('f0"+i+1+"')\" class=\"btn blue\"></td></tr>");
    }
</script>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<form action="diseaseRL" method="post"  >
患者编号： <input type="text" name="seachText" id="seachText">
<input class="btn blue" type="button" value="搜索" onclick="search()"><br><br>
 
<div  style="height:550px;width:95%;overflow-x:auto;">



    <table >
         <tbody id="freash">
         <% search ser=new search();
        out.print(ser.diseaseRLSearch(request.getParameter("patientId")));%>
        <!--   <tr><td>姓名:qwiuy<input type="text" value="201304042" id="patientId" name="patientId" style="display:none"></td><td>性别:男</td><td>年龄:15</td><td>科室：外科</td></tr>
           <tr>
             <td>病房时间：</td>
             <td colspan=2>巡查内容</td>
             <td>删除内容</td>
            </tr>
         
              <tr id="f01">
               <td style="width:130px">
                                                日期<input type="text" name="inspectionDate" id="inspectionDate" style="border: 1px solid #999;width:100px"
                    onfocus="setday(this)"> <br>
                                                  时间<input type="text" name="inspectionTime" id="inspectionTime" style="border: 1px solid #999;width:100px">
                </td>
                <td colspan=2><textarea rows="6" cols="60" name="inspectionContents" 
            onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                    id="inspectionContents"></textarea> </td>
                <td><input type="button" value="删除诊断结果" onclick="del('f01')" class="btn blue"></td>
            </tr> --> 
         </tbody>
    </table>
    <br>
    <input type="button" value="添加诊断结果" onclick="add()"  class="btn blue"  >
    <input type="submit" value="保存诊断结果"   class="btn blue"   >
    
    </div>
    </form>
</body>
</html>