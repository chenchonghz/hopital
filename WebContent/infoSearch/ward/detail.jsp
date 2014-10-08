<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.wardManagement.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../styles/common.css" type="text/css">
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript" src="../../scripts/calendar.js"></script>
<script type="text/javascript" src="../../scripts/jquery-1.4.2.min.js"></script>
 <script type="text/javascript">
 

var req; //异步传输对象
if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}
function save(q){
	var id=document.getElementById("id").value;
	var excuteId=document.getElementById("excuteId").value;
     
     var url="updateDocTell?docTellStatus="+q+"&id="+id+"&excuteId="+excuteId;
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
    	alert("确认成功");
    	window.location.reload(true);
    }
}


 
</script>
</head>
<body onload="checkAth()">
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
  <div>
使用者：（<%=session.getAttribute("username") %>） <br>

<table>
 <% search ds=new search();
        out.print(ds.dTDetailSearch(request.getParameter("id"))); %>
        <!--   <tr>
      <input type="text" value="2013071111" id="id"/>
      <input type="text" value="<%=session.getAttribute("userId") %>" id="excuteId"/>
        <td>主治医生:李涛</td>
        <td>作用时间:20130701~20130711</td>
    </tr>
     <tr>
        <td colspan="2">医嘱内容：
            <textarea readonly rows="6" cols="80" name="docTellContent" id="docTellContent" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea>
        </td>
    </tr>
     <tr>
     <td colspan="2">
        <input type=button  id="nurseCheck" onclick="save(2)" value="护士校对" class="btn blue"/>
        <input type=button id="docStop" onclick="save(3)"  value="停止医嘱"  class="btn blue"/>
        <input type=button  id="nurseConfirm" onclick="save(4)"  value="护士确认"   class="btn blue"/>
        </td>
     </tr>  --> 
</table>
</div>
       
</body>
</html>