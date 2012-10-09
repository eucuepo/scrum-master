package es.scmt.view.base;

import java.util.Arrays;
import java.util.Locale;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;

import es.scmt.entities.GenericLazyDataModel;
import es.scmt.entities.base.GenericEntity;
import es.scmt.services.LoginService;
import es.scmt.services.base.GenericService;
import es.scmt.util.RTDBError;

public abstract class GenericView<E extends GenericEntity> {
	protected static final Logger log = Logger.getLogger(GenericView.class);
	protected static final String SUCCESS = "success";
	protected static final String ERROR = "error";

	private Long id;
	private String name;

	protected GenericService<E> service;
	private GenericLazyDataModel<E> entities;

	private E selectedEntity;
	private E[] selectedEntities;
	private E[] filteredEntities;

	@Autowired
	@Qualifier("loginService")
	private LoginService loginService;

	protected abstract Class<E> getEntityClass();

	public GenericView(GenericService<E> service, GenericLazyDataModel<E> genericLazyDataModel) {
		this.service = service;
		this.entities = genericLazyDataModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GenericLazyDataModel<E> getEntities() {
		return entities;
	}

	public void setEntities(GenericLazyDataModel<E> entities) {
		this.entities = entities;
	}

	public E getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(E selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	public E[] getSelectedEntities() {
		return selectedEntities.clone();
	}

	public void setSelectedEntities(E[] selectedEntities) {
		this.selectedEntities = selectedEntities.clone();
	}

	public E[] getFilteredEntities() {
		if (filteredEntities == null) {
			return null;
		} else {
			return filteredEntities.clone();
		}

	}

	public void setFilteredEntities(E[] filteredEntities) {
		this.filteredEntities = filteredEntities.clone();
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public Locale getLocale() {
		return loginService.getLocaleUser();
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String viewEntity() {
		if (getSelectedEntity() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
			handler.performNavigation("edit" + getEntityClass().getSimpleName());
			return SUCCESS;
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, RTDBError.calcMsgError("genericView.selectEntityView", loginService.getLocaleUser()), RTDBError.calcMsgError(
							"genericView.selectEntityView", loginService.getLocaleUser())));
		}
		return ERROR;
	}

	public void onRowDblselect(SelectEvent event) {
		viewEntity();
	}

	public String updateEntity() {
		try {
			if (getSelectedEntity() != null) {
				service.update(getSelectedEntity());
				return SUCCESS;
			} else {
				String errorMessage = RTDBError.calcMsgError("genericView.selectEntityUpd", loginService.getLocaleUser());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage, errorMessage));
			}
		} catch (DataAccessException e) {
			String errorMessage = RTDBError.calcMsgError("genericService.ErrorUpdEntity", loginService.getLocaleUser());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
			log.error(Arrays.toString(e.getStackTrace()));
		}

		return ERROR;
	}

	public String deleteEntity() {
		try {
			if (getSelectedEntity() != null) {
				service.delete(getSelectedEntity());
				return SUCCESS;
			} else {
				String errorMessage = RTDBError.calcMsgError("genericView.selectEntityDel", loginService.getLocaleUser());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage, errorMessage));
			}
		} catch (DataAccessException e) {
			String errorMessage = RTDBError.calcMsgError("genericView.selectEntityDel", loginService.getLocaleUser());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage, errorMessage));
			log.error(Arrays.toString(e.getStackTrace()));
		}
		return ERROR;
	}
}
