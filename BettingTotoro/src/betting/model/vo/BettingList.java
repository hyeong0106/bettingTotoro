package betting.model.vo;

import java.sql.Date;

public class BettingList {
	private int BettingNo;
	private String BettingName;
	private String BettingEndTime;
	private int TotalBettingMoney;
	private String BettingType;
	private String TypeOfSports;
	private int HasResult;
	public BettingList() {
	}
	
	

	public BettingList(int bettingNo, String bettingName, String bettingEndTime, int totalBettingMoney,
			String bettingType, String typeOfSports, int hasResult) {
		BettingNo = bettingNo;
		BettingName = bettingName;
		BettingEndTime = bettingEndTime;
		TotalBettingMoney = totalBettingMoney;
		BettingType = bettingType;
		TypeOfSports = typeOfSports;
		HasResult = hasResult;
	}



	public int getHasResult() {
		return HasResult;
	}



	public void setHasResult(int hasResult) {
		HasResult = hasResult;
	}



	public int getBettingNo() {
		return BettingNo;
	}
	public void setBettingNo(int bettingNo) {
		BettingNo = bettingNo;
	}
	public String getBettingName() {
		return BettingName;
	}
	public void setBettingName(String bettingName) {
		BettingName = bettingName;
	}
	public String getBettingEndTime() {
		return BettingEndTime;
	}
	public void setBettingEndTime(String bettingEndTime) {
		BettingEndTime = bettingEndTime;
	}
	public int getTotalBettingMoney() {
		return TotalBettingMoney;
	}
	public void setTotalBettingMoney(int totalBettingMoney) {
		TotalBettingMoney = totalBettingMoney;
	}
	public String getBettingType() {
		return BettingType;
	}
	public void setBettingType(String bettingType) {
		BettingType = bettingType;
	}
	public String getTypeOfSports() {
		return TypeOfSports;
	}

	public void setTypeOfSports(String typeOfSports) {
		TypeOfSports = typeOfSports;
	}
	
}
