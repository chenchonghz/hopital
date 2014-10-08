<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="styles/common.css" type="text/css">
<script type="text/javascript" src="scripts/common.js"></script>
<script type="text/javascript" src="scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript">

//根据权限显示对应的菜单栏
 
function checkAth(){
    var authority=document.getElementById("authority").value;       //员工权限
     
    var infoReg=document.getElementById("infoReg");                 //1信息注册
    var patient=document.getElementById("patient");                     //1.1病人信息
    var employee=document.getElementById("employee");                   //1.2员工信息
    var drug=document.getElementById("drug");                           //1.3药品信息
    var checkEquip=document.getElementById("checkEquip");               //1.4医疗设备信息
    var watchDisease=document.getElementById("watchDisease");       //2 看病专栏
    var money=document.getElementById("money");                     //3收费专栏
    var pillList=document.getElementById("pillList");               //4药方专栏
    var infoSearch=document.getElementById("infoSearch");           //5信息查询
    
    var commonInfoSearch=document.getElementById("commonInfoSearch");           //5.1电子病历
    var pillSellStatus=document.getElementById("pillSellStatus");     //5.2药品卖出
    var equipSellTime=document.getElementById("equipSellTime");       //设备检查开具次数
    var recheck=document.getElementById("recheck");                   //5.3复查服务提醒
    var euqiCheck=document.getElementById("euqiCheck");             //6设备检查
    var wardManagement=document.getElementById("wardManagement");   //8住院管理
    var reqVacate=document.getElementById("reqVacate");             //请假考勤
    var approvalVacate=document.getElementById("approvalVacate");   //请假审批
    
    if(authority==1){//院长
    	 money.style.display="";
    	 reqVacate.style.display="none";
    	 approvalVacate.style.display="block";
    }else if(authority==2){//大夫
        employee.style.display="none";
        drug.style.display="none";
        equipSellTime.style.display="none";
        checkEquip.style.display="none";
        pillSellStatus.style.display="none";
    }else if(authority==3){//护士
    	employee.style.display="none";
        drug.style.display="none";
        checkEquip.style.display="none";
        watchDisease.style.display="none";
        pillSellStatus.style.display="none";
        money.style.display="none";
        equipSellTime.style.display="none";
    }else if(authority==4){//药剂师
    	infoReg.style.display="none";
    	watchDisease.style.display="none";
    	money.style.display="none";
    	infoSearch.style.display="none";
    	euqiCheck.style.display="none";
    	wardManagement.style.display="none";
    	pillSellStatus.style.display="none";
    	equipSellTime.style.display="none";
    }else if(authority==5){//财务
    	money.style.display="";
    	infoReg.style.display="none";
        watchDisease.style.display="none";
        pillList.style.display="none";
        infoSearch.style.display="none";
        euqiCheck.style.display="none";
        wardManagement.style.display="none";
        equipSellTime.style.display="none";
    }else if(authority==6){//CT观察员
    	infoReg.style.display="none";
        watchDisease.style.display="none";
        pillList.style.display="none";
        infoSearch.style.display="none";
        money.style.display="none";
        wardManagement.style.display="none";
        pillSellStatus.style.display="none";
        equipSellTime.style.display="none";
    } 
    
    
}
 
</script>
<style type="text/css">
body { 
    font: bold 20px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #4f6b72; 
} 
li{
font: bold 18px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
}
ul{
    margin:0 0 0 0;
}
 
</style>
<title>Insert title here</title>
</head>
<body onload="checkAth()">
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<h5 style="color:#8F111B">涞山医院医疗信息管理系统V1.0</h5><br> 
欢迎您：<%=session.getAttribute("username") %> <br>

<input type="text" value=<%=session.getAttribute("authority") %> id="authority" style="display:none"> 
<div id="menzhen"  onclick="hide('ee')" ><a>门诊登记表 </a>
	<ul id="ee"  onclick="hide('ee')" >
        <li  ><a href="menzhenSearch.jsp"  target="mainFrame">门诊登记表查询</a></li>
        <li  ><a href="menzhen.jsp" target="mainFrame">门诊登记表录入</a></li>
    </ul>
</div>
<div id="infoReg"  onclick="hide('aa')" ><a> 信息注册 </a>
	<ul id="aa"  onclick="hide('aa')" >
        <li id="patient"><a href="infoReg/patient.jsp"  target="mainFrame"> 患者信息</a></li>
        <li id="employee"><a href="infoReg/employee.jsp" target="mainFrame"> 员工信息</a></li>
        <li id="drug"><a href="infoReg/drug.jsp" target="mainFrame"> 药品信息</a></li>
        <li id="checkEquip"><a href="infoReg/regEquip.jsp" target="mainFrame"> 医疗设备信息</a></li>
    </ul>
