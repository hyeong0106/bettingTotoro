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
 * Servlet implementation class MemberEnrollEndServlet
 */
@WebServlet(urlPatterns= {"/member/memberEnrollEnd"},
             name ="MemberEnrollEndServlet")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId_ = request.getParameter("memberId_");
		String password = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String hint = request.getParameter("answer");
		String tel0 = request.getParameter("tel0");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		
		String tel = tel0+tel1+tel2;		
		Member m = new Member();
		m.setMemberId(memberId_);
		System.out.println("loginEnd회원가입"+password);
		new MemberService().insertMemberItems(m);
		m.setPassword(password);
		m.setMemberName(memberName);
		m.setHint(hint);
		m.setPhone(tel);
		m.setGender(gender);
		m.setEmail(email);
		System.out.println(hint);
		
		int result = new MemberService().insertMember(m);
		
		String msg = "";
		String view="";
		if(result > 0) {
			msg = "회원가입성공!";
			view = "/WEB-INF/views/member/MemberEnrollmsg.jsp";
		}
		else {
			msg = "회원가입실패!";
			 view = "/WEB-INF/views/common/msg.jsp";
			request.setAttribute("loc", "/");
			request.setAttribute("msg", msg);
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("memberId_", memberId_);
		
		//4.view단처리
		
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