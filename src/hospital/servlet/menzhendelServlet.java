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

public class menzhendelServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int age = 0;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String ids =  request.getParameter("ids") ;
		 
		
		
		
		
		 
		infoReg ptreg = new infoReg();
	 
			boolean result = ptreg.menzhenDel(ids);
			if (result) {
				 
			} else {
				out.print("删除失败");
				return;
			}
	 
	}

}