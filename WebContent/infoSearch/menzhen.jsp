<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ page import="hospital.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.jqprint.js"></script>
<script type="text/javascript" src="../scripts/Calendar2.js"></script>
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
	 var sinceDate=document.getElementById("sinceDate").value;
	 var endDate=document.getElementById("endDate").value;
	 
	 if(sinceDate==null||endDate==null||sinceDate==""||endDate==""){
		 alert("请核对查询日期");
		 return;
	 }else if(Date.parse(sinceDate.replace("-","/")) >= Date.parse(endDate.replace("-","/"))){
    	 alert("开始时间必须小于结束时间，请核对");
         return;
     }else{
		 var url="menzhen?sinceDate="+sinceDate+"&endDate="+endDate;
		 if(req){
	         //调用XMLHttpRequest的open方法
	         req.open("get",url,true);
	         
	         //指定回调函数
	         req.onreadystatechange=callback;
	         req.send(null);
	     }
	 }
 }
 
 function callback(){
	    if(req.readyState==4){
	        var info=req.responseText;
	        if(info==""){
	            alert("没有搜索到任何结果，请重新输入");
	        }
	        $(".cent").html(info);
	        
	    }
	}
</script> 
 
<style type="text/css">
body { 
    font: bold 15px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #4f6b72; 
    background: #E6EAE9; 
} 
.btn {  display: block; 
    position: relative; 
    background: #aaa; 
    padding: 0 5px 0 5px; 
    float: left; 
    color: #fff; 
    text-decoration: none; 
    cursor: pointer; 
    width:100px;
    }
.btn * { font-style: normal; 
        background-image: url(images/btn2.png); 
        background-repeat: no-repeat; 
        display: block; 
        position: relative;
        width:100px; 
        }
.btn.blue { background: #2ae; 
            border-radius: 8px;
            padding:5px;
            margin:5px;
            width:100px;
}
 .myInput{border-radius: 5px;width:200px; height:25px;}
 #prt{margin:0 0 0 20%;}
</style>
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<div style=" width:380px;" >
	选择就诊日期：<input type="text" name="sinceDate" id="sinceDate" style="border: 1px solid #999;width:80px;" 
				onfocus="setday(this)" >----
			<input type="text" name="endDate" id="endDate" style="border: 1px solid #999;width:80px;"
                    onfocus="setday(this)" >
             
</div>
<input type="button" value="搜索" onclick="search()" class="btn blue" style="top:-35px;left:330px;">
<input type=button name=button value="直接打印"   id="print" class="btn blue" style="top:-35px;left:370px;">
<div id="prt">
  <center><h2>涞山医院门诊登记表</h2></center>
  
  <table style="font-size:8px;BORDER-COLLAPSE: collapse"  border="1" cellPadding="1">
  	  <thead>
  		<tr>
  			<td>患者姓名</td>
  			<td>性别</td>
  			<td>年龄</td>
  			<td>住址</td>
  			<td>联系电话</td>
  			<td>就诊时间年月日</td>
  			<td colspan="2">
  			 	 
  			 		<div> 首诊体征和症状 </div>
  			 		<div><span>血压</span><span>症状体征(含辅助检查)</span></div>
  			 	 
  			</td>
  			<td>初步诊断</td>
  			<td>治疗用药情况</td>
  			<td>处置情况</td>
  			<td>复诊时间年月日</td>
  			<td>接诊医生</td>
  		</tr>
  	</thead>
  	<tbody class="cent">
  		    <!-- <tr>
  			<td>邱烨</td>
  			<td>男</td>
  			<td>26</td>
  			<td>北京市通州区通汇北路22号 7-5-1</td>
  			<td>14345674567</td>
  			<td>2014-02-02</td>
  			<td>120PA</td>
  			<td>“症状”是病人自己向医生陈述（或是别人代述）的痛苦表现，如头疼、腹痛、鼻塞、恶心、呕吐等；
				“体征”是医生给病人检查时发现的具有诊断意义的征候，例如：右下腹麦氏点反跳痛是诊断阑尾炎的阳性体征；角弓反张、颈项强直是诊断破伤风的阳性体征；
				说了简单点，就是你发现的告诉医生的是症状，医生自己检查不出来的叫体征。</td> 
  			<td>根据诊断的准确程度，指在经过病史调查、一般检查及系统检查之后所做出的诊断，它是进一步实施诊疗的基础。无论在任何条件下，初步诊断都是必要的，
  			否则诊疗方案和措施便无从谈起。</td>
  			<td>内科大夫给了点活血化瘀的药，吃过肋间神经药都没有改变。现在觉的整个左胸勒好像憋闷疼有时候到上部有时候到下部，近期一直吃阿莫西林，但还是不见，非常痛苦请高手给个治
  			疗方案。请求高手怎样才能吸收液体。注:肺加强CT、胸部加强CT都做过没发现肿瘤，去年做过胃镜也没发现异物，去年做过心脏加强CT，问题也不大。吃什么药积液吸收</td>
  			<td>血化瘀的药，吃过肋间神经药都没有改变。现在觉的整个左胸勒好像憋闷疼有时候到上部有时候到下部，近期一直吃阿莫西林，但还是不见，非常痛苦请高手给个治
  			疗方案。请求高手怎样才能吸收液体。注:肺加强CT、胸部加强CT都做</td>
  			<td>2014-09-09</td>
  			<td>气球</td>
  		</tr>  -->  
  	</tbody>  
  </table>
  </div>
</body>
</html>