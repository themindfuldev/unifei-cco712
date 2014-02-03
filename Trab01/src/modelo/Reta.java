package modelo;

public class Reta
{
	// Coeficiente angular.
	protected float m;
   // Coeficiente linear.
	protected Integer b;

	// M�todo getB: retorna b.
	public Integer getB()
   {
   	return b;
   }

	// M�todo getM: retorna m.
	public float getM()
   {
   	return m;
   }
	
	// M�todo equals: compara dois objetos.
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
	
	// M�todo calculaX: calcula o valor X a partir de um dado Y.
   protected Integer calculaX(int y)
	{
   	// Se o m for infinito.
		if (m == Float.POSITIVE_INFINITY) return b;
		
		// Se o m for 0.
		if (m == 0) return null;
		
		// Sen�o. 
		return (int) ((y-b)/m);
	}
	
	// M�todo calculaY: calcula o valor Y a partir de um dado X.
	protected Integer calculaY(int x)
	{
		// Se o m for infinito.
		if (m == Float.POSITIVE_INFINITY) return null;
		
		// Sen�o.
		return (int) (m*x + b);
	}		
}
