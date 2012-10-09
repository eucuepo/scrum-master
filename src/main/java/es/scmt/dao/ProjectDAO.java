package es.scmt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.scmt.dao.base.GenericDAOHibernate4;
import es.scmt.entities.Project;

@Component
@Transactional
@Repository
@Qualifier("projectDAO")
/**
 * Clase que implementa el dao generico Hibernate 4. Es la capa del modelo de la aplicacion
 * @author eucuepo
 *
 */
public class ProjectDAO extends GenericDAOHibernate4<Project, Long> {

	/**
	 * Constructor que recibe el sessionFactory de hibernate
	 * @param factory
	 */
	@Autowired
	public ProjectDAO(SessionFactory factory) {
		super(factory);
	}

	/**
	 * Funcion que retorna el tipo de entidad con la que se esta trabajando, en este caso Project
	 */
	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}
	
}