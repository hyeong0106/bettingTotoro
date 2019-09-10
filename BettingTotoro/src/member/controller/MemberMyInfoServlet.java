package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import point.model.service.PointService;
import point.model.vo.MemberItemList;
import point.model.vo.Point;

/**
 * Servlet implementation class MemberMyInfoServlet
 */
@WebServlet(urlPatterns= {"/member/MyInfo"},
		name= "MemberMyinfoServlet")
public class MemberMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String type = "";
		try {
			type = request.getParameter("type");
		}catch(NullPointerException e) {
			
		} finally {
			if(type==null) {
				type="MyInfo";
			}
		}
		
		//member정보 가져오기
		Member member = new MemberService().selectOne(memberId);
		//member의 아이템 리스트 가져오기
		List<MemberItemList> itemList = new PointService().SelectAllFromMemberItemListByMemberId(memberId);
		List<Point> list = new PointService().selectAllPointShop();
		request.setAttribute("member", member);
		request.setAttribute("itemList", itemList);
		request.setAttribute("list",list);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/WEB-INF/views/member/MemberMyInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
