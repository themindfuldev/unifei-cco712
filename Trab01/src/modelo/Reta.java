package modelo;

public class Reta
{
	// Coeficiente angular.
	protected float m;
   // Coeficiente linear.
	protected Integer b;

	// Método getB: retorna b.
	public Integer getB()
   {
   	return b;
   }

	// Método getM: retorna m.
	public float getM()
   {
   	return m;
   }
	
	// Método equals: compara dois objetos.
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
	
	// Método calculaX: calcula o valor X a partir de um dado Y.
   protected Integer calculaX(int y)
	{
   	// Se o m for infinito.
		if (m == Float.POSITIVE_INFINITY) return b;
		
		// Se o m for 0.
		if (m == 0) return null;
		
		// Senão. 
		return (int) ((y-b)/m);
	}
	
	// Método calculaY: calcula o valor Y a partir de um dado X.
	protected Integer calculaY(int x)
	{
		// Se o m for infinito.
		if (m == Float.POSITIVE_INFINITY) return null;
		
		// Senão.
		return (int) (m*x + b);
	}		
}
