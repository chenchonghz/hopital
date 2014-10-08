<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="hospital.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles/common.css" type="text/css">
<script type="text/javascript" src="scripts/jquery-1.4.2.min.js"></script>
 
<script type="text/javascript" src="scripts/Calendar2.js"></script>
<style type="text/css">
.psc{display:none;border:1px solid #67BCD1;overflow: scroll;padding-bottom: 30px;}
.psc table{
padding:10px 0;
}
 
input{text-align:center}
</style>
<script type="text/javascript">
 
 

var req; //异步传输对象
if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}
 
 
function finds(){
    
         var startDay=document.getElementById("startDay").value;
         var endDay=document.getElementById("endDay").value;
         
          if(Date.parse(startDay.replace("-","/")) > Date.parse(endDay.replace("-","/"))){
             alert("开始时间必须小于结束时间，请核对");
             return;
         }
         
         var patientName = document.getElementById("patientName").value;   
         var url="menzhenSearch?startDay="+startDay+"&endDay="+endDay+"&patientName="+patientName;
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
        if(info=="nothing"){
             alert("没有搜索到任何门诊记录");
        }else if(info ==""){
        	//$(".psc").css("display","none");
        }else{
        	$(".pscx1").html('<tr>  <td>患者姓名</td> <td>性别</td> <td>年龄</td> <td>联系电话</td> <td>住址</td> <td>就诊时间</td> <td>复诊时间</td> <td>接诊医生</td> <td>血压mmHg</td> <td>症状体征（含辅助检查）</td> <td>初步诊断</td> <td>治疗用药情况</td>  <td>处置情况</td> </tr>');
            $(".pscx1").append(info);
            $(".psc").css("display","block");
            
            
        	$(".savea").click(function(){
    			var idsval = $(this).attr("data");
    			var sex = $(this).parents("tr").find(".sex").val(); 
    			var pname = $(this).parents("tr").find(".pname").val(); 
    			 var age = $(this).parents("tr").find(".age").val(); 
    			 var pnum = $(this).parents("tr").find(".pnum").val(); 
    			 var address = $(this).parents("tr").find(".address").val(); 
    			 var watchDate = $(this).parents("tr").find(".watchDate").val(); 
    			 var fuzhendate = $(this).parents("tr").find(".fuzhendate").val(); 
    			 var doctor = $(this).parents("tr").find(".doctor").val(); 
    			 var BP = $(this).parents("tr").find(".BP").val(); 
    			 var shoutizz = $(this).parents("tr").find(".shoutizz").val(); 
    			 var chubuzd = $(this).parents("tr").find(".chubuzd").val(); 
    			 var zhiliaoyy = $(this).parents("tr").find(".zhiliaoyy").val(); 
    			 var chuzhiqk = $(this).parents("tr").find(".chuzhiqk").val(); 
    			 
    			 
    			 var url="menzhendupdate?ids="+idsval+"&sex="+sex+"&age="+age+"&pnum="+pnum+"&address="+address+"&watchDate="+watchDate+"&fuzhendate="+fuzhendate+"&doctor="+doctor+"&BP="+BP+"&shoutizz="+shoutizz+"&chubuzd="+chubuzd+"&zhiliaoyy="+zhiliaoyy+"&chuzhiqk="+chuzhiqk+"&pname="+pname;
    		    
    			 if(req){
    		         //调用XMLHttpRequest的open方法
    		         req.open("get",url,true);
    		         
    		         //指定回调函数  
    		         req.send(null);
    		         alert("保存成功");
    		     } 
    			 
    		});
    		
        }
        
    }
}
 function edit(e){ 
	  $("table.pscx1 input:disabled").attr("disabled",""); 
	  $("table.pscx1 textarea:disabled").attr("disabled",""); 
	  $("table #edit1").css("display","none");
	  $("table .savea").parent("td").css("display","block");
 }
 function del(e){ 
	   var url="menzhendel?ids="+e;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         for(var i=0;i<$(".ids").length;i++){
        	 if($(".ids").eq(i).val()==e){ 
        		 $(".ids").eq(i).parents("tr").remove();
             }
         }  
         alert("删除成功！");
         req.send(null);
     }  
 }
 function add(){
	 $(".pscx1 tr").eq(0).after('<tr><td><input type="text" name="ids"   class="ids" style="display:none;"><input type="text" name="pname" class="pname"   style="width:70px;"></td><td><div style="width:100px"><input type="radio" name="sex4" class="sex" value="1" checked="checked">男 <input type="radio" name="sex4" class="sex" value="2">女 </div></td><td><input type="text" name="age" class="age"   style="width:50px;"></td> <td><input type="text" class="pnum" name="pnum"   style="width:100px;"></td><td><input type="text" class="address" name="address" style="width:400px;"  ></td><td><input type="text" style="width:100px;" class="watchDate" name="watchDate" onfocus="setday(this)"  ></td><td><input type="text" style="width:100px;" class="fuzhendate" name="fuzhendate" onfocus="setday(this)"  ></td><td><input type="text" class="doctor" name="doctor"   style="width:70px;"></td><td><input type="text" class="BP" name="BP"   style="width:70px;"></td><td><textarea class="shoutizz" name="shoutizz" rows="1" cols="110"></textarea></td><td><textarea class="chubuzd" name="chubuzd" rows="1" cols="110"></textarea></td><td><textarea class="zhiliaoyy" name="zhiliaoyy" rows="1" cols="110"></textarea></td><td><textarea class="chuzhiqk" name="chuzhiqk" rows="1" cols="110"></textarea></td> <td style="display: block;"><input type="button" value="保存" class="btn blue saveaa" > </td></tr>');
	 $(".saveaa").click(function(){
		  
			
			var sex = $(this).parents("tr").find(".sex").val(); 
			var pname = $(this).parents("tr").find(".pname").val(); 
			 var age = $(this).parents("tr").find(".age").val(); 
			 var pnum = $(this).parents("tr").find(".pnum").val(); 
			 var address = $(this).parents("tr").find(".address").val(); 
			 var watchDate = $(this).parents("tr").find(".watchDate").val(); 
			 var fuzhendate = $(this).parents("tr").find(".fuzhendate").val(); 
			 var doctor = $(this).parents("tr").find(".doctor").val(); 
			 var BP = $(this).parents("tr").find(".BP").val(); 
			 var shoutizz = $(this).parents("tr").find(".shoutizz").val(); 
			 var chubuzd = $(this).parents("tr").find(".chubuzd").val(); 
			 var zhiliaoyy = $(this).parents("tr").find(".zhiliaoyy").val(); 
			 var chuzhiqk = $(this).parents("tr").find(".chuzhiqk").val(); 
			 
			 
			 var url="menzhend?sex="+sex+"&age="+age+"&pnum="+pnum+"&address="+address+"&watchDate="+watchDate+"&fuzhendate="+fuzhendate+"&doctor="+doctor+"&BP="+BP+"&shoutizz="+shoutizz+"&chubuzd="+chubuzd+"&zhiliaoyy="+zhiliaoyy+"&chuzhiqk="+chuzhiqk+"&pname="+pname;
		    
			 if(req){
		         //调用XMLHttpRequest的open方法
		         req.open("get",url,true);
		         
		         //指定回调函数  
		         req.send(null);
		         alert("保存成功");
		     }  
			 
		});
 
 
 }
