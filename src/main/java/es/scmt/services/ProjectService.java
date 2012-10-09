package es.scmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.scmt.dao.base.GenericDAO;
import es.scmt.entities.Project;
import es.scmt.services.base.GenericService;

@Service("projectService")
@Qualifier("projectService")
/**
 * Clase que implementa generic service para la entidad proyecto. Es el controlador, se encarga de mover los datos entre el modelo y la vista
 * @author ecuevas
 *
 */
public class ProjectService extends GenericService<Project> {
	/**
	 * Constructor que toma una clase que implementa un DAO generico, en este
	 * caso projectDAO
	 * 
	 * @param dao
	 */
	@Autowired
	public ProjectService(@Qualifier("projectDAO") GenericDAO<Project, Long> dao) {
		super(dao);
	}

}
