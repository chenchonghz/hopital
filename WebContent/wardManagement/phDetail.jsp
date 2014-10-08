<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.commonSearch.*" %>
     <%@ page import="hospital.service.wardManagement.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 <html>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style >
.btn.blue { background: #2ae; 
            border-radius: 8px;
            padding:5px;
            margin:5px;
            width:100px;
}
 
input{
border:none;
border-bottom-style:solid ;
border-bottom-width: thin
}
 
#s ul{
float:left;
}
 ul{
 line-height:2;
 }
</style>
<script type="text/javascript" src="../scripts/common.js"></script>
<script type="text/javascript" src="../scripts/calendar.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.jqprint.js"></script>
 <script type="text/javascript"> 
$(document).ready(function() { 
$("#print").click(function(){ 
$(".prt").jqprint(); 
  
}); 
}); 
 
</script> 
 <title>Insert title here</title>

<OBJECT  id=WebBrowser  classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 style="display:none">
 </OBJECT>
 </noscript>
 <style media=print>
 .Noprint{display:none;}  .PageNext{page-break-after: always;}
 </style>
  <table align="center" class=NOPRINT>
   <tr>
    <td align="center"><input type=button name=button value="直接打印"   id="print" class="btn blue"></td>
  </tr>
 </table>
<div  class=NOPRINT> 以下为打印区:<br>
 </div>
 <hr size=1 noshadow color=black  class="NOPRINT" > 
  <div class="prt">
 <div class="PageNext">
  <h2><center>住院病案首页</center></h2>
  <p>第___次住院</p>
 <table>

 <% commonSearch com=new commonSearch();
out.print(com.patientDetailSearch(request.getParameter("patientId")));%>
 
</table><br><br>
<hr size=1 noshadow color=black   ><br><br>
<table  >
<% search com1=new search();
out.print(com1.wardDetailSearch(request.getParameter("patientId"))); 
 %>
 
</table><br><br>
<hr size=1 noshadow color=black   ><br><br>
<table border style="margin:0 100px" cellspacing="0">
	<thead>
	   <tr height="20px">
	       <td style="width:350px"><b>出院诊断</b></td><td><b>入院病情</b></td><td><b>出院病情</b></td> 
	   </tr>
	</thead>
	<tbody>
	<% search com2=new search();
 out.print(com2.outWardDSearch(request.getParameter("patientId"))); 
 %>
	 <!--  <tr height="20px" ><td>出院诊断</td><td>入院病情</td><td>出院情况</td> </tr>
       <tr height="20px"><td></td><td></td><td></td> </tr>
       <tr height="20px"><td></td><td></td><td></td> </tr>
       <tr height="20px"><td></td><td></td><td></td> </tr>
       <tr height="20px"><td></td><td></td><td></td> </tr>
       <tr height="20px"><td></td><td></td><td></td> </tr>
       <tr height="20px"><td></td><td></td><td></td> </tr>
       <tr height="20px"><td></td><td></td><td></td> </tr>  -->
	</tbody>
</table><br><br>
<hr size=1 noshadow color=black   ><br><br>
<table >
       <tr>
           <td>科主任<input type="text" style="width:150px"/></td><td>主任（副主任）医师<input type="text" style="width:150px"/></td><td>主治医师<input type="text" style="width:150px"/></td><td>住院医师<input type="text" style="width:150px"/></td> 
       </tr>
       <tr>
           <td>责任护士<input type="text" style="width:150px"/></td><td>进修医师<input type="text" style="width:150px"/></td><td>实习医师<input type="text" style="width:150px"/></td><td>编码员<input type="text" style="width:150px"/></td> 
       </tr>
</table> 
</div>
 <hr size=1 noshadow color=black  class="NOPRINT" >
 <div class="PageNext">
 <h2><center>出院记录</center></h2>
 <%search com9=new search();
 if(com9.outWardAllSearch(request.getParameter("patientId")).equals("")){
	 %>
	 <div id="s">
     <ul> <b>姓名</b>： </ul>
     <ul><b>性别</b>：  </ul>
     <ul><b>年龄</b>：  </ul>
     <ul><b>职业</b>：</ul>
     <ul><b>科别</b>：  </ul>
  </div> <br><br> <br><br>

 <ul><b>入院日期</b>： &nbsp&nbsp<b>出院日期</b>： </ul>
<ul><b>入院情况</b>：</ul>
 <ul><b>入院诊断</b>： </ul>
 <ul><b>诊断经过</b>：</ul>
<ul><b>出院情况</b>：</ul>
<ul><b>出院医嘱</b>：</ul>
	 <%
 }else{
	 search com3=new search(); out.print(com3.outWardAllSearch(request.getParameter("patientId")));
 }
 %>
   
 <!-- <div id="s">
     <ul> <b>姓名</b>： 李文友 </ul>
     <ul><b>性别</b>： 男 </ul>
     <ul><b>年龄</b>： 29岁 </ul>
     <ul><b>职业</b>： 农民 </ul>
     <ul><b>科别</b>： 心血管内科 </ul>
  </div> <br><br> <br><br>

 <ul><b>入院日期</b>：  2013-04-04&nbsp&nbsp<b>出院日期</b>： 2013-09-09 </ul>
