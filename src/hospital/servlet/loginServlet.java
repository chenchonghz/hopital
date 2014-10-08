package hospital.servlet;

import hospital.dao.sqlMethod;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginServlet extends HttpServlet{
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
			response.setContentType("text/html;charset=UTF-8"); 
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			  //System.out.println(password);*/
			sqlMethod sm=new sqlMethod();
			int num=sm.selectAuthority(username, password);
			String name=sm.selectUser(username, password);
			String departId=sm.selectDepartId(username);
			HttpSession session=request.getSession(true);
			
			if(num==0){
				PrintWriter out=response.getWriter();
				out.print("<html>");
				out.print("<head>");
				out.print("</head>");
				out.print("<body style=\"background: #E6EAE9; text-align:center; font-size:25px; height:400px; width:1000px; margin:100px 0 0 0;\">");
				out.print("账号或密码错误，请重新输入<br>");
				out.print("<a href=\"login.html\">返回首页</a>");
				out.print("</body>");
				out.print("</html>");
				 
			}else{
				ServletContext sc = getServletContext();   
	            RequestDispatcher rd = null; 
	            session.setMaxInactiveInterval(43200);//璁剧疆session鐢熷懡鍛ㄦ湡涓�2灏忔椂
	            session.setAttribute("username", name);
				session.setAttribute("authority", num);
				session.setAttribute("departId", departId);
				session.setAttribute("userId", username);
	            rd = sc.getRequestDispatcher("/all.jsp"); //锟斤拷锟斤拷锟揭筹拷锟�  
	            rd.forward(request, response);
			}
			 
	}

}