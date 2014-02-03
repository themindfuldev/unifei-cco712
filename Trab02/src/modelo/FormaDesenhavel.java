package modelo;

import java.awt.*;

/**
 * Representa uma objeto que pode ser desenhado em um contexto
 * gráfico Graphics, e define a cor de seu desenho.
 */
public abstract class FormaDesenhavel
{
	/**
	 * Cor que será desenhada
	 */
	private Color cor;	
	
	// Construtores
	protected FormaDesenhavel()
   {
	   this.cor = Color.BLACK;
   }	

	protected FormaDesenhavel(Color cor)
   {
	   this.cor = cor;
   }	
	
	// Métodos
	public Color getCor()
   {
   	return cor;
   }
	
	protected void setCor(Color cor)
	{
		this.cor = cor;
	}

	// Métodos abstratos
	/**
	 * Especifica o procedimento de desenho da forma.
	 * 
	 * @param desenho	Contexto gráfico relacionado.
	 */
	public abstract void desenhar(Graphics desenho);
	/**
	 * Altera a cor da forma e de todas as formas pertencentes.
	 * @param cor	Cor a ser alterada.
	 */
	public abstract void mudarCor(Color cor);
}
