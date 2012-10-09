package es.scmt.services.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.scmt.dao.base.GenericDAO;
import es.scmt.entities.base.GenericEntity;

/**
 * Clase abstracta que implementa varios metodos de servicio que sirven para el
 * modelo. Las clases que lo extiendan pueden usar estos metodos o proveer con
 * los suyos propios
 * 
 * @author ecuevas
 * 
 * @param <E>
 */
public abstract class GenericService<E extends GenericEntity> {
	protected GenericDAO<E, Long> dao;

	@Autowired
	public GenericService(GenericDAO<E, Long> dao) {
		this.dao = dao;
	}

	public Long insert(E entidad) {
		return dao.save(entidad);
	}

	public void update(E entidad) {
		dao.saveOrUpdate(entidad);
	}

	public void delete(E entidad) {
		dao.delete(entidad);
	}

	public E getEntityById(Long id) {
		return dao.findById(id);
	}

	public List<E> getEntities() {
		return dao.findAll();
	}

	public Long getRowCount(Map<String, String> filters, Map<String, Object> entityFilters) {
		return (long) dao.findByFilters(filters, entityFilters).size();
	}

	public E findById(Long id) {
		return dao.findById(id);
	}

	public List<E> loadLazy(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		return dao.loadLazy(first, pageSize, sortField, sortOrder == SortOrder.ASCENDING ? "asc" : sortOrder == SortOrder.DESCENDING ? "desc" : null, filters);
	}

	public List<E> loadLazy(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Map<String, Object> entityFilters) {
		return dao.loadLazy(first, pageSize, sortField, sortOrder == SortOrder.ASCENDING ? "asc" : sortOrder == SortOrder.DESCENDING ? "desc" : null, filters, entityFilters);
	}

	public List<E> findAllByProperty(String property, String value) {
		return dao.findAllByProperty(property, value);
	}

	public List<E> findByName(String name) {
		return dao.findAllByProperty("name", name);
	}
}