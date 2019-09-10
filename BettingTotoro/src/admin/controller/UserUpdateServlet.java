package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.service.AdminService;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/admin/userUpdate")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		String phone  = request.getParameter("phone");
		String gender = request.getParameter("gender");
		int score = Integer.parseInt(request.getParameter("score"));
		
		Member member = new AdminService().selectOne(memberId);
		member.setPassword(password);
		member.setMemberName(memberName);
		member.setEmail(email);
		member.setPhone(phone);
		member.setGender(gender);
		member.setScore(score);
		
		int result = new AdminService().updateUser(member);
		
		String msg = "";
		String loc = "/";
		if(result > 0) {
			msg = "회원정보수정성공!";
			loc = "/admin/userView?memberId="+memberId;
		}
		else {
			msg = "회원정보수정실패!";
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