<ul><b>入院情况</b>：空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉</ul>
 <ul><b>入院诊断</b>：1.冠心病 急性冠脉综合征 2.高血压2级 极高危 </ul>
 <ul><b>诊断经过</b>：空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯 
        空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯 
        空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯
        空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯顿飞据了解艾斯黛拉空间按时江苏旷达卡萨附近拉斯将阿斯</ul>
 <ul><b>出院诊断</b>：1.冠心病 急性冠脉综合征 2.高血压2级 极高危 </ul>
<ul><b>出院情况</b>：患者无胸闷、心悸等不适</ul>
<ul><b>出院医嘱</b>：1.注意休息2.坚持用药3.不适随诊</ul> -->
<ul style="margin:50px 0 0 400px"><b>主治医师</b>：______/<b>医师</b>：______</ul>
 
 </div>
 <hr size=1 noshadow color=black  class="NOPRINT" >
 <div class="PageNext">
 <h2><center>入院记录</center></h2>
  <%  search com4=new search();   out.print(com4.outWardrecordSearch(request.getParameter("patientId"))); %>
   <!-- <table>
    <tr style="height:50px"><td style="width:200px">姓名：</td><td >籍贯：</td></tr>
    <tr style="height:50px"><td>性别：</td><td>现住址：</td></tr>
    <tr style="height:50px"><td>年龄：</td><td>工作单位：</td></tr>
    <tr style="height:50px"><td>民族：</td><td>入院时间：</td></tr>
    <tr style="height:50px"><td>婚姻：</td><td>职业：</td></tr>
  </table>
  <ul><b>主诉</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>现病史</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>既往史</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>个人史</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>家族史</b>：患者无胸闷、心悸等不适</ul>-->
 </div>
 <hr size=1 noshadow color=black  class="NOPRINT" >
 <div class="PageNext">
 <h2><center>体格检查</center></h2>
 <%  search com5=new search();   out.print(com5.bodyCheckDetail(request.getParameter("patientId"))); %>
  
 <!-- <div id="s">
 <ul>T  ℃</ul><ul>P 次/分</ul><ul>R 次/分</ul><ul>BP mmHg</ul>
 </div><br><br><br><br>
  <ul><b>一般情况</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>皮肤黏膜</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>淋巴结</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>头部</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>颈部</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>胸部</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>肺部</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>心脏</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>腹部</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>肛门与直肠及生殖器</b>：患者无胸闷、心悸等不适</ul>
   <ul><b>脊柱四肢</b>：患者无胸闷、心悸等不适</ul>
  <ul><b>神经系统</b>：患者无胸闷、心悸等不适</ul>-->
 </div> 
 <hr size=1 noshadow color=black  class="NOPRINT" >
 <div class="PageNext">
 <h2><center>长期医嘱单</center></h2>
  <table border="1" style="margin:0 100px;" cellspacing="0">
    <tr>
        <td colspan="4"><center>开始</center></td><td colspan="3"><center>停止</center></td>
    </tr>
    <tr>
    <td style="width:140px">起始时间</td><td style="width:300px">医嘱内容</td><td style="width:50px">医生</td><td style="width:50px">护士</td><td style="width:140px">停止时间</td><td style="width:50px">医生</td><td style="width:50px">护士</td>
    </tr>
     <%  search com6=new search();   out.print(com6.docTellSearch(request.getParameter("patientId"))); %>
    <!--<tr>
    <td>2012-06-03 17:40</td><td>1级护理</td><td>朱晓阳</td><td>杨洪超</td><td>2012-06-19 09:40</td><td>朱晓阳</td><td>杨洪超</td>
    </tr>  -->
 
     
  </table>
 </div>
 <hr size=1 noshadow color=black  class="NOPRINT" >
 <div class="PageNext">
 <h2><center>临时医嘱单</center></h2>
  <table border="1" style="margin:0 100px;" cellspacing="0">
     
    <tr>
    <td style="width:140px">日期</td><td style="width:300px">医嘱内容</td><td style="width:50px">医生</td><td style="width:140px">执行时间</td><td style="width:50px">护士</td> 
    </tr>
     <%  search com7=new search();   out.print(com7.docTellLSearch(request.getParameter("patientId"))); %>
    <!--<tr>
    <td> </td><td> </td><td> </td><td> </td><td> </td> 
    </tr>  -->
 
     
  </table>
 </div>
  <hr size=1 noshadow color=black  class="NOPRINT" >
  <div class="PageNext">
 <h2><center>出院消费账单</center></h2>
   <table border="1" style="margin:0 100px;" cellspacing="0">
         <thead>
            <tr>
                <td>患者编号</td>
                <td>患者姓名</td>
                <td>明细</td>
                <td>价格</td>
                <td>缴费状态</td>
                <td>收据编号</td>
            </tr>
         </thead>
         <tbody id="freash">
         <%  search com8=new search();   out.print(com8.patientOutSearch(request.getParameter("patientId"))); %>
             <!-- <tr>
                <td><a href="outWardDetail.jsp?patientId=201306061">201306061</a></td>
                <td>qiuye</td>
                <td>中药</td>
                <td>20.00元</td>
                <td>未交费</td>
                <td>收据编号</td>
            </tr>      
            <tr><td colspan="6">总价：500元</td></tr> --> 
         </tbody>
    </table>
 </div>
 </div>
 </html>