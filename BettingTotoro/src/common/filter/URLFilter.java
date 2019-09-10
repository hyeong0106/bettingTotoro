package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(servletNames = { "MemberMyinfoServlet","MemberBettingList"})
public class URLFilter implements Filter {

    /**
     * Default constructor. 
     */
    public URLFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, 
						 ServletResponse response, 
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request; 
		HttpSession session = httpRequest.getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		
		String reqMemberId = httpRequest.getParameter("memberId");
			
		if(memberLoggedIn == null ||
		   reqMemberId == null ||
		   (!"admin".equals(memberLoggedIn.getMemberId())&&
		   !reqMemberId.equals(memberLoggedIn.getMemberId()))) {
			System.out.println(memberLoggedIn.getMemberId()+"=memberLoggedIn");
			System.out.println(reqMemberId+"=reqMemberId");
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
