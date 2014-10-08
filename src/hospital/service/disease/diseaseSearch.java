package hospital.service.disease;

import hospital.dao.dbConn;
import hospital.service.equipCheck.equipCheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class diseaseSearch {
	static Connection conn;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt2=null;
	static ResultSet rs=null;
	StringBuffer sb = new StringBuffer();
	
	 static java.util.Date date = new java.util.Date();
	 static SimpleDateFormat sy=new SimpleDateFormat("yyyyMMdd");
	 static String dateFormat=sy.format(date);
	 static SimpleDateFormat sy2=new SimpleDateFormat("yyyy-MM-dd");
	 static String dateFormat2=sy2.format(date);
	 equipCheck equi=new equipCheck();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
/*		diseaseSearch sis=new diseaseSearch();
		// TODO Auto-generated method stub
	
	String L = sis.findMedicineId().substring(8,sis.findMedicineId().length()).trim();
	int i= Integer.parseInt(L);
	int id=i+1;
	L=Integer.toString(id);
	String medicineId=dateFormat+L;*/
	//System.out.print(patientCaseUpdate("201304041","FredpatientCase我",""));
	}
	
	//from patient
	public String disSearch(){
		String patientId="";
		String patientName="";
		String creatDate="";
		String pcid="";
		String str="select r.id rid,pc.id pcid,r.patient_id,r.re_check_date,p.patient_name from patient_case pc,reCheckReg r,patient p where p.patient_id=pc.patient_id and r.patient_id=p.patient_id   order by r.id;";
		System.out.println("disSearch____________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			sb.append(" <tr><td>患者编号</td> <td>姓名</td> <td>挂号日期</td> </tr>");
			 while(num.next()){
				  patientId=num.getString("patient_id");
				  pcid=num.getString("pcid");
				 patientName=num.getString("patient_name");
				 creatDate=num.getString("re_check_date");
				sb.append("<tbody><tr><td><a href=\"diseaseDetail.jsp?patientId="+patientId+"&equiId=&pillId=&id="+pcid+"\">"+patientId+"</td><td>"+patientName+"</a></td><td>"+creatDate+"</td></tr></tbody>");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	//from patient
	public boolean dissearch(){
		boolean res=false;
		String str="select p.patient_id,p.patient_name,p.creat_date from patient p,patient_case pc,recheckreg rc where rc.patient_id=pc.patient_id and rc.status=1 and p.patient_id=pc.patient_id order by rc.id ;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			if(num.next()){
				res=true;
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	//from patient
	public String patientDetailSearch(String patientId){
		String patientName="";
		String sex="";
		String age="";
		String identityNumber=""; 
		String contactNumber="";
		int regDepartment=0;
		
		String str="select * from patient  where patient_id="+patientId+";";
		System.out.println("patientDetailSearch_____________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 sex=num.getString("sex");
				 patientName=num.getString("patient_name");
				 age=num.getString("age");
				 identityNumber=num.getString("identity_number");
				 contactNumber=num.getString("contact_number");
				 regDepartment=Integer.parseInt(num.getString("reg_department"));
				 
				 
				sb.append("<tr><td>患者编号：</td><td id=\"patientId\">"+patientId+"</td><td>患者姓名：</td><td>"+patientName+"</td> <td>性别：</td><td>");
				if(sex.equals("1")){
					sb.append("男");
				}else{
					sb.append("女");
				}
				sb.append("</td> <td>年龄：</td><td>"+age+"</td></tr><tr><td>身份证号：</td><td>"+identityNumber+"</td> <td >联系方式：</td><td colspan=\"2\">"+contactNumber+"</td> <td colspan=\"2\">挂号部门：</td><td>");
			switch(regDepartment){
			case 101:sb.append("外科");break;
			case 102:sb.append("妇科");break;
			case 103:sb.append("牙科");break;
			case 104:sb.append("内科");break;
			case 105:sb.append("儿科");break;
			case 106:sb.append("眼科");break;
			case 107:sb.append("耳鼻喉科");break;
			case 108:sb.append("口腔科");break;
			case 109:sb.append("皮肤科");break;
			case 110:sb.append("中医科");break;
			}
			sb.append("</td></tr>");
			 }
			 
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String patientDetail2Search(String patientId){
		String medicineUseMethod="";
		String diagnoseDate="";
		String bydoctorId="";
		String reviewDate=""; 
		String userName="";
		String dealStatus="";
		diseaseSearch dise=new diseaseSearch();
		String str="select u.user_name,pc.medicine_use_method,pc.diagnose_date,pc.bydoctor_id,pc.review_date,pc.deal_status from user u,patient_case pc where pc.id='"+patientId+"' and u.user_id=pc.bydoctor_id;";
		System.out.println("patientDetailSearch_____________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 medicineUseMethod=num.getString("medicine_use_method");
				 diagnoseDate=num.getString("diagnose_date");
				 bydoctorId=num.getString("bydoctor_id");
				 reviewDate=num.getString("review_date");
				 userName=num.getString("user_name");
				 dealStatus=num.getString("deal_status");
				 
				 sb.append("<tr> <td>处方使用说明：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"medicineUseMethod\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"medicineUseMethod\">"+medicineUseMethod+"</textarea> </td> </tr>");
				 sb.append("<tr> <td>处置情况：</td> <td><textarea rows=\"6\" cols=\"80\" name=\"dealStatus\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\" id=\"dealStatus\">"+dealStatus+"</textarea> </td> </tr>");
					
				 sb.append("<tr> <td>诊断日期：</td> <td><input type=\"text\" name=\"diagnoseDate\" id=\"diagnoseDate\" style=\"border: 1px solid #999;\"  onfocus=\"setday(this)\"   value=\""+diagnoseDate+"\"> </td> </tr>");
				sb.append("<tr> <td>*诊断医生：</td> <td><select id=\"bydoctorId\" name=\"bydoctorId\"> <option value=\""+bydoctorId+"\">"+userName+"</option>"+ dise.patientDoctorSearch()+" </select> </tr>");
				sb.append("<tr> <td>复查日期：</td> <td><input type=\"text\" value=\""+reviewDate+"\" name=\"reviewDate\" id=\"reviewDate\" style=\"border: 1px solid #999;\" onfocus=\"setday(this)\" > </td> </tr>");
			 }
			 
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	//pill_list
	public  boolean pillListInsert(String patientId,String pillNum,String price,String amount,String doctorId,String pillType){
		boolean res=false;
		String medicineId="";
		int id = 0;
		double totle=Double.parseDouble(price)*Double.parseDouble(amount);
		if(findId().equals("nothing")){
			medicineId=dateFormat+"1";
		}else{
			String L = findId().substring(8,findId().length()).trim();
			int i= Integer.parseInt(L);
			id=i+1;
			L=Integer.toString(id);
			medicineId=dateFormat+L;
		}
		
		String str="INSERT INTO `pill_list`(patient_id,pill_id,id,price,pill_amount,all_price,doctor_id,pill_date,pill_type) VALUES ('"+patientId+"', '"+pillNum+"', '"+medicineId+"', '"+price+"', '"+amount+"', '"+totle+"', '"+doctorId+"', '"+dateFormat2+"','"+pillType+"');";
		String str2="insert into recepit_list(receipt_id,amount,receipter,payer,pillOrEquip,status,receipt_date) values('"+medicineId+"','"+totle+"','',(select patient_name from patient where patient_id='"+patientId+"'),1,1,'"+dateFormat2+"');";
		System.out.print(str);
		System.out.print(str2);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(str2);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
				res=true;
				
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	//pill_list1
	public  boolean pillListInsert(String patientId,String pillNum,String price,String amount,String doctorId,double totle2,String pillType){
		boolean res=false;
		double totle=Double.parseDouble(price)*Double.parseDouble(amount);
		 
		
		String str="INSERT INTO `pill_list`(patient_id,pill_id,id,price,pill_amount,all_price,doctor_id,pill_date,pill_type ) VALUES ('"+patientId+"', '"+pillNum+"', '"+patientId+"', '"+price+"', '"+amount+"', '"+totle+"', '"+doctorId+"',  '"+dateFormat2+"','"+pillType+"' );";
		System.out.println("pillListInsert2_________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt.executeUpdate();
				res=true;
				
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	//pill_list2
		public  boolean pillListInsert(String patientId,double totle2){
			boolean res=false;
			 
			
			String str2="insert into recepit_list(receipt_id,amount,receipter,payer,pillOrEquip,status,receipt_date) values('"+patientId+"','"+totle2+"','',(select patient_name from patient where patient_id='"+patientId+"'),1,1,'"+dateFormat2+"' );";
			System.out.println("pillListInsert_________"+str2);
			try{
				conn=dbConn.getConn();
				pstmt2=conn.prepareStatement(str2);
				pstmt2.executeUpdate();
					res=true;
					
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
	//from pill_list
		public String findElId(String patientId){
			String Id="nothing";
			  
			String s="select id from equipment_list el where el.patient_id="+patientId+" and el.check_date=CURDATE();";
			
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(s);
				 
				ResultSet num=pstmt.executeQuery();
				 if(num.next()){
					 Id=num.getString("id");
				}
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return Id;
		}
	//from pill_list
	public String findId(){
		String Id="nothing";
		  
		String s="select id from pill_list where pill_date='"+dateFormat2+"' order by id desc limit 0,1;";
		System.out.println("findId___________________"+s);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(s);
			 
			ResultSet num=pstmt.executeQuery();
			 if(num.next()){
				 Id=num.getString("id");
			}
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Id;
	}
	
	//from pill_list
	public String findId(String paitentId){
		String Id="nothing";
		  
		String s="select id from pill_list where  patient_id='"+paitentId+"' order by id desc limit 0,1;";
		System.out.println("findId___________________"+s);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(s);
			 
			ResultSet num=pstmt.executeQuery();
			 if(num.next()){
				 Id=num.getString("id");
			}
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Id;
	}
	
	//from pill
	public String diseaseSearch(String searchText){
		String pillName="";
		String price="";
		String pillNum="";
		String pillType="";
		
		String str="select pill_num,pill_name,price,pill_type from pill where pill_name like '%"+searchText+"%';";
		System.out.println(" diseaseSearch(String searchText)______________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 pillNum=num.getString("pill_num");
				 pillType=num.getString("pill_type");
				 pillName=num.getString("pill_name");
				 price=num.getString("price");
				 sb.append(" <tr><td>药品名称</td><td>是否添加</td></tr>");
				 sb.append("<tr><td><input type=\"text\" class=\"pillType"+pillNum+"\" name=\"pillType\" value=\""+pillType+"\" style=\"display:none;\"><input type=\"text\" id=\"price"+pillNum+"\" value=\""+price+"\" style=\"display:none;\"><input type=\"text\" id=\"pillNum"+pillNum+"\" value=\""+pillNum+"\" style=\"display:none;\"><input type=\"text\" id=\"pillName"+pillNum+"\" value=\""+pillName+"\" style=\"display:none;\">"+pillName+"</td><td><input type=\"button\" class=\"btn blue\" value=\"添加\" onclick=\"add("+pillNum+")\"></td></tr>");
				 		}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
		
	}
	//from findmypc
	public String findMyPl(String patientId){
		String id="";
	int i=1;
		
		String str="select distinct id from pill_list pl where pl.patient_id='"+patientId+"' and  pill_date=curdate();";
		System.out.println(" findMyPl______________"+str);
	
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 id=num.getString("id");
			sb.append("<a href=\"../pillList/pillListDetail.jsp?id="+id+"\">我的处方"+i+"</a>&nbsp&nbsp");
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
	//from findMyPID
	public int findMyPID(String patientId){
	int i=0;
		
		String str="select distinct pill_type from pill_list pl where pl.patient_id='"+patientId+"' and  pill_date=curdate();";
		System.out.println("findMyPID______________"+str);
	
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
			i++;
				 		}
		
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return i;
		
	}
	//from findMyPID
	public String findMyPId(String patientId){
		String pill_type="";
		String str="select distinct pill_type from pill_list pl where pl.patient_id='"+patientId+"' and  pill_date=curdate();";
		System.out.println("findMyPId______________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 pill_type=num.getString("pill_type");
				 if(pill_type.equals("1")){
					 sb.append("已创建中药药方");
				 }else if(pill_type.equals("2")){
					 sb.append("已创建西药药方");
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
	// patient_case
	public boolean patientCaseUpdate1(String id,String patientStatus,String doctorDiagnoseResult,String nowDisHis,String pastDisHis,String bodyHis,String famHis,String patientId,String patientTell,String BP){
		boolean res=false;
		String str="UPDATE patient_case SET patient_status = '"+patientStatus+"' , doctor_diagnose_result='"+doctorDiagnoseResult+"', now_dis_his='"+nowDisHis+"', past_dis_his='"+pastDisHis+"', body_his='"+bodyHis+"', fam_his='"+famHis+"',patient_tell='"+patientTell+"',BP='"+BP+"' WHERE id = '"+id+"';";
		String str2="update recheckreg set status=2 where re_check_date=current_date() and patient_id='"+patientId+"';";
		System.out.println("patientCaseUpdate1_______"+str);
		System.out.println("patientCaseUpdate1_______"+str2);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(str2);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
				res=true;
				
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	// patient_case
		public boolean patientCaseUpdate(String id,String patientStatus,String doctorDiagnoseResult,String medicineUseMethod,String diagnoseDate,String reviewDate,String bydoctorId,String patientId,String patientTell,String BP,String dealStatus){
			boolean res=false;
			if(reviewDate.equals("")){
				reviewDate="2013-01-01";
			}
			String str="UPDATE patient_case SET patient_status = '"+patientStatus+"' , doctor_diagnose_result='"+doctorDiagnoseResult+"',medicine_use_method='"+medicineUseMethod+"',diagnose_date='"+diagnoseDate+"',bydoctor_id='"+bydoctorId+"',review_date='"+reviewDate+"',status='2',patient_tell='"+patientTell+"',BP='"+BP+"',deal_status='"+dealStatus+"' WHERE id = '"+id+"';";
			String str2="update recheckreg set status=2 where re_check_date=current_date() and patient_id='"+patientId+"';";
			System.out.println("patientCaseUpdate_____________________"+str);
			System.out.println("patientCaseUpdate1_______"+str2);
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				pstmt2=conn.prepareStatement(str2);
				pstmt.executeUpdate();
				pstmt2.executeUpdate();
					res=true;
					
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
		
		public boolean patientCaseInsert(String patientId,String patientStatus,String doctorDiagnoseResult,String medicineUseMethod,String diagnoseDate,String reviewDate,String bydoctorId,String nowDisHis,String pastDisHis,String bodyHis,String famHis,String patientTell,String BP,String dealStatus){
			boolean res=false;
			String str="insert into patient_case(patient_id,patient_status,doctor_diagnose_result,medicine_use_method,diagnose_date,bydoctor_id,review_date,status,now_dis_his,past_dis_his,body_his,fam_his,patient_tell,BP,deal_status) values ('"+patientId+"','"+patientStatus+"','"+doctorDiagnoseResult+"','"+medicineUseMethod+"','"+diagnoseDate+"','"+bydoctorId+"','"+reviewDate+"','2','"+nowDisHis+"','"+pastDisHis+"','"+bodyHis+"','"+famHis+"','"+patientTell+"','"+BP+"','"+dealStatus+"');";
			System.out.println("patientCaseInsert_____________________"+str);
	
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				 
				pstmt.executeUpdate();
					res=true;
					
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
	
	
	
	// patient_case
	public String patientCaseSearch(String patientId){
		String patientStatus="";
		String doctorDiagnoseResult="";
		String nowDisHis="";
		String pastDisHis="";
		String bodyHis="";
		String famHis="";
		String id="";
		String patientTell="";
		String BP="";
		
		String str="select id,patient_status ,doctor_diagnose_result,now_dis_his,past_dis_his,body_his,fam_his,patient_tell,BP from patient_case where id='"+patientId+"';";
		
		System.out.println("patientCaseSearch_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 if(num.getString("patient_status")!=null){
					 patientStatus=num.getString("patient_status").replaceAll("<br>", "\n");
					 //System.out.println(num.getString("patient_status"));
				 }
				 if(num.getString("doctor_diagnose_result")!=null){
					 doctorDiagnoseResult=num.getString("doctor_diagnose_result").replaceAll("<br>", "\n");
				 }
				 if(num.getString("now_dis_his")!=null){
					 nowDisHis=num.getString("now_dis_his").replaceAll("<br>", "\n");
				 }
				 if(num.getString("past_dis_his")!=null){
					 pastDisHis=num.getString("past_dis_his").replaceAll("<br>", "\n");
				 }
				 if(num.getString("body_his")!=null){
					 bodyHis=num.getString("body_his").replaceAll("<br>", "\n");
				 }
				 if(num.getString("fam_his")!=null){
					 famHis=num.getString("fam_his").replaceAll("<br>", "\n");
				 }
				 if(num.getString("patient_tell")!=null){
					 patientTell=num.getString("patient_tell").replaceAll("<br>", "\n");
				 }
				 id=num.getString("id");
				 BP=num.getString("BP");
				 sb.append(" <tr><td>主诉：</td><td><textarea rows=\"6\" cols=\"80\" name=\"patientTell\" id=\"patientTell\"  onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+patientTell+"</textarea></td></tr>");
				 sb.append(" <tr><td>血压：</td><td><input type=\"text\" name=\"BP\" id=\"BP\"  value=\""+BP+"\"\">mmHG</td></tr>");
					
				 sb.append(" <tr><input type=\"text\" id=\"id\" name=\"id\" value=\""+id+"\" style=\"display:none\" ><td>现病史：</td><td><textarea rows=\"6\" cols=\"80\" name=\"nowDisHis\" id=\"nowDisHis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+nowDisHis+"</textarea></td></tr>");
				 sb.append(" <tr><td>既往史：</td><td><textarea rows=\"6\" cols=\"80\" name=\"pastDisHis\" id=\"pastDisHis\"  onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+pastDisHis+"</textarea></td></tr>");
				 sb.append(" <tr><td>个人史：</td><td><textarea rows=\"6\" cols=\"80\" name=\"bodyHis\" id=\"bodyHis\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+bodyHis+"</textarea></td></tr>");
				 sb.append(" <tr><td>家族史：</td><td><textarea rows=\"6\" cols=\"80\" name=\"famHis\" id=\"famHis\"   onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+famHis+"</textarea></td></tr>");
			
				 sb.append(" <tr><td>患者现状：</td><td><textarea rows=\"6\" cols=\"80\" name=\"patientStatus\" id=\"patientStatus\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+patientStatus+"</textarea></td></tr>");
				 sb.append(" <tr><td>医生诊断结果：</td><td><textarea rows=\"6\" cols=\"80\" name=\"doctorDiagnoseResult\" id=\"doctorDiagnoseResult\" onBlur=\"save()\" onpropertychange=\"if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5\">"+doctorDiagnoseResult+"</textarea></td></tr>");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	// patient
		public String patientDoctorSearch( ){
			String userName="";
			String userId="";
			String str="select distinct u.user_name,u.user_id  from user u where u.authority=2;";
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				 
				ResultSet num=pstmt.executeQuery();
				 while(num.next()){
					 userName=num.getString("user_name");
					 userId=num.getString("user_id");
					 sb.append("<option value=\""+userId+"\">"+userName+"</option>");
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
			String id="";
			String str="select pc.id,p.patient_id,p.patient_name,pc.diagnose_date from patient p,patient_case pc where p.patient_id=pc.patient_id and  p.patient_id like '%"+searchText+"%' or p.patient_name like '%"+searchText+"%';";
			System.out.println("patientSearch_________________"+str);
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				 
				ResultSet num=pstmt.executeQuery();
				 while(num.next()){
					  patientId=num.getString("patient_id");
					  id=num.getString("id");
					 patientName=num.getString("patient_name");
					 creatDate=num.getString("diagnose_date");
					 sb.append("<tbody><tr><td><a href=\"diseaseDetail.jsp?patientId="+patientId+"&equiId="+patientId+"&pillId="+patientId+"&id="+id+"\">"+patientId+"</td><td>"+patientName+"</a></td><td>"+creatDate+"</td></tr></tbody>");
						}
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return sb.toString();
		}
		
		
		public String pillfind(String patientId){
			String pillName="";
			String price="";
			String pillNum="";
			String pillAmount="";
			
			String str="select p.pill_num,p.pill_name,p.price,pl.pill_amount from pill_list pl,pill p where pl.pill_id=p.pill_num and pl.patient_id='"+patientId+"' ;";
			System.out.println("pillfind______________"+str);
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				 
				ResultSet num=pstmt.executeQuery();
				 while(num.next()){
					 pillNum=num.getString("pill_num");
					 pillName=num.getString("pill_name");
					 price=num.getString("price");
					 pillAmount=num.getString("pill_amount");
					 sb.append(" <tr id=\""+pillNum+"\"><td><input type=\"text\" name=\"pillNum\" value=\""+pillNum+"\" style=\"display:none;\"><input type=\"text\" value=\""+pillName+"\" name=\"pillName\" readonly></td><td><input type=\"text\" value=\""+price+"\" name=\"price\"  readonly></td><td><input type=\"text\" name=\"amount\" id=\"amount\" name=\"amount\" onBlur=\"ischeckNum('amount');\" value=\""+pillAmount+"\"   >个</td><td><input type=\"button\" value=\"删除\" class=\"btn blue\" onclick=\"dele("+pillNum+")\"></td></tr>");
					 		}
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return sb.toString();
			
		}
		
		public boolean pillCaseDel(String patientId,String pillId){
			boolean res=false;
			String str="delete from pill_list where patient_id='"+patientId+"' and pill_id='"+pillId+"' and pill_date=current_date();";
			 System.out.println("pillCaseDel_______"+str);
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				 
				int num=pstmt.executeUpdate();
				if( num>0){res=true;}
				
					
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return res;
		}
}
