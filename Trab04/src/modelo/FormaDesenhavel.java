package modelo;

import java.awt.*;

/**
 * Representa uma objeto que pode ser desenhado em um contexto
 * gráfico Graphics, e define a cor de seu desenho.
 */
public interface FormaDesenhavel
{
	// Métodos abstratos
	/**
	 * Especifica o procedimento de desenho da forma.
	 * 
	 * @param desenho	Contexto gráfico relacionado.
	 */
	public void desenhar(Graphics desenho, Color cor);
}
