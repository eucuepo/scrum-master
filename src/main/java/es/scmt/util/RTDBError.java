package es.scmt.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class RTDBError {
	private static final String FICHERO_MENSAJES = "es.scmt.messages.pmw";

	private RTDBError() {
		// Para evitar que se cree una instancia de esta clase.
	}

	public static String calcMsgError(String message, Locale locale) {
		ResourceBundle bundle = null;
		// Leemos el fichero de recursos
		if (locale != null) {
			bundle = ResourceBundle.getBundle(FICHERO_MENSAJES, locale);
		} else {
			bundle = ResourceBundle.getBundle(FICHERO_MENSAJES);
		}
		return bundle.getString(message);
	}
}
