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
 * Servlet implementation class MatchViewServlet
 */
@WebServlet(urlPatterns= {"/match/matchView"},name="MatchViewServlet")
public class MatchViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int MatchNo = Integer.parseInt(request.getParameter("MatchNo"));
		
		Match m = new MatchService().SelectMatch(MatchNo);
		Team Home = new MatchService().SelectTeam(m.getHomeTeamName());
		Team Away = new MatchService().SelectTeam(m.getAwayTeamName());
		
		int HasResult = new MatchService().selectHasResult(m.getBettingNo());
		System.out.println("HasResult"+HasResult);
		request.setAttribute("HasResult", HasResult);
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