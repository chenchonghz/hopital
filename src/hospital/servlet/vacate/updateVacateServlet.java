package hospital.servlet.vacate;

import hospital.service.reqVacate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateVacateServlet extends HttpServlet{
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
		String startday=request.getParameter("startday");
		String endday=request.getParameter("endday");
		String dateNum=request.getParameter("dateNum");
		
		
		reqVacate req=new reqVacate();
		boolean result=false;
		
		result=req.vacateUpdate(id, startday, endday, dateNum);
		
		response.getWriter().print(result);  
		
		
		
	}

}