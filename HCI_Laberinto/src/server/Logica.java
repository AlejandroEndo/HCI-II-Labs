package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
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
				reenviarMensaje("mas", controlCliente);
			}

			if (mensaje.contains("acabe")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("muevase");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("muevase");
				}
			}

			if (mensaje.contains("up")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("up");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("up");
				}
			}
			if (mensaje.contains("down")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("down");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("down");
				}
			}
			if (mensaje.contains("left")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("left");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("left");
				}
			}
			if (mensaje.contains("right")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("right");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("right");
				}
			}
			
			if (mensaje.contains("arriba")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("arriba");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("arriba");
				}
			}
			
			if (mensaje.contains("derecha")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("derecha");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("derecha");
				}
			}
			
			if (mensaje.contains("abajo")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("abajo");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("abajo");
				}
			}
			
			if (mensaje.contains("izquierda")) {
				if (controlCliente.getId() == clientes.size() - 1) {
					clientes.get(0).enviarMensaje("izquierda");
				} else {
					clientes.get(controlCliente.getId() + 1).enviarMensaje("izquierda");
				}
			}
		}
	}

	private void reenviarMensaje(String mensaje, Comunicacion remitente) {
		int reenvios = 0;
		for (Iterator<Comunicacion> iterator = clientes.iterator(); iterator.hasNext();) {
			Comunicacion com = (Comunicacion) iterator.next();
			if (!com.equals(remitente)) {
				com.enviarMensaje(mensaje);
				reenvios++;
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
