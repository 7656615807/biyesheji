package cn.lc.pojo;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

/**
 * Document entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "document", catalog = "office")

public class Document implements java.io.Serializable {

	// Fields

	private Integer did;
	private Doctype doctype;
	private User user;
	private String title;
	private String content;
	private String file;
	private Date pubdate;

	// Constructors

	/** default constructor */
	public Document() {
	}

	/** minimal constructor */
	public Document(String title) {
		this.title = title;
	}

	/** full constructor */
	public Document(Doctype doctype, User user, String title, String content, String file, Date pubdate) {
		this.doctype = doctype;
		this.user = user;
		this.title = title;
		this.content = content;
		this.file = file;
		this.pubdate = pubdate;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "did", unique = true, nullable = false)

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dtid")

	public Doctype getDoctype() {
		return this.doctype;
	}

	public void setDoctype(Doctype doctype) {
		this.doctype = doctype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uerid")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "title", nullable = false, length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "file", length = 200)

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}


	@Column(name = "pubdate")
	@Temporal(TemporalType.DATE)
	public Date getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

}