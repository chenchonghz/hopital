<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	 <%@ page import="hospital.service.wardManagement.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
 
<title>Insert title here</title>
</head>
<body>
	<form action="bodycheck" method="post" id="bodycheck">
		<input type="text" name="patientId" id="patientId"
			value=<%=request.getParameter("patientId")%>  style="display:none">
			
		<table>
			<% search dis=new search();
			if(!dis.patientBFind(request.getParameter("patientId"))){
			    %><tr>
                <td colspan="2">T<input type="text" value="" id="T" name="T" style="width:30px"><input type="text" value="" id="t" name="t" style="display:none;">℃&nbsp;&nbsp;
                                P<input type="text" value="" id="P" name="P" style="width:30px">次/分&nbsp;&nbsp;
                                R<input type="text" value="" id="R" name="R" style="width:30px">次/分&nbsp;&nbsp;
                                BP<input type="text" value="" id="BP" name="BP" style="width:50px">mmHg
                </td>
            </tr>
            <tr>
                <td>一般情况：</td>
                <td><textarea rows="6" cols="80" name="generalCase"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="generalCase"  ></textarea></td>
            </tr>
            <tr>
                <td>皮肤黏膜：</td>
                <td><textarea rows="6" cols="80" name="skin"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="skin"  ></textarea></td>
            </tr>
            <tr>
                <td>淋巴结：</td>
                <td><textarea rows="6" cols="80" name="lymph"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="lymph"  ></textarea></td>
            </tr>
            <tr>
                <td>头部：</td>
                <td><textarea rows="6" cols="80" name="head"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="head" ></textarea></td>
            </tr>
            <tr>
                <td>颈部：</td>
                <td><textarea rows="6" cols="80" name="neck"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="neck" ></textarea></td>
            </tr>
            <tr>
                <td>胸部：</td>
                <td><textarea rows="6" cols="80" name="breast"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="breast" ></textarea></td>
            </tr>
            <tr>
                <td>肺部：</td>
                <td><textarea rows="6" cols="80" name="lung"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="lung" ></textarea></td>
            </tr>


            <tr>
                <td>心脏：</td>
                <td><textarea rows="6" cols="80" name="heart"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="heart"></textarea>
                </td>
            </tr>
            <tr>
                <td>腹部：</td>
                <td><textarea rows="6" cols="80" name="stomach"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="stomach" ></textarea></td>
            </tr>
            <tr>
                <td>肛门与直肠<br>及生殖器：</td>
                <td><textarea rows="6" cols="80" name="anus"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="anus" ></textarea></td>
            </tr>
            <tr>
                <td>脊柱四肢：</td>
                <td><textarea rows="6" cols="80" name="spinal"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="spinal" ></textarea></td>
            </tr>
            <tr>
                <td>神经系统：</td>
                <td><textarea rows="6" cols="80" name="nervous"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="nervous" ></textarea></td>
            </tr><%
			}else{out.print(dis.bodyCheckSearch(request.getParameter("patientId"))); 
			    
			 }
%>
 
		</table>
		<input type="submit" value="保存"   class="btn blue">

	</form>
</body>
</html>