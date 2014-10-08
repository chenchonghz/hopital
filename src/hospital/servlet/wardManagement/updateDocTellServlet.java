package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateDocTellServlet extends HttpServlet {
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
		String docTellStatus =  request.getParameter("docTellStatus") ; 
		String id =  request.getParameter("id") ; 
		String excuteId =  request.getParameter("excuteId") ; 
		  
			boolean res=com.docTellUpdate(id,docTellStatus,excuteId);
			response.getWriter().print(res);
		 
	}

}