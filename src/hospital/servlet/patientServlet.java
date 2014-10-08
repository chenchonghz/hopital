package hospital.servlet;


import hospital.service.infoReg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class patientServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int age=0;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8"); 
		String patientName=request.getParameter("patientName");
		String patientId=request.getParameter("patientId");
		int sex=Integer.parseInt(request.getParameter("sex"));
		if(request.getParameter("age")!=""||request.getParameter("age")!=null){
			 age=Integer.parseInt(request.getParameter("age"));
		}
		
		String identityNumber=request.getParameter("identityNumber");
		String contactNumber=request.getParameter("contactNumber");
		String regDepartment=request.getParameter("regDepartment");
		String marriage=request.getParameter("marriage");
		String job=request.getParameter("job");
		String diseaseOffer=request.getParameter("diseaseOffer");
		String trust=request.getParameter("trust");
		String brithday=request.getParameter("brithday");
		String national=request.getParameter("national");
		String nowAddress=request.getParameter("nowAddress");
		String nowPostCode=request.getParameter("nowPostCode");
		String huAddress=request.getParameter("huAddress");
		String huPostCode=request.getParameter("huPostCode");
		String jobAddress=request.getParameter("jobAddress");
		String jobNumber=request.getParameter("jobNumber");
		String contactName=request.getParameter("contactName");
		String relationship=request.getParameter("relationship");
		String relationNumber=request.getParameter("relationNumber");
		String relationAddress=request.getParameter("relationAddress");
		
		
		
		
		infoReg ptreg=new infoReg();
		if(request.getParameter("patientId").equals("")||request.getParameter("patientId")==null){
			boolean result=ptreg.patientInsert(patientName, sex, age, identityNumber, contactNumber, regDepartment,job,brithday,national,nowAddress,nowPostCode,huAddress,huPostCode,jobAddress,jobNumber,contactName,relationship,relationNumber,relationAddress,marriage,diseaseOffer,trust);
			if(result){
				 response.sendRedirect(request.getContextPath()+"/infoSearch/commonSearch/patientSearch.jsp");   
			}else{
				out.print("注册失败");
				return;
			}
		}else{
			boolean result1=ptreg.patientUpdate(patientId,patientName, sex, age, identityNumber, contactNumber, regDepartment,job,brithday,national,nowAddress,nowPostCode,huAddress,huPostCode,jobAddress,jobNumber,contactName,relationship,relationNumber,relationAddress,marriage,diseaseOffer,trust);
			if(result1){
				 response.sendRedirect(request.getContextPath()+"/infoSearch/commonSearch/patientSearch.jsp");   
			}else{
				out.print("注册失败");
				return;
			}
		}		
	}

}