</div>
<div id="watchDisease"><a href="watchDisease/diseaseRegList.jsp" target="mainFrame"> 看病专栏</a></div>
<div id="money" style="display:none"><a href="money/recepitList.jsp" target="mainFrame"> 收费专栏</a></div>
<div id="pillList"><a href="pill/pillPay.jsp" target="mainFrame"> 药品专栏</a></div>
<div id="infoSearch"  onclick="hide('bb')"><a> 信息查询 </a>
    <ul id="bb"  onclick="hide('bb')">
    	<!-- <li id="menzhenSearch"  ><a href="infoSearch/menzhen.jsp" target="mainFrame"> 门诊登记表</a> </li> -->
        <li id="commonInfoSearch" onmouseover="ddd('cc','show')" onmouseout="ddd('cc','hide')"><a> 注册信息查询</a> 
        	 <ul id="cc">
		        <li id=""><a href="infoSearch/commonSearch/patientSearch.jsp" target="mainFrame">患者基本信息查询</a></li>
		        <li id=""><a href="infoSearch/commonSearch/employeeSearch.jsp" target="mainFrame">员工信息查询</a></li>
		        <li id=""><a href="infoSearch/commonSearch/pillSearch.jsp" target="mainFrame">药品信息查询</a></li>
		        <li id=""><a href="infoSearch/commonSearch/equipSearch.jsp" target="mainFrame">医疗设备信息查询</a></li>
    		</ul>
        </li>
        <li id="pillSellStatus"><a href="infoSearch/pillSell.jsp" target="mainFrame"> 药品卖出</a></li>
        <li id="equipSellTime"><a href="infoSearch/equipSellTime.jsp" target="mainFrame"> 设备检查开具次数</a></li>
        <li id="recheck"><a href="infoSearch/reviewCheck.jsp" target="mainFrame"> 复查服务提醒</a></li>
        <li id="diseaseSearch"><a href="infoSearch/diseaseSearch/diseaseList.jsp" target="mainFrame"> 电子病历</a></li>
        <li id="wardSearch"  ><a href="infoSearch/ward/docTellList.jsp" target="mainFrame"> 医嘱信息查询</a> </li>
    </ul>
</div>
<div id="euqiCheck"><a href="equipCheck/equipCheckList.jsp" target="mainFrame"> 设备检查</a></div>
<div id="pillList"><a href="pillList/pillList.jsp" target="mainFrame"> 药方专栏</a></div>
<div id="wardManagement" onclick="hide('dd')" ><a> 住院管理</a>
<ul id="dd" onclick="hide('dd')">
        <li id="wardEntry"><a href="wardManagement/patientList.jsp"  target="mainFrame"> 患者住院登记\医嘱录入</a></li>
        <li id="wardEntry"><a href="wardManagement/bodychecklist.jsp" target="mainFrame"> 体格检查</a></li>
        <li id="wardEntry"><a href="wardManagement/outWard.jsp" target="mainFrame"> 出院账单明细</a></li>
        <li id="wardEntry"><a href="wardManagement/outWardResult.jsp" target="mainFrame">出院诊断结果登录</a></li>
         <li id="wardEntry"><a href="wardManagement/inWardLog.jsp" target="mainFrame">住院志</a></li>
         <li id="wardEntry"><a href="wardManagement/diseaseRecordList.jsp" target="mainFrame">病程记录</a></li>
         <li id="wardEntry"><a href="wardManagement/outWardLog.jsp" target="mainFrame">出院记录</a></li>
         <li id="wardEntry"><a href="wardManagement/everyList.jsp" target="mainFrame">每日清单</a></li>
         <li id="wardEntry"><a href="wardManagement/pasteDisease.jsp" target="mainFrame">粘贴病历</a></li>
    </ul>
</div>
<div id="euqiCheck"><a href="newsReg/newsList.jsp" target="mainFrame"> 公告录入</a></div>
<div id="reqVacate"><a href="reqVacate.jsp" target="mainFrame">请假考勤</a></div>
<div id="approvalVacate" style="display:none;"><a href="infoSearch/approvalVacate.jsp" target="mainFrame" > 请假审批</a></div>
<div ><a href="main.jsp" target="mainFrame">返回首页</a></div>
</body>
</html>