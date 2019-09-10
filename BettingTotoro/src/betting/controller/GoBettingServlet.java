package betting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import betting.model.service.BettingService;
import betting.model.vo.BettingList;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class GoBettingServlet
 */
@WebServlet("/betting/gobetting")
public class GoBettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		
		String CheckMatchResult1 = "";
		String CheckMatchResult2 = "";
		String CheckMatchResult3 = "";
		
		int choice = 0;
		int BettingNo = Integer.parseInt(request.getParameter("BettingNo"));
		
		String bettingType = request.getParameter("bettingType");
		
		int money = Integer.parseInt(request.getParameter("money"));
		int member_no = Integer.parseInt(request.getParameter("member_No"));
		if(bettingType.equals("single")) {
			CheckMatchResult1 = request.getParameter("CheckMatchResult1");
			if(CheckMatchResult1.equals("Homewin")) choice += 100;
			else if(CheckMatchResult1.equals("Draw")) choice += 300;
			else if(CheckMatchResult1.equals("HomeLose")) choice += 200;
		}else if(bettingType.equals("double")){
			CheckMatchResult1 = request.getParameter("CheckMatchResult1");
			if(CheckMatchResult1.equals("Homewin")) choice += 100;
			else if(CheckMatchResult1.equals("Draw")) choice += 300;
			else if(CheckMatchResult1.equals("HomeLose")) choice += 200;
			CheckMatchResult2 = request.getParameter("CheckMatchResult2");
			if(CheckMatchResult2.equals("Homewin")) choice += 10;
			else if(CheckMatchResult2.equals("Draw")) choice += 30;
			else if(CheckMatchResult2.equals("HomeLose")) choice += 20;
		}else if(bettingType.equals("triple")) {
			CheckMatchResult1 = request.getParameter("CheckMatchResult1");
			if(CheckMatchResult1.equals("Homewin")) choice += 100;
			else if(CheckMatchResult1.equals("Draw")) choice += 300;
			else if(CheckMatchResult1.equals("HomeLose")) choice += 200;
			CheckMatchResult2 = request.getParameter("CheckMatchResult2");
			if(CheckMatchResult2.equals("Homewin")) choice += 10;
			else if(CheckMatchResult2.equals("Draw")) choice += 30;
			else if(CheckMatchResult2.equals("HomeLose")) choice += 20;
			CheckMatchResult3 = request.getParameter("CheckMatchResult3");
			if(CheckMatchResult3.equals("Homewin")) choice += 1 ;
			else if(CheckMatchResult3.equals("Draw")) choice += 3;
			else if(CheckMatchResult3.equals("HomeLose")) choice += 2;
		}
		
		new BettingService().BettingOrder(BettingNo,member_no,money,choice);
		
		new BettingService().BettingMoneyInsert(BettingNo,money);
		int result = new MemberService().UpdateMemberMoney(member_no,money,money);
		
		Member memberLoggedIn = new MemberService().selectOne(member_no);
		HttpSession session = request.getSession();
        session.setAttribute("memberLoggedIn", memberLoggedIn);
		
		
		int numPerPage = 5;//한페이지당 수
		int cPage = 1;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
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
		
		
		ArrayList<Integer> bettingListNo = new ArrayList<Integer>();
		if(h_member_no!=-10) {
			bettingListNo = new BettingService().getBettingList(h_member_no);
		}
	
		
		String msg = "";
		String loc = "";
		if(result > 0) {
			msg = "배팅 하기 성공!";
			System.out.println(memberLoggedIn.getMemberId());
			
		}
		else {
			msg = "배팅 하기 실패!";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/member/memberBettingList?memberId="+memberLoggedIn.getMemberId());
		//4.view단처리
	
		request.setAttribute("bettingListNo", bettingListNo);
		request.setAttribute("BettingList", BettingList);
		request.setAttribute("pageBar",pageBar);	
		request.setAttribute("OK","OK");
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}