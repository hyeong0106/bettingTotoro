package betting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import betting.model.service.BettingService;
import team.model.vo.Team;

/**
 * Servlet implementation class BettingMatchViewServlet
 */
@WebServlet("/betting/matchView")
public class BettingMatchViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int BettingNo = Integer.parseInt(request.getParameter("BettingNo"));
		
		request.setAttribute("BettingNo", BettingNo);
		BettingService BS = new BettingService();
		String bettingType = BS.getbettingType(BettingNo);
		
		
		ArrayList<String> TeamNameList = new ArrayList<>();
		ArrayList<String> TeamNameList2 = new ArrayList<>();
		
	
		TeamNameList = BS.getSelectTeamName(BettingNo);
		TeamNameList2 = BS.getSelectTeamName2(BettingNo);
		
		ArrayList<Team> TeamList = new ArrayList<>();
		
		for(int i=0;i<TeamNameList.size();i++) {
			Team t = BS.getTeamList(TeamNameList.get(i));
			TeamList.add(t);
		}
		for(int i=0;i<TeamNameList2.size();i++) {
			Team t = BS.getTeamList(TeamNameList2.get(i));
			TeamList.add(t);
		}
		System.out.println("팀리스트 사이즈"+TeamList.size());
		if(bettingType.equals("single") || bettingType.equals("score-hit")) {
			
			
		}
		else if(bettingType.equals("double")) {
			
		}
		else if(bettingType.equals("tirple")) {
			
		}
		
		request.setAttribute("bettingType", bettingType);
		request.setAttribute("BettingNo", BettingNo);
		request.setAttribute("TeamList", TeamList);
		
		
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
