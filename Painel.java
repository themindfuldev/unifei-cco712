package cih;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import cdp.*;

public class Painel extends JPanel{
	
	
	private Ponto pi,pf;	
	//construtor
	public Painel(){		
		
		pi = new Ponto(50,50);
		pf = new Ponto(100,100);
		
		
	
		
		setBackground(Color.WHITE);
	}
	public void drawPixel(Graphics g,Ponto p, Color c){
		g.setColor(c);	
		g.drawLine(p.getX(),p.getY(),p.getX(),p.getY());		
	}
	
	public void desenhaReta(Graphics g, Ponto pi, Ponto pf, Color c){
		int i,y,m;
		Ponto p;
		m = (pf.getY() - pi.getY())/(pf.getX() - pi.getX());
		
		for (i=pi.getX();i<=pf.getX();i++){
			y = m*i - m*pi.getX() + pi.getY();
			p = new Ponto(i,y);
			this.drawPixel(g,p,c);
		}
	}
	
	public void desenhaArco(Graphics g, Ponto pi, Ponto pf, int r, Color c){
		int i,y;
		Ponto p;		
		
		for (i=pi.getX();i<=pf.getX();i++){
			y = -(int)Math.sqrt(Math.pow(r,2)-Math.pow(i-pi.getX(),2)+pi.getX());
			p = new Ponto(i,y);
			this.drawPixel(g,p,c);
		}
	}
	public void desenhaPontos(Graphics g, ArrayList pontos, Color c){
		Ponto p;		
		
		while(pontos.isEmpty()){
			p = (Ponto)pontos.remove(0);		
			this.drawPixel(g,p,c);
		}
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);		
		this.desenhaReta(g,this.pi,this.pf,Color.BLACK);
		this.desenhaArco(g,this.pi,this.pf,100,Color.BLACK);
	}
}
