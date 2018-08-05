package board.student.model;

import java.util.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentVo implements HttpSessionBindingListener{


	private Logger logger = LoggerFactory.getLogger(StudentVo.class);

	private String std_id;	//아이디
	private String pass;	//비번
	private int num;			//학생번호
	private String name;	//학생이름
	private Date reg_dt;	//등록일자
	private String addr1;	//주소
	private String addr2;	//상세주소
	private String zipcd;	//우편번호
	private String pic;		//파일이름
	private String picpath;	//파일경로
	private String picname;	//파일이름






	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getStd_id() {
		return std_id;
	}

	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcd() {
		return zipcd;
	}

	public void setZipcd(String zipcd) {
		this.zipcd = zipcd;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}


	@Override
	public String toString() {
		return "StudentVo ["			
				+ (std_id != null ? "std_id=" + std_id + ", " : "")
				+ (pass != null ? "pass=" + pass + ", " : "") + "num=" + num
				+ ", " + (name != null ? "name=" + name + ", " : "")
				+ (reg_dt != null ? "reg_dt=" + reg_dt + ", " : "")
				+ (addr1 != null ? "addr1=" + addr1 + ", " : "")
				+ (addr2 != null ? "addr2=" + addr2 + ", " : "")
				+ (zipcd != null ? "zipcd=" + zipcd + ", " : "")
				+ (pic != null ? "pic=" + pic + ", " : "")
				+ (picpath != null ? "picpath=" + picpath + ", " : "")
				+ (picname != null ? "picname=" + picname : "") + "]";
	}

	
	public boolean validataUser(String std_id, String password){
		if (this.getStd_id().equals(std_id)&&this.getPass().equals(password))
			return true;
		else
			return false;
	}
	
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		//session객체에서 session.setAttribute("userVo",userVo_가 호출 되었을때
		logger.debug("class StudentVo valueBound : "+event.getName());

	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		//session객체에서 session.removeAttribute("userVo",userVo_가 호출 되었을때
		logger.debug("class StudentVo valueUnBound : "+event.getName());
	}

}
