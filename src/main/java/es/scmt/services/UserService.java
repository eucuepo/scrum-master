package es.scmt.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.scmt.dao.base.GenericDAO;
import es.scmt.entities.User;
import es.scmt.services.base.GenericService;

@Service("userService")
@Qualifier("userService")
public class UserService extends GenericService<User> {
	@Autowired
	public UserService(@Qualifier("userDAO") GenericDAO<User, Long> dao) {
		super(dao);
	}
	
	
	public List<User> findUserByEmail(String email){
		return super.findAllByProperty("email", email);
	}


}