package cliente;

import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class Logica implements Observer{

	private PApplet app;

	static Comunicacion com;
	
	private int conectados;
	
	private float x;
	private float y;
	
	private boolean movible;
	
	public Logica(PApplet app) {
		this.app = app;
		
		com = new Comunicacion();
		new Thread(com).start();
		com.addObserver(this);
		
		movible = false;
	}

	public void pintar() {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
