package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class outWardresServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("utf-8");
		boolean e=false;
		response.setContentType("text/html;charset=UTF-8"); 
		search com=new search();
		
	 
	String patientId = request.getParameter("seachText");
	String[] outWardDiagnosis = request.getParameterValues("outWardDiagnosis");
	String[] inWardStatus = request.getParameterValues("inWardStatus");
	String[] outWardStatus = request.getParameterValues("outWardStatus");
	String[] id = request.getParameterValues("id");
	String[] status = request.getParameterValues("status");
	
 for(int i=0;i<outWardDiagnosis.length;i++){
		if(status[i].equals("2")){
			e=com.outWardResInsert(patientId,outWardDiagnosis[i], inWardStatus[i], outWardStatus[i]);
		}else if(status[i].equals("1")){
			e=com.outWardResUpdate(outWardDiagnosis[i], inWardStatus[i], outWardStatus[i],id[i]);
		}
		
	}
	
	 if(e){
		 response.sendRedirect(request.getContextPath()+"/wardManagement/outWardResult.jsp");   
	}else{
		out.print("保存失败");
		return;
	}  
	 
	}

}