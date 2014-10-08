package hospital.service.diseaseSearch;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class diseaseSearch {
	static Connection conn;
	static PreparedStatement pstmt=null;
	static ResultSet rs=null;
	StringBuffer sb = new StringBuffer();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public String disListSearch(){
		String patientId="";
		String diagnoseDate="";
		String department="";
		String patientName="";
		String userName="";
		String id="";
		
		String str="select u.user_name,p.patient_id,pc.id,pc.diagnose_date,u.department,p.patient_name  from user u,patient p,patient_case pc where pc.status=2 and pc.patient_id=p.patient_id and pc.bydoctor_id=u.user_id order by pc.diagnose_date desc;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientId=num.getString("patient_id");
				 diagnoseDate=num.getString("diagnose_date");
				 department=num.getString("department");
				 patientName=num.getString("patient_name");
				 userName=num.getString("user_name");
				 id=num.getString("id");
				 
				  sb.append(" <tr> <td>"+diagnoseDate+"</td><td>"+department+"</td><td><a href=\"diseaseDetail.jsp?patientId="+patientId+"&id="+id+"\">"+patientName+"</a></td> <td>"+userName+"</td></tr> ");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String disListSearch(String startDay,String endDay,String pname,String docname){
		String patientId="";
		String diagnoseDate="";
		String department="";
		String patientName="";

		String userName="";
		String pnameS="";
		String docnameS="";
		String startDayS="";
		String endDayS="";
		if(!startDay.equals("")){
			startDayS=" and pc.diagnose_date>'"+startDay+"' ";
		}
		
		if(!endDay.equals("")){
			endDayS=" and  pc.diagnose_date<'"+endDay+"' ";
		}
		if(!pname.equals("")){
		
			pnameS=" and p.patient_name like '%"+pname+"%' ";
		}
		
		if(!docname.equals("")){
			docnameS=" and u.user_name like '%"+docname+"%' ";
		}
		
		
		String str="select u.user_name,pc.id,pc.diagnose_date,u.department,p.patient_name  from user u,patient p,patient_case pc where pc.patient_id=p.patient_id and pc.bydoctor_id=u.user_id "+pnameS+docnameS+startDayS+endDayS+"order by pc.diagnose_date desc;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientId=num.getString("id");
				 diagnoseDate=num.getString("diagnose_date");
				 department=num.getString("department");
				 patientName=num.getString("patient_name");
				 userName=num.getString("user_name");
				  sb.append(" <tr> <td>"+diagnoseDate+"</td><td>"+department+"</td><td><a href=\"diseaseDetail.jsp?id="+patientId+"\">"+patientName+"</a></td><td>"+userName+"</td></tr> ");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String disDetailSearch(String patientId){
		String patientStatus="";
		String diagnoseDate="";
		String doctorDiagnoseResult="";
		String pillName="";
		String medicineUseMethod="";
		
		String str="select pc.medicine_use_method,pc.diagnose_date,pc.patient_status,pc.doctor_diagnose_result,p.pill_name  from patient_case pc,pill_list pl,pill p where pc.id='"+patientId+"' and pc.patient_id=pl.patient_id and pc.diagnose_date=pl.pill_date and pl.pill_id=p.pill_num;";
		System.out.println("disDetailSearch_______"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			
			
			 
			 while(num.next()){
				 patientStatus=num.getString("patient_status").replaceAll("\n", "<br>");
				 diagnoseDate=num.getString("diagnose_date").replaceAll("\n", "<br>");
				 doctorDiagnoseResult=num.getString("doctor_diagnose_result").replaceAll("\n", "<br>");
				 medicineUseMethod=num.getString("medicine_use_method").replaceAll("\n", "<br>");
				 
				
				 
				 pillName+=num.getString("pill_name")+" ";
				 
				 
			}
			 sb.append(" <tr><td>看病日期</td><td>"+diagnoseDate+"</td></tr>");
			 sb.append(" <tr> <td>患者现象</td> <td>"+patientStatus+"</td> </tr>");
			 sb.append(" <tr> <td>诊断结果</td> <td>"+doctorDiagnoseResult+"</td> </tr>");
			 sb.append(" <tr> <td>开具的药物</td> <td>"+pillName+"</td> </tr> ");
			 sb.append(" <tr> <td>药物服用方法</td> <td>"+medicineUseMethod+"</td></tr>");
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}

}
