package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import admin.model.dao.AdminDAO;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class AdminService {

	public List<Member> selectUserList(int cPage, int numPerPage) {
		//Connection객체 생성
		Connection conn = getConnection();
		//dao업무로직 호출
		List<Member> list= new AdminDAO().selectUserList(conn, cPage, numPerPage);
		
		//트랜잭션 필요없음		
		//Connection객체 자원반납
		close(conn);
		
		return list;
	}

	public int selectUserCount() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectUserCount(conn);
		close(conn);
		return totalContent;
	}

	public List<Member> selectUserByMemberId(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectUserByMemberId(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectUserByMemberIdCount(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectUserByMemberIdCount(conn,searchKeyword);
		close(conn);
		return totalContent;
	}

	public List<Member> selectUserByMemberName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectUserByMemberName(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectUserByMemberNameCount(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectUserByMemberNameCount(conn,searchKeyword);
		close(conn);
		return totalContent;
	}
	public Member selectOne(String id) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, id);
		close(conn);
		return m;
	}

	public int updateUser(Member member) {
		Connection conn = getConnection();
		int result = new AdminDAO().updateUser(conn, member);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteUser(String memberId) {
		Connection conn = getConnection();
		int result = new AdminDAO().deleteUser(conn, memberId);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}


}
