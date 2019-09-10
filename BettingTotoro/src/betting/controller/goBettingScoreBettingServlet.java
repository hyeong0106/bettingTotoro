package betting.controller;

import java.io.IOException;
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
 * Servlet implementation class goBettingScoreBettingServlet
 */
@WebServlet("/betting/GoScoreBetting")
public class goBettingScoreBettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int member_no = Integer.parseInt(request.getParameter("member_no"));
		
		Member memberLoggedIn = new MemberService().selectOne(member_no);
		HttpSession session = request.getSession();
        session.setAttribute("memberLoggedIn", memberLoggedIn);
		
		
		int numPerPage = 10;//한페이지당 수
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
		
		
		//[이전] section
				if(pageNo == 1 ){
					//pageBar += "<span>[이전]</span>"; 
				}
				else {
					pageBar += "<a href='"+request.getContextPath()+"/betting/bettingList?cPage="+(pageNo-1)+"'>[이전]</a> ";
				}
					
				// pageNo section
				// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//						while(pageNo<=pageEnd && pageNo<=totalPage){
				while(!(pageNo>pageEnd || pageNo > totalPage)){
					
					if(cPage == pageNo ){
						pageBar += "<span class='cPage'>"+pageNo+"</span> ";
					} 
					else {
						pageBar += "<a href='"+request.getContextPath()+"/betting/bettingList?cPage="+pageNo+"'>"+pageNo+"</a> ";
					}
					pageNo++;
				}
				
				//[다음] section
				if(pageNo > totalPage){
					//pageBar += "<span>[다음]</span>";
				} else {
					pageBar += "<a href='"+request.getContextPath()+"/betting/bettingList?cPage="+pageNo+"'>[다음]</a>";
				}
				
		
		
		request.setAttribute("BettingList", BettingList);
		request.setAttribute("pageBar",pageBar);	
		
		request.getRequestDispatcher("/WEB-INF/views/betting/bettingList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
