package hospital.service;

import hospital.dao.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pillSell {
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
	public String pillSearch(String searchText){
		String pillNum="";
		String pillName="";
		String str=" select pill_num,pill_name from pill where pill_name like '%"+searchText+"%';";
		System.out.println("pillSearch_______"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 pillNum=num.getString("pill_num");
				 pillName=num.getString("pill_name");
				 sb.append("<input type=\"radio\" name=\"pillNum\" value=\""+pillNum+"\" />"+pillName+"");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String equipSearch(String searchText){
		String equipId="";
		String equipName="";
		String str="select equip_id,equip_name from equipment where equip_name like '%"+searchText+"%';";
		System.out.println("pillSearch_______"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 equipId=num.getString("equip_id");
				 equipName=num.getString("equip_name");
				 sb.append("<input type=\"radio\" name=\"equipId\" value=\""+equipId+"\" />"+equipName+"");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String docSearch(String searchText){
		String userId="";
		String userName="";
		String str="select user_id, user_name from user where  user_name like '%"+searchText+"%';";
		System.out.println("docSearch_________"+str);
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			sb.append("<input  type=\"text\" class=\"myInput\" id=\"docname\"> <input type=\"button\" value=\"查找医生\" class=\"btn blue\" onclick=\"searchDoc()\"> ");
			 
			 while(num.next()){
				 userId=num.getString("user_id");
				 userName=num.getString("user_name");
				 sb.append("<input type=\"radio\" name=\"userId\" value=\""+userId+"\" />"+userName+"");
			}
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String sellSearch(String startDay,String endDay,String pillNum,String userId){
		String searchPill="";
		String docId="";
		String pillAmount="";
		float price=0;
		if(!pillNum.equals("null")){
			searchPill="pl.pill_id='"+pillNum+"' and ";
		}
		
		if(!userId.equals("null")){
			docId="pl.doctor_id='"+userId+"' and";
		}
		String str="select p.pill_name,pl.all_price,pl.pill_amount,pl.price  from pill_list pl,pill p,recepit_list rl where rl.status=2 and rl.pillOrEquip=1 and rl.receipt_id=pl.id and "+searchPill+docId+"  pl.pill_id=p.pill_num and pill_date>='"+startDay+"' And  pill_date<='"+endDay+"';";
		System.out.println("sellSearch______"+str);
		float allPrice=0;
		float title=0;
		String pillName="";
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			sb.append(" <tr> <td>药品名称</td> <td>卖出金额</td><td>卖出数量</td> <td>药品单价</td> </tr>");
			 while(num.next()){
				 pillAmount=num.getString("pill_amount");
				 price=Float.parseFloat(num.getString("price"));
				 pillName=num.getString("pill_name");
				 allPrice=Float.parseFloat(num.getString("all_price"));
				 sb.append("<tr><td>"+pillName+"</td><td>"+allPrice+"元</td><td>"+pillAmount+"</td><td>"+price+"元</td> </tr>");
				 title+=allPrice;
			}
			 sb.append(" <tr> <td><h3>总价</h3></td> <td colspan=3><h3>"+title+"元</h3></td> </tr>");
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String sellESearch(String startDay,String endDay,String equipId,String userId){
		String searchPill="";
		String docId="";
		String checkDate="";
		String equipName="";
		float checkPrice=0;
		int numb=0;
		if(!equipId.equals("null")){
			searchPill="el.equipment_id='"+equipId+"'";
		}
		
		if(!userId.equals("null")){
			docId=" el.doc_id='"+userId+"' and ";
		}
		String str="select el.check_date,e.equip_name,e.check_price,COUNT(el.equipment_id) numb from equipment e,equipment_list el,recepit_list rl where rl.receipt_id=el.id and e.equip_id=el.equipment_id and el.check_date>='"+startDay+"' And  el.check_date<='"+endDay+"' and  rl.status=2 and "+docId+searchPill+" ;";
		System.out.println("sellESearch____________"+str);
		float title=0;
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			sb.append(" <tr> <td>设备名称</td><td>使用次数</td> <td>设备检查单价</td></tr>");
			 while(num.next()){
				 equipName=num.getString("equip_name");
				 checkDate=num.getString("check_date");
				 checkPrice=Float.parseFloat(num.getString("check_price"));
				 numb=Integer.parseInt(num.getString("numb"));
				 
				 sb.append("<tr><td>"+equipName+"</td><td>"+numb+"</td><td>"+checkPrice+"元</td> </tr>");
				 title+=checkPrice*numb;
			}
			 sb.append(" <tr> <td><h3>总价</h3></td> <td colspan=2><h3>"+title+"元</h3></td> </tr>");
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public String sellSearch(String startDay,String endDay,String userId){
		String docId="";
		String userName="";
		String reqStartDate="";
		String reqEndDate="";
		float dateNum=0;
		float tatle=0;
		 
		if(!userId.equals("null")){
			docId="and rv.user_id='"+userId+"'";
		}
		String str="select u.user_name,rv.* from req_vatcate rv,user u where u.user_id=rv.user_id and rv.req_start_date>'"+startDay+"' and rv.req_end_date<'"+endDay+"'"+docId+";";
		System.out.println("sellSearch______________"+str);
		sb.append("<tr> <td>员工姓名</td> <td>休假时间</td> <td>休假天数</td> </tr>");
		try{
			conn=dbConn.getConn();
			pstmt=conn.prepareStatement(str);
			 
			ResultSet num=pstmt.executeQuery();
			 while(num.next()){
				 userName=num.getString("user_name");
				 reqStartDate=num.getString("req_start_date");
				 reqEndDate=num.getString("req_end_date");
				 dateNum=Float.parseFloat(num.getString("date_num"));
				 sb.append("<tr> <td>"+userName+"</td> <td>"+reqStartDate+"~"+reqEndDate+"</td> <td>"+dateNum+"</td> </tr>");
				 tatle+=dateNum;
			}
			 sb.append("<tr> <td colspan=\"3\">总天数："+tatle+"</td> </tr>");
			 pstmt.close();
			 dbConn.close(conn);
			 
		}catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
}
