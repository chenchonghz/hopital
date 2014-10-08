package hospital.servlet.diseaseSearch;

import hospital.service.diseaseSearch.diseaseSearch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class disCaseSearchServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/xml;charset=UTF-8"); 
			
			String startDay=request.getParameter("startDay");
			String endDay=request.getParameter("endDay");
			String pname=new String(request.getParameter("pname").getBytes("ISO-8859-1"),"utf-8"); 
			String docname= new String(request.getParameter("docname").getBytes("ISO-8859-1"),"utf-8"); 
			
			 diseaseSearch ds=new diseaseSearch();
			response.getWriter().print(ds.disListSearch(startDay, endDay, pname, docname));
	}

}