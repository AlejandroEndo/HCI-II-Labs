package cliente;

import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class Logica implements Observer {

	private PApplet app;

	static Comunicacion com;

	private int conectados;
	private int h;
	private int id;

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
		app.fill(0);
		app.text("soy " + id + "\n y hay " + conectados, app.width / 2, app.height - 50);
		if (movible) {
			app.fill(h, 100, 100);
			app.ellipse(x, y, 50, 50);
			x += 5;
		}
		validar();
	}

	private void validar() {
		if (x >= app.width + 25) {
			com.enviarMensaje("acabe");
			movible = false;
			x = 0;
		}
	}

	@Override
	public void update(Observable o, Object msn) {

		if (msn instanceof String) {
			String mensaje = (String) msn;
			System.out.println("Se recibio: " + mensaje);

			if (mensaje.contains("Hola cliente")) {
				com.enviarMensaje("values");
			}

			if (mensaje.contains("id")) {
				String[] partes = mensaje.split(":");
				id = Integer.parseInt(partes[1]);
				conectados = id;
				y = id * 100;
				System.out.println(id);
			}

			if (mensaje.contains("color")) {
				String[] partes = mensaje.split(":");
				h = Integer.parseInt(partes[1]);
				System.out.println(h);
			}

			if (mensaje.contains("mas")) {
				conectados++;
			}

			if (mensaje.contains("muevase")) {
				movible = true;
			}
		}

	}

}
