package cliente;

import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PConstants;

public class Logica implements Observer {

	private PApplet app;

	static Comunicacion com;

	private Pantalla pantalla;

	private int[][] matrixA = { { 0, 0, 5, 0, 5, 0, 1, 4 }, { 5, 2, 2, 1, 2, 1, 2, 0 }, { 0, 1, 0, 0, 1, 0, 2, 1 },
			{ 5, 2, 2, 1, 2, 0, 1, 0 }, { 0, 0, 1, 0, 2, 1, 2, 1 }, { 1, 1, 0, 0, 1, 0, 0, 0 },
			{ 1, 0, 1, 1, 2, 1, 2, 1 }, { 3, 1, 1, 0, 5, 0, 5, 0 } };

	private int[][] matrixADir = { { 0, 0, 5, 0, 5, 0, 1, 4 }, { 5, 2, 2, 1, 2, 1, 2, 0 }, { 0, 1, 0, 0, 1, 0, 2, 1 },
			{ 5, 2, 2, 1, 2, 0, 1, 0 }, { 0, 0, 1, 0, 2, 1, 2, 1 }, { 1, 1, 0, 0, 1, 0, 0, 0 },
			{ 1, 0, 1, 1, 2, 1, 2, 1 }, { 3, 1, 1, 0, 5, 0, 5, 0 } };

	private int[][] matrixB = { { 4, 0, 0, 5, 0, 0, 5, 0 }, { 1, 1, 0, 1, 0, 0, 1, 0 }, { 0, 2, 1, 2, 1, 1, 2, 5 },
			{ 1, 2, 0, 1, 0, 0, 1, 0 }, { 0, 1, 0, 2, 1, 2, 2, 5 }, { 1, 2, 0, 1, 0, 1, 0, 0 },
			{ 0, 2, 1, 2, 1, 2, 0, 1 }, { 0, 5, 0, 5, 0, 1, 3, 1 } };

	private int[][] matrixBDir = { { 4, 0, 0, 5, 0, 0, 5, 0 }, { 1, 1, 0, 1, 0, 0, 1, 0 }, { 0, 2, 1, 2, 1, 1, 2, 5 },
			{ 1, 2, 0, 1, 0, 0, 1, 0 }, { 0, 1, 0, 2, 1, 2, 2, 5 }, { 1, 2, 0, 1, 0, 1, 0, 0 },
			{ 0, 2, 1, 2, 1, 2, 0, 1 }, { 0, 5, 0, 5, 0, 1, 3, 1 } };

	private int[][] matrixAImage = { { 0, 0, 1, 0, 1, 0, 4, 2 }, { 2, 9, 7, 2, 11, 2, 10, 0 },
			{ 0, 1, 0, 0, 1, 0, 8, 2 }, { 2, 7, 9, 2, 10, 0, 1, 0 }, { 0, 0, 1, 0, 8, 2, 7, 2 },
			{ 4, 2, 0, 0, 1, 0, 0, 0 }, { 1, 0, 4, 2, 11, 2, 9, 2 }, { 3, 2, 6, 0, 1, 0, 1, 0 } };

	private int[][] matrixBImage = { { 5, 0, 0, 1, 0, 0, 1, 0 }, { 3, 5, 0, 1, 0, 0, 1, 0 },
			{ 0, 8, 2, 11, 2, 2, 11, 2 }, { 2, 10, 0, 1, 0, 0, 1, 0 }, { 0, 1, 0, 8, 2, 9, 7, 2 },
			{ 2, 10, 0, 1, 0, 1, 0, 0 }, { 0, 8, 2, 11, 2, 10, 0, 1 }, { 0, 1, 0, 1, 0, 3, 2, 6 } };

	private int[][] matrix = new int[8][8];
	private int[][] matrixDir = new int[8][8];
	private int conectados;
	private int h;
	private int id;

	private int dir;
	private boolean movil = true;

	private float s;
	private float x;
	private float y;

	private boolean movible;

