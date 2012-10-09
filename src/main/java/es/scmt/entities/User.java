package es.scmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import es.scmt.entities.base.GenericEntity;

@Entity
@Table(name = "USER")
@Component("userEntity")
public class User extends GenericEntity {

	private static final int MINLOG = 5;

	private Long id;
	private String login;
	private String password;
	private String name;
	private String language;
	private Role role;
	private String email;

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
	 * @return the login
	 */
	@Column(name = "LOGIN", unique = true, nullable = false)
	@Size(min = MINLOG, message = "Login tiene debe ser como mínimo de longitud 8")
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	@Column(name = "PASSWORD", unique = false, nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * @return the idioma
	 */
	@Column(name = "LANGUAGE", unique = false, length = 1)
	public String getLanguage() {
		return language;
	}

	/**
	 * @param idioma
	 *            the idioma to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the email
	 */
	@Column(name = "EMAIL", unique = true)
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the rol
	 */
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ROLE", referencedColumnName = "ID") })
	public Role getRole() {
		return role;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}