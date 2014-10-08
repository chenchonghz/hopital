package hospital.servlet;

import hospital.service.infoReg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class employeeServlet extends HttpServlet{
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
		String userName=request.getParameter("userName");
		String userId=request.getParameter("userId");
		int sex=Integer.parseInt(request.getParameter("sex"));
		String brithday=request.getParameter("brithday");
		String inday=request.getParameter("inday");
		String contactNumber=request.getParameter("contactNumber");
		String address=request.getParameter("address");
		String education=request.getParameter("education");
		int authority=Integer.parseInt(request.getParameter("authority"));
		int department=Integer.parseInt(request.getParameter("department"));
		 boolean result=false;
		 infoReg ptreg=new infoReg();
		 if(userId == null || userId.equals("")){
			 result=ptreg.employeeInsert(userName, sex, brithday, department, contactNumber, address,education,authority,inday);
				PrintWriter out=response.getWriter();
				if(result){
					 response.sendRedirect(request.getContextPath()+"/infoSearch/commonSearch/employeeSearch.jsp");   
				}else{
					response.setCharacterEncoding("utf-8");
					out.print("注册失败");
					return;
				}
		 }else{
			 String password=request.getParameter("password");
			 result=ptreg.employeeUpdate(userId,password,userName, sex, brithday, department, contactNumber, address,education,authority,inday);
				PrintWriter out=response.getWriter();
				if(result){
					 response.sendRedirect(request.getContextPath()+"/infoSearch/commonSearch/employeeSearch.jsp");   
				}else{
					out.print("注册失败");
					return;
				}
		 } 
		
	}

}