	public Logica(PApplet app) {
		this.app = app;

		pantalla = new Pantalla(app);

		com = new Comunicacion();
		new Thread(com).start();
		com.addObserver(this);

		movible = false;

		s = 30;
	}

	public void pintar() {
		app.fill(0);

		pantalla.draw();
		if (pantalla.getPantalla() == 2)
			matrix();

		app.text("soy " + id + "\n y hay " + conectados, app.width / 2, app.height - 50);
		if (movible) {
			// app.fill(h, 100, 100);
			// app.ellipse(x, y, 50, 50);
			// x += 5;
		}
		// validar();
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

			if (mensaje.contains("mas")) {
				conectados++;
			}

			// if (mensaje.contains("muevase")) {
			// movible = true;
			// }

			if (mensaje.contains("up")) {
				dir = 4;
				movil = true;
			}

			if (mensaje.contains("down")) {
				dir = 1;
				movil = true;
			}

			if (mensaje.contains("left")) {
				dir = 2;
				movil = true;
			}

			if (mensaje.contains("right")) {
				dir = 3;
				movil = true;
			}
		}

	}

	private void matrix() {
		if (id == 1) {
			matrix = matrixA;
			matrixDir = matrixADir;
		} else if (id == 2) {
			matrix = matrixB;
			matrixDir = matrixBDir;
		}

		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {

				switch (matrix[j][i]) {
				case 0:
					app.fill(0);
					break;

				case 1:
					app.fill(255);
					break;

				case 2:
					app.fill(255, 255, 0);
					break;

				case 3:
					app.fill(0, 255, 255);
					break;

				case 4:
					app.fill(255, 0, 255);
					break;

				case 5:
					app.fill(255, 0, 0);
					break;
				}
				app.rect(i * s + 70, j * s + 70, s, s);
			}
		}

		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				if (movil) {
					if (matrix[j][i] == 3) {
						pantalla.setPos(matrixAImage[j][i]);
						switch (dir) {

						case 4:
							if (j > 0) {
								if (matrix[j - 1][i] == 1 || matrixDir[j - 1][i] == 2) {
									matrix[j - 1][i] = 3;
									matrix[j][i] = 1;
									System.out.println("aaa");
								}
								if (matrixDir[j - 1][i] == 2) {
									movil = false;
								}
							}
							dir = 0;
							break;

						case 1:
							if (j < 7) {
								if (matrix[j + 1][i] == 1 || matrixDir[j + 1][i] == 2) {
									matrix[j + 1][i] = 3;
									matrix[j][i] = 1;
									System.out.println("aaa");
								}
								if (matrixDir[j + 1][i] == 2) {
									movil = false;
								}
							}
							dir = 0;
							break;

						case 2:
							if (i > 0) {
								if (matrix[j][i - 1] == 1 || matrixDir[j][i - 1] == 2) {
									matrix[j][i - 1] = 3;
									matrix[j][i] = 1;
									System.out.println("aaa");
								}
								if (matrixDir[j][i - 1] == 2) {
									movil = false;
								}
							}
							dir = 0;
							break;

						case 3:
							if (i < 7) {
								if (matrix[j][i + 1] == 1 || matrixDir[j][i + 1] == 2) {
									matrix[j][i + 1] = 3;
									matrix[j][i] = 1;
									System.out.println("aaa");
								}
								if (matrixDir[j][i + 1] == 2) {
									movil = false;
								}
							}
							dir = 0;
							break;
						}
					}
				}
			}
		}
	}

	public void key() {

		pantalla.setPantalla(pantalla.getPantalla() + 1);
		switch (app.keyCode) {

		case PConstants.UP:
			// dir = 4;
			com.enviarMensaje("up");
			// movil = true;
			break;

		case PConstants.DOWN:
			// dir = 1;
			com.enviarMensaje("down");
			// movil = true;
			break;

		case PConstants.LEFT:
			// dir = 2;
			com.enviarMensaje("left");
			// movil = true;
			break;

		case PConstants.RIGHT:
			// dir = 3;
			com.enviarMensaje("right");
			// movil = true;
			break;
		}
	}

}
