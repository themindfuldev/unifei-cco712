package modelo;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Forma desenhável do segmento de reta, 
 * utilizando um modelo matemático.
 */
public class Segmento extends Reta implements Comparable
{
	// Atributos
	/** 
	 * Pontos que delimitam o segmento.
	 */
	private Ponto p1, p2;
	
	// Relacionamentos
	/**
	 * Lista de pontos que pertencem ao segmento.
	 */
	private List<Ponto> listaPontos;
	
	// Construtores
	public Segmento(Color cor, Ponto p1, Ponto p2) 
	{		
		super(cor);
				
		this.p1 = p1;
		this.p2 = p2;
		this.listaPontos = new LinkedList<Ponto>();
		
		// Calcula os valores de m e b.
		if (p1.getX() == p2.getX())
		{
			this.m = Float.POSITIVE_INFINITY;
			this.b = null;
		}
		else
		{
			this.m = (p1.getY() - p2.getY())/(p1.getX() - p2.getX());			
			this.b = (int) (-m * p1.getX() + p1.getY());				
		}
		
		// Calcula os pontos da reta, e armazena na lista.
		preencheLista();
	}

	public Segmento(Ponto p1, Ponto p2) 
	{
		this(Color.BLACK, p1, p2);
	}

	// Métodos
	public Ponto getP1()
   {
   	return p1;
   }

	public Ponto getP2()
   {
   	return p2;
   }	

   /**
    * Compara dois objetos.  
    */
   public boolean equals(Object segmentoObj)
   {
		boolean retorno = false;
		
		if (segmentoObj instanceof Segmento)
		{
			//	Verifica se os objetos possuem os mesmos atributos.
			Segmento segmento = (Segmento) segmentoObj;
			if (super.equals(segmento) && this.p1.equals(segmento.p1) && 
					this.p2.equals(segmento.p2))
				retorno = true;
		}
		
	   return retorno;
   }

   /**
    * Imprime o segmento.
    */
   public String toString()
   {
		return "P1 = " + p1 + " e " + "P2 = " + p2;
   }	
	
	// Método haInsersecao: retorna se os segmentos fazem interseção.
	/**
	 * Verifica se dois segmentos fazem interseção.
	 * @param seg1	Segmento 1 
	 * @param seg2	Segmento 2 
	 * @return Se há a interseção ou não. 
	 */
   public static boolean haIntersecao(Segmento seg1, Segmento seg2)
	{		
		// Se estiverem em retas paralelas.
		if ((seg1.m == seg2.m || (Float.isInfinite(seg1.m) && 
			  Float.isInfinite(seg2.m))) && 
			 (seg1.b != seg2.b || (seg1.b == null && seg1.b == null)))
			return false;
		
		// Calcula os limites Y destes segmentos.
		int seg1Ymin = (seg1.p1.getY() < seg1.p2.getY())? 
				seg1.p1.getY(): seg1.p2.getY();			
		int seg1Ymax = (seg1.p1.getY() > seg1.p2.getY())? 
				seg1.p1.getY(): seg1.p2.getY();
		int seg2Ymin = (seg2.p1.getY() < seg2.p2.getY())? 
				seg2.p1.getY(): seg2.p2.getY();
		int seg2Ymax = (seg2.p1.getY() > seg2.p2.getY())? 
				seg2.p1.getY(): seg2.p2.getY();
		
		// Se estiverem na mesma reta.
		if ((seg1.m == seg2.m) || 
			 (Float.isInfinite(seg1.m) && Float.isInfinite(seg2.m)))
		{			
			if ((seg1Ymin < seg2Ymax && seg2Ymin > seg1Ymax) || 
				 ((seg2Ymin < seg1Ymax && seg1Ymin > seg2Ymax)))
				return false;
			else
				return true;
		}
		
		// Se estiverem em retas concorrentes.	
		// Coordenadas x e y do ponto de interseção.
		int xInt, yInt;
		
		// Calcula o ponto de interseção:
		// Se seg1 tiver m infinito.
		if (seg1.b == null)
		{
			xInt = seg1.p1.getX();
			yInt = (int) (seg2.m*xInt + seg2.b);			
		}
		// Se seg2 tiver m infinito.
		else if (seg2.b == null)
		{
			xInt = seg2.p1.getX();
			yInt = (int) (seg1.m*xInt + seg1.b);			
		}
		// Senão
		else 
		{
			xInt = (int) ((seg2.b - seg1.b)/(seg1.m - seg2.m));
			yInt = (int) (seg1.m*xInt + seg1.b);
		}
		
		// Calcula os limites X destes segmentos.
		int seg1Xmin = (seg1.p1.getX() < seg1.p2.getX())? 
				seg1.p1.getX(): seg1.p2.getX();			
		int seg1Xmax = (seg1.p1.getX() > seg1.p2.getX())? 
				seg1.p1.getX(): seg1.p2.getX();
		int seg2Xmin = (seg2.p1.getX() < seg2.p2.getX())? 
				seg2.p1.getX(): seg2.p2.getX();
		int seg2Xmax = (seg2.p1.getX() > seg2.p2.getX())? 
				seg2.p1.getX(): seg2.p2.getX();		
		
		// Verifica se o ponto de interseção pertence a ambos segmentos.
		if (yInt <= seg1Ymax && yInt >= seg1Ymin && 
			 yInt <= seg2Ymax && yInt >= seg2Ymin &&
			 xInt <= seg1Xmax && xInt >= seg1Xmin && 
			 xInt <= seg2Xmax && xInt >= seg2Xmin)
			return true;
		else
			return false;		
		
	}
	
	/**
	 * Compara dois objetos mensuráveis.
	 */
   public int compareTo(Object segmentoObj)
   {
		int retorno = 0;
		
		if (segmentoObj instanceof Segmento)
		{
			// Compara os objetos quanto aos atributos.
			Segmento segmento = (Segmento) segmentoObj;
			
			int comparacao = this.p1.compareTo(segmento.p1);			
			if (comparacao != 0) retorno = comparacao;
			else
			{
				comparacao = this.p2.compareTo(segmento.p2);
				retorno = comparacao;
			}
		}

	   return retorno;
   }	
	
	/**
	 * Preenche a lista de pontos, a partir da equação da reta.
	 */
   private void preencheLista()
   {
   	// Declaração de variáveis.
		int y, xMin, xMax, yMin, yMax;
		Ponto ponto;
		
		// Reta vertical
		if (Float.isInfinite(m) == true)
		{
			yMin = (p1.getY() <= p2.getY())? p1.getY(): p2.getY();			
			yMax = (p1.getY() >= p2.getY())? p1.getY(): p2.getY();			
			
			for (y=yMin; y<=yMax; y++)
			{
				ponto = new Ponto(getCor(), p1.getX(), y);
				listaPontos.add(ponto);
			}
				
			return;
		}
		
		// Outras retas
		xMin = (p1.getX() <= p2.getX())? p1.getX(): p2.getX();			
		xMax = (p1.getX() >= p2.getX())? p1.getX(): p2.getX();
		
		while (xMin <= xMax) 
		{
			y = calculaY(xMin);
			ponto = new Ponto(getCor(), xMin, y);
			listaPontos.add(ponto);
		   xMin++; 
		}		
   }
	
	public void desenhar(Graphics desenho)
	{
		for (Ponto ponto: listaPontos)
			ponto.desenhar(desenho);
	}	
	
   public void mudarCor(Color cor)
   {
   	for (Ponto ponto: listaPontos)
   		ponto.setCor(cor);	   
   }
	
}
