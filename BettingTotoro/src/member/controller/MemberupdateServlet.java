package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet(urlPatterns="/member/memberUpdate",
            name="memberUpdateInfo")
public class MemberupdateServlet extends HttpServlet {
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
		String tel0 = request.getParameter("tel0");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String phone = tel0+tel1+tel2;		
		String gender = request.getParameter("gender");
		
		Member member = new MemberService().selectOne(memberId);
		member.setPassword(password);
		member.setMemberName(memberName);
		member.setEmail(email);
		member.setPhone(phone);
		member.setGender(gender);
		
		int result = new MemberService().updateMember(member);
		
		String msg = "";
		String loc = "/";
		if(result > 0) {
			msg = "내정보수정성공!";
			loc = "/member/MyInfo?memberId="+memberId;
		}
		else {
			msg = "내정보수정실패!";
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