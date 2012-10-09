package es.scmt.ui.menu;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.scmt.dao.base.GenericDAO;
import es.scmt.entities.Project;
import es.scmt.entities.Release;
import es.scmt.entities.Sprint;


@Component("treeBean")
@Scope("session")
public class TreeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreeNode root;
	private TreeNode selectedNode;

	private Project selectedProject;
	private Release selectedRelease;
	private Sprint selectedSprint;

	public Release getSelectedRelease() {
		return selectedRelease;
	}

	public void setSelectedRelease(Release selectedRelease) {
		this.selectedRelease = selectedRelease;
	}

	public Sprint getSelectedSprint() {
		return selectedSprint;
	}

	public void setSelectedSprint(Sprint selectedSprint) {
		this.selectedSprint = selectedSprint;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project project) {
		this.selectedProject = project;
	}

	@Autowired
	public TreeBean(@Qualifier("projectDAO") GenericDAO<Project, Long> projectDAO) {
		root = new DefaultTreeNode("Root", null);
		List<Project> projectList = projectDAO.findAll();
		TreeNode projectAux;
		TreeNode releaseAux;
		for (Project project : projectList) {
			projectAux = new DefaultTreeNode(project, root);
			List<Release> releaseList = project.getReleaseList();
			for (Release release : releaseList) {
				releaseAux = new DefaultTreeNode(release, projectAux);
				List<Sprint> sprintList = release.getSprintList();
				for (Sprint sprint : sprintList) {
					new DefaultTreeNode(sprint, releaseAux);
				}
			}
		}

	}

	public void onNodeSelect(NodeSelectEvent event) {
		String navigateTo = "";
		FacesContext context = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
		if (event.getTreeNode().getData() instanceof Project) {
			this.selectedProject = (Project) event.getTreeNode().getData();
			navigateTo = "viewProject?faces-redirect=true";
		} else if (event.getTreeNode().getData() instanceof Release) {
			this.selectedRelease = (Release) event.getTreeNode().getData();
			navigateTo = "viewRelease?faces-redirect=true";
		} else if (event.getTreeNode().getData() instanceof Sprint) {
			this.selectedSprint = (Sprint) event.getTreeNode().getData();
			navigateTo = "viewSprint?faces-redirect=true";
		}
		handler.performNavigation(navigateTo);
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

}