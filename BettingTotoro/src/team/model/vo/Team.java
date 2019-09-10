package team.model.vo;

public class Team {
	private int TeamNo;
	private String TeamName;
	private int win;
	private int lose;
	private int draw;
	private String type_of_sports;
	private String image_file;
	
	
	public Team() {
	}
	public Team(int teamNo, String teamName, int win, int lose, int draw, String type_of_sports, String image_file) {
		TeamNo = teamNo;
		TeamName = teamName;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
		this.type_of_sports = type_of_sports;
		this.image_file = image_file;
	}

	public int getTeamNo() {
		return TeamNo;
	}
	public void setTeamNo(int teamNo) {
		TeamNo = teamNo;
	}
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public String getType_of_sports() {
		return type_of_sports;
	}
	public void setType_of_sports(String type_of_sports) {
		this.type_of_sports = type_of_sports;
	}
	public String getImage_file() {
		return image_file;
	}
	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}
	
}

