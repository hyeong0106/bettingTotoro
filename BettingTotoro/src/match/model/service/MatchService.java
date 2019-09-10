package match.model.service;

import static common.JDBCTemplate.getConnection;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import match.model.dao.MatchDAO;
import match.model.vo.Match;
import team.model.vo.Team;

public class MatchService {

	public ArrayList<Match> MatchAllList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		ArrayList<Match> matchList = new MatchDAO().MatchAllList(conn, cPage, numPerPage);
		
		close(conn);
		return matchList;
	}

	public int selectMatchCount() {
		Connection conn = getConnection();
		int totalContent = new MatchDAO().selectMatchCount(conn);
		close(conn);
		return totalContent;
	}

	public Match SelectMatch(int matchNo) {
		Connection conn = getConnection();
		Match m = new MatchDAO().SelectMatch(conn,matchNo);
		close(conn);
		return m;
	}

	public Team SelectTeam(String string) {
		Connection conn = getConnection();
		Team t = new MatchDAO().SelectTeam(conn,string);
		close(conn);
		return t;
	}

	public Match SelectMatchByBettinNo(int bettingNo) {
		Connection conn = getConnection();
		Match m = new MatchDAO().SelectMatchByBettinNo(conn,bettingNo);
		close(conn);
		return m;
	}

	public int selectHasResult(int bettingNo) {
		Connection conn = getConnection();
		int HasResult = new MatchDAO().selectHasResult(conn,bettingNo);
		close(conn);
		return HasResult;
	}


}