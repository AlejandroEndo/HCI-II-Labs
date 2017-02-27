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
	// Juliana empieza aqui
	private PImage[] imgs = new PImage[39];
	private PImage[] botones = new PImage[10];
	private PImage[] lugares = new PImage[17];
	private PImage[] fondo = new PImage[2];
	private PImage[] indicacion = new PImage[5];
	private PImage personaje;
	private PImage[] ImgConfianza = new PImage[2];

	// aun no hago nada de esta porque ni la instrucciones estan bien 
	private PImage[] pantallas;

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
		
		// Juliana
		cargarImgs();

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
			app.image(fondo[0], 0, 0);
			app.image(fondo[1], 500, 0);
			// hay que arreglar las pos, para que se acoplen a las nuevas imagenes
			app.image(lugares[pos], 500, 0);
			
		//	app.image(lugar[pos], 500, 0);
			
			// QUIERO METER ESTO PERO NO SE COMO 
			// app.image(indicacion[log.getMensaje.getDir], 0, 0);
			// o algo asi, porque putas tenias que hacer la pantalla separada
			
		//	app.image(sugerencias[confianza], 0, 0);
			
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

	// juliana
	public void cargarImgs() {
		for (int i = 1; i <= imgs.length; i++) {
			imgs[i] = app.loadImage("../data/real/hci (" + i + ").png");
		}
		
		for (int i = 0; i < botones.length; i++) {
			botones[i]= imgs[i+4];
		}
		for (int i = 0; i < lugares.length; i++) {
			lugares[i]= imgs[i+15];
		}
		fondo[0]= imgs[36] ;
		fondo[1]= imgs[14] ;
		personaje = imgs[3];
		ImgConfianza[0]= imgs[34];
		ImgConfianza[1]= imgs[35];
		indicacion[0]= imgs[32];
		indicacion[1]= imgs[33];
		indicacion[2]= imgs[37];
		indicacion[3]= imgs[38];
		indicacion[4]= imgs[39];
	}
}
