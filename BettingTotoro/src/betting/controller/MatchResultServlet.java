package betting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import betting.model.service.BettingService;
import match.controller.MatchListServlet;
import match.model.vo.Match;
import team.model.vo.Team;

/**
 * Servlet implementation class MatchResultServlet
 */
@WebServlet("/betting/matchResultView")
public class MatchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bettingNo = Integer.parseInt(request.getParameter("bettingNo"));
		
		ArrayList<Match> MatchList = new BettingService().getMatchList(bettingNo);
		ArrayList<String> HomeList = new BettingService().getSelectTeamName(bettingNo);
		ArrayList<String> AwayList = new BettingService().getSelectTeamName2(bettingNo);
		
		ArrayList<Team> TeamList = new ArrayList<Team>();
		

		for(int i=0;i<HomeList.size();i++) {
			Team t = new BettingService().getTeamList(HomeList.get(i));
			TeamList.add(t);
		}
		for(int i=0;i<AwayList.size();i++) {
			Team t = new BettingService().getTeamList(AwayList.get(i));
			TeamList.add(t);
		}
		
		request.setAttribute("MatchList", MatchList);
		request.setAttribute("TeamList",TeamList);
		request.getRequestDispatcher("/WEB-INF/views/match/MatchResultView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
