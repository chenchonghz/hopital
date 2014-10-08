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
         var url="searchIWL?seachText="+anything;
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
 
<div  style="height:550px;width:95%;overflow-x:auto;" id="freash">
   <!--   <table >
    <tr> <td colspan=4> <center>入院记录 </center></td> </tr>
        <tr><td>姓名：</td><td>性别：</td> <td>年龄：</td><td>入院部门：</td>  </tr>
        <tr> <td colspan=4> <center>记录日期：2013-03-03 </center></td> </tr>
	    <tr ><td>主诉：</td><td colspan=3>患者无胸闷、心悸等不适</td> </tr>
	    <tr ><td>现病史：</td><td colspan=3>患者无胸闷、心悸等不适</td> </tr>
	    <tr ><td>既往史：</td><td colspan=3>患者无胸闷、心悸等不适</td> </tr>
	    <tr ><td>个人史：</td><td colspan=3>患者无胸闷、心悸等不适</td> </tr>
	    <tr ><td>家族史：</td><td colspan=3>患者无胸闷、心悸等不适</td> </tr>
	    <tr> <td colspan=4><center>体格检查</center> </td> </tr>
	    
	    <tr><td>T 54 ℃</td><td>P 65次/分</td> <td>R 53次/分</td><td>BP 5633mmHg</td>  </tr>
	     <tr><td><b>一般情况</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>皮肤黏膜</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>淋巴结</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>头部</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>颈部</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>胸部</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>肺部</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>心脏</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>腹部</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>肛门与直肠及生殖器</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
   <tr><td><b>脊柱四肢</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
  <tr><td><b>神经系统</b></td><td colspan=3>：患者无胸闷、心悸等不适</td></tr>
    </table>
    
   <p>辅助检查<textarea rows="6" cols="60" name="inspectionContents" 
            onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                    id="inspectionContents"></textarea></p>
   <span>出院诊断：患者无胸闷</span><span>初步诊断：心悸等不适</span>-->
    </div>
</body>
</html>