package es.scmt.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interfaz que define el contrato para un DataAccessObject, incluye metodos de
 * servicio como save, update, etc...
 * 
 * @author ecuevas
 * 
 * @param <E>
 * @param <PK>
 */
public interface GenericDAO<E, PK extends Serializable> {
	PK save(E newInstance);

	void update(E transientObject);

	void saveOrUpdate(E transientObject);

	void delete(E persistentObject);

	E findById(PK id);

	List<E> findAll();

	List<E> findAllByProperty(String propertyName, Object value);

	List<E> findByFilters(Map<String, String> filters, Map<String, Object> entityFilters);

	List<E> loadLazy(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters);

	List<E> loadLazy(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters, Map<String, Object> entityFilters);
}
