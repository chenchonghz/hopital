package hospital.servlet.equipCheck;

import hospital.service.equipCheck.equipCheck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class searchequipServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/xml;charset=UTF-8"); 
		equipCheck equi=new equipCheck();
		
	String searchText = new String(request.getParameter("seachEquip").getBytes("ISO-8859-1"),"utf-8"); 
	
	String res="";
			 res=equi.equipSearch(searchText);
	response.getWriter().print(res);
	}

}