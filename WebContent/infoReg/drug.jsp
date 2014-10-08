<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" >
function confrim(){
	var drug=document.getElementById("drug");
	
	var pillName=document.getElementById("pillName");
	if(pillName.value!=""){
		drug.submit();
    }else{
    	alert("请核对后输入");
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
当前登录用户的信息：（<%=session.getAttribute("username") %>） <br>
<form action="drug" method="post" id="drug">
<table>
	 <tr style="display:none">
		<td>*药品编号：</td>
		<td><input type="text" name="pillNum" id="pillNum"></td>
	</tr> 
	
	<tr>
		<td>*药品名称：</td>
		<td><input type="text" name="pillName" id="pillName"></td>
	</tr>
	<tr>
        <td>*药品编号：</td>
        <td><input type="text" name="pillId" id="pillId"></td>
    </tr>
	<tr>
		<td>规格：</td>
		<td><input type="text" name="standard" id="standard"></td>
	</tr>
	<tr>
		<td>单价：</td>
		<td><input type="text" name="price" id="price" onBlur="ischeckNum('price');">元</td>
	</tr>
	<tr>
		<td>库存数量：</td>
		<td><input type="text" name="number" id="number" onBlur="ischeckNum('number');">瓶（盒）</td>
	</tr>
	<tr>
        <td>药品类型：</td>
        <td><select id="pillType" name="pillType">
                        <option value="1" selected>中药</option>
                        <option value="2">西药</option>
                        <option value="3">中成药</option>
            </select></td>
    </tr>
</table>
<input type="button" class="btn blue" value="注册" onclick="confrim()" ><input type="reset" class="btn blue" value="重置">
</form>
</body>
</html>