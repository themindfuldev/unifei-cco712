package pacote_11877_12643.modelo;

import java.util.*;

/**
 * Forma desenhavel de um arco de circunferência.
 */
public class ArcoDeCircunferencia extends FormaPlotavel
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
	/**
	 * Ângulo inicial, em radianos.
	 */
	private double anguloInicial;
	/**
	 * Ângula final, em radianos.
	 */
	private double anguloFinal;	

	// Construtores
	/**
	 * Construtor com os ângulos em radianos.
	 */
	public ArcoDeCircunferencia(Ponto centro, int raio, double anguloInicial,
	      double anguloFinal)
	{
		this.centro = centro;
		this.raio = raio;
		this.anguloInicial = anguloInicial;
		this.anguloFinal = anguloFinal;

		if (anguloInicial > Math.PI * 2) this.anguloInicial -= Math.PI * 2;
		if (anguloFinal > Math.PI * 2) this.anguloFinal -= Math.PI * 2;
		
		// Calcula os pontos, e armazena na lista.
		this.listaPontos = preencheLista();		
	}

	/**
	 * Construtor com os ângulos em graus.
	 */
	public ArcoDeCircunferencia(Ponto centro, int raio, int anguloInicial,
	      int anguloFinal)
	{
		this(centro, raio, Math.toRadians(anguloInicial), Math
		      .toRadians(anguloFinal));
	}
	
	/**
	 * Construtor com pontos.
	 */
	public ArcoDeCircunferencia(Ponto centro, int raio, Ponto pontoInicial,
			Ponto pontoFinal)
	{
		this(centro, raio, 
				Operacoes.anguloDeArco(centro, raio, pontoInicial), 
				Operacoes.anguloDeArco(centro, raio, pontoFinal));
	}

	// Metodos
	/**
	 * Preenche a lista de pontos.
	 * Utiliza o algoritmo incremental.
	 */
	private List<Ponto> preencheLista()
	{
		//	Declaracao de variaveis.
		List<Ponto> lista;		
		Ponto p;
		int x, y, xCentro, yCentro;
		double teta, dX, dY, dXtemp, dYtemp, varSen, varCos, inc;

		// Inicializacao.
		lista = new LinkedList<Ponto>();
		
		xCentro = centro.getX();
		yCentro = centro.getY();
		
		inc = 1.0 / raio;
		varSen = Math.sin(inc);
		varCos = Math.cos(inc);

		dX = x = (int) Math.round(raio*Math.cos(anguloInicial));
		dY = y = (int) Math.round(raio*Math.sin(anguloInicial));

		// Determina o percurso do preenchimento.
		// Percurso do ângulo inicial ao final.
		if (anguloInicial < anguloFinal)
		{
			// Percorre os ângulos e preenche.
			for (teta = anguloInicial; teta <= anguloFinal; teta += inc)
			{
				p = new Ponto(xCentro + x, yCentro - y);
				lista.add(p);
				
				dXtemp = dX * varCos - dY * varSen;
				dYtemp = dY * varCos + dX * varSen;

				dX = dXtemp;
				dY = dYtemp;

				x = (int) dX;
				y = (int) dY;
			}
			
			return lista;
		}

		// Percurso do ângulo final ao inicial.
		
		// Percorre os ângulos e preenche.		
		for (teta = 0; teta <= anguloFinal; teta += inc)
		{
			p = new Ponto(xCentro + x, yCentro - y);
			lista.add(p);
			
			dXtemp = dX * varCos - dY * varSen;
			dYtemp = dY * varCos + dX * varSen;

			dX = dXtemp;
			dY = dYtemp;

			x = (int) dX;
			y = (int) dY;
		}		
		for (teta = anguloInicial; teta < 2 * Math.PI; teta += inc)
		{
			p = new Ponto(xCentro + x, yCentro - y);
			lista.add(p);
			
			dXtemp = dX * varCos - dY * varSen;
			dYtemp = dY * varCos + dX * varSen;

			dX = dXtemp;
			dY = dYtemp;

			x = (int) dX;
			y = (int) dY;
		}
		return lista;	
	}

	public Ponto getCentro()
	{
		return centro;
	}

	public int getRaio()
	{
		return raio;
	}
}
