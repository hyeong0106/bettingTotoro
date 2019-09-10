package Message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Message.model.service.MessageService;
import common.Util;

/**
 * Servlet implementation class replyMessageServlet
 */
@WebServlet("/message/sendReplyMessage")
public class replyMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("들어는와?");
		//1. 인코딩
		Util u = new Util();
		
		request.setCharacterEncoding("UTF-8");
		String sendId = request.getParameter("sendId");
		String receiveId = request.getParameter("receiveId");
		String messageContent = u.encodeContent(request.getParameter("messageContent"));


		
		int result = new MessageService().insertReplyMessage(sendId,receiveId,messageContent);
		
				
		//4. view단 처리
		String msg = "";
		if(result>0) {
			msg = "답장 전송 성공!";
		}
		else {	
			msg = "답장 전송 실패!";
				
		
			
		}

		request.setAttribute("msg",msg);
		request.setAttribute("loc","/member/Message?memberId="+sendId+"&type=MyMessage&messageType=send");
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