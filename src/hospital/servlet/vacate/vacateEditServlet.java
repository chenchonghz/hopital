package hospital.servlet.vacate;

import hospital.service.reqVacate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class vacateEditServlet extends HttpServlet{
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
		
		String saveId=request.getParameter("saveId");
		String cancleId=request.getParameter("cancleId");
		
		reqVacate req=new reqVacate();
		boolean result=false;
		
		if(saveId==null&&cancleId!=null){
			result=req.vacateCancle(cancleId);
		}else if(saveId!=null&&cancleId==null){
			result=req.vacateSave(saveId);
		}
		
		
		response.getWriter().print(result);  
		
		
		
	}

}