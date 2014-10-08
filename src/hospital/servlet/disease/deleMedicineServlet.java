package hospital.servlet.disease;




import hospital.service.disease.diseaseSearch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class deleMedicineServlet extends HttpServlet {
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
		
	String pillId = request.getParameter("pillId"); 
	String patientId = request.getParameter("patientId"); 
	boolean res=false;
			 res=dis.pillCaseDel(patientId, pillId);
	response.getWriter().print(res);
	}

}
