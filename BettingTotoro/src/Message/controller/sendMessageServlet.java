package Message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Message.model.service.MessageService;
import common.Util;
import member.model.vo.Member;
/**
 * Servlet implementation class sendMessageServlet
 */
@WebServlet("/message/sendMessage")
public class sendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		Util u = new Util();
		
		String sendId = request.getParameter("sendId");
		String srchName = "";
		String messageContent="";
		///2.파라미터핸들링
		try {
			srchName = request.getParameter("srchName");
			messageContent = u.encodeContent(request.getParameter("messageContent"));			
		}catch(NullPointerException e) {
			srchName=null;
			messageContent=null;
		}

		int result=0;
		Member m=null;
		if(messageContent != null && srchName != null) {
			m = new MessageService().selectOne(srchName);
			if(m != null) {
				result = new MessageService().insertMessage(sendId,srchName,messageContent);
			}
		}
				
		//4. view단 처리
		String msg = "";
		if(result>0) {
			msg = "메세지 전송 성공!";
		}
		else {	
			msg = "메세지 전송 실패!";
				
		
			
		}

		request.setAttribute("msg",msg);
		request.setAttribute("loc","/member/Message?memberId="+sendId+"&type=MyMessage");
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