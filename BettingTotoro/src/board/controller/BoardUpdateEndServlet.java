package board.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdateEnd")
public class BoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//1. 파라미터
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		int cPage = 1;
		int numPerPage = 5;
		
		
		//2. 업무로직
		int result = new BoardService().updateBoard(boardNo,boardTitle,boardContent);
		System.err.println("updateServlet@result="+result);
		
		
		Board board = new Board();
		board = new BoardService().selectOne(boardNo);
		System.out.println("updateServlet@Board="+board);
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
		
		request.setAttribute("result", result);
		request.setAttribute("commentList", commentList);
		request.setAttribute("board", board);
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