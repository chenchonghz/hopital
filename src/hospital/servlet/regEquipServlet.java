package hospital.servlet;

import hospital.service.infoReg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class regEquipServlet extends HttpServlet{
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
		String equipName=request.getParameter("equipName");
		String equipId=request.getParameter("equipId");
		String buyate=request.getParameter("buyate");
		String checkedate=request.getParameter("checkedate");
		String price=request.getParameter("price");
		String checkPrice=request.getParameter("checkPrice");
		String number=request.getParameter("number");
		String checkCycle=request.getParameter("checkCycle");
		
		int checkCycleo=0;
		
		double priceo=0.00;
		double checkPriceo=0.00;//Double.parseDouble(request.getParameter("checkPrice"));
		int numbero=0;//Integer.parseInt(request.getParameter("number"));
		
		infoReg ptreg=new infoReg();
		boolean result=false;
		if(equipId.equals("")||equipId==null){
			if(price == null || price.equals("")){}
			else{
				priceo=Double.parseDouble(request.getParameter("price"));
			}
			
			if(checkPrice == null || checkPrice.equals("")){}
			else{
				checkPriceo=Double.parseDouble(request.getParameter("checkPrice"));
			}
			if(number == null || number.equals("")){}
			else{
				numbero=Integer.parseInt(request.getParameter("number"));
			}
			if(checkCycle == null || checkCycle.equals("")){}
			else{
				checkCycleo=Integer.parseInt(request.getParameter("checkCycle"));
			}
			if(buyate == null || buyate.equals("")){
				buyate="1950-01-01";
			}
			if(checkedate == null || checkedate.equals("")){
				checkedate="1950-01-01";
			}
			
			 result=ptreg.equiInsert(equipName,priceo,checkPriceo,numbero,buyate,checkedate,checkCycleo);
				PrintWriter out=response.getWriter();
				if(result){
					 response.sendRedirect(request.getContextPath()+"/infoSearch/commonSearch/equipSearch.jsp");   
				}else{
					out.print("注册失败");
					return;
				}
		}else{
			if(price == null || price.equals("")){}
			else{
				priceo=Double.parseDouble(request.getParameter("price"));
			}
			
			if(checkPrice == null || checkPrice.equals("")){}
			else{
				checkPriceo=Double.parseDouble(request.getParameter("checkPrice"));
			}
			if(number == null || number.equals("")){}
			else{
				numbero=Integer.parseInt(request.getParameter("number"));
			}
			if(checkCycle == null || checkCycle.equals("")){}
			else{
				checkCycleo=Integer.parseInt(request.getParameter("checkCycle"));
			}
			if(buyate == null || buyate.equals("")){
				buyate="1950-01-01";
			}
			if(checkedate == null || checkedate.equals("")){
				checkedate="1950-01-01";
			}
			System.out.println("checkPriceo______"+checkPriceo+"priceo_______"+priceo);
			 result=ptreg.equiUpdate(equipId,equipName,priceo,checkPriceo,numbero,buyate,checkedate,checkCycleo);
			PrintWriter out=response.getWriter();
			if(result){
				 response.sendRedirect(request.getContextPath()+"/infoSearch/commonSearch/equipSearch.jsp");   
			}else{
				out.print("注册失败");
				return;
			}
		}
		
	}

}