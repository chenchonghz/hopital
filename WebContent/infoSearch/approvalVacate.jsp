<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.*" %>
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
    
    var req; //异步传输对象
    if(window.XMLHttpRequest){
        
        req=new XMLHttpRequest();
        
    }else if(window.ActiveXObject){
         
        req=new ActiveXObject("Microsoft.XMLHTTP");
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
    function callbackdoc(){
        if(req.readyState==4){
            var info=req.responseText;
            if(info==""){
                alert("没有搜索到任何结果，请重新输入");
            }
            
            $("#ys").html(info);
            
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
        
        var userId = document.getElementsByName("userId");   
        var val2=null;   
        for(var i=0;i<userId.length;i++)   
        {   
        if (userId[i].checked)   
        {  
            val2=userId[i].value; 
            break;    }    
        } 
    
    var url="ssearchVacate?startDay="+startDay+"&endDay="+endDay+"&docId="+val2;
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
            $("#pscx").html(info);
            
        }
    }
    
    function callbackEdit(){
        if(req.readyState==4){
            var info=req.responseText;
            if(info){
                alert("保存成功");
            }
            location.reload(); 
        }
    }
    
    function save(e){
    	var url="vacateEdit?saveId="+e;
        if(req){
            //调用XMLHttpRequest的open方法
            req.open("get",url,true);
            
            //指定回调函数
            req.onreadystatechange=callbackEdit;
            req.send(null);
        }
    }
    function cancle(e){
        var url="vacateEdit?cancleId="+e;
        if(req){
            //调用XMLHttpRequest的open方法
            req.open("get",url,true);
            
            //指定回调函数
            req.onreadystatechange=callbackEdit;
            req.send(null);
        }
    }
    
    
        window.document.onkeydown=myKeyPress;
        function myKeyPress(evt){
            //兼容IE和Firefox获得keyBoardEvent对象
            evt = (evt) ? evt : ((window.event) ? window.event : ""); 
             //兼容IE和Firefox获得keyBoardEvent对象的键值
            var key = evt.keyCode?evt.keyCode:evt.which; 
            if( key == 13 || key == 10 ){ 
 
                find();  
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

<div style="height:300px;overflow-x:auto;">
<table>
    <caption>请假记录</caption>
    <tr>
        <td>申请人姓名</td>
        <td>请假日期</td>
        <td>天数（单位：天）</td>
        <td>审批/拒绝</td>
        
    </tr>
     <% reqVacate req=new reqVacate();
        out.print(req.vacateSearch());%>
   
     <!--<tr>
     <td>邱烨</td>
        <td>2013-08-12——2013-08-08</td>
        <td><center>0.5</center></td>
        <td><input type="button" value="审批" onclick="save(id)"    class="btn blue">
            <input type="button" value="拒绝" onclick="cancle(id)"    class="btn blue"></td>
    </tr>  -->
      
    
</table>
</div>
<div>
搜索条件:<br>
*时间段 ：<input type="text" class="myInput" name="startDay" id="startDay" style="border:1px solid #999;" onfocus="setday(this)"  />
    ----<input type="text" class="myInput" name="endDay" id="endDay" style="border:1px solid #999;" onfocus="setday(this)"   />
   <br>
</div>
<br>
 <div id="ys"> 
<input  type="text" class="myInput" id="docname">

<input type="button" value="查找医生" class="btn blue" onclick="searchDoc()"> 
  <!--<input type="radio" name="userId" value="10100001" />王浩
      <input type="radio" name="userId" value="10100002" />老毛  --> 
</div><br>
<input type="button" value="查询" onclick="find()" class="btn blue">
<br><br><br>
<table id="pscx">
    <!--<tr>
        <td>员工姓名</td>
        <td>休假时间</td>
        <td>休假天数</td>
    </tr>
      <tr>
        <td>邱烨</td>
        <td>2013-04-04</td>
        <td>0.5</td>
    </tr>
    <tr>
        <td colspan="3">总天数：0.5</td>
    </tr> -->
</table>
</body>
</html>