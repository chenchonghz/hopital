<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="hospital.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles/common.css" type="text/css">
 <script type="text/javascript" src="scripts/common.js"></script>
 
</head>
<body>
      <h1> 涞山医院医疗信息管理系统V1.0</h1> 
            
            <table   id="mytable" style="width:725px">
	            <tr>
	               <td class="alt" style="width:480px">公告内容</td>
	               <td class="alt">发生时间</td>
	            </tr>
            </table>
			<table id="mytable" >
			    <tbody><tr>
			      <td>
			<div id="demohq" style="overflow:hidden;height:100px;" >
			
			    <div id="demohq1" >
			    <table id="mytable" style="background:#E6EAE9;">
			        <tbody>
			        <%  newsReg nr=new newsReg();
			            out.print(nr.newsSearch());%>
			        </tbody>
			    </table>
			    </div>
			
			    <div id="demohq2">
			    <table id="mytable" style="background:#E6EAE9;">
			        <tbody>
			             <% out.print(nr.newsSearch());%>
			        </tbody>
			    </table>
			    </div>
			 
			</div>
			      </td>
			    </tr>
			  </tbody>
			</table>            
           
  
 
 
 
			 <table   id="mytable"  style="width:720px">
			 <h3>复查提示</h3>
			 <thead>
			    <tr>
			        <td class="alt" style="width:100px">患者姓名</td>
			        <td class="alt" style="width:210px">联系方式</td>
			        <td class="alt" style="width:160px">看病日期</td>
			        <td class="alt" >复查日期</td>
			    </tr>
			    </thead>
			    <tbody> </tbody>
			  
			</table>
			<table>
			<tbody><tr>
			      <td>
			<div id="qemohq" style="overflow:hidden;height:100px;" >
			
			    <div id="qemohq1" >
			    <table id="mytable" style="background:#E6EAE9;">
			        <tbody>
			        <% reviewCheck rc=new reviewCheck();
			            out.print(rc.reviewSearch()); %>
			        </tbody>
			    </table>
			    </div>
			
			    <div id="qemohq2">
			    <table id="mytable" style="background:#E6EAE9;">
			        <tbody>
			               <%  out.print(rc.reviewSearch()); %>
			        </tbody>
			    </table>
                </div>
 
			</div>
			      </td>
			    </tr>
			  </tbody>
			</table> 
			 
			
			 <table   id="mytable"  style="width:720px">
             <h3>设备年检提示</h3>
             <thead>
                <tr>
                    <td class="alt" style="width:107px">设备名称</td>
                    <td class="alt" style="width:206px">设备编号</td>
                    <td class="alt" style="width:160px">上次检查日期</td>
                    <td class="alt" >年检周期</td>
                </tr>
                </thead>
                <tbody> </tbody>
              
            </table>
            <table>
            <tbody><tr>
                  <td>
            <div id="wemohq" style="overflow:hidden;height:100px;" >
            
                <div id="wemohq1" >
                <table id="mytable" style="background:#E6EAE9;">
                    <tbody>
                    <%  
                        out.print(rc.equiReviewSearch()); %>
                    </tbody>
                </table>
                </div>
            
                <div id="wemohq2">
                <table id="mytable" style="background:#E6EAE9;">
                    <tbody>
                           <%  out.print(rc.equiReviewSearch()); %>
                    </tbody>
                </table>
                </div>
 
            </div>
                  </td>
                </tr>
              </tbody>
            </table> 
            
            
			
 
<script>
var speedhq=60;
function Marqueehq(){
    var demohq=document.getElementById("demohq");
    var demohq1=document.getElementById("demohq1");
    if(demohq.scrollTop==demohq1.offsetHeight){
        demohq.scrollTop=0;
    }else{
        demohq.scrollTop++;
    }
}
var MyMarhq=setInterval(Marqueehq,speedhq);
demohq.onmouseover=function() {clearInterval(MyMarhq);}
demohq.onmouseout=function() {MyMarhq=setInterval(Marqueehq,speedhq);}


function qarqueehq(){
    var qemohq=document.getElementById("qemohq");
    var qemohq1=document.getElementById("qemohq1");
    if(qemohq.scrollTop==qemohq1.offsetHeight){
        qemohq.scrollTop=0;
    }else{
        qemohq.scrollTop++;
    }
}
var qyMarhq=setInterval(qarqueehq,speedhq);
qemohq.onmouseover=function() {clearInterval(qyMarhq);}
qemohq.onmouseout=function() {qyMarhq=setInterval(qarqueehq,speedhq);}


function warqueehq(){
    var wemohq=document.getElementById("wemohq");
    var wemohq1=document.getElementById("wemohq1");
    if(wemohq.scrollTop==wemohq1.offsetHeight){
        wemohq.scrollTop=0;
    }else{
        wemohq.scrollTop++;
    }
}
var wyMarhq=setInterval(warqueehq,speedhq);
wemohq.onmouseover=function() {clearInterval(wyMarhq);}
wemohq.onmouseout=function() {wyMarhq=setInterval(warqueehq,speedhq);}
</script> 

</body>
</html>