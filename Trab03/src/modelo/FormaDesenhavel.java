package modelo;

import java.awt.*;

/**
 * Representa uma objeto que pode ser desenhado em um contexto
 * gr�fico Graphics, e define a cor de seu desenho.
 */
public interface FormaDesenhavel
{
	// M�todos abstratos
	/**
	 * Especifica o procedimento de desenho da forma.
	 * 
	 * @param desenho	Contexto gr�fico relacionado.
	 */
	public void desenhar(Graphics desenho, Color cor);
}
