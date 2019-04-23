package cn.lc.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tasktype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tasktype", catalog = "office")

public class Tasktype implements java.io.Serializable {

	// Fields

	private Integer ttid;
	private String title;
	private Set<Task> tasks = new HashSet<Task>(0);

	// Constructors

	/** default constructor */
	public Tasktype() {
	}

	/** minimal constructor */
	public Tasktype(String title) {
		this.title = title;
	}

	/** full constructor */
	public Tasktype(String title, Set<Task> tasks) {
		this.title = title;
		this.tasks = tasks;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "ttid", unique = true, nullable = false)

	public Integer getTtid() {
		return this.ttid;
	}

	public void setTtid(Integer ttid) {
		this.ttid = ttid;
	}

	@Column(name = "title", nullable = false, length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tasktype")

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}