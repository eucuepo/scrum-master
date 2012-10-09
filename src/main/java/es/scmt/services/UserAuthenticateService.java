package es.scmt.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.scmt.entities.User;

@Service("userAuthenticateService")
@Qualifier("userAuthenticateService")
public class UserAuthenticateService implements UserDetailsService {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		User userEntity = userService.findAllByProperty("login", username).get(0);
		if (userEntity == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return buildUserFromUserEntity(userEntity);
	}

	org.springframework.security.core.userdetails.User buildUserFromUserEntity(es.scmt.entities.User userEntity) {

		String username = userEntity.getLogin();
		String password = userEntity.getPassword();
		boolean enabled = false;
		boolean accountNonExpired = false;
		boolean credentialsNonExpired = false;
		boolean accountNonLocked = false;
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		enabled = true;
		accountNonExpired = true;
		credentialsNonExpired = true;
		accountNonLocked = true;

		authorities.add(new GrantedAuthorityImpl(userEntity.getRole().getName()));

		return new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}
