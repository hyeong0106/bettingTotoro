package common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoggerFilter
 */
@WebFilter("/index.jsp")
public class IndexFilter implements Filter {

    /**
     * Default constructor. 
     */
    public IndexFilter() {
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
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      
      List<Member> memberlistPointTop = new MemberService().selectAllMember();
      List<Member> memberlistScoreTop = new MemberService().selectScoreTop();
      List<Member> memberlistScoreWorst = new MemberService().selectScoreWorst();
      
      List<String> PointTopbadgeList = new ArrayList<>();
      List<String> ScoreWorstbadgeList = new ArrayList<>();
      List<String> ScoreTopbadgeList = new ArrayList<>();
      
      for(int i=0;i<3;i++) {
    	  PointTopbadgeList.add(new MemberService().selectBadeg(memberlistPointTop.get(i).getMemberId())); 
    	  ScoreTopbadgeList.add(new MemberService().selectBadeg(memberlistScoreTop.get(i).getMemberId()));
    	  ScoreWorstbadgeList.add(new MemberService().selectBadeg(memberlistScoreWorst.get(i).getMemberId()));
    	  
      }      
      new MemberService().resetLoginList();
      System.out.println(PointTopbadgeList.size());
      System.out.println(ScoreWorstbadgeList.size());
      System.out.println(ScoreTopbadgeList.size());
      
      request.setAttribute("PointTopbadgeList", PointTopbadgeList);
      request.setAttribute("ScoreTopbadgeList", ScoreTopbadgeList);
      request.setAttribute("ScoreWorstbadgeList", ScoreWorstbadgeList);
      request.setAttribute("memberlistPointTop", memberlistPointTop);
      request.setAttribute("memberlistScoreTop", memberlistScoreTop);
      request.setAttribute("memberlistScoreWorst", memberlistScoreWorst);
      request.getRequestDispatcher("index.jsp").forward(request, response);
      
   }

   /**
    * @see Filter#init(FilterConfig)
    */
   public void init(FilterConfig fConfig) throws ServletException {
      // TODO Auto-generated method stub
   }

}