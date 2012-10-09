package es.scmt.util;

public class PasswordGenerator {

	private static String NUMEROS = "0123456789";
	private static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	private static String POSITION = "01234567";
	private static final int LONG_PASSWORD = 8;

	public static String getPassword() {
		String pswd = "";
		// El password debe contener al menos una mayúscula
		char may = MAYUSCULAS.charAt((int) (Math.random() * MAYUSCULAS.length()));
		// Generamos la posición de la mayúscula
		int posMay = Integer.valueOf(POSITION.charAt((int) (Math.random() * POSITION.length())));
		// El password debe contener al menos un número
		char num = NUMEROS.charAt((int) (Math.random() * NUMEROS.length()));
		// Generamos la posición del número
		int posNum = Integer.valueOf(POSITION.charAt((int) (Math.random() * POSITION.length())));
		while (posNum == posMay) {
			// Si la posicionCoincide
			posNum = Integer.valueOf(POSITION.charAt((int) (Math.random() * POSITION.length())));
		}

		String key = NUMEROS.concat(MAYUSCULAS).concat(MINUSCULAS);

		for (int i = 0; i < LONG_PASSWORD; i++) {
			if (i == posMay) {
				pswd += may;
			} else if (i == posNum) {
				pswd += num;
			} else {
				pswd += (key.charAt((int) (Math.random() * key.length())));
			}
		}

		return pswd;
	}

}
