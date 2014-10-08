package hospital.servlet.wardManagement;

import hospital.service.commonSearch.commonSearch;
import hospital.service.wardManagement.search;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ELSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/xml;charset=UTF-8"); 
		search com=new search();
		
	String startDay =  request.getParameter("startDay") ; 
	String endDay =  request.getParameter("endDay") ; 
	String patientId =  request.getParameter("patientId") ; 
		  
			String res=com.ELSearch(patientId, startDay, endDay);
			response.getWriter().print(res);
		 
	}

}