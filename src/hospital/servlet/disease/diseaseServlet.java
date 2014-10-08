package hospital.servlet.disease;




import hospital.service.disease.diseaseSearch;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class diseaseServlet extends HttpServlet {
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
		boolean result=false;
		boolean result2=false;
		ServletContext sc = getServletContext(); 
		RequestDispatcher rd = null; 
		String pillNum[]=request.getParameterValues("pillNum"); 
		String pillType=request.getParameter("pillType");
		String pillName[]=request.getParameterValues("pillName"); 
		String price[]=request.getParameterValues("price"); 
		String amount[]=request.getParameterValues("amount"); 
		String doctorId=request.getParameter("doctorId");
		double totle=0;
		int q=0;
		String id="";
		String	patientId=request.getParameter("patientId");
		String	pcid=request.getParameter("pcid");
		if(pillType.equals("1")||pillType.equals("3")){
			pillType="1";
		}else if(pillType.equals("2")){
			pillType="2";
		}
		if(pillNum.length==1){
			  id=dis.findElId(patientId);
				 result=dis.pillListInsert(patientId,pillNum[0],price[0],amount[0],doctorId,pillType);
				 result2=true;
		}else if(pillNum.length>1){
			for(int i=0;i<pillNum.length;i++){
				
				 totle+=Double.parseDouble(price[i])*Double.parseDouble(amount[i]);
			}
			for(int i=0;i<pillNum.length;i++){
			
				result=dis.pillListInsert(patientId,pillNum[i],price[i],amount[i],doctorId,totle,pillType);
				  id=dis.findElId(patientId);
			} 
			result2=dis.pillListInsert(patientId,totle);
			
		}
		
		if(result&&result2){
			if(!id.equals("")){
				q=1;
			}
			   rd = sc.getRequestDispatcher("/watchDisease/diseaseDetail.jsp?patientId="+request.getParameter("patientId")+"&equiId="+q+"&pillId=1&id="+pcid); //锟斤拷锟斤拷锟揭筹拷锟�  
	            rd.forward(request, response);
		}else{
			 rd = sc.getRequestDispatcher("/watchDisease/diseaseDetail.jsp?patientId="+request.getParameter("patientId")+"&equiId="+q+"&pillId=&id="+pcid); //锟斤拷锟斤拷锟揭筹拷锟�  
	            rd.forward(request, response);
		}

	}
}
