package pacote_11877_12643.modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * Forma desenhável do segmento de reta, 
 * utilizando um modelo matemático.
 */
public class Circunferencia implements FormaDesenhavel 
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
	
	// Relacionamentos
	/**
	 * Lista de pontos que pertencem à circunferência.
	 */
	private List<Ponto> listaPontos;
	
	// Construtores
	public Circunferencia(Ponto centro, int raio) 
	{	
		this.centro = centro;
		this.raio = raio;
		
	// Calcula os pontos, e armazena na lista.
		this.listaPontos = preencheLista();		
	}

	// Métodos	
   /**
    * Compara dois objetos.  
    */
   public boolean equals(Object circunferenciaObj)
   {
		boolean retorno = false;
		
		if (circunferenciaObj instanceof Circunferencia)
		{
			//	Verifica se os objetos possuem os mesmos atributos.
			Circunferencia circunferencia = (Circunferencia) circunferenciaObj;
			if (this.centro.equals(circunferencia.centro) && 
					this.raio == circunferencia.raio)
				retorno = true;
		}
		
	   return retorno;
   }
	
	public void desenhar(Graphics desenho, Color cor)
	{
		for (Ponto ponto: listaPontos)
			ponto.desenhar(desenho, cor);
	}	
	
	// Metodos
	public List<Ponto> preencheLista()
	{
		int xCentro, yCentro, varSen, varCos;		
		double teta;
		Ponto p;
		List<Ponto> lista = new LinkedList<Ponto>();
		
		xCentro = centro.getX();
		yCentro = centro.getY();
		
		for(teta = 0;teta <= Math.PI/4; teta+=Math.toRadians(1))
		{
			varCos = (int) (raio*Math.cos(teta));
			varSen = (int) (raio*Math.sin(teta));
			
			p = new Ponto(xCentro+varCos, yCentro+varSen);
			lista.add(p);
			p = new Ponto(xCentro+varSen, yCentro+varCos);
			lista.add(p);			
			
			p = new Ponto(xCentro+varCos, yCentro-varSen);
			lista.add(p);
			p = new Ponto(xCentro-varSen, yCentro+varCos);
			lista.add(p);			
			
			p = new Ponto(xCentro-varCos, yCentro+varSen);
			lista.add(p);
			p = new Ponto(xCentro+varSen, yCentro-varCos);
			lista.add(p);			
			
			p = new Ponto(xCentro-varCos, yCentro-varSen);
			lista.add(p);
			p = new Ponto(xCentro-varSen, yCentro-varCos);
			lista.add(p);	
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
