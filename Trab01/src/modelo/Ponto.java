package modelo;

public class Ponto implements Comparable
{
	// Coordenadas x e y do ponto.
	private int x, y;

	// Construtor.
	public Ponto(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	// Método getX: retorna x.
	public int getX()
	{
		return x;
	}

	// Método getY: retorna y.
	public int getY()
	{
		return y;
	}

	// Método toString: imprime o ponto.
   public String toString()
   {
	   return "(" + String.format("%3d", x) + ", " + String.format("%3d", y) + ")";
   }

   // Método equals: compara dois objetos.
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
	
	// Método compareTo: compara dois objetos.
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
	
}
