package point.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import point.model.dao.PointDAO;
import point.model.vo.MemberItemList;
import point.model.vo.MemberUseItem;
import point.model.vo.Point;

public class PointService {

	public int insertItem(Point p) {
		Connection conn = getConnection();
		int result = new PointDAO().insertItem(conn,p);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Point> selectAllPointShopByType(String type) {
		Connection conn = getConnection();
		List<Point> list = new PointDAO().selectAllPointShopByType(conn, type);
		close(conn);
		return list;
	}

	public Point selectOne(String itemName) {
		Connection conn = getConnection();
		Point p = new PointDAO().selectOne(conn, itemName);
		close(conn);
		return p;
	}

	public int deleteItem(String itemName) {
		Connection conn = getConnection();
		int result = new PointDAO().deleteItem(conn,itemName);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int MinusPointFromMember(String memberId, int newPrice) {
		Connection conn = getConnection();
		int result = new PointDAO().MinusPointFromMember(conn,memberId, newPrice);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public MemberItemList SelectAllFromMemberItemListByItem(String memberId, String itemName) {
		Connection conn = getConnection();
		MemberItemList m = new PointDAO().SelectAllFromMemberItemListByItem(conn,memberId,itemName);
		close(conn);
		return m;
	}

	public int insertItemFromMemberItemList(String memberId, String itemName, int price) {
		Connection conn = getConnection();
		int result = new PointDAO().insertItemFromMemberItemList(conn,memberId, itemName, price);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateItemEaFromMemberItemList(int itemEA, String memberId, String itemName) {
		Connection conn = getConnection();
		int result = new PointDAO().updateItemEaFromMemberItemList(conn,itemEA,memberId, itemName);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<MemberItemList> SelectAllFromMemberItemListByMemberId(String memberId) {
		Connection conn = getConnection();
		List<MemberItemList> itemList = new PointDAO().SelectAllFromMemberItemListByMemberId(conn, memberId);
		close(conn);
		return itemList;
	}

	public List<Point> selectAllPointShop() {
		Connection conn = getConnection();
		List<Point> list = new PointDAO().selectAllPointShop(conn);
		close(conn);
		return list;
	}

	public int deleteFromMemberItemList(String memberId, String itemName) {
		Connection conn = getConnection();
		int result = new PointDAO().deleteFromMemberItemList(conn,memberId, itemName);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateFromMemberItemList(String memberId, String itemName) {
		Connection conn = getConnection();
		int result = new PointDAO().updateFromMemberItemList(conn,memberId, itemName);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public MemberUseItem selectAllFromMemberUseItem(String memberId) {
		Connection conn = getConnection();
		MemberUseItem useItemList = new PointDAO().selectAllFromMemberUseItem(conn, memberId);
		close(conn);
		return useItemList;
	}

	public int insertBackgroundFromMemberUseItem(String memberId, String itemName) {
		Connection conn = getConnection();
		int result = new PointDAO().insertBackgroundFromMemberUseItem(conn,memberId, itemName);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertBadgeFromMemberUseItem(String memberId, String itemName) {
		Connection conn = getConnection();
		int result = new PointDAO().insertBadgeFromMemberUseItem(conn,memberId, itemName);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateBackgroundFromMemberUseItem(String memberId, String itemName) {
		Connection conn = getConnection();
		int result = new PointDAO().updateBackgroundFromMemberUseItem(conn,memberId, itemName);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateBadgeFromMemberUseItem(String memberId, String itemName) {
		Connection conn = getConnection();
		int result = new PointDAO().updateBadgeFromMemberUseItem(conn,memberId, itemName);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}


	public String GetDate(String memberId) {
		Connection conn = getConnection();
		String date = new PointDAO().GetDate(conn,memberId);
		close(conn);
		return date;
	}

	public void InsertFreeChargeList(String memberId) {
		Connection conn = getConnection();
		int result = new PointDAO().InsertFreeChargeList(conn,memberId);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		
		
	}

	public void PlusUpdatePoint(int point, String memberId) {
		Connection conn = getConnection();
		int result = new PointDAO().PlusUpdatePoint(conn,point,memberId);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
	}

	public void UpdateFreeChargeList(String memberId) {
		Connection conn = getConnection();
		int result = new PointDAO().UpdateFreeChargeList(conn,memberId);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
	}

	public int ResetScore(String memberId) {
		Connection conn = getConnection();
		int result = new PointDAO().ResetScore(conn,memberId);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int ApplyBettingMoney(String memberId) {
		Connection conn = getConnection();
		int result = new PointDAO().ApplyBettingMoney(conn,memberId);
		if(result > 0) {
			commit(conn);
		}	
		else
			rollback(conn);
		close(conn);
		return result;
	}


}
