package modelo;

import java.awt.*;

public class Ponto implements FormaDesenhavel, Comparable
{
	/**
	 * Coordenadas x e y do ponto.
	 */
	private int x, y;

	// Construtores
	public Ponto(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	// Métodos
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	/**
	 * Imprime o segmento.
	 */
	public String toString()
	{
		return "(" + String.format("%3d", x) + ", " + String.format("%3d", y)
				+ ")";
	}

	/**
	 * Compara dois objetos.
	 */
	public boolean equals(Object pontoObj)
	{
		boolean retorno = false;

		if (pontoObj instanceof Ponto)
		{
			// Verifica se os objetos possuem os mesmos atributos.
			Ponto ponto = (Ponto) pontoObj;
			if ((this.x == ponto.x) && (this.y == ponto.y))
				retorno = true;
		}

		return retorno;
	}

	/**
	 * Compara dois objetos mensuráveis.
	 */
	public int compareTo(Object pontoObj)
	{
		int retorno = 0;

		if (pontoObj instanceof Ponto)
		{
			// Compara os objetos quanto aos atributos.
			Ponto ponto = (Ponto) pontoObj;

			if (this.x > ponto.x)
				retorno = 1;
			else if (this.x < ponto.x)
				retorno = -1;
			else if (this.y > ponto.y)
				retorno = 1;
			else if (this.y < ponto.y)
				retorno = -1;
			else
				retorno = 0;
		}
		return retorno;
	}

	public void desenhar(Graphics desenho, Color cor)
	{
		desenho.setColor(cor);
		desenho.drawLine(x, y, x, y);
	}
}
