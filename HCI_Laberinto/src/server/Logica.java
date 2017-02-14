package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Logica implements Observer, Runnable {

	private final int PORT = 5666;

	private ServerSocket ss;

	private boolean conectado;
	private boolean moviendo;

	private ArrayList<Comunicacion> clientes = new ArrayList<>();

	public Logica() {

		try {
			ss = new ServerSocket(PORT);
			conectado = true;
			System.out.println(
					"[SERVER]: Escuchando en: " + InetAddress.getLocalHost().getHostAddress().toString() + ":" + PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (conectado) {
			try {
				Socket s = ss.accept();

				Comunicacion com = new Comunicacion(s, clientes.size());
				
				// agregar el gestor como observador
				com.addObserver(this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
