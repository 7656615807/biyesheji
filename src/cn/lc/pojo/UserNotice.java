package cn.lc.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserNotice entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class UserNotice implements java.io.Serializable {

	// Fields

	private Integer snid;
	private String userid;
	private Date rdate;

	// Constructors

	/** default constructor */
	public UserNotice() {
	}

	/** full constructor */
	public UserNotice(Integer snid, String userid, Date rdate) {
		this.snid = snid;
		this.userid = userid;
		this.rdate = rdate;
	}

	// Property accessors

	@Column(name = "snid")

	public Integer getSnid() {
		return this.snid;
	}

	public void setSnid(Integer snid) {
		this.snid = snid;
	}

	@Column(name = "userid", length = 50)

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "rdate", length = 19)
	@Temporal(TemporalType.DATE)
	public Date getRdate() {
		return this.rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserNotice))
			return false;
		UserNotice castOther = (UserNotice) other;

		return ((this.getSnid() == castOther.getSnid()) || (this.getSnid() != null && castOther.getSnid() != null
				&& this.getSnid().equals(castOther.getSnid())))
				&& ((this.getUserid() == castOther.getUserid()) || (this.getUserid() != null
						&& castOther.getUserid() != null && this.getUserid().equals(castOther.getUserid())))
				&& ((this.getRdate() == castOther.getRdate()) || (this.getRdate() != null
						&& castOther.getRdate() != null && this.getRdate().equals(castOther.getRdate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSnid() == null ? 0 : this.getSnid().hashCode());
		result = 37 * result + (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result + (getRdate() == null ? 0 : this.getRdate().hashCode());
		return result;
	}

}