package hospital.service.pillList;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class pillListSearch {
	static Connection conn;
	static PreparedStatement pstmt=null;
	static ResultSet rs=null;
	StringBuffer sb = new StringBuffer();
	
	 static java.util.Date date = new java.util.Date();
	 static SimpleDateFormat sy=new SimpleDateFormat("yyyyMMdd");
	 static String dateFormat=sy.format(date);
	 static SimpleDateFormat sy2=new SimpleDateFormat("yyyy-MM-dd");
	 static String dateFormat2=sy2.format(date);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//pill_list
	public String pillListSearch(){
		String id="";
		String pcid="";
		String pillDate="";
		String patientName="";
		String str="select distinct p.patient_name,pl.id,pl.pill_date,pc.id pcid from patient_case pc, pill_list pl,patient p where pc.patient_id=p.patient_id and pc.diagnose_date=pl.pill_date and  pl.patient_id=p.patient_id order by pl.pill_date desc;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 patientName=num.getString("patient_name");
				  id=num.getString("id");
				  pcid=num.getString("pcid");
				  pillDate=num.getString("pill_date");
				  sb.append("<tr> <td>"+id+"</td><td>"+pillDate+"</td><td><a href=\"pillListDetail.jsp?id="+id+"&pcid="+pcid+"\">"+patientName+"</a></td></tr>");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String pillListSearch(String id,String pcid){
		String pillName="";
		String standard="";
		String pillAmount="";
		String medicineUseMethod="";
		String price="";
		float allPrice=0;
		String pillDate="";
		
		String str="select distinct p.pill_name,p.standard,pl.pill_amount,pc.medicine_use_method,pl.price,pl.all_price,pl.pill_date from pill p,pill_list pl,patient_case pc where pl.id='"+id+"' and p.pill_num=pl.pill_id and pl.patient_id=pc.patient_id;";
		System.out.print("pillListSearch___________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 
			 while(num.next()){
				 pillName=num.getString("pill_name");
				 standard=num.getString("standard");
				 pillAmount=num.getString("pill_amount");
				 
				 medicineUseMethod=num.getString("medicine_use_method");
				 price=num.getString("price");
				       
				 pillDate=num.getString("pill_date");
				 allPrice+=Float.parseFloat(num.getString("all_price"));
				 sb.append("<tr><td>"+pillName+"</td><td>"+standard+"</td><td>"+pillAmount+"</td><td>"+price+"元</td></tr>");
			 }
			 	sb.append("<tr><td>服用方法</td><td  colspan=\"3\">"+medicineUseMethod+"</td></tr>");
				sb.append("<tr><td>总价</td><td>"+allPrice+"元</td><td>药方日期</td><td>"+pillDate+"</td></tr>");
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return sb.toString();
	}
}
