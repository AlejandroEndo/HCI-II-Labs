package server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Logica implements Observer, Runnable {

	private final int PORT = 5000;

	private ServerSocket socketServer;

	private boolean conectado;
	private boolean moviendo;

	private ArrayList<Comunicacion> clientes = new ArrayList<>();

	@Override
	public void run() {

	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
