package hospital.servlet.pill;

import hospital.service.pill.pillCaseSearch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pillNotPayServlet extends HttpServlet{
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
			
			String id=request.getParameter("id");
			
			
			 pillCaseSearch pc=new pillCaseSearch();
			response.getWriter().print(pc.pillNotPayList(id));
	}

}