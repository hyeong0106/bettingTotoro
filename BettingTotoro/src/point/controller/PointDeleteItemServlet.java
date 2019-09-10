package point.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import point.model.service.PointService;
import point.model.vo.Point;

/**
 * Servlet implementation class PointDeleteItemServlet
 */
@WebServlet("/point/pointDeleteItem")
public class PointDeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String itemName = (String)request.getParameter("itemName");
		
		int result = new PointService().deleteItem(itemName);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/point/pointShopList";
		
		if(result>0) {
			msg = "상품 삭제 성공!";
		}
		else {
			msg = "상품 삭제 실패!";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
