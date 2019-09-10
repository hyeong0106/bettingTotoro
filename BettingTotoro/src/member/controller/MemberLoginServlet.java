package member.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dao.MemberDAO;
import member.model.service.MemberService;
import member.model.vo.Member;
import point.model.service.PointService;
import point.model.vo.MemberUseItem;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet(urlPatterns= {"/member/login"},
            name ="MemberLoginServlet")
public class MemberLoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.setCharacterEncoding("UTF-8");
      String id = request.getParameter("input-id");
      String password = request.getParameter("input-pwd");
    
       Member m =new Member();
       m.setMemberId(id);
       
       //암호화로 복호화
       
       
       m.setPassword(password);
       
        
       int result = new MemberService().loginCheck(m);
       
       MemberUseItem memberUseItem = new PointService().selectAllFromMemberUseItem(id);
       
       Map<String, String> headerMap = new HashMap<>();
       Enumeration<String> headerNames = request.getHeaderNames();
       while(headerNames.hasMoreElements()) {
          String name = headerNames.nextElement();
          String value = request.getHeader(name);
          headerMap.put(name, value);
       }
       String referer = request.getHeader("Referer");
       String origin = request.getHeader("Origin");
       String view = "";
       String msg = "";
       
       String loc = referer.replace(origin+request.getContextPath(), "");
       boolean AlreadyLogin = new MemberService().checkLogin(id);
       if(AlreadyLogin==false) {
	       if(result == MemberDAO.LOGIN_OK) {
		         msg = "로그인 성공";
		         view = "/";
		         System.out.println("요기기");
		         Member memberLoggedIn  = new MemberService().selectOne(id);
		         HttpSession session = request.getSession();
		         session.setAttribute("memberUseItem",memberUseItem);
		         session.setAttribute("memberLoggedIn", memberLoggedIn);
		         session.setMaxInactiveInterval(60*60);
		         response.sendRedirect(referer);
	
	       }
	       else {
		          view = "/WEB-INF/views/common/msg.jsp";
		          if(result == MemberDAO.WRONG_PASSWORD) {
		             msg = "비밀번호가 틀렸습니다.";
		          }
		          else {
		             msg ="존재하지 않는 아이디입니다.";
		          }
		          request.setAttribute("msg", msg);
		          request.setAttribute("loc", loc);
		         
		          RequestDispatcher reqDispatcher   = request.getRequestDispatcher(view);
		          reqDispatcher.forward(request, response);
	      }
      }
      else if(AlreadyLogin==true) {
    	  view = "/WEB-INF/views/common/msg.jsp";
    	  msg = "이미 접속중인 아이디입니다.";
    	  request.setAttribute("msg", msg);
          request.setAttribute("loc", loc);
          
          RequestDispatcher reqDispatcher   = request.getRequestDispatcher(view);
          reqDispatcher.forward(request, response);
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}