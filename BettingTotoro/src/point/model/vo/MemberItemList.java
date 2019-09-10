package point.model.vo;

import java.sql.Date;

public class MemberItemList {
	private int purchaseNo;
	private String memberId;
	private String itemName;
	private int itemEA;
	private Date purchaseDate;
	private int itemPrice;
	public MemberItemList() {
		
	}
	
	public MemberItemList(int purchaseNo, String memberId, String itemName, int itemEA, Date purchaseDate,
			int itemPrice) {
		this.purchaseNo = purchaseNo;
		this.memberId = memberId;
		this.itemName = itemName;
		this.itemEA = itemEA;
		this.purchaseDate = purchaseDate;
		this.itemPrice = itemPrice;
	}

	public int getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemEA() {
		return itemEA;
	}
	public void setItemEA(int itemEA) {
		this.itemEA = itemEA;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "MemberItemList [purchaseNo=" + purchaseNo + ", memberId=" + memberId + ", itemName=" + itemName
				+ ", itemEA=" + itemEA + ", purchaseDate=" + purchaseDate + ", itemPrice=" + itemPrice + "]";
	}

}
