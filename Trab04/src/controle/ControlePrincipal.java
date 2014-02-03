package controle;

import java.util.*;
import modelo.*;
import modelo.algoritmos.*;

/**
 * Controle principal do programa.
 * 
 * Abstrai a camada de controle do programa (MVC).
 */
public class ControlePrincipal
{
	// Relacionamentos
	/**
	 * Lista ligada simples dos segmentos lidos.
	 */ 
	private LinkedList<Figura> listaFiguras;
	
	// Construtor
	public ControlePrincipal()
	{
		listaFiguras = new LinkedList<Figura>();		
		
		constroiFiguras();
	}

	private void constroiFiguras()
	{
		AlgoritmoCircunferencia algoritmo;
		
		Figura figura;
		Circunferencia circ;
		int xCentro, yCentro;

		algoritmo = new AlgoritmoDireto();		
		
		xCentro = 300;
		yCentro = 400;
		figura = new Figura();
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 4, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 30, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 60, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 90, algoritmo);
		figura.add(circ);		
		listaFiguras.add(figura);
		
		xCentro = 100;
		yCentro = 100;
		figura = new Figura();
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 70, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+41, yCentro), 29, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro-41, yCentro), 29, algoritmo);
		figura.add(circ);	
		circ = new Circunferencia(new Ponto(xCentro, yCentro+41), 29, algoritmo);
		figura.add(circ);	
		circ = new Circunferencia(new Ponto(xCentro, yCentro-41), 29, algoritmo);
		figura.add(circ);		
		listaFiguras.add(figura);
		
		algoritmo = new AlgoritmoParametrizado();
		
		xCentro = 500;
		yCentro = 400;
		figura = new Figura();
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 70, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+41, yCentro), 29, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro-41, yCentro), 29, algoritmo);
		figura.add(circ);	
		circ = new Circunferencia(new Ponto(xCentro, yCentro+41), 29, algoritmo);
		figura.add(circ);	
		circ = new Circunferencia(new Ponto(xCentro, yCentro-41), 29, algoritmo);
		figura.add(circ);		
		listaFiguras.add(figura);
		
		xCentro = 450;
		yCentro = 150;
		figura = new Figura();
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 120, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+40, yCentro), 80, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+80, yCentro), 40, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+95, yCentro), 15, algoritmo);
		figura.add(circ);
		listaFiguras.add(figura);
		
		algoritmo = new AlgoritmoIncremental();

		xCentro = 250;
		yCentro = 200;		
		figura = new Figura();
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 30, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro-45, yCentro), 15, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro-45, yCentro), 2, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+45, yCentro), 15, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+45, yCentro), 2, algoritmo);
		figura.add(circ);	
		circ = new Circunferencia(new Ponto(xCentro, yCentro-45), 15, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro, yCentro-45), 2, algoritmo);
		figura.add(circ);		
		circ = new Circunferencia(new Ponto(xCentro, yCentro+45), 15, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro, yCentro+45), 2, algoritmo);
		figura.add(circ);	
		circ = new Circunferencia(new Ponto(xCentro, yCentro+10), 2, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro-10, yCentro-10), 2, algoritmo);
		figura.add(circ);		
		circ = new Circunferencia(new Ponto(xCentro+10, yCentro-10), 2, algoritmo);
		figura.add(circ);		
		listaFiguras.add(figura);

		xCentro = 100;
		yCentro = 400;		
		figura = new Figura();
		circ = new Circunferencia(new Ponto(xCentro, yCentro), 30, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro-45, yCentro), 15, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro-45, yCentro), 2, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+45, yCentro), 15, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro+45, yCentro), 2, algoritmo);
		figura.add(circ);	
		circ = new Circunferencia(new Ponto(xCentro, yCentro-45), 15, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro, yCentro-45), 2, algoritmo);
		figura.add(circ);		
		circ = new Circunferencia(new Ponto(xCentro, yCentro+45), 15, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro, yCentro+45), 2, algoritmo);
		figura.add(circ);	
		circ = new Circunferencia(new Ponto(xCentro, yCentro+10), 2, algoritmo);
		figura.add(circ);
		circ = new Circunferencia(new Ponto(xCentro-10, yCentro-10), 2, algoritmo);
		figura.add(circ);		
		circ = new Circunferencia(new Ponto(xCentro+10, yCentro-10), 2, algoritmo);
		figura.add(circ);		
		listaFiguras.add(figura);
	}

	public LinkedList<Figura> getListaFiguras()
	{
		return listaFiguras;
	}

}
