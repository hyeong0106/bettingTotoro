package point.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.vo.Member;
import point.model.vo.MemberItemList;
import point.model.vo.MemberUseItem;
import point.model.vo.Point;

public class PointDAO {
	
private Properties prop = new Properties();
	
	public PointDAO() {
		try {
            String fileName = PointDAO.class.getResource("/sql/point/point-query.properties").getPath();
            prop.load(new FileReader(fileName));
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	public int insertItem(Connection conn, Point p) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertItem");

        
        try {
        	
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getItemType());
			pstmt.setString(2, p.getItemName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getContent());
			pstmt.setString(5, p.getItemImage());
			
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<Point> selectAllPointShopByType(Connection conn, String type) {
		List<Point> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectAllPointShopByType");
        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, type);
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	Point p = new Point();
                p.setContent(rset.getString("content"));
                p.setItemImage(rset.getString("item_image"));
                p.setItemName(rset.getString("item_name"));
                p.setItemType(rset.getString("item_type"));
                p.setPrice(rset.getInt("price"));

                list.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return list;
	}


	public Point selectOne(Connection conn, String itemName) {
		Point p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, itemName);
			
			//쿼리실행
			rset = pstmt.executeQuery();
			
			//rset의 정보를 member 객체에 옮겨담기
			if(rset.next()) {
				p = new Point();
				p.setContent(rset.getString("content"));
                p.setItemImage(rset.getString("item_image"));
                p.setItemName(rset.getString("item_name"));
                p.setItemType(rset.getString("item_type"));
                p.setPrice(rset.getInt("price"));
			}
			System.out.println("member@dao="+p);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return p;
	}


	public int deleteItem(Connection conn, String itemName) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteItem");

        
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, itemName);
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int MinusPointFromMember(Connection conn, String memberId, int newPrice) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("MinusPointFromMember");

     
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newPrice);
			pstmt.setString(2, memberId);
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public MemberItemList SelectAllFromMemberItemListByItem(Connection conn, String memberId, String itemName) {
		MemberItemList m = new MemberItemList();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("SelectAllFromMemberItemListByItem");
        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memberId);
            pstmt.setString(2, itemName);
            rset = pstmt.executeQuery();
           
            
            if(rset.next()){
            	m.setItemEA(rset.getInt("item_ea"));
                m.setItemName(rset.getString("item_name"));
                m.setMemberId(rset.getString("member_id"));
                m.setPurchaseDate(rset.getDate("purchase_date"));
                m.setPurchaseNo(rset.getInt("purchase_no"));
                m.setItemPrice(rset.getInt("item_price"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return m;
	}


	public int insertItemFromMemberItemList(Connection conn, String memberId, String itemName, int price) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertItemFromMemberItemList");

     
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, itemName);
			System.out.println(itemName);
			pstmt.setInt(3, price);
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateItemEaFromMemberItemList(Connection conn, int itemEA, String memberId, String itemName) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateItemEaFromMemberItemList");

     
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemEA);
			pstmt.setString(2, memberId);
			pstmt.setString(3, itemName);
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<MemberItemList> SelectAllFromMemberItemListByMemberId(Connection conn, String memberId) {
		List<MemberItemList> itemList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("SelectAllFromMemberItemListByMemberId");
        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memberId);
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MemberItemList m = new MemberItemList();
            	m.setItemEA(rset.getInt("item_ea"));
                m.setItemName(rset.getString("item_name"));
                m.setMemberId(rset.getString("member_id"));
                m.setPurchaseDate(rset.getDate("purchase_date"));
                m.setPurchaseNo(rset.getInt("purchase_no"));
                m.setItemPrice(rset.getInt("item_price"));
              
                itemList.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return itemList;
	}


	public List<Point> selectAllPointShop(Connection conn) {
		List<Point> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectAllPointShop");
        try{
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	Point p = new Point();
                p.setContent(rset.getString("content"));
                p.setItemImage(rset.getString("item_image"));
                p.setItemName(rset.getString("item_name"));
                p.setItemType(rset.getString("item_type"));
                p.setPrice(rset.getInt("price"));

                list.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return list;
	}


	public int deleteFromMemberItemList(Connection conn, String memberId, String itemName) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteFromMemberItemList");

        
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, itemName);
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateFromMemberItemList(Connection conn, String memberId, String itemName) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateFromMemberItemList");

        
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, itemName);
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public MemberUseItem selectAllFromMemberUseItem(Connection conn, String memberId) {
		MemberUseItem useItemList = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectAllFromMemberUseItem");
        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memberId);
            rset = pstmt.executeQuery();
            
            if(rset.next()){
            	useItemList = new MemberUseItem();
            	useItemList.setBackground(rset.getString("background"));
            	useItemList.setBadge(rset.getString("badge"));
            	useItemList.setMemberId(rset.getString("member_Id"));
            }
            System.out.println("ASDGSDG"+useItemList);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return useItemList;
	}


	public int insertBackgroundFromMemberUseItem(Connection conn, String memberId, String itemName) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertBackgroundFromMemberUseItem");

        
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, itemName);
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int insertBadgeFromMemberUseItem(Connection conn, String memberId, String itemName) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertBadgeFromMemberUseItem");

        
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, itemName);
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateBackgroundFromMemberUseItem(Connection conn, String memberId, String itemName) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateBackgroundFromMemberUseItem");

        
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, itemName);
			pstmt.setString(2, memberId);
			
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateBadgeFromMemberUseItem(Connection conn, String memberId, String itemName) {
		int result = 0;
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateBadgeFromMemberUseItem");

        
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, itemName);
			pstmt.setString(2, memberId);
			
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public String GetDate(Connection conn, String memberId) {
		String Date = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("GetLastFreeChargeDate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				Date = rset.getString("click_date");
			}
			else {
				Date = "빔";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return Date;
	}


	public int InsertFreeChargeList(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("InsertFreeChargeList");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
				
		return result;
	}


	public int PlusUpdatePoint(Connection conn, int point, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("PlusPointupdate");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, point);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
				
		return result;
	}


	public int UpdateFreeChargeList(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("UpdateFreeChargeList");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
				
		return result;
	}


	public int ResetScore(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("ResetScore");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
				
		return result;

	}


	public int ApplyBettingMoney(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("ApplyBettingMoney");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
				
		return result;

	}

}
