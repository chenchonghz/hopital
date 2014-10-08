package hospital.servlet.pillsell;

import hospital.service.pillSell;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pillSellSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/xml;charset=UTF-8"); 
		String res="";
		pillSell ps=new pillSell();
		
	String startDay = request.getParameter("startDay");
	String endDay = request.getParameter("endDay"); 
	String pillId = request.getParameter("pillId");
	String docId = request.getParameter("docId");
	if(pillId==null){
		res=ps.sellSearch(startDay, endDay,""  ,docId);
	}else{
		res=ps.sellSearch(startDay, endDay, pillId, docId);
	}
	 response.getWriter().print(res);
		 
	}

}