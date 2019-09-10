package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import betting.model.service.BettingService;
import betting.model.vo.BettingOrder;
import betting.model.vo.BettingResult;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberBettingListServelt
 */
@WebServlet(urlPatterns= {"/member/memberBettingList"},name="MemberBettingList")
public class MemberBettingListServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int numPerPage = 5;//한페이지당 수
		int cPage = 1;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
		
		}
		
		String memberId = request.getParameter("memberId");
		
		int memberno = new MemberService().getMemberNo(memberId);
		ArrayList<BettingOrder> BOlist = new MemberService().getBettingListByMemberNo(memberno,cPage, numPerPage);
		
		int totalBettingCount = new MemberService().selectMemberBettingCount(memberno);
		int totalPage = (int)Math.ceil((double)totalBettingCount/numPerPage);
		
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
			pageBar += "<a href='"+request.getContextPath()+"/member/memberBettingList?memberId=\"+memberId+\"&cPage="+(pageNo-1)+"'>[이전]</a> ";
		}
			
		// pageNo section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//				while(pageNo<=pageEnd && pageNo<=totalPage){
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/member/memberBettingList?memberId="+memberId+"&cPage="+pageNo+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			//pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/member/memberBettingList?memberId=\"+memberId+\"&cPage="+pageNo+"'>[다음]</a>";
		}
		

		
		request.setAttribute("BOlist", BOlist);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("memberId", memberId);
		request.getRequestDispatcher("/WEB-INF/views/member/MemberBettingList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
