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
 * Servlet implementation class ItemStartPopupServlet
 */
@WebServlet("/point/ItemStartPopup")
public class ItemStartPopupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");

		String itemName = (String)request.getParameter("itemName");
		String memberId = (String)request.getParameter("memberId");
		
		Point point = new PointService().selectOne(itemName);

		request.setAttribute("point", point);
		request.setAttribute("memberId", memberId);
		
		
		if(itemName.equals("룰렛")) {
			request.getRequestDispatcher("/WEB-INF/views/point/roulettePopup.jsp")
				   .forward(request, response);
		}
		else if(itemName.equals("가위바위보")) {
			request.getRequestDispatcher("/WEB-INF/views/point/rockScissorsPaperPopup.jsp")
				   .forward(request, response);
		}
		else if(itemName.equals("무료포인트충전")) {
			String date = "";
			date = new PointService().GetDate(memberId);
			
			if(date.equals("빔")) {
				new PointService().InsertFreeChargeList(memberId);
			}
			System.out.println("데이트는 몇인가?"+date);
			
			request.setAttribute("date", date);
			request.getRequestDispatcher("/WEB-INF/views/point/freeChargingStationPopup.jsp")
				   .forward(request, response);
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
