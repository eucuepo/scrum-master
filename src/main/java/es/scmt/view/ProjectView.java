package es.scmt.view;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.scmt.entities.GenericLazyDataModel;
import es.scmt.entities.Project;
import es.scmt.entities.User;
import es.scmt.services.base.GenericService;
import es.scmt.ui.menu.TreeBean;
import es.scmt.view.base.GenericView;

@Component("projectView")
@Scope("view")
public class ProjectView extends GenericView<User> implements Serializable {

	private static final long serialVersionUID = 8983474434608725243L;
	
	private Project project;
	
	
	@Autowired
	public ProjectView(@Qualifier("projectService") GenericService<User> projectService) {
		super(projectService, new GenericLazyDataModel<User>(projectService));
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}



	// Función para preCargar los datos
	public void preLoad() {
		project = ((TreeBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("treeBean")).getSelectedProject();
	}


	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
