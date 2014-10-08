<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.disease.*" %>
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
        var anything=document.getElementById("seachText").value;
         var url="searchd?seachText="+anything;
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
            $("#freash").append("<tr> <td>患者编号</td> <td>姓名</td> <td>诊断日期</td> </tr>"+info);
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
 
   
         
           <!-- <tr>
                <td>患者编号</td>
                <td>姓名</td>
                <td>挂号日期</td>
            </tr>  --> 
         
          <% diseaseSearch disease=new diseaseSearch();
       String departId=session.getAttribute("departId").toString();
       if(!disease.dissearch()){
    	   out.print("<h3>没有患者挂号</h3>");
       }else{%>
        <table border="1" id="freash">
    	<%    out.print(disease.disSearch()); %>
    	 </table>
    	<%
       }
%>
          <!--    <tr>
                <>201306071</>
                <><a href="diseaseDetail.jsp?patientId=201306071">邱烨</a></>
                <>2013-05-05</>
            </tr> -->
          
   <div id="seachText2" style="margin:20px 0 0 0 ">
  请 输入患者编号或姓名
    <input type="text" name="seachText" id="seachText"><br><input class="btn blue" type="button" value="搜索" onclick="search()">
</div>
</body>
</html>