package hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sqlMethod sd=new sqlMethod();
		System.out.print(sd.selectUser("123453","123456"));
	}
	
	
	static Connection conn;
	static PreparedStatement pstmt=null;
	static ResultSet rs=null;
	
	//�����û�Ȩ��
	public int selectAuthority(String username,String password){
		String str="SELECT t.authority FROM `user` t where t.user_id='"+username+"' and t.`password`='"+password+"'";
		int number = 0;
		try{
			
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);

			ResultSet nu=pstmt.executeQuery(str);
			while(nu.next()){
				 number = nu.getInt("authority");
			} 
			dbConn.close(conn);
		 
			
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
		
	}
	//�����û�����
		public String selectUser(String username,String password){
			String str="SELECT t.user_name FROM `user` t where t.user_id='"+username+"' and t.`password`='"+password+"'";
			String name="";
			try{
				
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);

				ResultSet nu=pstmt.executeQuery(str);
				while(nu.next()){
					name = nu.getString("user_name");
				} 
				dbConn.close(conn);
			 
				
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return name;
			
		}
		
		public String selectDepartId(String username){
			String str="SELECT t.depart_id FROM `user` t where t.user_id='"+username+"'";
			String departId = "";
			try{
				
				conn=dbConn.getConn();
				pstmt=conn.prepareStatement(str);

				ResultSet nu=pstmt.executeQuery(str);
				while(nu.next()){
					departId = nu.getString("depart_id");
				} 
				dbConn.close(conn);
			 
				
			}catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return departId;
			
		}
		
}
