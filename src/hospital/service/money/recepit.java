package hospital.service.money;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class recepit {
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
	
	
	public String recepitListSearch(){
		String receiptId="";
		String payer="";
		String receiptDate="";
		String status="";
		String pillOrEquip="";
		String str="select receipt_id,payer,receipt_date,status,pillOrEquip  from recepit_list order by receipt_id desc;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 receiptId=num.getString("receipt_id");
				 payer=num.getString("payer");
				 status=num.getString("status");
				 pillOrEquip=num.getString("pillOrEquip");
				 receiptDate=num.getString("receipt_date");
				 if(pillOrEquip.equals("1")){
					 if(status.equals("1")){
						 sb.append("<tr> <td>"+receiptId+"</td><td><a href=\"recepitDetail.jsp?patient="+receiptId+"&poe=1\">"+payer+"</a></td><td>否</td><td>药品</td></tr>");
					 }else{
						 sb.append("<tr> <td>"+receiptId+"</td><td><a href=\"recepitDetail.jsp?patient="+receiptId+"&poe=1\">"+payer+"</a></td><td>是</td><td>药品</td></tr>");
					 }
					 
				 }else{
					 if(status.equals("1")){
						 sb.append("<tr> <td>"+receiptId+"</td><td><a href=\"recepitDetail.jsp?patient="+receiptId+"&poe=2\">"+payer+"</a></td><td>否</td><td>设备</td></tr>");
					 }else{
						 sb.append("<tr> <td>"+receiptId+"</td><td><a href=\"recepitDetail.jsp?patient="+receiptId+"&poe=2\">"+payer+"</a></td><td>是</td><td>设备</td></tr>");
					 } 
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
	
	public String recepitListSearch(String receiptId){
		String payer="";
		String receiptDate="";
		String status="";
		String pillOrEquip="";
		String str="select receipt_id,payer,receipt_date,status,pillOrEquip  from recepit_list where receipt_id='"+receiptId+"' order by receipt_id desc;";
		System.out.println("recepitListSearch__________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 receiptId=num.getString("receipt_id");
				 payer=num.getString("payer");
				 status=num.getString("status");
				 pillOrEquip=num.getString("pillOrEquip");
				 receiptDate=num.getString("receipt_date");
				 if(pillOrEquip.equals("1")){
					 if(status.equals("1")){
						 sb.append("<tr> <td>"+receiptId+"</td><td><a href=\"recepitDetail.jsp?patient="+receiptId+"&poe=1\">"+payer+"</a></td><td>否</td><td>药品</td></tr>");
					 }else{
						 sb.append("<tr> <td>"+receiptId+"</td><td><a href=\"recepitDetail.jsp?patient="+receiptId+"&poe=1\">"+payer+"</a></td><td>是</td><td>药品</td></tr>");
					 }
					 
				 }else{
					 if(status.equals("1")){
						 sb.append("<tr> <td>"+receiptId+"</td><td><a href=\"recepitDetail.jsp?patient="+receiptId+"&poe=2\">"+payer+"</a></td><td>否</td><td>设备</td></tr>");
					 }else{
						 sb.append("<tr> <td>"+receiptId+"</td><td><a href=\"recepitDetail.jsp?patient="+receiptId+"&poe=2\">"+payer+"</a></td><td>是</td><td>设备</td></tr>");
					 } 
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
	
	public String recepit(String receiptId,String poe){
		int i=1;
		String str="";
		String equipName="";
		String pillName="";
		String pillAmount="";
		String payer="";
		String patientid="";
		String amount="";
		String receiptDate="";
		String status="";
		String no="";
		if(poe.equals("1")){
			str="select p.pill_name,pl.pill_amount,pl.patient_id,rl.* from recepit_list rl,pill p,pill_list pl where rl.pillOrEquip='1' and  rl.receipt_id='"+receiptId+"' and rl.receipt_id=pl.id and pl.pill_id=p.pill_num;";
		}else if(poe.equals("2")){
			str="select  e.equip_name,rl.* from equipment_list el,recepit_list rl,equipment e where rl.pillOrEquip='2' and  e.equip_id=el.equipment_id and el.id=rl.receipt_id and receipt_id='"+receiptId+"';";
		}
		
		System.out.print("recepit____________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 payer=num.getString("payer");
				 patientid=num.getString("patient_id");
				 amount=num.getString("amount");
				 status=num.getString("status");
				 receiptDate=num.getString("receipt_date");
				 no=num.getString("no");
				 if(poe.equals("1")){
					 pillName=num.getString("pill_name");
					 pillAmount=num.getString("pill_amount");
					 sb.append("<tr> <td>收费内容"+i+"</td> <td>"+pillName+"（"+pillAmount+"）</td> </tr>");
				i++;
				 }else if(poe.equals("2")){
					equipName=num.getString("equip_name");
					sb.append("<tr> <td>收费内容"+i+"</td> <td>"+equipName+"</td> </tr>");
					i++;
				}
				 
				 
				
			}
			 if(status.equals("1")){
				 sb.append(" <tr><td>收据编号<input type=\"text\" name=\"no\" id=\"no\" value=\""+no+"\" style=\"display:none\"></td><td><input type=\"text\" name=\"recepitId\" id=\"recepitId\" value="+receiptId+" style=\"display:none\">"+receiptId+"</td></tr>");
				 sb.append(" <tr><td>总价</td><td><input type=\"text\" name=\"amount\" id=\"amount\" value=\""+amount+"\" style=\"display:none\">"+amount+"</td></tr>");
				 sb.append("<tr><td>交款人</td><td><input type=\"text\" name=\"patientid\" id=\"patientid\" value=\""+patientid+"\" style=\"display:none\"><input type=\"text\" name=\"payer\" id=\"payer\" value=\""+payer+"\" style=\"display:none\">"+payer+"</td></tr>");
				 sb.append(" <tr><td>是否收讫</td><td><input type=\"text\" name=\"status\" id=\"status\" value=\"否\" style=\"display:none\">否</td></tr>");
			 }else{
				 sb.append(" <tr><td>收据编号<input type=\"text\" name=\"no\" id=\"no\" value=\""+no+"\" style=\"display:none\"></td><td><input type=\"text\" name=\"recepitId\" id=\"recepitId\" value="+receiptId+" style=\"display:none\">"+receiptId+"</td></tr>");
				 sb.append(" <tr><td>总价</td><td><input type=\"text\" name=\"amount\" id=\"amount\" value=\""+amount+"\" style=\"display:none\">"+amount+"</td></tr>");
				 sb.append("<tr><td>交款人</td><td><input type=\"text\" name=\"patientid\" id=\"patientid\" value=\""+patientid+"\" style=\"display:none\"><input type=\"text\" name=\"payer\" id=\"payer\" value=\""+payer+"\" style=\"display:none\">"+payer+"</td></tr>");
				 sb.append(" <tr><td>是否收讫</td><td><input type=\"text\" name=\"status\" id=\"status\" value=\"是\" style=\"display:none\">是</td></tr>");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	
	public boolean recepitUpdate(String recepiter,String recepitId,String no,String patientid){
		boolean result=false;
		String str="update recepit_list set receipter='"+recepiter+"' ,status='2' , patient_id= '"+patientid+"' where receipt_id='"+recepitId+"' and no='"+no+"';";
	System.out.println(str);
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
	
	public boolean recepitUpdateReturn(String recepiter,String recepitId){
		boolean result=false;
		String str="update recepit_list set receipter='"+recepiter+"' ,status='1' where receipt_id='"+recepitId+"';";
	System.out.println("recepitUpdateReturn______________"+str);
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

}
