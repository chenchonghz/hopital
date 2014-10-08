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
	     
	   var patientId=document.getElementById("patientId").value;
     var url="ELSearch?startDay="+startDay+"&endDay="+endDay+"&patientId="+patientId;
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
%>搜索条件
<div>
    *时间段 ：<input type="text" class="myInput" name="startDay" id="startDay" style="border:1px solid #999;"
     onfocus="setday(this)"   />
    ----<input type="text" class="myInput" name="endDay" id="endDay" style="border:1px solid #999;"  onfocus="setday(this)"  />
    <br><br>
    患者编号：
<input  type="text" class="myInput" id="patientId" name="patientId"><br>
<input type="button" value="查询" onclick="find()" class="btn blue">
</div>
<div>
 
 <table  style="margin:100px 0 0 0">
         <thead>
            <tr>
                <td>患者编号</td>
                <td>患者姓名</td>
                <td>明细</td>
                <td>价格</td>
                <td>缴费状态</td>
                <td>收据编号</td>
                <td>消费时间</td>
            </tr>
         </thead>
         <tbody id="pscx">
             <!--  <tr>
                <td>201306061</a></td>
                <td>qiuye</td>
                <td>中药</td>
                <td>20.00元</td>
                <td>未交费</td>
                <td>收据编号</td>
                <td>2012-02-02</td>
            </tr>      
            <tr><td colspan="7">总价：500元</td></tr>   --> 
         </tbody>
    </table>

</div>

</body>
</html>