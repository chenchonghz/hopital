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
<script type="text/javascript">
var req; //异步传输对象
if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}
function search(){
    var pillId=document.getElementById("pillId").value;
     
     var url="pillCaseSearch?pillId="+pillId ;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callback;
         req.send(null);
     }
}
function search(e){
     
     var url="pillCaseSearch?pillId="+e ;
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
         document.getElementById('search').innerHTML=info;
         
         var res=document.getElementById('fyzt').innerHTML;
         if(res!="已付药"){
        	 document.getElementById('bao').style.display = "";
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
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
处方编号<input type="text" id="pillId" name="pillId">
<input type="button" value="搜索" onclick="search()" class="btn blue">
<a href="pillNotPay.jsp">六个月之内未拿药人员</a>

<br><br>
<form action="pillSave" method="post">
<table border="1" id="search">
 <% pillCaseSearch pcs=new pillCaseSearch();
      out.print(pcs.pillNotPaySearch());%>
    
     <!--  <tr>
        <td>药品名称</td>
        <td>数量</td>
        <td>库存数量</td>
    </tr> <tr>
        <td><input type="text" value="头孢" readonly><input type="text" value="10001" style="display:none" name="pillid"></td>
        <td><input type="text" value="2" name="pillAmount" readonly> </td>
        <td><input type="text" value="2000" name="pillNum" readonly></td>
    </tr>
     <tr>
        <td>交款状态</td>
        <td colspan="2"><input type="text" value="已交款" readonly></td>
    </tr>
    <tr>
        <td>付药状态</td>
        <td colspan="2" id="fyzt">已付药</td>
    </tr>
     --> 
      
</table>

<input type="text" value="<%=session.getAttribute("username") %>" name="payer" style="display:none">
<input type="submit" value="保存" id="bao" style="display:none" class="btn blue">
</form>
</body>
</html>