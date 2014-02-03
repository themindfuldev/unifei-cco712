package pacote_11877_12643.modelo;

import java.util.*;

/**
 * Forma desenhavel de um segmento de reta.
 */
public class Segmento extends FormaPlotavel 
{
	// Atributos
	/** 
	 * Pontos que delimitam o segmento.
	 */
	private Ponto pInicial, pFinal;
	
	// Construtores
	public Segmento(Ponto p1, Ponto p2) 
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
		
		// Calcula os pontos, e armazena na lista.
		listaPontos = preencheLista();
	}

	// Metodos
	/**
	 * Preenche a lista de pontos.
	 * Utiliza o algoritmo DDA inteiro.
	 */
   private List<Ponto> preencheLista()
   {
   	// Declaracao de variaveis.
		int x, y, xMin, xMax, yMin, yMax, i, erro;
		Ponto ponto;
		List<Ponto> lista;		
		
		// Inicializacao.
		lista = new LinkedList<Ponto>();
		
		// Reta vertical
		if (pInicial.getX() == pFinal.getX())
		{
			yMin = (pInicial.getY() <= pFinal.getY())? pInicial.getY(): pFinal.getY();			
			yMax = (pInicial.getY() >= pFinal.getY())? pInicial.getY(): pFinal.getY();			
			
			for (y=yMin; y<=yMax; y++)
			{
				ponto = new Ponto(pInicial.getX(), y);
				lista.add(ponto);
			}				
			return lista;
		}
		
		// Reta horizontal
		if (pInicial.getY() == pFinal.getY())
		{
			xMin = (pInicial.getX() <= pFinal.getX())? pInicial.getX(): pFinal.getX();			
			xMax = (pInicial.getX() >= pFinal.getX())? pInicial.getX(): pFinal.getX();			
			
			for (x=xMin; x<=xMax; x++)
			{
				ponto = new Ponto(x, pInicial.getY());
				lista.add(ponto);
			}				
			return lista;
		}		
		
		// Algoritmo DDA inteiro
		int deltaX = pFinal.getX()-pInicial.getX();
		int deltaY = pFinal.getY()-pInicial.getY();

		// Adiciona o ponto inicial.
		lista.add(pInicial);
		
		erro = 0;             
		x = pInicial.getX();
		y = pInicial.getY();
		
		// Adiciona os pontos intermedi�rios.
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
		   			lista.add(ponto);
		   			erro += deltaY;
		   		}
		   		else
		   		{
		   			x++;
		   			y++;
		   			ponto = new Ponto(x, y);
		   			lista.add(ponto);
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
		   			lista.add(ponto);
		   			erro += deltaY - deltaX;		   			
		   		}
		   		else
		   		{
		   			y++;
		   			ponto = new Ponto(x, y);
		   			lista.add(ponto);
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
		   			lista.add(ponto);
		   			erro += deltaY;
		   		}
		   		else
		   		{
		   			x--;
		   			y++;
		   			ponto = new Ponto(x, y);
		   			lista.add(ponto);
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
		   			lista.add(ponto);
		   			erro += deltaY + deltaX;		   			
		   		}
		   		else
		   		{
		   			y++;
		   			ponto = new Ponto(x, y);
		   			lista.add(ponto);
		   			erro += deltaX;		   			
		   		}		   		
		   	}				
			}
		}
		
		// Adiciona o ponto final.
		lista.add(pFinal);
		
		return lista;
   }
   
   public double getM()
   {
   	return ((double)pFinal.getY()-pInicial.getY())/(pFinal.getX()-pInicial.getX());
   }
   
   public double getMInv()
   {
   	return ((double)pFinal.getX()-pInicial.getX())/(pFinal.getY()-pInicial.getY());
   }
   
	public Segmento corte(Viewport janela)
	{
		Set<Direcoes> dirIni, dirFim, direcao;
		Ponto p, p1, p2, pIntersecao;
		
		dirIni = new HashSet<Direcoes>();
		dirFim = new HashSet<Direcoes>();

		p1 = pInicial;
		p2 = pFinal;
		pIntersecao = null;
		
		janela.preencheDirecoes(p1, dirIni);
		janela.preencheDirecoes(p2, dirFim);
		
		while (dirIni.isEmpty() == false || dirFim.isEmpty() == false)
		{
			direcao = new HashSet<Direcoes>();
			direcao.addAll(dirIni);
			direcao.retainAll(dirFim);
			
			if (direcao.isEmpty() == false)
				return null;
			
			if (dirIni.isEmpty() == true)
			{
				direcao = dirFim;
				p =  p2;
			}
			else
			{
				direcao = dirIni;
				p = p1;
			}
			
			if (direcao.contains(Direcoes.ESQUERDA))
				pIntersecao = janela.intersecaoBordaEsquerda(this, p);
			else if (direcao.contains(Direcoes.DIREITA))
				pIntersecao = janela.intersecaoBordaDireita(this, p);
			
			if (direcao.contains(Direcoes.INFERIOR))
				pIntersecao = janela.intersecaoBordaInferior(this, p);
			else if (direcao.contains(Direcoes.SUPERIOR))
				pIntersecao = janela.intersecaoBordaSuperior(this, p);
			
			if (direcao == dirIni)
			{
				p1 = pIntersecao;
				janela.preencheDirecoes(p1, dirIni);
			}
			else
			{
				p2 = pIntersecao;
				janela.preencheDirecoes(p2, dirFim);
			}
		}
		
		return new Segmento(p1, p2);
	}

	public Ponto getPFinal()
	{
		return pFinal;
	}

	public Ponto getPInicial()
	{
		return pInicial;
	}

}
