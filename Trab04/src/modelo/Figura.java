package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class Figura implements FormaDesenhavel
{
	/**
	 *  Conjunto de segmentos.
	 */
	private Set<FormaDesenhavel> formas;
	
	// Construtor
	public Figura()
   {
		formas = new HashSet<FormaDesenhavel>();		
   }
	
	// Métodos
	
	/**
	 *  Adiciona um novo segmento ao conjunto.
	 */
	public boolean add(FormaDesenhavel forma)
	{
		return formas.add(forma);
	}
	
   /**
    * Compara dois objetos.  
    */
	public boolean equals(Object figuraObj)
   {
		boolean retorno = false;
		
		if (figuraObj instanceof Figura)
		{
			//	Verifica se os objetos possuem os mesmos atributos.
			Figura figura = (Figura) figuraObj;
			
			if (figura.formas.containsAll(this.formas)
				&& this.formas.containsAll(figura.formas))
				retorno = true;
		}
		
	   return retorno;
   }

	/**
	 * @return	O conjunto de segmentos.
	 */
	public Set<FormaDesenhavel> getFormas()
   {
   	return formas;
   }	

	public void desenhar(Graphics desenho, Color cor)
	{
		for (FormaDesenhavel segmento: formas)
			segmento.desenhar(desenho, cor);
	}

}
