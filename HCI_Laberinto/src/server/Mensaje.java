package server;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x, y;

	private String dir;

	public Mensaje(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Mensaje(String dir) {
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getDir() {
		return dir;
	}
	
}
