package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class wardModifyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("utf-8");
		boolean r=false;
		boolean e=false;
		response.setContentType("text/html;charset=UTF-8"); 
		search com=new search();
		
	String inHospitalWay = request.getParameter("inHospitalWay"); 
	String inDate = request.getParameter("inDate"); 
	String inDateMin = request.getParameter("inDateMin"); 
	String outDateMin = request.getParameter("outDateMin"); 
	String inHospitalDepart = request.getParameter("inHospitalDepart"); 
	String roomId = request.getParameter("roomId"); 
	String changeDepart = request.getParameter("changeDepart"); 
	String outDate = request.getParameter("outDate"); 
	String outHospitalDepart = request.getParameter("outHospitalDepart"); 
	String drugAllergyStatus = request.getParameter("drugAllergyStatus"); 
	String drugAllergy = request.getParameter("drugAllergy"); 
	String bloodType = request.getParameter("bloodType"); 
	String patientId = request.getParameter("patientId");
	String userId = request.getParameter("userId");
	 
	 r=com.patientFind(patientId);
	if(r){
		e=com.wardUpdate(patientId, inHospitalWay, inDate, inHospitalDepart, roomId, changeDepart, outDate, outHospitalDepart, drugAllergyStatus, drugAllergy, bloodType, userId,inDateMin,outDateMin);
	}else{
		e=com.wardInsert(patientId, inHospitalWay, inDate, inHospitalDepart, roomId, changeDepart, outDate, outHospitalDepart, drugAllergyStatus, drugAllergy, bloodType, userId,inDateMin,outDateMin);
	}
	
	if(e){
		 response.sendRedirect(request.getContextPath()+"/wardManagement/patientList.jsp");   
	}else{
		out.print("注册失败");
		return;
	} 
	 
	}

}