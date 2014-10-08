package hospital.servlet.disease;




import hospital.service.disease.diseaseSearch;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class patientCaseReServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=UTF-8"); 
		diseaseSearch dis=new diseaseSearch();
		boolean result=false;
		ServletContext sc = getServletContext(); 
		RequestDispatcher rd = null; 
		String	bydoctorId=request.getParameter("bydoctorId");
		String patientId=request.getParameter("patientId"); 
		String id=request.getParameter("id"); 
		String BP=request.getParameter("BP"); 
		String doctorDiagnoseResult = new String(request.getParameter("doctorDiagnoseResult").getBytes("ISO-8859-1"),"utf-8");
		String patientStatus = new String(request.getParameter("patientStatus").getBytes("ISO-8859-1"),"utf-8");
		String nowDisHis = new String(request.getParameter("nowDisHis").getBytes("ISO-8859-1"),"utf-8");
		String patientTell = new String(request.getParameter("patientTell").getBytes("ISO-8859-1"),"utf-8");
		String pastDisHis = new String(request.getParameter("pastDisHis").getBytes("ISO-8859-1"),"utf-8");
		String bodyHis = new String(request.getParameter("bodyHis").getBytes("ISO-8859-1"),"utf-8");
		String famHis = new String(request.getParameter("famHis").getBytes("ISO-8859-1"),"utf-8");
		String	medicineUseMethod=request.getParameter("medicineUseMethod");
		String	diagnoseDate=request.getParameter("diagnoseDate");
		String	reviewDate=request.getParameter("reviewDate");
		String	dealStatus=request.getParameter("dealStatus");
		
		result=dis.patientCaseInsert(patientId, patientStatus, doctorDiagnoseResult, medicineUseMethod, diagnoseDate, reviewDate, bydoctorId, nowDisHis, pastDisHis, bodyHis, famHis,patientTell,BP,dealStatus);
			if(result){
				rd = sc.getRequestDispatcher("/watchDisease/diseaseRegList.jsp"); //锟斤拷锟斤拷锟揭筹拷锟�  
	            rd.forward(request, response);
			} 
	}
}
