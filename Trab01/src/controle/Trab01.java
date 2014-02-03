package controle;

import java.io.*;
import java.util.*;
import modelo.*;
import util.*;

public class Trab01
{
	// Stream de entrada.
	private static BufferedReader entrada = new BufferedReader(
	      new InputStreamReader(System.in));

	// Stream de sa�da.
	private static PrintStream saida = System.out;

	// M�todo main: entrada do programa.
	// Argumentos:
	// <Arquivo> - nome do arquivo a ser aberto.

	@SuppressWarnings("unchecked")
   public static void main(String[] args)
	{
		try
		{
			// Verifica os argumentos.
			if (args.length != 1) throw new IllegalArgumentException(
			      "ERRO: Uso incorreto.\nUso correto: Trab01 <Arquivo>");

			//-------------------------------------------------------------------//
			// Etapa 1 - Leitura

			// Inst�ncia de um objeto que representa o arquivo a ser aberto.
			File arquivo = new File(args[0]);

			// Verifica��o do arquivo.
			if (arquivo.canRead() == false) throw new IllegalStateException(
			      "ERRO: N�o foi poss�vel realizar a leitura do arquivo.");

			// Inst�ncia de um fluxo de leitura especializado, a partir de arquivo.
			SegmentoInputStream stream = new SegmentoInputStream(
			      new FileInputStream(arquivo));

			// Lista ligada simples dos segmentos lidos.
			LinkedList<Segmento> listaSegmentos = new LinkedList<Segmento>();

			// Flag de fim de arquivo.
			Segmento segmentoFlag = new Segmento(new Ponto(0, 0), new Ponto(0, 0));
			
			// Leitura do primeiro segmento.
			Segmento segmentoLido = stream.readSegmento();

			// Leitura do resto do arquivo.
			while (segmentoLido.equals(segmentoFlag) == false)
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

			//-------------------------------------------------------------------//
			// Etapa 2 - Menu
			
			// Lista de resposta dos m�todos.
			LinkedList<Segmento> listaResposta;
			
			// Tabela de resposta do m�todo retasInterceptadas().
			Map<Segmento, LinkedList<Segmento>> tabelaResposta;
			
			// Conjunto de resposta do m�todo retasPoligono().
			Set<Poligono> listaPoligonos;

			// Op��o do menu.
			int opcao;

			// Repeti��o do menu, at� sa�da (op��o 0).
			do
			{
				// Leitura do menu.
				opcao = menu();

				// Sele��o da op��o.
				switch (opcao)
				{
					// Op��o 1 - Mostrar todas as retas.
					case 1:
						mostrar(listaSegmentos);
						break;

					// Op��o 2 - Mostrar as retas horizontais.
					case 2:
						listaResposta = retasHorizontais(listaSegmentos);
						mostrar(listaResposta);
						break;

					// Op��o 3 - Mostrar as retas verticais.
					case 3:
						listaResposta = retasVerticais(listaSegmentos);
						mostrar(listaResposta);
						break;

					// Op��o 4 - Mostrar as retas n�o horizontais nem verticais.
					case 4:
						listaResposta = retasNaoHorizontaisNemVerticais(listaSegmentos);
						mostrar(listaResposta);
						break;

					// Op��o 5 - Mostrar as retas que se interceptam.
					case 5:
						tabelaResposta = retasInterceptadas(listaSegmentos);
						mostrarInterceptadas(tabelaResposta);
						break;

					// Op��o 6 - Mostrar as retas que formam um pol�gono fechado.
					case 6:
						listaPoligonos = retasPoligono(listaSegmentos);
						mostrarPoligonos(listaPoligonos);
						break;

					// Op��o 0 - Fim.
					case 0:
						saida.println("Finalizando...");
						listaSegmentos.clear();
						break;

					// Caso de erro.
					default:
						saida.println("ERRO: Op��o Inv�lida!");
						break;
				}
			} while (opcao != 0);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERRO: Arquivo n�o encontrado.");
		}
		catch (EOFException e)
		{
			System.out.println("ERRO: Arquivo incompleto.");
		}
		catch (IOException e)
		{
			System.out.println("ERRO: Erro de leitura.");
		}
		catch (NumberFormatException e)
		{
			System.out.println("ERRO: Erro de entrada.");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	// M�todo menu: Imprime o menu e permite a escolha de uma op��o.
	private static int menu() throws IOException
	{
		// Op��o do menu.
		int opcao;

		// Impress�o do menu.
		saida.println("\n\nMenu de op��es");
		saida.println("--------------\n");
		saida.println("1 - Mostrar todas as retas");
		saida.println("2 - Mostrar as retas horizontais");
		saida.println("3 - Mostrar as retas verticais");
		saida.println("4 - Mostrar as retas n�o horizontais nem verticais");
		saida.println("5 - Mostrar as retas que se interceptam");
		saida.println("6 - Mostrar as retas que formam um pol�gono fechado");
		saida.println("0 - Fim\n");
		saida.print("Digite a op��o desejada: ");

		// Leitura e retorno.
		opcao = Integer.parseInt(entrada.readLine());

		saida.println();
		return opcao;
	}

	// M�todo mostrar: Exibe o conte�do de uma lista de segmentos.
	private static void mostrar(LinkedList<Segmento> listaSegmentos)
	{
		// Obten��o do primeiro elemento da lista.
		int contador = 1;

		// Varredura e impress�o da lista.
		for (Segmento segmento: listaSegmentos)
		{
			saida.printf("Segmento %2d: %s\n", contador++, segmento);
		}
	}

	// M�todo retasHorizontais: Obt�m uma lista apenas com retas horizontais.
	private static LinkedList<Segmento> retasHorizontais(
	      LinkedList<Segmento> listaSegmentos)
	{
		// Lista particular desta tarefa.
		LinkedList<Segmento> listaResposta = new LinkedList<Segmento>();

		// Varredura da lista e teste de cada segmento.
		for (Segmento segmento: listaSegmentos)
		{
			if (segmento.getM() == 0) 
				listaResposta.add(segmento);			
		}

		return listaResposta;
	}

	// M�todo retasVerticais: Obt�m uma lista apenas com retas verticais.
	private static LinkedList<Segmento> retasVerticais(
	      LinkedList<Segmento> listaSegmentos)
	{
		// Lista particular desta tarefa.
		LinkedList<Segmento> listaResposta = new LinkedList<Segmento>();

		// Varredura da lista e teste de cada segmento.
		for (Segmento segmento: listaSegmentos)
		{
			if (Float.isInfinite(segmento.getM()) == true) 
				listaResposta.add(segmento);				
		}

		return listaResposta;
	}

	// M�todo retasNaoHorizontaisNemVerticais: Obt�m uma lista apenas com 
	// retas n�o horizontais nem verticais.
	private static LinkedList<Segmento> retasNaoHorizontaisNemVerticais(
	      LinkedList<Segmento> listaSegmentos)
	{
		// Lista particular desta tarefa.
		LinkedList<Segmento> listaResposta = new LinkedList<Segmento>();

		// Varredura da lista e teste de cada segmento.
		for (Segmento segmento: listaSegmentos)
		{
			if ((segmento.getM() != 0) && 
				 (Float.isInfinite(segmento.getM())) == false) 
				listaResposta.add(segmento);				
		}

		return listaResposta;
	}

	// M�todo retasInterceptadas: Obt�m uma tabela, onde cada entrada possui
	// um segmento base, e uma lista ligada simples contendo todos os segmentos
	// que se interceptam com este segmento base.
	private static Map<Segmento, LinkedList<Segmento>> retasInterceptadas(
	      LinkedList<Segmento> listaSegmentos)
	{
		// Tabela particular desta tarefa.
		Map<Segmento, LinkedList<Segmento>> tabelaResposta = 
			new TreeMap<Segmento, LinkedList<Segmento>>();

		// Varredura da lista.
		for (Segmento segmento1: listaSegmentos)
		{
			// Lista de segmentos que se interceptam com o segmento1.
			LinkedList<Segmento> listaRespostaSegmento = 
				new LinkedList<Segmento>();
			
			// Insere uma entrada na tabela.
			tabelaResposta.put(segmento1, listaRespostaSegmento);

			// Varredura da lista e teste de cada segmento.
			for (Segmento segmento2: listaSegmentos)
			{
				// Se for o mesmo segmento, pula.
				if (segmento1 == segmento2) continue;

				// Se houver inserse��o, adiciona � lista.
				if (Segmento.haIntersecao(segmento1, segmento2) == true) 
					listaRespostaSegmento.add(segmento2);
			}
		}

		return tabelaResposta;
	}

	// M�todo mostrarInterceptadas: Exibe o conte�do de uma tabela.
	private static void mostrarInterceptadas(
	      Map<Segmento, LinkedList<Segmento>> tabelaSegmentos)
	{
		// Obten��o do primeiro elemento da lista.
		int contador = 1;

		// Varredura da lista.
		for (Map.Entry<Segmento, LinkedList<Segmento>> entrada: 
			tabelaSegmentos.entrySet())
		{
			// S� imprime esta entrada se houver interse��es.
			if (entrada.getValue().size() > 0)
			{
				// Imprime o segmento base. 
				Segmento primeiroSegmento = entrada.getKey();
				saida.printf("\nSegmento %2d: %s\n", contador, primeiroSegmento);

				// Imprime os segmentos com interse��o.
				for (Segmento segmento : entrada.getValue())
					saida.printf("  -> %s\n", segmento);
			}
			
			// Soma o contador.
			contador++;
		}
	}

	// M�todo retasPoligono: Obt�m um conjunto com pol�gonos.
	private static Set<Poligono> retasPoligono(
			LinkedList<Segmento> listaSegmentos)
	{
		// Lista particular desta tarefa.
		Set<Poligono> conjuntoResposta = new HashSet<Poligono>();
		
		// Tabela de segmentos e suas interse��es.
		Map<Segmento, LinkedList<Segmento>> tabelaInterceptadas = 
			retasInterceptadas(listaSegmentos);
		
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
			for (Segmento segmento: entrada.getValue())
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

	// M�todo buscaPoligono: busca recursivamente pol�gonos, percorrendo
	// segmentos com interse��o. 
	private static Set<Poligono> buscaPoligono(Set<Poligono> conjuntoResposta,
	      Map<Segmento, LinkedList<Segmento>> tabelaInterceptadas,
	      Stack<Segmento> pilhaPercurso, Segmento segmentoBase, Segmento segmento)
	{
		// Lista de interse��es a este segmento.
		LinkedList<Segmento> listaIntersecoes = tabelaInterceptadas.get(segmento);

		// Varre a lista de interse��es.
		for (Segmento segmentoBuscado: listaIntersecoes)
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
			if (naoEstaEmpilhado == false) continue;

			// Verifica se este segmento fecha o pol�gono empilhado.
			if (Segmento.haIntersecao(segmentoBuscado, segmentoBase) == true)
			{
				// Novo pol�gono.
				Poligono poligono = new Poligono();
				
				// Adiciona todos os elementos da pilha � lista. 
				for (Segmento segmentoEmpilhado: pilhaPercurso)
					poligono.add(segmentoEmpilhado);
				
				// Adiciona este segmento.
				poligono.add(segmentoBuscado);

				// Verifica se j� foi detectado este pol�gono. 
				boolean unico = true;
				for (Poligono poli: conjuntoResposta)
				{
					if (poligono.equals(poli) == true)
					{
						unico = false;
						break;
					}
				}
				if (unico == true) conjuntoResposta.add(poligono);
			}
			// Sen�o, continua buscando.
			else
			{
				// Empilha este segmento.
				pilhaPercurso.push(segmentoBuscado);
				
				// Busca pol�gonos que este segmento participe.
				buscaPoligono(conjuntoResposta, tabelaInterceptadas, pilhaPercurso,
				      segmentoBase, segmentoBuscado);
				
				//	Desempilha este segmento.
				pilhaPercurso.pop();
			}
		}

		return conjuntoResposta;
	}

	// M�todo mostrarPoligonos: Exibe o conte�do de um conjunto de pol�gonos.
	private static void mostrarPoligonos(Set<Poligono> listaPoligonos)
	{
		// Obten��o do primeiro elemento da lista.
		int contador = 1;

		// Varredura da lista e impress�o dos pol�gonos.
		for (Poligono poligono : listaPoligonos)
		{
			saida.printf("\nPol�gono %2d:\n", contador++);

			for (Segmento segmento : poligono.getSegmentos())
				saida.printf("  -> %s\n", segmento);
		}
	}

}
