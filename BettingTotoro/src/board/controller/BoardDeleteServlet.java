package board.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//2. 파라미터 핸들링
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		//3. 업무로직
		int result = new BoardService().deleteBoard(boardNo);
		
		//4. view단 처리
		String msg = "";
		if(result>0) {
			msg = "게시물 삭제 성공!";
		}
		else {
			msg = "게시물 삭제 실패!";
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
