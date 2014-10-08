package hospital.service;

import hospital.dao.dbConn;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class infoReg {

	/**
	 * @param args
	 */
	static Connection conn;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt2=null;
	static PreparedStatement pstmt3=null;
	static ResultSet rs=null;
	
	 static java.util.Date date = new java.util.Date();
	 static SimpleDateFormat sy1=new SimpleDateFormat("yyyy-MM-dd");
	 static SimpleDateFormat sy2=new SimpleDateFormat("yyyyMMdd");
	 static String dateFormat=sy1.format(date);
	 static String dateFormat2=sy2.format(date);
	 StringBuffer sb = new StringBuffer();
		
	
	public static void main(String[] args) {
		 infoReg inf=new infoReg();
		 
		 //System.out.print(inf.pillInsert("yuyuyu", "jkjkjk", 80.90,89) );
		// System.out.print(inf.findEmpNum());
	}
	
	//�����豸��Ϣ
	public boolean equiInsert(String equipName,double price,double checkPrice,int number,String buyate,String checkedate,int checkCycle){
		boolean result=false;
		infoReg inf=new infoReg();
		int equiId=inf.findEquiNum()+1;
		String str4="INSERT INTO `equipment` VALUES ('"+equiId+"', '"+equipName+"', '"+price+"', '"+number+"', '"+checkPrice+"', '"+buyate+"', '"+checkedate+"', '"+checkCycle+"');";
		System.out.println("equiInsert()______+"+str4);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str4);
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
	
	public boolean equiUpdate(String equipId,String equipName,double price,double checkPrice,int number,String buyate,String checkedate,int checkCycle){
		boolean result=false;
		String str4="";
		if(buyate!=null&&checkedate!=null){
			 str4="update equipment set equip_name='"+equipName+"',price='"+price+"',number='"+number+"',check_price='"+checkPrice+"',buy_date='"+buyate+"',checked_date='"+checkedate+"',check_cycle="+checkCycle+" where equip_id='"+equipId+"';";
		}else if(buyate==null&&checkedate!=null){
			 str4="update equipment set equip_name='"+equipName+"',price='"+price+"',number='"+number+"',check_price='"+checkPrice+"',buy_date=null,checked_date='"+checkedate+"',check_cycle="+checkCycle+" where equip_id='"+equipId+"';";
		}else if(buyate!=null&&checkedate==null){
			 str4="update equipment set equip_name='"+equipName+"',price='"+price+"',number='"+number+"',check_price='"+checkPrice+"',buy_date='"+buyate+"',checked_date=null,check_cycle="+checkCycle+" where equip_id='"+equipId+"';";
		}else if(buyate==null&&checkedate==null){
			 str4="update equipment set equip_name='"+equipName+"',price='"+price+"',number='"+number+"',check_price='"+checkPrice+"',buy_date='"+buyate+"',checked_date=null,check_cycle=null where equip_id='"+equipId+"';";
		}
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str4);
			System.out.println("equiUpdate()______+"+str4);
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
	//����Ա����Ϣ������true
	
	
	public boolean employeeInsert(String userName,int sex,String brithday,int department,String contactNumber,String address,String education,int authority,String inday){
		boolean result=false;
		infoReg inf=new infoReg();
		String dapartName=inf.finDeptNum(department);
		String positionName=inf.finPositionNum(authority);	
		int userId=0;
		userId=inf.findEmpNum()+1;
		if(brithday == null || brithday.equals("")){
			brithday="1900-01-01";
		}
		String str3="INSERT INTO `user` VALUES ('"+userId+"', '123456', '"+authority+"', '"+department+"', '"+dapartName+"', '"+userName+"', '"+contactNumber+"', '"+positionName+"', '"+brithday+"', '"+address+"','"+education+"', '"+sex+"','"+inday+"');";
		System.out.println(str3);
		 try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str3);
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
	
	public boolean employeeUpdate(String userId,String password,String userName,int sex,String brithday,int department,String contactNumber,String address,String education,int authority,String inday){
		boolean result=false;
		infoReg inf=new infoReg();
		String dapartName=inf.finDeptNum(department);
		String positionName=inf.finPositionNum(authority);	
		String str3="UPDATE user SET password = '"+password+"', authority = '"+authority+"',depart_id='"+department+"',department='"+dapartName+"',user_name='"+userName+"',tel_number='"+contactNumber+"',position='"+positionName+"',brithday ='"+brithday+"', address='"+address+"',education='"+education+"',sex='"+sex+"',inday='"+inday+"' WHERE user_id = '"+userId+"';";
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
	//����ҩƷ��Ϣ������true
	public boolean pillInsert(String pillName,String standard,double d,int number,String pillId,String pillType){
		boolean result=false;
		infoReg inf=new infoReg();
		int numb=0;
		numb=inf.findpillNum()+1;
		String str2="INSERT INTO `pill` VALUES ('"+numb+"', '"+pillName+"', '"+standard+"', '"+d+"', '"+number+"','"+pillId+"','"+pillType+"');";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str2);
			pstmt.executeUpdate();
			result=true;
			
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.print(str2);
		dbConn.close(conn);
		return result;
	}
	public boolean pillUpdate(String pillNum,String pillName,String standard,double d,int number,String pillId,String pillType){
		boolean result=false;
		infoReg inf=new infoReg();
		String str2="update pill set pill_name='"+pillName+"' ,standard='"+standard+"',price='"+d+"',number='"+number+"',pill_id='"+pillId+"',pill_type='"+pillType+"' where pill_num='"+pillNum+"';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str2);
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
	
	
	//���벡����Ϣ������ture
	public  boolean patientInsert(String patientName,int sex,int age,String identityNumber,String contactNumber,String regDepartment,String job,String brithday,String national,String nowAddress,String nowPostCode,String huAddress,String huPostCode,String jobAddress,String jobNumber,String contactName,String relationship,String relationNumber,String relationAddress,String marriage,String diseaseOffer,String trust){
		boolean result=false;
		infoReg inf=new infoReg();
		String L="";
		int id = 0;
		String patientId=dateFormat2+L;//201306061
		if(inf.findPatientDate().equals("nothing")){
			patientId=dateFormat2+"1";
		}else{
			L=inf.findPatientDate().substring(8,inf.findPatientDate().length()).trim();
			int i= Integer.parseInt(L);
			id=i+1;
			L=Integer.toString(id);
			patientId=dateFormat2+L;
		}
		if(brithday.equals("")||brithday==null){
			brithday=dateFormat;
		}
		String str="insert into patient values('"+patientId+"','"+patientName+"','"+sex+"','"+age+"','"+identityNumber+"','"+contactNumber+"','"+regDepartment+"','"+dateFormat+"','"+job+"','"+brithday+"','"+national+"','"+nowAddress+"','"+nowPostCode+"','"+huAddress+"','"+huPostCode+"','"+jobAddress+"','"+jobNumber+"','"+contactName+"','"+relationship+"','"+relationNumber+"','"+relationAddress+"','"+marriage+"','1','"+diseaseOffer+"','"+trust+"');";
		String str2="insert into patient_case(patient_id,patient_status,doctor_diagnose_result,medicine_use_method,diagnose_date,bydoctor_id,review_date,status,now_dis_his,past_dis_his,body_his,fam_his) values ('"+patientId+"', null, null, null, null, null, null,1,null,null,null,null);";
		String str3="insert into reCheckReg(patient_id,re_check_date,status) values('"+patientId+"',now(),1);";
		System.out.println("patientInsert____________"+str);
		System.out.println("patientInsert______________"+str2);
		System.out.println("patientInsert______________"+str3);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(str2);
			pstmt3=conn.prepareStatement(str3);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			result=true;
			dbConn.close(conn);
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return result;
		
	}
	
	public  boolean patientUpdate(String patientId,String patientName,int sex,int age,String identityNumber,String contactNumber,String regDepartment,String job,String brithday,String national,String nowAddress,String nowPostCode,String huAddress,String huPostCode,String jobAddress,String jobNumber,String contactName,String relationship,String relationNumber,String relationAddress,String marriage,String diseaseOffer,String trust){
		boolean result=false;
		infoReg inf=new infoReg();
		String L="";
		int id = 0;
		if(brithday.equals("")||brithday==null){
			brithday=dateFormat;
		}
		String str="UPDATE patient SET patient_name = '"+patientName+"', sex = "+sex+",age = "+age+",identity_number = '"+identityNumber+"',contact_number = '"+contactNumber+"',reg_department = '"+regDepartment+"',job = '"+job+"',brithday = '"+brithday+"',national = '"+national+"',now_address = '"+nowAddress+"',now_post_code = '"+nowPostCode+"',hu_address = '"+huAddress+"',hu_post_code = '"+huPostCode+"',job_address = '"+jobAddress+"',job_number = '"+jobNumber+"',contact_name = '"+contactName+"',relationship = '"+relationship+"',relation_number = '"+relationNumber+"',relation_address = '"+relationAddress+"',marriage = '"+marriage+"',disease_offer='"+diseaseOffer+"',trust='"+trust+"' WHERE patient_id='"+patientId+"';";
		System.out.print("patientUpdate______"+str);
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
	//select patient_id from patient where creat_date='2013-06-06'  limit 0,1;�ڼ���,����
	//�ҵ������id����ֵ��201306071�������з��ء�nothing��
	public String findPatientDate(){
		String patientId="nothing";
		  
		String s="select patient_id from patient where creat_date='"+dateFormat+"' order by  patient_id desc limit 0,1;";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(s);
			 
			ResultSet num=pstmt.executeQuery();
			 if(num.next()){
				 patientId=num.getString("patient_id");
			}
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return patientId;
	}
	
	
	//�ҵ�ҩƷ������һλ
	public int findpillNum(){
		int pillNum=10000;
		  
		String s="select pill_num from pill order by pill_num desc limit 0, 1;";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(s);
			 
			ResultSet num=pstmt.executeQuery();
			 if(num.next()){
				 pillNum=Integer.parseInt(num.getString(1));
			}
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return pillNum;
	}
	//�ҵ�������һλ
	public int findEmpNum(){
		int EmpNum=10100001;
		  
		String s="select user_id from user order by user_id desc limit 0, 1;";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(s);
			 
			ResultSet num=pstmt.executeQuery();
			 if(num.next()){
				 EmpNum=Integer.parseInt(num.getString(1));
			}
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return EmpNum;
	}
	//���Ҳ������
	public String finDeptNum(int deptId){
		String department="";
		  
		String s="select department from department_map where department_id='"+deptId+"';";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(s);
			 
			ResultSet num=pstmt.executeQuery();
			 if(num.next()){
				 department=num.getString(1);
			}
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return department;
	}
	//����ְλ���
	public String finPositionNum(int authority){
		String Position="";
		
		String s="select position from position where authority='"+authority+"';";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(s);
			 
			ResultSet num=pstmt.executeQuery();
			 if(num.next()){
				 Position=num.getString(1);
			}
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Position;
	}
	
	//�����豸���
	public int findEquiNum(){
		int equiNum=3001;
		  
		String s="select equip_id from equipment order by equip_id desc limit 0, 1;";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(s);
			 
			ResultSet num=pstmt.executeQuery();
			 if(num.next()){
				 equiNum=Integer.parseInt(num.getString(1));
			}
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return equiNum;
	}
	
	public  boolean patientReCheck(String patientId){
		boolean result=false;
		 
		String str="insert into reCheckReg(patient_id,re_check_date,status) values('"+patientId+"',now(),1);";
		System.out.print("patientReCheck______"+str);
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
	
	
	public boolean menzhenInsert(String pname,int sex,int age,String address, String pnum,String watchDate,String BP,
			String shoutizz,String chubuzd,String zhiliaoyy,String chuzhiqk,String fuzhendate, String doctor){
		boolean result=false;
	 
		String str="insert into menzhen(name, sex, age,address, connectNum, watchDate, BP,shoutizz, chubuzd, zhiliaoyy, chuzhiqk, fuzhendate,doctor) values ('"+pname+"',"+sex+","+age+",'"+address+"','"+pnum+"','"+watchDate+"','"+BP+"','"+shoutizz+"','"+chubuzd+"','"+zhiliaoyy+"','"+chuzhiqk+"','"+fuzhendate+"','"+doctor+"');";
		System.out.println("menzhenInsert()______+"+str);
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
	public boolean menzhenUpdate(String pname,int sex,int age,String address, String pnum,String watchDate,String BP,
			String shoutizz,String chubuzd,String zhiliaoyy,String chuzhiqk,String fuzhendate, String doctor,String id){
		boolean result=false;
	 
		String str="update menzhen set name='"+pname+"',age='"+age+"',sex='"+sex+"',address='"+address+"', connectNum='"+pnum+"', watchDate='"+watchDate+"', BP='"+BP+"',shoutizz='"+shoutizz+"', chubuzd='"+chubuzd+"', zhiliaoyy='"+zhiliaoyy+"', chuzhiqk='"+chuzhiqk+"', fuzhendate='"+fuzhendate+"',doctor='"+doctor+"' where id='"+id+"';";
		System.out.println("menzhenUpdate()______+"+str);
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
	public boolean menzhenDel(String id){
		boolean result=false;
	 
		String str="delete from menzhen where id='"+id+"';";
		System.out.println("menzhenDel()______+"+str);
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
	
	
	
	public String menzhenSearch(String startDay,String endDay,String patientName){
		
		String pname;
		String sex;
		String age;
		String address;
		String pnum;
		String watchDate;
		String BP;
		String shoutizz;
		String chubuzd;
		String zhiliaoyy;
		String chuzhiqk;
		String fuzhendate;
		String doctor;
		String ids;
		String str1;
		if(startDay ==""||endDay== ""){
			  str1="select * from menzhen where name like '%"+patientName+"%';";
		}else if(patientName ==""){
			  str1="select * from menzhen where watchDate>'"+startDay+"' and watchDate<'"+endDay+"';";
		}else{
			 str1="select * from menzhen where watchDate>'"+startDay+"' and watchDate<'"+endDay+"' and name like '%"+patientName+"%';";
		}
		
		System.out.println("menzhenSearch————————————————————————"+str1);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str1);
			ResultSet num=pstmt.executeQuery();
			 
			 while(num.next()){
				 pname=num.getString("name");
				 sex=num.getString("sex");
				 age=num.getString("age");
				 address=num.getString("address");
				 pnum=num.getString("connectNum");
				 watchDate=num.getString("watchDate");
				 BP=num.getString("BP");
				 shoutizz=num.getString("shoutizz");
				 chubuzd=num.getString("chubuzd");
				 zhiliaoyy=num.getString("zhiliaoyy");
				 chuzhiqk=num.getString("chuzhiqk");
				 fuzhendate=num.getString("fuzhendate");
				 doctor=num.getString("doctor");
				 ids=num.getString("id");
				 
				  
				 sb.append("<tr> <td style=\"display: none\"><input type=\"button\" value=\"保存\"  class=\"btn blue savea\"   data=\""+ids+"\"><input type=\"button\" value=\"删除\" onclick=\"del("+ids+")\" class=\"btn blue\"> </td>");
				 sb.append("<td><input type=\"text\" name=\"ids\" value=\""+ids+"\" class=\"ids\" style=\"display:none;\"/><input type=\"text\"   name=\"pname\" class=\"pname\" value=\""+pname+"\" style=\"width:70px;\" disabled=\"disabled\"/></td><td><div style=\"width:100px\">");
				 if(sex.equals("1")){
						sb.append("<input type=\"radio\" name=\"sex"+ids+"\"  class=\"sex\"   value=\"1\" checked=\"checked\" disabled=\"disabled\"/>男 <input type=\"radio\" name=\"sex"+ids+"\" class=\"sex\"  value=\"2\" disabled=\"disabled\"/>女 ");
					}else if(sex.equals("2")){
						sb.append("<input type=\"radio\" name=\"sex"+ids+"\"   class=\"sex\"  value=\"1\"  disabled=\"disabled\"/>男 <input type=\"radio\" checked=\"checked\" name=\"sex"+ids+"\"   class=\"sex\"  value=\"2\" disabled=\"disabled\"/>女 ");
					}
				 sb.append("</div></td><td><input type=\"text\"   name=\"age\" class=\"age\" value=\""+age+"\" style=\"width:50px;\" disabled=\"disabled\"/></td>");
				 sb.append(" <td><input type=\"text\"  class=\"pnum\" name=\"pnum\" value=\""+pnum+"\" style=\"width:100px;\" disabled=\"disabled\"/></td>");
				 sb.append("<td><input type=\"text\"  class=\"address\"   name=\"address\"  style=\"width:400px;\" value=\""+address+"\" disabled=\"disabled\"/></td>");
				 sb.append("<td><input type=\"text\"   style=\"width:100px;\" class=\"watchDate\"  name=\"watchDate\" style=\"border:1px solid #999;\" onfocus=\"setday(this)\" value=\""+watchDate+"\" disabled=\"disabled\"/></td>");
				 sb.append("<td><input type=\"text\"   style=\"width:100px;\" class=\"fuzhendate\"  name=\"fuzhendate\"  style=\"border:1px solid #999;\" onfocus=\"setday(this)\" value=\""+fuzhendate+"\" disabled=\"disabled\"/></td>");
				 sb.append("<td><input type=\"text\"  class=\"doctor\"  name=\"doctor\" value=\""+doctor+"\" style=\"width:70px;\" disabled=\"disabled\"/></td>");
				 sb.append("<td><input type=\"text\"   class=\"BP\" name=\"BP\" value=\""+BP+"\" style=\"width:70px;\" disabled=\"disabled\"/></td>");
				 sb.append("<td><textarea  class=\"shoutizz\" name=\"shoutizz\"  rows=\"1\" cols=\"110\"   disabled=\"disabled\">"+shoutizz+" </textarea></td>");
				 sb.append("<td><textarea class=\"chubuzd\" name=\"chubuzd\"  rows=\"1\" cols=\"110\"  disabled=\"disabled\">"+chubuzd+" </textarea></td>");
				 sb.append("<td><textarea class=\"zhiliaoyy\" name=\"zhiliaoyy\"  rows=\"1\" cols=\"110\"  disabled=\"disabled\">"+zhiliaoyy+" </textarea></td>");
				 sb.append("<td><textarea class=\"chuzhiqk\" name=\"chuzhiqk\"  rows=\"1\" cols=\"110\"  disabled=\"disabled\">"+chuzhiqk+" </textarea></td></tr> ");
			 
				  
			}
			 
			 
			 
			 
			 
			 pstmt.close();
			 
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
}
