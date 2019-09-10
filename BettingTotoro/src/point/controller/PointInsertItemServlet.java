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
 * Servlet implementation class PointInsertItem
 */
@WebServlet("/point/InsertItem")
public class PointInsertItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String itemType = request.getParameter("itemType");
		String itemImage = request.getParameter("itemImage");
		String itemName = request.getParameter("itemName");
		String content = request.getParameter("content");
		int price = Integer.parseInt(request.getParameter("price"));
		
		Point p = new Point(itemType, itemName, price, content, itemImage);
		
		int result = new PointService().insertItem(p);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/point/pointShopList";
		
		if(result>0) {
			msg = "상품 등록 성공!";
		}
		else {
			msg = "상품 등록 실패!";
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
