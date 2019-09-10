package board.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import admin.model.dao.AdminDAO;
import board.model.vo.Board;
import board.model.vo.BoardComment;
import member.model.vo.Member;


public class BoardDAO {
	private Properties prop = new Properties();
	
	public BoardDAO() {
		try {
            String fileName = AdminDAO.class.getResource("/sql/board/board-query.properties").getPath();
            prop.load(new FileReader(fileName));
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public List<Board> selectBoardList(Connection conn, int cPage, int numPerPage) {
		List<Board> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectBoardList");
        System.out.println(query);
        try{
            
            pstmt = conn.prepareStatement(query);
         
            int startRowNum = (cPage-1)*numPerPage+1;
            int endRowNum = cPage * numPerPage;
            pstmt.setInt(1, startRowNum);
            pstmt.setInt(2, endRowNum);
            
            
            
            rset = pstmt.executeQuery();
            
            while(rset.next()){
                Board b = new Board();
                
                //컬럼명은 대소문자 구분이 없다.
                b.setBoardNo(rset.getInt("BOARD_NO"));
                b.setBoardWriter(rset.getString("BOARD_WRITER"));
                b.setBoardTitle(rset.getString("BOARD_TITLE"));
                b.setBoardContent(rset.getString("BOARD_CONTENT"));
                b.setBoardDate(rset.getDate("BOARD_DATE"));
                b.setReadCount(rset.getInt("BOARD_READCOUNT"));
                b.setCommentCnt(rset.getInt("comment_cnt"));
                list.add(b);
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

	public int selectBoardCount(Connection conn) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardCount");
		
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

	public List<Board> selectBoaradByMemberId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPagedBoardByMemberId");
		
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
				Board b = new Board();
                
                b.setBoardNo(rset.getInt("BOARD_NO"));
                b.setBoardWriter(rset.getString("BOARD_WRITER"));
                b.setBoardTitle(rset.getString("BOARD_TITLE"));
                b.setBoardContent(rset.getString("BOARD_CONTENT"));
                b.setBoardDate(rset.getDate("BOARD_DATE"));
                b.setReadCount(rset.getInt("BOARD_READCOUNT"));
                
                list.add(b);
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

	public int selectBoardByMemberIdCount(Connection conn, String searchKeyword) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardByMemberIdCount");
		
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

	public List<Board> selectBoardByBoardTitle(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardByBoardTitle");
		
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
				Board b = new Board();
                
                b.setBoardNo(rset.getInt("BOARD_NO"));
                b.setBoardWriter(rset.getString("BOARD_WRITER"));
                b.setBoardTitle(rset.getString("BOARD_TITLE"));
                b.setBoardContent(rset.getString("BOARD_CONTENT"));
                b.setBoardDate(rset.getDate("BOARD_DATE"));
                b.setReadCount(rset.getInt("BOARD_READCOUNT"));
                
                list.add(b);
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

	public int selectBoardByBoardTitleCount(Connection conn, String searchKeyword) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardByBoardTitleCount");
		
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

	public Board selectOne(Connection conn, int boardNo) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			//쿼리실행
			rset = pstmt.executeQuery();
			
			//rset의 정보를 member객체에 옮겨담기
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
                b.setBoardWriter(rset.getString("BOARD_WRITER"));
                b.setBoardTitle(rset.getString("BOARD_TITLE"));
                b.setBoardContent(rset.getString("BOARD_CONTENT"));
                b.setBoardDate(rset.getDate("BOARD_DATE"));
                b.setReadCount(rset.getInt("BOARD_READCOUNT"));
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public int deleteBoard(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	public int updateBoard(Connection conn, int boardNo, String boardTitle, String boardContent) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoard");
		System.out.println("게시물 수정 DAO");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	public int insertBoard(Connection conn, String boardTitle, String boardWriter, String boardContent) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardWriter);
			pstmt.setString(2, boardTitle);
			pstmt.setString(3, boardContent);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int increaseReadCount(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseReadCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	public int selectLastSeq(Connection conn) {
		int boardNo = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastSeq");
		
		try {
			//객체생성할때 쿼리를 전달하지않음 실행할때 전달
			stmt  = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				boardNo = rset.getInt("board_No");
			}
			System.out.println("board@dao="+boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		
		
		return boardNo;
	}

	public int insertBoardComment(Connection conn, BoardComment bc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoardComment"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리 완성
			pstmt.setInt(1, bc.getBoardCommentLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			
			//db의 board_comment_ref는 number이고,
			//db number타입은 null값을 허용한다.
			//현재 board_comment_ref는 board_comment_no를 참조하고 있음
			//board_comment_no의 0값은 존재하지 않는다.
			//String은 null을 처리할수있음 setInt->setString
			//오라클이 자동형변환해줌
			pstmt.setString(5, bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef()));
			
			//쿼리실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoardComment(Connection conn, int boardCommentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoardComment"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리 완성
			pstmt.setInt(1, boardCommentNo);
			
			//쿼리실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<BoardComment> selectBoardComment(Connection conn, int boardNo, int cPage, int numPerPage) {
		List<BoardComment> commentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentListByPaging");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			int startRowNum = (cPage-1)*numPerPage+1;
            int endRowNum = cPage * numPerPage;
            pstmt.setInt(2, startRowNum);
            pstmt.setInt(3, endRowNum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardComment bc = new BoardComment();
				bc.setBoardCommentNo(rset.getInt("board_comment_no"));
				bc.setBoardCommentLevel(rset.getInt("board_comment_level"));
				bc.setBoardCommentWriter(rset.getString("board_comment_writer"));
				bc.setBoardCommentContent(rset.getString("board_comment_content"));
				bc.setBoardRef(rset.getInt("board_ref"));
				bc.setBoardCommentRef(rset.getInt("board_comment_ref"));
				bc.setBoardCommentDate(rset.getDate("board_comment_date"));
				commentList.add(bc);
			}
			System.out.println("commentList@dao="+commentList);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return commentList;
	}

	public int selectBoardCommentCount(Connection conn, int boardNo) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardCommentCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
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

	

}