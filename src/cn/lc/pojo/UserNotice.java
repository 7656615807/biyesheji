package cn.lc.pojo;


import java.util.Date;

/**
 * UserNotice entity. @author MyEclipse Persistence Tools
 */

public class UserNotice implements java.io.Serializable {

	// Fields

	private String userid;
	private int snid;
	private Date rdate;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getSnid() {
		return snid;
	}

	public void setSnid(int snid) {
		this.snid = snid;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
}