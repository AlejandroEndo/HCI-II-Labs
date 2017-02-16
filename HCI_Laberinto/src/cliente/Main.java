package cliente;

import processing.core.PApplet;

public class Main extends PApplet {

	private Logica logica;

	@Override
	public void setup() {
		size(1200, 700);
		textAlign(CENTER, CENTER);
		logica = new Logica(this);
	}

	@Override
	public void draw() {
		background(255);
		logica.pintar();
	}
	
	@Override
	public void keyPressed() {
		logica.key();
	}
}
