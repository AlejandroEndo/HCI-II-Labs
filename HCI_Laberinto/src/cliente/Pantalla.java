package cliente;

import processing.core.PApplet;
import processing.core.PImage;

public class Pantalla {

	private PApplet app;

	private int pantalla;

	public Pantalla(PApplet app) {
		this.app = app;
	}

	public void draw() {
		switch (pantalla) {
		case 0:
			app.text("Esperando al otro jugador...", app.width / 2, app.height / 2);
			break;

		case 1:
			app.text("Estas son instrucciones xDxdXdXdXd", app.width / 2, app.height / 2);
			break;
		}
	}
}
