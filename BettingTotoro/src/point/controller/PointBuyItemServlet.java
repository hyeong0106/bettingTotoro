package point.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import point.model.service.PointService;
import point.model.vo.MemberItemList;

/**
 * Servlet implementation class PointBuyItemServlet
 */
@WebServlet("/point/pointBuyItem")
public class PointBuyItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");
		int price = Integer.parseInt(request.getParameter("price"));
		int newPrice = Integer.parseInt(request.getParameter("newPrice"));
		String itemName = request.getParameter("itemName");
		String memberId = request.getParameter("memberId");
		
		String msg = "";
		int result = 0 ;
		//1. 구매내역에 같은 사용자의 같은 아이템이 있는지 확인
		MemberItemList m = new PointService().SelectAllFromMemberItemListByItem(memberId,itemName);
		if(m.getMemberId()==null && m.getItemName()==null) {
			//1.아이템리스트에 상품추가
			result = new PointService().insertItemFromMemberItemList(memberId, itemName, price);
		}else {
			//2.아이템리스트에 수량+1 update
			int itemEA = m.getItemEA()+1;
			result = new PointService().updateItemEaFromMemberItemList(itemEA, memberId, itemName);
		}
		if(result>0) {
			result = new PointService().MinusPointFromMember(memberId, newPrice);
			if(result>0) {
				Member memberLoggedIn = new MemberService().selectOne(memberId);
				HttpSession session = request.getSession();
	            session.setAttribute("memberLoggedIn", memberLoggedIn);
				msg = "상품 구매 성공!";
			}else {
				msg = "상품 구매 실패!";
			}
		} else {
			msg = "상품 구매 실패!";
		}
	
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String loc = "/point/pointShopList?type="+type;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		if(type.equals("게임") || type.equals("포인트충전")) {
			
		}else {
			request.getRequestDispatcher(view).forward(request, response);
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
