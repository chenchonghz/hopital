package hospital.servlet.pill;

import hospital.service.infoReg;
import hospital.service.pill.pillCaseSearch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pillSaveServlet extends HttpServlet{
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
		request.setCharacterEncoding("utf-8");
		boolean re1=false;
		boolean re2=false;
		pillCaseSearch pcs=new pillCaseSearch();
	 
		String id=request.getParameter("id");//201304041
		String payer=request.getParameter("payer");//王浩
		String[] pillid=request.getParameterValues("pillid");//10001
		
		int pillamount[]=new int[pillid.length];
		int pillnum[]=new int[pillid.length];
		 for(int i=0;i<pillid.length;i++){
			 pillamount[i]=Integer.parseInt(request.getParameterValues("pillAmount")[i]);//2
			 pillnum[i]=Integer.parseInt(request.getParameterValues("pillNum")[i]);//2000
			 
			 pillnum[i]= pillnum[i]-pillamount[i];
			 re1=pcs.pillAmountUpdate(pillid[i], pillnum[i]);
		}
		
		
		
		
		
		
		re2=pcs.payerUpdate(id, payer);
		
		if(re1&&re2){
			 response.sendRedirect(request.getContextPath()+"/pill/pillPay.jsp"); 
		}else{
			out.print("提交失败");
			return;
		} 
	}

}