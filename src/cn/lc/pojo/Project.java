package cn.lc.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project", catalog = "office")

public class Project implements java.io.Serializable {

	// Fields

	private Integer proid;
	private User userByMgr;
	private User userByCreid;
	private String name;
	private String title;
	private String note;
	private Date pubdate;
	private Set<Task> tasks = new HashSet<Task>(0);

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(User userByMgr, User userByCreid, String name, String title, String note, Date pubdate,
			Set<Task> tasks) {
		this.userByMgr = userByMgr;
		this.userByCreid = userByCreid;
		this.name = name;
		this.title = title;
		this.note = note;
		this.pubdate = pubdate;
		this.tasks = tasks;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "proid", unique = true, nullable = false)

	public Integer getProid() {
		return this.proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mgr")

	public User getUserByMgr() {
		return this.userByMgr;
	}

	public void setUserByMgr(User userByMgr) {
		this.userByMgr = userByMgr;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creid")

	public User getUserByCreid() {
		return this.userByCreid;
	}

	public void setUserByCreid(User userByCreid) {
		this.userByCreid = userByCreid;
	}

	@Column(name = "name", length = 50)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "title", length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "note", length = 65535)

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "pubdate", length = 19)
	@Temporal(TemporalType.DATE)
	public Date getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}