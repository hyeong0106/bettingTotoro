package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import board.model.service.BoardService;

/**
 * Servlet implementation class BoardInsertEndServlet
 */
@WebServlet("/board/boardInsertEnd")
public class BoardInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		///2.파라미터핸들링
		String boardTitle = request.getParameter("boardTitle");
		String boardWriter = request.getParameter("boardWriter");
		String boardContent = request.getParameter("boardContent");
		
		int result = new BoardService().insertBoard(boardTitle,boardWriter,boardContent);
		
		//4. view단 처리
		String msg = "";
		if(result>0) {
			msg = "게시물 등록 성공!";
		}
		else {
			msg = "게시물 등록 실패!";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("loc","/board/boardList");
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request,response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
