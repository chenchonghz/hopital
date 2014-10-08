package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class bodycheckServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8"); 
		search com=new search();
		boolean e=false;
	String T = request.getParameter("T"); 
	String t = request.getParameter("t");
	String P = request.getParameter("P");
	String R = request.getParameter("R");
	String BP = request.getParameter("BP");
	String generalCase = request.getParameter("generalCase"); 
	String skin = request.getParameter("skin"); 
	String lymph = request.getParameter("lymph"); 
	String head = request.getParameter("head"); 
	String neck = request.getParameter("neck"); 
	String breast = request.getParameter("breast"); 
	String lung = request.getParameter("lung"); 
	String heart = request.getParameter("heart"); 
	String stomach = request.getParameter("stomach"); 
	String anus = request.getParameter("anus");
	String spinal = request.getParameter("spinal");
	String nervous = request.getParameter("nervous");
	String patientId = request.getParameter("patientId");
	 
	
	  if(!t.equals("")){
		e=com.bodyCheckUpdate(patientId, T, P, R, BP, generalCase, skin, lymph, head, neck, breast, lung, heart, stomach, anus, spinal, nervous);
	}else{
		e=com.bodyCheckInsert(patientId, T, P, R, BP, generalCase, skin, lymph, head, neck, breast, lung, heart, stomach, anus, spinal, nervous);
	}
	
	if(e){
		 response.sendRedirect(request.getContextPath()+"/wardManagement/bodychecklist.jsp");   
	}else{
		out.print("保存失败");
		return;
	}   
	 
	}

}