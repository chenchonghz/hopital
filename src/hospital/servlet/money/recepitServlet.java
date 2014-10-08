package hospital.servlet.money;

import hospital.service.money.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recepitServlet extends HttpServlet{
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
		String recepiter=request.getParameter("recepiter");
		String patientid=request.getParameter("patientid");
		String no=request.getParameter("no");	
		boolean result=false;
		 recepit rec=new recepit();
		 result=rec.recepitUpdate(recepiter, recepitId,no,patientid);
		 if(result){
			 response.sendRedirect(request.getContextPath()+"/money/recepitList.jsp");  
		 }
	}

}