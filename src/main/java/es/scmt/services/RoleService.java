package es.scmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.scmt.dao.base.GenericDAO;
import es.scmt.entities.Role;
import es.scmt.services.base.GenericService;

@Service("roleService")
@Qualifier("roleService")
public class RoleService extends GenericService<Role> {
	@Autowired
	public RoleService(@Qualifier("roleDAO") GenericDAO<Role, Long> dao) {
		super(dao);
	}

}
