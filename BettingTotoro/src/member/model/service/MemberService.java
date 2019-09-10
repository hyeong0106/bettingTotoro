package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import betting.model.vo.BettingOrder;
import betting.model.vo.BettingResult;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberService {

	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn, m);
		//dml인 경우 transaction처리 직접하기
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		
		//연결객체 반납
		close(conn);
		return result;
	}

	public int loginCheck(Member m) {
        Connection conn = getConnection();
        int result = new MemberDAO().loginCheck(conn,m);
        close(conn);
        return result;
    }

	public Member selectOne(String id) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, id);
		close(conn);
		return m;
	}
	

	public Member selectOne(int member_no) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, member_no);
		close(conn);
		return m;
	}


	public int checkIdDuplicate(String memberId_) {
		Connection conn = getConnection();
		int cnt = new MemberDAO().checkIdDuplicate(conn,memberId_);
		close(conn);
			return cnt;
		}

	public String MemberSearchid(String searchname,String searchphone) {
		  Connection conn =getConnection();
		  System.out.println("Service의  searchname= "+searchname);
		  System.out.println("Service의  searchphone= "+searchphone);
		
		String MemberSearchid = new MemberDAO().MemberSearchid(conn,searchname,searchphone);
			close(conn);
		  return MemberSearchid;
		}

	public Member memberChangedPwd(Member pwd) {
		 Connection conn =getConnection();
		 Member chpwd =new MemberDAO().MemberChangedPwd(conn,pwd);
		 close(conn);
		return chpwd;
	}

	public int selectChangingMember(String searchid, String searchname, String searchphone, String answer) {
		Connection conn = getConnection();
		int cnt = new MemberDAO().selectChangingMember(conn, searchid,searchname,searchphone,answer);
		close(conn);
		return cnt;
	}

	public String memberChangedPwd(String searchid,String changedpassword) {
		 Connection conn =getConnection();
		 String changedpwd =new MemberDAO().MemberChangedPwd(conn,searchid,changedpassword);
		 close(conn);
		return changedpwd;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = new MemberDAO().deleteMember(conn, memberId);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().updateMember(conn, member);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public void giveBettingMoney(int MemberNoList, int d, int e) {
		Connection conn = getConnection();
		int result = new MemberDAO().giveBettingMoney(conn, MemberNoList,d,e);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		

	}

	public int UpdateMemberMoney(int member_no, int money, int money2) {
		Connection conn = getConnection();
		int result = new MemberDAO().UpdateMemberMoney(conn, member_no,money,money2);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int getMemberNo(String memberId) {
		Connection conn = getConnection();
		int memberNo= new MemberDAO().getMemberNo(conn,memberId);
		close(conn);
		return memberNo;
	}

	public ArrayList<BettingOrder> getBettingListByMemberNo(int memberno) {
		ArrayList<BettingOrder> BOlist = new ArrayList<BettingOrder>();
		Connection conn = getConnection();
		BOlist=new MemberDAO().getBettingListByMemberNo(conn,memberno);
		close(conn);
		return BOlist;
	}

	public void insertMemberItems(Member m) {
		Connection conn = getConnection();
		int result= new MemberDAO().getBettingListByMemberNo(conn,m);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);

	}

	public int selectChangingMember(Member searchpwd) {
		Connection conn = getConnection();
		int cnt = new MemberDAO().selectChangingMember(conn, searchpwd);
		close(conn);
		return cnt;
	}

	public int updateReasonFromMemberDel(String memberId, String content) {
        Connection conn = getConnection();
        int result = new MemberDAO().updateReasonFromMemberDel(conn, memberId, content);
        if(result>0){
            commit(conn);
        }
        else
            rollback(conn);
        close(conn);
        return result;
    }

	public ArrayList<BettingOrder> getBettingListByMemberNo(int memberno, int cPage, int numPerPage) {
		ArrayList<BettingOrder> BOlist = new ArrayList<BettingOrder>();
		Connection conn = getConnection();
		BOlist=new MemberDAO().getBettingListByMemberNo(conn,memberno,cPage,numPerPage);
		close(conn);
		return BOlist;
	}

	public int selectMemberBettingCount(int memberno) {
		Connection conn = getConnection();
		int totalBoardCount = new MemberDAO().selectBettingCount(conn,memberno);
		close(conn);
		return totalBoardCount;
	}

	public ArrayList<Member> selectAllMember() {
	      ArrayList<Member> memberList = new ArrayList<Member>();
	      Connection conn = getConnection();
	      memberList=new MemberDAO().selectAllMember(conn);
	      close(conn);
	      return memberList;
	   }

	public List<Member> selectScoreTop() {
		 ArrayList<Member> memberList = new ArrayList<Member>();
	      Connection conn = getConnection();
	      memberList=new MemberDAO().selectScoreTop(conn);
	      close(conn);
	      return memberList;
	}

	public List<Member> selectScoreWorst() {
		 ArrayList<Member> memberList = new ArrayList<Member>();
	      Connection conn = getConnection();
	      memberList=new MemberDAO().selectScoreWorst(conn);
	      close(conn);
	      return memberList;
	}

	public boolean checkLogin(String id) {
		Connection conn = getConnection();
		boolean AlreadyLogin =  new MemberDAO().checkLogin(conn,id);
		close(conn);
		return AlreadyLogin;
	}

	public void insertIntoLoginList(String id) {
		 Connection conn = getConnection();
		 int result = new MemberDAO().insertIntoLoginList(conn,id);
	     if(result>0){
	            commit(conn);
	     }
	     else
	         rollback(conn);
		close(conn);
		
	}

	public void updateLoginList(String memberId) {
		 Connection conn = getConnection();
		 int result = new MemberDAO().updateLoginList(conn,memberId);
	     if(result>0){
	            commit(conn);
	     }
	     else
	         rollback(conn);
		close(conn);
		
	}

	public void resetLoginList() {
		 Connection conn = getConnection();
		 int result = new MemberDAO().resetLoginList(conn);
		 if(result>0){
	            commit(conn);
	     }
	     else
	         rollback(conn);
		close(conn);
	}

	public String selectBadeg(String memberId) {
		Connection conn = getConnection();
		String BadgeList = new MemberDAO().selectBadeg(conn,memberId);
		close(conn);
		return BadgeList;
	}
}
