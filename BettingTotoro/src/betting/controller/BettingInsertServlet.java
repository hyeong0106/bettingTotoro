package betting.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import betting.model.service.BettingService;
import betting.model.vo.BettingList;

/**
 * Servlet implementation class BettingInsertServlet
 */
@WebServlet("/betting/bettingadd")
public class BettingInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String admin_cometition = request.getParameter("admin_cometition");
		String gamecount = request.getParameter("gamecount");
		
		String team1 = request.getParameter("team1");
		String team2 = request.getParameter("team2");
		String game1_time = request.getParameter("game1_time");
		System.out.println(game1_time);
		
         


		String game_time1 = game1_time.substring(0, 4)+"/"+game1_time.substring(5, 7)+"/"+game1_time.substring(8,10)+
							" "+game1_time.substring(11,13)+":"+game1_time.substring(14);
		
		System.out.println(game_time1);
		
		System.out.println(team1);
		System.out.println(team2);
		String team3 = "";
		String team4 = "";
		String team5 = "";
		String team6 = "";
		System.out.println(admin_cometition);
		
		System.out.println(gamecount);
		BettingService bs = new BettingService(); 
		bs.InsertBettingList(admin_cometition,gamecount,game_time1);
		int bettingno = bs.getBettingNo();
		int numPerPage = 5;//한페이지당 수
		int cPage = 1;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
		
		}
		
		List<BettingList> BettingList = bs.ShowBettingList(cPage, numPerPage);
		int totalBettingCount = new BettingService().selectBettingCount();
		int totalPage = (int)Math.ceil((double)totalBettingCount/numPerPage);
		
		
		String pageBar = "";	
		int pageBarSize = 5;
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		int h_member_no=-10;
		try{
			h_member_no = Integer.parseInt(request.getParameter("h_member_no"));
		} catch(NumberFormatException e){
				
		}
		//[이전] section
		if(pageNo == 1 ){
			//pageBar += "<span>[이전]</span>"; 
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/betting/bettingList?cPage="+(pageNo-1)+"&h_member_no="+h_member_no+"'>[이전]</a> ";
		}
			
		// pageNo section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//				while(pageNo<=pageEnd && pageNo<=totalPage){
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/betting/bettingList?cPage="+pageNo+"&h_member_no="+h_member_no+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			//pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/betting/bettingList?cPage="+pageNo+"&h_member_no="+h_member_no+"'>[다음]</a>";
		}
		
				
		
		if(gamecount.equals("single")||gamecount.equals("score-hit")) {
			bs.InsertMatchList(team1,team2,bettingno,admin_cometition,game_time1);
		}
		else if(gamecount.equals("double")) {
			team3 = request.getParameter("team3");
			team4 = request.getParameter("team4");
			bs.InsertMatchList(team1,team2,bettingno,admin_cometition,game_time1);
			bs.InsertMatchList(team3,team4,bettingno,admin_cometition,game_time1);
			System.out.println(team3);
			System.out.println(team4);
			
		}
		else if(gamecount.equals("triple")) {
			team3 = request.getParameter("team3");
			team4 = request.getParameter("team4");
			team5 = request.getParameter("team5");
			team6 = request.getParameter("team6");
			bs.InsertMatchList(team1,team2,bettingno,admin_cometition,game_time1);
			bs.InsertMatchList(team3,team4,bettingno,admin_cometition,game_time1);
			bs.InsertMatchList(team5,team6,bettingno,admin_cometition,game_time1);
		}
	
		ArrayList<Integer> bettingListNo = new ArrayList<Integer>();
		if(h_member_no!=-10) {
			bettingListNo = new BettingService().getBettingList(h_member_no);
		}
				
		request.setAttribute("bettingListNo", bettingListNo);	
		request.setAttribute("BettingList", BettingList);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("OK","NO");
		
		request.getRequestDispatcher("/WEB-INF/views/betting/bettingList2.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}