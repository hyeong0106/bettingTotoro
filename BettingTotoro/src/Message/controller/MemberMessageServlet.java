package Message.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Message.model.service.MessageService;
import Message.model.vo.Message;
import member.model.service.MemberService;
import member.model.vo.Member;
import point.model.service.PointService;
import point.model.vo.MemberItemList;
import point.model.vo.Point;

/**
 * Servlet implementation class MemberMessageServlet
 */
@WebServlet("/member/Message")
public class MemberMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String type = "";
		String messageType = ""; 
		int cPage = 1;
		int numPerPage = 5;

		try {
			type = request.getParameter("type");
			messageType = request.getParameter("messageType");
		}catch(NullPointerException e) {
			
		} finally {
			if(type==null) {
				type="MyInfo";
			}
			if(messageType==null) {
				messageType="receive";
			}
		}
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			
		}
		
		//member정보 가져오기
		Member member = new MemberService().selectOne(memberId);
		
		
		List<Message> message = new MessageService().selectMessage(messageType,memberId,cPage, numPerPage);
		int totalContents = new MessageService().selectMessageCount(messageType,memberId);
		
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		int pageBarSize = 5;
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		String pageBar = "";
		
		//[이전]
		if(pageNo == 1) {
			
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/member/Message?type="+type+"&messageType="+messageType+"&memberId="+memberId+"&cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		
		//[pageNo]
		while(pageNo<=pageEnd && pageNo <= totalPage) {
			if(cPage == pageNo) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/member/Message?type="+type+"&messageType="+messageType+"&memberId="+memberId+"&cPage="+(pageNo)+"'>"+pageNo+"</a>";
			}
			
			pageNo++;
		}
		
		//[다음]
		if(pageNo > totalPage) {
			
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/member/Message?type="+type+"&messageType="+messageType+"&memberId="+memberId+"&cPage="+(pageNo)+"'>[다음]</a>";
		}
				
				
				
		request.setAttribute("member", member);
		request.setAttribute("type", type);
		request.setAttribute("messageType", messageType);
		request.setAttribute("message", message);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		
		request.getRequestDispatcher("/WEB-INF/views/member/MemberMyInfo.jsp")
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
