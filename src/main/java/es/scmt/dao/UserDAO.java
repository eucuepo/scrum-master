package es.scmt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.scmt.dao.base.GenericDAOHibernate4;
import es.scmt.entities.User;

@Component
@Transactional
@Repository
@Qualifier("userDAO")
public class UserDAO extends GenericDAOHibernate4<User, Long> {

	@Autowired
	public UserDAO(SessionFactory factory) {
		super(factory);
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
	
}
