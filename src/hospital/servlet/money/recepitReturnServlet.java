package hospital.servlet.money;

import hospital.service.money.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recepitReturnServlet extends HttpServlet{
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
		
		String recepitId=request.getParameter("recepitId");
		String recepiter=new String(request.getParameter("recepiter").getBytes("ISO-8859-1"),"utf-8");	
		boolean result=false;
		 recepit rec=new recepit();
		 result=rec.recepitUpdateReturn(recepiter, recepitId);
		 response.getWriter().print(result);
		  
	}

}