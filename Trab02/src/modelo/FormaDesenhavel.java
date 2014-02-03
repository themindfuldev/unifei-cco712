package modelo;

import java.awt.*;

/**
 * Representa uma objeto que pode ser desenhado em um contexto
 * gr�fico Graphics, e define a cor de seu desenho.
 */
public abstract class FormaDesenhavel
{
	/**
	 * Cor que ser� desenhada
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
	
	// M�todos
	public Color getCor()
   {
   	return cor;
   }
	
	protected void setCor(Color cor)
	{
		this.cor = cor;
	}

	// M�todos abstratos
	/**
	 * Especifica o procedimento de desenho da forma.
	 * 
	 * @param desenho	Contexto gr�fico relacionado.
	 */
	public abstract void desenhar(Graphics desenho);
	/**
	 * Altera a cor da forma e de todas as formas pertencentes.
	 * @param cor	Cor a ser alterada.
	 */
	public abstract void mudarCor(Color cor);
}
