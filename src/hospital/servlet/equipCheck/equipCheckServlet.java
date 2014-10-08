package hospital.servlet.equipCheck;

import hospital.service.equipCheck.equipCheck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class equipCheckServlet  extends HttpServlet{
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
		
		String equiId=request.getParameter("equiId");
		String id=request.getParameter("id");
			
		boolean result=false;
		 equipCheck rec=new equipCheck();
		 result=rec.equipCheckUpdate(equiId,id);
		 if(result){
			 response.sendRedirect(request.getContextPath()+"/equipCheck/equipCheckList.jsp");  
		 }
	}

}