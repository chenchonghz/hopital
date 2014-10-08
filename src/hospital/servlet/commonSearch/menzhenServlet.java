package hospital.servlet.commonSearch;

import hospital.service.commonSearch.commonSearch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class menzhenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/xml;charset=UTF-8"); 
		commonSearch com=new commonSearch();
		
	String sinceDate =  request.getParameter("sinceDate") ; 
	String endDate =  request.getParameter("endDate") ; 
			String res=com.menzhenSearch(sinceDate,endDate);
			response.getWriter().print(res);
		 
	}

}