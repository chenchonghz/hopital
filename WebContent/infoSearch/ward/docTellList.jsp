<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
 
 
function find(){
    
         var startDay=document.getElementById("startDay").value;
         var endDay=document.getElementById("endDay").value;
         
          if(Date.parse(startDay.replace("-","/")) > Date.parse(endDay.replace("-","/"))){
             alert("开始时间必须小于结束时间，请核对");
             return;
         }
         
         var patientName = document.getElementById("patientName").value;   
         var url="docTellSearch?startDay="+startDay+"&endDay="+endDay+"&patientName="+patientName;
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
        $("#pscx").html("");
        $("#pscx").append(info);
        
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
%><h3>搜索条件</h3>
<div>
    按时间搜索：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp起始时间<input type="text" name="startDay" id="startDay" class="myInput"     onfocus="setday(this)"  />
  <br> <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 终止时间在<input type="text" name="endDay" id="endDay"    class="myInput"    onfocus="setday(this)"  />之前
<br><br>按患者姓名搜索:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" name="patientName" id="patientName" class="myInput"/>
</div>
<br><br>
<input type="button" value="查询" onclick="find()" class="btn blue">
<br><br><br><br>
<div >

<table  id="pscx">

	<!--<tr>
	    <td>医嘱ID</td>
	    <td>主治医生</td>
	    <td>作用时间</td>
	    <td>执行护士</td>
	    <td>医嘱状态</td>
	    <td>医嘱类型</td>
	</tr>
    <tr>
        <td><a href="detail.jsp?id=2013071111">2013071111</a></td>
        <td>李涛</td>
        <td>20130701~20130711</td>
        <td>董卿</td>
        <td>下达医嘱、护士校对、停止医嘱、护士确认</td>
        <td>长期医嘱、临时医嘱</td>
    </tr>   --> 
 
</table>

</div>

</body>
</html>