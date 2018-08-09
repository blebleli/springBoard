package board.comment.model;

import java.util.Date;

public class CommentVo {
	
	int c_id	    ;
	int w_id	    ;
	String std_id	;
	String c_cmt	;
	Date c_regdt	;
	String c_delny	;
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getW_id() {
		return w_id;
	}
	public void setW_id(int w_id) {
		this.w_id = w_id;
	}
	public String getStd_id() {
		return std_id;
	}
	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}
	public String getC_cmt() {
		return c_cmt;
	}
	public void setC_cmt(String c_cmt) {
		this.c_cmt = c_cmt;
	}
	public Date getC_regdt() {
		return c_regdt;
	}
	public void setC_regdt(Date c_regdt) {
		this.c_regdt = c_regdt;
	}
	public String getC_delny() {
		return c_delny;
	}
	public void setC_delny(String c_delny) {
		this.c_delny = c_delny;
	}
	
	@Override
	public String toString() {
		return "CommentVo [c_id=" + c_id + ", w_id=" + w_id + ", std_id="
				+ std_id + ", c_cmt=" + c_cmt + ", c_regdt=" + c_regdt
				+ ", c_delny=" + c_delny + "]";
	}
	
}
