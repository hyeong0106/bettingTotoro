package betting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import betting.model.service.BettingService;
import match.model.vo.Match;
import team.model.vo.Team;

/**
 * Servlet implementation class BettingViewServlet2
 */
@WebServlet("/betting/BettingView2")
public class BettingViewServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int BettingNo = Integer.parseInt(request.getParameter("BettingNo"));
		request.setAttribute("BettingNo", BettingNo);
		System.out.println("스코어맞추기 서블릿이다.");
		String bettingType = new BettingService().getbettingType(BettingNo);
		ArrayList<Match> MatchList = new BettingService().getMatchList(BettingNo);
		
		ArrayList<String> HomeNameList = new ArrayList<>();
		ArrayList<String> AwayNameList = new ArrayList<>();
	
		HomeNameList = new BettingService().getSelectTeamName(BettingNo);
		AwayNameList = new BettingService().getSelectTeamName2(BettingNo);
		
		ArrayList<Team> TeamList = new ArrayList<>();
		
		for(int i=0;i<HomeNameList.size();i++) {
			Team t = new BettingService().getTeamList(HomeNameList.get(i));
			TeamList.add(t);
		}
		for(int i=0;i<AwayNameList.size();i++) {
			Team t = new BettingService().getTeamList(AwayNameList.get(i));
			TeamList.add(t);
		}
		
		request.setAttribute("BettingNo", BettingNo);
		request.setAttribute("TeamList", TeamList);
		request.setAttribute("MatchList", MatchList);
		request.getRequestDispatcher("/WEB-INF/views/betting/bettingView2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
