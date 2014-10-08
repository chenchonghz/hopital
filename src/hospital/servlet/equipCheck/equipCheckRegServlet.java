package hospital.servlet.equipCheck;

import hospital.service.equipCheck.equipCheck;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class equipCheckRegServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  java.util.Date date = new java.util.Date();
		   SimpleDateFormat sy=new SimpleDateFormat("yyyyMMdd");
		   String dateFormat=sy.format(date);
		request.setCharacterEncoding("utf-8");
		String	pcid=request.getParameter("pcid");
		String equiId[]=request.getParameterValues("equipId");
		String patientId[]=request.getParameterValues("patientId");
		String docId[]=request.getParameterValues("docId");
		equipCheck equi=new equipCheck();
		
		String id="";
		if(equi.idSearch()==null||equi.idSearch().equals("")){
			id=dateFormat+"1";
		}else{
			int a=Integer.parseInt(equi.idSearch())+1;
			id=a+"";
		}
			
		boolean result=false;
		boolean res1=false;
		 equipCheck rec=new equipCheck();
		 int price=0;
		 if(equiId.length==1){
			 price=rec.priceSearch(equiId[0]);
			 result=rec.equipCheckInsert(id,price,equiId[0], docId[0], patientId[0]);
		 }else if(equiId.length>1){
			 for(int i=0;i<equiId.length;i++){
				 price+=rec.priceSearch(equiId[i]);
			 }
			 for(int i=0;i<equiId.length;i++){
				 result=rec.equipCheckInsert(id,price,equiId[i], docId[0], patientId[0]);
			 }
			
		 }
		 res1=rec.equipCheckInsert(id, price,patientId[0]);
		 
		 if(result&&res1){
			 response.sendRedirect(request.getContextPath()+"/watchDisease/diseaseDetail.jsp?patientId="+request.getParameter("patientId")+"&equiId=1&id="+pcid);  
		 }
	}

}