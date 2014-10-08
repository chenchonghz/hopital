<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="hospital.service.equipCheck.*"%>
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
      
         var url="searchEquip?seachEquip="+anything;
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
            $("#search").html("");
        	$("#search").append(info);
        }
    }
    function add(i){
    	var equipId=document.getElementById("equipId"+i).value;
    	var equipName=document.getElementById("equipName"+i).value;
    	var checkPrice =document.getElementById("checkPrice"+i).value;
    	$("#addList").append("<tr id=\""+i+"\"><td><input type=\"text\" id=\"equipId\"  name=\"equipId\" value=\""+i+"\" style=\"display:none\"> <input type=\"text\" id=\"equipName\" name=\"equipName\" value=\""+equipName+"\" readonly></td><td><input type=\"text\" id=\"equipPrice\"  name=\"equipPrice\" value=\""+checkPrice+"\" readonly></td><td><input type=\"button\" class=\"btn blue\" value=\"删除\" onclick=\"del("+i+")\"></td></tr>");
    }
    function del(a){
        document.getElementById(a).parentNode.removeChild(document.getElementById(a));
            }
 
    function confrim(){
        var equipName=document.getElementsByName("equipName");
        var equipCheckReg=document.getElementById("equipCheckReg");
        if(equipName.length==0){
        	 alert("请选择检查设备");
        	 return;
        }else{
        	equipCheckReg.submit();
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
    
    function dele(id){
    	 var patientId=document.getElementById("patientId");
        var url="deleEquipId?equipId="+id+"&patientId="+patientId.value;
        if(req){
            //调用XMLHttpRequest的open方法
            req.open("get",url,true);
            
            //指定回调函数
            req.onreadystatechange=callbackdele;
            req.send(null);
        }
    }
    //处理服务器返回的信息
    function callbackdele(){
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
</script>
</head>
<body>
	<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
	<input type="text" name="seachText" id="seachText">
	<input type="button" value="搜索" onclick="search()" class="btn blue"><br><br>
	<table border="1"  >
		<tr>
			<td>设备名称：</td>
			<td>是否添加：</td>
        </tr>
        <tr id="search">
        </tr>
        <!--  <tr>
            <td><input type="text" id="equipId3001" value="3001" style="display:none"> 
            <input type="text" id="checkPrice3001" value="200" style="display:none"> 
                <input type="text" id="equipName3001" value="ct机" readonly>
            </td>
            <td> 
                <input type="button" value="添加" onclick="add(3001)">
            </td>
            
        </tr>-->
			
	</table>
	<br>
            <br>
            <br>
            <br>
	设备检查具体内容：
	<form action="equipCheckReg" method="post" id="equipCheckReg">
	<input type="text" id="pcid" name="pcid" value=<%=request.getParameter("id") %> style="display:none;">
    <table id="addList">
			<tr>
				<td>设备名称：</td>
				<td>单价：</td>
				<td>是否删除：</td>
			</tr>
			<% equipCheck ec=new equipCheck();
               out.print(ec.equipfind(request.getParameter("patientId")));%>
			 
			<!-- <tr id="3001">
				<td>
				    <input type="text" id="equipId" name="equipId" value="3001" style="display:none"> 
				    <input type="text" id="equipName" name="equipName" value="ct机" readonly>
				</td>
				<td>
				<input type="text" id="equipPrice" name="equipPrice" value="120" readonly>
				</td>
				<td>
				 <input type="button" value="删除" onclick="del(3001)">
                </td>
			</tr> -->


		</table>
		<input type="text" value=<%=request.getParameter("patientId")%> name="patientId" id="patientId" style="display:none;">
        <input type="text" value=<%=session.getAttribute("userId") %> name="docId" style="display:none;">
		<input type="button" class="btn blue" value="保存" onclick="confrim()"> 
	</form>
</body>
</html>
</body>
</html>