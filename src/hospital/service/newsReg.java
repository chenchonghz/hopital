package hospital.service;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class newsReg {

	/**
	 * @param args
	 */
	static Connection conn;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt2=null;
	static ResultSet rs=null;
	StringBuffer sb = new StringBuffer();
	 static java.util.Date date = new java.util.Date();
	 static SimpleDateFormat sy1=new SimpleDateFormat("yyyy-MM-dd");
	 static SimpleDateFormat sy2=new SimpleDateFormat("yyyyMMdd");
	 static String dateFormat=sy1.format(date);
	 static String dateFormat2=sy2.format(date);
	 
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public boolean newsInsert(String newsContent,String creatDate,String userId){
		boolean result=false;
		String str4="insert into news(news_comtent,creat_date,writer) values('"+newsContent+"','"+creatDate+"',(select user_name from user where user_id='"+userId+"'));";
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
	public boolean newsDel(String userId){
		boolean result=false;
		String str4="delete from news where news_id='"+userId+"';";
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
	
	
	public String newsSearch(){
		String newsComtent ="";
		String creatDate="";
		
		String str="select * from news where DATE_SUB(CURDATE(), INTERVAL 2 MONTH) <= date(creat_date);";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 creatDate=num.getString("creat_date");
				 newsComtent=num.getString("news_comtent");
				 
				sb.append("<tr><td>"+newsComtent+"</td><td>"+creatDate+"</td></tr>");
			 }
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	public String newsListSearch(){
		String newsComtent ="";
		String creatDate="";
		String newsId="";
		
		String str="select * from news where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(creat_date);";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 creatDate=num.getString("creat_date");
				 newsId=num.getString("news_id");
				 newsComtent=num.getString("news_comtent");
				 
				sb.append("<tr><td>"+newsId+"</td><td>"+newsComtent+"</td><td>"+creatDate+"</td><td><input type=\"button\" class=\"btn blue\" value=\"删除\" onclick=\"del("+newsId+")\"></td></tr>");
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
