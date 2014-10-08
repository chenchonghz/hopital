package hospital.servlet.disease;




import hospital.service.disease.diseaseSearch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class searchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/xml;charset=UTF-8"); 
		diseaseSearch dis=new diseaseSearch();
		
	String seachText = new String(request.getParameter("seachText").getBytes("ISO-8859-1"),"utf-8"); 
	String res="";
			 res=dis.diseaseSearch(seachText);
	response.getWriter().print(res);
	}

}
