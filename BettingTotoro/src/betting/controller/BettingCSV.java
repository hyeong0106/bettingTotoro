package betting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import betting.model.service.BettingService;

/**
 * Servlet implementation class BettingCSV
 */
@WebServlet("/betting/csv")
public class BettingCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/csv; charset=UTF-8");
		
		String type = request.getParameter("type");
		System.out.println(type);
		
		List<String> teamList = new BettingService().BringTeam(type);
		String csv = "";
		for(int i=0; i<teamList.size(); i++) {
			if(i!=0) csv += "§";
			csv += teamList.get(i);
		}
		
		System.out.println(csv);
		
		response.setContentType("text/csv; charset=UTF-8");
		response.getWriter().append(csv);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
