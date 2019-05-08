package dut.game;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;

public class Sun {
	private int x;
	private int y;
	private final int taille = 30;
	private int valeur;
	
	public Sun(int x,int y,int valeur) {
		this.x = x;
		this.y = y;
		this.valeur=valeur;
	}
	
	public Float draw() {
		return new Ellipse2D.Float(x,y,taille,taille);
	}
	
	public int getValeur() {
		return valeur;
	}
	
	
	
}
