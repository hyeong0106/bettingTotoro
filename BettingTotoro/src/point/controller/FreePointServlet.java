package point.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import point.model.service.PointService;
import point.model.vo.Point;

/**
* Servlet implementation class FreePointServlet
*/
@WebServlet("/point/giveFreePoint")
public class FreePointServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int point = Integer.parseInt(request.getParameter("point"));
        String memberId = request.getParameter("memberId");
        if(point!=0) {
            new PointService().PlusUpdatePoint(point,memberId);
            new PointService().UpdateFreeChargeList(memberId);
            Member memberLoggedIn = new MemberService().selectOne(memberId);
            HttpSession session = request.getSession();
           session.setAttribute("memberLoggedIn", memberLoggedIn);
        }
        String msg = "";

        if(point!= 0) {
            msg = "포인트 충전 성공!";
        }
        else {
            msg = "포인트 충전 실패";
        }
        
        request.setAttribute("msg", msg);
        System.out.println(msg);
        request.setAttribute("loc", "/index.jsp");

        
        List<Member> memberlistPointTop = new MemberService().selectAllMember();
        List<Member> memberlistScoreTop = new MemberService().selectScoreTop();
        List<Member> memberlistScoreWorst = new MemberService().selectScoreWorst();
        request.setAttribute("memberlistPointTop", memberlistPointTop);
        request.setAttribute("memberlistScoreTop", memberlistScoreTop);
        request.setAttribute("memberlistScoreWorst", memberlistScoreWorst);
        request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
           .forward(request, response);

        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    
    }

}