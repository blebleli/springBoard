package board.write.model;

import java.util.Date;

public class WriteVo {

	int w_id         ;
	int w_parent     ;
	String std_id    ;
	int b_id         ;
	String w_title   ;
	String w_content ;
	Date w_regdt     ;
	String w_delny   ;
	int w_view       ;
	int w_filenum    ;
	int numview		 ;
	String titleview ;
	



	public int getW_id() {
		return w_id;
	}




	public void setW_id(int w_id) {
		this.w_id = w_id;
	}




	public int getW_parent() {
		return w_parent;
	}




	public void setW_parent(int w_parent) {
		this.w_parent = w_parent;
	}




	public String getStd_id() {
		return std_id;
	}




	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}




	public int getB_id() {
		return b_id;
	}




	public void setB_id(int b_id) {
		this.b_id = b_id;
	}




	public String getW_title() {
		return w_title;
	}




	public void setW_title(String w_title) {
		this.w_title = w_title;
	}




	public String getW_content() {
		return w_content;
	}




	public void setW_content(String w_content) {
		this.w_content = w_content;
	}




	public Date getW_regdt() {
		return w_regdt;
	}




	public void setW_regdt(Date w_regdt) {
		this.w_regdt = w_regdt;
	}




	public String getW_delny() {
		return w_delny;
	}




	public void setW_delny(String w_delny) {
		this.w_delny = w_delny;
	}




	public int getW_view() {
		return w_view;
	}




	public void setW_view(int w_view) {
		this.w_view = w_view;
	}




	public int getW_filenum() {
		return w_filenum;
	}




	public void setW_filenum(int w_filenum) {
		this.w_filenum = w_filenum;
	}




	public int getNumview() {
		return numview;
	}




	public void setNumview(int numview) {
		this.numview = numview;
	}




	public String getTitleview() {
		return titleview;
	}




	public void setTitleview(String titleview) {
		this.titleview = titleview;
	}




	@Override
	public String toString() {
		return "WriteVo [w_id=" + w_id + ", w_parent=" + w_parent + ", std_id="
				+ std_id + ", b_id=" + b_id + ", w_title=" + w_title
				+ ", w_content=" + w_content + ", w_regdt=" + w_regdt
				+ ", w_delny=" + w_delny + ", w_view=" + w_view
				+ ", w_filenum=" + w_filenum + "]";
	}
	
}
