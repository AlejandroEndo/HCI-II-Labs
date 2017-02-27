package cliente;

import processing.core.PApplet;
import processing.core.PImage;

public class Boton {
//juliana
	private PApplet app;
	private String direccion;
	private PImage[] imgs;
	private PImage[] botoncito=new PImage [2];
	private int x;
	private int y;
	private boolean hover;

	public Boton(PApplet app, PImage[] imgs, String direccion, int x, int y){
		this.app=app;
		this.imgs=imgs;
		this.direccion=direccion;
		this.x=x;
		this.y=y;
		
		switch(direccion){
		case "arriba":
			botoncito[0]=imgs[1];
			botoncito[1]=imgs[2];
			break;
		case "abajo":
			botoncito[0]=imgs[6];
			botoncito[1]=imgs[7];
			break;
		case "derecha":
			botoncito[0]=imgs[4];
			botoncito[1]=imgs[5];
			break;
		case "izquierda":
			botoncito[0]=imgs[0];
			botoncito[1]=imgs[3];
			break;
		case "parar":
			botoncito[0]=imgs[8];
			botoncito[1]=imgs[9];
			break;
		}
		
		}
	
	public void pintar(){
		if(!hover){
		app.image(botoncito[1],x,y);
		} else{
		app.image(botoncito[0],x,y);	
		}
	}
	public void hover(int x1, int y1){
		if (app.dist(x,y,x1,y1)<25){
			hover=true;
		}else{
			hover= false;
		}
		
		}
	}
	
