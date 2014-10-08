package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class outWardServlet extends HttpServlet {
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
		
	 
	String patientId = request.getParameter("patientId");
	String id = request.getParameter("id");
	String treatment = request.getParameter("treatment");
	String outWardDiagnosis = request.getParameter("outWardDiagnosis");
	String outWardConditions = request.getParameter("outWardConditions");
	String outWardTell = request.getParameter("outWardTell");
	if(id.equals("")){
		e=com.outWardInsert(patientId, treatment, outWardDiagnosis, outWardConditions, outWardTell);
	}else{
		e=com.outWardUpdate(patientId, treatment, outWardDiagnosis, outWardConditions, outWardTell);
		
	}
	  
	
	if(e){
		 response.sendRedirect(request.getContextPath()+"/wardManagement/outWard.jsp");   
	}else{
		out.print("保存失败");
		return;
	} 
	 
	}

}