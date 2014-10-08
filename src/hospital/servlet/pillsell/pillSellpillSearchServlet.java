package hospital.servlet.pillsell;

import hospital.service.pillSell;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pillSellpillSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/xml;charset=UTF-8"); 
		pillSell ps=new pillSell();
		
	String seachText = new String(request.getParameter("seachText").getBytes("ISO-8859-1"),"utf-8"); 
		 if(seachText!=null){
			String res=ps.pillSearch(seachText);
			response.getWriter().print(res);
		}
	}

}
