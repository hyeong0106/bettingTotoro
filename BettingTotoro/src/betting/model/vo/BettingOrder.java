package betting.model.vo;

public class BettingOrder {
	private int BettingOrderNo;
	private int BettingNo;
	private int MemberNo;
	private int BettingMoney;
	private int choice;
	private String BettingDate;
	private int pass;
	
	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public BettingOrder(int bettingOrderNo, int bettingNo, int memberNo, int bettingMoney, int choice,
			String bettingDate, int pass) {
		BettingOrderNo = bettingOrderNo;
		BettingNo = bettingNo;
		MemberNo = memberNo;
		BettingMoney = bettingMoney;
		this.choice = choice;
		BettingDate = bettingDate;
		this.pass = pass;
	}


	public BettingOrder() {
	}


	public int getBettingOrderNo() {
		return BettingOrderNo;
	}
	public void setBettingOrderNo(int bettingOrderNo) {
		BettingOrderNo = bettingOrderNo;
	}
	public int getBettingNo() {
		return BettingNo;
	}
	public void setBettingNo(int bettingNo) {
		BettingNo = bettingNo;
	}
	public int getMemberNo() {
		return MemberNo;
	}
	public void setMemberNo(int memberNo) {
		MemberNo = memberNo;
	}
	public int getBettingMoney() {
		return BettingMoney;
	}
	public void setBettingMoney(int bettingMoney) {
		BettingMoney = bettingMoney;
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}

	public String getBettingDate() {
		return BettingDate;
	}

	public void setBettingDate(String bettingDate) {
		BettingDate = bettingDate;
	}
	
}
