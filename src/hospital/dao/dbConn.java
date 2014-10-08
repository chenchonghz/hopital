package hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dbConn {
	private   static String dbpro="DBConfig";
	private    static String Driver="";
	private   static String URL="";
	private   static String USER="";
	private   static String PASSWORD="";
	static {
		ResourceBundle rs=ResourceBundle.getBundle(dbpro);
		Driver=rs.getString("Driver");
		URL=rs.getString("URL");
		USER=rs.getString("USER");
		PASSWORD=rs.getString("PASSWORD"); 
	}
	public static Connection getConn(){
		Connection conn=null;
		try {
			Class.forName(Driver);
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
			
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return conn;	
	}
	public static void close(Connection con){
		try{
			con.close();
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
