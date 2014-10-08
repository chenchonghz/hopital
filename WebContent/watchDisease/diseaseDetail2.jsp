<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="hospital.service.disease.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/calendar.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
 

var req; //异步传输对象
if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}
function save(){
	var patientId=document.getElementById("patientId").innerHTML;
	var BP=document.getElementById("BP").value;
	 var patientStatus=document.getElementById("patientStatus").value;
	 var patientTell=document.getElementById("patientTell").value;
	 var doctorDiagnoseResult=document.getElementById("doctorDiagnoseResult").value;
	 
	 var nowDisHis=document.getElementById("nowDisHis").value;
	 var pastDisHis=document.getElementById("pastDisHis").value;
	 var bodyHis=document.getElementById("bodyHis").value;
	 var famHis=document.getElementById("famHis").value;
  
 
	patientStatus= patientStatus.replace(/\r\n/ig,'<br>');
	patientStatus= patientStatus.replace(/\r/ig,'<br>');
	patientStatus= patientStatus.replace(/\n/ig,'<br>');
	
	patientTell= patientTell.replace(/\r\n/ig,'<br>');
	patientTell= patientTell.replace(/\r/ig,'<br>');
	patientTell= patientTell.replace(/\n/ig,'<br>');
	
	doctorDiagnoseResult= doctorDiagnoseResult.replace(/\r\n/ig,'<br>');
	doctorDiagnoseResult= doctorDiagnoseResult.replace(/\r/ig,'<br>');
	doctorDiagnoseResult= doctorDiagnoseResult.replace(/\n/ig,'<br>');

	nowDisHis= nowDisHis.replace(/\r\n/ig,'<br>');
	nowDisHis= nowDisHis.replace(/\r/ig,'<br>');
	nowDisHis= nowDisHis.replace(/\n/ig,'<br>');
    

	pastDisHis= pastDisHis.replace(/\r\n/ig,'<br>');
	pastDisHis= pastDisHis.replace(/\r/ig,'<br>');
	pastDisHis= pastDisHis.replace(/\n/ig,'<br>');

	bodyHis= bodyHis.replace(/\r\n/ig,'<br>');
	bodyHis= bodyHis.replace(/\r/ig,'<br>');
	bodyHis= bodyHis.replace(/\n/ig,'<br>');

	famHis= famHis.replace(/\r\n/ig,'<br>');
	famHis= famHis.replace(/\r/ig,'<br>');
	famHis= famHis.replace(/\n/ig,'<br>');
	 
     var url="patientCase?patientStatus="+patientStatus+"&doctorDiagnoseResult="+doctorDiagnoseResult+"&patientId="+patientId+"&nowDisHis="+nowDisHis+"&pastDisHis="+pastDisHis+"&bodyHis="+bodyHis+"&famHis="+famHis+"&patientTell="+patientTell+"&BP="+BP;
     
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
		
	}
}


function confrim(){
    var diseaseDetail=document.getElementById("diseaseDetail");
    var bydoctorId=document.getElementById("bydoctorId");
        if(bydoctorId.value==null||bydoctorId.value==""){
            alert("请选择主治医生");
            return;
        }else{
        	diseaseDetail.submit();
        }
}

