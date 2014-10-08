package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class docTellServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("utf-8");
		boolean r=false;
		boolean e=false;
		response.setContentType("text/html;charset=UTF-8"); 
		search com=new search();
		
	String id[] = request.getParameterValues("id");
	String docTellId[] = request.getParameterValues("docTellId"); 
	String docId[] = request.getParameterValues("docId"); 
	String docTellContent[] = request.getParameterValues("docTellContent"); 
	String excuteDate[] = request.getParameterValues("excuteDate"); 
	String excuteId[] = request.getParameterValues("excuteId"); 
	String docTellType[] = request.getParameterValues("docTellType"); 
	String stopDate[] = request.getParameterValues("stopDate"); 
	String patientId = request.getParameter("patientId");
	
	for(int i=0;i<docTellId.length;i++){
		r=com.docTellFind(id[i]);
		if(r){
			e=com.docTellUpdate(id[i], docTellContent[i], excuteDate[i], excuteId[i], docTellType[i], stopDate[i]);
			 
		}else{
			System.out.println(docTellId[i]);
			e=com.docTellInsert(id[i], docTellId[i], docId[i], docTellContent[i], excuteDate[i], excuteId[i], docTellType[i], stopDate[i], patientId);
		 
		}
	}
	 
	
	if(e){
		 response.sendRedirect(request.getContextPath()+"/wardManagement/patientList.jsp");   
	}else{
		out.print("注册失败");
		return;
	} 
	 
	}

}