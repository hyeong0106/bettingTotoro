package point.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class PointIndexServlet
 */
@WebServlet("/point/pointShopList")
public class PointIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("UTF-8");
		String type = "";
		try {
			type = request.getParameter("type");
		}catch(NullPointerException e) {
			
		} finally {
			if(type==null) {
				type="아이템";
			}
		}
		//3.업무로직
		List<Point> list = new PointService().selectAllPointShopByType(type);
		//4.view단처리
		request.setAttribute("list",list);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/WEB-INF/views/point/pointIndex.jsp")
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
