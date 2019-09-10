package match.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import match.model.service.MatchService;
import match.model.vo.Match;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MatchListServlet
 */
@WebServlet(urlPatterns= {"/match/MatchList"}, name="MatchListServlet")
public class MatchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		
		int cPage = 1;
		int numPerPage = 5;

		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			
		}
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch(NumberFormatException e) {
			
		}
		
		//1. 업무로직: 서비스단에 회원목록정보 요청
		//1.1 컨텐츠영역
		ArrayList<Match> MatchList = new MatchService().MatchAllList(cPage, numPerPage);
		
		//1.2 페이지바영역
	 	//totalContents: 전체컨텐츠수
		int totalContents = new MatchService().selectMatchCount();
		
		//totalPage: 전체페이지수 (공식2) : totalContent, numPerPage
		//npp = 10, tc=122 -> 13
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		
		
		//pageBarSize: 페이지바에 표시할 페이지수
		int pageBarSize = 5;
		
		//pageNo: 페이지번호
		
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		
		
		String pageBar = "";
		
		//[이전]
		if(pageNo == 1) {
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/match/MatchList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		
		//[pageNo]
		while(pageNo<=pageEnd && pageNo <= totalPage) {
			if(cPage == pageNo) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/match/MatchList?cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo > totalPage) {
			
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/match/MatchList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("MatchList", MatchList);
		request.getRequestDispatcher("/WEB-INF/views/match/MatchList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
