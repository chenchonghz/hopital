package hospital.servlet.wardManagement;

import hospital.service.commonSearch.commonSearch;
import hospital.service.wardManagement.search;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class searchPDServlet extends HttpServlet {
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
		
	String seachText =  request.getParameter("seachText") ; 
		 if(seachText!=null){
			String res=com.patientDetailSearch(seachText);
			response.getWriter().print(res);
		}
	}

}