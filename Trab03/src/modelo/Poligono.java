package modelo;

import java.awt.*;
import java.util.*;

public class Poligono implements FormaDesenhavel
{
	/**
	 *  Conjunto de segmentos.
	 */
	private Set<Segmento> segmentos;
	
	// Construtor
	public Poligono()
   {
		segmentos = new HashSet<Segmento>();		
   }
	
	// Métodos
	
	/**
	 *  Adiciona um novo segmento ao conjunto.
	 */
	public boolean add(Segmento segmento)
	{
		return segmentos.add(segmento);
	}
	
   /**
    * Compara dois objetos.  
    */
	public boolean equals(Object poligonoObj)
   {
		boolean retorno = false;
		
		if (poligonoObj instanceof Poligono)
		{
			//	Verifica se os objetos possuem os mesmos atributos.
			Poligono poligono = (Poligono) poligonoObj;
			
			if (poligono.segmentos.containsAll(this.segmentos)
				&& this.segmentos.containsAll(poligono.segmentos))
				retorno = true;
		}
		
	   return retorno;
   }

	/**
	 * @return	O conjunto de segmentos.
	 */
	public Set<Segmento> getSegmentos()
   {
   	return segmentos;
   }	

	public void desenhar(Graphics desenho, Color cor)
	{
		for (Segmento segmento: segmentos)
			segmento.desenhar(desenho, cor);
	}
	
}
