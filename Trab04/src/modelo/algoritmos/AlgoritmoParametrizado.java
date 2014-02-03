package modelo.algoritmos;

import java.util.LinkedList;
import java.util.List;

import modelo.Ponto;

public class AlgoritmoParametrizado implements AlgoritmoCircunferencia
{

	public List<Ponto> preencheLista(Ponto centro, int raio)
	{
		int xCentro, yCentro, varSen, varCos;		
		double teta;
		Ponto p;
		List<Ponto> lista = new LinkedList<Ponto>();
		
		xCentro = centro.getX();
		yCentro = centro.getY();
		
		for(teta = 0;teta <= Math.PI/4; teta+=Math.toRadians(1))
		{
			varCos = (int) (raio*Math.cos(teta));
			varSen = (int) (raio*Math.sin(teta));
			
			p = new Ponto(xCentro+varCos, yCentro+varSen);
			lista.add(p);
			p = new Ponto(xCentro+varSen, yCentro+varCos);
			lista.add(p);			
			
			p = new Ponto(xCentro+varCos, yCentro-varSen);
			lista.add(p);
			p = new Ponto(xCentro-varSen, yCentro+varCos);
			lista.add(p);			
			
			p = new Ponto(xCentro-varCos, yCentro+varSen);
			lista.add(p);
			p = new Ponto(xCentro+varSen, yCentro-varCos);
			lista.add(p);			
			
			p = new Ponto(xCentro-varCos, yCentro-varSen);
			lista.add(p);
			p = new Ponto(xCentro-varSen, yCentro-varCos);
			lista.add(p);	
		}

		return lista;
	}

}
