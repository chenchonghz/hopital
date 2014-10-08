<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="hospital.service.wardManagement.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/Calendar2.js"></script>

<script type="text/javascript">
var i=1;
function confrim(){
	var had=document.getElementsByName("had");
	var excuteDate=document.getElementsByName("excuteDate") ;
	var stopDate=document.getElementsByName("stopDate") ;
	var excuteId=document.getElementsByName("excuteId");
	
	for(var i=had.length;i<excuteDate.length;i++){
		if(excuteId[i].value==''){alert('请您填写执行者编号');return;
		}else if(excuteDate[i].value==null&&stopDate[i].value==null){alert('请您填写执行或结束日期');return;}
		 //excute[i].value=Date.parse(new Date(excute[i].value.replace(/-/g,"/")));    
		   // stop[i].value=Date.parse(new Date(stop[i].value.replace(/-/g,"/"))); 
		   // if(stop[i].value-excute[i].value<0){alert('执行日期需在结束日期之前，请重新选择');return;
		    //}else if(excuteId[i].value==''){alert('请您填写执行者编号');return;
		    //}else if(excuteDate[i].value==null&&stopDate[i].value==null){alert('请您填写执行或结束日期');return;
		    //}
	}
   

    var docTell=document.getElementById("docTell");
    docTell.submit();
         
}
    //window.document.onkeydown=enterSumbit;
    function enterSumbit(e){  
       if (e.keyCode == 13){  
    	   confrim();  
       }  
     }
    function addOne(){
    	i++;
    	 var tell=document.getElementById("tell");
    	 var docTellId=document.getElementById("patientId").value;
    	 var div_box = document.createElement("table");
    	 div_box.id="tell"+i;
    	 div_box.innerHTML="<tr> <td>医嘱编号</td> <td><input type=\"text\" name=\"id\" id=\"id\" value=\""+docTellId+i+"\" readonly /><input type=\"text\" name=\"docTellId\" id=\"docTellId\"  value=\""+docTellId+"\" style=\"display:none\"/> </td> </tr><tr> <td>医生编号</td> <td><input type=\"text\" name=\"docId\" id=\"docId\" value=\"\" /> </td> </tr> <tr> <td>医嘱内容</td> <td><textarea rows=\"6\" cols=\"80\" name=\"docTellContent\" id=\"docTellContent\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"medicineUseMethod\"></textarea> </td> </tr><tr> <td>执行日期</td> <td><input type=\"text\" name=\"excuteDate\" id=\"excuteDate\" style=\"border: 1px solid #999;\" onfocus=\"setday(this)\"></td>  </tr> <tr> <td>执行者编号</td> <td><input type=\"text\" name=\"excuteId\" id=\"excuteId\" value=\"\" /> </td> </tr><tr>  <td>医嘱类型</td> <td><select style=\"width: 60px\" id=\"docTellType\" name=\"docTellType\"> <option value=\"1\" selected>长期</option> <option value=\"2\">临时</option> </select> </td> </tr><tr> <td>医嘱结束日期</td> <td><input type=\"text\" name=\"stopDate\" id=\"stopDate\" value=\"\" style=\"border: 1px solid #999;\" onfocus=\"setday(this)\" /> <input style=\"float:right\" type=\"button\" value=\"删除该医嘱\" onclick=\"delOne("+i+")\" class=\"btn blue\"></td> </tr> ";
    	 tell.appendChild(div_box);
    	 return i;
    }
    function delOne(i){
    	
    	$("#tell"+i).append("<!---->");
         i--;
         return i;
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
 
	 <form action="docTell" method="post" id="docTell">
	 <div id="tell">
	 <input type="text" name="patientId" id="patientId" style="display:none"
                    value="<%=request.getParameter("patientId") %>" />
		<table id="tell1">
		<% search ser=new search();
        out.print(ser.docTellDetailSearch(request.getParameter("patientId")));%>
		
		
		<!--  
		
			<tr>
				<td>医嘱编号<input type="text" name="id" id="id" value="<%=request.getParameter("patientId") %>1" style="display:none" /></td>
				<td><input type="text" name="docTellId" id="docTellId" readonly
					value="<%=request.getParameter("patientId") %>" />
					
				</td>
			</tr>
			<tr>
				<td>医生编号</td>
				<td><input type="text" name="docId" id="docId" value="" onBlur="ischeckNum('docId');"/>
				</td>
			</tr>
			<tr>
				<td>医嘱内容</td>
				<td><textarea rows="6" cols="80" name="docTellContent" id="docTellContent" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea>
				</td>
			</tr>

			<tr>
				<td>执行日期</td>
				<td><input type="text" name="excuteDate" id="excuteDate"
					style="border: 1px solid #999;"
					onclick="fPopCalendar(event,this,this)" onfocus="this.select()"></td>
			</tr>
			<tr>
				<td>执行者社编</td>
				<td><input type="text" name="excuteId" id="excuteId" value="" onBlur="ischeckNum('excuteId');"/>
				</td>
			</tr>
			<tr>
				<td>医嘱类型</td>
				<td><select style="width: 60px" id="docTellType"
					name="docTellType">
						<option value="1" selected>长期</option>
						<option value="2">临时</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>医嘱结束日期</td>
				<td><input type="text" name="stopDate" id="stopDate" value=""
					style="border: 1px solid #999;"
					onclick="fPopCalendar(event,this,this)" onfocus="this.select()" />
				</td>
			</tr>-->
		</table>
		
		</div>
		 
		<input type="button" value="保存" onclick="confrim()" class="btn blue">
		<input type="button" value="添加医嘱" onclick="addOne()" class="btn blue">
	</form>
</body>
</html>