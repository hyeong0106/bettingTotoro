package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.RandomPassword;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberSearchPwdEndServlet
 */
@WebServlet(urlPatterns="/member/MemberSearchPwdEnd",
            name ="MemberSearchPwdEndServlet")
public class MemberSearchPwdEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchid = request.getParameter("searchid");
	String searchname = request.getParameter("searchname");
	String searchphone = request.getParameter("searchphone");
	String answer =request.getParameter("answer");
	String changedpassword   = request.getParameter("changedpassword");
	String viewchangedpassword   = request.getParameter("viewchangedpassword");
	String msg = "";
	Member searchpwd = new Member();

	
	
	System.out.println("changedpassword="+changedpassword);
	
	searchpwd.setMemberId(searchid);
	searchpwd.setMemberName(searchname);
	searchpwd.setPhone(searchphone);
	searchpwd.setHint(answer);
	searchpwd.setPassword(changedpassword);
	
	int cnt = new MemberService().selectChangingMember(searchpwd);

	if(cnt ==0) {
		msg = "입력하신 정보에 일치하는 회원이 없습니다. 다시입력해주세요";
		request.setAttribute("msg", msg);
	}
	else {
		Member m =  new MemberService().memberChangedPwd(searchpwd);
	
		request.setAttribute("m", m);
	}
	request.setAttribute("cnt", cnt);
	request.setAttribute("viewchangedpassword", viewchangedpassword);
	
	
		

	
	request.getRequestDispatcher("/WEB-INF/views/member/MemberSearchPwdEnd.jsp").forward(request, response);
	
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}