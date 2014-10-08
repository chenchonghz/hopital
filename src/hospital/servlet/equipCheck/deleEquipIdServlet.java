package hospital.servlet.equipCheck;

import hospital.service.commonSearch.commonSearch;
import hospital.service.equipCheck.equipCheck;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class deleEquipIdServlet  extends HttpServlet{
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
		
		String equiId=request.getParameter("equipId");
		String patientId=request.getParameter("patientId");
		equipCheck equi=new equipCheck();
		
		boolean result=false;
		result=equi.deleteEquip(equiId,patientId);
		response.getWriter().print(result);
	}

}