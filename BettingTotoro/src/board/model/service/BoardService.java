package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import board.model.dao.BoardDAO;
import board.model.vo.Board;
import board.model.vo.BoardComment;




public class BoardService {

	public List<Board> selectBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list= new BoardDAO().selectBoardList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContent = new BoardDAO().selectBoardCount(conn);
		close(conn);
		return totalContent;
	}

	public List<Board> selectBoaradByMemberId(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list = new BoardDAO().selectBoaradByMemberId(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectBoardByMemberIdCount(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new BoardDAO().selectBoardByMemberIdCount(conn,searchKeyword);
		close(conn);
		return totalContent;
	}

	public List<Board> selectBoardByBoardTitle(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list = new BoardDAO().selectBoardByBoardTitle(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectBoardByBoardTitleCount(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new BoardDAO().selectBoardByBoardTitleCount(conn,searchKeyword);
		close(conn);
		return totalContent;
	}

	public Board selectOne(int boardNo) {
		Connection conn = getConnection();
		Board board = new BoardDAO().selectOne(conn, boardNo);
		close(conn);
		return board;
	}

	public int deleteBoard(int boardNo) {
		Connection conn = getConnection();
		int result = new BoardDAO().deleteBoard(conn, boardNo);
		if(result > 0) {
			commit(conn);
		}
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateBoard(int boardNo, String boardTitle, String boardContent) {
		Connection conn = getConnection();
		int result = new BoardDAO().updateBoard(conn, boardNo,boardTitle,boardContent);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int insertBoard(String boardTitle, String boardWriter, String boardContent) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoard(conn, boardTitle,boardWriter,boardContent);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int increaseReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = new BoardDAO().increaseReadCount(conn, boardNo);
		if(result > 0) {
			commit(conn);
			result = new BoardDAO().selectLastSeq(conn);
		}
		else
			rollback(conn);
		close(conn);
		return result;
		
	}

	public int insertBoardComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoardComment(conn, bc);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public int deleteBoardComment(int boardCommentNo) {
		Connection conn = getConnection();
		int result = new BoardDAO().deleteBoardComment(conn, boardCommentNo);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public List<BoardComment> selectBoardComment(int boardNo, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<BoardComment> commentList = new BoardDAO().selectBoardComment(conn,boardNo,cPage, numPerPage);
		close(conn);
		
		return commentList;
	}

	public int selectBoardCommentCount(int boardNo) {
		Connection conn = getConnection();
		int totalContent = new BoardDAO().selectBoardCommentCount(conn,boardNo);
		close(conn);
		return totalContent;
	}

}