package board.board.model;

import java.util.Date;

public class BoardVo {
	int b_id    ;
	String b_name  ;
	Date b_regdt   ;
	String b_useny ;
	String std_id  ;
	
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public Date getB_regdt() {
		return b_regdt;
	}
	public void setB_regdt(Date b_regdt) {
		this.b_regdt = b_regdt;
	}
	public String getB_useny() {
		return b_useny;
	}
	public void setB_useny(String b_useny) {
		this.b_useny = b_useny;
	}
	public String getStd_id() {
		return std_id;
	}
	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}
	
	@Override
	public String toString() {
		return "BoardVo [b_id=" + b_id + ", "
				+ (b_name != null ? "b_name=" + b_name + ", " : "")
				+ (b_regdt != null ? "b_regdt=" + b_regdt + ", " : "")
				+ (b_useny != null ? "b_useny=" + b_useny + ", " : "")
				+ (std_id != null ? "std_id=" + std_id : "") + "]";
	}
	
	
}
