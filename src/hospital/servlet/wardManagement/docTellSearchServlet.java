package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class docTellSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=UTF-8"); 
		search com=new search();
		  
		String startDay=request.getParameter("startDay");
		 String endDay=request.getParameter("endDay");
		 String patientName = new String(request.getParameter("patientName").getBytes("ISO-8859-1"),"utf-8"); 
		  
			String res=com.docTellSearch(startDay,endDay,patientName);
			response.getWriter().print(res);
		 
	}

}