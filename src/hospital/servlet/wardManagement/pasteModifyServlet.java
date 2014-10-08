package hospital.servlet.wardManagement;

import hospital.service.wardManagement.search;
 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pasteModifyServlet extends HttpServlet {
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
		
		String patientStatus = request.getParameter("patientStatus");
		String doctorDiagnoseResult = request.getParameter("doctorDiagnoseResult");
		String treatment = request.getParameter("treatment");
		String outWardDiagnosis = request.getParameter("outWardDiagnosis"); 
		String outWardConditions = request.getParameter("outWardConditions"); 
		String outWardTell = request.getParameter("outWardTell");
		
		String nowDisHis = request.getParameter("nowDisHis");
		String pastDisHis = request.getParameter("pastDisHis"); 
		String bodyHis = request.getParameter("bodyHis"); 
		String famHis = request.getParameter("famHis");
		
		String T = request.getParameter("T"); 
		String P = request.getParameter("P");
		String R = request.getParameter("R");
		String BP = request.getParameter("BP");
		String generalCase = request.getParameter("generalCase"); 
		String skin = request.getParameter("skin"); 
		String lymph = request.getParameter("lymph"); 
		String head = request.getParameter("head"); 
		String neck = request.getParameter("neck"); 
		String breast = request.getParameter("breast"); 
		String lung = request.getParameter("lung"); 
		String heart = request.getParameter("heart"); 
		String stomach = request.getParameter("stomach"); 
		String anus = request.getParameter("anus");
		String spinal = request.getParameter("spinal");
		String nervous = request.getParameter("nervous");
		String patientId = request.getParameter("patientId");
		String id = request.getParameter("ig");
		boolean res=false;
		PrintWriter out=response.getWriter();
		res=com.PDInsert(id, patientId, patientStatus, doctorDiagnoseResult, treatment, outWardDiagnosis, outWardConditions, outWardTell, nowDisHis, pastDisHis, bodyHis, famHis, T, P, R, BP, generalCase, skin, lymph, head, neck, breast, lung, heart, stomach, anus, spinal, nervous);
		if(res){
			 response.sendRedirect(request.getContextPath()+"/wardManagement/diseaseRecordList.jsp?patientId="+patientId);   
		}else{
			out.print("wrong date.");
			return;
		} 
		
	}

}