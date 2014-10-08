<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="hospital.service.wardManagement.*" %>
     <%@ page import="hospital.service.disease.*" %>
      <%@ page import="hospital.service.commonSearch.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/Calendar2.js"></script>
<script type="text/javascript">
    
    
    var req; //异步传输对象
     if(window.XMLHttpRequest){
         
         req=new XMLHttpRequest();
         
     }else if(window.ActiveXObject){    
          
         req=new ActiveXObject("Microsoft.XMLHTTP");
     }
   
     function search(){
         var anything=document.getElementById("seachText").value;
          var url="searchPD?seachText="+anything;
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
             $("#freash").html("");
             $("#freash").append(info);
             
         }
     }
   function searchSimilar(){
	   var anything=document.getElementById("seachSPText").value;
       var url="searchSP?seachText="+anything;
       if(req){
           //调用XMLHttpRequest的open方法
           req.open("get",url,true);
           
           //指定回调函数
           req.onreadystatechange=callbackSimilarSearch;
           req.send(null);
       }
   }
   function callbackSimilarSearch(){
       if(req.readyState==4){
           var info=req.responseText;
           if(info==""){
               alert("没有搜索到任何结果，请重新输入");
           }
           $("#similarPerson").html("");
           $("#similarPerson").append(info);
           
       }
   }
    
  /*   window.document.onkeydown=myKeyPress;
    function myKeyPress(evt){
        //兼容IE和Firefox获得keyBoardEvent对象
        evt = (evt) ? evt : ((window.event) ? window.event : ""); 
         //兼容IE和Firefox获得keyBoardEvent对象的键值
        var key = evt.keyCode?evt.keyCode:evt.which; 
        if( key == 13 || key == 10 ){ 
        	search();  
        }
    } */
</script>
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<div  style="height:100%;width:100%;overflow-x:auto;">
	<h1>患者解基本信息</h1>
	
	<input type="text" name="seachText" id="seachText">
	<input class="btn blue" type="button" value="搜索" onclick="search()">
	
 <form action=pasteModify method="post" >
	 
	<table border="1" id="freash"> </table>
	
	<hr>
	<h2>搜索相似病人病历</h2>
	患者编号：<input type="text" name="seachSPText" id="seachSPText">
	<input class="btn blue" type="button" value="搜索" onclick="searchSimilar()">
	
	<div id="similarPerson">
	
		<!--   <h3><center>出院记录</center></h3>
		 <div>
		 <table>
		 
		<tr><td><b>入院情况</b>：</td><td colspan=3><textarea rows="6" cols="80" name="patientStatus"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="patientStatus"  ></textarea></td></tr>
		 <tr><td><b>入院诊断</b>：</td><td colspan=3> <textarea rows="6" cols="80" name="doctorDiagnoseResult"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="doctorDiagnoseResult"  ></textarea></td></tr>
		 <tr><td><b>诊断经过</b>：</td><td colspan=3><textarea rows="6" cols="80" name="treatment"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="treatment"  ></textarea></td></tr>
		 <tr><td><b>出院诊断</b>：</td><td colspan=3><textarea rows="6" cols="80" name="outWardDiagnosis"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="outWardDiagnosis"  ></textarea></td></tr>
		<tr><td><b>出院情况</b>：</td><td colspan=3><textarea rows="6" cols="80" name="outWardConditions"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="outWardConditions"  ></textarea></td></tr>
		<tr><td><b>出院医嘱</b>：</td><td colspan=3><textarea rows="6" cols="80" name="outWardTell"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="outWardTell"  ></textarea></td></tr>
		 </table>
		</div>
		 <h3><center>入院记录</center></h3>
		 <div>
		 <table>
		  <tr><td><b>主诉</b>：</td><td  ><textarea rows="6" cols="80" name="patientStatus"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="patientStatus"  ></textarea></td></tr>
		  <tr><td><b>现病史</b>：</td><td  ><textarea rows="6" cols="80" name="nowDisHis"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="nowDisHis"  ></textarea></td></tr>
		  <tr><td><b>既往史</b>：</td><td  ><textarea rows="6" cols="80" name="pastDisHis"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="pastDisHis"  ></textarea></td></tr>
		  <tr><td><b>个人史</b>：</td><td  ><textarea rows="6" cols="80" name="bodyHis"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="bodyHis"  ></textarea></td></tr>
		  <tr><td><b>家族史</b>：</td><td  ><textarea rows="6" cols="80" name="famHis"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="famHis"  ></textarea></td></tr>   
		 </table>
		  </div>
		  <h3><center>体格检查</center></h3>
		  <div>
		  <table>
		   <tr><td>T<input type="text" value="" id="T" name="T" style="width:30px">℃</td>
                 <td>P<input type="text" value="" id="P" name="P" style="width:30px">次/分</td>
                   <td>R<input type="text" value="" id="R" name="R" style="width:30px">次/分</td>
                   <td>BP<input type="text" value="" id="BP" name="BP" style="width:50px">mmHg</td></tr>
		  <br> 
		  <tr><td><b>一般情况</b>：</td><td colspan=3><textarea rows="6" cols="80" name="generalCase"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="generalCase"  ></textarea></td></tr>
		  <tr><td><b>皮肤黏膜</b>：</td><td colspan=3><textarea rows="6" cols="80" name="skin"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="skin"  ></textarea></td></tr>
		  <tr><td><b>淋巴结</b>：</td><td colspan=3><textarea rows="6" cols="80" name="lymph"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="lymph"  ></textarea></td></tr>
		  <tr><td><b>头部</b>：</td><td colspan=3><textarea rows="6" cols="80" name="head"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="head" ></textarea></td></tr>
		  <tr><td><b>颈部</b>：</td><td colspan=3><textarea rows="6" cols="80" name="neck"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="neck" ></textarea></td></tr>
		  <tr><td><b>胸部</b>：</td><td colspan=3><textarea rows="6" cols="80" name="breast"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="breast" ></textarea></td></tr>
		  <tr><td><b>肺部</b>：</td><td colspan=3><textarea rows="6" cols="80" name="lung"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="lung" ></textarea></td></tr>
		  <tr><td><b>心脏</b>：</td><td colspan=3><textarea rows="6" cols="80" name="heart"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="heart"></textarea></td></tr>
		  <tr><td><b>腹部</b>：</td><td colspan=3><textarea rows="6" cols="80" name="stomach"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="stomach" ></textarea></td></tr>
		  <tr><td><b>肛门与直肠及生殖器</b>：</td><td colspan=3><textarea rows="6" cols="80" name="anus"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="anus" ></textarea></td></tr>
		   <tr><td><b>脊柱四肢</b>：</td><td colspan=3><textarea rows="6" cols="80" name="spinal"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="spinal" ></textarea></td></tr>
		  <tr><td><b>神经系统</b>：</td><td colspan=3><textarea rows="6" cols="80" name="nervous"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="nervous" ></textarea></td></tr>  
		  </table>
			</div>-->
	
	</div>
	
	
	
	
	<br><br>
	<input type="submit" class="btn blue" value="保存"   > 
 </form>
 
</div>
      
</body>
</html>