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
	var patient=document.getElementById("patient");
	var patientName=document.getElementById("patientName");
	var age=document.getElementById("age");
	var regDepartment=document.getElementById("regDepartment");
	var regDepartValue=regDepartment.options[regDepartment.selectedIndex].value;
	//alert(regDepartment.options[regDepartment.selectedIndex].value   +"\n"   +regDepartment.options[regDepartment.selectedIndex].text) 
	
	if(patientName.value!=""&&age.value!=""){
		patient.submit();
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
	当前登录用户的信息：（<%=session.getAttribute("username") %>）
	<br>
	<form action="patient" method="post" id="patient">
		<table>
			<tr style="display: none">
				<td>*患者编号：</td>
				<td><input type="text" name="patientId" id="patientId">
				</td>
			</tr>
			<tr>
				<td>*患者姓名： <input type="text" name="patientName"
					id="patientName" value="" style="width: 70px"> *年龄： <input
					type="text" name="age" id="age" onBlur="ischeckNum('age');"
					style="width: 70px">岁</td>
				<td>性别： <input type="radio" name="sex" id="sex" value="1"
					checked="checked" />男 <input type="radio" name="sex" id="sex"
					value="2" />女</td>
				<td>职业： <input type="text" name="job" id="job" value=""
					style="width: 70px">
				</td>
			</tr>
			<tr>
				<td>身份证号： <input type="text" name="identityNumber"
					id="identityNumber" value="" style="width: 170px">
				</td>
				<td colspan="2">民族： <input type="text" name="national" id="national"
                    value="" style="width: 70px"> 婚姻： <input type="text"
                    name="marriage" id="marriage" value="" style="width: 70px">
                </td>
			</tr>
			<tr>
                <td>病史提供者： <input type="text" name="diseaseOffer"
                    id="diseaseOffer" value="" style="width: 170px">
                </td>
                <td colspan="2">可靠程度： 
                 <select id="trust" name="trust">
                        <option value="1">可靠</option>
                        <option value="2">基本可靠</option>
                        <option value="3">不可靠</option>
                </select>  
                </td>
            </tr>
			<tr>
				<td >出生日期： <input type="text" style="width: 170px"
                    name="brithday" id="brithday" style="border:1px solid #999;"
                     onfocus="setday(this)"/>
                </td>
				<td colspan="2">挂号部门： <select id="regDepartment"
					name="regDepartment">

						<option value="101">外科</option>
						<option value="102">妇科</option>
						<option value="103">牙科</option>
						<option value="104">内科</option>
						<option value="105">儿科</option>
						<option value="106">眼科</option>
						<option value="107">耳鼻喉科</option>
						<option value="108">口腔科</option>
						<option value="109">皮肤科</option>
						<option value="110">中医科</option>

				</select></td>
			</tr>
			<tr>
				<td>现住址 ： <input type="text" name="nowAddress" id="nowAddress"
					value="" style="width: 500px">
				</td>
				<td>电话： <input type="text" name="contactNumber"
					id="contactNumber" value="" style="width: 170px">
				</td>
				<td>邮编： <input type="text" name="nowPostCode" id="nowPostCode"
					value="" style="width: 70px">
				</td>
			</tr>
			<tr>
				<td colspan="2">户口住址： <input type="text" name="huAddress"
					id="huAddress" value="" style="width: 500px">
				</td>
				<td>邮编： <input type="text" name="huPostCode" id="huPostCode"
					value="" style="width: 70px">
				</td>
			</tr>
			<tr>
				<td colspan="2">工作单位： <input type="text" name="jobAddress"
					id="jobAddress" value="" style="width: 500px">
				</td>
				<td>电话： <input type="text" name="jobNumber" id="jobNumber"
					value="" style="width: 170px">
				</td>
			</tr>
			<tr>
				<td colspan="3">联系人姓名： <input type="text" name="contactName"
					id="contactName" value="" style="width: 70px"> 关系： <input
					type="text" name="relationship" id="relationship" value=""
					style="width: 70px"> 电话： <input type="text"
					name="relationNumber" id="relationNumber" value=""
					style="width: 170px">
				</td>

			</tr>
			<tr>
				<td colspan="3">联系人住址： <input type="text"
					name="relationAddress" id="relationAddress" value=""
					style="width: 500px">
				</td>

			</tr>

		</table>
		<input type="button" class="btn blue" value="注册" onclick="confrim()"><input
			type="reset" class="btn blue" value="重置">
	</form>
</body>
</html>