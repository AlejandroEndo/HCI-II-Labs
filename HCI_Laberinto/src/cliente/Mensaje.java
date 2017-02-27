package cliente;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x, y;

	private String dir;

	private String tipo;
	// el tipo en el mensaje, puede ayudar a identificar
	// si el mensaje es una indicacion , una direccion, etc.
	// y en la logica hacer acciones dependiendo del tipo de mensaje

	public Mensaje(String tipo, int x, int y) {
		this.x = x;
		this.y = y;
		this.tipo = tipo;
	}

	public Mensaje(String tipo, String dir) {
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getDir() {
		return dir;
	}

}
