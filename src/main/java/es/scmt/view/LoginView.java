package es.scmt.view;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.scmt.entities.User;
import es.scmt.services.LoginService;
import es.scmt.services.UserService;
import es.scmt.util.PasswordGenerator;
import es.scmt.util.RTDBError;

@Component("loginView")
@Scope("session")
public class LoginView implements Serializable {

	private static final Logger log = Logger.getLogger(LoginView.class);
	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	private LoginService loginService;
	private UserService userService;
	private User sessionUser;
	private String email;
	protected static final String SUCCESS = "success";
	protected static final String ERROR = "error";
	public LoginView() {
	}

	@Autowired
	public LoginView(LoginService loginService, UserService userService) {
		this.loginService = loginService;
		this.userService = userService;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public User getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void reset() {
		this.setUser("");
		this.setPassword("");
	}

	public void login() {
		if (getLoginService().login(getUser(), getPassword())) {
			sessionUser = getLoginService().getSessionUser();
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", getUser());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			handler.performNavigation("index");
		}
	}

	public void sendNewPassword() {
		try {
			// Buscamos el usuario
			List<User> list = loginService.findUserByEmail(email);
			if (list.isEmpty()) {
				String errorMessage = RTDBError.calcMsgError("msgLogin.emailNotExists", loginService.getLocaleUser());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
				log.error(errorMessage);
			} else {
				//Se actualiza la contraseña
				String password = PasswordGenerator.getPassword();
				User user = list.get(0);
				user.setPassword(password);
				userService.update(user);
				this.sendPassword(password);
			}

		} catch (Exception e) {
			String errorMessage = RTDBError.calcMsgError("msgLogin.emailNotSent", loginService.getLocaleUser());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
			log.error(errorMessage);
		}
	}

	private void sendPassword(String password) throws MessagingException, AddressException {
		//TODO: Ver como hacer funcionar esto en gmail
		// Propiedades de la conexión
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.google.es");
		props.put("mail.transport.protocol", "smtp");
		props.setProperty("mail.user", email);

		// Preparamos la sesion
		Session session = Session.getDefaultInstance(props);

		// Construimos el mensaje
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("eucuepo@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		message.setSubject("ScrumMaster: Nueva contraseña");
		String msg = "Su nueva contraseña de acceso para ScrumMaster es:\n".concat(password);
		message.setText(msg);

		// Lo enviamos.
		Transport.send(message);
		String successMessage = RTDBError.calcMsgError("msgLogin.emailSent", loginService.getLocaleUser());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, successMessage, successMessage));
	}

}
