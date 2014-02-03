package util;

import java.io.*;
import modelo.*;

public class SegmentoInputStream extends BufferedReader
{
	// Construtor.
	public SegmentoInputStream(InputStream is)
   {
	   super(new InputStreamReader(is));
   }
	
	// Método readSegmento: lê um segmento.
	public Segmento readSegmento() throws IOException, EOFException
	{
		// Variáveis internas.
		int x1, y1, x2, y2;
		Ponto p1, p2;
		
		// Lê uma linha;
		String linha[] = readLine().split(" ");
		
		// Interpreta quatro inteiros lidos. 
		x1 = Integer.parseInt(linha[0]);
		y1 = Integer.parseInt(linha[1]);
		x2 = Integer.parseInt(linha[2]);
		y2 = Integer.parseInt(linha[3]);
		
		// Criação de pontos a partir dos valores lidos.
		p1 = new Ponto(x1, y1);
		p2 = new Ponto(x2, y2);		
		
		// Retorna um novo segmento.
		return new Segmento(p1, p2);
	}

}
