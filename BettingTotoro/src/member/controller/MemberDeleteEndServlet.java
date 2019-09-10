package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;


/**
* Servlet implementation class MemberDeleteServlet
*/
@WebServlet("/member/memberDeleteEnd")
public class MemberDeleteEndServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        String memberId = request.getParameter("memberId");
        String content = request.getParameter("content");
        
        String msg = "";
        String loc = "/";
        int result = new MemberService().deleteMember(memberId);
        if(result > 0) {
            result = new MemberService().updateReasonFromMemberDel(memberId, content);
            if(result >0) {
                msg="탈퇴성공!";
                loc="/member/logout";
            }
            else {
                msg = "탈퇴실패!";
            }
        }
        else {
            msg = "탈퇴실패!";
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