package hospital.service;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class reqVacate {
	StringBuffer sb = new StringBuffer();
	static Connection conn;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt2=null;
	static ResultSet rs=null;
	
	 static java.util.Date date = new java.util.Date();
	 static SimpleDateFormat sy1=new SimpleDateFormat("yyyy-MM-dd");
	 static SimpleDateFormat sy2=new SimpleDateFormat("yyyyMMdd");
	 static String dateFormat=sy1.format(date);
	 static String dateFormat2=sy2.format(date);
	 
	 
	 public boolean vacateInsert(String userId,String startday,String endday,String dateNum){
			boolean result=false;
			String str="insert into req_vatcate(user_id,req_start_date,req_end_date,date_num,status) values  ('"+userId+"', '"+startday+"', '"+endday+"', '"+dateNum+"', '1');";
			System.out.println("newsInsert_________________"+str);
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
	 
	 public boolean vacateUpdate(String id,String startday,String endday,String dateNum){
			boolean result=false;
			String str="update req_vatcate set req_start_date='"+startday+"' , req_end_date='"+endday+"' , date_num='"+dateNum+"' where id='"+id+"';";
			System.out.println("vacateUpdate_________________"+str);
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
	 
	 public boolean vacateDele(String id){
			boolean result=false;
			String str="delete from req_vatcate where id='"+id+"';";
			System.out.println("vacateDele_________________"+str);
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
	 
	 
	 public String vacateSearch(String userId){
			String id="";
			String reqStartDate="";
			String reqEndDate="";
			String dateNum="";
			String status="";
			float sum=0;
			
			String str="select * from req_vatcate where user_id='"+userId+"';";
			System.out.println("vacateSearch_________________"+str);
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				 
				ResultSet num=pstmt.executeQuery();
				 while(num.next()){
					 id=num.getString("id");
					 reqStartDate=num.getString("req_start_date");
					 reqEndDate=num.getString("req_end_date");
					 dateNum=num.getString("date_num");
					 status=num.getString("status");
					 
					 if(status.equals("1")){ 
						 sb.append("<tr> <td><a href=\"javascript:search("+id+")\">"+reqStartDate+"~"+reqEndDate+"</a></td> <td><center>"+dateNum+"</center></td> <td>审批中</td> </tr>");
					 }else if(status.equals("2")){
						 sb.append("<tr> <td>"+reqStartDate+"~"+reqEndDate+"</td> <td><center>"+dateNum+"</center></td> <td>已审批</td> </tr>");
						 sum=sum+ Float.parseFloat(dateNum) ;			 
					 }else if(status.equals("3")){
						 sb.append("<tr> <td>"+reqStartDate+"~"+reqEndDate+"</td> <td><center>"+dateNum+"</center></td> <td>拒绝申请</td> </tr>");
					 }
				}
				 sb.append("<tr> <td colspan=\"3\"> 已休假总天数："+sum+" </td> </tr>");
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return sb.toString();
		}
	 
	 
	 public String vacateSearchNow(String id){
			String reqStartDate="";
			String reqEndDate="";
			String dateNum="";
			
			String str="select * from req_vatcate where user_id='"+id+"';";
			System.out.println("vacateSearchNow_________________"+str);
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				 
				ResultSet num=pstmt.executeQuery();
				 while(num.next()){
					 reqStartDate=num.getString("req_start_date");
					 reqEndDate=num.getString("req_end_date");
					 dateNum=num.getString("date_num");
					 sb.append("<tr> <td>请假日期 <input type=\"text\" style=\"width: 170px\" name=\"startday\" id=\"startday\" style=\"border:1px; solid #999;\" onclick=\"fPopCalendar(event,this,this)\" value=\""+reqStartDate+"\" onfocus=\"this.select()\" />——");
					  sb.append("<input type=\"text\" style=\"width: 170px\" name=\"endday\" id=\"endday\" style=\"border:1px; solid #999;\" onclick=\"fPopCalendar(event,this,this)\" onfocus=\"this.select()\" value=\""+reqEndDate+"\"/></td> "); 
				if(dateNum.equals("0.5")){
					sb.append("<td><input type=\"radio\" id=\"days\" name=\"days\" checked value=\"0.5\" onclick=\"DateDiff()\">半天<input type=\"radio\" id=\"days\" name=\"days\" value=\"1\" >全天</td> ");
				}else{
					sb.append("<td><input type=\"radio\" id=\"days\" name=\"days\"  value=\"0.5\" onclick=\"DateDiff()\">半天<input type=\"radio\" checked id=\"days\" name=\"days\" value=\"1\" >全天</td> ");
				}
				sb.append("</tr> <tr> <td colspan=\"2\"><input type=\"button\" value=\"保存\" onclick=\"saveNow("+id+")\"   class=\"btn blue\"><input type=\"button\" value=\"取消\" onclick=\"deleNow("+id+")\"  class=\"btn blue\"></td> </tr> ");
				
				
				 }
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return sb.toString();
		}
	 
	 
	 public String vacateSearch(){
			String id="";
			String userName="";
			String reqStartDate="";
			String reqEndDate="";
			String dateNum="";
			
			String str="select u.user_name,rv.* from req_vatcate rv,user u where rv.user_id=u.user_id and status='1' order by req_start_date desc;";
			System.out.println("vacateSearch_________________"+str);
			try{
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);
				 
				ResultSet num=pstmt.executeQuery();
				 while(num.next()){
					 id=num.getString("id");
					 userName=num.getString("user_name");
					 reqStartDate=num.getString("req_start_date");
					 reqEndDate=num.getString("req_end_date");
					 dateNum=num.getString("date_num");
					 
					  sb.append("<tr> <td>"+userName+"</td> <td>"+reqStartDate+"——"+reqEndDate+"</td> <td><center>"+dateNum+"</center></td> <td><input type=\"button\" value=\"审批\" onclick=\"save("+id+")\"    class=\"btn blue\"> <input type=\"button\" value=\"拒绝\" onclick=\"cancle("+id+")\"    class=\"btn blue\"></td> </tr>");
				}
				 pstmt.close();
				 dbConn.close(conn);
				 
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return sb.toString();
		}
	 
	 
	 public boolean vacateSave(String id){
			boolean result=false;
			String str="update req_vatcate set status='2' where id='"+id+"';";
			System.out.println("vacateSave________________"+str);
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
	 public boolean vacateCancle(String id){
			boolean result=false;
			String str="update req_vatcate set status='3' where id='"+id+"';";
			System.out.println("vacateSave________________"+str);
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
	 
	 
	 
	 
	 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
