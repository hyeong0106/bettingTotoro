package betting.model.vo;

public class BettingResult {
	private int bettingNo;
	private int bettingMoney;
	private int result;
	
	public BettingResult() {
	}
	public BettingResult(int bettingNo, int bettingMoney, int result) {
		this.bettingNo = bettingNo;
		this.bettingMoney = bettingMoney;
		this.result = result;
	}

	public int getBettingNo() {
		return bettingNo;
	}
	public void setBettingNo(int bettingNo) {
		this.bettingNo = bettingNo;
	}
	public int getBettingMoney() {
		return bettingMoney;
	}
	public void setBettingMoney(int bettingMoney) {
		this.bettingMoney = bettingMoney;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
}
