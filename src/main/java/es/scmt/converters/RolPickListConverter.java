package es.scmt.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.scmt.entities.Role;

@FacesConverter("rolPickListConverter")
public class RolPickListConverter implements Converter {
	private static final Logger LOG = LoggerFactory.getLogger(RolPickListConverter.class);

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LOG.trace("String value: {}", value);

		return getObjectFromUIPickListComponent(component, value);
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		String string;
		LOG.trace("Object value: {}", object);
		if (object == null) {
			string = "";
		} else {
			try {
				string = String.valueOf(((Role) object).getId());
			} catch (ClassCastException cce) {
				throw new ConverterException(cce);
			}
		}
		return string;
	}

	@SuppressWarnings("unchecked")
	private Role getObjectFromUIPickListComponent(UIComponent component, String value) {
		final DualListModel<Role> dualList;
		try {
			dualList = (DualListModel<Role>) ((PickList) component).getValue();
			Role rol = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
			if (rol == null) {
				rol = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
			}

			return rol;
		} catch (ClassCastException cce) {
			throw new ConverterException(cce);
		} catch (NumberFormatException nfe) {
			throw new ConverterException(nfe);
		}
	}

	private Role getObjectFromList(final List<?> list, final Integer identifier) {
		for (final Object object : list) {
			final Role rol = (Role) object;
			if (rol.getId().equals(Long.parseLong(String.valueOf(identifier)))) {
				return rol;
			}
		}
		return null;
	}

}
