package hospital.servlet;

import hospital.dao.sqlMethod;
import hospital.service.infoReg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class menzhenSearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String startDay =  request.getParameter("startDay") ;
		String endDay =  request.getParameter("endDay") ;
		 
		String patientName =  new   String(request.getParameter("patientName").getBytes("ISO8859-1"),"utf-8")   ;
		infoReg ptreg = new infoReg();
		String result = ptreg.menzhenSearch(startDay,endDay,patientName);
		System.out.print(result);
		if(result.equals("")){
			result = "nothing";
		}
		response.getWriter().print(result);
			 
	 
	}

}