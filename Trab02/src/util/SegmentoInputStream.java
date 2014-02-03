package util;

import java.io.*;
import java.awt.*;

import modelo.*;

/**
 * BufferedReader capacitado de ler o arquivo e retornar Segmentos
 * instanciados. 
 */
public class SegmentoInputStream extends BufferedReader
{
	// Atributos
	/**
	 * Cor a ser passada aos segmentos instanciados.
	 */
	private Color cor;
	
	// Construtor
	public SegmentoInputStream(InputStream is, Color cor)
   {
	   super(new InputStreamReader(is));
	   this.cor = cor;
   }
	
	/*
	 * Lê dados, e retorna um segmento instanciado.
	 */
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
		p1 = new Ponto(cor, x1, y1);
		p2 = new Ponto(cor, x2, y2);		
		
		// Retorna um novo segmento.
		return new Segmento(cor, p1, p2);
	}

}
