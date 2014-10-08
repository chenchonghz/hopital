package hospital.servlet.newsReg;

import hospital.dao.sqlMethod;
import hospital.service.newsReg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class newsRegServlet extends HttpServlet{
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
			String newsContent=request.getParameter("newsContent");
			String creatDate=request.getParameter("creatDate");
			String userId=request.getParameter("userId");
			 newsReg nr=new newsReg();
			boolean result=false;
			PrintWriter out= response.getWriter();//输出返回信息
			ServletContext sc = getServletContext(); 
			RequestDispatcher rd = null; 
			result=nr.newsInsert(newsContent, creatDate, userId);
			if(result){
				out.print("保存成功");
				rd = sc.getRequestDispatcher("/newsReg/newsList.jsp"); 
	            rd.forward(request, response);
			}else{
				out.print("保存失败");
				rd = sc.getRequestDispatcher("/newsReg/newsList.jsp"); 
	            rd.forward(request, response);
			} 
			 
	}

}