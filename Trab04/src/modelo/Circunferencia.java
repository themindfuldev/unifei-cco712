package modelo;

import java.awt.*;
import java.util.List;
import modelo.algoritmos.*;

/**
 * Forma desenh�vel do segmento de reta, 
 * utilizando um modelo matem�tico.
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
	/**
	 * Algoritmo para a obten��o dos pontos.
	 */
	private AlgoritmoCircunferencia algoritmo;
	
	// Relacionamentos
	/**
	 * Lista de pontos que pertencem � circunfer�ncia.
	 */
	private List<Ponto> listaPontos;
	
	// Construtores
	public Circunferencia(Ponto centro, int raio, AlgoritmoCircunferencia algoritmo) 
	{	
		this.centro = centro;
		this.raio = raio;
		this.algoritmo = algoritmo;
				
		// Calcula os pontos da reta, e armazena na lista.
		this.listaPontos = this.algoritmo.preencheLista(centro, raio);
	}

	// M�todos	
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
	
}
