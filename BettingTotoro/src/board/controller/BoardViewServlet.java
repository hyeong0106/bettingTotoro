package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//2. 파라미터핸들링
		int boardNo =Integer.parseInt(request.getParameter("boardNo"));
		int cPage = 1;
		int numPerPage = 5;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			
		}
		
		//3. 업무로직
		Board board = new BoardService().selectOne(boardNo);
		
		//사용자 읽음여부 쿠키검사
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false;
		String boardCookieVal = "";
		
		//사이트 첫방문시 아무런 쿠키도 없으므로, cookies가 null
		//boardCookie = |2||3||100|
		if(cookies != null) {
			for(Cookie c: cookies) {
				String name = c.getName();
				String value = c.getValue();
				
				//boardCookie인 경우
				if("boardCookie".equals(name)) {
					boardCookieVal = value;
					
					if(value.contains("|"+boardNo+"|")) {
						hasRead = true;
						break;
					}
				}
			}
		}
				
		//쿠키에 읽은 값이 없는 경우
		if(!hasRead) {
			new BoardService().increaseReadCount(boardNo);
			
			//쿠키생성
			Cookie boardCookie = new Cookie("boardCookie",boardCookieVal+"|"+boardNo+"|");
			//여기서만 사용하겠다
			boardCookie.setPath(request.getContextPath()+"/board/boardView");
			
			//응답객체 cookie 전송
			response.addCookie(boardCookie);
			
			System.out.println("boardCookie생성 : "+boardCookie.getValue());
		}
		List<BoardComment> commentList = new BoardService().selectBoardComment(boardNo,cPage, numPerPage);
		
		int totalContents = new BoardService().selectBoardCommentCount(boardNo);
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		int pageBarSize = 5;
		System.out.println("totalContents@BoardViewServlet"+totalContents);
		System.out.println("totalPage@BoardViewServlet"+totalPage);		
		
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		String pageBar = "";
		
		//[이전]
		if(pageNo == 1) {
			
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/board/boardView?boardNo="+boardNo+"&cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		
		//[pageNo]
		while(pageNo<=pageEnd && pageNo <= totalPage) {
			if(cPage == pageNo) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/board/boardView?boardNo="+boardNo+"&cPage="+(pageNo)+"'>"+pageNo+"</a>";
			}
			
			pageNo++;
		}
		
		//[다음]
		if(pageNo > totalPage) {
			
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/board/boardView?boardNo="+boardNo+"&cPage="+pageNo+"'>[다음]</a>";
		}
		
		//4. view단 처리
		int result = -100;
		request.setAttribute("result", result);
		request.setAttribute("board", board);
		request.setAttribute("commentList", commentList);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.getRequestDispatcher("/WEB-INF/views/board/BoardView.jsp")
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