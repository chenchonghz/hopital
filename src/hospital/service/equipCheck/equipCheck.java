package hospital.service.equipCheck;

import hospital.dao.dbConn;
import hospital.service.infoReg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class equipCheck {
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
	 static equipCheck equi=new equipCheck();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.print(equi.noSearch());
	}
	
	public String equipListSearch(){
		String id="";
		String checkDate="";
		String patientName="";
		String moneyStatus="";
		String patientId="";
		String equipName="";
		String equipId="";
		String str="select distinct e.equip_id,e.equip_name,el.id,el.check_date,p.patient_name,rl.status,p.patient_id from equipment e,equipment_list el ,patient p,recepit_list rl where el.patient_id=p.patient_id and el.id=rl.receipt_id and e.equip_id=el.equipment_id and rl.pillOrEquip=2 order by el.id desc;";
		System.out.println("equipListSearch()______+"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 id=num.getString("id");
				 checkDate=num.getString("check_date");
				 patientName=num.getString("patient_name");
				 moneyStatus=num.getString("status");
				 patientId=num.getString("patient_id");
				 equipName=num.getString("equip_name");
				 equipId=num.getString("equip_id");
				 
				 if(moneyStatus.equals("1")){
					 sb.append("<tr><td>"+id+"</td><td>"+checkDate+"</td><td><a href=\"equiCheckDetail.jsp?patientId="+patientId+"&equipId="+equipId+"\">"+patientName+"</a></td><td>否</td><td>"+equipName+"</td></tr>");
				 }else{	 
					 sb.append("<tr><td>"+id+"</td><td>"+checkDate+"</td><td><a href=\"equiCheckDetail.jsp?patientId="+patientId+"&equipId="+equipId+"\">"+patientName+"</a></td><td>是</td><td>"+equipName+"</td></tr>");
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
	
	public String equipDetailSearch(String patientId,String equipId){
		String equipName="";
		String id="";
		String equiId="";
		String checkStatus="";
		String patientName="";
		String str="select el.equipment_id,e.equip_name,el.checkStatus,p.patient_name,el.id from equipment_list el,equipment e,patient p where el.equipment_id=e.equip_id and el.patient_id=p.patient_id and el.patient_id='"+patientId+"' and el.equipment_id="+equipId+";";
		System.out.println("equipDetailSearch()______+"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 equipName=num.getString("equip_name");
				 checkStatus=num.getString("checkStatus");
				 patientName=num.getString("patient_name");
				 id=num.getString("id");
				 equiId=num.getString("equipment_id");
				 
				 if(checkStatus.equals("1")){
					 sb.append("<input type=\"text\" id=\"id\" name=\"id\" value=\""+id+"\" style=\"display:none;\">");
					 sb.append("<input type=\"text\" id=\"equiId\" name=\"equiId\" value=\""+equiId+"\" style=\"display:none;\">");
					 sb.append("<tr><td>"+equipName+"</td><td>"+patientName+"</td><td>否</td></tr>");
				 }else{	
					 sb.append("<input type=\"text\" id=\"equiId\" name=\"equiId\" value=\""+id+"\" style=\"display:none;\">");
					 sb.append("<input type=\"text\" id=\"equiId\" name=\"equiId\" value=\""+equiId+"\" style=\"display:none;\">");
					 sb.append("<tr><td>"+equipName+"</td><td>"+patientName+"</td><td>是</td></tr>");
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
	
	
	public boolean equipCheckUpdate(String equiId,String id){
		boolean res=false;
		String str="UPDATE equipment_list SET checkStatus = '2' where id='"+id+"' and equipment_id='"+equiId+"';";
		System.out.println("equipCheckUpdate()______+"+str);
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
	public boolean equipMoneyUpdate(String equiId){
		boolean res=false;
		String str="UPDATE equipment_list SET moneyStatus = '2' where id='"+equiId+"';";
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
	
	
	public String equipSearch(String searchText){
		String equipId ="";
		String equipName="";
		String checkPrice="";
		
		String str="select * from equipment where equip_name like '%"+searchText+"%';";
		System.out.println("equipSearch()______+"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 equipId=num.getString("equip_id");
				 equipName=num.getString("equip_name");
				 checkPrice=num.getString("check_price");
				 sb.append("<tr><td><input type=\"text\" id=\"equipId"+equipId+"\" value=\""+equipId+"\" style=\"display:none\"> ");
			sb.append(" <input type=\"text\" id=\"checkPrice"+equipId+"\" value=\""+checkPrice+"\" style=\"display:none\"> <input type=\"text\" id=\"equipName"+equipId+"\" value=\""+equipName+"\" readonly>");	 		
			 sb.append("</td><td> <input type=\"button\" class=\"btn blue\" value=\"添加\" onclick=\"add("+equipId+")\"></td></tr>");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
		
	}
	public boolean equipCheckInsert(String id,int price,String equipId,String docId,String patientId){
		boolean result=false;
		
		 
		 
		 
		String str4="insert into equipment_list(check_date,id,equipment_id,doc_id,checkStatus,patient_id)  values('"+dateFormat2+"','"+id+"','"+equipId+"','"+docId+"','1','"+patientId+"');";
		System.out.println("equipCheckInsert_____________"+str4);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str4);
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
	public boolean equipCheckInsert(String id,int price,String patientId){
		boolean result=false;
		
		 
		 
		 
		String str2="insert into recepit_list(receipt_id,amount,receipter,payer,pillOrEquip,status,receipt_date,patient_id) values('"+id+"','"+price+"','',(select patient_name from patient where patient_id='"+patientId+"'),2,1,'"+dateFormat2+"',"+patientId+");";
		System.out.println("equipCheckInsert_____________"+str2);
		try{
			conn=dbConn.getConn();
			pstmt2=conn.prepareStatement(str2);
			pstmt2.executeUpdate();
			result=true;
			dbConn.close(conn);
			return result;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
	}
	
	
	public String idSearch(){
		String id ="";
		
		String str="select distinct id from equipment_list where check_date='"+dateFormat2+"' order by id desc LIMIT 0, 1;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 id=num.getString("id");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return id;
		
	}
	
	 
	
	public int priceSearch(String equipId){
		int price =0;
		
		String str="select check_price from equipment where equip_id='"+equipId+"' ;";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 price=num.getInt("check_price");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return price;
		
	}
	
	public String equipfind(String patientId){
		String equipId ="";
		String equipName="";
		String checkPrice="";
		
		String str="select el.equipment_id,e.equip_name,e.check_price from equipment_list el,equipment e where el.check_date=current_date() and el.patient_id='"+patientId+"' and e.equip_id=el.equipment_id;;";
		System.out.println("equipfind()______+"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 equipId=num.getString("equipment_id");
				 equipName=num.getString("equip_name");
				 checkPrice=num.getString("check_price");
				 sb.append("<tr><td><input type=\"text\" id=\"equipId"+equipId+"\" value=\""+equipId+"\" style=\"display:none\"> ");
			sb.append(" <input type=\"text\" id=\"equipName"+equipId+"\" value=\""+equipName+"\" readonly>");	 		
			 sb.append("</td><td> <input type=\"text\" id=\"checkPrice"+equipId+"\" value=\""+checkPrice+"\" readonly> </td><td><input type=\"button\" value=\"删除\" class=\"btn blue\" onclick=\"dele("+equipId+")\"></td></tr>");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
		
	}
	
	public boolean deleteEquip(String equipId,String patientId){
		boolean res=false;
		String strd="delete from equipment_list where patient_id='"+patientId+"' and check_date=current_date() and equipment_id='"+equipId+"';";
		String str= "delete from recepit_list where receipt_id=(select el.id from equipment_list el where el.patient_id='"+patientId+"' and el.equipment_id='"+equipId+"' and el.check_date=current_date()) and pillOrEquip=2;";
		System.out.println("deleteEquip_________________________"+strd);
		System.out.println("deleteEquip_________________________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			pstmt2=conn.prepareStatement(strd);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			res=true;
			dbConn.close(conn);
			return res;
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return res;
	}

}
