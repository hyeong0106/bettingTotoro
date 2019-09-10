package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Board;
import member.model.service.MemberService;
import member.model.vo.Member;


/**
 * Servlet implementation class BoradListServlet
 */
@WebServlet("/board/boardList")
public class BoradListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//1. 파라미터
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
		List<Board> list = new BoardService().selectBoardList(cPage, numPerPage);
		
		//1.2 페이지바영역
	 	//totalContents: 전체컨텐츠수
		int totalContents = new BoardService().selectBoardCount();
		
		//totalPage: 전체페이지수 (공식2) : totalContent, numPerPage
		//npp = 10, tc=122 -> 13
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		System.out.println("@List:totalPage="+totalPage);
		
		//pageBarSize: 페이지바에 표시할 페이지수
		int pageBarSize = 5;
		
		//pageNo: 페이지번호
		//cPage=1, pageBarSize=5 -> 1 2 3 4 5
		//cPage=5, pageBarSize=5 -> 1 2 3 4 5
		//cPage=6, pageBarSize=5 -> 6 7 8 9 10
		//cPage=10, pageBarSize=5 -> 6 7 8 9 10
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		System.out.println("@List:pageStart="+pageStart+"pageEnd="+pageEnd);
		
		String pageBar = "";
		
		//[이전]
		if(pageNo == 1) {
			
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/board/boardList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		
		//[pageNo]
		while(pageNo<=pageEnd && pageNo <= totalPage) {
			if(cPage == pageNo) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/board/boardList?cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			
			pageNo++;
		}
		
		//[다음]
		if(pageNo > totalPage) {
			
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/board/boardList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		
		
		
		
		
		//2. view단 처리
		request.setAttribute("list",list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		
		request.getRequestDispatcher("/WEB-INF/views/board/BoardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
