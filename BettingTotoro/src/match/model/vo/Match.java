package match.model.vo;

import java.sql.Date;

public class Match {
	private int matchNo;
	private String HomeTeamName;
	private String AwayTeamName;
	private Date MatchDate;
	private int HomeTeamScore;
	private int AwayTeamScore;
	private int BettingNo;
	private String TypeOfSports;
	
	
	public Match() {
	}
	
	public Match(int matchNo, String homeTeamName, String awayTeamName, Date matchDate, int homeTeamScore,
			int awayTeamScore, int bettingNo, String typeOfSports) {
		super();
		this.matchNo = matchNo;
		HomeTeamName = homeTeamName;
		AwayTeamName = awayTeamName;
		MatchDate = matchDate;
		HomeTeamScore = homeTeamScore;
		AwayTeamScore = awayTeamScore;
		BettingNo = bettingNo;
		TypeOfSports = typeOfSports;
	}



	public int getMatchNo() {
		return matchNo;
	}
	public void setMatchNo(int matchNo) {
		this.matchNo = matchNo;
	}
	public String getHomeTeamName() {
		return HomeTeamName;
	}
	public void setHomeTeamName(String homeTeamName) {
		HomeTeamName = homeTeamName;
	}
	public String getAwayTeamName() {
		return AwayTeamName;
	}
	public void setAwayTeamName(String awayTeamName) {
		AwayTeamName = awayTeamName;
	}
	public Date getMatchDate() {
		return MatchDate;
	}
	public void setMatchDate(Date matchDate) {
		MatchDate = matchDate;
	}
	public int getHomeTeamScore() {
		return HomeTeamScore;
	}
	public void setHomeTeamScore(int homeTeamScore) {
		HomeTeamScore = homeTeamScore;
	}
	public int getAwayTeamScore() {
		return AwayTeamScore;
	}
	public void setAwayTeamScore(int awayTeamScore) {
		AwayTeamScore = awayTeamScore;
	}
	public int getBettingNo() {
		return BettingNo;
	}
	public void setBettingNo(int bettingNo) {
		BettingNo = bettingNo;
	}
	
	public String getTypeOfSports() {
		return TypeOfSports;
	}

	public void setTypeOfSports(String typeOfSports) {
		TypeOfSports = typeOfSports;
	}

}
