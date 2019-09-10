package admin.model.dao;

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

public class AdminDAO {
	//쿼리를 관리할 Properties객체 생성
	private Properties prop = new Properties();
	
	public AdminDAO() {
		//properties파일에서 쿼리문 읽어다 prop채우기
		try {
            String fileName = AdminDAO.class.getResource("/sql/admin/admin-query.properties").getPath();
            prop.load(new FileReader(fileName));
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public List<Member> selectUserList(Connection conn, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectUserListByPaging");
        System.out.println(query);
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
            //startrownum, endrownum
            //numPerPage = 5
            //cPage가 1인경우, 1,5
            //cPage가 2인경우, 6,10
            //cPage가 3인경우, 11,15
            //...
            int startRowNum = (cPage-1)*numPerPage+1;
            int endRowNum = cPage * numPerPage;
            pstmt.setInt(1, startRowNum);
            pstmt.setInt(2, endRowNum);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
                Member m = new Member();
                //컬럼명은 대소문자 구분이 없다.
                m.setMemberNo(rset.getInt("MEMBER_NO"));
                m.setMemberId(rset.getString("MEMBER_ID"));
                m.setPassword(rset.getString("PASSWORD"));
                m.setMemberName(rset.getString("MEMBER_NAME"));
                m.setGender(rset.getString("GENDER"));
                m.setEmail(rset.getString("EMAIL"));
                m.setPhone(rset.getString("PHONE"));
                m.setPoint(rset.getInt("POINT"));
                m.setScore(rset.getInt("SCORE"));
                m.setEnrollDate(rset.getDate("ENROLLDATE"));
                
                list.add(m);
            }
            System.out.println("list@dao"+list);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public int selectUserCount(Connection conn) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUserCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			System.out.println("cnt@dao="+totalContent);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}

	public List<Member> selectUserByMemberId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPagedUserByMemberId");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage * numPerPage);
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("MEMBER_NO"));
                m.setMemberId(rset.getString("MEMBER_ID"));
                m.setPassword(rset.getString("PASSWORD"));
                m.setMemberName(rset.getString("MEMBER_NAME"));
                m.setGender(rset.getString("GENDER"));
                m.setEmail(rset.getString("EMAIL"));
                m.setPhone(rset.getString("PHONE"));
                m.setPoint(rset.getInt("POINT"));
                m.setScore(rset.getInt("SCORE"));
                m.setEnrollDate(rset.getDate("ENROLLDATE"));
                
                list.add(m);
			}
			System.out.println("list@dao="+list);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int selectUserByMemberIdCount(Connection conn, String searchKeyword) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUserByMemberIdCount");
		
		try {
			System.out.println("IdCount@dao: searchKeyword"+searchKeyword);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			System.out.println("cnt@dao="+totalContent);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}

	public List<Member> selectUserByMemberName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPagedUserByMemberName");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage * numPerPage);
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("MEMBER_NO"));
                m.setMemberId(rset.getString("MEMBER_ID"));
                m.setPassword(rset.getString("PASSWORD"));
                m.setMemberName(rset.getString("MEMBER_NAME"));
                m.setGender(rset.getString("GENDER"));
                m.setEmail(rset.getString("EMAIL"));
                m.setPhone(rset.getString("PHONE"));
                m.setPoint(rset.getInt("POINT"));
                m.setScore(rset.getInt("SCORE"));
                m.setEnrollDate(rset.getDate("ENROLLDATE"));
                
                list.add(m);
			}
			System.out.println("list@dao="+list);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int selectUserByMemberNameCount(Connection conn, String searchKeyword) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUserByMemberNameCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			System.out.println("cnt@dao="+totalContent);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
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

	public int updateUser(Connection conn, Member m) {
		int result = 0;
		
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateUser");
        
        try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getGender());
			pstmt.setInt(6, m.getScore());
			pstmt.setString(7, m.getMemberId());
			
			//쿼리실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
        
		return result;
	}

	public int deleteUser(Connection conn, String memberId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteUser");
        
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

}
