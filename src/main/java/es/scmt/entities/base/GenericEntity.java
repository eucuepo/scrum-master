package es.scmt.entities.base;

/**
 * Clase abstracta que deben extender las entidades de la aplicación.
 * <p>
 * Sólo se ha definido para poder identificar los pojos que son entidades.
 * <p>
 * Por tanto, toda entidad de la aplicación debe extender esta clase abstracta.
 * <p>
 * Tambien sirve para usar el tipo generico cuando se definen los parámetros de
 * los métodos, los tipos List ó ArrayList, etc.
 */
public abstract class GenericEntity {
	public abstract Long getId();
}
