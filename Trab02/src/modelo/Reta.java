package modelo;

import java.awt.*;

/**
 * Modelo matemático de uma reta.
 */
public abstract class Reta extends FormaDesenhavel
{
	// Atributos
	/**
	 *  Coeficiente angular.
	 */
	protected float m;
   /**
    * Coeficiente linear.
    */ 
	protected Integer b;
	
	// Construtor	
	protected Reta(Color cor)
	{
		super(cor);
   }

	// Métodos
	public Integer getB()
   {
   	return b;
   }

	public float getM()
   {
   	return m;
   }
	
   /**
    * Compara dois objetos. 
    */
	public boolean equals(Object retaObj)
   {
		boolean retorno = false;
		
		if (retaObj instanceof Reta)
		{
			// Verifica se os objetos possuem os mesmos atributos.
			Reta reta = (Reta) retaObj;
			if ((this.m == reta.m) && (this.b == reta.b))
				retorno = true;
		}
		
	   return retorno;
   }
	
	/**
	 * Calcula o valor X a partir de um dado Y.
	 * 
	 * @param y	Valor de Y
	 * @return O valor de X
	 */
	protected Integer calculaX(int y)
	{
   	// Se o m for infinito.
		if (m == Float.POSITIVE_INFINITY) return b;
		
		// Se o m for 0.
		if (m == 0) return null;
		
		// Senão. 
		return (int) ((y-b)/m);
	}
	
	/**
	 * Calcula o valor Y a partir de um dado X.
	 * 
	 * @param X	Valor de X
	 * @return O valor de Y
	 */
	protected Integer calculaY(int x)
	{
		// Se o m for infinito.
		if (m == Float.POSITIVE_INFINITY) return null;
		
		// Senão.
		return (int) (m*x + b);
	}		
}
