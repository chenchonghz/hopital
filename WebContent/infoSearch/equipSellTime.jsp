<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
function searchPill(){
    var pillname=document.getElementById("pillname").value;
     
     var url="pillSellEquipSearch?seachText="+pillname;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callbackpill;
         req.send(null);
     }
}
function searchDoc(){
    var docname=document.getElementById("docname").value;
     
     var url="pillSelldocSearch?seachText="+docname;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callbackdoc;
         req.send(null);
     }
}
function find(){
    
         var startDay=document.getElementById("startDay").value;
         var endDay=document.getElementById("endDay").value;
         
         if(startDay==null||endDay==null||startDay==""||endDay==""){
             alert("搜索时间为必填项，请核对");
             return;
         }else if(Date.parse(startDay.replace("-","/")) > Date.parse(endDay.replace("-","/"))){
             alert("开始时间必须小于结束时间，请核对");
             return;
         }
         
         
         var equipId = document.getElementsByName("equipId");   
         var val1=null;   
         for(var i=0;i<equipId.length;i++)   
         {   
         if (equipId[i].checked)   
         {  
             val1=equipId[i].value; break;    }    
         } 
         
         var userId = document.getElementsByName("userId");   
         var val2=null;   
         for(var i=0;i<userId.length;i++)   
         {   
         if (userId[i].checked)   
         {  
             val2=userId[i].value; 
             break;    }    
         } 
         
         
         
         
     
     var url="pillSellESearch?startDay="+startDay+"&endDay="+endDay+"&equipId="+val1+"&docId="+val2;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callback;
         req.send(null);
     }
}
function callbackpill(){
     if(req.readyState==4){
         var info=req.responseText;
         if(info==""){
             alert("没有搜索到任何结果，请重新输入");
         }
         $("#yp").html("");
         $("#yp").append(info);
         
     }
}
function callbackdoc(){
    if(req.readyState==4){
        var info=req.responseText;
        if(info==""){
            alert("没有搜索到任何结果，请重新输入");
        }
        $("#ys").append(info);
        
    }
}
function callback(){
    if(req.readyState==4){
        var info=req.responseText;
        if(info==""){
            alert("没有搜索到任何结果，请重新输入");
        }
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
%>搜索条件
<div>
    *时间段 ：<input type="text" class="myInput" name="startDay" id="startDay" style="border:1px solid #999;"   onfocus="setday(this)"     />
    ----<input type="text" class="myInput" name="endDay" id="endDay" style="border:1px solid #999;"   onfocus="setday(this)"     />
    
</div>
<br><br>
<div id="yp">&nbsp&nbsp
   <input  type="text" id="pillname" class="myInput" name="pillname">
   
   <input type="button" value="按名称查找设备" class="btn blue" onclick="searchPill()" style="margin:0;">
   <!-- <input type="radio" name="equipId" value="10001" />头孢
      <input type="radio" name="equipId" value="10002" />安定  -->
</div>
<br><br>
<div id="ys">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<input  type="text" class="myInput" id="docname">

<input type="button" value="查找医生" class="btn blue" onclick="searchDoc()"> 
  <!--<input type="radio" name="userId" value="123451" />王浩
      <input type="radio" name="userId" value="123453" />老毛  --> 
</div>
<br><br>
<input type="button" value="查询" onclick="find()" class="btn blue">
<br><br><br><br>
<div >

<table border id="pscx">
    
    <!--<tr>
        <td>头孢</td>
        <td>2000元</td>
    </tr>
    <tr>
        <td>安定</td>
        <td>4000元</td>
    </tr>
    <tr>
        <td><h3>总价</h3></td>
        <td><h3>6000元</h3></td>
    </tr>  -->
</table>

</div>

</body>
</html>