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

    <form action="outWard" method="post"  >
        <input type="text" name="patientId" id="patientId"
            value=<%=request.getParameter("patientId")%>  style="display:none">
             
        <table>
            <% search dis=new search();
            out.print(dis.outWardSearch(request.getParameter("patientId")));%>


           <!--    
            <tr>
                <td>诊疗经过：<input type="text" name="id" id="id"
            value=""  style="display:none"></td>
                <td><textarea rows="6" cols="80" name="treatment"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="treatment"  ></textarea></td>
            </tr>
             <tr>
                <td>出院诊断：</td>
                <td><textarea rows="6" cols="80" name="outWardDiagnosis"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="outWardDiagnosis"  ></textarea></td>
            </tr>
             <tr>
                <td>出院情况：</td>
                <td><textarea rows="6" cols="80" name="outWardConditions"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="outWardConditions"  ></textarea></td>
            </tr>
             <tr>
                <td>出院医嘱：</td>
                <td><textarea rows="6" cols="80" name="outWardTell"
                        onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"
                        id="outWardTell"  ></textarea></td>
            </tr>-->
        </table>
        <input type="submit" value="保存"   class="btn blue">
    </form>
    <br><br><br><br><a  target="_blank" href="phDetail.jsp?patientId=<%=request.getParameter("patientId")%>">导出入院详细记录</a>
</body>
</html>