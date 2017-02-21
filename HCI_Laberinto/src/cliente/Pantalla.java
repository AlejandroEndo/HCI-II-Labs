package cliente;

import processing.core.PApplet;
import processing.core.PImage;

public class Pantalla {

	private PApplet app;

	private PImage inicio;
	private PImage instruccion;
	private PImage interfaz;
	private PImage turno;

	private PImage[] lugar = new PImage[11];

	private int pantalla = 2;
	private int pos;

	public Pantalla(PApplet app) {
		this.app = app;

		inicio = app.loadImage("../data/inicio.png");
		instruccion = app.loadImage("../data/instruccion.png");
		interfaz = app.loadImage("../data/interfaz.png");
		turno = app.loadImage("../data/turno.png");

		for (int i = 0; i < 11; i++) {
			lugar[i] = app.loadImage("../data/" + (i + 1) + ".png");
		}
	}

	public void draw() {
		switch (pantalla) {
		case 0:
			app.image(inicio, 0, 0);
			break;

		case 1:
			app.image(instruccion, 0, 0);
			break;

		case 2:
			app.image(interfaz, 0, 0);
			app.image(lugar[pos], 500, 0);
			app.image(turno, 0, 0);
			break;
		}
	}

	public int getPantalla() {
		return pantalla;
	}

	public void setPantalla(int pantalla) {
		if (pantalla < 3)
			this.pantalla = pantalla;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

}
