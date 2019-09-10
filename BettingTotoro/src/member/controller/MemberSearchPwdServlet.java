package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.RandomPassword;

/**
 * Servlet implementation class MemberSearchPwdServlet
 */
@WebServlet("/member/MemberSearchPwd")
public class MemberSearchPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String changedpassword = new RandomPassword().randomPassword(10);
	   request.setAttribute("changedpassword", changedpassword);
	   
	
		request.getRequestDispatcher("/WEB-INF/views/member/MemberSearchPwd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}