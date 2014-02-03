package pacote_11877_12643.modelo;

import java.util.*;

public class Viewport
{
	private Ponto pEsqInf, pDirSup;

	public Viewport(Ponto esqInf, Ponto dirSup)
	{
		super();
		pEsqInf = esqInf;
		pDirSup = dirSup;
	}

	public Ponto getPDirSup()
	{
		return pDirSup;
	}

	public Ponto getPEsqInf()
	{
		return pEsqInf;
	}

	public Ponto intersecaoBordaEsquerda(Segmento s, Ponto p)
	{
		int x, y;

		x = pEsqInf.getX();
		y = (int) Math.round(s.getM() * (x - p.getX()) + p.getY());
		return new Ponto(x, y);
	}

	public Ponto intersecaoBordaDireita(Segmento s, Ponto p)
	{
		int x, y;

		x = pDirSup.getX();
		y = (int) Math.round(s.getM() * (x - p.getX()) + p.getY());
		return new Ponto(x, y);
	}

	public Ponto intersecaoBordaInferior(Segmento s, Ponto p)
	{
		int x, y;

		y = pEsqInf.getY();
		x = (int) Math.round(s.getMInv() * (y - p.getY()) + p.getX());
		return new Ponto(x, y);
	}

	public Ponto intersecaoBordaSuperior(Segmento s, Ponto p)
	{
		int x, y;

		y = pDirSup.getY();
		x = (int) Math.round(s.getMInv() * (y - p.getY()) + p.getX());
		return new Ponto(x, y);
	}

	public void preencheDirecoes(Ponto p, Set<Direcoes> direcoes)
	{
		direcoes.clear();

		if (p.getX() < pEsqInf.getX())
			direcoes.add(Direcoes.ESQUERDA);
		else if (p.getX() > pDirSup.getX())
			direcoes.add(Direcoes.DIREITA);

		if (p.getY() < pEsqInf.getY())
			direcoes.add(Direcoes.INFERIOR);
		else if (p.getY() > pDirSup.getY())
			direcoes.add(Direcoes.SUPERIOR);
	}

	public boolean dentroJanela(Ponto ponto, Direcoes direcoes)
	{
		boolean dentro = false;

		switch (direcoes)
		{
		case SUPERIOR:
			if (ponto.getY() <= pDirSup.getY())
				dentro = true;
			break;

		case INFERIOR:
			if (ponto.getY() >= pEsqInf.getY())
				dentro = true;
			break;

		case ESQUERDA:
			if (ponto.getX() >= pEsqInf.getX())
				dentro = true;
			break;

		case DIREITA:
			if (ponto.getX() <= pDirSup.getX())
				dentro = true;
		}

		return dentro;
	}

	public List<Ponto> cortePoligonos(List<Ponto> listaEntrada, Direcoes direcao)
	{
		Ponto p1, p2;
		List<Ponto> listaSaida = new ArrayList<Ponto>();

		p1 = listaEntrada.remove(0);
		listaSaida.add(p1);
		while (!listaEntrada.isEmpty())
		{
			p2 = listaEntrada.remove(0);
			// Caso 1 ou 4
			if (dentroJanela(p2, direcao))
			{
				// caso 1
				if (dentroJanela(p1, direcao))
				{
					listaSaida.add(p2);
				}
				// caso 4
				else
				{
					listaSaida.add(intercepta(p1, p2, direcao));
					listaSaida.add(p2);
				}
			}
			// Segundo ponto fora: caso 2 ou 3
			else
			{
				if (dentroJanela(p1, direcao))
					listaSaida.add(intercepta(p1, p2, direcao));
			}
			p1 = p2;
		}
		return listaSaida;
	}

	private Ponto intercepta(Ponto p1, Ponto p2, Direcoes direcao)
	{
		Ponto retorno = null;
		
		switch (direcao)
		{
			case SUPERIOR:
				retorno = intersecaoBordaSuperior(new Segmento(p1, p2), p2); 
				break;
	
			case INFERIOR:
				retorno = intersecaoBordaInferior(new Segmento(p1, p2), p2);
				break;
	
			case ESQUERDA:
				retorno = intersecaoBordaEsquerda(new Segmento(p1, p2), p2);
				break;
	
			case DIREITA:
				retorno = intersecaoBordaDireita(new Segmento(p1, p2), p2);
		}
		return retorno;
	}

}
