package es.scmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import es.scmt.entities.base.GenericEntity;

@Entity
@Table(name="RTDB_USERTYPE")
@Component("userTypeEntity")
public class UserType extends GenericEntity{

	private Long id;
	private String name;
	private String description;
	
	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
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
	 * Get Nombre
	 * 
	 * @return String - Nombre
	 */
	@Column(name="NAME", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	
	/**
	 * Set Nombre
	 * 
	 * @param String - Nombre
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get descripcion
	 * 
	 * @return String - Nombre
	 */
	@Column(name="DESCRIPTION", unique = true, nullable = false)
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set descripcion
	 * 
	 * @param String - User Surname
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
}
