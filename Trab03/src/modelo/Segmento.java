package modelo;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Forma desenhável do segmento de reta, 
 * utilizando um modelo matemático.
 */
public class Segmento implements FormaDesenhavel, Comparable 
{
	// Atributos
	/** 
	 * Pontos que delimitam o segmento.
	 */
	private Ponto pInicial, pFinal;
	
	// Relacionamentos
	/**
	 * Lista de pontos que pertencem ao segmento.
	 */
	private List<Ponto> listaPontos;
	
	// Construtores
	public Segmento(Ponto p1, Ponto p2) 
	{	
		this.listaPontos = new LinkedList<Ponto>();
				
		// Calcula os deltas X e Y.
		int deltaX = Math.abs(p1.getX()-p2.getX());
		int deltaY = Math.abs(p1.getY()-p2.getY());
		
		// Identifica os pontos inicial e final.
		if (deltaY >= deltaX)
		{
			if (p1.getY() > p2.getY())
			{
				this.pInicial = p2;
				this.pFinal = p1;
			}
			else
			{
				this.pInicial = p1;
				this.pFinal = p2;
			}
		}
		else
		{
			if (p1.getX() > p2.getX())
			{
				this.pInicial = p2;
				this.pFinal = p1;
			}
			else
			{
				this.pInicial = p1;
				this.pFinal = p2;
			}			
		}
				
		// Calcula os pontos da reta, e armazena na lista.
		preencheLista();
	}

	// Métodos
	public Ponto getPInicial()
   {
   	return pInicial;
   }

	public Ponto getPFinal()
   {
   	return pFinal;
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
			if (this.pInicial.equals(segmento.pInicial) && 
					this.pFinal.equals(segmento.pFinal))
				retorno = true;
		}
		
