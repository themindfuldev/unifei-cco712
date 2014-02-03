package pacote_11877_12643.modelo;

import java.util.*;

/**
 * Forma desenhavel de uma circunferencia.
 */
public class Circunferencia extends FormaPlotavel
{
	// Atributos
	/** 
	 * Ponto central.
	 */
	private Ponto centro;
	/**
	 * Raio. 
	 */
	private int raio;
	
	// Construtores
	public Circunferencia()
	{
		centro = null;
		raio = 0;
	}
	
	public Circunferencia(Ponto centro, int raio) 
	{	
		this.centro = centro;
		this.raio = raio;
				
		// Calcula os pontos, e armazena na lista.
		this.listaPontos = preencheLista();
	}

	// Metodos	
	/**
	 * Preenche a lista de pontos.
	 * Utiliza o algoritmo DDA inteiro.
	 */
	private List<Ponto> preencheLista()
	{
   	// Declaracao de variaveis.
		int x, y, xDist, xCentro, yCentro, yDist;
		double dA, dB, s;
		Ponto p;
		List<Ponto> lista = new LinkedList<Ponto>();
		
		// Inicializacao.
		xCentro = centro.getX();
		yCentro = centro.getY();
		yDist = raio;

		// Percorre ao longo do eixo X.
		for (xDist = 0; xDist <= (double) raio/Math.sqrt(2); xDist++)
		{
			// Calcula dA, dB e s.
			dA = Math.pow((xDist+1), 2) + Math.pow((yDist), 2) - Math.pow(raio, 2);
			dB = Math.pow((xDist+1), 2) + Math.pow((yDist-1), 2) - Math.pow(raio, 2);
			s = dA + dB;
			if (s > 0) yDist--;
			
			// Determina os pontos e suas reflexões.
			x = xCentro + xDist;
			y = yCentro - yDist;		
			p = new Ponto(x, y);
			lista.add(p);
			
			y = yCentro + yDist;		
			p = new Ponto(x, y);
			lista.add(p);
			
			
			x = xCentro - xDist;
			y = yCentro - yDist;		
			p = new Ponto(x, y);
			lista.add(p);
			
			y = yCentro + yDist;		
			p = new Ponto(x, y);
			lista.add(p);
			
			
			x = xCentro + yDist;
			y = yCentro - xDist;		
			p = new Ponto(x, y);
			lista.add(p);
			
			y = yCentro + xDist;		
			p = new Ponto(x, y);
			lista.add(p);
			
			
			x = xCentro - yDist;
			y = yCentro - xDist;		
			p = new Ponto(x, y);
			lista.add(p);
			
			y = yCentro + xDist;		
			p = new Ponto(x, y);
			lista.add(p);
		}
		return lista;
	}	

	/**
	 * @return  the centro
	 * @uml.property  name="centro"
	 */
	public Ponto getCentro()
	{
		return centro;
	}

	/**
	 * @return  the raio
	 * @uml.property  name="raio"
	 */
	public int getRaio()
	{
		return raio;
	}
}
