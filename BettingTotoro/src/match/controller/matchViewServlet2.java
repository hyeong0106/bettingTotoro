package match.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import match.model.service.MatchService;
import match.model.vo.Match;
import team.model.vo.Team;

/**
 * Servlet implementation class matchViewServlet2
 */
@WebServlet("/betting/matchView2")
public class matchViewServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bettingNo = Integer.parseInt(request.getParameter("bettingNo"));
		
		Match m = new MatchService().SelectMatchByBettinNo(bettingNo);
		Team Home = new MatchService().SelectTeam(m.getHomeTeamName());
		Team Away = new MatchService().SelectTeam(m.getAwayTeamName());
		request.setAttribute("m", m);
		request.setAttribute("Away",Away);
		request.setAttribute("Home",Home);
		request.getRequestDispatcher("/WEB-INF/views/match/MatchView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
