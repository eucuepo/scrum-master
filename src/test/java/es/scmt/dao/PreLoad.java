package es.scmt.dao;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.scmt.dao.base.GenericDAO;
import es.scmt.entities.Role;
import es.scmt.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContextTest.xml", "classpath*:spring-securityTest.xml" })
public class PreLoad extends TestCase {

	private GenericDAO<User, Long> userDao;
	private GenericDAO<Role, Long> roleDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void Preload(@Qualifier("userDAO") GenericDAO<User, Long> userDao, @Qualifier("userDAO") GenericDAO<Role, Long> roleDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Este test no debe ejecutarse en maven, es para precargar la aplicacion
	 * para que rule
	 **/
	@Test
	public void preLoad() {
		Role role = new Role();
		role.setName("INTERNO");
		role.setDescription("Usuario interno");
		roleDao.save(role);

		User user = new User();
		user.setName("Eugenio");
		user.setLogin("admin");
		user.setPassword(passwordEncoder.encodePassword("pinbul", "admin"));
		userDao.save(user);
	}
}
