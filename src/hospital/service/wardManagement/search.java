package hospital.service.wardManagement;

import hospital.dao.dbConn;
import hospital.service.infoReg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class search {
	static Connection conn;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt2=null;
	static PreparedStatement pstmt3=null;
	static ResultSet rs=null;
	StringBuffer sb = new StringBuffer();

	
	 static java.util.Date date = new java.util.Date();
	 static SimpleDateFormat sy1=new SimpleDateFormat("yyyyMMdd");
	 static String dateFormat=sy1.format(date);
	 static SimpleDateFormat sy2=new SimpleDateFormat("HH:mm:ss");
	 static String dateFormat2=sy2.format(date);
	 static SimpleDateFormat sy3=new SimpleDateFormat("yyyy-MM-dd");
	 static String dateFormat3=sy3.format(date);
	 java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");    
     java.util.Date beginDate;
     java.util.Date endDate;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(dateFormat2);
	}
	
	public String patientSearch(){
		String patientId="";
		String patientName="";
		String creatDate="";
		String wardStatus="";
		String str="select patient_id,patient_name,creat_date,ward_status from patient  order by creat_date desc;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				  patientId=num.getString("patient_id");
				 patientName=num.getString("patient_name");
				 creatDate=num.getString("creat_date");
				 wardStatus=num.getString("ward_status");
				 sb.append("<tr> <td><a href=\"wardDetail.jsp?patientId="+patientId+"\">"+patientId+"</a></td> <td>"+patientName+"</td> <td>"+creatDate+"</td>");
				 if(wardStatus.equals("1")){
					 sb.append("<td>未办理入院</td>");
				 }else if(wardStatus.equals("2")){
					 sb.append("<td>已办理入院</td>");
				 }else if(wardStatus.equals("3")){
					 sb.append("<td>已办理出院</td>");
				 }
				 sb.append("<td><a href=\"docTell.jsp?patientId="+patientId+"\">医嘱录入</a></td> </tr> ");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public String patientSearch(String searchText){
		String patientId="";
		String patientName="";
		String creatDate="";
		String wardStatus="";
		String str="select patient_id,patient_name,creat_date,ward_status from patient  where  patient_id like '%"+searchText+"%' or patient_name like '%"+searchText+"%';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				  patientId=num.getString("patient_id");
				 patientName=num.getString("patient_name");
				 creatDate=num.getString("creat_date");
				 wardStatus=num.getString("ward_status");
				 sb.append("<tr> <td><a href=\"wardDetail.jsp?patientId="+patientId+"\">"+patientId+"</a></td> <td>"+patientName+"</td> <td>"+creatDate+"</td>");
				 if(wardStatus.equals("1")){
					 sb.append("<td>未办理入院</td>");
				 }else if(wardStatus.equals("2")){
					 sb.append("<td>已办理入院</td>");
				 }else if(wardStatus.equals("3")){
					 sb.append("<td>已办理出院</td>");
				 }
				 sb.append("<td><a href=\"docTell.jsp?patientId="+patientId+"\">医嘱录入</a></td> </tr> ");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public String wardDetailSearch(String patientId) throws ParseException{
		String inHospitalWay="";
		String inDate="";
		String inHospitalDepart="";
		String roomId=""; 
		String changeDepart="";
		String outDate=""; 
		String outHospitalDepart=""; 
		String docTellId=""; 
		String drugAllergyStatus=""; 
		String drugAllergy=""; 
		String bloodType=""; 
		String userId=""; 
		String inDateMin="";
		String outDateMin="";
		
		long day=0;
		  
		
		String str="select * from ward where patient_id='"+patientId+"';";
		System.out.println("wardDetailSearch____"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 inHospitalWay=num.getString("in_hospital_way");
				 inDate=num.getString("in_date");
				 inDateMin=num.getString("in_date_min");
				 outDateMin=num.getString("out_date_min");
				 inHospitalDepart=num.getString("in_hospital_depart");
				 roomId=num.getString("room_id");
				 changeDepart=num.getString("change_depart");
				 outDate=num.getString("out_date");
				 outHospitalDepart=num.getString("out_hospital_depart");
				 docTellId=num.getString("doc_tell_id");
				 drugAllergyStatus=num.getString("drug_allergy_status");
				 drugAllergy=num.getString("drug_allergy");
				 bloodType=num.getString("blood_type");
				 userId=num.getString("user_id"); 
				 
				 beginDate = format.parse(inDate);
			        endDate= format.parse(outDate);    
			        day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);  
				 
				 sb.append("<tr> <td>入院途径：</td> <td><input type=\"text\" name=\"inHospitalWay\" id=\"inHospitalWay\"  value=\""+inHospitalWay+"\"></td><td>入院科别：</td> <td><input type=\"text\" name=\"inHospitalDepart\" id=\"inHospitalDepart\" value=\""+inHospitalDepart+"\"></td> </tr> ");				 
				 sb.append(" <tr><td>入院时间：</td> <td>日期：<input type=\"text\" name=\"inDate\" id=\"inDate\" value=\""+inDate+"\" style=\"border:1px solid #999;\"  onfocus=\"setday(this)\" > <br>时间：<input type=\"text\" name=\"inDateMin\" id=\"inDateMin\" value=\""+inDateMin+"\"></td>  <td>病房：</td> <td><input type=\"text\" name=\"roomId\" id=\"roomId\" value=\""+roomId+"\"></td> </tr>");
				 sb.append("<tr> <td>转科科别：</td> <td><input type=\"text\" name=\"changeDepart\" id=\"changeDepart\" value=\""+changeDepart+"\"></td><td>出院科别：</td> <td><input type=\"text\" name=\"outHospitalDepart\" id=\"outHospitalDepart\" value=\""+outHospitalDepart+"\"></td> </tr>");
				 sb.append("<tr><td>出院时间：</td> <td>日期：<input type=\"text\" name=\"outDate\" id=\"outDate\" value=\""+outDate+"\" style=\"border:1px solid #999;\"  onfocus=\"setday(this)\" > <br> 时间：<input type=\"text\" name=\"outDateMin\" id=\"inDateMin\" value=\""+outDateMin+"\"></td>  <td>实际住院：</td> <td>"+day+"天</td>  </tr>");
				 if(drugAllergyStatus.equals("1")){
					 sb.append("<tr>  <td>药物过敏：<select  style=\"width:60px\" id=\"drugAllergyStatus\"   name=\"drugAllergyStatus\"> <option value=\"1\" selected>没有</option> <option value=\"2\">有</option> </select></td> <td>过敏药物：<input type=\"text\" name=\"drugAllergy\" id=\"drugAllergy\" value=\""+drugAllergy+"\"></td>  <td colspan=\"2\">血型：<input type=\"text\" name=\"bloodType\" id=\"bloodType\" value=\""+bloodType+"\"></td> </tr>");
				 }else {
					 sb.append("<tr>  <td>药物过敏：<select  style=\"width:60px\" id=\"drugAllergyStatus\"   name=\"drugAllergyStatus\"> <option value=\"1\" >没有</option> <option value=\"2\" selected>有</option> </select></td> <td>过敏药物：<input type=\"text\" name=\"drugAllergy\" id=\"drugAllergy\" value=\""+drugAllergy+"\"></td>  <td colspan=\"2\">血型：<input type=\"text\" name=\"bloodType\" id=\"bloodType\" value=\""+bloodType+"\"></td> </tr>");
					 }
			 }	
			 pstmt.close();
			 dbConn.close(conn);
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
		}
	
	
	public  boolean wardUpdate(String patientId,String inHospitalWay,String inDate,String inHospitalDepart,String roomId,String changeDepart,String outDate,String outHospitalDepart,String drugAllergyStatus,String drugAllergy,String bloodType,String userId,String inDateMin,String outDateMin){
		boolean result=false;
		 
		String str="update ward set in_hospital_way='"+inHospitalWay+" ',in_date='"+inDate+"',in_hospital_depart='"+inHospitalDepart+"',room_id='"+roomId+"',change_depart='"+changeDepart+"',out_date='"+outDate+"',out_hospital_depart='"+outHospitalDepart+"',drug_allergy_status='"+drugAllergyStatus+"',drug_allergy='"+drugAllergy+"',blood_type='"+bloodType+"',in_date_min='"+inDateMin+"',out_date_min='"+outDateMin+"' where patient_id='"+patientId+"' and user_id='"+userId+"';";
	System.out.println("wardUpdate_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			int num=pstmt.executeUpdate();
			result=true;
			dbConn.close(conn);
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	public boolean wardInsert(String patientId,String inHospitalWay,String inDate,String inHospitalDepart,String roomId,String changeDepart,String outDate,String outHospitalDepart,String drugAllergyStatus,String drugAllergy,String bloodType,String userId,String inDateMin,String outDateMin){
		boolean result=false;
		String str="";
		 if(outDate.equals("")){ 
			 str="insert into ward (patient_id,in_hospital_way,in_date,in_hospital_depart,room_id,change_depart,out_date,out_hospital_depart,doc_tell_id,drug_allergy_status,drug_allergy,blood_type,user_id,check_status,in_date_min,out_date_min) values('"+patientId+"','"+inHospitalWay+"','"+inDate+"','"+inHospitalDepart+"','"+roomId+"','"+changeDepart+"',date_add(in_date, interval 9 month),'"+outHospitalDepart+"','','"+drugAllergyStatus+"','"+drugAllergy+"','"+bloodType+"','"+userId+"',1,'"+inDateMin+"','"+outDateMin+"');";
		 }else{
			 str="insert into ward (patient_id,in_hospital_way,in_date,in_hospital_depart,room_id,change_depart,out_date,out_hospital_depart,doc_tell_id,drug_allergy_status,drug_allergy,blood_type,user_id,check_status,in_date_min,out_date_min) values('"+patientId+"','"+inHospitalWay+"','"+inDate+"','"+inHospitalDepart+"','"+roomId+"','"+changeDepart+"','"+outDate+"','"+outHospitalDepart+"','','"+drugAllergyStatus+"','"+drugAllergy+"','"+bloodType+"','"+userId+"',1,'"+inDateMin+"','"+outDateMin+"');";
		 } 
		String str3="update patient set ward_status='2' where patient_id='"+patientId+"';";
		System.out.println("wardInsert_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(str3);
			pstmt2.executeUpdate();
			pstmt.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	
	
	
	public boolean patientFind(String patientId){
		 boolean s=false;
		String str="select patient_id from ward  where  patient_id ='"+patientId+"';";
		System.out.println("patientFind_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			if(num.next()){
				s=true;
			}
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return s;
	}
	public boolean patientBFind(String patientId){
		 boolean s=false;
		String str="select patient_id from body_check  where  patient_id ='"+patientId+"';";
		System.out.println("patientFind_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			if(num.next()){
				s=true;
			}
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return s;
	}
	public boolean docTellFind(String id){
		 boolean s=false;
		String str="select doc_tell_id from doc_tell  where id ='"+id+"';";
		System.out.println("docTellFind_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			if(num.next()){
				s=true;
			}
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return s;
	}
	public  boolean docTellUpdate(String id,String docTellContent,String excuteDate,String excuteId,String docTellType,String stopDate){
		boolean result=false;
		 
		String str="update doc_tell set doc_tell_content='"+docTellContent+"',excute_date='"+excuteDate+" "+dateFormat2+"',excute_id='"+excuteId+"',doc_tell_type='"+docTellType+"',stop_date='"+stopDate+" "+dateFormat2+"' where id='"+id+"';";
	System.out.println("docTellUpdate_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			int num=pstmt.executeUpdate();
			result=true;
			dbConn.close(conn);
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	public boolean docTellInsert(String id,String docTellId,String docId,String docTellContent,String excuteDate,String excuteId,String docTellType,String stopDate,String patientId){
		boolean result=false;
		 
		String str="insert into doc_tell values('"+id+"','"+docTellId+"','"+excuteDate+" "+dateFormat2+"','"+docId+"','"+docTellContent+"','"+excuteDate+" "+dateFormat2+"','"+excuteId+"','"+docTellType+"','1','"+stopDate+" "+dateFormat2+"');";
		String str3="update ward set doc_tell_id='"+docTellId+"' where patient_id='"+docTellId+"';";
		System.out.println("docTellInsert_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(str3);
			 pstmt.executeUpdate();
			 pstmt2.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	public String docTellDetailSearch(String patientId){
		String id="";
		String docTellId="";
		String docId="";
		String docTellContent=""; 
		String excuteDate="";
		String excuteId="";
		String docTellType="";
		String stopDate="";
		boolean res=false;
		String str="select * from doc_tell a where a.doc_tell_id='"+patientId+"';";
		System.out.println("docTellDetailSearch____________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
		
			 while(num.next()){
				 id=num.getString("id");
				 docTellId=num.getString("doc_tell_id");
				 docId=num.getString("doc_id");
				 docTellContent=num.getString("doc_tell_content");
				 excuteDate=num.getString("excute_date");
				 excuteId=num.getString("excute_id");
				 docTellType=num.getString("doc_tell_type");
				 stopDate=num.getString("stop_date");
				 res=true;
				 sb.append("<tr> <td>医嘱编号<div name=\"had\" style=\"display:none;\"></div></td><td><input type=\"text\" name=\"id\" id=\"id\" value=\""+id+"\" readonly /><input type=\"text\" name=\"docTellId\" id=\"docTellId\" style=\"display:none\" value=\""+patientId+"\" />  </td> </tr>");
				 sb.append("<tr> <td>医生编号</td> <td><input type=\"text\" name=\"docId\" id=\"docId\" value=\""+docId+"\" onBlur=\"ischeckNum('docId');\"/> </td> </tr> <tr> <td>医嘱内容</td> <td><textarea rows=\"6\" cols=\"80\" name=\"docTellContent\" id=\"docTellContent\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+docTellContent+"</textarea></td> </tr>");
				 sb.append("<tr> <td>执行日期</td> <td><input type=\"text\" name=\"excuteDate\" value=\""+excuteDate+"\" id=\"excuteDate\" style=\"border: 1px solid #999;\" onfocus=\"setday(this)\"></td> </tr> <tr> <td>执行者编号</td> <td><input type=\"text\" name=\"excuteId\" id=\"excuteId\" value=\""+excuteId+"\" onBlur=\"ischeckNum('excuteId');\"/> </td> </tr>");
				 if(docTellType.equals("1")){
						sb.append("<tr> <td>医嘱类型</td> <td><select style=\"width: 60px\" id=\"docTellType\" name=\"docTellType\"> <option value=\"1\" selected>长期</option> <option value=\"2\">临时</option> </select> </td> </tr>");
					}else{
						sb.append("<tr> <td>医嘱类型</td> <td><select style=\"width: 60px\" id=\"docTellType\" name=\"docTellType\"> <option value=\"1\" >长期</option> <option value=\"2\" selected>临时</option> </select> </td> </tr>");
					}
				 sb.append("<tr> <td>医嘱结束日期</td> <td><input type=\"text\" name=\"stopDate\" id=\"stopDate\" value=\""+stopDate+"\" style=\"border: 1px solid #999;\" onfocus=\"setday(this)\" /> </td> </tr>");
			 }
				if(!res){
					sb.append("<tr> <td>医嘱编号</td><td><input type=\"text\" name=\"id\" id=\"id\" value=\""+patientId+"1"+"\" readonly /><input type=\"text\" name=\"docTellId\" id=\"docTellId\" style=\"display:none\" value=\""+patientId+"\" />  </td> </tr>");
					 sb.append("<tr> <td>医生编号</td> <td><input type=\"text\" name=\"docId\" id=\"docId\" value=\""+docId+"\" onBlur=\"ischeckNum('docId');\"/> </td> </tr> <tr> <td>医嘱内容</td> <td><textarea rows=\"6\" cols=\"80\" name=\"docTellContent\" id=\"docTellContent\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+docTellContent+"</textarea></td> </tr>");
					 sb.append("<tr> <td>执行日期</td> <td><input type=\"text\" name=\"excuteDate\" value=\""+excuteDate+"\" id=\"excuteDate\" style=\"border: 1px solid #999;\" onfocus=\"setday(this)\"></td> </tr> <tr> <td>执行者编号</td> <td><input type=\"text\" name=\"excuteId\" id=\"excuteId\" value=\""+excuteId+"\" onBlur=\"ischeckNum('excuteId');\"/> </td> </tr>");
					 sb.append("<tr> <td>医嘱类型</td> <td><select style=\"width: 60px\" id=\"docTellType\" name=\"docTellType\"> <option value=\"1\" selected>长期</option> <option value=\"2\">临时</option> </select> </td> </tr>");
					 sb.append("<tr> <td>医嘱结束日期</td> <td><input type=\"text\" name=\"stopDate\" id=\"stopDate\" value=\""+stopDate+"\" style=\"border: 1px solid #999;\" onfocus=\"setday(this)\" /> </td> </tr>");
				 }
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String docTellSearch(String startDay,String endDay,String patientName){
		String id="";
		String docName="";
		String nurseName="";
		String startDay1="";
		String endDay1="";
		String docTellStatus="";
		String docTellType="";
		
		String sd="";
		String pn="";
		
		if(!startDay.equals("")&&!endDay.equals("")){
			sd="and dt.doc_tell_start_date> '"+startDay+"' and dt.stop_date<'"+endDay+"'";
		}else if(!startDay.equals("")&&endDay.equals("")){
			sd="and dt.doc_tell_start_date> '"+startDay+"' and dt.stop_date<'"+dateFormat3+"'";
		}
		
		if(!patientName.equals("")){
			pn="and p.patient_name like '%"+patientName+"%'";
		}
		String str="select dt.id,u.user_name docname,dt.doc_tell_start_date,dt.stop_date,w.user_name,dt.doc_tell_status,dt.doc_tell_type  from user u,user w,doc_tell dt,patient p where p.patient_id=dt.doc_tell_id and u.user_id=dt.doc_id "+sd+pn+"  and w.user_id=dt.excute_id;";
		System.out.println("docTellSearch__________"+str);
		 
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			sb.append(" <tr> <td>医嘱ID</td> <td>主治医生</td> <td>作用时间</td> <td>执行护士</td> <td>医嘱状态</td> <td>医嘱类型</td> </tr>");
			 while(num.next()){
				 id=num.getString("id");
				 docName= num.getString("docname");
				 startDay1=num.getString("doc_tell_start_date");
				 endDay1=num.getString("stop_date");
				 nurseName=num.getString("user_name");
				 docTellStatus= num.getString("doc_tell_status");
				 docTellType=num.getString("doc_tell_type");
				 
				 sb.append("<tr> <td><a href=\"detail.jsp?id="+id+"\">"+id+"</a></td> <td>"+docName+"</td> <td>"+startDay1+"~"+endDay1+"</td> <td>"+nurseName+"</td>");
				 switch(Integer.parseInt(docTellStatus))
				 {
				 case 1:sb.append(" <td>下达医嘱 </td>");break;
				 case 2:sb.append(" <td> 护士校对 </td>");break;
				 case 3:sb.append(" <td> 停止医嘱 </td>");break;
				 case 4:sb.append(" <td> 护士确认</td>");break;
				 }
				 switch(Integer.parseInt(docTellType))
				 {
				 case 1:sb.append(" <td>长期医嘱 </td>");break;
				 case 2:sb.append(" <td>临时医嘱</td>");break;
				 }
				 
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public String dTDetailSearch(String id){
		String docName="";
		String docTellContent="";
		String excuteDate="";
		String stopDate="";
		String docTellStatus="";
		String docTellType="";
		
		String sd="";
		String pn="";
	 
		String str="select u.user_name,dt.doc_tell_content,dt.excute_date,dt.stop_date,dt.doc_tell_type,dt.doc_tell_status from doc_tell dt,user u where dt.id='"+id+"' and dt.doc_id=u.user_id;";
		System.out.println("docTellDetailSearch_______________________"+str);
		 
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 excuteDate=num.getString("excute_date");
				 stopDate=num.getString("stop_date");
				 docName=num.getString("user_name");
				 docTellStatus= num.getString("doc_tell_status");
				 docTellType=num.getString("doc_tell_type");
				 docTellContent=num.getString("doc_tell_content");
				 
				 sb.append(" <tr><input type=\"text\" value=\"<%=session.getAttribute('userId') %>\" style=\"display:none\" id=\"excuteId\"/><input type=\"text\" value=\""+id+"\" id=\"id\" style=\"display:none\"/> <td>主治医生:"+docName+"</td>  <td>作用时间:"+excuteDate+"~"+stopDate+"</td> </tr>");
				 sb.append("<tr> <td colspan=\"2\">医嘱内容： <textarea readonly rows=\"6\" cols=\"80\" name=\"docTellContent\" id=\"docTellContent\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+docTellContent+"</textarea></td> </tr><td colspan=\"2\">");
				  if(docTellType.equals("1")){
					  switch(Integer.parseInt(docTellStatus))
						 {
						 case 1:sb.append("<input type=button  id=\"nurseCheck\" onclick=\"save(2)\" value=\"护士校对\" class=\"btn blue\"/>");break;
						 case 2:sb.append("<input type=button id=\"docStop\"  onclick=\"save(3)\" value=\"停止医嘱\"  class=\"btn blue\"/>");break;
						 case 3:sb.append("<input type=button  id=\"nurseConfirm\" onclick=\"save(4)\"  value=\"护士确认\"   class=\"btn blue\"/>");break;
						 }
				  }else if(docTellType.equals("2")){
					  	sb.append("<input type=button  id=\"nurseConfirm\"  value=\"护士确认\" onclick=\"save(4)\"  class=\"btn blue\"/>");
				  }
				  sb.append("</td>  </tr>");
				 
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public boolean docTellUpdate(String id,String docTellStatus,String excuteId){
		boolean result=false;
	 
		String str3="update doc_tell set doc_tell_status='"+docTellStatus+"' , excute_id='"+excuteId+"' where id='"+id+"';";
		System.out.println(str3);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str3);
			int num=pstmt.executeUpdate();
			result=true;
			dbConn.close(conn);
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	public String patientCheckSearch(){
		String patientId="";
		String patientName="";
		String checkStatus="";
		String str="select w.patient_id,p.patient_name,w.check_status from patient p,ward w where p.patient_id=w.patient_id order by w.patient_id desc;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				  patientId=num.getString("patient_id");
				 patientName=num.getString("patient_name");
				 checkStatus=num.getString("check_status");
				 
				 sb.append("<tr> <td><a href=\"bodycheckdetial.jsp?patientId="+patientId+"\">"+patientId+"</a></td> <td>"+patientName+"</td>");				
				 
				 
				 if(checkStatus.equals("1")){
					 sb.append(" <td>未检查</td>  </tr> ");
				 }else if(checkStatus.equals("2")){
					 sb.append(" <td>已检查</td>  </tr> ");
				 } 
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String patientCheckSearch(String searchText){
		String patientId="";
		String patientName="";
		String checkStatus="";
		String str="select  w.patient_id,p.patient_name,w.check_status from patient p,ward w  where p.patient_id=w.patient_id and  p.patient_name like '%"+searchText+"%';";
		System.out.println(str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientId=num.getString("patient_id");
				 patientName=num.getString("patient_name");
				 checkStatus=num.getString("check_status");
				 sb.append("<tr> <td><a href=\"bodycheckdetial.jsp?patientId="+patientId+"\">"+patientId+"</a></td> <td>"+patientName+"</td>");				
				 if(checkStatus.equals("1")){
					 sb.append(" <td>未检查</td>  </tr> ");
				 }else if(checkStatus.equals("2")){
					 sb.append(" <td>已检查</td>  </tr> ");
				 } 
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	
	public  boolean bodyCheckUpdate(String patientId,String T,String P,String R,String BP,String general_case,String skin,String lymph,String head,String neck,String breast,String lung,String heart,String stomach,String anus,String spinal,String nervous){
		boolean result=false;
		 
		String str="update body_check set T='"+T+"',P='"+P+"',R='"+R+"',BP='"+BP+"',general_case='"+general_case+"',skin='"+skin+"',lymph='"+lymph+"',head='"+head+"',neck='"+neck+"',breast='"+breast+"',lung='"+lung+"',heart='"+heart+"',stomach='"+stomach+"',anus='"+anus+"',spinal='"+spinal+"',nervous='"+nervous+"' where patient_id='"+patientId+"';";
	System.out.println("bodyCheckUpdate_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			int num=pstmt.executeUpdate();
			result=true;
			dbConn.close(conn);
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	public boolean bodyCheckInsert(String patientId,String T,String P,String R,String BP,String general_case,String skin,String lymph,String head,String neck,String breast,String lung,String heart,String stomach,String anus,String spinal,String nervous){
		boolean result=false;
		 
		String str="insert into body_check values('"+patientId+"','"+T+"','"+P+"','"+R+"','"+BP+"','"+general_case+"','"+skin+"','"+lymph+"','"+head+"','"+neck+"','"+breast+"','"+lung+"','"+heart+"','"+stomach+"','"+anus+"','"+spinal+"','"+nervous+"');";
		String str2="update ward set check_status='2' where patient_id='"+patientId+"';";
		System.out.println("bodyCheckInsert______________________"+str);
		System.out.println("bodyCheckInsert______________________"+str2);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(str2);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	
	public String bodyCheckSearch(String patientId){
		String T="";
		String P="";
		String R="";
		String BP="";
		String general_case="";
		String skin="";
		String lymph="";
		String head="";
		String neck="";
		String breast="";
		String lung="";
		String heart="";
		String stomach="";
		String anus="";
		String spinal="";
		String nervous="";
		
		
		String str="select * from body_check where patient_id='"+patientId+"';";
		System.out.println("bodyCheckSearch_____________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 T=num.getString("T");
				 P=num.getString("P");
				 R=num.getString("R");
				 BP=num.getString("BP");
				 general_case=num.getString("general_case");
				 skin=num.getString("skin");
				 lymph=num.getString("lymph");
				 head=num.getString("head");
				 neck=num.getString("neck");
				 breast=num.getString("breast");
				 lung=num.getString("lung");
				 heart=num.getString("heart");
				 stomach=num.getString("stomach");
				 anus=num.getString("anus");
				 spinal=num.getString("spinal");
				 nervous=num.getString("nervous");
				 
				 sb.append(" <tr> <td colspan=\"2\">T<input type=\"text\" value=\""+T+"\" id=\"T\" name=\"T\" style=\"width:30px\"><input type=\"text\" value=\""+T+"\" id=\"t\" name=\"t\"  style=\"display:none\">℃&nbsp;&nbsp;P<input type=\"text\" value=\""+P+"\" id=\"P\" name=\"P\" style=\"width:30px\">次/分&nbsp;&nbsp;R<input type=\"text\" value=\""+R+"\" id=\"R\" name=\"R\" style=\"width:30px\">次/分&nbsp;&nbsp;BP<input type=\"text\" value=\""+BP+"\" id=\"BP\" name=\"BP\" style=\"width:50px\">mmHg </td> </tr>");
				 sb.append("<tr> <td>一般情况：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"generalCase\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"generalCase\"  >"+general_case+"</textarea></td> </tr>");
				 sb.append("<tr> <td>皮肤黏膜：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"skin\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"skin\"  >"+skin+"</textarea></td> </tr>");
				sb.append("<tr> <td>淋巴结：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"lymph\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"lymph\"  >"+lymph+"</textarea></td> </tr>");
				sb.append("<tr> <td>头部：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"head\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"head\" >"+head+"</textarea></td> </tr>");
				sb.append("<tr> <td>颈部：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"neck\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"neck\" >"+neck+"</textarea></td> </tr>");
				sb.append("<tr> <td>胸部：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"breast\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"breast\" >"+breast+"</textarea></td> </tr>");
				sb.append("<tr> <td>肺部：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"lung\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"lung\" >"+lung+"</textarea></td> </tr>");
				sb.append("<tr> <td>心脏：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"heart\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"heart\">"+heart+"</textarea> </td> </tr>");
				sb.append("<tr> <td>腹部：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"stomach\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"stomach\" >"+stomach+"</textarea></td> </tr>");
				sb.append("<tr> <td>肛门与直肠<br>及生殖器：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"anus\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"anus\" >"+anus+"</textarea></td> </tr>");
				sb.append("<tr> <td>脊柱四肢：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"spinal\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"spinal\" >"+spinal+"</textarea></td> </tr>");
				sb.append("<tr> <td>神经系统：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"nervous\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"nervous\" >"+nervous+"</textarea></td> </tr>");
			 
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public String patientOutSearch(String patientId){
		String patientName="";
		String pillName="";
		String amount="";
		String status="";
		String no="";
		float totle=0;
		String str="select pt.patient_name,p.pill_name,rl.amount,rl.status,rl.no from recepit_list rl,pill_list pl,pill p,patient pt where rl.pillOrEquip=1 and rl.receipt_id=pl.id and pl.pill_id=p.pill_num and pt.ward_status=2 and rl.patient_id=pt.patient_id and pt.patient_id='"+patientId+"' union all  select p.patient_name,e.equip_name,rl.amount,rl.status,rl.no from equipment e, recepit_list rl,patient p,equipment_list el where e.equip_id=el.equipment_id and p.ward_status=2 and rl.patient_id=p.patient_id and el.id=rl.receipt_id and rl.pillOrEquip=2 and p.patient_id='"+patientId+"';";
		System.out.println("patientOutSearch————————————————————————"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 pillName=num.getString("pill_name");
				 amount=num.getString("amount");
				 status=num.getString("status");
				 no=num.getString("no");
				 totle=totle+Float.parseFloat(amount);
				 sb.append("<tr> <td><a href=\"outWardDetail.jsp?patientId="+patientId+"\">"+patientId+"</a></td> <td>"+patientName+"</td><td>"+pillName+"</td><td>"+amount+"元</td>");
				 if(status.equals("1")){
					 sb.append("<td>未交费</td>");
				 }else if(status.equals("2")){
					 sb.append("<td>已交费</td>");
				 } 
				 sb.append("<td>"+no+"</td>");
				 
			}
			 sb.append("</tr><tr><td colspan=\"6\">总价："+totle+"元</td></tr> ");
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String patientOutRSearch(String patientId){
		String outWardDiagnosis="";
		String inWardStatus="";
		String outWardStatus="";
		String id="";
		int i=0;
		
		String str="select * from outWardResult owr where owr.patient_id='"+patientId+"';";
		System.out.println("patientOutRSearch————————————————————————"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 i++;
				 outWardDiagnosis=num.getString("out_ward_diagnosis");
				 inWardStatus=num.getString("in_ward_status");
				 outWardStatus=num.getString("out_ward_status");
				 id=num.getString("id");
				 
				 sb.append(" <tr id=\"f"+i+"\"><td style=\"width:40%\">"+outWardDiagnosis+"</td>");
				 switch(Integer.parseInt(inWardStatus)){
				 case 1:sb.append(" <td> 有 </td>");break;
				 case 2:sb.append(" <td>临床未确定  </td>");break;
				 case 3:sb.append(" <td>情况不明</td>");break;
				 case 4:sb.append(" <td>无  </td>");break;
				 }
				 
				 switch(Integer.parseInt(outWardStatus)){
				 case 1:sb.append(" <td>治愈 </td>");break;
				 case 2:sb.append(" <td>好转</td>");break;
				 case 3:sb.append(" <td>未愈 </td>");break;
				 case 4:sb.append(" <td>死亡</td>");break;
				 case 5:sb.append(" <td>其他 </td>");break;
				 }
				 sb.append("<td><input type=\"button\" value=\"删除诊断结果\" onclick=\"del(f"+i+")\" class=\"btn blue\"></td></tr>");
				 
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public  boolean outWardUpdate(String patientId,String treatment,String outWardDiagnosis,String outWardConditions,String outWardTell){
		boolean result=false;
		 
		String str="update out_ward set treatment='"+treatment+"',outWardDiagnosis='"+outWardDiagnosis+"',outWardConditions='"+outWardConditions+"',outWardTell='"+outWardTell+"' where patient_id='"+patientId+"';";
	System.out.println("outWardUpdate_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 pstmt.executeUpdate();
			result=true;
			dbConn.close(conn);
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	public boolean outWardInsert(String patientId,String treatment,String outWardDiagnosis,String outWardConditions,String outWardTell){
		boolean result=false;
		 
		String str="insert into out_ward values('"+patientId+"','"+treatment+"','"+outWardDiagnosis+"','"+outWardConditions+"','"+outWardTell+"');";
		String str3="update patient set ward_status=3 where patient_id='"+patientId+"';";
		System.out.println("outWardInsert_________________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(str3);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	
	public String outWardSearch(String patientId){

		String treatment="";
		String outWardDiagnosis="";
		String outWardConditions="";
		String outWardTell="";
		boolean res=true;
		
		String str="select * from out_ward ow where ow.patient_id='"+patientId+"';";
		System.out.println("outWardSearch__________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 treatment=num.getString("treatment");
				 outWardDiagnosis=num.getString("outWardDiagnosis");
				 outWardConditions=num.getString("outWardConditions");
				 outWardTell=num.getString("outWardTell");
				 sb.append("<tr> <td>诊疗经过：<input type=\"text\" name=\"id\" id=\"id\" value=\""+treatment+"\"  style=\"display:none\"></td> <td><textarea rows=\"6\" cols=\"80\" name=\"treatment\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"treatment\"  >"+treatment+"</textarea></td> </tr>");
			sb.append("<tr> <td>出院诊断：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"outWardDiagnosis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardDiagnosis\"  >"+outWardDiagnosis+"</textarea></td> </tr>");
			 sb.append("<tr> <td>出院情况：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"outWardConditions\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardConditions\"  >"+outWardConditions+"</textarea></td> </tr>");
			 sb.append(" <tr> <td>出院医嘱：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"outWardTell\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardTell\"  >"+outWardTell+"</textarea></td> </tr>");
			 res=false;
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 if(res){
				 sb.append("<tr> <td>诊疗经过：<input type=\"text\" name=\"id\" id=\"id\" value=\""+treatment+"\"  style=\"display:none\"></td> <td><textarea rows=\"6\" cols=\"80\" name=\"treatment\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"treatment\"  >"+treatment+"</textarea></td> </tr>");
					sb.append("<tr> <td>出院诊断：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"outWardDiagnosis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardDiagnosis\"  >"+outWardDiagnosis+"</textarea></td> </tr>");
					 sb.append("<tr> <td>出院情况：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"outWardConditions\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardConditions\"  >"+outWardConditions+"</textarea></td> </tr>");
					 sb.append(" <tr> <td>出院医嘱：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"outWardTell\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardTell\"  >"+outWardTell+"</textarea></td> </tr>");
					 
			 }
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public String outWardDSearch(String patientId){
		String outWardDiagnosis="";
		String inWardStatus="";
		String outWardStatus="";
		String str="select * from out_ward_result ow where  ow.patient_id='"+patientId+"';";
		int i=0;
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 outWardDiagnosis=num.getString("out_ward_diagnosis");
				 inWardStatus=num.getString("in_ward_status");
				 outWardStatus=num.getString("out_ward_status");
				 sb.append("<tr height=\"20px\"><td>"+outWardDiagnosis+"</td>");
				 
				 switch(Integer.parseInt(inWardStatus)){
				 case 1:sb.append("<td>有</td>");break;
				 case 2:sb.append("<td>临床未确定</td>");break;
				 case 3:sb.append("<td>情况不明</td>");break;
				 case 4:sb.append("<td>无</td>");break;
				 }
				 switch(Integer.parseInt(outWardStatus)){
				 case 1:sb.append("<td>治愈</td></tr>");break;
				 case 2:sb.append("<td>好转</td></tr>");break;
				 case 3:sb.append("<td>未愈</td></tr>");break;
				 case 4:sb.append("<td>死亡</td></tr>");break;
				 case 5:sb.append("<td>其他</td></tr>");break;
				 }
				 i++;
			}
			 while(i<10){
				  sb.append("<tr height=\"20px\"><td></td><td></td><td></td> </tr>");
			 i++;
				 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String outWardAllSearch(String patientId){
		String patientName="";
		String sex="";
		String age="";
		String job="";
		String department="";
		String inDate="";
		String outDate="";
		String patientStatus="";
		String doctorDiagnoseResult="";
		String outWardDiagnosis="";
		String treatment="";
		String outWardConditions="";
		String outWardTell="";
		String str="select p.patient_name,p.sex,p.age,p.job,dm.department,w.in_date,w.out_date,pc.patient_status,pc.doctor_diagnose_result,ow.treatment,ow.outWardDiagnosis,ow.outWardConditions,ow.outWardTell  from patient p,patient_case pc,ward w,out_ward ow,department_map dm where dm.department_id=p.reg_department and ow.patient_id=w.patient_id and p.patient_id=pc.patient_id and pc.patient_id=w.patient_id and w.patient_id='"+patientId+"';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 sex=num.getString("sex");
				 if(sex.equals("1")){
					 sex="男";
				 }else if(sex.equals("2")){
					 sex="女";
				 }
				 age=num.getString("age");
				 job=num.getString("job");
				 department=num.getString("department");
				 inDate=num.getString("in_date");
				 outDate=num.getString("out_date");
				 patientStatus=num.getString("patient_status");
				 doctorDiagnoseResult=num.getString("doctor_diagnose_result");
				 outWardDiagnosis=num.getString("outWardDiagnosis");
				 treatment=num.getString("treatment");
				 outWardConditions=num.getString("outWardConditions");
				 outWardTell=num.getString("outWardTell");
				 
				 sb.append(" <div id=\"s\"> <ul> <b>姓名</b>："+patientName+" </ul> <ul><b>性别</b>： "+sex+" </ul> <ul><b>年龄</b>： "+age+"岁 </ul> <ul><b>职业</b>： "+job+" </ul> <ul><b>科别</b>： "+department+" </ul> </div> <br><br> <br><br>");
				 sb.append("<ul><b>入院日期</b>： "+inDate+"&nbsp&nbsp<b>出院日期</b>："+outDate+" </ul>");
				 sb.append("<ul><b>入院情况</b>："+patientStatus+"</ul>");
				 sb.append("<ul><b>入院诊断</b>："+doctorDiagnoseResult+" </ul>");
				 sb.append("<ul><b>诊断经过</b>："+treatment+"</ul>");
					sb.append("<ul><b>出院诊断</b>："+outWardDiagnosis+"</ul>");
					sb.append("<ul><b>出院情况</b>："+outWardConditions+"</ul>");
					sb.append("<ul><b>出院医嘱</b>："+outWardTell+"</ul>");
				 
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String outWardrecordSearch(String patientId){
		String patientName="";
		String sex="";
		String age="";
		String national="";
		String marriage="";
		String job="";
		String huAddress="";
		String nowAddress="";
		String jobAddress="";
		String inDate="";
		String patientStatus="";
		String nowDisHis="";
		String pastDisHis="";
		String bodyHis="";
		String famHis="";
		String str="select p.patient_name,p.sex,p.age,p.national,p.marriage,p.job,p.hu_address,p.now_address,p.job_address,w.in_date,pc.patient_status,pc.now_dis_his,pc.past_dis_his,pc.body_his,pc.fam_his from ward w, patient p,patient_case pc where p.patient_id=pc.patient_id and w.patient_id=p.patient_id and p.patient_id='"+patientId+"';";
		//System.out.println("outWardrecordSearch____"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 sex=num.getString("sex");
				 if(sex.equals("1")){
					 sex="男";
				 }else if(sex.equals("2")){
					 sex="女";
				 }
				 age=num.getString("age");
				 national=num.getString("national");
				 marriage=num.getString("marriage");
				 job=num.getString("job");
				 huAddress=num.getString("hu_address");
				 nowAddress=num.getString("now_address");
				 jobAddress=num.getString("job_address");
				 inDate=num.getString("in_date");
				 patientStatus=num.getString("patient_status");
				 if(num.getString("now_dis_his")!=null){
					 nowDisHis=num.getString("now_dis_his");
				 }
				 if(num.getString("past_dis_his")!=null){
					 pastDisHis=num.getString("past_dis_his");
				 }
				 if(num.getString("body_his")!=null){
					 bodyHis=num.getString("body_his");
				 }
				 if(num.getString("fam_his")!=null){
					 famHis=num.getString("fam_his");
				 } 
				 
				 sb.append(" <table style=\"margin:0 100px\"> <tr style=\"height:50px\"><td style=\"width:200px\">姓名："+patientName+"</td><td style=\"width:200px\">籍贯："+huAddress+"</td></tr> <tr style=\"height:50px\"><td>性别："+sex+"</td><td>现住址："+nowAddress+"</td></tr> <tr style=\"height:50px\"><td>年龄："+age+"</td><td>工作单位："+jobAddress+"</td></tr> <tr style=\"height:50px\"><td>民族："+national+"</td><td>入院时间："+inDate+"</td></tr>  <tr style=\"height:50px\"><td>婚姻："+marriage+"</td><td>职业："+job+"</td></tr> </table>");				 
				 sb.append(" <ul><b>主诉</b>："+patientStatus+"</ul> <ul><b>现病史</b>："+nowDisHis+"</ul> <ul><b>既往史</b>："+pastDisHis+"</ul> <ul><b>个人史</b>："+bodyHis+"</ul> <ul><b>家族史</b>："+famHis+"</ul>");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public String bodyCheckDetail(String patientId){
		String T="";
		String P="";
		String R="";
		String BP="";
		String generalCase="";
		String skin="";
		String lymph="";
		String head="";
		String neck="";
		String breast="";
		String lung="";
		String heart="";
		String stomach="";
		String anus="";
		String spinal="";
		String nervous="";
		String str="select * from body_check where patient_id='"+patientId+"';";
		//System.out.println("outWardrecordSearch____"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 T=num.getString("T");
				 P=num.getString("P");
				 R=num.getString("R");
				 BP=num.getString("BP");
				 generalCase=num.getString("general_case");
				 skin=num.getString("skin");
				 lymph=num.getString("lymph");
				 head=num.getString("head");
				 neck=num.getString("neck");
				 breast=num.getString("breast");
				 lung=num.getString("lung");
				 heart=num.getString("heart");
				 stomach=num.getString("stomach");
				 anus=num.getString("anus");
				 spinal=num.getString("spinal");
				 nervous=num.getString("nervous");
				 
				 sb.append("<div id=\"s\"> <ul>T "+T+" ℃</ul><ul>P "+P+"次/分</ul><ul>R "+R+"次/分</ul><ul>BP "+BP+"mmHg</ul> </div><br><br><br><br>");
				 sb.append("<ul><b>一般情况</b>："+generalCase+"</ul> <ul><b>皮肤黏膜</b>："+skin+"</ul> <ul><b>淋巴结</b>："+lymph+"</ul> <ul><b>头部</b>："+head+"</ul> <ul><b>颈部</b>："+neck+"</ul> <ul><b>胸部</b>："+breast+"</ul> <ul><b>肺部</b>："+lung+"</ul> <ul><b>心脏</b>："+heart+"</ul> <ul><b>腹部</b>："+stomach+"</ul> <ul><b>肛门与直肠及生殖器</b>："+anus+"</ul> <ul><b>脊柱四肢</b>："+spinal+"</ul> <ul><b>神经系统</b>："+nervous+"</ul>");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	
	public String docTellSearch(String patientId){
		String docTellStartDate="";
		String docTellContent="";
		String userName="";
		String nurseName="";
		String stopDate="";
	 
		String str="select dt.doc_tell_start_date,dt.doc_tell_content,u.user_name,u2.user_name nurse_name,dt.stop_date from user u,doc_tell dt,user u2 where u.user_id=dt.doc_id and u2.user_id=dt.excute_id and doc_tell_id='"+patientId+"' and doc_tell_type=1;";
		int i=0;
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 docTellStartDate=num.getString("doc_tell_start_date");
				 docTellContent=num.getString("doc_tell_content");
				 userName=num.getString("user_name");
				 nurseName=num.getString("nurse_name");
				 stopDate=num.getString("stop_date");
				 sb.append("<tr> <td>"+docTellStartDate+"</td><td>"+docTellContent+"</td><td>"+userName+"</td><td>"+nurseName+"</td><td>"+stopDate+"</td><td>"+userName+"</td><td>"+nurseName+"</td></tr>");
				 
				 i++;
			}
			 while(i<50){
				  sb.append("<tr height=\"20px\"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
			 i++;
				 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public String docTellLSearch(String patientId){
		String docTellStartDate="";
		String docTellContent="";
		String userName="";
		String nurseName="";
		String excuteDate="";
	 
		String str="select dt.doc_tell_start_date,dt.doc_tell_content,u.user_name,u2.user_name nurse_name,dt.excute_date from user u,doc_tell dt,user u2 where u.user_id=dt.doc_id and u2.user_id=dt.excute_id and doc_tell_id='"+patientId+"' and doc_tell_type=2;";
		int i=0;
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 docTellStartDate=num.getString("doc_tell_start_date");
				 docTellContent=num.getString("doc_tell_content");
				 userName=num.getString("user_name");
				 nurseName=num.getString("nurse_name");
				 excuteDate=num.getString("excute_date");
				 sb.append("<tr> <td>"+docTellStartDate+"</td><td>"+docTellContent+"</td><td>"+userName+"</td><td>"+excuteDate+"</td><td>"+nurseName+"</td></tr>");
				 
				 i++;
			}
			 while(i<50){
				  sb.append("<tr height=\"20px\"><td> </td><td></td><td></td><td></td><td></td></tr>");
			 i++;
				 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public boolean outWardResInsert(String patientId,String outWardDiagnosis,String inWardStatus,String outWardStatus){
		boolean result=false;
		 
		String str=" insert into out_ward_result(patient_id,out_ward_diagnosis,in_ward_status,out_ward_status,reg_date) values('"+patientId+"','"+outWardDiagnosis+"',"+inWardStatus+","+outWardStatus+",now());";
		System.out.println("outWardResInsert_________________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	
	public boolean outWardResUpdate(String outWardDiagnosis,String inWardStatus,String outWardStatus,String id){
		boolean result=false;
		 
		String str="update out_ward_result set  out_ward_diagnosis='"+outWardDiagnosis+"',in_ward_status ='"+inWardStatus+"',out_ward_status='"+outWardStatus+"',reg_date=now() where id="+id+";";
		System.out.println("outWardResUpdate_________________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	
	public String outWardResSearch(String patientId){
		String patientName="";
		String outWardDiagnosis="";
		String inWardStatus="";
		String outWardStatus="";
		String id="";
		int i=0;
		
		
		String str="select owr.*,p.patient_name from  out_ward_result owr,patient p where p.patient_id=owr.patient_id and p.patient_id='"+patientId+"';";
		System.out.println("outWardResSearch————————————————————————"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 outWardDiagnosis=num.getString("out_ward_diagnosis");
				 inWardStatus=num.getString("in_ward_status");
				 outWardStatus=num.getString("out_ward_status");
				 id=num.getString("id");
				 i++;
				 
				 sb.append(" <tr id=\"f"+i+"\"> <td style=\"width:40%\"><input type=\"text\" value=\""+patientId+"\" name=\"patientId\" id=\"patientId\" style=\"display:none\"><input type=\"text\" value=\"1\" name=\"status\" id=\"status\" style=\"display:none\"><input type=\"text\" value=\""+id+"\" name=\"id\" id=\"id\" style=\"display:none\"><input type=\"text\" value=\""+outWardDiagnosis+"\" name=\"outWardDiagnosis\" id=\"outWardDiagnosis\"></td> <td> <select  name=\"inWardStatus\" id=\"inWardStatus\">");
				 switch(Integer.parseInt(inWardStatus)){
				 case 1:sb.append(" <option value=\"1\" selected >有</option> <option  value=\"2\" >临床未确定</option> <option value=\"3\">情况不明</option> <option value=\"4\">无</option>");break;
				 case 2:sb.append(" <option value=\"1\"  >有</option> <option  value=\"2\" selected>临床未确定</option> <option value=\"3\">情况不明</option> <option value=\"4\">无</option>");break;
				 case 3:sb.append(" <option value=\"1\"  >有</option> <option  value=\"2\" >临床未确定</option> <option value=\"3\" selected>情况不明</option> <option value=\"4\">无</option>");break;
				 case 4:sb.append(" <option value=\"1\"  >有</option> <option  value=\"2\" >临床未确定</option> <option value=\"3\">情况不明</option> <option value=\"4\" selected>无</option>");break;
				 }
				 
				 sb.append("</select> </td> <td> <select name=\"outWardStatus\" id=\"outWardStatus\">");
				 switch(Integer.parseInt(outWardStatus)){
				 case 1:sb.append("<option value=\"1\" selected >治愈</option> <option  value=\"2\" >好转</option> <option value=\"3\">未愈</option> <option value=\"4\">死亡</option> <option value=\"5\">其他</option>");break;
				 case 2:sb.append("<option value=\"1\"  >治愈</option> <option  value=\"2\" selected>好转</option> <option value=\"3\">未愈</option> <option value=\"4\">死亡</option> <option value=\"5\">其他</option>");break;
				 case 3:sb.append("<option value=\"1\"  >治愈</option> <option  value=\"2\" >好转</option> <option value=\"3\" selected>未愈</option> <option value=\"4\">死亡</option> <option value=\"5\">其他</option>");break;
				 case 4:sb.append("<option value=\"1\"  >治愈</option> <option  value=\"2\" >好转</option> <option value=\"3\">未愈</option> <option value=\"4\" selected>死亡</option> <option value=\"5\">其他</option>");break;
				 case 5:sb.append("<option value=\"1\"  >治愈</option> <option  value=\"2\" >好转</option> <option value=\"3\">未愈</option> <option value=\"4\">死亡</option> <option value=\"5\" selected>其他</option>");break;
				 }
				 sb.append("</select>  </td> <td><input type=\"button\" value=\"删除诊断结果\" onclick=\"del('f"+i+"')\" class=\"btn blue\"></td> </tr>");
				 
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public boolean outWardResDele(String id){
		boolean result=false;
		 
		String str="delete from out_ward_result where id="+id+";";
		System.out.println("outWardResDele_________________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	
	
	public String diseaseRLSearch(String patientId){
		String id="";
		String inspectionDate="";
		String inspectionTime="";
		String inspectionContents="";
		String patientName="";
		String sex="";
		String age="";
		String department="";
		
		String str1="select p.patient_name,p.sex,p.age,dm.department from patient p,department_map dm where  p.patient_id='"+patientId+"' and p.reg_department=dm.department_id;";
		String str2="select * from inspection_patient ip where ip.patient_id='"+patientId+"';";
		System.out.println("diseaseRLSearch————————————————————————"+str1);
		System.out.println("diseaseRLSearch————————————————————————"+str2);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str1);
			ResultSet num=pstmt.executeQuery();
			pstmt2=conn.prepareStatement(str2);
			ResultSet num2=pstmt2.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 sex=num.getString("sex");
				 age=num.getString("age");
				 department=num.getString("department");
				  
				 sb.append("<tr><td>姓名:"+patientName+"<input type=\"text\" value=\""+patientId+"\" id=\"patientId\" name=\"patientId\" style=\"display:none\"></td>");
				 switch(Integer.parseInt(sex)){
				 case 1:sb.append("<td>性别:男</td>");break;
				 case 2:sb.append("<td>性别:女</td>");break;
				 }
				 sb.append("<td>年龄:"+age+"</td><td>科室："+department+"</td></tr>");
			}
			 sb.append("<tr> <td>病房时间：</td> <td colspan=2>巡查内容</td> <td>删除内容</td> </tr>");
			 while(num2.next()){
				id=num2.getString("id");
				inspectionDate=num2.getString("inspection_date");
				inspectionTime=num2.getString("inspection_time");
				inspectionContents=num2.getString("inspection_contents");
				
				sb.append("<tr id=\"f"+id+"\"> <td style=\"width:130px\"><input type=\"text\" value=\""+id+"\" id=\"ids\" name=\"ids\" style=\"display:none\">  日期<input type=\"text\" name=\"inspectionDate\" id=\"inspectionDate\" style=\"border: 1px solid #999;width:100px\" onfocus=\"setday(this)\" value=\""+inspectionDate+"\"> <br>  时间<input type=\"text\" name=\"inspectionTime\" id=\"inspectionTime\" style=\"border: 1px solid #999;width:100px\" value=\""+inspectionTime+"\"></td>");
			sb.append("<td colspan=2><textarea rows=\"6\" cols=\"60\" name=\"inspectionContents\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"inspectionContents\">"+inspectionContents+"</textarea> </td> <td><input type=\"button\" value=\"删除诊断结果\" onclick=\"del('f"+id+"')\" class=\"btn blue\"></td> </tr> ");
			 
			 }
			 pstmt.close();
			 pstmt2.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public  boolean DRLdelete(String id ){
		boolean result=false;
		
		String str=" delete from inspection_patient where id='"+id+"'; ";
		 
	System.out.println("DRLdelete_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt.executeUpdate();
			result=true;
			dbConn.close(conn);
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	public boolean DRLInsert(String patientId,String inspectionDate,String inspectionTime,String inspectionContents){
		boolean result=false;
		String  str="insert into inspection_patient(patient_id,inspection_date,inspection_time,inspection_contents) values ('"+patientId+"','"+inspectionDate+"','"+inspectionTime+"','"+inspectionContents+"');";
		System.out.println("DRLInsert_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	public boolean DRLUpdate(String id,String inspectionDate,String inspectionTime,String inspectionContents){
		boolean result=false;
		String  str="update inspection_patient set  inspection_date='"+inspectionDate+"',inspection_time='"+inspectionTime+"',inspection_contents='"+inspectionContents+"' where id='"+id+"';";
		System.out.println("DRLUpdate_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}
	
	public String DOWSearch(String patientId){
		String patientName="";
		String sex="";
		String age="";
		String inHospitalDepart="";
		String inDate="";
		String inDateMin="";
		String outDate="";
		String outDateMin="";
		String pillName="";
		String amount="";
		String status="";
		String no="";
		float totle=0;
		
		String str1="select p.patient_name,p.sex,p.age,w.in_hospital_depart,w.in_date,w.in_date_min,w.out_date,w.out_date_min from patient p,ward w where  p.patient_id='"+patientId+"' and p.patient_id=w.patient_id;";
		String str2="select p.pill_name,rl.amount,rl.status,rl.no from recepit_list rl,pill_list pl,pill p,patient pt where rl.pillOrEquip=1 and rl.receipt_id=pl.id and pl.pill_id=p.pill_num and pt.ward_status=2 and rl.patient_id=pt.patient_id and pt.patient_id='"+patientId+"' union all  select e.equip_name,rl.amount,rl.status,rl.no from equipment e, recepit_list rl,patient p,equipment_list el where e.equip_id=el.equipment_id and p.ward_status=2 and rl.patient_id=p.patient_id and el.id=rl.receipt_id and rl.pillOrEquip=2 and p.patient_id='"+patientId+"';";
		System.out.println("DOWSearch————————————————————————"+str1);
		System.out.println("DOWSearch————————————————————————"+str2);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str1);
			ResultSet num=pstmt.executeQuery();
			pstmt2=conn.prepareStatement(str2);
			ResultSet num2=pstmt2.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 sex=num.getString("sex");
				 age=num.getString("age");
				 inHospitalDepart=num.getString("in_hospital_depart");
				 inDate=num.getString("in_date");
				 inDateMin=num.getString("in_date_min");
				 outDate=num.getString("out_date");
				 outDateMin=num.getString("out_date_min");
				 
				 sb.append(" <h2><center>出院记录</center></h2> <table border=\"1\" style=\"margin:0 0 0 100px;\"> <tr> <td style=\"width:20%\"><b>患者姓名</b>:"+patientName+"</td>");
				 switch(Integer.parseInt(sex)){
				 case 1:sb.append("<td style=\"width:20%\"><b>性别</b>:男</td>");break;
				 case 2:sb.append("<td style=\"width:20%\"><b>性别</b>:女</td>");break;
				 }
				 sb.append("<td style=\"width:20%\"><b>年龄</b>:"+age+"</td> <td style=\"width:20%\"><b>科别</b>:"+inHospitalDepart+"</td> </tr>");
				 sb.append("<tr> <td><b>入院时间</b></td> <td>"+inDate+" "+inDateMin+"</td> <td><b>出院时间</b></td> <td>"+outDate+" "+outDateMin+"</td> </tr>");
				 sb.append(" <tr> <td><b>明细</b></td> <td><b>价格</b></td> <td><b>缴费状态</b></td> <td><b>收据编号</b></td> </tr>");
			 }
			 while(num2.next()){
				 pillName=num2.getString("pill_name");
				 amount=num2.getString("amount");
				 status=num2.getString("status");
				 no=num2.getString("no");
				 totle=totle+Float.parseFloat(amount);
				 sb.append("<tr> <td>"+pillName+"</td> <td>"+amount+"元</td>");
				 if(status.equals("1")){
					 sb.append("<td>未交费</td>");
				 }else if(status.equals("2")){
					 sb.append("<td>已交费</td>");
				 } 
				 sb.append("<td>"+no+"</td></tr>");
			 }
			 sb.append("</tr><tr><td colspan=\"6\"><b>总价</b>："+totle+"元</td></tr> ");
			 sb.append(" </table> <div  style=\"margin:100px 30px 0 100px\"> <span style=\"margin:0 30px 0 0\">住院医生签字：__________________</span> <span>主治医生签字：__________________</span></div>");
			 pstmt.close();
			 pstmt2.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String IWLSearch(String patientId){
		String patientName="";
		String sex="";
		String age="";
		String department="";
		
		String T="";
		String P="";
		String R="";
		String BP="";
		String generalCase="";
		String skin="";
		String lymph="";
		String head="";
		String neck="";
		String breast="";
		String lung="";
		String heart="";
		String stomach="";
		String anus="";
		String spinal="";
		String nervous="";
		
		String patientStatus="";
		String nowDisHis="";
		String pastDisHis="";
		String bodyHis="";
		String famHis="";
		String diagnoseDate="";
		
		String outWardDiagnosis="";
		String inWardStatus="";
		String outWardStatus="";
		
		String str1="select bc.*,p.patient_name,p.sex,p.age,dm.department from patient p,department_map dm,body_check bc where  p.patient_id='"+patientId+"' and p.reg_department=dm.department_id and bc.patient_id=p.patient_id;";
		String str2="select pc.patient_status,pc.now_dis_his,pc.past_dis_his,pc.body_his,pc.fam_his,pc.diagnose_date from patient_case pc where pc.patient_id='"+patientId+"';";
		String str3="select * from out_ward_result ow where  ow.patient_id='"+patientId+"';";
		System.out.println("IWLSearch————————————————————————"+str1);
		System.out.println("IWLSearch————————————————————————"+str2);
		System.out.println("IWLSearch————————————————————————"+str3);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str1);
			ResultSet num=pstmt.executeQuery();
			pstmt2=conn.prepareStatement(str2);
			ResultSet num2=pstmt2.executeQuery();
			pstmt3=conn.prepareStatement(str3);
			ResultSet num3=pstmt3.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 sex=num.getString("sex");
				 age=num.getString("age");
				 department=num.getString("department");
				 
				 T=num.getString("T");
				 P=num.getString("P");
				 R=num.getString("R");
				 BP=num.getString("BP");
				 generalCase=num.getString("general_case");
				 skin=num.getString("skin");
				 lymph=num.getString("lymph");
				 head=num.getString("head");
				 neck=num.getString("neck");
				 breast=num.getString("breast");
				 lung=num.getString("lung");
				 heart=num.getString("heart");
				 stomach=num.getString("stomach");
				 anus=num.getString("anus");
				 spinal=num.getString("spinal");
				 nervous=num.getString("nervous");
				 
				 
				  
				 sb.append(" <table > <tr> <td colspan=4> <center>入院记录 </center></td> </tr> <tr><td>姓名:"+patientName+"</td>");
				 switch(Integer.parseInt(sex)){
				 case 1:sb.append("<td>性别:男</td>");break;
				 case 2:sb.append("<td>性别:女</td>");break;
				 }
				 sb.append("<td>年龄:"+age+"</td><td>科室："+department+"</td></tr>");
			}
			 while(num2.next()){
				 patientStatus=num2.getString("patient_status");
				 diagnoseDate=num2.getString("diagnose_date");
				 if(num2.getString("now_dis_his")!=null){
					 nowDisHis=num2.getString("now_dis_his");
				 }
				 if(num2.getString("past_dis_his")!=null){
					 pastDisHis=num2.getString("past_dis_his");
				 }
				 if(num2.getString("body_his")!=null){
					 bodyHis=num2.getString("body_his");
				 }
				 if(num2.getString("fam_his")!=null){
					 famHis=num2.getString("fam_his");
				 } 
				 sb.append("<tr> <td colspan=4> <center>记录日期："+diagnoseDate+"</center></td> </tr>");
				 sb.append("<tr ><td>主诉：</td><td colspan=3>"+patientStatus+"</td> </tr> <tr ><td>现病史：</td><td colspan=3>"+nowDisHis+"</td> </tr><tr ><td>既往史：</td><td colspan=3>"+pastDisHis+"</td> </tr> <tr ><td>个人史：</td><td colspan=3>"+bodyHis+"</td> </tr> <tr ><td>家族史：</td><td colspan=3>"+famHis+"</td> </tr>");
			 
			 }
			 sb.append("<tr> <td colspan=4><center>体格检查</center> </td> </tr> <tr><td>T "+T+" ℃</td><td>P "+P+"次/分</td> <td>R "+R+"次/分</td><td>BP "+BP+"mmHg</td>  </tr> <tr><td><b>一般情况</b></td><td colspan=3>："+generalCase+"</td></tr> <tr><td><b>皮肤黏膜</b></td><td colspan=3>："+skin+"</td></tr> <tr><td><b>淋巴结</b></td><td colspan=3>："+lymph+"</td></tr> <tr><td><b>头部</b></td><td colspan=3>："+head+"</td></tr>");
			sb.append("<tr><td><b>颈部</b></td><td colspan=3>："+neck+"</td></tr> <tr><td><b>胸部</b></td><td colspan=3>："+breast+"</td></tr> <tr><td><b>肺部</b></td><td colspan=3>："+lung+"</td></tr>  <tr><td><b>心脏</b></td><td colspan=3>："+heart+"</td></tr> <tr><td><b>腹部</b></td><td colspan=3>："+stomach+"</td></tr> <tr><td><b>肛门与直肠及生殖器</b></td><td colspan=3>："+anus+"</td></tr> <tr><td><b>脊柱四肢</b></td><td colspan=3>："+spinal+"</td></tr> <tr><td><b>神经系统</b></td><td colspan=3>："+nervous+"</td></tr>  </table>");
			 sb.append("<p>辅助检查<textarea rows=\"6\" cols=\"60\" name=\"inspectionContents\"  onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"inspectionContents\"></textarea></p>");
			 sb.append("<table> <tr > <td ><b>出院诊断</b></td><td><b>入院病情</b></td><td><b>出院病情</b></td>  </tr>");
			 while(num3.next()){
				 outWardDiagnosis=num3.getString("out_ward_diagnosis");
				 inWardStatus=num3.getString("in_ward_status");
				 outWardStatus=num3.getString("out_ward_status");
				 sb.append("<tr height=\"20px\"><td>"+outWardDiagnosis+"</td>");
				 
				 switch(Integer.parseInt(inWardStatus)){
				 case 1:sb.append("<td>有</td>");break;
				 case 2:sb.append("<td>临床未确定</td>");break;
				 case 3:sb.append("<td>情况不明</td>");break;
				 case 4:sb.append("<td>无</td>");break;
				 }
				 switch(Integer.parseInt(outWardStatus)){
				 case 1:sb.append("<td>治愈</td></tr></table>");break;
				 case 2:sb.append("<td>好转</td></tr></table>");break;
				 case 3:sb.append("<td>未愈</td></tr></table>");break;
				 case 4:sb.append("<td>死亡</td></tr></table>");break;
				 case 5:sb.append("<td>其他</td></tr></table>");break;
				 }
			}
			 sb.append("<span>初步诊断：未知</span>");
			 
			 
			 pstmt.close();
			 pstmt2.close();
			 pstmt3.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public String ELSearch(String patientId,String startDate,String endDate){
		String patientName="";
		String pillName="";
		String amount="";
		String status="";
		String no="";
		float totle=0;
		String receiptDate="";
		 
		String str="select rl.receipt_date,pt.patient_name,p.pill_name,rl.amount,rl.status,rl.no from recepit_list rl,pill_list pl,pill p,patient pt where rl.pillOrEquip=1 and rl.receipt_id=pl.id and pl.pill_id=p.pill_num and pt.ward_status=2 and rl.patient_id=pt.patient_id and pt.patient_id='"+patientId+"'  and rl.receipt_date<='"+endDate+"' and rl.receipt_date>='"+startDate+"' union all  select rl.receipt_date,p.patient_name,e.equip_name,rl.amount,rl.status,rl.no from equipment e, recepit_list rl,patient p,equipment_list el where e.equip_id=el.equipment_id and p.ward_status=2 and rl.payer=p.patient_name and el.id=rl.receipt_id and rl.pillOrEquip=2 and p.patient_id='"+patientId+"'  and rl.receipt_date<='"+endDate+"' and rl.receipt_date>='"+startDate+"' ;"; 
			System.out.println("ELSearch————————————————————————"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 pillName=num.getString("pill_name");
				 amount=num.getString("amount");
				 status=num.getString("status");
				 no=num.getString("no");
				 receiptDate=num.getString("receipt_date");
				 totle=totle+Float.parseFloat(amount);
				 sb.append("<tr> <td> "+patientId+" </td> <td>"+patientName+"</td><td>"+pillName+"</td><td>"+amount+"元</td>");
				 if(status.equals("1")){
					 sb.append("<td>未交费</td>");
				 }else if(status.equals("2")){
					 sb.append("<td>已交费</td>");
				 } 
				 sb.append("<td>"+no+"</td><td>"+receiptDate+"</td>");
				 
			}
			 sb.append("</tr><tr><td colspan=\"7\">总价："+totle+"元</td></tr> ");
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public String SPSearch(String patientId){
		//出院记录
		String inDate="";
		String outDate="";
		String patientStatus="";
		String doctorDiagnoseResult="";
		String outWardDiagnosis="";
		String treatment="";
		String outWardConditions="";
		String outWardTell="";
		//入院记录
		String nowDisHis="";
		String pastDisHis="";
		String bodyHis="";
		String famHis="";
		//体格检查
		String T="";
		String P="";
		String R="";
		String BP="";
		String generalCase="";
		String skin="";
		String lymph="";
		String head="";
		String neck="";
		String breast="";
		String lung="";
		String heart="";
		String stomach="";
		String anus="";
		String spinal="";
		String nervous="";
		
		String str="select bc.*,pc.patient_status,pc.now_dis_his,pc.past_dis_his,pc.body_his,pc.fam_his,pc.doctor_diagnose_result,ow.treatment,ow.outWardDiagnosis,ow.outWardConditions,ow.outWardTell from body_check bc,patient_case pc,ward w,out_ward ow where pc.patient_id=w.patient_id and pc.patient_id='"+patientId+"' and ow.patient_id=w.patient_id and bc.patient_id=pc.patient_id;";
		System.out.println("SPSearch————————————————————————"+str);
		
		sb.append("<h3><center>出院记录</center></h3> <div> <table>");
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientStatus=num.getString("patient_status");
				 doctorDiagnoseResult=num.getString("doctor_diagnose_result");
				 outWardDiagnosis=num.getString("outWardDiagnosis");
				 treatment=num.getString("treatment");
				 outWardConditions=num.getString("outWardConditions");
				 outWardTell=num.getString("outWardTell");
				 if(num.getString("now_dis_his")!=null){
					 nowDisHis=num.getString("now_dis_his");
				 }
				 if(num.getString("past_dis_his")!=null){
					 pastDisHis=num.getString("past_dis_his");
				 }
				 if(num.getString("body_his")!=null){
					 bodyHis=num.getString("body_his");
				 }
				 if(num.getString("fam_his")!=null){
					 famHis=num.getString("fam_his");
				 } 
				 T=num.getString("T");
				 P=num.getString("P");
				 R=num.getString("R");
				 BP=num.getString("BP");
				 generalCase=num.getString("general_case");
				 skin=num.getString("skin");
				 lymph=num.getString("lymph");
				 head=num.getString("head");
				 neck=num.getString("neck");
				 breast=num.getString("breast");
				 lung=num.getString("lung");
				 heart=num.getString("heart");
				 stomach=num.getString("stomach");
				 anus=num.getString("anus");
				 spinal=num.getString("spinal");
				 nervous=num.getString("nervous");
				 
				 sb.append("<tr><td><b>入院情况</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"patientStatus\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"patientStatus\"  >"+patientStatus+"</textarea></td></tr>");
				 sb.append("<tr><td><b>入院诊断</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"doctorDiagnoseResult\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"doctorDiagnoseResult\"  >"+doctorDiagnoseResult+"</textarea></td></tr>");
				 sb.append("<tr><td><b>诊断经过</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"treatment\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"treatment\"  >"+treatment+"</textarea></td></tr>");
				 sb.append("<tr><td><b>出院诊断</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"outWardDiagnosis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardDiagnosis\"  >"+outWardDiagnosis+"</textarea></td></tr>");
				 sb.append("<tr><td><b>出院情况</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"outWardConditions\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardConditions\"  >"+outWardConditions+"</textarea></td></tr>");
				 sb.append("<tr><td><b>出院医嘱</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"outWardTell\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"outWardTell\"  >"+outWardTell+"</textarea></td></tr>");
				 sb.append(" </table> </div> <h3><center>入院记录</center></h3> <div> <table>");
				 sb.append("<tr><td><b>主诉</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"patientStatus\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\"    >"+patientStatus+"</textarea></td></tr>");
				 sb.append("<tr><td><b>现病史</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"nowDisHis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"nowDisHis\"  >"+nowDisHis+"</textarea></td></tr>");
				 sb.append("<tr><td><b>既往史</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"pastDisHis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"pastDisHis\"  >"+pastDisHis+"</textarea></td></tr>");
				 sb.append("<tr><td><b>个人史</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"bodyHis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"bodyHis\"  >"+bodyHis+"</textarea></td></tr>");
				 sb.append("<tr><td><b>家族史</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"famHis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"famHis\"  >"+famHis+"</textarea></td></tr>");
				 sb.append("</table> </div> <h3><center>体格检查</center></h3> <div> <table>");
				 sb.append(" <tr><td>T<input type=\"text\" value=\""+T+"\" id=\"T\" name=\"T\" style=\"width:30px\">℃</td> <td>P<input type=\"text\" value=\""+P+"\" id=\"P\" name=\"P\" style=\"width:30px\">次/分</td>  <td>R<input type=\"text\" value=\""+R+"\" id=\"R\" name=\"R\" style=\"width:30px\">次/分</td> <td>BP<input type=\"text\" value=\""+BP+"\" id=\"BP\" name=\"BP\" style=\"width:50px\">mmHg</td></tr> <br> ");
				
				 sb.append("<tr><td><b>一般情况</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"generalCase\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"generalCase\"  >"+generalCase+"</textarea></td></tr>");
				 sb.append("<tr><td><b>皮肤黏膜</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"skin\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"skin\"  >"+skin+"</textarea></td></tr>");
				 sb.append("<tr><td><b>淋巴结</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"lymph\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"lymph\"  >"+lymph+"</textarea></td></tr>");
				 sb.append("<tr><td><b>头部</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"head\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"head\"  >"+head+"</textarea></td></tr>");
				 sb.append("<tr><td><b>颈部</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"neck\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"neck\"  >"+neck+"</textarea></td></tr>");
				 sb.append("<tr><td><b>胸部</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"breast\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"breast\"  >"+breast+"</textarea></td></tr>");
				 sb.append("<tr><td><b>肺部</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"lung\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"lung\"  >"+lung+"</textarea></td></tr>");
				 sb.append("<tr><td><b>心脏</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"heart\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"heart\"  >"+heart+"</textarea></td></tr>");
				 sb.append("<tr><td><b>腹部</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"stomach\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"stomach\"  >"+stomach+"</textarea></td></tr>");
				 sb.append("<tr><td><b>肛门与直肠及生殖器</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"anus\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"anus\"  >"+anus+"</textarea></td></tr>");
				 sb.append("<tr><td><b>脊柱四肢</b>：</td><td colspan=3><textarea rows=\"6\" cols=\"80\" name=\"spinal\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"spinal\"  >"+spinal+"</textarea></td></tr>");
				 sb.append("<tr><td><b>神经系统</b>：</td><td colspan=3> <textarea rows=\"6\" cols=\"80\" name=\"nervous\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"nervous\"  >"+nervous+"</textarea></td></tr> </table></div>");
				 
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public boolean PDInsert(String id,String patientId,String patientStatus,String doctorDiagnoseResult,String treatment,String outWardDiagnosis,String outWardConditions,String outWardTell,String nowDisHis,String pastDisHis,String bodyHis,String famHis,String T,String P,String R,String BP,String general_case,String skin,String lymph,String head,String neck,String breast,String lung,String heart,String stomach,String anus,String spinal,String nervous){
		boolean result=false;
		String str="insert into body_check values('"+patientId+"','"+T+"','"+P+"','"+R+"','"+BP+"','"+general_case+"','"+skin+"','"+lymph+"','"+head+"','"+neck+"','"+breast+"','"+lung+"','"+heart+"','"+stomach+"','"+anus+"','"+spinal+"','"+nervous+"');";
		String str2="update patient_case set patient_status='"+patientStatus+"' ,doctor_diagnose_result='"+doctorDiagnoseResult+"',now_dis_his='"+nowDisHis+"',past_dis_his='"+pastDisHis+"',body_his='"+bodyHis+"',fam_his='"+famHis+"' where id='"+id+"';";
		String str3="insert into out_ward(patient_id,treatment,outWardDiagnosis,outWardConditions,outWardTell) values ('"+patientId+"','"+treatment+"','"+outWardDiagnosis+"','"+outWardConditions+"','"+outWardTell+"');";
		System.out.println("PDInsert_________________"+str);
		System.out.println("PDInsert_________________"+str2);
		System.out.println("PDInsert_________________"+str3);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(str2);
			pstmt3=conn.prepareStatement(str3);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			result=true;
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dbConn.close(conn);
		return result;
	}

}
