package member.controller;

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
import point.model.vo.MemberItemList;
import point.model.vo.MemberUseItem;

/**
 * Servlet implementation class MemberItemUseServlet
 */
@WebServlet("/member/memberItemUse")
public class MemberItemUseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String itemName = request.getParameter("itemName");
		String itemtype = request.getParameter("itemtype");
		String type = request.getParameter("type");
		//멤버아이템 목록가져오기
		List<MemberItemList> itemList = new PointService().SelectAllFromMemberItemListByMemberId(memberId);
		
		int result = 0;
		String msg = "";
		//1.멤버아이템 목록에서 수량 줄이기	
		for(MemberItemList m : itemList) {
			if(m.getItemName().equals(itemName)) {
				//1)목록에 수량이 1이면 0이되므로 delete
				if(m.getItemEA()<=1) {
					result = new PointService().deleteFromMemberItemList(memberId, itemName);
				} 
				//2)목록에 수량이 1초과시 수량-1 로 update
				else {
					result = new PointService().updateFromMemberItemList(memberId, itemName);
				}
			}
		}
		if(result >0) {
			//2.멤버아이템 사용 테이블에 추가
			//멤버아이디로 사용테이블 조회
			MemberUseItem useItemList = new PointService().selectAllFromMemberUseItem(memberId);
			//1)기존에 사용한 아이템이 없으면 insert
			if(useItemList==null) {
				//배경인경우
				if(itemtype.equals("배경")) {
					result = new PointService().insertBackgroundFromMemberUseItem(memberId, itemName);
				}
				//뱃지인경우
				else {
					result = new PointService().insertBadgeFromMemberUseItem(memberId, itemName);
				}
			}
			//2)기존에 사용한 아이템이 있으면 update
			else {
				//배경인경우
				if(itemtype.equals("배경")) {
					result = new PointService().updateBackgroundFromMemberUseItem(memberId, itemName);
				}
				//뱃지인경우
				else if(itemtype.equals("뱃지")){
					result = new PointService().updateBadgeFromMemberUseItem(memberId, itemName);
				}else if(itemtype.equals("아이템")){
					if(itemName.equals("전적초기화")) {
						result = new PointService().ResetScore(memberId);
					}
					else if(itemName.equals("더블배팅")) {
						result = new PointService().ApplyBettingMoney(memberId);
					}
				}
			}
			if(result >0) {
				msg = "아이템을 성공적으로 사용했습니다.";
				HttpSession session = request.getSession();
				Member memberLoggedIn 
				= new MemberService().selectOne(memberId);
				MemberUseItem memberUseItem = new PointService().selectAllFromMemberUseItem(memberId);
				session.setAttribute("memberUseItem",memberUseItem);
				session.setAttribute("memberLoggedIn", memberLoggedIn);
				session.setMaxInactiveInterval(5*60);
				
			}else {
				msg = "오류발생(멤버 아이템 사용 테이블 수정오류)";
			}
		}else {
			msg = "오류발생(멤버 아이템 목록 수량 삭제오류)";
		}
			
		String view = "/WEB-INF/views/common/msg.jsp";
		String loc = "/member/MyInfo?memberId="+memberId+"&type="+type;
		
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
