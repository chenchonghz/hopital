package hospital.service;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class reviewCheck {

	/**
	 * @param args
	 */
	
	static Connection conn;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt2=null;
	static ResultSet rs=null;
	
	 static java.util.Date date = new java.util.Date();
	 static SimpleDateFormat sy1=new SimpleDateFormat("yyyy-MM-dd");
	 static SimpleDateFormat sy2=new SimpleDateFormat("yyyyMMdd");
	 static String dateFormat=sy1.format(date);
	 static String dateFormat2=sy2.format(date);
	 
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/*本周内：
	select * from wap_content where week(created_at) = week(now)


	查询一天：
	select * from table where to_days(column_time) = to_days(now());select * from table where date(column_time) = curdate(); 


	查询7天：
	select * from table  where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(column_time);


	查询一个月：
	select * from table  where DATE_SUB(CURDATE(), INTERVAL INTERVAL 1 MONTH) <= date(column_time);
*/
	
	
	public String reviewSearch(){
		String contactNumber="";
		String diagnoseDate="";
		String patientName="";
		String reviewDate="";
		StringBuffer sb = new StringBuffer();
		String str="select p.patient_name ,p.contact_number,pc.diagnose_date,pc.review_date from  patient p,patient_case pc where p.patient_id=pc.patient_id and DATE_SUB(CURDATE(), INTERVAL -7 DAY) > date(pc.review_date)  and pc.review_date>now();";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				 contactNumber=num.getString("contact_number");
				 diagnoseDate=num.getString("diagnose_date");
				 reviewDate=num.getString("review_date");
				 
				 sb.append("<tr><td>"+patientName+"</td><td>"+contactNumber+"</td><td>"+diagnoseDate+"</td><td style=\"color:red\">"+reviewDate+"</td></tr>");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public String equiReviewSearch(){
		String equipId="";
		String equipName="";
		String checkedDate="";
		String checkCycle="";
		StringBuffer sb = new StringBuffer();
		String str="select * from equipment e where  DATE_SUB(CURDATE(), INTERVAL -1 MONTH)> date_add(e.checked_date, interval e.check_cycle year);";
		System.out.println("equiReviewSearch()____"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 equipId=num.getString("equip_id");
				 equipName=num.getString("equip_name");
				 checkedDate=num.getString("checked_date");
				 checkCycle=num.getString("check_cycle");
				 
				 sb.append("<tr><td style=\"width:90px\">"+equipName+"</td><td style=\"width:210px\">"+equipId+"</td><td style=\"width:160px\">"+checkedDate+"</td><td  >"+checkCycle+"</td></tr>");
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
