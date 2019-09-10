package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/userFinder")
public class AdminUserFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//1. 파라미터핸들링
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		int cPage =1 ;
		int numPerPage = 5;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		} catch(NumberFormatException e) {
			
		}
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch(NumberFormatException e) {
			
		}
		//2.업무로직-컨텐츠영역
		List<Member> list = null;
		int totalContents = 0;
		switch(searchType) {
		case "memberId": list = new AdminService().selectUserByMemberId(searchKeyword, cPage, numPerPage);
						 totalContents = new AdminService().selectUserByMemberIdCount(searchKeyword);
						 System.out.println("IdTotalContents="+totalContents);
						 break;
		case "memberName": list = new AdminService().selectUserByMemberName(searchKeyword, cPage, numPerPage); 
						 totalContents = new AdminService().selectUserByMemberNameCount(searchKeyword);
						 break;
		}
		
		//2.업무로직-페이지바영역
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		System.out.println("@Finer:totalPage="+totalPage);
		
		int pageBarSize = 5;
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
		int pageEnd = pageStart+pageBarSize-1;
		System.out.println("@Finder:pageStart="+pageStart+"pageEnd="+pageEnd);
		int pageNo = pageStart;
		String pageBar = "";
		
		//[이전]
		if(pageNo == 1) {
			
		}
		else {
			//pageBar += "<a href='"+request.getContextPath()+"/admin/memberFinder?cPage="+(pageNo-1)+"'>[이전]</a>";
			pageBar += "<a href='"+request.getContextPath()+"/admin/UserFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		
		//[pageNo]
		while(pageNo<=pageEnd && pageNo <= totalPage) {
			if(cPage == pageNo) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/admin/UserFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			
			pageNo++;
		}
		
		//[다음]
		if(pageNo > totalPage) {
			
		}
		else {
			//pageBar += "<a href='"+request.getContextPath()+"/admin/memberFinder?cPage="+pageNo+"'>[다음]</a>";
			pageBar += "<a href='"+request.getContextPath()+"/admin/UserFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		
		
		System.out.println("searchType="+searchType);
		System.out.println("searchKeyword="+searchKeyword);
		System.out.println("cPage="+cPage);
		
		
		
		//3.뷰단처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage",numPerPage);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/UserFinder.jsp")
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
