package es.scmt.util;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import es.scmt.entities.base.GenericEntity;

public final class RTDBUtil {

	private RTDBUtil() {
		// Para evitar que se cree una instancia de esta clase.
	}

	/**
	 * Obtiene un duplicado de un objeto fecha.
	 * 
	 * @param fechaAClonar
	 *            Fecha de la cuál se debe obtener un duplicado.
	 * @return El objeto fecha duplicado.
	 */
	public static Date cloneFecha(Date fechaAClonar) {
		/*
		 * Si la fecha recibida no es nula se hace uso del método clone de
		 * Object para obtener una copia del mismo.
		 */
		Date fecha;

		if (fechaAClonar == null) {
			fecha = null;
		} else {
			fecha = (Date) fechaAClonar.clone();
		}
		return fecha;
	}

	public static Map<Long, GenericEntity> convertirArraysToMap(Object[] valores) {
		Map<Long, GenericEntity> resultado = new HashMap<Long, GenericEntity>();
		if (valores != null) {
			long[] nombres = new long[valores.length];
			for (int j = 0; j < valores.length; j++) {
				nombres[j] = ((GenericEntity) valores[j]).getId();
			}

			for (int indice = 0; indice < nombres.length; indice++) {
				resultado.put(nombres[indice], (GenericEntity) valores[indice]);
			}
		}
		return resultado;
	}

}
