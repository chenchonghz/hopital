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
<script type="text/javascript" src="scripts/Calendar2.js"></script>
<script type="text/javascript" src="scripts/common.js"></script>

<script type="text/javascript">
var req; //异步传输对象
var cha=0;//申请的天数



var dateNum=0;

if(window.XMLHttpRequest){
    
    req=new XMLHttpRequest();
    
}else if(window.ActiveXObject){
     
    req=new ActiveXObject("Microsoft.XMLHTTP");
}
function save(){
	var days=document.getElementsByName("days");
	var d1=document.getElementById("startday").value;
    var d2=document.getElementById("endday").value;
    var day;
    if(Date.parse(d1.replace("-","/")) > Date.parse(d2.replace("-","/"))){
        alert("开始时间必须小于结束时间，请核对");
        return;
    }
    
    
    var day = 24 * 60 * 60 *1000;
    try{
    	if(d1!=d2){
    		var dateArr = d1.split("-");
    	       var checkDate = new Date();
    	            checkDate.setFullYear(dateArr[0], dateArr[1]-1, dateArr[2]);
    	       var checkTime = checkDate.getTime();
    	      
    	       var dateArr2 = d2.split("-");
    	       var checkDate2 = new Date();
    	            checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1, dateArr2[2]);
    	       var checkTime2 = checkDate2.getTime();
    	        
    	        cha = (checkTime2 - checkTime)/day+1;  
    	}
       
        
        }catch(e){
        return false;
    }
    
    
    
	for(var i=0;i<days.length;i++){
		if(days[i].checked){
			day=days[i].value;
		}
	}
  if(cha!=0){
	  dateNum=cha;
  }else{
	  dateNum=day;
  }
   
  var userId=document.getElementById("userId").value;
     var url="reqVacate?userId="+userId+"&startday="+d1+"&endday="+d2+"&dateNum="+dateNum;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callbackSave;
         req.send(null);
     }
}

function saveNow(id){
    var days=document.getElementsByName("days");
    var d1=document.getElementById("startday").value;
    var d2=document.getElementById("endday").value;
    var day;
    
    
    
    var day = 24 * 60 * 60 *1000;
    try{
        if(d1!=d2){
            var dateArr = d1.split("-");
               var checkDate = new Date();
                    checkDate.setFullYear(dateArr[0], dateArr[1]-1, dateArr[2]);
               var checkTime = checkDate.getTime();
              
               var dateArr2 = d2.split("-");
               var checkDate2 = new Date();
                    checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1, dateArr2[2]);
               var checkTime2 = checkDate2.getTime();
                
                cha = (checkTime2 - checkTime)/day+1;  
        }
       
        
        }catch(e){
        return false;
    }
    
    
    
    for(var i=0;i<days.length;i++){
        if(days[i].checked){
            day=days[i].value;
        }
    }
  if(cha!=0){
      dateNum=cha;
  }else{
      dateNum=day;
  }
   
     var url="updateVacate?id="+id+"&startday="+d1+"&endday="+d2+"&dateNum="+dateNum;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callbackSave;
         req.send(null);
     }
}
function callbackSave(){
    if(req.readyState==4){
        var info=req.responseText;
        if(info){
            alert("保存成功");
        }
        location.reload(); 
    }
}
     
   
    
    function DateDiff(){//d1  is end date
        var day = 24 * 60 * 60 *1000;
    try{ 
    	var d1=document.getElementById("startday").value;
    	var d2=document.getElementById("endday").value;
       var dateArr = d1.split("-");
       var checkDate = new Date();
            checkDate.setFullYear(dateArr[0], dateArr[1]-1, dateArr[2]);
       var checkTime = checkDate.getTime();
      
       var dateArr2 = d2.split("-");
       var checkDate2 = new Date();
            checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1, dateArr2[2]);
       var checkTime2 = checkDate2.getTime();
        
        cha = (checkTime2 - checkTime)/day;  
        if(cha!=0){
            alert("申请日期大于一天，不可以选择半天");
           days[1].checked=true;
           
        }  
        }catch(e){
        return false;
    }
        
      
    }//end fun
    
    function DateDeff(){//d1  is end date
        var day = 24 * 60 * 60 *1000;
    try{ 
        var d1=document.getElementById("startday").value;
        var d2=document.getElementById("endday").value;
       var dateArr = d1.split("-");
       var checkDate = new Date();
            checkDate.setFullYear(dateArr[0], dateArr[1]-1, dateArr[2]);
       var checkTime = checkDate.getTime();
      
       var dateArr2 = d2.split("-");
       var checkDate2 = new Date();
            checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1, dateArr2[2]);
       var checkTime2 = checkDate2.getTime();
        
        cha = (checkTime2 - checkTime)/day;  
        
        }catch(e){
        return false;
    }
   }
    
 function search(e){
	 var url="ssearchVacate?id="+e;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callbackSearch;
         req.send(null);
     }
 }
 
 function callbackSearch(){
	    var now=document.getElementById("now");
	    if(req.readyState==4){
	        var info=req.responseText;
	        if(info!=""){
	           now.innerHTML=info;
	        }
	    }
	}
 function deleNow(id){
	 var url="deleVacate?id="+id;
     if(req){
         //调用XMLHttpRequest的open方法
         req.open("get",url,true);
         
         //指定回调函数
         req.onreadystatechange=callbackDele;
         req.send(null);
     }
 }
 
 function callbackDele(){
	    if(req.readyState==4){
	        var info=req.responseText;
	        if(info){
	            alert("删除成功");
	        }
	        location.reload(); 
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
<input type="text" value=<%=session.getAttribute("userId") %> name="userId" id="userId" style="display:none;">
<table id ="now">
   
    <tr>
        <td>请假日期 <input type="text"   id="startday" name="startday"
                    style="border: 1px solid #999;"
                    onfocus="setday(this)">——
                     <input type="text"   id="endday" name="endday"
                    style="border: 1px solid #999;"
                    onfocus="setday(this)"></td>
        <td><input type="radio" id="days" name="days" value="0.5" onclick="DateDiff()">半天
            <input type="radio" id="days" name="days" value="1" >全天</td>            
        
    </tr>
     <tr>
     <td colspan="2"><input type="button" value="保存" onclick="save()" id="baocun"  class="btn blue">
                   </td>
    </tr> 
  
    </table>
  
    <table>
    <caption>请假记录</caption>
    <tr>
        <td>请假日期</td>
        <td>天数（单位：天）</td>
        <td>申请状态</td>
        
    </tr>
     <% reqVacate req=new reqVacate();
               out.print(req.vacateSearch(session.getAttribute("userId").toString()));%>
   <!--<tr>
        <td><a href="javascript:search(14)">2013-08-18~2013-08-19</a></td>
        <td><center>2</center></td>
        <td>审批中</td>
    </tr>
     <tr>
        <td>2013-08-12</td>
        <td><center>0.5</center></td>
        <td>已审批</td>
    </tr>
     <tr>
        <td>2013-08-08</td>
        <td><center>1</center></td>
        <td>已审批</td>
    </tr>
     <tr>
        <td colspan="3"> 已休假总天数：1.5 </td>
    </tr>  --> 
    
</table>
</body>
</html>