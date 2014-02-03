package controle;

import java.awt.*;
import java.io.*;
import java.util.*;
import modelo.*;
import util.*;

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
	private LinkedList<Segmento> listaSegmentos;
	
	// Atributos
	/**
	 * Tabela de cores.
	 */
	private Map<String, Color> tabelaCores;
	
	// Construtor
	public ControlePrincipal()
	{
		listaSegmentos = new LinkedList<Segmento>();
		tabelaCores = new TreeMap<String, Color>();
		
		// Constr�i a tabela.
		iniciaTabela();
	}

	/**
	 * Constr�i a tabela de cores.
	 */
	private void iniciaTabela()
   {
		tabelaCores.put("Preto", Color.BLACK);
		tabelaCores.put("Azul", Color.BLUE);
		tabelaCores.put("Ciano", Color.CYAN);
		tabelaCores.put("Cinza escuro", Color.DARK_GRAY);
		tabelaCores.put("Cinza", Color.GRAY);
		tabelaCores.put("Verde", Color.GREEN);
		tabelaCores.put("Verde claro", Color.LIGHT_GRAY);
		tabelaCores.put("Magenta", Color.MAGENTA);
		tabelaCores.put("Laranja", Color.ORANGE);
		tabelaCores.put("Rosa", Color.PINK);
		tabelaCores.put("Vermelho", Color.RED);
		tabelaCores.put("Branco", Color.WHITE);
		tabelaCores.put("Amarelo", Color.YELLOW);	   
   }

	public LinkedList<Segmento> getListaSegmentos()
	{
		return listaSegmentos;
	}

	/**
	 * @return	Uma lista ligada apenas com segmentos horizontais.
	 */ 
	public LinkedList<Segmento> retasHorizontais()
	{
		// Lista particular desta tarefa.
		LinkedList<Segmento> listaResposta = new LinkedList<Segmento>();

		// Varredura da lista e teste de cada segmento.
		for (Segmento segmento : listaSegmentos)
		{
			if (segmento.getPInicial().getY() == segmento.getPFinal().getY())
				listaResposta.add(segmento);
		}

		return listaResposta;
	}

	/**
	 * @return	Uma lista ligada apenas com segmentos verticais.
	 */ 
	public LinkedList<Segmento> retasVerticais()
	{
		// Lista particular desta tarefa.
		LinkedList<Segmento> listaResposta = new LinkedList<Segmento>();

		// Varredura da lista e teste de cada segmento.
		for (Segmento segmento : listaSegmentos)
		{
			if (segmento.getPInicial().getX() == segmento.getPFinal().getX())
				listaResposta.add(segmento);
		}

		return listaResposta;
	}

	/**
	 * @return	Uma lista ligada apenas com segmentos n�o 
	 * horizontais nem verticais.
	 */ 
	public LinkedList<Segmento> retasNaoHorizontaisNemVerticais()
	{
		// Lista particular desta tarefa.
		LinkedList<Segmento> listaResposta = new LinkedList<Segmento>();

		// Varredura da lista e teste de cada segmento.
		for (Segmento segmento : listaSegmentos)
		{
			if (segmento.getPInicial().getY() != segmento.getPFinal().getY() &&
 				 segmento.getPInicial().getX() != segmento.getPFinal().getX())
				listaResposta.add(segmento);
		}

		return listaResposta;
	}

	/**
	 * @return	Uma tabela, onde cada entrada possui um segmento base,
	 * e uma lista ligada simples contendo todos os segmentos que 
	 * se interceptam com este segmento base.
	 */ 
	private Map<Segmento, LinkedList<Segmento>> tabelaInterceptadas()
	{
		// Tabela particular desta tarefa.
		Map<Segmento, LinkedList<Segmento>> tabelaResposta = new TreeMap<Segmento, LinkedList<Segmento>>();

		// Varredura da lista.
		for (Segmento segmento1 : listaSegmentos)
		{
			// Lista de segmentos que se interceptam com o segmento1.
			LinkedList<Segmento> listaRespostaSegmento = new LinkedList<Segmento>();

			// Insere uma entrada na tabela.
			tabelaResposta.put(segmento1, listaRespostaSegmento);

			// Varredura da lista e teste de cada segmento.
			for (Segmento segmento2 : listaSegmentos)
			{
				// Se for o mesmo segmento, pula.
				if (segmento1 == segmento2)
					continue;

				// Se houver inserse��o, adiciona � lista.
				if (Segmento.haIntersecao(segmento1, segmento2) == true)
					listaRespostaSegmento.add(segmento2);
			}
		}

		return tabelaResposta;
	}
	
	/**
	 * @return	Um conjunto apenas com segmentos que se interceptam.
	 */ 
	public Set<Segmento> retasInterceptadas()
	{
		// Lista particular desta tarefa.
		Set<Segmento> conjuntoResposta = new HashSet<Segmento>();
		
		// Tabela de segmentos e suas interse��es.
		Map<Segmento, LinkedList<Segmento>> tabelaInterceptadas = tabelaInterceptadas();

		for (Map.Entry<Segmento, LinkedList<Segmento>> entrada : tabelaInterceptadas.entrySet())
		{
			if (entrada.getValue().isEmpty() == false)
			{
				conjuntoResposta.add(entrada.getKey());
				
				for (Segmento segmento: entrada.getValue())
					conjuntoResposta.add(segmento);
			}			
		}

		return conjuntoResposta;
	}	

	/**
	 * @return	Um conjunto apenas com pol�gonos.
	 */ 
	public Set<Poligono> retasPoligono()
	{
		// Lista particular desta tarefa.
		Set<Poligono> conjuntoResposta = new HashSet<Poligono>();

		// Tabela de segmentos e suas interse��es.
		Map<Segmento, LinkedList<Segmento>> tabelaInterceptadas = tabelaInterceptadas();

		// Pilha de segmentos para tra�ar o caminho percorrido.
		Stack<Segmento> pilhaPercurso = new Stack<Segmento>();

		// Varredura da lista.
		for (Map.Entry<Segmento, LinkedList<Segmento>> entrada: 
			tabelaInterceptadas.entrySet())
		{
			// Obt�m o segmento base e empilha.
			Segmento segmentoBase = entrada.getKey();
			pilhaPercurso.push(segmentoBase);

			// Varredura da lista de segmentos com interse��o a este.
			for (Segmento segmento : entrada.getValue())
			{
				// Empilha este segmento.
				pilhaPercurso.push(segmento);

				// Busca pol�gonos que este segmento participe.
				buscaPoligono(conjuntoResposta, tabelaInterceptadas, pilhaPercurso,
						segmentoBase, segmento);

				// Desempilha este segmento.
				pilhaPercurso.pop();
			}
			// Desempilha o segmento base.
			pilhaPercurso.pop();
		}

		return conjuntoResposta;
	}

	/**
	 * Busca recursivamente pol�gonos, percorrendo segmentos com interse��o.
	 * 
	 * @param conjuntoResposta		Conjunto resposta a ser montado.
	 * @param tabelaInterceptadas	Tabela de segmentos e suas interse��es.
	 * @param pilhaPercurso			Pilha do percurso recursivo, contendo os segmentos visitados.			
	 * @param segmentoBase			Segmento base da recurs�o.
	 * @param segmento				Segmento percorrido no momento.
	 * @return
	 */
	private Set<Poligono> buscaPoligono(Set<Poligono> conjuntoResposta,
			Map<Segmento, LinkedList<Segmento>> tabelaInterceptadas,
			Stack<Segmento> pilhaPercurso, Segmento segmentoBase, Segmento segmento)
	{
		// Lista de interse��es a este segmento.
		LinkedList<Segmento> listaIntersecoes = tabelaInterceptadas.get(segmento);

		// Varre a lista de interse��es.
		for (Segmento segmentoBuscado : listaIntersecoes)
		{
			// Verifica se este segmento j� est� empilhado.
			boolean naoEstaEmpilhado = true;
			for (Segmento segmentoEmpilhado : pilhaPercurso)
			{
				if (segmentoEmpilhado.equals(segmentoBuscado) == true)
				{
					naoEstaEmpilhado = false;
					break;
				}
			}
			if (naoEstaEmpilhado == false)
				continue;

			// Verifica se este segmento fecha o pol�gono empilhado.
			if (Segmento.haIntersecao(segmentoBuscado, segmentoBase) == true)
			{
				// Novo pol�gono.
				Poligono poligono = new Poligono();

				// Adiciona todos os elementos da pilha � lista.
				for (Segmento segmentoEmpilhado : pilhaPercurso)
					poligono.add(segmentoEmpilhado);

				// Adiciona este segmento.
				poligono.add(segmentoBuscado);

				// Verifica se j� foi detectado este pol�gono.
				boolean unico = true;
				for (Poligono poli : conjuntoResposta)
				{
					if (poligono.equals(poli) == true)
					{
						unico = false;
						break;
					}
				}
				if (unico == true)
					conjuntoResposta.add(poligono);
			}
			// Sen�o, continua buscando.
			else
			{
				// Empilha este segmento.
				pilhaPercurso.push(segmentoBuscado);

				// Busca pol�gonos que este segmento participe.
				buscaPoligono(conjuntoResposta, tabelaInterceptadas, pilhaPercurso,
						segmentoBase, segmentoBuscado);

				// Desempilha este segmento.
				pilhaPercurso.pop();
			}
		}

		return conjuntoResposta;
	}
	
	/**
	 * L� um arquivo de texto e monta a lista de segmentos lidos.
	 * 
	 * @param arquivo	Arquivo a ser lido.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws EOFException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
   public void lerArquivo(File arquivo) throws IOException, FileNotFoundException, EOFException, Exception
	{
		// Verifica��o do arquivo.
		if (arquivo.canRead() == false)
			throw new Exception(
					"N�o foi poss�vel realizar a leitura do arquivo!");

		// Inst�ncia de um fluxo de leitura especializado, a partir de
		// arquivo.
		SegmentoInputStream stream = new SegmentoInputStream(
				new FileInputStream(arquivo));
		
		// Limpeza da lista.
		listaSegmentos.clear();

		// Flag de fim de arquivo.
		Segmento segmentoFlag = new Segmento(new Ponto(0, 0), new Ponto(0,
				0));

		// Leitura do primeiro segmento.
		Segmento segmentoLido = stream.readSegmento();

		// Leitura do resto do arquivo.
		while (segmentoLido.equals(segmentoFlag) == false && stream.ready())
		{
			// Adiciona o segmento lido � listaSegmentos.
			listaSegmentos.add(segmentoLido);

			// Leitura do pr�ximo segmento.
			segmentoLido = stream.readSegmento();
		}

		// Fechamento do arquivo.
		stream.close();				

		// Ordena��o da lista.
		Collections.sort(listaSegmentos);
	}
	
	/**
	 * @param nomeDaCor	Nome da cor.
	 * @return	Cor relacionada ao nome.
	 */
	public Color getCor(String nomeDaCor)
	{
		return tabelaCores.get(nomeDaCor);
	}
	
	/**
	 * @return O vetor de nomes de cores.
	 */
	public String[] getNomesDasCores()
	{
		return tabelaCores.keySet().toArray(new String[tabelaCores.size()]);
	}

}
