package es.scmt.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "SPRINT")
@Component("sprintEntity")
public class Sprint {

	private Long id;
	private String name;
	private String description;
	private int order;
	private Date startDate;
	private Date finishDate;
	private List<Task> taskList;
	

	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	@Primary
	public Long getId() {
		return id;
	}

	/**
	 * Set User Id
	 * 
	 * @param int - User Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	@Column(name = "NAME", unique = false, nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "LIST_ORDER", unique = false, nullable = true)
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "ID_SPRINT", referencedColumnName = "ID")
	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
	@Column(name = "START_DATE", unique = false, nullable = true)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name = "FINISH_DATE", unique = false, nullable = true)
	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String toString(){
		return this.name;
	}
}
