package pacote_11877_12643.modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

/**
 * @author  tiago
 */
public class FormaPlotavel implements FormaDesenhavel
{
	// Relacionamentos
	/**
	 * Lista de pontos que pertencem ao segmento.
	 */
	protected List<Ponto> listaPontos;
	
	// Construtor
	public FormaPlotavel()
	{
		listaPontos = new ArrayList<Ponto>();
	}
	
	// Metodos
	public void desenhar(Graphics desenho, Color cor)
	{
		for (Ponto ponto: listaPontos)
			ponto.desenhar(desenho, cor);
	}

	/**
	 * @return  the listaPontos
	 * @uml.property  name="listaPontos"
	 */
	public List<Ponto> getListaPontos()
   {
   	return listaPontos;
   }
	
	/**
	 * Valida a forma plotavel de acordo com um viewport. 
	 * @param altura
	 * @param largura
	 * @return	se e valido ou nao.
	 */
	public boolean validar(int altura, int largura)
	{
		// Declaracao de variaveis.
		boolean valida;
		
		// Varredura e alidacao.
		valida = true;
		for (Ponto ponto: listaPontos)
		{
			if (!(ponto.getX() >= 0 && ponto.getX() <= largura &&
					ponto.getY() >= 0 && ponto.getY() <= altura))
			{
				valida = false;
				break;
			}
		}
		
		return valida;
	}
}
