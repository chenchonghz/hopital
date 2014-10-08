<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.pillList.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../styles/common.css" type="text/css">
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.jqprint.js"></script>
<style type="text/css" media=print>
            .btn blue{display : none } .PageNext{ PAGE-BREAK-AFTER: always }
        </style>
        
         <style media=print>
 .Noprint{display:none;}  .PageNext{page-break-after: always;}
body{text-align:right;}
 table{margin:130px auto 0;}
  
 </style>
        <SCRIPT language=javascript>
           /*  function printsetup() {
                // 打印页面设置 
                wb.execwb(8, 1);
            }
            function printpreview() {
                // 打印页面预览 
                wb.execwb(7, 1);

            }

            function printit() {
                if (confirm('确定打印吗？')) {
                    wb.execwb(6, 6);
                }
            }
            function printWithoutAlert() {     
            	wb.execwb(6,6);    
              } */
        </SCRIPT>
<title></title>
</head>
<body>
<%
//通过session防止访问者绕过认证
 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
     response.sendRedirect(request.getContextPath()+"/login.html");
 }
%>
<script type="text/javascript"> 
$(document).ready(function() { 
$("#print").click(function(){ 
$("#prt").jqprint(); 
  
}); 
}); 
 
</script> 
<!--调用active-->
        <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=3></OBJECT>
  <div id="prt">
                  
 <center>主治医生：（<%=session.getAttribute("username") %>） </center> 


 
   <center>药方具体内容：</center> 
    <table border="2" style="margin:20px auto;">
    <tr>
        <td>药品名称：</td>
        <td>规格：</td>
        <td>药品数量：</td>
         <td>药品单价</td>
    </tr>
     <% pillListSearch pill=new pillListSearch();
        out.print(pill.pillListSearch(request.getParameter("id"),request.getParameter("pcid"))); %> 
    <!--  <tr>
        <td>头孢</td>
        <td>100mg/瓶</td>
        <td>2</td>
        <td>20.00元</td>
    </tr>
     <tr>
        <td>伊可新</td>
        <td>30mg/瓶</td>
        <td>3</td>
        <td>12.00元</td>
    </tr> 
    <tr>
        <td>服用方法</td>
        <td  colspan="3">用水冲服，每日3次用水冲服每日3次</td>
    </tr>
    <tr>
        <td>总价</td>
        <td>90元</td>
        <td>药方日期</td>
        <td>2013-03-04</td>
    </tr> -->
</table>
<br><br> 

<div style="margin:20px auto;width:100px;"  >
医生_______________<br><br>
收费：_______________<br><br>
抓药人：_______________
</div>
 
        </div>
        <div class="Noprint">
        <input type=button name=button value="直接打印"   id="print" class="btn blue">
       </div>
        
</body>
</html>