	   return retorno;
   }

   /**
    * Imprime o segmento.
    */
   public String toString()
   {
		return "pInicial = " + pInicial + " e " + "pFinal = " + pFinal;
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
   	Segmento[] segmentos = {seg1, seg2};   	
   	float[] m = new float[2];
   	Integer[] b = new Integer[2];
   	
   	for (int i=0; i<2; i++)
   	{
			if (segmentos[i].pInicial.getX() == segmentos[i].pFinal.getX())
			{
				m[i] = Float.POSITIVE_INFINITY;
				b[i] = null;
			}
			else
			{
				m[i] = (segmentos[i].pInicial.getY() - segmentos[i].pFinal.getY())/(segmentos[i].pInicial.getX() - segmentos[i].pFinal.getX());			
				b[i] = (int) (-m[i] * segmentos[i].pInicial.getX() + segmentos[i].pInicial.getY());				
			}
   	}
   	
		// Se estiverem em retas paralelas.
		if ((m[0] == m[1] || (Float.isInfinite(m[0]) && 
			  Float.isInfinite(m[1]))) && 
			 (b[0] != b[1] || (b[0] == null && b[1] == null)))
			return false;
		
		// Calcula os limites Y destes segmentos.
		int seg1Ymin = (seg1.pInicial.getY() < seg1.pFinal.getY())? 
				seg1.pInicial.getY(): seg1.pFinal.getY();			
		int seg1Ymax = (seg1.pInicial.getY() > seg1.pFinal.getY())? 
				seg1.pInicial.getY(): seg1.pFinal.getY();
		int seg2Ymin = (seg2.pInicial.getY() < seg2.pFinal.getY())? 
				seg2.pInicial.getY(): seg2.pFinal.getY();
		int seg2Ymax = (seg2.pInicial.getY() > seg2.pFinal.getY())? 
				seg2.pInicial.getY(): seg2.pFinal.getY();
		
		// Se estiverem na mesma reta.
		if ((m[0] == m[1]) || 
			 (Float.isInfinite(m[0]) && Float.isInfinite(m[1])))
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
		if (b[0] == null)
		{
			xInt = seg1.pInicial.getX();
			yInt = (int) (m[1]*xInt + b[1]);			
		}
		// Se seg2 tiver m infinito.
		else if (b[1] == null)
		{
			xInt = seg2.pInicial.getX();
			yInt = (int) (m[0]*xInt + b[0]);			
		}
		// Senão
		else 
		{
			xInt = (int) ((b[1] - b[0])/(m[0] - m[1]));
			yInt = (int) (m[0]*xInt + b[0]);
		}
		
		// Calcula os limites X destes segmentos.
		int seg1Xmin = (seg1.pInicial.getX() < seg1.pFinal.getX())? 
				seg1.pInicial.getX(): seg1.pFinal.getX();			
		int seg1Xmax = (seg1.pInicial.getX() > seg1.pFinal.getX())? 
				seg1.pInicial.getX(): seg1.pFinal.getX();
		int seg2Xmin = (seg2.pInicial.getX() < seg2.pFinal.getX())? 
				seg2.pInicial.getX(): seg2.pFinal.getX();
		int seg2Xmax = (seg2.pInicial.getX() > seg2.pFinal.getX())? 
				seg2.pInicial.getX(): seg2.pFinal.getX();		
		
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
			
			int comparacao = this.pInicial.compareTo(segmento.pInicial);			
			if (comparacao != 0) retorno = comparacao;
			else
			{
				comparacao = this.pFinal.compareTo(segmento.pFinal);
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
		int x, y, xMin, xMax, yMin, yMax, i, erro;
		Ponto ponto;
		
		// Reta vertical
		if (pInicial.getX() == pFinal.getX())
		{
			yMin = (pInicial.getY() <= pFinal.getY())? pInicial.getY(): pFinal.getY();			
			yMax = (pInicial.getY() >= pFinal.getY())? pInicial.getY(): pFinal.getY();			
			
			for (y=yMin; y<=yMax; y++)
			{
				ponto = new Ponto(pInicial.getX(), y);
				listaPontos.add(ponto);
			}
				
			return;
		}
		
		// Reta horizontal
		if (pInicial.getY() == pFinal.getY())
		{
			xMin = (pInicial.getX() <= pFinal.getX())? pInicial.getX(): pFinal.getX();			
			xMax = (pInicial.getX() >= pFinal.getX())? pInicial.getX(): pFinal.getX();			
			
			for (x=xMin; x<=xMax; x++)
			{
				ponto = new Ponto(x, pInicial.getY());
				listaPontos.add(ponto);
			}
				
			return;
		}		
		
		// Algoritmo DDA inteiro
		int deltaX = pFinal.getX()-pInicial.getX();
		int deltaY = pFinal.getY()-pInicial.getY();

		// Adiciona o ponto inicial.
		listaPontos.add(pInicial);
		
		erro = 0;             
		x = pInicial.getX();
		y = pInicial.getY();
		
		// Adiciona os pontos intermediários.
		if (deltaX >= 0)
		{			
			// Caso 1: deltaX > 0, deltaY > 0, 0 < m < 1
			if (Math.abs(deltaX) >= Math.abs(deltaY))
			{				
		   	for (i = 1; i <= Math.abs(deltaX)-1; i++){		   		
		   		if (erro <= 0)
		   		{		   			
		   			x++;		   			
		   			ponto = new Ponto(x, y);
		   			listaPontos.add(ponto);
		   			erro += deltaY;
		   		}
		   		else
		   		{
		   			x++;
		   			y++;
		   			ponto = new Ponto(x, y);
		   			listaPontos.add(ponto);
		   			erro += deltaY - deltaX;		   			
		   		}		   		
		   	}
			}	
			// Caso 2: deltaX > 0, deltaY > 0, m > 1
			else
			{
				for (i = 1; i <= Math.abs(deltaY)-1; i++){		   		
		   		if (erro < 0)
		   		{
		   			x++;
		   			y++;
		   			ponto = new Ponto(x, y);
		   			listaPontos.add(ponto);
		   			erro += deltaY - deltaX;		   			
		   		}
		   		else
		   		{
		   			y++;
		   			ponto = new Ponto(x, y);
		   			listaPontos.add(ponto);
		   			erro -= deltaX;		   			
		   		}		   		
		   	}				
			}
		}
		else
		{
			// Caso 3: deltaX < 0, deltaY > 0, -1 < m < 0
			if (Math.abs(deltaX) >= Math.abs(deltaY))
			{
				for (i = 1; i <= Math.abs(deltaX)-1; i++){		   		
		   		if (erro < 0)
		   		{
		   			x--;
		   			ponto = new Ponto(x, y);
		   			listaPontos.add(ponto);
		   			erro += deltaY;
		   		}
		   		else
		   		{
		   			//x++;
		   			x--;
		   			y++;
		   			ponto = new Ponto(x, y);
		   			listaPontos.add(ponto);
		   			erro += deltaY + deltaX;		   			
		   		}		   		
		   	}
			}	
			// Caso 4: deltaX < 0, deltaY > 0, m < -1
			else
			{
				for (i = 1; i <= Math.abs(deltaY)-1; i++){		   		
		   		if (erro < 0)
		   		{
		   			x--;
		   			y++;
		   			ponto = new Ponto(x, y);
		   			listaPontos.add(ponto);
		   			erro += deltaY + deltaX;		   			
		   		}
		   		else
		   		{
		   			y++;
		   			ponto = new Ponto(x, y);
		   			listaPontos.add(ponto);
		   			erro += deltaX;		   			
		   		}		   		
		   	}				
			}
		}
		
		// Adiciona o ponto final.
		listaPontos.add(pFinal);		
   }
	
	public void desenhar(Graphics desenho, Color cor)
	{
		for (Ponto ponto: listaPontos)
			ponto.desenhar(desenho, cor);
	}	
	
}
