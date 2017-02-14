package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
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

		moviendo = true;
	}

	@Override
	public void run() {

		while (conectado) {
			try {
				Socket s = ss.accept();

				if (clientes.size() < 2) {
					Comunicacion com = new Comunicacion(s, clientes.size());

					// agregar el gestor como observador
					com.addObserver(this);

					new Thread(com).start();

					// Agregar a la coleccion de clientes
					clientes.add(com);
					System.out.println("[SERVER]: Tenemos " + clientes.size() + " clientes");
				} else {
					rechazarCliente(s);
				}
				Thread.sleep(100);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable o, Object msn) {
		Comunicacion controlCliente = (Comunicacion) o;

		if (msn instanceof String) {
			String mensaje = (String) msn;

			System.out.println(mensaje);

			if (mensaje.equalsIgnoreCase("cliente desconectado")) {
				clientes.remove(controlCliente);
				System.out.println("[SERVER]: Tenemos" + clientes.size() + " clientes");
			}

			if (mensaje.contains("values")) {
				controlCliente.enviarMensaje("id:" + clientes.size());
				controlCliente.enviarMensaje("color:" + (int) (Math.random() * 360));
			}

			if (moviendo) {
				controlCliente.enviarMensaje("muevase");
				moviendo = false;
			}

			if (mensaje.contains("acabe")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("muevase");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("muevase");
				}
			}
		}
	}

	private void rechazarCliente(Socket s) {
		try (ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream())) {
			String mensaje = "No se aceptan mas clientes en el momento";
			salida.writeObject(mensaje);
			System.out.println("[SERVER]: Se envio el mensaje: " + mensaje);
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