function recheck(){
	window.location("http://localhost:8080/hopital/watchDisease/diseaseDetail.jsp?patientId=201308201&equiId=&pillId=&id=3");
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

	<table  >
		<% diseaseSearch disease=new diseaseSearch();
	    out.print(disease.patientDetailSearch(request.getParameter("patientId")));%>
		<!-- 
<tr>
        <td>患者编号：</td>
        <td><input type="text" name="patientId" id="patientId" value=201306062 readonly></td>
    </tr>
    <tr>
        <td>患者姓名：</td>
        <td><input type="text" name="patientName" id="patientName" value="qiuye" readonly></td>
    </tr>
    <tr>
        <td>性别：</td>
        <td> <input type="radio" name="sex" id="sex" value="1" checked="checked" readonly/>男 
             <input type="radio" name="sex" id="sex" value="2" readonly/>女
         </td>
    </tr>
    <tr>
        <td>年龄：</td>
        <td><input type="text" name="age" id="age" value="25" readonly></td>
    </tr>
    <tr>
        <td>身份证号：</td>
        <td><input type="text" name="identityNumber" id="identityNumber" value="5675467567" readonly></td>
    </tr>
    <tr>
        <td>联系方式：</td>
        <td><input type="text" name="contactNumber" id="contactNumber" value="1231231234" readonly></td>
    </tr> -->
	</table>
	<form action="patientCaseRe" method="post" id="diseaseDetail">
<input type="text" name="patientId" id="patientId" value=<%=request.getParameter("patientId")%> style="display:none">
		<table>
		<tr><td>主诉：</td><td><textarea rows="6" cols="80" name="patientTell" id="patientTell"  onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea></td></tr> 
			 <tr><td>血压：</td><td><input type="text" id="BP" name="BP" />mmHG</td></tr>
			  <tr><input type="text" id="id" name="id" value="" style="display:none" ><td>现病史：</td><td><textarea rows="6" cols="80" name="nowDisHis" id="nowDisHis" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea></td></tr> 
                 <tr><td>既往史：</td><td><textarea rows="6" cols="80" name="pastDisHis" id="pastDisHis"  onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea></td></tr> 
                  <tr><td>个人史：</td><td><textarea rows="6" cols="80" name="bodyHis" id="bodyHis" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea></td></tr> 
                <tr><td>家族史：</td><td><textarea rows="6" cols="80" name="famHis" id="famHis"   onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea></td></tr>         
                   <tr><td>患者现状：</td><td><textarea rows="6" cols="80" name="patientStatus" id="patientStatus" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea></td></tr> 
                   <tr><td>医生诊断结果：</td><td><textarea rows="6" cols="80" name="doctorDiagnoseResult" id="doctorDiagnoseResult" onBlur="save()" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea></td></tr> 
             
			
			
			 <tr>
                <td><a
                    href="../equipCheck/equipCheckReg.jsp?patientId=<%=request.getParameter("patientId") %>">创建检查项</a>
                </td>
                <td><span id="equiId"></span> <%
                if(request.getParameter("equiId").equals("1")){%>
                	<script type="text/javascript">
                	 var equiId=document.getElementById("equiId"); 
                	 equiId.innerHTML="设备检查完成";
                	</script>
               <%  }
                 %>
                </td>

                <!--进入注册设备检查的页面  -->
            </tr>
	 
    <!--<tr>
        <td>医生诊断结果：</td>
        <td><textarea rows="3" cols="20" name="doctorDiagnoseResult" id="doctorDiagnoseResult" onBlur="save()"></textarea></td>
    </tr>-->

			<tr>
				<td><a
					href="creatDisease.jsp?patientId=<%=request.getParameter("patientId") %>">创建处方</a>
				</td>

				<td>处方ID：<a
					href="../pillList/pillListDetail.jsp?id=<%=disease.findId(request.getParameter("patientId")) %>">我的处方</a>
				 
				</td>
				<!--进入药方专栏的对应detail页面  -->
			</tr>
			<% diseaseSearch disease1=new diseaseSearch();
			diseaseSearch disease2=new diseaseSearch();
			if(!disease1.patientDetail2Search(request.getParameter("id")).equals("")){
				out.print(disease2.patientDetail2Search(request.getParameter("id")));
			}else{%>
			<tr>
                <td>处方使用说明：</td>
                <td><textarea rows="6" cols="80" name="medicineUseMethod"
                onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="medicineUseMethod"></textarea>
                </td>
            </tr>
            <tr>
                <td>处置情况：</td>
                <td><textarea rows="6" cols="80" name="dealStatus"
                onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="dealStatus"></textarea>
                </td>
            </tr>
            <tr>
                <td>诊断日期：</td>
                <td><input type="text" name="diagnoseDate" id="diagnoseDate"
                    style="border: 1px solid #999;"
                    onclick="fPopCalendar(event,this,this)" onfocus="this.select()">
                </td>
            </tr>

            <tr>
                <td>*诊断医生：</td>
                <td><select id="bydoctorId" name="bydoctorId">
                        <option value="">SELECT</option>
                        <% diseaseSearch dise=new diseaseSearch();
                          out.print(dise.patientDoctorSearch());%>
                </select>
            </tr>
            <tr>
                <td>复查日期：</td>
                <td><input type="text" name="reviewDate" id="reviewDate"
                    style="border: 1px solid #999;"
                    onclick="fPopCalendar(event,this,this)" onfocus="this.select()">
                </td>
            </tr>
			<%}
        %>
			 
		</table>
		<input type="button" value="注册" onclick="confrim()" class="btn blue">
	</form>
</body>
</html>