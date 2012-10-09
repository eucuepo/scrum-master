package es.scmt.services.base;

import java.util.Locale;

import es.scmt.entities.User;

/** 
 * Definición de las acciones que debe implementar un servicio de login. <p>
 */  
public interface GenericLoginService {
	/** Realiza las validaciones y acciones necesarias en el login de la aplicación. */
	//RTDBError login(JFSEISValores valores);
	boolean login(String usuario, String Password);
	/** Calcula el usuario que se debe almacenar en la sesión. */
	User getSessionUser();
	/** Calcula el Locale (objeto Java de localización) del usuario */
	Locale getLocaleUser();
}
