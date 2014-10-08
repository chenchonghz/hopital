package hospital.service.commonSearch;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class commonSearch {
	static Connection conn;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt2=null;
	static PreparedStatement pstmt3=null;
	static ResultSet rs=null;
	StringBuffer sb = new StringBuffer();

	
	 static java.util.Date date = new java.util.Date();
	 static SimpleDateFormat sy1=new SimpleDateFormat("yyyyMMdd");
	 static String dateFormat=sy1.format(date);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		commonSearch com=new commonSearch();
		System.out.print(dateFormat);
	}
	
//***********************patient_____start******************************************	
	public String patientSearch(){
		String patientId="";
		String id="";
		String patientName="";
		String creatDate="";
		String str="select p.patient_id,p.patient_name,p.creat_date,pc.id from patient p,patient_case pc where pc.patient_id=p.patient_id  order by p.creat_date desc;";
		System.out.println("patientSearch_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				  patientId=num.getString("patient_id");
				  id=num.getString("id");
				 patientName=num.getString("patient_name");
				 creatDate=num.getString("creat_date");
				sb.append(" <tr><td><a href=\"patientDetail.jsp?patientId="+patientId+"\">"+patientId+"</a></td><td><a href=\"../../watchDisease/diseaseDetail.jsp?patientId="+patientId+"&equiId=&pillId=&id="+id+" \">"+patientName+"</a></td><td>"+creatDate+"</td><td><input type=\"button\" value=\"删除\"  class=\"btn blue\" onclick=\"dele("+patientId+")\"></td></tr> ");
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
		String str="select patient_id,patient_name,creat_date from patient  where  patient_id like '%"+searchText+"%' or patient_name like '%"+searchText+"%';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				  patientId=num.getString("patient_id");
				 patientName=num.getString("patient_name");
				 creatDate=num.getString("creat_date");
				sb.append("<tbody><tr><td><a href=\"patientDetail.jsp?patientId="+patientId+"\">"+patientId+"</td><td>"+patientName+"</a></td><td>"+creatDate+"</td><td><input type=\"button\" class=\"btn blue\" value=\"删除\"  onclick=\"dele("+patientId+")\"></td></tr></tbody>");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public boolean deletePatient(String id){
		boolean res=false;
		String strd="delete from patient where patient_id='"+id+"';";
		String str="delete from reCheckReg where patient_id='"+id+"';";
		String str2="delete from patient_case where patient_id='"+id+"';";
		System.out.println(strd);
		System.out.println(str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(strd);
			pstmt2=conn.prepareStatement(str);
			pstmt3=conn.prepareStatement(str2);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			res=true;
			dbConn.close(conn);
			return res;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return res;
	}
	public String patientDetailSearch(String patientId){
		String patientName="";
		String sex="";
		String age="";
		String identityNumber=""; 
		String contactNumber="";
		int regDepartment=0;
		String ig="";
		String job="";
		String id="";
		String brithday="";
		String national="";
		String nowAddress="";
		String nowPostCode="";
		String huAddress="";
		String huPostCode="";
		String jobAddress="";
		String jobNumber="";
		String contactName="";
		String relationship="";
		String relationNumber="";
		String relationAddress="";
		String marriage="";
		String trust="";
		String diseaseOffer="";
		
		//String str="select pc.id ig,p.*,w.id from patient p,ward w,patient_case pc where p.patient_id="+patientId+" and p.patient_id=w.patient_id and pc.patient_id=p.patient_id;";
		String str="select pc.id ig,p.* from patient p,patient_case pc where p.patient_id="+patientId+"  and pc.patient_id=p.patient_id;";
		System.out.println("patientDetailSearch___"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 sex=num.getString("sex");
				 ig=num.getString("ig");
				 //id=num.getString("id");
				 patientName=num.getString("patient_name");
				 age=num.getString("age");
				 trust=num.getString("trust");
				 diseaseOffer=num.getString("disease_offer");
				 if(num.getString("identity_number")!=null){
					 identityNumber=num.getString("identity_number");
				 }
				 if(num.getString("contact_number")!=null){
					 contactNumber=num.getString("contact_number");
				 }
				 regDepartment=Integer.parseInt(num.getString("reg_department"));
				 if(num.getString("marriage")!=null){
					 marriage=num.getString("marriage");
				 }
				 if(num.getString("job")!=null){
					 job=num.getString("job");
				 }
				 if(num.getString("brithday")!=null){
					 brithday=num.getString("brithday");
				 }
				 if(num.getString("national")!=null){
					 national=num.getString("national");
				 }
				 if(num.getString("now_address")!=null){
					 nowAddress=num.getString("now_address");
				 }
				 if(num.getString("now_post_code")!=null){
					 nowPostCode=num.getString("now_post_code");
				 }
				 if(num.getString("hu_address")!=null){
					 huAddress=num.getString("hu_address");
				 }
				 if(num.getString("hu_post_code")!=null){
					 huPostCode=num.getString("hu_post_code");
				 }
				 if(num.getString("job_address")!=null){
					 jobAddress=num.getString("job_address");
				 }
				 if(num.getString("job_number")!=null){
					 jobNumber=num.getString("job_number");
				 }
				 if(num.getString("contact_name")!=null){
					 contactName=num.getString("contact_name");
				 }
				 if(num.getString("relationship")!=null){
					 relationship=num.getString("relationship");
				 }
				 if(num.getString("relation_number")!=null){
					 relationNumber=num.getString("relation_number");
				 }
				 if(num.getString("relation_address")!=null){
					 relationAddress=num.getString("relation_address");
				 }
				 
				 
				 sb.append("<tr> <td colspan=\"3\">*患者编号：<input type=\"text\" name=\"ig\" id=\"ig\" value=\""+ig+"\" style=\"display:none\"> <input type=\"text\" name=\"patientId\" id=\"patientId\" value=\""+patientId+"\" readonly> </td> </tr>");//病案号:"+id+"
				 sb.append("<tr> <td>*患者姓名： <input type=\"text\" name=\"patientName\" id=\"patientName\"value=\""+patientName+"\" style=\"width: 70px\"> *年龄： <input type=\"text\" name=\"age\" id=\"age\" onBlur=\"ischeckNum('age');\" style=\"width: 70px\"  value=\""+age+"\">岁</td> <td>性别：");
				 if(sex.equals("1")){
						sb.append("<input type=\"radio\" name=\"sex\" id=\"sex\" value=\"1\" checked=\"checked\" />男<input type=\"radio\" name=\"sex\" id=\"sex\" value=\"2\" />女");
					}else{
						sb.append("<input type=\"radio\" name=\"sex\" id=\"sex\" value=\"1\"  />男<input type=\"radio\" name=\"sex\" id=\"sex\" value=\"2\" checked=\"checked\"/>女");
					}
				 sb.append("</td> <td>职业： <input type=\"text\" name=\"job\" id=\"job\" value=\""+job+"\" style=\"width: 70px\"> </tr>");
				 sb.append("<tr>  <td>身份证号： <input type=\"text\" name=\"identityNumber\" id=\"identityNumber\" value=\""+identityNumber+"\" style=\"width: 170px\"> </td><td colspan=\"2\">出生日期： <input type=\"text\" value=\""+brithday+"\" style=\"width: 170px\"  name=\"brithday\" id=\"brithday\" style=\"border:1px solid #999;\" onfocus=\"setday(this)\"  /> </td>  </tr>");
				 sb.append(" <tr> <td>民族： <input type=\"text\" name=\"national\" id=\"national\" value=\""+national+"\" style=\"width: 70px\"> 婚姻： <input type=\"text\" name=\"marriage\" id=\"marriage\" value=\""+marriage+"\" style=\"width: 70px\"> </td> <td colspan=\"2\">挂号部门： <select id=\"regDepartment\" name=\"regDepartment\">");
				 switch(regDepartment){
					case 104:sb.append("<option value=\"105\" >儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" selected>内科</option><option value=\"101\" >外科</option><option value=\"103\">牙科</option><option value=\"102\">妇科</option>");break;
					case 101:sb.append("<option value=\"105\" >儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" >内科</option><option value=\"101\" selected>外科</option><option value=\"103\">牙科</option><option value=\"102\">妇科</option>");break;
					case 103:sb.append("<option value=\"105\" >儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" >内科</option><option value=\"101\" >外科</option><option value=\"103\" selected>牙科</option><option value=\"102\">妇科</option>");break;
					case 102:sb.append("<option value=\"105\" >儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" >内科</option><option value=\"101\" >外科</option><option value=\"103\">牙科</option><option value=\"102\" selected>妇科</option>");break;
					case 105:sb.append("<option value=\"105\" selected>儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" >内科</option><option value=\"101\" >外科</option><option value=\"103\">牙科</option><option value=\"102\"  >妇科</option>");break;
					case 106:sb.append("<option value=\"105\" >儿科</option><option value=\"106\" selected>眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" >内科</option><option value=\"101\" >外科</option><option value=\"103\">牙科</option><option value=\"102\"  >妇科</option>");break;
					case 107:sb.append("<option value=\"105\" >儿科</option><option value=\"106\">眼科</option><option value=\"107\" selected>耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" >内科</option><option value=\"101\" >外科</option><option value=\"103\">牙科</option><option value=\"102\"  >妇科</option>");break;
					case 108:sb.append("<option value=\"105\" >儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\" selected>口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" >内科</option><option value=\"101\" >外科</option><option value=\"103\">牙科</option><option value=\"102\"  >妇科</option>");break;
					case 109:sb.append("<option value=\"105\" >儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\" selected>皮肤科</option><option value=\"110\">中医科</option><option value=\"104\" >内科</option><option value=\"101\" >外科</option><option value=\"103\">牙科</option><option value=\"102\"  >妇科</option>");break;
					case 110:sb.append("<option value=\"105\" >儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\" selected>中医科</option><option value=\"104\" >内科</option><option value=\"101\" >外科</option><option value=\"103\">牙科</option><option value=\"102\" >妇科</option>");break;
					
					}
				 sb.append("</select></td></tr>");
				 sb.append("<tr> <td>病史提供者： <input type=\"text\" name=\"diseaseOffer\" id=\"diseaseOffer\" value=\""+diseaseOffer+"\" style=\"width: 170px\"> </td> <td colspan=\"2\">可靠程度： ");
				 switch(Integer.parseInt(trust)){
					case 1:sb.append("<select id=\"trust\" name=\"trust\"> <option value=\"1\" selected>可靠</option> <option value=\"2\">基本可靠</option> <option value=\"3\">不可靠</option> </select> </td> </tr>");break;
					case 2:sb.append("<select id=\"trust\" name=\"trust\"> <option value=\"1\">可靠</option> <option value=\"2\" selected>基本可靠</option> <option value=\"3\">不可靠</option> </select> </td> </tr>");break;
					case 3:sb.append("<select id=\"trust\" name=\"trust\"> <option value=\"1\">可靠</option> <option value=\"2\">基本可靠</option> <option value=\"3\" selected>不可靠</option> </select> </td> </tr>");break;
					}
				 sb.append(" <tr> <td>现住址 ： <input type=\"text\" name=\"nowAddress\" id=\"nowAddress\" value=\""+nowAddress+"\" style=\"width: 500px\"> </td> <td>电话： <input type=\"text\" name=\"contactNumber\" id=\"contactNumber\" value=\""+contactNumber+"\" style=\"width: 170px\"> </td> <td>邮编： <input type=\"text\" name=\"nowPostCode\" id=\"nowPostCode\" value=\""+nowPostCode+"\" style=\"width: 70px\"> </td> </tr>");
				 sb.append("<tr> <td colspan=\"2\">户口住址： <input type=\"text\" name=\"huAddress\" id=\"huAddress\" value=\""+huAddress+"\" style=\"width: 500px\"> </td> <td>邮编： <input type=\"text\" name=\"huPostCode\" id=\"huPostCode\" value=\""+huPostCode+"\" style=\"width: 70px\"> </td>  </tr>");
				 sb.append("<tr> <td colspan=\"2\">工作单位： <input type=\"text\" name=\"jobAddress\" id=\"jobAddress\" value=\""+jobAddress+"\" style=\"width: 500px\"> </td> <td>电话： <input type=\"text\" name=\"jobNumber\" id=\"jobNumber\" value=\""+jobNumber+"\" style=\"width: 170px\">  </tr>");
				 sb.append(" <tr> <td colspan=\"3\">联系人姓名： <input type=\"text\" name=\"contactName\" id=\"contactName\" value=\""+contactName+"\" style=\"width: 70px\"> 关系： <input type=\"text\" name=\"relationship\" id=\"relationship\" value=\""+relationship+"\" style=\"width: 70px\"> 电话： <input type=\"text\" name=\"relationNumber\" id=\"relationNumber\" value=\""+relationNumber+"\" style=\"width: 170px\"> </td> </tr>");
				 sb.append("<tr> <td colspan=\"3\">联系人住址： <input type=\"text\" name=\"relationAddress\" id=\"relationAddress\" value=\""+relationAddress+"\" style=\"width: 500px\"> </td> </tr>");
				  
			 }
			 
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
//***********************patient_____end******************************************	

	
	
	
//***********************user_____start******************************************
	public String userSearch(){
		String userId="";
		String userName="";
		String department="";
		String position="";
		String str="select user_id,user_name,department,position from user;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 userId=num.getString("user_id");
				 userName=num.getString("user_name");
				 department=num.getString("department");
				 position=num.getString("position");
				 sb.append("<tr><td>"+userId+"</td><td><a href=\"employeeDetail.jsp?userId="+userId+"\">"+userName+"</a></td><td>"+department+"</td><td>"+position+"</td><td><input type=\"button\" class=\"btn blue\" value=\"删除\"  onclick=\"dele("+userId+")\"></td></tr>");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public String useSearch(String id){
		String userId="";
		String userName="";
		String department="";
		String position="";
		String str="select user_id,user_name,department,position from user where user_id='"+id+"';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 userId=num.getString("user_id");
				 userName=num.getString("user_name");
				 department=num.getString("department");
				 position=num.getString("position");
				 sb.append("<tr><td>"+userId+"</td><td><a href=\"employeeDetail.jsp?userId="+userId+"\">"+userName+"</a></td><td>"+department+"</td><td>"+position+"</td> </tr>");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public String userSearch(String searchText){
		String userId="";
		String userName="";
		String department="";
		String position="";
		String str="select user_id,user_name,department,position from user where user_id like '%"+searchText+"%' or user_name like '%"+searchText+"%';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 userId=num.getString("user_id");
				 userName=num.getString("user_name");
				 department=num.getString("department");
				 position=num.getString("position");
				 sb.append("<tr><td>"+userId+"</td><td><a href=\"employeeDetail.jsp?userId="+userId+"\">"+userName+"</a></td><td>"+department+"</td><td>"+position+"</td><td><input type=\"button\" class=\"btn blue\" value=\"删除\"  onclick=\"dele("+userId+")\"></td></tr>");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public boolean deleteUser(String id){
		boolean res=false;
		String strd="delete from user where user_id='"+id+"';";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(strd);
			int num=pstmt.executeUpdate();
			res=true;
			dbConn.close(conn);
			return res;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return res;
	}
	
	public String userDetailSearch(String userId){
		String password="";
		int authority=0;
		int departId=0; 
		String department="";
		String userName="";
		String contactNumber="";
		String position="";
		String brithday=""; 
		String inday=""; 
		String address="";
		String education=""; 
		String sex="";
		
		String str="select * from user  where user_id="+userId+";";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 password=num.getString("password");
				 authority=Integer.parseInt(num.getString("authority"));
				 departId=Integer.parseInt(num.getString("depart_id"));
				 department=num.getString("department");
				 userName=num.getString("user_name");
				 contactNumber=num.getString("tel_number");
				 position=num.getString("position");
				 brithday=num.getString("brithday");
				 inday=num.getString("inday");
				 address=num.getString("address");
				 sex=num.getString("sex");
				 education=num.getString("education");
				 
				 sb.append("<tr><td>员工编号：</td><td><input type=\"text\" name=\"userId\" id=\"userId\" value="+userId+" readonly></td></tr>");
				 sb.append("<tr><td>员工密码：</td><td><input type=\"text\" name=\"password\" id=\"password\" value="+password+" ></td></tr>");
				 sb.append("<tr><td>*员工姓名：</td><td><input type=\"text\" name=\"userName\" id=\"userName\" value=\""+userName+"\"></td></tr>");
				 if(sex.equals("1")){
						sb.append("<tr><td>性别：</td><td><input type=\"radio\" name=\"sex\" id=\"sex\" value=\"1\" checked=\"checked\" />男<input type=\"radio\" name=\"sex\" id=\"sex\" value=\"2\" />女</td></tr>");
					}else{
						sb.append("<tr><td>性别：</td><td><input type=\"radio\" name=\"sex\" id=\"sex\" value=\"1\"  />男<input type=\"radio\" name=\"sex\" id=\"sex\" value=\"2\" checked=\"checked\"/>女</td></tr>");
					}
				 sb.append("<tr><td>出生日期：</td><td><input type=\"text\" name=\"brithday\" id=\"brithday\" style=\"border:1px solid #999;\" onfocus=\"setday(this)\"   value=\""+brithday+"\" /></td></tr>");
				 sb.append("<tr><td>所属部门：</td><td><select   id=\"department\"   name=\"department\"> ");
				 switch(departId){
				 case 102:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" selected>妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 101:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" >妇科</option><option value=\"101\" selected>外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 103:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\" selected>牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 104:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\" selected>内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 105:sb.append("<option value=\"105\" selected>儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option> <option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 106:sb.append("<option value=\"105\">儿科</option><option value=\"106\" selected>眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option> <option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 107:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\" selected>耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option> <option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 108:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\" selected>口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option> <option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 109:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\" selected>皮肤科</option><option value=\"110\">中医科</option> <option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 110:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\" selected>中医科</option> <option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 201:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\" selected>前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 301:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\" selected>药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 401:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\" selected>收款处</option><option value=\"501\">CT室</option><option value=\"901\" >院长室</option>");break;
				 case 501:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\" selected>CT室</option><option value=\"901\" >院长室</option>");break;
				 case 901:sb.append("<option value=\"105\">儿科</option><option value=\"106\">眼科</option><option value=\"107\">耳鼻喉科</option><option value=\"108\">口腔科</option><option value=\"109\">皮肤科</option><option value=\"110\">中医科</option><option value=\"102\" >妇科</option><option value=\"101\">外科</option><option value=\"103\">牙科</option><option value=\"104\">内科</option><option value=\"201\">前台</option><option value=\"301\">药房</option><option value=\"401\">收款处</option><option value=\"501\">CT室</option><option value=\"901\" selected>院长室</option>");break;
				 }
				 sb.append("</select></td></tr><tr><td>*联系方式：</td><td><input type=\"text\" name=\"contactNumber\" id=\"contactNumber\" value=\""+contactNumber+"\"></td></tr>");
				 sb.append("<tr><td>现住址：</td><td><input type=\"text\" name=\"address\" id=\"address\" value=\""+address+"\"></td></tr>");
				 sb.append("<tr><td>学历：</td><td><input type=\"text\" name=\"education\" id=\"education\" value=\""+education+"\"></td></tr><tr> <td>入职日期：</td> <td><input type=\"text\" name=\"inday\" id=\"inday\" style=\"border:1px solid #999;\" onfocus=\"setday(this)\"  value=\""+inday+"\"  /></td>  </tr><tr><td>权限设置：</td><td><select   id=\"authority\"   name=\"authority\"> ");
				 switch(authority){
				 case 1:sb.append("<option value=\"1\" selected>院长</option><option value=\"2\">医生</option><option value=\"3\">护士</option><option value=\"4\">药剂师</option><option value=\"5\">财务</option><option value=\"6\">仪器检测员</option>");break;
				 case 2:sb.append("<option value=\"1\" >院长</option><option value=\"2\" selected>医生</option><option value=\"3\">护士</option><option value=\"4\">药剂师</option><option value=\"5\">财务</option><option value=\"6\">仪器检测员</option>");break;
				 case 3:sb.append("<option value=\"1\" >院长</option><option value=\"2\">医生</option><option value=\"3\" selected>护士</option><option value=\"4\">药剂师</option><option value=\"5\">财务</option><option value=\"6\">仪器检测员</option>");break;
				 case 4:sb.append("<option value=\"1\" >院长</option><option value=\"2\">医生</option><option value=\"3\">护士</option><option value=\"4\" selected>药剂师</option><option value=\"5\">财务</option><option value=\"6\">仪器检测员</option>");break;
				 case 5:sb.append("<option value=\"1\" >院长</option><option value=\"2\">医生</option><option value=\"3\">护士</option><option value=\"4\">药剂师</option><option value=\"5\" selected>财务</option><option value=\"6\">仪器检测员</option>");break;
				 case 6:sb.append("<option value=\"1\" >院长</option><option value=\"2\">医生</option><option value=\"3\">护士</option><option value=\"4\">药剂师</option><option value=\"5\">财务</option><option value=\"6\" selected>仪器检测员</option>");break;
													
				 }
				 sb.append("</select></td></tr>");
				 
			 }
			 
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
//***********************user_____end******************************************
	
	
	
//**********************equipment_____start******************************************
	public String equipSearch(int i){
		String equipId="";
		String equipName="";
		String checkedDate="";
		int checkCycle=0;
		int checkdate=0;
		String str="select equip_id,equip_name,checked_date,check_cycle from equipment;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 equipId=num.getString("equip_id");
				 equipName=num.getString("equip_name");
				 if(num.getString("checked_date")!=null){
					 checkedDate=num.getString("checked_date");
					  checkdate=Integer.parseInt(checkedDate.replace("-",""));
				 }
				 
				 if(num.getString("check_cycle")!=null){
					 checkCycle=Integer.parseInt(num.getString("check_cycle"));
				 }
				 if(i==1){sb.append("<tr><td>"+equipId+"</td><td><a href=\"equipDetail.jsp?equipId="+equipId+"\">"+equipName+"</a></td><td>"+checkedDate+"</td>");
				 int nowDate=Integer.parseInt(dateFormat);//当前时间 
				 
				
				 if(nowDate>(checkCycle*10000+checkdate)){
					 sb.append("<td>Y</td>");
				 }else{
					 sb.append("<td>N</td>");
				 }
				 sb.append("<td><input type=\"button\" class=\"btn blue\" value=\"删除\"  onclick=\"dele("+equipId+")\"></td></tr>");
				 }else{
					 sb.append("<tr><td>"+equipId+"</td><td><a href=\"equipDetail.jsp?equipId="+equipId+"\">"+equipName+"</a></td><td>"+checkedDate+"</td>");
					 int nowDate=Integer.parseInt(dateFormat);//当前时间 
					 
					
					 if(nowDate>(checkCycle*10000+checkdate)){
						 sb.append("<td>Y</td>");
					 }else{
						 sb.append("<td>N</td>");
					 }
					 sb.append("<td>N</td> </tr>");
					 
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
	public String equipSearch(String searchText){
		String equipId="";
		String equipName="";
		String checkedDate="";
		int checkCycle=0;
		int checkdate=0;
		String str="select equip_id,equip_name,checked_date,check_cycle from equipment where equip_id like '%"+searchText+"%' or equip_name like '%"+searchText+"%';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
		System.out.println(str);	 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 equipId=num.getString("equip_id");
				 equipName=num.getString("equip_name");
				 if(num.getString("checked_date")!=null){
					 checkedDate=num.getString("checked_date");
					  checkdate=Integer.parseInt(checkedDate.replace("-",""));
				 }
				 
				 if(num.getString("check_cycle")!=null){
					 checkCycle=Integer.parseInt(num.getString("check_cycle"));
				 }
				 
				 sb.append("<tr><td>"+equipId+"</td><td><a href=\"equipDetail.jsp?equipId="+equipId+"\">"+equipName+"</a></td><td>"+checkedDate+"</td>");
				 int nowDate=Integer.parseInt(dateFormat);//当前时间 
				 
				
				 if(nowDate>(checkCycle*10000+checkdate)){
					 sb.append("<td>Y</td>");
				 }else{
					 sb.append("<td>N</td>");
				 }
				 sb.append("<td><input type=\"button\" class=\"btn blue\" value=\"删除\"  onclick=\"dele("+equipId+")\"></td></tr>");
				 
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public boolean deleteEquip(String id){
		boolean res=false;
		String strd="delete from equipment where equip_id='"+id+"';";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(strd);
			int num=pstmt.executeUpdate();
			res=true;
			dbConn.close(conn);
			return res;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return res;
	}
	public String equipDetailSearch(String equipId){
		String equipName="";
		String price="";
		String number="";
		String checkPrice=""; 
		String buyDate="";
		String checkedDate="";
		String checkCycle=""; 
		
		String str="select * from equipment where equip_id='"+equipId+"';";
		System.out.println("equipDetailSearch(String equipId)______+"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 equipName=num.getString("equip_name");
				 price=num.getString("price");
				 number=num.getString("number");
				 checkPrice=num.getString("check_price");
				 buyDate=num.getString("buy_date");
				 checkedDate=num.getString("checked_date");
				 checkCycle=num.getString("check_cycle");
				 
				 sb.append("<tr><td>设备编号：</td><td><input type=\"text\" name=\"equipId\" id=\"equipId\" value=\""+equipId+"\" readonly></td></tr> ");
				 sb.append("<tr><td>*设备名称：</td><td><input type=\"text\" name=\"equipName\" id=\"equipName\" value=\""+equipName+"\"></td></tr>");
				 sb.append("<tr><td>设备价格：</td><td><input type=\"text\" name=\"price\" id=\"price\" value=\""+price+"\">元</td></tr>");
				 sb.append("<tr><td>单次检查价格：</td><td><input type=\"text\" name=\"checkPrice\" id=\"checkPrice\" value=\""+checkPrice+"\">元</td></tr>");
				 sb.append("<tr><td>设备数量：</td><td><input type=\"text\" name=\"number\" id=\"number\" value=\""+number+"\">台</td></tr>");
				 sb.append("<tr><td>购买日期：</td><td><input type=\"text\" name=\"buyate\" id=\"buyate\" style=\"border:1px solid #999;\" onfocus=\"setday(this)\" value=\""+buyDate+"\" ></td></tr>");
				 sb.append(" <tr><td>校正/检定日期：</td><td><input type=\"text\" value=\""+checkedDate+"\"  name=\"checkedate\" id=\"checkedate\" style=\"border:1px solid #999;\" onfocus=\"setday(this)\"  ></td></tr>");
				 sb.append("<tr><td>检定周期：</td><td><input type=\"text\" name=\"checkCycle\" id=\"checkCycle\" value=\""+checkCycle+"\">年</td></tr>");
				 
			 }
			 pstmt.close();
			 dbConn.close(conn);
			
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
		}
	
//***********************equipment_____end******************************************
	
	
	
	
//**********************pill_____start******************************************
	public String pillSearch(int i){
		String pillNum="";
		String pillName="";
		String str="select pill_num,pill_name from pill;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 pillNum=num.getString("pill_num");
				 pillName=num.getString("pill_name");
				 if(i==1){
					 sb.append("<tr><td>"+pillNum+"</td><td><a href=\"pillDetail.jsp?pillNum="+pillNum+"\">"+pillName+"</a></td><td><input class=\"btn blue\" type=\"button\" value=\"删除\"  onclick=\"dele("+pillNum+")\"></td></tr>");
				 }else{
					 sb.append("<tr><td>"+pillNum+"</td><td><a href=\"pillDetail.jsp?pillNum="+pillNum+"\">"+pillName+"</a></td><td>N</td> </tr>");
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
	
	public String pillSearch(String searchText){
		String pillNum="";
		String pillName="";
		String str="select pill_num,pill_name from pill where pill_num like '%"+searchText+"%' or pill_name like '%"+searchText+"%';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 pillNum=num.getString("pill_num");
				 pillName=num.getString("pill_name");
				 sb.append("<tr><td>"+pillNum+"</td><td><a href=\"pillDetail.jsp?pillNum="+pillNum+"\">"+pillName+"</a></td><td><input type=\"button\" class=\"btn blue\" value=\"删除\"  onclick=\"dele("+pillNum+")\"></td></tr>");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public boolean deletePill(String id){
		boolean res=false;
		String strd="delete from pill where pill_num='"+id+"';";
		
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(strd);
			int num=pstmt.executeUpdate();
			res=true;
			dbConn.close(conn);
			return res;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return res;
	}
	public String pillDetailSearch(String pillNum){
		String pillName="";
		String standard="";
		String price="";
		String pillId="";
		String number=""; 
		String pillType="";
		
		String str="select * from pill where pill_num='"+pillNum+"';";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 pillName=num.getString("pill_name");
				 standard=num.getString("standard");
				 price=num.getString("price");
				 number=num.getString("number");
				 pillId=num.getString("pill_id");
				 pillType=num.getString("pill_type");
				 
				 sb.append(" <tr style=\"display:none\"><td>药品编号：</td><td><input type=\"text\" name=\"pillNum\" id=\"pillNum\" readonly value="+pillNum+"></td></tr> ");
				 sb.append("<tr><td>*药品名称：</td><td><input type=\"text\" name=\"pillName\" id=\"pillName\" value=\""+pillName+"\"></td></tr>");
				 sb.append("<tr> <td>药品编号：</td> <td><input type=\"text\" name=\"pillId\" id=\"pillId\" readonly value=\""+pillId+"\"></td> </tr> ");
				 sb.append("<tr><td>规格：</td><td><input type=\"text\" name=\"standard\" id=\"standard\" value=\""+standard+"\"></td></tr>");
				 sb.append("<tr><td>单价：</td><td><input type=\"text\" name=\"price\" id=\"price\" value=\""+price+"\"></td></tr>");
				 sb.append("<tr><td>库存数量：</td><td><input type=\"text\" name=\"number\" id=\"number\" value=\""+number+"\"></td></tr>");
				 if(pillType.equals("1")){
					 sb.append("<tr> <td>药品类型：</td> <td><select id=\"pillType\" name=\"pillType\"> <option value=\"1\" selected>中药</option> <option value=\"2\">西药</option> <option value=\"3\">中成药</option> </select></td> </tr>");
				 }else if(pillType.equals("2")){
					 sb.append("<tr> <td>药品类型：</td> <td><select id=\"pillType\" name=\"pillType\"> <option value=\"1\" >中药</option> <option value=\"2\" selected>西药</option> <option value=\"3\">中成药</option> </select></td> </tr>");
					 
				 }else if(pillType.equals("3")){
					 sb.append("<tr> <td>药品类型：</td> <td><select id=\"pillType\" name=\"pillType\"> <option value=\"1\" >中药</option> <option value=\"2\">西药</option> <option value=\"3\" selected>中成药</option> </select></td> </tr>");
					 
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
	
	
	
	
	public String menzhenSearch(String sinceDate,String endDate){
		String patientName="";
		String sex="";
		String age="";
		String nowAddress="";
		String contactNumber="";
		String diagnoseDate="";
		String BP="";
		String patientTell="";
		String doctorDiagnoseResult="";
		String medicineUseMethod="";
		String dealStatus="";
		String reviewDate="";
		String userName="";
		 
		
		
		String str="select p.patient_name,p.sex,p.age,p.now_address,p.contact_number,pc.diagnose_date,pc.BP,pc.patient_tell,pc.doctor_diagnose_result,pc.medicine_use_method,pc.deal_status,pc.review_date,u.user_name from patient_case pc,patient p,user u where pc.patient_id=p.patient_id and pc.bydoctor_id=u.user_id and pc.diagnose_date>'"+sinceDate+"' and pc.diagnose_date<'"+endDate+"'  order by pc.diagnose_date desc;";
		System.out.println("menzhenSearch_________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 sex=num.getString("sex");
				 age=num.getString("age");
				 nowAddress=num.getString("now_address");
				 contactNumber=num.getString("contact_number");
				 diagnoseDate=num.getString("diagnose_date");
				 BP=num.getString("BP");
				 patientTell=num.getString("patient_tell");
				 doctorDiagnoseResult=num.getString("doctor_diagnose_result");
				 medicineUseMethod=num.getString("medicine_use_method");
				 dealStatus=num.getString("deal_status");
				 reviewDate=num.getString("review_date");
				 userName=num.getString("user_name");
				 
				 sb.append("<tr> <td>"+patientName+"</td> <td>"+sex+"</td> <td>"+age+"</td> <td>"+nowAddress+"</td> <td>"+contactNumber+"</td> <td>"+diagnoseDate+"</td> <td>"+BP+"mmHG</td>");
			sb.append("<td>"+patientTell+"</td><td>"+doctorDiagnoseResult+"</td><td>"+medicineUseMethod+"</td><td>"+dealStatus+"</td><td>"+reviewDate+"</td><td>"+userName+"</td>");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
//***********************pill_____end******************************************
	}
