package es.scmt.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.util.ComponentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

import es.scmt.entities.GenericLazyDataModel;
import es.scmt.entities.Role;
import es.scmt.entities.User;
import es.scmt.services.base.GenericService;
import es.scmt.util.RTDBError;
import es.scmt.view.base.GenericView;

@Component("userView")
@Scope("application")
public class UserView extends GenericView<User> implements Serializable {

	private static final long serialVersionUID = 8983474434608725243L;

	private String login;
	private String password;
	private String passwordConf;
	private String passwordMod;
	private String passwordModConf;
	private String language;
	private Role role;
	private String searchRole;
	private String email;
	
	private Map<String, String> types = new HashMap<String, String>();
	private Map<String, String> languages = new HashMap<String, String>();

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserView(@Qualifier("userService") GenericService<User> userService, PasswordEncoder passwordEncoder) {
		super(userService, new GenericLazyDataModel<User>(userService));
		// Inicialización de los tipos
		types.put("Cliente", "C");
		types.put("Partner", "P");
		types.put("Interno", "I");

		// Inicialización de los idiomas
		languages.put("Español", "S");
		languages.put("Inglés", "E");
		languages.put("Francés", "F");

		this.passwordEncoder = passwordEncoder;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConf() {
		return passwordConf;
	}

	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Map<String, String> getTypes() {
		return types;
	}

	public void setTypes(Map<String, String> types) {
		this.types = types;
	}

	public Map<String, String> getLanguages() {
		return languages;
	}

	public void setLanguages(Map<String, String> languages) {
		this.languages = languages;
	}

	public String getSearchRole() {
		return searchRole;
	}

	public void setSearchRole(String searchRole) {
		this.searchRole = searchRole;
	}

	public String getPasswordMod() {
		return passwordMod;
	}

	public void setPasswordMod(String passwordMod) {
		this.passwordMod = passwordMod;
	}

	public String getPasswordModConf() {
		return passwordModConf;
	}

	public void setPasswordModConf(String passwordModConf) {
		this.passwordModConf = passwordModConf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getUsersByName(String name) {
		List<User> foundUsers = service.findByName(name);
		List<String> names = new ArrayList<String>();

		for (User m : foundUsers) {
			names.add(m.getName());
		}

		return names;
	}

	public void search(ActionEvent actionEvent) {
		HashMap<String, String> filter = new HashMap<String, String>();

		filter.put("login", getLogin());
		filter.put("name", getName());
		filter.put("language", String.valueOf(getLanguage()));

		if (getRole() != null && !getSearchRole().isEmpty() && !getRole().getName().isEmpty()) {
			getEntities().getEntityFilters().put("role", getRole());
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();
		DataTable table = (DataTable) ComponentUtils.findComponent(facesContext.getViewRoot(), "users");
		table.setFilters(filter);
	}

	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	public String addUser() {
		try {
			if (getPassword().equals(getPasswordConf())) {
				User user = new User();
				user.setLogin(getLogin());
				user.setPassword(passwordEncoder.encodePassword(getPassword(), user.getLogin()));
				user.setName(getName());
				user.setEmail(getEmail());
				user.setLanguage(getLanguage());
				user.setRole(getRole());
				service.insert(user);
			} else {
				String errorMessage = RTDBError.calcMsgError("msgUser.passwordNotMatch", getLocale());
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return ERROR;
			}

		} catch (DuplicateKeyException e) {
			String errorMessage = RTDBError.calcMsgError("addUser.duplicateEntity", getLocale());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return ERROR;

		} catch (DataAccessException e) {
			String errorMessage = RTDBError.calcMsgError("addUser.duplicateEntity", getLocale());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return ERROR;
		}
		reset();
		return SUCCESS;

	}

	@Override
	public String updateEntity() {
		String res = super.updateEntity();
		reset();
		return res;
	}
	
	//FIXME: Cambiar a scope session y sobreescribir este metodo
	public void onRowDblselect(SelectEvent event) {
		viewEntity();
	}

	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setId(0L);
		this.setLogin("");
		this.setPassword("");
		this.setName("");
		this.setLanguage("");
		this.setRole(null);
		this.setSearchRole("");
	}

	// Validación de los Passwords
	public void comparePassword(FacesContext context, UIComponent component, Object value) {
		// Obtenemos el valor actual de la comparación del password
		Object o = ((UIInput) component).getSubmittedValue();
		if (!password.equals(o.toString())) {
			String errorMessage = RTDBError.calcMsgError("msgUser.passwordNotMatch", getLocale());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			context.addMessage(null, msg);
		}

	}

	// Validación de los Passwords
	public void comparePasswordModal(FacesContext context, UIComponent component, Object value) {
		boolean error = false;
		// Obtenemos el valor actual de la comparación del password
		Object o = ((UIInput) component).getSubmittedValue();
		if (!passwordMod.equals(o.toString())) {
			String errorMessage = RTDBError.calcMsgError("msgUser.passwordNotMatch", getLocale());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			context.addMessage(null, msg);
			error = true;
		}
		RequestContext.getCurrentInstance().addCallbackParam("error", error);
	}

	public void updatePasswordModal() {
		boolean error = (Boolean) RequestContext.getCurrentInstance().getCallbackParams().get("error");
		if (!error) {
			getSelectedEntity().setPassword(passwordEncoder.encodePassword(getPasswordMod(), getSelectedEntity().getLogin()));
			RequestContext.getCurrentInstance().execute("dlg.hide()");
		}
	}

	// Función para preCargar los datos
	public void preLoad() {
		// Actualizamos el rol seleccionado
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("selectedRole") != null) {
			role = (Role) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("selectedRole");
			if (getSelectedEntity() != null) {
				getSelectedEntity().setRole(role);
			}
			searchRole = role.getName();
		}

	}

	public void postLoad() {
		// Inicializamos el entityFilters
		getEntities().setEntityFilters(new HashMap<String, Object>());
	}

	public void handleToggle(ToggleEvent event) {
		reset();
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
