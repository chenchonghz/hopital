<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="styles/common.css" type="text/css">
 <script type="text/javascript" src="scripts/common.js"></script>
 <style media=print>
 .Noprint{display:none;}  .PageNext{page-break-after: always;}
 </style>
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<iframe src="left.jsp" width="23%" height="630"  class="Noprint"> </iframe> 
<iframe src="main.jsp" width="75%" height="630" name="mainFrame"  > 
</iframe> 
	<!-- <frameset rows="70,*" cols="*" frameborder="no" border="0" framespacing="0">
			<frame src="head.html" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" /> 
			 
			<frameset cols="193,*" frameborder="no" border="0" framespacing="0">   
				 <frame src="left.html" scrolling="No" noresize="noresize" id="leftFrame" />    
				 <frame src="main.html" name="mainFrame" id="mainFrame" /> 
			</frameset>
	</frameset> -->
</body>
</html>