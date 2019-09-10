package point.model.vo;

public class MemberUseItem {
	private	String memberId;
	private String background;
	private String badge;
	public MemberUseItem() {
		
	}
	public MemberUseItem(String memberId, String background, String badge) {
		this.memberId = memberId;
		this.background = background;
		this.badge = badge;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	@Override
	public String toString() {
		return "MemberUseItem [memberId=" + memberId + ", background=" + background + ", badge=" + badge + "]";
	}
	
}
