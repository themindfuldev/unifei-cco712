package pacote_11877_12643.controle;

/**
 * Mediator para o dialogo de translacao. 
 */
public class MediatorTranslacao
{
	// Atributos
	/**
	 * Coordenada X.
	 */
	private int x;
	/**
	 * Coordenada Y.
	 */
	private int y;

	// Metodos
	public int getX()
   {
   	return x;
   }

	public int getY()
   {
   	return y;
   }

	public void setPonto(int x, int y)
   {
   	this.x = x;
   	this.y = y;
   }
}
