package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable {

	private Socket s;
	private boolean conectado;

	public Comunicacion() {
		try {
			s = new Socket(InetAddress.getByName("127.0.0.1"), 5666);
			conectado = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (conectado) {
			recibirMensaje();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void recibirMensaje() {
		DataInputStream dis = null;

		try {
			dis = new DataInputStream(s.getInputStream());
			String mensaje = dis.readUTF();

			setChanged();
			notifyObservers(mensaje);
			clearChanged();
		} catch (IOException e) {
			try {
				if (dis != null) {
					dis.close();
				}
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			s = null;
			conectado = false;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enviarMensaje(String mensaje) {
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(mensaje);
		} catch (IOException e) {

			try {
				if (dos != null) {
					dos.close();
				}
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			s = null;
			conectado = false;
			e.printStackTrace();
		}
	}
}
