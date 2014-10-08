package hospital.servlet;

import hospital.service.infoReg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class drugServlet extends HttpServlet{
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
		String pillName=request.getParameter("pillName");
		String pillNum=request.getParameter("pillNum");
		String pillId=request.getParameter("pillId");
		String standard=request.getParameter("standard");
		String price=request.getParameter("price");
		String number=request.getParameter("number");
		String pillType=request.getParameter("pillType");
		double priceo=0.00;
		int numbero=0;
		
		infoReg ptreg=new infoReg();
		boolean result=false;
		if(pillNum == null || pillNum.equals("")){
			if(price == null || price.equals("")){}
			else{
				priceo=Double.parseDouble(request.getParameter("price"));
			}
			if(number == null || number.equals("")){}
			else{
				numbero=Integer.parseInt(request.getParameter("number"));
			}
			 result=ptreg.pillInsert(pillName, standard, priceo, numbero,pillId,pillType);
				PrintWriter out=response.getWriter();
				if(result){
					 response.sendRedirect(request.getContextPath()+"/infoSearch/commonSearch/pillSearch.jsp");   
				}else{
					out.print("注册失败");
					return;
				}
		}else{
			if(price == null || price.equals("")){}
			else{
				priceo=Double.parseDouble(request.getParameter("price"));
			}
			if(number == null || number.equals("")){}
			else{
				numbero=Integer.parseInt(request.getParameter("number"));
			}
			 result=ptreg.pillUpdate(pillNum, pillName, standard, priceo, numbero,pillId,pillType);
				PrintWriter out=response.getWriter();
				if(result){
					 response.sendRedirect(request.getContextPath()+"/infoSearch/commonSearch/pillSearch.jsp");   
				}else{
					out.print("注册失败");
					return;
				}
		}
		
		
	}
}
