package hospital.service.pill;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pillCaseSearch {
	static Connection conn;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt2=null;
	static ResultSet rs=null;
	StringBuffer sb = new StringBuffer();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String pillcaseSearch(String pillId){
		String status="";
		String payer="";
		String pillAmount="";
		String number="";
		String pillName="";
		String pillNum="";
		
		String str="select distinct pl.payer,p.pill_num,rl.status,pl.pill_amount,p.number,p.pill_name from pill p,pill_list pl,recepit_list rl where pl.id='"+pillId+"' and  p.pill_num=pl.pill_id and rl.receipt_id=pl.id and rl.pillOrEquip='1' order by p.pill_name;";
		System.out.println("pillcaseSearch________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			sb.append("<tr> <td>药品名称</td> <td>数量</td> <td>库存数量</td> </tr> ");
			 while(num.next()){
				 pillNum=num.getString("pill_num");
				 status=num.getString("status");
				 payer=num.getString("payer");
				  
				 pillAmount=num.getString("pill_amount");
				 number=num.getString("number");
				 pillName=num.getString("pill_name");
				 sb.append(" <tr><td><input type=\"text\" value=\""+pillName+"\" readonly><input type=\"text\" value=\""+pillNum+"\" style=\"display:none\" name=\"pillid\"></td>");
				 sb.append("<td><input type=\"text\" value=\""+pillAmount+"\" name=\"pillAmount\" readonly> </td><td><input type=\"text\" value=\""+number+"\" name=\"pillNum\" readonly></td></tr>");
			sb.append("<input type=\"text\"  value=\""+pillId+"\" name=\"id\" style=\"display:none\">");
			 }
			 if(status.equals("1")){
				 sb.append("<tr> <td>交款状态</td> <td colspan=\"2\"><input type=\"text\" value=\"未交款\" readonly></td> </tr>");
			 }else if(status.equals("2")){
				 sb.append("<tr> <td>交款状态</td> <td colspan=\"2\"><input type=\"text\" value=\"已交款\" readonly></td> </tr>");
			 }
			 if(payer==null ){
				 sb.append("<tr> <td>付药状态</td> <td colspan=\"2\" id=\"fyzt\">未付药</td> </tr>");
			 }else if(payer!=null){
				 sb.append("<tr> <td>付药状态</td> <td colspan=\"2\" id=\"fyzt\">已付药</td> </tr>");
			 }
			 
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public boolean payerUpdate(String id,String payer){
		boolean result=false;
		String str4="update pill_list set payer='"+payer+"' where id='"+id+"';";
		 
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
	
	
	public boolean pillAmountUpdate(String pillid,int pillNum){
		boolean result=false;
		String str4="update pill set number='"+pillNum+"' where pill_num='"+pillid+"';";
		 
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
	
	public String pillNotPaySearch(){
		String patientName="";
		String pillDate="";
		String id="";
		//String str="select pi.pill_name,pl.pill_amount from pill_list pl,patient p,pill pi where pl.pill_id=pi.pill_num and  pl.payer is null and pl.patient_id=p.patient_id and pl.pill_date between date_sub(now(),interval 6 month) and now();";
		String str2="select distinct p.patient_name,pl.pill_date,pl.id from pill_list pl,patient p,recepit_list rl where rl.status=1 and pl.patient_id=p.patient_id and rl.pillOrEquip=1 and  rl.receipt_id=pl.id and  rl.receipt_date between date_sub(now(),interval 6 month) and now();";
		try{
			conn=dbConn.getConn();
			//pstmt=conn.prepareStatement(str);
			//ResultSet num=pstmt.executeQuery();//药品名称 数量
			pstmt2=conn.prepareStatement(str2);
			ResultSet num2=pstmt2.executeQuery();//患者姓名、开药时间、id
			sb.append("<tr> <td>患者名称</td>  <td>处方编号</td> <td>处方开具日期</td> </tr>");
		 while(num2.next()){
			   patientName=num2.getString("patient_name");
			   pillDate=num2.getString("pill_date");
			   id=num2.getString("id");
				sb.append("<tr id=\""+id+"\"> <td>"+patientName+"</td> <td><a href=\"javascript:search("+id+")\">"+id+"</a></td> <td>"+pillDate+"</td> </tr>");	
		 } 
			 pstmt2.close();
			 dbConn.close(conn);
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public String pillPaySearch(){
		String patientName="";
		String pillDate="";
		String id="";
		String str2="select distinct p.patient_name,pl.pill_date,pl.id from pill_list pl,patient p,recepit_list rl where rl.status=1 and pl.patient_id=p.patient_id and rl.pillOrEquip=1 and  rl.receipt_id=pl.id and  rl.receipt_date between date_sub(now(),interval 6 month) and now();";
		try{
			conn=dbConn.getConn();
			pstmt2=conn.prepareStatement(str2);
			ResultSet num2=pstmt2.executeQuery();//患者姓名、开药时间、id
			sb.append("<tr> <td>患者名称</td>  <td>处方编号</td> <td>处方开具日期</td> </tr>");
		 while(num2.next()){
			   patientName=num2.getString("patient_name");
			   pillDate=num2.getString("pill_date");
			   id=num2.getString("id");
				sb.append("<tr id=\""+id+"\"> <td>"+patientName+"</td> <td><a href=\"javascript:search("+id+")\">"+id+"</a></td> <td>"+pillDate+"</td> </tr>");	
		 } 
			 pstmt2.close();
			 dbConn.close(conn);
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	
	public String pillNotPayList(String id){
		String pillName="";
		String pillAmount="";
		String str="select pl.pill_amount,p.pill_name from pill_list pl,pill p where p.pill_num=pl.pill_id and pl.id='"+id+"';";
		System.out.print(str);
		try{
			conn=dbConn.getConn();
			 pstmt=conn.prepareStatement(str);
			 ResultSet num=pstmt.executeQuery();//药品名称 数量
			 sb.append("<tr> <td>药品名称</td> <td>数量</td> </tr>");
		 while(num.next()){
			 pillName=num.getString("pill_name");
			 pillAmount=num.getString("pill_amount");
			 sb.append("<tr> <td>"+pillName+"</td> <td>"+pillAmount+"</td> </tr>");
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
