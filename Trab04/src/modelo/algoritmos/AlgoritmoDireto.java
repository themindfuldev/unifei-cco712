package modelo.algoritmos;

import java.util.*;

import modelo.Ponto;

public class AlgoritmoDireto implements AlgoritmoCircunferencia
{
	
	public List<Ponto> preencheLista(Ponto centro, int raio)
	{
		int x, y, dist, xCentro, yCentro, xEsq, xDir;
		double variacao;
		Ponto p;
		List<Ponto> lista = new LinkedList<Ponto>();
		
		xCentro = centro.getX();
		yCentro = centro.getY();
		xEsq = xCentro - raio;
		xDir = xCentro + raio;
		
		for(dist = 0; dist <= raio/2; dist++)
		{			
			x = xEsq + dist;
			variacao = Math.sqrt(Math.pow(raio, 2) - Math.pow(x - xCentro, 2));
			y = (int)(yCentro + variacao);
			p = new Ponto(x,y);
			lista.add(p);
			y = (int)(yCentro - variacao);
			p = new Ponto(x,y);
			lista.add(p);
			
			x = xDir - dist;
			variacao = Math.sqrt(Math.pow(raio, 2) - Math.pow(x - xCentro, 2));
			y = (int)(yCentro + variacao);
			p = new Ponto(x,y);
			lista.add(p);
			y = (int)(yCentro - variacao);
			p = new Ponto(x,y);
			lista.add(p);
			
			x = xCentro + dist;
			variacao = Math.sqrt(Math.pow(raio, 2) - Math.pow(x - xCentro, 2));
			y = (int)(yCentro + variacao);
			p = new Ponto(x,y);
			lista.add(p);
			y = (int)(yCentro - variacao);
			p = new Ponto(x,y);
			lista.add(p);
			
			x = xCentro - dist;
			variacao = Math.sqrt(Math.pow(raio, 2) - Math.pow(x - xCentro, 2));
			y = (int)(yCentro + variacao);
			p = new Ponto(x,y);
			lista.add(p);
			y = (int)(yCentro - variacao);
			p = new Ponto(x,y);
			lista.add(p);		
			
		}

		return lista;
	}

}
