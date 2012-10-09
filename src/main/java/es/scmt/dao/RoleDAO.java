package es.scmt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.scmt.dao.base.GenericDAOHibernate4;
import es.scmt.entities.Role;

@Component
@Transactional
@Repository
@Qualifier("roleDAO")
public class RoleDAO extends GenericDAOHibernate4<Role, Long> {

	@Autowired
	public RoleDAO(SessionFactory factory) {
		super(factory);
	}

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}
	
}
