package Message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Message.model.service.MessageService;
import Message.model.vo.Message;

/**
 * Servlet implementation class MessageViewServlet
 */
@WebServlet("/message/messageView")
public class MessageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		//2. 파라미터핸들링
		String messageType = request.getParameter("messageType");
		int messageNo = Integer.parseInt(request.getParameter("messageNo"));
		String memberId = request.getParameter("memberId");
		
		Message message = new MessageService().selectMessageOne(messageNo);
		
		request.setAttribute("message", message);
		request.setAttribute("messageType", messageType);
		request.setAttribute("memberId", memberId);
		request.getRequestDispatcher("/WEB-INF/views/member/MessageView.jsp")
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
