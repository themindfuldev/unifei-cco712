package pacote_11877_12643.modelo;

/**
 * Classe que abstrai as operacoes de transformacoes utilizando matriz 3x3. 
 */
public class Transformacao
{
	/**
	 * Matriz de transformacoes
	 */
	private double[][] matriz;
	
	/**
	 * Cria matriz zerada.
	 * @return nova matriz.
	 */
	private double[][] novaMatriz()
   {
		//	Declaracao de variaveis.
		double[][] novaMatriz;
		
		// Operacao.
		novaMatriz = new double[3][3];	
	   for (int i=0; i<2; i++)
			for (int j=0; j<3; j++)
				novaMatriz[i][j] = 0;
		for (int j=0; j<3; j++)
			novaMatriz[2][j] = 1;
	   
	   return novaMatriz;
   }
	
	
	/**
	 * Realiza translacao.
	 * @param x
	 * @param y
	 */
	public void fazTranslacao(int x, int y)
	{
		//	Declaracao de variaveis.
		double[][] novaMatriz;
		
		// Operacao.
		novaMatriz = novaMatriz();
		novaMatriz[0][0] = 1;
		novaMatriz[1][1] = 1;
		novaMatriz[0][2] += x;
		novaMatriz[1][2] += y;
		
		// Atualizacao da matriz
		atualizarMatriz(novaMatriz);
	}

	/**
	 * Realiza a multiplicacao de matrizes. 
	 * @param novaMatriz matriz a se multiplicar com a atual
	 */
	private void atualizarMatriz(double[][] novaMatriz)
   {
		// Declaracao de variaveis.
		double[][] resultanteMatriz;
		
	   if (matriz == null)
	   	matriz = novaMatriz;
		else
		{
			resultanteMatriz = new double[3][3];
			
			// Multiplicacao de matriz 
			for (int i=0; i<3; i++)
				for (int j=0; j<3; j++)
					for (int k=0; k<3; k++)
						resultanteMatriz[i][j] += matriz[i][k]*novaMatriz[k][j];
			
			matriz = resultanteMatriz;
		}
   }	
	
	/**
	 * Realiza escala.
	 * @param sX
	 * @param sY
	 */
	public void fazEscala(double sX, double sY)
	{
		//	Declaracao de variaveis.
		double[][] novaMatriz;
		
		// Operacao.
		novaMatriz = novaMatriz();
		novaMatriz[0][0] = sX;
		novaMatriz[1][1] = sY;
		
		// Atualizacao da matriz
		atualizarMatriz(novaMatriz);
	}
	
	/**
	 * Realiza escala.
	 * @param s
	 */
	public void fazEscala(double s)
	{
		fazEscala(s, s);
	}		
	
	/**
	 * Realiza rotacao.
	 * @param angulo em radianos
	 */
	public void fazRotacao(double angulo)
	{
		//	Declaracao de variaveis.
		double[][] novaMatriz;
		
		// Operacao.
		novaMatriz = novaMatriz();
		novaMatriz[0][0] = Math.cos(angulo);
		novaMatriz[0][1] = -Math.sin(angulo);
		novaMatriz[1][0] = Math.sin(angulo);
		novaMatriz[1][1] = Math.cos(angulo);
		
		// Atualizacao da matriz
		atualizarMatriz(novaMatriz);		
	}
	
	/**
	 * Realiza rotacao.
	 * @param angulo em graus 
	 */
	public void fazRotacao(int angulo)
	{		
		fazRotacao(Math.toRadians(angulo));
	}
	
	/**
	 * Aplica a transformacao a um ponto.
	 * @param x
	 * @param y
	 * @return Ponto transformado
	 */
	public Ponto calcula(int x, int y)
	{
		// Declaracao de variaveis.
		int xDes, yDes;
		
		// Calculo do ponto
		xDes = (int) Math.round(matriz[0][0]*x + matriz[0][1]*y + matriz[0][2]);
		yDes = (int) Math.round(matriz[1][0]*x + matriz[1][1]*y + matriz[1][2]);
		Ponto p = new Ponto(xDes, yDes);
		
		return p;
	}
	
}
