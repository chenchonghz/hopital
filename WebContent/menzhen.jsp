<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles/common.css" type="text/css">
<script type="text/javascript" src="scripts/jquery-1.4.2.min.js"></script>
 <script type="text/javascript" src="scripts/common.js"></script>
 <script type="text/javascript" src="scripts/Calendar2.js"></script>
 <script type="text/javascript">
function confrim(){
	var age=document.getElementById("age");
	var watchDate=document.getElementById("watchDate");
	var fuzhendate=document.getElementById("fuzhendate");
	var menzhen=document.getElementById("menzhen");
	   if(!isNaN(age.value)){
		   if(age.value!=""&&watchDate.value!=""&&fuzhendate.value!=""){
			   menzhen.submit();
		   }else{
			   alert("请核对选择的日期");
			   return;
		   }
	   }else{
		   alert("请核对年龄输入格式");
		   return;
	   }
	   
}
</script>
</head>
<body>
     <center><h2> 涞山医院门诊登记表</h2> </center> 
     <form action="menzhend" method="post" id="menzhen">
     	<table>
      	<tr>
      		<td>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" id="pname" name="pname" style="width:70px;"/></td>
      		<td>性别： <input type="radio" name="sex" id="sex" value="1"
					checked="checked" />男 <input type="radio" name="sex" id="sex"
					value="2" />女</td>
      		<td>年龄：<input type="text" id="age" name="age" style="width:50px;"/>岁</td>
      		<td>联系电话：<input type="text" id="pnum" name="pnum" style="width:100px;"/></td>
      	</tr>
      	<tr>
      		<td>就诊时间： <input type="text"   style="width:100px;"
                    name="watchDate" id="watchDate" style="border:1px solid #999;"
                     onfocus="setday(this)"/></td>
           <td colspan="3">住址：<input type="text" id="address" name="address" style="width:600px;"/></td>
      	</tr>
      
      	<tr>
      		<td>首诊体征和症状</td>
      		<td colspan="4">血压<input type="text" id="BP" name="BP" style="width:70px;"/>mmHg</td>
      	</tr>
      	<tr>
      		<td>症状体征（含辅助检查）</td>
      		<td colspan="4"><textarea rows="6" cols="90" name="shoutizz"
                onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="shoutizz"></textarea>
            </td>
      	</tr>
      	<tr>
      		<td>初步诊断</td>
      		<td colspan="4"><textarea rows="6" cols="90" name="chubuzd"
                onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="chubuzd"></textarea>
            </td>
      	</tr>
      	<tr>
      		<td>治疗用药情况</td>
      		<td colspan="4"><textarea rows="6" cols="90" name="zhiliaoyy"
                onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="zhiliaoyy"></textarea>
            </td>
      	</tr>
      	 <tr>
      		<td>处置情况</td>
      		<td colspan="4"><textarea rows="6" cols="90" name="chuzhiqk"
                onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="chuzhiqk"></textarea>
            </td>
      	</tr>
      	 <tr>
      		<td colspan="2">复诊时间： <input type="text"   style="width:100px;"
                    name="fuzhendate" id="fuzhendate" style="border:1px solid #999;"
                     onfocus="setday(this)"/></td>
      		<td colspan="2">接诊医生：<input type="text" id="doctor" name="doctor" style="width:70px;"/>
            </td>
      	</tr>
      </table> 
      <input type="button" class="btn blue" value="注册" onclick="confrim()">
      <input type="reset" class="btn blue" value="重置">
     </form>
</body>
</html>