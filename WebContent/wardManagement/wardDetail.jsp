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
<script type="text/javascript" src="../scripts/Calendar2.js"></script>

<script type="text/javascript">
    
    
    
    
    window.document.onkeydown=myKeyPress;
    function myKeyPress(evt){
        //兼容IE和Firefox获得keyBoardEvent对象
        evt = (evt) ? evt : ((window.event) ? window.event : "") 
         //兼容IE和Firefox获得keyBoardEvent对象的键值
        var key = evt.keyCode?evt.keyCode:evt.which; 
        if( key == 13 || key == 10 ){ 
        	 var ss=document.getElementById("ward");
             ss.submit();    
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
<form action="wardModify" method="post" id="ward">
<input type="text" value="<%=request.getParameter("patientId") %>" id="patientId" name="patientId" style="display:none"/>
<input type="text" value="<%=session.getAttribute("userId") %>" id="userId" name="userId" style="display:none"/>
<table border="1">
<% search com=new search();
if(!com.patientFind(request.getParameter("patientId"))){
	%><tr>
        <td>入院途径：</td>
        <td><input type="text" name="inHospitalWay" id="inHospitalWay"  value=""></td>
         <td>入院科别：</td>
        <td><input type="text" name="inHospitalDepart" id="inHospitalDepart" value=""></td>
        
         </tr> 
    <tr><td>入院时间：</td>
        <td>日期：<input type="text" name="inDate" id="inDate" value="" style="border:1px solid #999;"  onfocus="setday(this)" >
        <br>  时间：<input type="text" name="inDateMin" id="inDateMin" value="">
        </td>
    
       <td>病房：</td>
        <td><input type="text" name="roomId" id="roomId" value="" onBlur="ischeckNum('roomId');"></td>
    </tr>
    <tr>
        <td>转科科别：</td>
        <td><input type="text" name="changeDepart" id="changeDepart" value=""></td>
         <td>出院科别：</td>
        <td><input type="text" name="outHospitalDepart" id="outHospitalDepart" value=""></td>
       
        </tr>
     <tr>
     <td>出院时间：</td>
        <td>日期：<input type="text" name="outDate" id="outDate" value="" style="border:1px solid #999;"  onfocus="setday(this)" >
       <br>  时间：<input type="text" name="outDateMin" id="inDateMin" value=""> </td>
    
        <td>实际住院：</td>
        <td>0天</td>
    </tr>
     <tr>
        <td>药物过敏：
        <select  style="width:60px" id="drugAllergyStatus"   name="drugAllergyStatus"> 
                <option value="1" selected>没有</option>
                <option value="2">有</option>
            </select>
         </td>
        <td>过敏药物：<input type="text" name="drugAllergy" id="drugAllergy" value=""></td>
        <td colspan="2">血型：<input type="text" name="bloodType" id="bloodType" value=""></td>
    </tr><%
}else{out.print(com.wardDetailSearch(request.getParameter("patientId"))); 
	
 }
%>

      <!-- <tr>
        <td>入院途径：</td>
        <td><input type="text" name="inHospitalWay" id="inHospitalWay"  value="急诊"></td>
         <td>入院时间：</td>
        <td><input type="text" name="inDate" id="inDate" value="2013-05-04" style="border:1px solid #999;" onclick="fPopCalendar(event,this,this)" onfocus="this.select()"></td>
    </tr> 
    <tr>
        <td>入院科别：</td>
        <td><input type="text" name="inHospitalDepart" id="inHospitalDepart" value="外科"></td>
        <td>病房：</td>
        <td><input type="text" name="roomId" id="roomId" value="9001"></td>
    </tr>
    <tr>
        <td>转科科别：</td>
        <td><input type="text" name="changeDepart" id="changeDepart" value="神经科"></td>
        <td>出院时间：</td>
        <td><input type="text" name="outDate" id="outDate" value="2012-02-02" style="border:1px solid #999;" onclick="fPopCalendar(event,this,this)" onfocus="this.select()"> </td>
    </tr>
     <tr>
        <td>出院科别：</td>
        <td><input type="text" name="outHospitalDepart" id="outHospitalDepart" value="神经科"></td>
        <td>实际住院：</td>
        <td>6天</td>
    </tr>
     <tr>
        <td>药物过敏：
        <select  style="width:60px" id="drugAllergyStatus"   name="drugAllergyStatus"> 
                <option value="1" selected>没有</option>
                <option value="2">有</option>
            </select>
         </td>
        <td>过敏药物：<input type="text" name="drugAllergy" id="drugAllergy" value="头孢"></td>
        <td colspan="2">血型：<input type="text" name="bloodType" id="bloodType" value="A"></td>
    </tr>  --> 
</table>
<input type="submit" value="保存" class="btn blue"> 
</form>
</body>
</html>