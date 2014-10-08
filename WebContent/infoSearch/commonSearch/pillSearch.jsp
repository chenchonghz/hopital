<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.commonSearch.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../styles/common.css" type="text/css">
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript" src="../../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
    
    
    var req; //异步传输对象
    if(window.XMLHttpRequest){
        
        req=new XMLHttpRequest();
        
    }else if(window.ActiveXObject){
         
        req=new ActiveXObject("Microsoft.XMLHTTP");
    }
    function dele(id){
         
       
        //将usrname提交到pre请求指定的servlet
        var url="pillNum?pill_num="+id;
        if(req){
            //调用XMLHttpRequest的open方法
            req.open("get",url,true);
            
            //指定回调函数
            req.onreadystatechange=callback;
            req.send(null);
        }
    }
    //处理服务器返回的信息
    function callback(){
        if(req.readyState==4){
             var info=req.responseText;
             if(info){
                 alert("删除成功");
             }else{
                 alert("删除失败");
             }
             window.location.reload(); 
        }
    }
    function search(){
        var anything=document.getElementById("seachText").value;
         var url="searchPill?seachText="+anything;
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
        evt = (evt) ? evt : ((window.event) ? window.event : "") 
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
<div  style="height:400px;width:60%;overflow-x:auto;">
	<table border="1">
		 <thead>
			<tr>
				<td>药品编号</td>
				<td>药品名称</td>
				<td>是否删除</td>
			</tr>
		 </thead>
		 <tbody id="freash">
		 <% commonSearch com=new commonSearch();
out.print(com.pillSearch( Integer.parseInt(session.getAttribute("authority").toString())));%>
			<!--  <tr>
				<td>10001</td>
				<td><a href="pillDetail.jsp?pillNum=10001">头孢</a></td>
				<td><input type="button" value="删除"  onclick="dele(10001)"></td>
			</tr>-->
		 </tbody>
	</table>
	</div>
	  <div id="seachText2" style="margin:20px 0 0 0 ">
    <input type="text" name="seachText" id="seachText"><input type="button" value="搜索" onclick="search()" class="btn blue">
</div>
</body>
</html>