package betting.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import betting.model.dao.BettingDAO;
import betting.model.vo.BettingList;
import betting.model.vo.BettingResult;
import match.model.vo.Match;
import member.model.dao.MemberDAO;
import team.model.vo.Team;




public class BettingService {
	public List<BettingList> ShowBettingList() {
		Connection conn = getConnection();
		//dao업무로직 호출
		List<BettingList> list= new BettingDAO().selectBettingList(conn);
		close(conn);
		return list;
	}
	public List<BettingList> ShowBettingList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		//dao업무로직 호출
		List<BettingList> list= new BettingDAO().selectBettingList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	public int InsertBettingList(String admin_cometition, String gamecount, String game_time1) {
		Connection conn = getConnection();
		int result = new BettingDAO().InsertBettingList(conn,admin_cometition,gamecount,game_time1);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		//연결객체 반납
		close(conn);
		return result;
	}

	public void InsertMatchList(String team1, String team2, int bettingno, String admin_cometition, String game_time1) {
		Connection conn = getConnection();
		int result = new BettingDAO().InsertMatchList(conn,team1,team2,bettingno,admin_cometition,game_time1);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
	}


	public String getbettingType(int bettingNo) {
		Connection conn = getConnection();
		String bettingType = new BettingDAO().bettingType(conn,bettingNo);
		
		close(conn);
		return bettingType;
	}

	public ArrayList<String> getSelectTeamName(int bettingNo) {
		Connection conn = getConnection();
		ArrayList<String> TeamNameList = new BettingDAO().getSelectTeamName(conn,bettingNo);
		close(conn);

		return TeamNameList;
	}

	public Team getTeamList(String TeamName) {
		Connection conn = getConnection();
		Team t = new BettingDAO().getTeamList(conn,TeamName);
		
		close(conn);

		return t;
	}

	public ArrayList<String> getSelectTeamName2(int bettingNo) {
		Connection conn = getConnection();
		ArrayList<String> TeamNameList = new BettingDAO().getSelectTeamName2(conn,bettingNo);
		close(conn);

		return TeamNameList;
	}


	public List<String> BringTeam(String type) {
		Connection conn = getConnection();
		List<String> teamList = new BettingDAO().BringTeam(conn,type);
		close(conn);
		return teamList;
	}

	public ArrayList<Integer> getMatchNoList(int bettingNo) {
		Connection conn = getConnection();
		ArrayList<Integer> MatchNoList = new BettingDAO().getMatchNoList(conn,bettingNo);
		close(conn);
		return MatchNoList;
	}

	public ArrayList<Match> getMatchList(int bettingNo) {
		Connection conn = getConnection();
		ArrayList<Match> MatchList = new BettingDAO().getMatchList(conn,bettingNo);
		close(conn);
		return MatchList;
	}

	public int selectBettingCount() {
		Connection conn = getConnection();
		int totalBoardCount = new BettingDAO().selectBettingCount(conn);
		close(conn);
		return totalBoardCount;
	}
	public void InsertMatchResult(int match_no, int homescore, int awayscore) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().InsertMatchResult(conn,match_no,homescore,awayscore);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
	}
	public void updateBettingList(int bettingNo) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().updateBettingList(conn,bettingNo);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
	}
	public String selectHomeTeamName(int match_no1) {
		
		Connection conn = getConnection();
		String HomeTeamName = new BettingDAO().selectHomeTeamName(conn,match_no1);
		close(conn);
		return HomeTeamName;
	}
	public String selectAwayTeamName(int match_no1) {
		Connection conn = getConnection();
		String AwayTeamName = new BettingDAO().selectAwayTeamName(conn,match_no1);
		close(conn);
		return AwayTeamName;
	}
	public void updateDrawScore(String TeamName) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().updateDrawScore(conn,TeamName);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
	}
	public void updateWinScore(String TeamName) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().updateWinScore(conn,TeamName);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
	}
	public void updateLoseScore(String TeamName) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().updateLoseScore(conn,TeamName);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
	}
	public void InsertBettingResult(int betting_no, int bettingResult) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().InsertBettingResult(conn,betting_no,bettingResult);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
	}
	public void InsertBettingResultList(int bettingNo) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().InsertBettingResultList(conn,bettingNo);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
	}

	public void BettingOrder(int betting_no, int member_no, int money, int choice) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().BettingOrder(conn,betting_no,member_no,money,choice);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
	}
	public void BettingMoneyInsert(int betting_no, int money) {
		int result= 0;
		Connection conn = getConnection();
		result = new BettingDAO().BettingMoneyInsert(conn,betting_no,money);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
	}
	public ArrayList<Integer> WhoGiveBettingMoney(int betting_no, int bettingResult) {
		Connection conn = getConnection();
		ArrayList<Integer> list = new BettingDAO().WhoGiveBettingMoney(conn,betting_no,bettingResult);
		
		close(conn);
		
		return list;
	}
	public ArrayList<Integer> HowMuchBettingMoney(int betting_no, int bettingResult) {
		Connection conn = getConnection();
		ArrayList<Integer> list = new BettingDAO().HowMuchBettingMoney(conn,betting_no,bettingResult);
		
		close(conn);
		
		return list;
	}
	public int getTotalBettingMoney(int betting_no) {
		int Totalmoney =0;
		Connection conn = getConnection();
		Totalmoney = new BettingDAO().getTotalBettingMoney(conn,betting_no);
		close(conn);
		return Totalmoney;
	}
	public ArrayList<Integer> WhoLoseBettingMoney(int betting_no, int bettingResult) {
		Connection conn = getConnection();
		ArrayList<Integer> list = new BettingDAO().WhoLoseBettingMoney(conn,betting_no,bettingResult);
		close(conn);
		
		return list;
	}
	public void updateBettingOrderRight(int betting_no, int member_no) {
		Connection conn = getConnection();
		int result = new BettingDAO().updateBettingOrderRight(conn,betting_no,member_no);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
	}
	public void updateBettingOrderFalse(int betting_no, int member_no) {
		Connection conn = getConnection();
		int result = new BettingDAO().updateBettingOrderFalse(conn,betting_no,member_no);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
	}
	public int UseDoubleItem(String member_Id) {
		Connection conn = getConnection();
		int apply =  new BettingDAO().UseDoubleItem(conn,member_Id);
		return apply;
	}
	public ArrayList<Integer> getBettingList(int member_no) {
		Connection conn = getConnection();
		ArrayList<Integer> BettingListNo = new BettingDAO().getBettingList(conn,member_no);
		close(conn);
		return BettingListNo;
	}
	public int getBettingNo() {
		int bettingNo=0;
		Connection conn = getConnection();
		bettingNo = new BettingDAO().getBettingNo(conn);
		close(conn);
		return bettingNo;
	}
	public void updateBettingOrderMoney(int betting_no, int member_no, int money) {
		Connection conn = getConnection();
		int result = new BettingDAO().updateBettingOrderMoney(conn,betting_no,member_no,money);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
	}


}