</script>
</head>
<body>
	<center>
		<h2>涞山医院门诊登记表</h2>
	</center>
	<div>
		<h3>搜索条件</h3>
		<div>
			就诊时间(例：2014-09-30)：<input type="text" name="startDay" id="startDay" class="myInput" onfocus="setday(this)" style="width:100px;"/>
			
			~~<input type="text" name="endDay" id="endDay" class="myInput" onfocus="setday(this)" style="width:100px;"/> 
			 患者姓名:<input type="text" name="patientName" id="patientName" class="myInput" style="width:100px;"/>
		</div>
		  <input type="button" value="查询" onclick="finds()" class="btn blue"> 
		  
	</div>	 
	<br><br><br><br>
	<div class="psc">
		<input type="button" value="修改" onclick="edit('pscx1')" class="btn blue" id="edit1">  
		<input type="button" value="添加" onclick="add()" class="btn blue" id="add">  
		<table  class="pscx1">
			<tr> <td></td> <td>患者姓名</td> <td>性别</td> <td>年龄</td> <td>联系电话</td> <td>住址</td> <td>就诊时间</td> <td>复诊时间</td> <td>接诊医生</td> <td>血压mmHg</td> <td>症状体征（含辅助检查）</td> <td>初步诊断</td> <td>治疗用药情况</td>  <td>处置情况</td> </tr>
		 </table> 
	</div>
	 
</body>
</html>