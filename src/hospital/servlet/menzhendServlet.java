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

public class menzhendServlet extends HttpServlet {
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
		 
		String pname = new   String(request.getParameter("pname").getBytes("ISO8859-1"),"utf-8")   ;
		int sex = Integer.parseInt(request.getParameter("sex"));
		 age = Integer.parseInt(request.getParameter("age"));
		String pnum =  request.getParameter("pnum") ;
		String watchDate =  request.getParameter("watchDate") ;
		 
		String address = new   String(request.getParameter("address").getBytes("ISO8859-1"),"utf-8")   ;
		String BP =  request.getParameter("BP"); 
		String shoutizz = new   String(request.getParameter("shoutizz").getBytes("ISO8859-1"),"utf-8")   ; 
		String chubuzd = new   String(request.getParameter("chubuzd").getBytes("ISO8859-1"),"utf-8")   ; 
		String zhiliaoyy = new   String(request.getParameter("zhiliaoyy").getBytes("ISO8859-1"),"utf-8")   ; 
		String chuzhiqk = new   String(request.getParameter("chuzhiqk").getBytes("ISO8859-1"),"utf-8")   ;
		String fuzhendate =  request.getParameter("fuzhendate") ; 
		String doctor = new   String(request.getParameter("doctor").getBytes("ISO8859-1"),"utf-8")   ;
		
		 
		infoReg ptreg = new infoReg();
	 
			boolean result = ptreg.menzhenInsert(pname, sex, age,
					address, pnum,watchDate, BP,
					shoutizz, chubuzd, zhiliaoyy, chuzhiqk, fuzhendate,
					doctor);
			if (result) {/*
				response.sendRedirect(request.getContextPath()
						+ "/menzhenSearch.jsp");*/
			} else {
				out.print("注册失败");
				return;
			}
	 
	}

}