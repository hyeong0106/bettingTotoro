package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import betting.model.vo.BettingOrder;
import betting.model.vo.BettingResult;
import member.model.vo.Member;

public class MemberDAO {
	public static final int LOGIN_OK = 1;
    public static final int WRONG_PASSWORD = 0;
    public static final int ID_NOT_EXIST = -1;
	private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName = getClass().getResource("/sql/member/member-query.properties")
									.getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int insertMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			//미완성 쿼리를 가지고 statement객체 생성
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 대입
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getHint());
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public int loginCheck(Connection conn, Member m) {
		int result = 0;
		String sql = prop.getProperty("selectOne");
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			
			rset = pstmt.executeQuery();
			
			String memberId ="";
			String password = "";
			
			while(rset.next()) {
				 memberId =rset.getString("member_id");
				 password =rset.getString("password");
			}
			
			
			if(memberId.equals("")) {
				result = ID_NOT_EXIST;
			}
			else if(memberId.equals(m.getMemberId())
					&& password.equals(m.getPassword())) {
				result = LOGIN_OK;
			}
			else if(memberId.equals(m.getMemberId()) && (!password.equals(m.getPassword()))) {
				result = WRONG_PASSWORD;
			}else if(password.equals(m.getPassword()) && (!memberId.equals(m.getMemberId()))) {
				result = ID_NOT_EXIST;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	public Member selectOne(Connection conn, String id) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//쿼리실행
			rset = pstmt.executeQuery();
			
			//rset의 정보를 member객체에 옮겨담기
			if(rset.next()) {
				m = new Member();
				
				m.setMemberNo(rset.getInt("member_No"));
				m.setMemberId(rset.getString("member_id"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setHint(rset.getString("hint"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setPoint(rset.getInt("point"));
				m.setScore(rset.getInt("score"));
				
			}
//			System.out.println("member@dao="+m);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	public Member selectOne(Connection conn, int member_no) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			//쿼리실행
			rset = pstmt.executeQuery();
			
			//rset의 정보를 member객체에 옮겨담기
			if(rset.next()) {
				m = new Member();
				
				m.setMemberNo(rset.getInt("member_No"));
				m.setMemberId(rset.getString("member_id"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setHint(rset.getString("hint"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setPoint(rset.getInt("point"));
				m.setScore(rset.getInt("score"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}

	public int checkIdDuplicate(Connection conn, String memberId_) {
		int cnt= 0;
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String sql = prop.getProperty("checkIdDuplicate");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId_);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
			 cnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return cnt;
	}
	
	public String MemberSearchid(Connection conn,String searchname,String searchphone) {
		String MemberSearchid = "";
		
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberSearchId");
		System.out.println(sql);
		System.out.println("["+searchname+"]");
		System.out.println("["+searchphone+"]");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchname);
			pstmt.setString(2, searchphone);
			
			rset =pstmt.executeQuery();
		
			if (rset.next()) {

				MemberSearchid = rset.getString("member_id");
				
			}
		
		} catch (SQLException e) {
			MemberSearchid = "등록된 아이디가 없습니다. 회원가입해주세요.";
			e.printStackTrace();
		}finally {
			System.out.println("DAO의  MemberSearchid= "+MemberSearchid);
			close(rset);
			close(pstmt);
		}
		System.out.println("memberDAO="+MemberSearchid);
		return MemberSearchid;
	}
	public Member MemberChangedPwd(Connection conn, Member pwd) {
		int result = 0;
		PreparedStatement pstmt =null;
		
		String sql = prop.getProperty("MemberChangedPWd");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pwd.getPassword());
			pstmt.setString(2,pwd.getMemberId());
			result = pstmt.executeUpdate();
			
			if(result >0) {
				
                System.out.println("업데이트되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("PASSWORD의 값은 :"+ pwd.getPassword());
			close(pstmt);
			
		}
		return pwd;
	}
	public int selectChangingMember(Connection conn, String searchid, String searchname, String searchphone,
			String answer) {
		int cnt =0;
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectChangingMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchid);
			pstmt.setString(2, searchname);
			pstmt.setString(3, searchphone);
			pstmt.setString(4, answer);
			
			rset = pstmt.executeQuery();
		
			
			if(rset.next()) {
				 cnt = rset.getInt("cnt");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return cnt;
	}
	public String MemberChangedPwd(Connection conn, String searchid,String changedpassword) {
		   int result=0;
			String changedpwd ="";
			PreparedStatement pstmt =null;
			
			String sql = prop.getProperty("MemberChangedPWD");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,changedpassword);
				pstmt.setString(2,searchid);
				result = pstmt.executeUpdate();
				
				if(result >0) {
					
	                System.out.println("업데이트되었습니다.");
	                changedpwd = changedpassword;
				}
				else {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				
				close(pstmt);
				
			}
			return changedpwd;
		}
	public int updateMember(Connection conn, Member m) {
		int result = 0;
		
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateMember");
        
        try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getGender());
			pstmt.setString(6, m.getMemberId());
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
        
		return result;
	}
	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteMember");
        
        try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,memberId);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
        
		return result;
	}
	
	public int giveBettingMoney(Connection conn, int memberNoList, int d, int e2) {

		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("giveBettingMoney");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(3, memberNoList);
			pstmt.setDouble(1, d);
			pstmt.setDouble(2, e2);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int UpdateMemberMoney(Connection conn, int member_no, int money, int money2) {

		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("UpdateMemberMoney");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, money);
			pstmt.setInt(2, money2);
			pstmt.setInt(3, member_no);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
				
		
		return result;
	}
	public int getMemberNo(Connection conn, String memberId) {
		int memberNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getMemberNoByMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				memberNo = rset.getInt("member_no");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return memberNo;
	}
	public ArrayList<BettingOrder> getBettingListByMemberNo(Connection conn, int memberno) {
		ArrayList<BettingOrder> BOlist = new ArrayList<BettingOrder>();
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		String sql = prop.getProperty("getBettingListByMemberNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BettingOrder BO = new BettingOrder();
				BO.setBettingNo(rset.getInt("betting_no"));
				BO.setBettingMoney(rset.getInt("Betting_money"));
				BO.setBettingDate(rset.getString("Bettingdate"));
				BO.setPass(rset.getInt("pass"));
				BOlist.add(BO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return BOlist;
	}
	public int getBettingListByMemberNo(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("MadeItemList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	public int selectChangingMember(Connection conn,Member searchpwd) {
		int cnt =0;
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectChangingMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchpwd.getMemberId());
			pstmt.setString(2, searchpwd.getMemberName());
			pstmt.setString(3, searchpwd.getPhone());
			pstmt.setString(4, searchpwd.getHint());
			
			rset = pstmt.executeQuery();
		
			
			if(rset.next()) {
				 cnt = rset.getInt("cnt");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return cnt;
	}
	
	public int updateReasonFromMemberDel(Connection conn, String memberId, String content) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateReasonFromMemberDel");
        
        try {
            //미완성 쿼리를 가지고 statement객체 생성
            pstmt = conn.prepareStatement(sql);
            //미완성쿼리에 데이터 대입

            pstmt.setString(1, content);
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
	public ArrayList<BettingOrder> getBettingListByMemberNo(Connection conn, int memberno, int cPage, int numPerPage) {
		ArrayList<BettingOrder> BOlist = new ArrayList<BettingOrder>();
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		String sql = prop.getProperty("getBettingListByMemberNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberno);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BettingOrder BO = new BettingOrder();
				BO.setBettingOrderNo(rset.getInt("betting_order_no"));
				BO.setBettingNo(rset.getInt("betting_no"));
				BO.setBettingMoney(rset.getInt("Betting_money"));
				BO.setBettingDate(rset.getString("Bettingdate"));
				BO.setPass(rset.getInt("pass"));
				BOlist.add(BO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return BOlist;
	}
	public int selectBettingCount(Connection conn, int memberno) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberBettingCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberno);
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalMember = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}
	public ArrayList<Member> selectAllMember(Connection conn) {
	      ArrayList<Member> memberList = new ArrayList<Member>();
	      PreparedStatement pstmt = null;
	      ResultSet rset =null;
	      String sql = prop.getProperty("selectAllMemberPointTop");
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rset = pstmt.executeQuery();
	         while(rset.next()) {
	            Member m = new Member();
	            m.setMemberNo(rset.getInt("member_No"));
	            m.setMemberId(rset.getString("member_id"));
	            m.setPassword(rset.getString("password"));
	            m.setMemberName(rset.getString("member_name"));
	            m.setGender(rset.getString("gender"));
	            m.setEmail(rset.getString("email"));
	            m.setPhone(rset.getString("phone"));
	            m.setHint(rset.getString("hint"));
	            m.setEnrollDate(rset.getDate("enrolldate"));
	            m.setPoint(rset.getInt("point"));
	            m.setScore(rset.getInt("score"));
	            memberList.add(m);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(rset);
	         close(pstmt);
	      }
	      return memberList;
	   }
	public ArrayList<Member> selectScoreTop(Connection conn) {
		   ArrayList<Member> memberList = new ArrayList<Member>();
		      PreparedStatement pstmt = null;
		      ResultSet rset =null;
		      String sql = prop.getProperty("selectScoreTop");
		      try {
		         pstmt = conn.prepareStatement(sql);
		         rset = pstmt.executeQuery();
		         while(rset.next()) {
		            Member m = new Member();
		            m.setMemberNo(rset.getInt("member_No"));
		            m.setMemberId(rset.getString("member_id"));
		            m.setPassword(rset.getString("password"));
		            m.setMemberName(rset.getString("member_name"));
		            m.setGender(rset.getString("gender"));
		            m.setEmail(rset.getString("email"));
		            m.setPhone(rset.getString("phone"));
		            m.setHint(rset.getString("hint"));
		            m.setEnrollDate(rset.getDate("enrolldate"));
		            m.setPoint(rset.getInt("point"));
		            m.setScore(rset.getInt("score"));
		            memberList.add(m);
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         close(rset);
		         close(pstmt);
		      }
		      return memberList;
	}
	public ArrayList<Member> selectScoreWorst(Connection conn) {
		   ArrayList<Member> memberList = new ArrayList<Member>();
		      PreparedStatement pstmt = null;
		      ResultSet rset =null;
		      String sql = prop.getProperty("selectScoreWorst");
		      try {
		         pstmt = conn.prepareStatement(sql);
		         rset = pstmt.executeQuery();
		         while(rset.next()) {
		            Member m = new Member();
		            m.setMemberNo(rset.getInt("member_No"));
		            m.setMemberId(rset.getString("member_id"));
		            m.setPassword(rset.getString("password"));
		            m.setMemberName(rset.getString("member_name"));
		            m.setGender(rset.getString("gender"));
		            m.setEmail(rset.getString("email"));
		            m.setPhone(rset.getString("phone"));
		            m.setHint(rset.getString("hint"));
		            m.setEnrollDate(rset.getDate("enrolldate"));
		            m.setPoint(rset.getInt("point"));
		            m.setScore(rset.getInt("score"));
		            memberList.add(m);
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         close(rset);
		         close(pstmt);
		      }
		      return memberList;
	}
	public boolean checkLogin(Connection conn, String id) {
		boolean Already = false;
		PreparedStatement pstmt = null;
	    ResultSet rset =null;
	    String sql = prop.getProperty("checkLogin");
	    try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				Already= true;
			}
			else {
				Already= false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);			
		}
		
		return Already;
	}
	public int insertIntoLoginList(Connection conn, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertIntoLoginList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateLoginList(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateLoginList");
		
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
	public int resetLoginList(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("resetLoginList");
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public String selectBadeg(Connection conn, String memberId) {
		String BadgeList = "";
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBadge");
		ResultSet rset = null;
		String s = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				BadgeList = rset.getString("badge");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return BadgeList;
	}
	
	
}
