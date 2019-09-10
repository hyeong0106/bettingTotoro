package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import point.model.service.PointService;
import point.model.vo.MemberUseItem;

/**
 * Servlet implementation class MemberPointRefreshServlet
 */
@WebServlet("/member/PointRefresh")
public class MemberPointRefreshServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("memberId");
		String referer = request.getHeader("Referer");
	    String origin = request.getHeader("Origin");
	    String loc = referer.replace(origin+request.getContextPath(), "");
		Member memberLoggedIn  = new MemberService().selectOne(id);
		MemberUseItem memberUseItem = new PointService().selectAllFromMemberUseItem(id);
		HttpSession session = request.getSession();
        session.setAttribute("memberUseItem",memberUseItem);
        session.setAttribute("memberLoggedIn", memberLoggedIn);
        session.setMaxInactiveInterval(60*60);
        response.sendRedirect(referer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
