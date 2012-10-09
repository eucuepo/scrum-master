package es.scmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "TASK")
@Component("taskEntity")
public class Task {

	private Long id;
	private String name;
	private String description;
	private int order;
	private int estimatedHours;
	private int realHours;

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

	@Column(name = "DESCRIPTION", unique = false, nullable = false)
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

	@Column(name = "ESTIMATED_HOURS", unique = false, nullable = true)
	public int getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(int predictedHours) {
		this.estimatedHours = predictedHours;
	}

	@Column(name = "REAL_HOURS", unique = false, nullable = true)
	public int getRealHours() {
		return realHours;
	}

	public void setRealHours(int realHours) {
		this.realHours = realHours;
	}
	
	

}
