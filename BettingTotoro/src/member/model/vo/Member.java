package member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String memberId;
	private String password;
	private String memberName;
	private String phone;
	private String gender;
	private String email;
	private String hint;
	private int point;
	private int score;
	private Date enrollDate;
		
	public Member() {
	
	}

	public Member(int memberNo, String memberId, String password, String memberName, String phone, String gender,
			String email, String hint, int point, int score, Date enrollDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.phone = phone;
		this.gender = gender;
		this.email = email;
		this.hint = hint;
		this.point = point;
		this.score = score;
		this.enrollDate = enrollDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
}
