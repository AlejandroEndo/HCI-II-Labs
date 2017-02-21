package cliente;

import processing.core.PApplet;
import processing.core.PImage;

public class Pantalla {

	private PApplet app;

	private PImage inicio;
	private PImage instruccion;
	private PImage interfaz;
	private PImage turno;

	private PImage[] lugar = new PImage[12];
	private PImage[] sugerencias = new PImage[5];

	private int pantalla = 2;
	private int pos;
	private int confianza;

	public Pantalla(PApplet app) {
		this.app = app;

		inicio = app.loadImage("../data/inicio.png");
		instruccion = app.loadImage("../data/instruccion.png");
		interfaz = app.loadImage("../data/interfaz.png");
		turno = app.loadImage("../data/turno.png");

		for (int i = 0; i < 12; i++) {
			lugar[i] = app.loadImage("../data/" + (i) + ".png");
		}

		sugerencias[0] = app.loadImage("../data/incognito.png");
		sugerencias[1] = app.loadImage("../data/arriba.png");
		sugerencias[2] = app.loadImage("../data/derecha.png");
		sugerencias[3] = app.loadImage("../data/abajo.png");
		sugerencias[4] = app.loadImage("../data/izquierda.png");
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
			app.image(sugerencias[confianza], 0, 0);
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

	public int getConfianza() {
		return confianza;
	}

	public void setConfianza(int confianza) {
		this.confianza = confianza;
	}

}
