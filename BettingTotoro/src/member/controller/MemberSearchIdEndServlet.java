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
 * Servlet implementation class MemberSearchEndServlet
 */
@WebServlet("/member/MemberSearchIdEnd")
public class MemberSearchIdEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		request.setCharacterEncoding("UTF-8");
		
		String searchname = request.getParameter("searchname");
		String searchphone =request.getParameter("searchphone");
		
		
		 System.out.println("Serviet의  searchname= "+searchname);
		  System.out.println("Serviet의 searchphone= "+searchphone);
		
		
	
	String MemberSearchid= new MemberService().MemberSearchid(searchname,searchphone);
		
		request.setAttribute("MemberSearchid", MemberSearchid);
		request.getRequestDispatcher("/WEB-INF/views/member/MemberSearchIdEnd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}