package es.scmt.services;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import es.scmt.entities.User;
import es.scmt.services.base.GenericLoginService;
import es.scmt.util.RTDBError;

@Service("loginService")
public class LoginService implements GenericLoginService {

	/** Instancia del logger para mostrar los mensajes */
	protected static final Logger LOG = Logger.getLogger(LoginService.class.getName());

	private User sessionUser;
	private Locale localeUser;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	// Bean Personalizado del Manejador de Autentificación
	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager;

	public User getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean login(String entryUser, String password) {
		FacesContext context = FacesContext.getCurrentInstance();

		boolean validacionOK = true;

		if (entryUser == null || password == null) {
			validacionOK = false;
		}

		if (validacionOK && entryUser.isEmpty()) {
			validacionOK = false;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, RTDBError.calcMsgError("loginService.userEmpty", null), RTDBError.calcMsgError("loginService.userEmpty", null));
			context.addMessage(null, msg);
		}

		if (validacionOK && password.isEmpty()) {
			validacionOK = false;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, RTDBError.calcMsgError("loginService.passwordEmpty", null), RTDBError.calcMsgError("loginService.passwordEmpty", null));
			context.addMessage(null, msg);
		}

		if (validacionOK) {
			User selectUser = null;
			List<User> userlist = userService.findAllByProperty("login", entryUser);
			for (User u : userlist) {
				if (u.getLogin().equals(entryUser)) {
					selectUser = u;
				}
			}

			if (selectUser == null) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, RTDBError.calcMsgError("loginService.noExistUser", null), RTDBError.calcMsgError("loginService.noExistUser", null));
				context.addMessage(null, msg);
				validacionOK = false;
			}


			if (validacionOK) {
				try {
					Authentication request = new UsernamePasswordAuthenticationToken(entryUser, password);
					Authentication result = authenticationManager.authenticate(request);
					SecurityContextHolder.getContext().setAuthentication(result);

					sessionUser = selectUser;
					if (selectUser.getLanguage().equals("S")) {
						localeUser = new Locale("es", "ES");
					} else if (selectUser.getLanguage().equals("E")) {
						localeUser = new Locale("en", "EN");
					} else {
						localeUser = new Locale("fr", "FR");
					}
					validacionOK = true;

				} catch (AuthenticationException ex) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, RTDBError.calcMsgError("loginService.incorrectPassword", null), RTDBError.calcMsgError(
							"loginService.incorrectPassword", null));
					context.addMessage(null, msg);
					validacionOK = false;
				}
			}

		}
		return validacionOK;
	}

	public final void setLocaleUser(Locale localeUser) {
		this.localeUser = localeUser;
	}

	public final Locale getLocaleUser() {
		return this.localeUser;
	}
	
	public List<User> findUserByEmail(String email){
		return userService.findUserByEmail(email);
	}
}
