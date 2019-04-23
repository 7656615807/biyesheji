package cn.lc.pojo;

import java.util.Date;
import javax.persistence.*;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice", catalog = "office")

public class Notice implements java.io.Serializable {

	// Fields

	private Integer snid;
	private User user;
	private String title;
	private Date pubdate;
	private String content;
	private Integer level;
	private Integer rnum;


	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** full constructor */
	public Notice(User user, String title, Date pubdate, String content, Integer level, Integer rnum) {
		this.user = user;
		this.title = title;
		this.pubdate = pubdate;
		this.content = content;
		this.level = level;
		this.rnum = rnum;

	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "snid", unique = true, nullable = false)

	public Integer getSnid() {
		return this.snid;
	}

	public void setSnid(Integer snid) {
		this.snid = snid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "title", length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "pubdate")
	@Temporal(TemporalType.DATE)
	public Date getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	@Column(name = "content", length = 65535)

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "level")

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "rnum")

	public Integer getRnum() {
		return this.rnum;
	}

	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}

}