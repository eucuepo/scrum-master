package es.scmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import es.scmt.entities.base.GenericEntity;

@Entity
@Table(name = "RTDB_ROLE")
@Component("roleEntity")
public class Role extends GenericEntity {

	private Long id;
	private String name;
	private String description;

	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	@Id
	@Primary
	@GeneratedValue
	@Column(name = "ID", nullable = false)
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
	 * Get Rol Name
	 * 
	 * @return String - Rol Name
	 */
	@Column(name = "NAME", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * Set Rol Name
	 * 
	 * @param String
	 *            - Rol Name
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

}
