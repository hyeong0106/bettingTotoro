package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/admin/userDelete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		int result = new AdminService().deleteUser(memberId);
		
		String msg = "";
		String loc = "/";
		if(result > 0) {
			msg = "회원삭제성공!";
			loc = "/admin/userList";
		}
		else {
			msg = "회원삭제실패!";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		//4. view단 처리
		String view ="/WEB-INF/views/common/msg.jsp";
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
