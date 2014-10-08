<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.disease.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript">
    
    var req; //异步传输对象
    if(window.XMLHttpRequest){
        
        req=new XMLHttpRequest();
        
    }else if(window.ActiveXObject){
         
        req=new ActiveXObject("Microsoft.XMLHTTP");
    }
    function search(){
        var anything=document.getElementById("seachText").value;
      
         var url="searchMedicine?seachText="+anything;
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
            $("#search").append(info);
        }
    }
     
    function add(i){
    	var pt=0;
        var price=document.getElementById("price"+i).value;
        var pillNum=document.getElementById("pillNum"+i).value;
        var pillName=document.getElementById("pillName"+i).value;
        var addList=document.getElementById("addList");
        var pillType=document.getElementsByName("pillType");
        var div_box = document.createElement("tr");
        var le=$("#addList tr").length;//添加的药品列表长度
        var pi=$(".pillType"+i).val();
        var pilltype=$("#pilltype").text();
         if(pilltype=="已创建中药药方"){
        	 pt=1;
         }else if(pilltype=="已创建西药药方"){
        	 pt=2;
         }
        div_box.id=i;
       /*  if(le>1&&pi!=$("#addList tr input").eq(0).val()){
        	alert("您选择的药品类型不对，请重新添加");
 		   return;
        } */ 
       if(le>1){
    	   if(pi!=$("#addList tr input").eq(0).val()){
    		   if($("#addList tr input").eq(0).val()==2&&(pt==1||pt==0)){
    			   if(pi==1||pi==3){
    				   alert("您选择的药品类型不对，请重新添加");
    		 		   return;
    			   }
    		   }else if(($("#addList tr input").eq(0).val()==1||$("#addList tr input").eq(0).val()==3)(pt==2||pt==0)){
    			   if(pi==2){
    				   alert("您选择的药品类型不对，请重新添加");
    		 		   return;
    			   }
    		   }
    	   }else{}
       }
        div_box.innerHTML=" <tr id=\""+i+"\"><input type=\"text\" name=\"pillType\" value=\""+pi+"\" style=\"display:none;\"><td><input type=\"text\" name=\"pillNum\" value=\""+pillNum+"\" style=\"display:none;\"><input type=\"text\" name=\"pillName\" value=\""+pillName+"\" readonly></td><td><input type=\"text\" name=\"price\" value=\""+price+"\" readonly></td><td><input type=\"text\"  name=\"amount\" id=\"amount\" onBlur=\"ischeckNum('amount');\">个</td><td><input type=\"button\" value=\"删除\" class=\"btn blue\" onclick=\"del("+i+")\"></td></tr>";
        addList.appendChild(div_box);
        }
        function del(a){
    document.getElementById(a).parentNode.removeChild(document.getElementById(a));
        }
        function dele(a){
        	 
        	 var patientId=document.getElementById("patientId").value;
             
             var url="deleMedicine?pillId="+a+"&patientId="+patientId;
             if(req){
                 //调用XMLHttpRequest的open方法
                 req.open("get",url,true);
                 
                 //指定回调函数
                 req.onreadystatechange=deleback;
                 req.send(null);
             }
        }
        function deleback(){
            if(req.readyState==4){
                var info=req.responseText;
                if(info){
                    alert("删除成功");
                    location.reload(); 
                }
                
                
            }
        }
        
        
        function confrim(){
            var amount=document.getElementsByName("amount");
            var disease=document.getElementById("disease");
            for(var a=0;a<amount.length;a++){
            	if(amount[a].value==null||amount[a].value == ""){
            		alert("请核对后输入");
            		break;
            	}else{
            		 disease.submit();
            	}
            }
        }
       
        
        window.document.onkeydown=myKeyPress;
        function myKeyPress(evt){
            //兼容IE和Firefox获得keyBoardEvent对象
            evt = (evt) ? evt : ((window.event) ? window.event : ""); 
             //兼容IE和Firefox获得keyBoardEvent对象的键值
            var key = evt.keyCode?evt.keyCode:evt.which; 
            if( key == 13 || key == 10 ){ 
 
            	search();  
            }
        }
        
        function check(){
        	 var patientId=document.getElementById("patientId").value;
          
             var url="checkPillType?patientId="+patientId;
             if(req){
                 //调用XMLHttpRequest的open方法
                 req.open("get",url,true);
                 
                 //指定回调函数
                 req.onreadystatechange=callbackcheck;
                 req.send(null);
             }
        }
        function callbackcheck(){
            if(req.readyState==4){
                var info=req.responseText;
                $("#pilltype").append(info);
            }
        }
</script>
</head>
<body onload="check()">
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<div id="pilltype"></div>
    <input type="text" name="seachText" id="seachText"><input type="button" value="搜索" class="btn blue" onclick="search()">


<table border="1" id="search">
<!--  
    <tr>
        <td>药品名称：</td>
        <td>是否添加：</td>
    </tr>
     <tr>
     <input type="text" name="price" value="12.00" style="display:none;">
     <input type="text" name="pillNum" value="1200" style="display:none;">
        <td><input type="text" name="pillName" value="1200" style="display:none;">头孢：</td>
        <td><input type="button" value="添加"></td>
    </tr>-->
    <br> <br> <br> <br>
    </table>
    药方具体内容：
    <form action="disease" method="post" id="disease">
    <input type="text" id="pcid" name="pcid" value=<%=request.getParameter("id") %> style="display:none;">
    <table id="addList">
    <tr>
        <td>药品名称：</td>
        <td>单价：</td>
        <td>数量：</td>
        <td>是否删除：</td>
    </tr>
    <% diseaseSearch dis=new diseaseSearch();
               //out.print(dis.pillfind(request.getParameter("patientId")));%>
    <!--  <tr> 
        <td>头孢</td>
        <td>20.00</td>
        <td>2</td>
        <td><input type="button" value="删除"></td>
    </tr>
     <tr>
        <td>伊可新</td>
        <td>40.00</td>
        <td>3</td>
        <td><input type="button" value="删除"></td>
    </tr>-->
</table>
<input type="text" value=<%=request.getParameter("patientId")%> name="patientId" id="patientId" style="display:none;">
<input type="text" value=<%=session.getAttribute("userId") %> name="doctorId" style="display:none;">
<input type="button" value="保存" onclick="confrim()" class="btn blue"> 
<!--保存后返回detail页面时“处方ID处显示生成的ID”  -->
</form>
</body>
</html>