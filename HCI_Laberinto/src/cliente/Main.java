package cliente;

import processing.core.PApplet;

public class Main extends PApplet {

	private Logica logica;

	@Override
	public void setup() {
		size(1200, 700);
		logica = new Logica(this);
	}

	@Override
	public void draw() {
		logica.pintar();
	}
}
