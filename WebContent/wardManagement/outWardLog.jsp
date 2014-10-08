<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.commonSearch.*" %>
     <%@ page import="hospital.service.wardManagement.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 <html>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/calendar.js"></script>
<script type="text/javascript" src="../scripts/jquery.jqprint.js"></script>
 <script type="text/javascript">
    
    
  /*   var req; //异步传输对象
     if(window.XMLHttpRequest){
         
         req=new XMLHttpRequest();
         
     }else if(window.ActiveXObject){
          
         req=new ActiveXObject("Microsoft.XMLHTTP");
     }
 function search(){
        var anything=document.getElementById("seachText").value;
         var url="searchDRL?seachText="+anything;
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
            }else{
            	$("#freash").html("");
            	$("#freash").append(info);
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
     */
</script>
<style >
.btn.blue { background: #2ae; 
            border-radius: 8px;
            padding:5px;
            margin:5px;
            width:100px;
}
 
input{
border:none;
border-bottom-style:solid ;
border-bottom-width: thin
}
 
#s ul{
float:left;
}
 ul{
 line-height:2;
 }
</style>

 <title>Insert title here</title>
 
 <script type="text/javascript">
 $(document).ready(function() { 
	 $("#print").click(function(){ 
	 $("#prt").jqprint(); 
	   
	 }); 
	 }); 
    
    var req; //异步传输对象
     if(window.XMLHttpRequest){
         
         req=new XMLHttpRequest();
         
     }else if(window.ActiveXObject){
          
         req=new ActiveXObject("Microsoft.XMLHTTP");
     }
 function search(){
        var anything=document.getElementById("seachText").value;
         var url="searchDOWL?seachText="+anything;
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
            }else{
            	$("#freash").html("");
            	$("#freash").append(info);
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
<OBJECT  id=WebBrowser  classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 style="display:none">
 </OBJECT>
 </noscript>
 <style media=print>
 .Noprint{display:none;}  .PageNext{page-break-after: always;}
 </style>
  <table align="center" class=NOPRINT>
   <tr>
    <td align="center">
    <input type=button name=button value="直接打印"   id="print" class="btn blue">
       </td>
  </tr>
 </table><br>
<div  class=NOPRINT> <%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
患者编号： <input type="text" name="seachText" id="seachText">
<input class="btn blue" type="button" value="搜索" onclick="search()"><br><br>以下为打印区:<br>
 </div>

  
  
  <hr size=1 noshadow color=black  class="NOPRINT" >
  <div id="prt">
  <div class="PageNext" id="freash">
  <!-- 
 <h2><center>出院记录</center></h2>
   <table border="1" style="margin:0 0 0 100px;">
            <tr>
                <td style="width:20%"><b>患者姓名</b></td>
                <td style="width:20%"><b>性别</b></td>
                <td style="width:20%"><b>年龄</b></td>
                <td style="width:20%"><b>科别</b></td>
            </tr>
            <tr>
                <td><b>入院时间</b></td>
                <td>2013-09-09 11:24</td>
                <td><b>出院时间</b></td>
                <td>2013-09-19 11:24</td>
            </tr>
             <tr>
                <td><b>明细</b></td>
                <td><b>价格</b></td>
                <td><b>缴费状态</b></td>
                <td><b>收据编号</b></td>
            </tr>
              <tr>
                <td>中药</td>
                <td>20.00元</td>
                <td>未交费</td>
                <td>收据编号</td>
            </tr>      
            <tr><td colspan="4"><b>总价</b>：500元</td></tr>  
    </table>
    <div  style="margin:100px 30px 0 100px">
     <span style="margin:0 30px 0 0">住院医生签字：__________________</span>
    <span>主治医生签字：__________________</span></div>
    -->
 </div>
 </div>
 </html>