package betting.model.dao;

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

import static common.JDBCTemplate.close;

import betting.model.vo.BettingList;
import match.model.vo.Match;
import member.model.vo.Member;
import team.model.vo.Team;
//showBettingList
public class BettingDAO {
	private Properties prop = new Properties();
	
	public BettingDAO() {
		//properties파일에서 쿼리문 읽어다 prop채우기
		try {
            String fileName = BettingDAO.class.getResource("/sql/betting/betting-query.properties").getPath();
            prop.load(new FileReader(fileName));
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public List<BettingList> selectBettingList(Connection conn) {
		List<BettingList> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("showBettingList");
        try {
        	pstmt = conn.prepareStatement(sql);
        	
        	rset = pstmt.executeQuery();
        	
        	while(rset.next()) {
        		BettingList b = new BettingList();
        		b.setBettingNo(rset.getInt("Betting_no"));
        		b.setBettingName(rset.getString("betting_title"));
        		b.setBettingEndTime(rset.getString("betting_end_time"));
        		System.out.println(rset.getTimestamp("betting_end_time"));
        		b.setTotalBettingMoney(rset.getInt("total_betting_money"));
        		b.setBettingType(rset.getString("betting_type"));
        		b.setTypeOfSports(rset.getString("type_of_sports"));
        		b.setHasResult(rset.getInt("hasresult"));
        		list.add(b);
        	}
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
        return list;
	}

	public List<BettingList> selectBettingList(Connection conn, int cPage, int numPerPage) {
		List<BettingList> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("showBettingListPageing");
        
       int num1 = (cPage-1)*numPerPage+1;
       int num2 =cPage*numPerPage;
       System.out.println(num1+","+num2);
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
        	
			rset = pstmt.executeQuery();
        	
        	while(rset.next()) {
        
        		BettingList b = new BettingList();
        		b.setBettingNo(rset.getInt("Betting_no"));
        		b.setBettingName(rset.getString("betting_title"));
        		b.setBettingEndTime(rset.getString("a"));
        		b.setTotalBettingMoney(rset.getInt("total_betting_money"));
        		b.setBettingType(rset.getString("betting_type"));
        		b.setTypeOfSports(rset.getString("type_of_sports"));
        		b.setHasResult(rset.getInt("hasresult"));
        		list.add(b);
        
        	}
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
        return list;
	}
	public int InsertBettingList(Connection conn, String admin_cometition, String gamecount, String game_time1) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBetting");
		
		try {
			//미완성 쿼리를 가지고 statement객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, game_time1);
			pstmt.setString(2,gamecount);
			pstmt.setString(3,admin_cometition);
			//미완성쿼리에 데이터 대입
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int InsertMatchList(Connection conn, String team1, String team2, int bettingno, String admin_cometition, String game_time1) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMatchList");
		try {
			//미완성 쿼리를 가지고 statement객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,team1);
			pstmt.setString(2,team2);
			pstmt.setString(3,game_time1);
			pstmt.setInt(4,bettingno);
			pstmt.setString(5, admin_cometition);
			//미완성쿼리에 데이터 대입
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public String bettingType(Connection conn, int bettingNo) {
		String betType="";
		
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getBettingType");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,bettingNo);
			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				betType = rset.getString("betting_type");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return betType;
	}


	public ArrayList<String> getSelectTeamName(Connection conn, int bettingNo) {
		ArrayList<String> TeamNameList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SelectTeamName");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bettingNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				 TeamNameList.add(rset.getString("HOME_TEAM_NAME"));
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return TeamNameList;
		
		
	}


	public Team getTeamList(Connection conn, String teamName) {
		Team t = new Team();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SelectTeamByTeamName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, teamName);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				t.setTeamNo(rset.getInt("Team_no"));
				t.setTeamName(rset.getString("Team_name"));
				t.setWin(rset.getInt("win"));
				t.setLose(rset.getInt("lose"));
				t.setDraw(rset.getInt("draw"));
				t.setType_of_sports(rset.getString("type_of_sports"));
				t.setImage_file(rset.getString("Imgae_file"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return t;
	}


	public ArrayList<String> getSelectTeamName2(Connection conn, int bettingNo) {
		ArrayList<String> TeamNameList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SelectTeamName2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bettingNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				 TeamNameList.add(rset.getString("AWAY_TEAM_NAME"));
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return TeamNameList;
		
		
	}


	public List<String> BringTeam(Connection conn,String type) {
		List<String> teamList = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("BringTeam");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, type);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				 teamList.add(rset.getString("TEAM_NAME"));
			}
			System.out.println("dao =" +teamList);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
			 
		return teamList;
	}


	public ArrayList<Integer> getMatchNoList(Connection conn, int bettingNo) {
		ArrayList<Integer> MatchNoList = new ArrayList<Integer>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SelectMatchNoList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bettingNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MatchNoList.add(rset.getInt("Match_No"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return MatchNoList;
	}


	public ArrayList<Match> getMatchList(Connection conn, int bettingNo) {
		ArrayList<Match> MatchList = new ArrayList<Match>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMatchList");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bettingNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Match m = new Match();
				m.setMatchNo(rset.getInt("match_no"));
				m.setHomeTeamName(rset.getString("home_team_name"));
				m.setAwayTeamName(rset.getString("away_team_name"));
				m.setMatchDate(rset.getDate("match_date"));
				m.setHomeTeamScore(rset.getInt("A_score"));
				m.setAwayTeamScore(rset.getInt("B_score"));
				m.setBettingNo(rset.getInt("Betting_No"));
				m.setTypeOfSports(rset.getString("type_of_sports"));
				MatchList.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return MatchList;
	}


	public int selectBettingCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectBettingCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
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


	public int InsertMatchResult(Connection conn, int match_no, int homescore, int awayscore) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("UpdateMatchResult");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, homescore);
			pstmt.setInt(2, awayscore);
			pstmt.setInt(3, match_no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int updateBettingList(Connection conn, int bettingNo) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("UpdateBettingList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bettingNo);
	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public String selectHomeTeamName(Connection conn,int match_no1) {
		String HomeTeamName = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectHomeTeamName");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, match_no1);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				HomeTeamName = rset.getString("HOME_TEAM_NAME");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);	
		}
		
		
		return HomeTeamName;
	}


	public String selectAwayTeamName(Connection conn, int match_no1) {
		String AWAYTeamName = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectHomeTeamName");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, match_no1);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				AWAYTeamName = rset.getString("AWAY_TEAM_NAME");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);	
		}
		
		return AWAYTeamName;
	}


	public int updateDrawScore(Connection conn, String teamName) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateDrawScore");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teamName);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updateWinScore(Connection conn, String teamName) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateWinScore");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teamName);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updateLoseScore(Connection conn, String teamName) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateLoseScore");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teamName);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	public int InsertBettingResultList(Connection conn, int bettingNo) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("InsertBettingResultList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bettingNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int InsertBettingResult(Connection conn, int betting_no, int bettingResult) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("InsertBettingResult");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, betting_no);
			pstmt.setInt(1, bettingResult);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int BettingOrder(Connection conn, int betting_no, int member_no, int money, int choice) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("InsertBettingOrder");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, betting_no);
			pstmt.setInt(2, member_no);
			pstmt.setInt(3, money);
			pstmt.setInt(4, choice);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int BettingMoneyInsert(Connection conn, int betting_no, int money) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("BettingMoneyInsert");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, betting_no);
			pstmt.setInt(1, money);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}


	public ArrayList<Integer> WhoGiveBettingMoney(Connection conn, int betting_no, int bettingResult) {
		ArrayList<Integer> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("WhoGiveBettingMoney");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bettingResult);
			pstmt.setInt(2, betting_no);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(rset.getInt("member_no2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public ArrayList<Integer> HowMuchBettingMoney(Connection conn, int betting_no, int bettingResult) {
		ArrayList<Integer> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("WhoGiveBettingMoney");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bettingResult);
			pstmt.setInt(2, betting_no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(rset.getInt("betting_money"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int getTotalBettingMoney(Connection conn, int betting_no) {
		int TotalMoney = 0;
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		String sql = prop.getProperty("getTotalBettingMoney");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, betting_no);
			
			rset= pstmt.executeQuery();
			while(rset.next()) {
				TotalMoney = rset.getInt("total_betting_money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return TotalMoney;
	}


	public ArrayList<Integer> WhoLoseBettingMoney(Connection conn, int betting_no, int bettingResult) {
		ArrayList<Integer> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("WhoLoseBettingMoney");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bettingResult);
			pstmt.setInt(2, betting_no);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(rset.getInt("member_no2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int updateBettingOrderRight(Connection conn, int betting_no, int member_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBettingOrderRight");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, betting_no);
			pstmt.setInt(2, member_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public int updateBettingOrderFalse(Connection conn, int betting_no, int member_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBettingOrderFalse");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, betting_no);
			pstmt.setInt(2, member_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int UseDoubleItem(Connection conn, String member_Id) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("UseDoubleItem");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_Id);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result= rset.getInt("usedouble");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public ArrayList<Integer> getBettingList(Connection conn, int member_no) {
		ArrayList<Integer> BettingListNo = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getBettingList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			
			rset= pstmt.executeQuery();
			while(rset.next()) {
				BettingListNo.add(rset.getInt("Betting_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
				
		return BettingListNo;
	}


	public int getBettingNo(Connection conn) {
		int BettingNO=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getBettingNo");
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset= pstmt.executeQuery();
			if(rset.next()) {
				BettingNO= rset.getInt("Betting_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
				
		return BettingNO;
		
	}


	public int updateBettingOrderMoney(Connection conn, int betting_no, int member_no, int money) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBettingOrderPoint");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setInt(2, betting_no);
			pstmt.setInt(3, member_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
