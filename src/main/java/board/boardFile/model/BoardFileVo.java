package board.boardFile.model;


public class BoardFileVo {
	int f_id      ;
	int w_id      ;
	String std_id ;
	String f_file ;
	String f_path ;
	String f_name ;
	
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
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
	public String getF_file() {
		return f_file;
	}
	public void setF_file(String f_file) {
		this.f_file = f_file;
	}
	public String getF_path() {
		return f_path;
	}
	public void setF_path(String f_path) {
		this.f_path = f_path;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	
	@Override
	public String toString() {
		return "BoardFileVo [f_id=" + f_id + ", w_id=" + w_id + ", std_id="
				+ std_id + ", f_file=" + f_file + ", f_path=" + f_path
				+ ", f_name=" + f_name + "]";
	}
	

	
}
