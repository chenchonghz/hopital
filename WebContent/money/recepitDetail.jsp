<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.money.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.jqprint.js"></script>
<script type="text/javascript" src="../scripts/common.js"></script>
 
        
         <style media=print>
 .Noprint{display:none;}  .PageNext{page-break-after: always;}
body{text-align:right; }
 table{margin:130px auto 0;}
 
 </style>
       <script type="text/javascript"> 
$(document).ready(function() { 
$("#print").click(function(){ 
$("#prt").jqprint(); 
  
}); 

}); 
 
</script> 
  
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<script type="text/javascript">
var req; //异步传输对象
if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}
function cancel(){
    var recepitId=document.getElementById("recepitId").value;
    var recepiter=document.getElementById("recepiter").value;
     var url="recepitReturn?recepitId="+recepitId+"&recepiter="+recepiter ;
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
    	 window.history.go(-1);
     }
}

 
 
</script>
 <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=3></OBJECT>
    <div id="prt">
<form action="recepit" method="post" >
<table broder='1' style="margin:20px auto;">
 <% recepit rec=new recepit();
    out.print(rec.recepit(request.getParameter("patient"),request.getParameter("poe"))); %>
      <!--  <tr>
        <td>收据编号<input type="text" name="no" id="no" value="" style="display:none"></td>
        <td><input type="text" name="recepitId" id="recepitId" value=100001 readonly></td>
    </tr>
    <tr>
         <td>收费内容</td>
        <td>牛黄清热口服液（1）、牛黄清胃丸（1）、藿香正气水（1）</td>
    </tr>
    <tr>
        <td>总价</td>
        <td><input type="text" name="amount" id="amount" value="6767.00"></td>
    </tr>
    <tr>
        <td>交款人</td>
        <td><input type="text" name="payer" id="payer" value="邱烨"></td>
    </tr>
    <tr>
        <td>是否收讫</td>
        <td><input type="text" name="status" id="status" value="Y"></td>
    </tr>-->
    
   <input type="text" name="recepiter" id="recepiter" value=<%=session.getAttribute("username")%> style="display:none"/></td>
</table>
</div>
<div class="Noprint">
     <input type="submit" value="保存" class="btn blue">
     <input type="button" value="取消" onclick="cancel()" class="btn blue">
      <input type=button name=button value="直接打印"   id="print" class="btn blue">
</div>
</form>




</body>
</html>
