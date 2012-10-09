package es.scmt.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Repository
/**
 * Clase que implementa el interfaz GenericDAO para hibernate 4. Es generica, el primer parametro generico indica el tipo de entidad y el segundo el tipo de clave primaria 
 * @author ecuevas
 *
 * @param <E>
 * @param <PK>
 */
public abstract class GenericDAOHibernate4<E, PK extends Serializable> implements GenericDAO<E, PK> {
	private static final String UNCHECKED = "unchecked";
	private static final String ASC = "asc";
	private static final String DESC = "desc";

	protected SessionFactory factory;

	@SuppressWarnings(UNCHECKED)
	public PK save(E newInstance) {
		return (PK) factory.getCurrentSession().save(newInstance);

	}

	@SuppressWarnings(UNCHECKED)
	public E findById(PK id) {
		return (E) factory.getCurrentSession().get(getEntityClass(), id);
	}

	@SuppressWarnings(UNCHECKED)
	public List<E> findAll() {
		Criteria criteria = factory.getCurrentSession().createCriteria(getEntityClass());
		return criteria.list();
	}

	@SuppressWarnings(UNCHECKED)
	public List<E> findByFilters(Map<String, String> filters, Map<String, Object> entityFilters) {
		Criteria criteria = factory.getCurrentSession().createCriteria(getEntityClass());
		// comprobamos si hay entityfilters
		if (entityFilters != null) {
			for (Map.Entry<String, Object> e : entityFilters.entrySet()) {
				criteria.add(Restrictions.eq(e.getKey(), e.getValue()));
			}
		}

		for (Map.Entry<String, String> e : filters.entrySet()) {
			criteria.add(Restrictions.like(e.getKey(), e.getValue() + "%"));
		}
		return criteria.list();
	}

	@SuppressWarnings(UNCHECKED)
	public List<E> findAllByProperty(String propertyName, Object value) {
		Criteria criteria = factory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.like(propertyName, value));
		return criteria.list();
	}

	@SuppressWarnings(UNCHECKED)
	public List<E> findByExample(E object) {
		Criteria criteria = factory.getCurrentSession().createCriteria(getEntityClass());
		return criteria.list();
	}

	@SuppressWarnings(UNCHECKED)
	public List<E> findByExample(E object, int firstResult, int maxResults) {
		Criteria criteria = factory.getCurrentSession().createCriteria(getEntityClass());
		criteria.setMaxResults(maxResults);
		criteria.setFirstResult(firstResult);
		return criteria.list();
	}

	public void update(E transientObject) {
		factory.getCurrentSession().update(transientObject);
	}

	public void saveOrUpdate(E transientObject) {
		factory.getCurrentSession().saveOrUpdate(transientObject);
	}

	public void delete(E persistentObject) {
		factory.getCurrentSession().delete(persistentObject);
	}

	public List<E> loadLazy(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters) {
		return loadLazy(first, pageSize, sortField, sortOrder, filters, null);
	}

	@SuppressWarnings(UNCHECKED)
	public List<E> loadLazy(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters, Map<String, Object> entityFilters) {
		Criteria criteria = factory.getCurrentSession().createCriteria(getEntityClass());
		// comprobamos si hay entityfilters
		if (entityFilters != null) {
			for (Map.Entry<String, Object> e : entityFilters.entrySet()) {
				criteria.add(Restrictions.eq(e.getKey(), e.getValue()));
			}
		}
		for (Map.Entry<String, String> e : filters.entrySet()) {
			criteria.add(Restrictions.like(e.getKey(), e.getValue() + "%"));
		}

		if (sortField != null && sortOrder.equals(ASC)) {
			criteria.addOrder(Order.asc(sortField));
		} else if (sortField != null && sortOrder.equals(DESC)) {
			criteria.addOrder(Order.desc(sortField));
		}
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		return criteria.list();

	}

	protected abstract Class<E> getEntityClass();

	@Autowired
	public GenericDAOHibernate4(SessionFactory factory) {
		this.factory = factory;
	}

	protected DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}
}