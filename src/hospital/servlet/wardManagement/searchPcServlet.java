package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class searchPcServlet extends HttpServlet {
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
		
	String seachText = new String(request.getParameter("seachText").getBytes("ISO-8859-1"),"utf-8"); 
	System.out.println(seachText);
		 if(seachText.equals("")){
			String res=com.patientCheckSearch();
			response.getWriter().print(res);
		}else {
			String res=com.patientCheckSearch(seachText);
			response.getWriter().print(res);
		}
	 
	}

}