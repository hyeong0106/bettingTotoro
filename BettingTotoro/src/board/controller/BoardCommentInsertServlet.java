package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BoardComment;
import common.Util;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/boardCommentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//1.파라미터핸들링
		Util u =  new Util();
		
		
		int boardRef = Integer.parseInt(request.getParameter("boardRef"));
		String boardCommentWriter = request.getParameter("boardCommentWriter");
		String boardCommentContent = u.encodeContent(request.getParameter("boardCommentContent"));
		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
		
		BoardComment bc = new BoardComment(0, boardCommentLevel, boardCommentWriter, boardCommentContent, boardRef, boardCommentRef, null);
		System.out.println("boardComment@servlet="+bc);
		
		//2.업무로직
		int result = new BoardService().insertBoardComment(bc);
		
		//3.view단처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/boardView?boardNo="+boardRef;
		
		if(result>0) {
			msg = "댓글 등록 성공!";
		}
		else {
			msg = "댓글 등록 실패!";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
