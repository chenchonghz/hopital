package hospital.servlet.commonSearch;

import hospital.service.infoReg;
import hospital.service.commonSearch.commonSearch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class commonSearchServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
// TODO Auto-generated method stub
this.doPost(request, response);
}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String patientId=request.getParameter("patient_id");
		String userId=request.getParameter("user_id");
		String equipId=request.getParameter("equip_id");
		String pillNum=request.getParameter("pill_num");
		
		boolean result=false;
		commonSearch com=new commonSearch();
		if(patientId!=null){
			 result=com.deletePatient(patientId);
		}else if(userId!=null){
			 result=com.deleteUser(userId);
		}else if(equipId!=null){
			 result=com.deleteEquip(equipId);
		}else if(pillNum!=null){
			 result=com.deletePill(pillNum);
		}
		
		 response.getWriter().print(result);
	}
}
