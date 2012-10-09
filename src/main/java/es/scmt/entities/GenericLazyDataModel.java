package es.scmt.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import es.scmt.entities.base.GenericEntity;
import es.scmt.services.base.GenericService;

public class GenericLazyDataModel<E extends GenericEntity> extends LazyDataModel<E> {

	private static final long serialVersionUID = 1L;

	private GenericService<E> service;

	private Map<String, String> filters;
	private Map<String, Object> entityFilters;

	public GenericLazyDataModel(GenericService<E> service) {
		this.service = service;
		this.entityFilters = new HashMap<String, Object>();
	}

	public GenericService<E> getService() {
		return service;
	}

	public void setService(GenericService<E> service) {
		this.service = service;
	}

	@Override
	public int getRowCount() {
		return service.getRowCount(filters, entityFilters).intValue();
	}

	@Override
	public List<E> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		// store data filters
		this.filters = filters;
		List<E> lista;
		if (entityFilters != null && entityFilters.size() > 0) {
			lista = service.loadLazy(first, pageSize, sortField, sortOrder, filters, entityFilters);
		} else {
			lista = service.loadLazy(first, pageSize, sortField, sortOrder, filters);
		}
		// rowCount
		int dataSize = lista.size();
		this.setRowCount(dataSize);

		return lista;
	}

	public Map<String, Object> getEntityFilters() {
		return entityFilters;
	}

	public void setEntityFilters(Map<String, Object> entityFilters) {
		this.entityFilters = entityFilters;
	}

	@Override
	public Object getRowKey(E m) {
		return m.getId();
	}

	@Override
	public E getRowData(String rowKey) {
		return service.findById(Long.parseLong(rowKey));
	}
}
