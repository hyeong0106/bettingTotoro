package match.model.dao;

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

import admin.model.dao.AdminDAO;
import match.model.vo.Match;
import team.model.vo.Team;

public class MatchDAO {
	private Properties prop = new Properties();
	public MatchDAO() {
		try {
            String fileName = AdminDAO.class.getResource("/sql/match/match-query.properties").getPath();
            prop.load(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public ArrayList<Match> MatchAllList(Connection conn, int cPage, int numPerPage) {
		ArrayList<Match> MatchList = new ArrayList<Match>();
		
		PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectMatchListByPaging");
        System.out.println(query);
        try{
            
            pstmt = conn.prepareStatement(query);
         
            int startRowNum = (cPage-1)*numPerPage+1;
            int endRowNum = cPage * numPerPage;
            pstmt.setInt(1, startRowNum);
            pstmt.setInt(2, endRowNum);
            
            
            
            rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Match m = new Match();
				m.setMatchNo(rset.getInt("match_no"));
				m.setHomeTeamName(rset.getString("home_team_name"));
				m.setAwayTeamName(rset.getString("away_team_name"));
				m.setMatchDate(rset.getDate("match_date"));
				m.setHomeTeamScore(rset.getInt("A_score"));
				m.setAwayTeamScore(rset.getInt("B_score"));
				m.setBettingNo(rset.getInt("betting_no"));
				m.setTypeOfSports(rset.getString("type_of_sports"));
				MatchList.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return MatchList;
	}
	public int selectMatchCount(Connection conn) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMatchCount");
		
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
	public Match SelectMatch(Connection conn, int matchNo) {
		Match m = new Match();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMatch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, matchNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				m.setMatchDate(rset.getDate("Match_DATE"));
				m.setTypeOfSports(rset.getString("type_of_sports"));
				m.setHomeTeamName(rset.getString("home_team_name"));
				m.setHomeTeamScore(rset.getInt("A_score"));
				m.setAwayTeamName(rset.getString("Away_team_name"));
				m.setAwayTeamScore(rset.getInt("B_score"));
				m.setBettingNo(rset.getInt("Betting_no"));
				
			}
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return m;
	}
	public Team SelectTeam(Connection conn, String matchNo) {
		Team t = new Team();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTeam");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, matchNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				t.setImage_file(rset.getString("imgae_file"));
				t.setDraw(rset.getInt("draw"));
				t.setLose(rset.getInt("lose"));
				t.setWin(rset.getInt("win"));
				
			}
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return t;
	}
	public Match SelectMatchByBettinNo(Connection conn, int bettingNo) {
		Match m = new Match();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SelectMatchByBettinNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bettingNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				m.setMatchDate(rset.getDate("Match_DATE"));
				m.setTypeOfSports(rset.getString("type_of_sports"));
				m.setHomeTeamName(rset.getString("home_team_name"));
				m.setHomeTeamScore(rset.getInt("A_score"));
				m.setAwayTeamName(rset.getString("Away_team_name"));
				m.setAwayTeamScore(rset.getInt("B_score"));
				
			}
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return m;
	}
	public int selectHasResult(Connection conn, int bettingNo) {
		int HasResult =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectHasResult");
		System.out.println(sql);
		System.out.println(bettingNo);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bettingNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				System.out.println("이거 실행해라");
				HasResult=rset.getInt("Hasresult");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("HasResult 값 : "+HasResult);
		return HasResult;
	}
}