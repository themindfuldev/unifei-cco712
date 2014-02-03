package modelo.algoritmos;

import java.util.LinkedList;
import java.util.List;

import modelo.Ponto;

public class AlgoritmoIncremental implements AlgoritmoCircunferencia
{

	public List<Ponto> preencheLista(Ponto centro, int raio)
	{
		int xCentro, yCentro, varX, varY;		
		double varSen, varCos, dX, dY, incremento, dXtemp, dYtemp;
		Ponto p;
		List<Ponto> lista = new LinkedList<Ponto>();
		
		incremento = (double) 1/raio;
		varCos = Math.cos(incremento);
		varSen = Math.sin(incremento);
		
		xCentro = centro.getX();
		yCentro = centro.getY();
		
		dX = varX = 0;
		dY = varY = raio;
		
		do
		{			
			p = new Ponto(xCentro+varX, yCentro+varY);
			lista.add(p);
			p = new Ponto(xCentro+varY, yCentro+varX);
			lista.add(p);			
			
			p = new Ponto(xCentro+varX, yCentro-varY);
			lista.add(p);
			p = new Ponto(xCentro-varY, yCentro+varX);
			lista.add(p);			
			
			p = new Ponto(xCentro-varX, yCentro+varY);
			lista.add(p);
			p = new Ponto(xCentro+varY, yCentro-varX);
			lista.add(p);			
			
			p = new Ponto(xCentro-varX, yCentro-varY);
			lista.add(p);
			p = new Ponto(xCentro-varY, yCentro-varX);
			lista.add(p);
			
			dXtemp = dX*varCos - dY*varSen;
			dYtemp = dY*varCos + dX*varSen;
			
			dX = dXtemp;
			dY = dYtemp;
			
			varX = (int) dX;
			varY = (int) dY;			
		} while (varX != varY);

		return lista;
	}

}
