package Message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Message.model.service.MessageService;

/**
 * Servlet implementation class MessageDeleteServlet
 */
@WebServlet("/message/deleteMessage")
public class MessageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//2. 파라미터핸들링
		int messageNo = Integer.parseInt(request.getParameter("messageNo"));
		String loginId = request.getParameter("loginId");
		
		int result = new MessageService().deleteMessage(messageNo);
		
		String msg = "";
		if(result>0) {
			msg = "메세지 삭제 성공!";
		}
		else {	
			msg = "메세지 삭제 실패!";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("loc","/member/Message?memberId="+loginId+"&type=MyMessage");
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
