package pacote_11877_12643.modelo;

import java.awt.*;

/**
 * Representa uma objeto que pode ser desenhado em um contexto
 * grafico Graphics, e define a cor de seu desenho.
 */
public interface FormaDesenhavel
{
	// Metodos abstratos
	/**
	 * Especifica o procedimento de desenho da forma.
	 * 
	 * @param desenho	Contexto grafico relacionado.
	 * @param cor		Cor desejada.
	 */
	public void desenhar(Graphics desenho, Color cor);
}
