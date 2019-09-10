package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet("/admin/userView")
public class UserViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//2. 파라미터핸들링
		String memberId =request.getParameter("memberId");
		
		//3. 업무로직
		Member member = new AdminService().selectOne(memberId);
		
		//4. view단 처리
		String view = "";
		String loc = "";
		String msg = "";
		
		if(member != null) {
			view = "/WEB-INF/views/admin/UserView.jsp";
			request.setAttribute("member", member);
		}
		else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "해당 회원이 없습니다.";
			loc = "/";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
		
		request.getRequestDispatcher(view)
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
