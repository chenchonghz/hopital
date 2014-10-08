package hospital.servlet.commonSearch;

import hospital.service.commonSearch.commonSearch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class searchEmpServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/xml;charset=UTF-8"); 
		commonSearch com=new commonSearch();
		
	String seachText = new String(request.getParameter("seachText").getBytes("ISO-8859-1"),"utf-8"); 
		 if(seachText!=null){
			String res=com.userSearch(seachText);
			response.getWriter().print(res);
		}
	}

}
