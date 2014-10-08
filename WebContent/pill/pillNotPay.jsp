<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.pill.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script   language="javascript"   type="text/javascript"> 
var req; //异步传输对象
if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}

function search(i){
	var id=document.getElementById(i);
	var pname=document.getElementById('pname');
	pname.innerHTML="";
	pname.innerHTML+="<h3>患者名称:</h3><h3>"+id.cells[0].innerHTML+"</h3><br><h3>处方编号:</h3><h3>"+id.cells[1].innerHTML+"</h3><br>";
	
	
	
     var url="pillNotPay?id="+i;
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
        $("#pnum").html(info);
        
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
<h3>人员名单</h3><br>
 
<table border id='tab'>

 <% pillCaseSearch pcs=new pillCaseSearch();
      out.print(pcs.pillNotPaySearch());%>
 	<!--  <tr> 
        <td>患者名称</td>
        <td>处方编号</td>
        <td>处方开具日期</td>
 </tr>
 <tr id="201306224" >
        <td> 杜亮</td> 
        <td><a href="javascript:search(201306224)">201306224</a></td> 
        <td>2013-06-22</td>
    </tr>
     <tr id="201306223">
        <td> 王海涛</td> 
        <td><a href="javascript:search(201306223)">201306223</a></td> 
        <td>2013-06-22</td>
    </tr> --> 
</table>


<div id="pname">
 </div>
<table border id="pnum">

	<!--<tr>
        <td>药品名称</td>
        <td>数量</td>
    </tr>
    <tr>
        <td>头孢</td>
        <td>22</td>
    </tr>
    <tr>
        <td>贝特令</td>
        <td>22</td>
    </tr>  -->
</table>



</body>
</html>