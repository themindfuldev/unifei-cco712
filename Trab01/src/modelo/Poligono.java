package modelo;

import java.util.*;

public class Poligono
{
	// Conjunto de segmentos.
	private Set<Segmento> segmentos;
	
	// Construtor.
	public Poligono()
   {
		segmentos = new HashSet<Segmento>();		
   }
	
	// M�todo add: adiciona um novo segmento.
	public boolean add(Segmento segmento)
	{
		return segmentos.add(segmento);
	}
	
	// M�todo equals: compara dois objetos.
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

	// M�todo getSegmentos: retorna segmentos.
	public Set<Segmento> getSegmentos()
   {
   	return segmentos;
   }
	
}
