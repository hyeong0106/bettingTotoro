package betting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import betting.model.service.BettingService;
import betting.model.vo.BettingResult;
import match.model.vo.Match;
import team.model.vo.Team;

/**
 * Servlet implementation class BettingResultInsertServlet
 */
@WebServlet("/betting/BettingResultInsert")
public class BettingResultInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int BettingNo = Integer.parseInt(request.getParameter("BettingNo"));
		request.setAttribute("BettingNo", BettingNo);
		
		String bettingType = new BettingService().getbettingType(BettingNo);
		ArrayList<Match> MatchList = new BettingService().getMatchList(BettingNo);
		new BettingService().updateBettingList(BettingNo);
		ArrayList<String> TeamNameList = new ArrayList<>();
		ArrayList<String> TeamNameList2 = new ArrayList<>();
	
		TeamNameList = new BettingService().getSelectTeamName(BettingNo);
		TeamNameList2 = new BettingService().getSelectTeamName2(BettingNo);
		new BettingService().InsertBettingResultList(BettingNo);
		ArrayList<Team> TeamList = new ArrayList<>();
		
		for(int i=0;i<TeamNameList.size();i++) {
			Team t = new BettingService().getTeamList(TeamNameList.get(i));
			TeamList.add(t);
		}
		for(int i=0;i<TeamNameList2.size();i++) {
			Team t = new BettingService().getTeamList(TeamNameList2.get(i));
			TeamList.add(t);
		}
		System.out.println("앙?!");
		request.setAttribute("bettingType", bettingType);
		request.setAttribute("BettingNo", BettingNo);
		request.setAttribute("TeamList", TeamList);
		request.setAttribute("MatchList", MatchList);
		request.getRequestDispatcher("/WEB-INF/views/betting/bettingResultInsert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
