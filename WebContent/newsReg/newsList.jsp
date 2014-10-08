<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="hospital.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/Calendar2.js"></script>

<script type="text/javascript">
 
function confrim(){
    var newsReg=document.getElementById("newsReg");
    var newsContent=document.getElementById("newsContent");
    var creatDate=document.getElementById("creatDate");
        if(newsContent.value==null||creatDate.value==null){
            alert("请输入具体信息");
            return;
        }else{
            newsReg.submit();
        }
}
var req; //异步传输对象
if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}
function  del(i){
     var url="news?newsId="+i;
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
        window.location.reload(true);
    }
}

 
 
window.document.onkeydown=myKeyPress;
function myKeyPress(evt){
    //兼容IE和Firefox获得keyBoardEvent对象
    evt = (evt) ? evt : ((window.event) ? window.event : "") 
     //兼容IE和Firefox获得keyBoardEvent对象的键值
    var key = evt.keyCode?evt.keyCode:evt.which; 
    if( key == 13 || key == 10 ){ 
    	  confrim();  
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
<div  style="height:300px;width:60%;overflow-x:auto;">
	<table border="1">
        <tr>
            <td>序号</td>
            <td>公告内容</td>
            <td>公告日期</td>
            <td>是否删除</td>
        </tr>
        <% newsReg nr=new newsReg();
            out.print(nr.newsListSearch());%>
        <!-- <tr>
            <td>1</td>
            <td>新闻内容</td>
            <td>2013-04-04</td>
            <td><input type="button" value="删除" onclick="del(28)"></td>
        </tr>  -->
	</table>
	</div>
	
	<div>公告录入:</div>
	<form action="newsReg" method="post" id="newsReg">
    <table>
            <tr>
                <td>公告内容</td>
                <td ><textarea id="newsContent"  rows="6" cols="80" name="newsContent" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea></td>
                
            </tr>
            <tr>
                <td>公告录入时间</td>
              
                 <td  ><input type="text"   id="creatDate" name="creatDate"
                    style="border: 1px solid #999;"
                    onfocus="setday(this)">
                </td>
            </tr>
    </table>
    <input type="text" value=<%=session.getAttribute("userId") %> name="userId" style="display:none;">
    <input type="button" value="保存" onclick="confrim()" class="btn blue">
        <input type="reset" value="重置" class="btn blue">
</form>

</body>
</html>