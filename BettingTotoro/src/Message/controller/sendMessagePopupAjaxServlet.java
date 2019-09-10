package Message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Message.model.service.MessageService;

/**
 * Servlet implementation class InformationPopupServlet
 */
@WebServlet("/message/sendMessagePopupAjax")
public class sendMessagePopupAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/csv; charset=UTF-8");
		
		String srchName = request.getParameter("srchName");
		
		List<String> nameList = null;
		String csv = "";
		
		if(!srchName.trim().isEmpty()) {
			nameList = new MessageService().selectByName(srchName);
		
			if(!nameList.isEmpty()){
				for(int i=0; i< nameList.size(); i++){
					if(i!=0) csv += ",";
					csv +=  nameList.get(i);
				}
			}
		}
		response.setContentType("text/csv; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.append(csv);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
