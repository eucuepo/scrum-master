package es.scmt.entities;

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

import es.scmt.entities.base.GenericEntity;

@Entity
@Table(name = "PROJECT")
@Component("projectEntity")
/**
 * Clase que extiende genericEntity con los atributos del proyecto
 * @author eucuepo
 *
 */
public class Project extends GenericEntity{

	private Long id;
	private String name;
	private String description;
	private List<Release> releaseList;

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
	@Column(name = "NAME", unique = true, nullable = false)
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

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID")
	public List<Release> getReleaseList() {
		return releaseList;
	}

	public void setReleaseList(List<Release> releaseList) {
		this.releaseList = releaseList;
	}
	
	public String toString(){
		return this.name;
	}
}
