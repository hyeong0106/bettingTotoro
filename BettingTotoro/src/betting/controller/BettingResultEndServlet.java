package betting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import betting.model.service.BettingService;
import betting.model.vo.BettingList;
import betting.model.vo.BettingResult;
import member.model.service.MemberService;
import member.model.vo.Member;
import team.service.TeamService;

/**
 * Servlet implementation class BettingResultEndServlet
 */
@WebServlet("/betting/bettingResultEnd")
public class BettingResultEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type_of_sports = request.getParameter("type_of_sports");
		int betting_no = Integer.parseInt(request.getParameter("betting_no"));
		int match_no1 =0;
		int match_no2 =0;
		int match_no3 =0;
		System.out.println(type_of_sports);
		int Homescore1=0;
		int Awayscore1=0;
		int Homescore2=0;
		int Awayscore2=0;
		int Homescore3=0;
		int Awayscore3=0;
		
		int bettingResult=0;
		String HomeTeamName="";
		String AwayTeamName="";
		String HomeTeamName2="";
		String AwayTeamName2="";
		String HomeTeamName3="";
		String AwayTeamName3="";
		if(type_of_sports.equals("single")||type_of_sports.equals("score-hit")) {
			match_no1 = Integer.parseInt(request.getParameter("match_no1"));
			Homescore1 = Integer.parseInt(request.getParameter("Homescore1"));
			Awayscore1 = Integer.parseInt(request.getParameter("Awayscore1"));
			HomeTeamName = new BettingService().selectHomeTeamName(match_no1);
			AwayTeamName = new BettingService().selectAwayTeamName(match_no1);
			
			new BettingService().InsertMatchResult(match_no1,Homescore1,Awayscore1);
			if(type_of_sports.equals("score-hit")) {
				bettingResult += Homescore1*100;
				bettingResult += Awayscore1*1;
			}
			else {
				if(Homescore1==Awayscore1) {
					bettingResult+=300;
				}else if(Homescore1>Awayscore1) {
					bettingResult+=100;
				}else {
					bettingResult+=200;
				}
			}
			if(Homescore1==Awayscore1) {
				new BettingService().updateDrawScore(HomeTeamName);
				new BettingService().updateDrawScore(AwayTeamName);
			}else if(Homescore1>Awayscore1) {
				new BettingService().updateWinScore(HomeTeamName);
				new BettingService().updateLoseScore(AwayTeamName);
			}else {
				new BettingService().updateWinScore(AwayTeamName);
				new BettingService().updateLoseScore(HomeTeamName);
			}
		} 
		else if(type_of_sports.equals("double")) {
			match_no1 = Integer.parseInt(request.getParameter("match_no1"));
			match_no2 = Integer.parseInt(request.getParameter("match_no2"));
			Homescore1 = Integer.parseInt(request.getParameter("Homescore1"));
			Awayscore1 = Integer.parseInt(request.getParameter("Awayscore1"));
			Homescore2 = Integer.parseInt(request.getParameter("Homescore2"));
			Awayscore2 = Integer.parseInt(request.getParameter("Awayscore2"));
			
			HomeTeamName = new BettingService().selectHomeTeamName(match_no1);
			AwayTeamName = new BettingService().selectAwayTeamName(match_no1);
			HomeTeamName2 = new BettingService().selectHomeTeamName(match_no2);
			AwayTeamName2 = new BettingService().selectAwayTeamName(match_no2);
			
			new BettingService().InsertMatchResult(match_no1,Homescore1,Awayscore1);
			if(Homescore1==Awayscore1) {
				new BettingService().updateDrawScore(HomeTeamName);
				new BettingService().updateDrawScore(AwayTeamName);
				bettingResult+=300;
			}else if(Homescore1>Awayscore1) {
				new BettingService().updateWinScore(HomeTeamName);
				new BettingService().updateLoseScore(AwayTeamName);
				bettingResult+=100;
			}else {
				new BettingService().updateWinScore(AwayTeamName);
				new BettingService().updateLoseScore(HomeTeamName);
				bettingResult+=200;
			}
			new BettingService().InsertMatchResult(match_no2,Homescore2,Awayscore2);
			if(Homescore2==Awayscore2) {
				new BettingService().updateDrawScore(HomeTeamName2);
				new BettingService().updateDrawScore(AwayTeamName2);
				bettingResult+=30;
			}else if(Homescore2>Awayscore2) {
				new BettingService().updateWinScore(HomeTeamName2);
				new BettingService().updateLoseScore(AwayTeamName2);
				bettingResult+=10;
			}else {
				new BettingService().updateWinScore(AwayTeamName2);
				new BettingService().updateLoseScore(HomeTeamName2);
				bettingResult+=20;
			}
			System.out.println(Homescore1);
			System.out.println(Awayscore1);
			System.out.println(Homescore2);
			System.out.println(Awayscore2);
		}
		else if(type_of_sports.equals("triple")) {
			match_no1 = Integer.parseInt(request.getParameter("match_no1"));
			match_no2 = Integer.parseInt(request.getParameter("match_no2"));
			match_no3 = Integer.parseInt(request.getParameter("match_no3"));
			Homescore1 = Integer.parseInt(request.getParameter("Homescore1"));
			Awayscore1 = Integer.parseInt(request.getParameter("Awayscore1"));
			Homescore2 = Integer.parseInt(request.getParameter("Homescore2"));
			Awayscore2 = Integer.parseInt(request.getParameter("Awayscore2"));
			Homescore3 = Integer.parseInt(request.getParameter("Homescore3"));
			Awayscore3 = Integer.parseInt(request.getParameter("Awayscore3"));
			
			HomeTeamName = new BettingService().selectHomeTeamName(match_no1);
			AwayTeamName = new BettingService().selectAwayTeamName(match_no1);
			HomeTeamName2 = new BettingService().selectHomeTeamName(match_no2);
			AwayTeamName2 = new BettingService().selectAwayTeamName(match_no2);
			HomeTeamName3 = new BettingService().selectHomeTeamName(match_no3);
			AwayTeamName3 = new BettingService().selectAwayTeamName(match_no3);
			
			new BettingService().InsertMatchResult(match_no1,Homescore1,Awayscore1);
			if(Homescore1==Awayscore1) {
				new BettingService().updateDrawScore(HomeTeamName);
				new BettingService().updateDrawScore(AwayTeamName);
				bettingResult+=300;
			}else if(Homescore1>Awayscore1) {
				new BettingService().updateWinScore(HomeTeamName);
				new BettingService().updateLoseScore(AwayTeamName);
				bettingResult+=100;
			}else {
				new BettingService().updateWinScore(AwayTeamName);
				new BettingService().updateLoseScore(HomeTeamName);
				bettingResult+=200;
			}
			new BettingService().InsertMatchResult(match_no2,Homescore2,Awayscore2);
			if(Homescore2==Awayscore2) {
				new BettingService().updateDrawScore(HomeTeamName2);
				new BettingService().updateDrawScore(AwayTeamName2);
				bettingResult+=30;
			}else if(Homescore2>Awayscore2) {
				new BettingService().updateWinScore(HomeTeamName2);
				new BettingService().updateLoseScore(AwayTeamName2);
				bettingResult+=10;
			}else {
				new BettingService().updateWinScore(AwayTeamName2);
				new BettingService().updateLoseScore(HomeTeamName2);
				bettingResult+=20;
			}
			new BettingService().InsertMatchResult(match_no3,Homescore3,Awayscore3);
			if(Homescore3==Awayscore3) {
				new BettingService().updateDrawScore(HomeTeamName3);
				new BettingService().updateDrawScore(AwayTeamName3);
				bettingResult+=3;
			}else if(Homescore3>Awayscore3) {
				new BettingService().updateWinScore(HomeTeamName3);
				new BettingService().updateLoseScore(AwayTeamName3);
				bettingResult+=1;
			}else {
				new BettingService().updateWinScore(AwayTeamName3);
				new BettingService().updateLoseScore(HomeTeamName3);
				bettingResult+=2;
			}
			
		}
		new BettingService().InsertBettingResult(betting_no,bettingResult);
		ArrayList<Integer> MemberNoList = new BettingService().WhoGiveBettingMoney(betting_no,bettingResult);
		ArrayList<Integer> MoneyList = new BettingService().HowMuchBettingMoney(betting_no,bettingResult);
		int totalMoney = new BettingService().getTotalBettingMoney(betting_no);
		System.out.println("배팅총액  :  "+ totalMoney);
		int rightMoney = 0;
		double DividendRate = 0.0;
		if(MoneyList.size()>0) {
			for(int i =0; i<MoneyList.size();i++) {
				rightMoney += MoneyList.get(i);
			}
		}
		
		System.out.println("정배 배팅액  :  "+rightMoney );
		if(totalMoney!=0 && MoneyList.size()!=0) {
			DividendRate = (totalMoney/rightMoney)-0.05;
		}
		
		System.out.println("배당률  :  "+DividendRate );
		for(int i=0;i<MemberNoList.size();i++) {
			System.out.println(totalMoney);
			System.out.println(rightMoney);
			System.out.println("회원번호 = "+MemberNoList.get(i));
			System.out.println("배팅액 = "+MoneyList.get(i));
			System.out.println("배당률 = "+DividendRate);
			System.out.println("배당금 = "+(int)MoneyList.get(i)*DividendRate);
			new MemberService().giveBettingMoney(MemberNoList.get(i),(int)(MoneyList.get(i)*DividendRate),(int)(MoneyList.get(i)*DividendRate));
			System.out.println("회원번호 배당금 : "+MemberNoList.get(i)+" "+(int)(MoneyList.get(i)*DividendRate));
		}
		
		ArrayList<Integer> MemberFalseList = new BettingService().WhoLoseBettingMoney(betting_no,bettingResult);
		for(int i=0;i<MemberNoList.size();i++) {
			new BettingService().updateBettingOrderRight(betting_no,MemberNoList.get(i));
			new BettingService().updateBettingOrderMoney(betting_no,MemberNoList.get(i),(int)(MoneyList.get(i)*DividendRate));
			
		}
		for(int i=0;i<MemberFalseList.size();i++) {
			new BettingService().updateBettingOrderFalse(betting_no,MemberFalseList.get(i));
		}
		
		
		
		int numPerPage = 5;//한페이지당 수
		int cPage = 1;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
		
		}
		int h_member_no=-10;
		try{
			h_member_no = Integer.parseInt(request.getParameter("h_member_no"));
		} catch(NumberFormatException e){
				
		}
		List<BettingList> BettingList = new BettingService().ShowBettingList(cPage, numPerPage); 
		System.out.println("list="+BettingList);
		
		
		int totalBettingCount = new BettingService().selectBettingCount();
		int totalPage = (int)Math.ceil((double)totalBettingCount/numPerPage);
		System.out.println("totalBettingCount="+totalBettingCount+", totalPage="+totalPage);
		
		
		String pageBar = "";	
		int pageBarSize = 5;
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		
		//[이전] section
				if(pageNo == 1 ){
					//pageBar += "<span>[이전]</span>"; 
				}
				else {
					pageBar += "<a href='"+request.getContextPath()+"/betting/bettingList?cPage="+(pageNo-1)+"&h_member_no="+h_member_no+"'>[이전]</a> ";
				}
					
				// pageNo section
				// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//						while(pageNo<=pageEnd && pageNo<=totalPage){
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
				
		
		ArrayList<Integer> bettingListNo = new ArrayList<Integer>();
		if(h_member_no!=-10) {
			bettingListNo = new BettingService().getBettingList(h_member_no);
		}
		
		request.setAttribute("bettingListNo", bettingListNo);		
		request.setAttribute("BettingList", BettingList);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("OK","OK");
		
		RequestDispatcher reqDispatcher	= request.getRequestDispatcher("/WEB-INF/views/betting/bettingList2.jsp");
		reqDispatcher.forward(request, response);
		
		
	}

}