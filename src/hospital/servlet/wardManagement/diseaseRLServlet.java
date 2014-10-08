package hospital.servlet.wardManagement;

 
import hospital.service.wardManagement.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class diseaseRLServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/xml;charset=UTF-8"); 
		search com=new search();
		
	String patientId =  request.getParameter("patientId") ;
	String inspectionDate[]=request.getParameterValues("inspectionDate"); 
	String inspectionTime[] =request.getParameterValues("inspectionTime"); 
	String inspectionContents[] =request.getParameterValues("inspectionContents"); 
	String ids[] =request.getParameterValues("ids"); 
	boolean res=false;
	PrintWriter out=response.getWriter();
	 for(int i=0;i<inspectionDate.length;i++){
		 if(ids == null){
			res=com.DRLInsert(patientId, inspectionDate[i], inspectionTime[i], inspectionContents[i]);
		}else{
			res=com.DRLUpdate(ids[i],inspectionDate[i], inspectionTime[i], inspectionContents[i]);
		} 
	} 
	 if(res){
		 response.sendRedirect(request.getContextPath()+"/wardManagement/diseaseRecordList.jsp?patientId="+patientId);   
	}else{
		out.print("保存失败");
		return;
	} 
	}

}