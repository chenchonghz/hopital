package hospital.servlet.newsReg;

import hospital.service.newsReg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class newsDelServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 锟斤拷锟斤拷request锟斤拷锟诫，锟斤拷要锟斤拷为锟剿达拷锟斤拷锟斤拷通锟斤拷锟斤拷锟斤拷械锟斤拷锟斤拷锟斤拷锟斤拷锟�
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/xml;charset=UTF-8"); 
			String newsId=request.getParameter("newsId");
			 newsReg nr=new newsReg();
			boolean result=false;
			ServletContext sc = getServletContext(); 
			RequestDispatcher rd = null; 
			result=nr.newsDel(newsId);
			if(result){
				rd = sc.getRequestDispatcher("/newsReg/newsList.jsp"); 
	            rd.forward(request, response);
			} 
			 
	}

}