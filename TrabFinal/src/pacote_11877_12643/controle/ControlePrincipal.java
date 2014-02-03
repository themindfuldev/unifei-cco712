package pacote_11877_12643.controle;

import java.awt.Color;
import java.io.*;
import java.util.*;
import pacote_11877_12643.modelo.*;

/**
 * Controle principal do programa. Abstrai a camada de controle do programa (MVC).
 */
public class ControlePrincipal
{
	// Relacionamentos
	/**
	 * Lista das figuras originais.
	 */
	private Figura figuraOriginal;
	/**
	 * Pilha das figuras.
	 */
	private Stack<Stack<Figura>> pilhaPilhasFiguras;
	/**
	 * Lista de virolas genericas carregadas.
	 */
	private List<Virola> listaVirolas;
	/**
	 * Lista de figuras em transformacao composta.
	 */
	private List<Figura> listaFigurasEmTransformacaoComposta;
	/**
	 * Tabela de cores.
	 * @uml.property  name="tabelaCores"
	 * @uml.associationEnd  qualifier="key:java.lang.Object java.awt.Color"
	 */
	private Map<String, Color> tabelaCores;

	// Atributos
	/**
	 * Ponto de referência.
	 */
	private Ponto pontoReferencia;
	/**
	 * Altura do viewport.
	 */
	private int altura;
	/**
	 * Largura do viewport.
	 */
	private int largura;
	/**
	 * Indica se deve-se manter a figura inicial ou nao.
	 */
	private boolean manterInicial;

	// Construtor
	public ControlePrincipal()
	{
		pilhaPilhasFiguras = new Stack<Stack<Figura>>();
		tabelaCores = new TreeMap<String, Color>();
		listaVirolas = new ArrayList<Virola>();
		listaFigurasEmTransformacaoComposta = new ArrayList<Figura>();
		this.pontoReferencia = new Ponto(0, 0);
		manterInicial = true;

		// Constroi a tabela de cores.
		iniciaTabelaDeCores();

		// Le arquivos e carrega virolas.
		carregaVirolas();
	}

	// Metodos
	/**
	 * Carrega as virolas genericas dos arquivos.
	 */
	private void carregaVirolas()
	{
		// Declaracao de variaveis.
		BufferedReader br;
		File arquivo;
		String[] linha;
		int[] arcosSuperior, arcosInferior;
		int numeroArcosSuperior, numeroArcosInferior, largura, altura, pos;

		try
		{
			// Varre os tres arquivos.
			arquivo = null;
			for (int i = 1; i <= 3; i++)
			{
				// Abertura do arquivo.
				arquivo = new File(String.format("figura%d_11877_12643.dat", i));

				// Verificacao do arquivo.
				if (arquivo.canRead() == false)
					throw new Exception(
							"Nao foi possivel realizar a leitura do arquivo "
									+ arquivo.getName() + "!");

				// Instancia de um fluxo de leitura.
				br = new BufferedReader(new FileReader(arquivo));

				// Le arcos superiores
				numeroArcosSuperior = 0;
				linha = br.readLine().split("\\s");
				for (String parte : linha)
				{
					if (parte.equals("") == false)
					{
						numeroArcosSuperior = Integer.parseInt(parte);
						break;
					}
				}
				if (numeroArcosSuperior == 0)
					throw new Exception("Arquivo com entrada incorreta.");

				arcosSuperior = new int[3 * numeroArcosSuperior];
				pos = 0;

				for (int arco = 0; arco < numeroArcosSuperior; arco++)
				{
					linha = br.readLine().split("\\s");
					for (String parte : linha)
						if (parte.equals("") == false)
							arcosSuperior[pos++] = (int) Math.round(Double.parseDouble(parte));
				}

				// Le arcos inferiores
				numeroArcosInferior = 0;
				linha = br.readLine().split("\\s");
				for (String parte : linha)
				{
					if (parte.equals("") == false)
					{
						numeroArcosInferior = Integer.parseInt(parte);
						break;
					}
				}
				if (numeroArcosInferior == 0)
					throw new Exception("Arquivo com entrada incorreta.");

				arcosInferior = new int[3 * numeroArcosInferior];
				pos = 0;

				for (int arco = 0; arco < numeroArcosInferior; arco++)
				{
					linha = br.readLine().split("\\s");
					for (String parte : linha)
						if (parte.equals("") == false)
							arcosInferior[pos++] = (int) Math.round(Double.parseDouble(parte));
				}

				// Largura e altura
				largura = 0;
				linha = br.readLine().split("\\s");
				for (String parte : linha)
				{
					if (parte.equals("") == false)
					{
						largura = (int) Math.round(Double.parseDouble(parte));
						break;
					}
				}
				if (largura == 0)
					throw new Exception("Arquivo com entrada incorreta.");

				altura = 0;
				linha = br.readLine().split("\\s");
				for (String parte : linha)
				{
					if (parte.equals("") == false)
					{
						altura = (int) Math.round(Double.parseDouble(parte));
						break;
					}
				}
				if (altura == 0)
					throw new Exception("Arquivo com entrada incorreta.");

				// Instanciacao da virola generica
				listaVirolas.add(new Virola(new Ponto(0, 0), numeroArcosSuperior,
						arcosSuperior, numeroArcosInferior, arcosInferior, largura,
						altura));
			}
		} catch (Exception e)
		{
			System.out.println("ERRO: " + e.getMessage());
			System.exit(-1);
		}

	}

	/**
	 * Constroi a tabela de cores.
	 */
	private void iniciaTabelaDeCores()
	{
		tabelaCores.put("Preto", Color.BLACK);
		tabelaCores.put("Azul", Color.BLUE);
		tabelaCores.put("Ciano", Color.CYAN);
		tabelaCores.put("Cinza escuro", Color.DARK_GRAY);
		tabelaCores.put("Cinza", Color.GRAY);
		tabelaCores.put("Verde", Color.GREEN);
		tabelaCores.put("Magenta", Color.MAGENTA);
		tabelaCores.put("Laranja", Color.ORANGE);
		tabelaCores.put("Rosa", Color.PINK);
		tabelaCores.put("Vermelho", Color.RED);
		tabelaCores.put("Branco", Color.WHITE);
		tabelaCores.put("Amarelo", Color.YELLOW);
	}

	/**
	 * @param nomeDaCor
	 *           Nome da cor.
	 * @return Cor relacionada ao nome.
	 */
	public Color getCor(String nomeDaCor)
	{
		return tabelaCores.get(nomeDaCor);
	}

	/**
	 * Adiciona uma figura.
	 * @param x
	 * @param y
	 * @param tipo Tipo da figura (1, 2 ou 3)
	 */
	public void addFiguraOriginal(int x, int y, int tipo)
	{
		// Declaracao de variaveis.
		Stack<Figura> pilhaFiguras;		
		
		// Instanciacao e armazenamento da figura.
		pilhaFiguras = new Stack<Figura>();
		figuraOriginal = new Virola(new Ponto(x, y), listaVirolas.get(tipo));		
		pilhaFiguras.push(figuraOriginal);
		
		pilhaPilhasFiguras.push(pilhaFiguras);
	}

	/**
	 * Realiza uma operacao de undo.
	 */
	public void undo()
	{
		// Declaracao de variaveis.
		Stack<Figura> pilhaFiguras;

		// Inicializacao.
		pilhaFiguras = pilhaPilhasFiguras.pop();
		if (pilhaFiguras == null)
			return;
		
		// Desempilha e substitui por nova pilha.
		if (pilhaFiguras.isEmpty() == false)
		{
			pilhaFiguras.pop();
			if (pilhaFiguras.isEmpty() == true)
			{
				if (pilhaPilhasFiguras.isEmpty() == false)
				{
					pilhaFiguras = pilhaPilhasFiguras.pop();
					if (pilhaFiguras.isEmpty() == false)
						figuraOriginal = pilhaFiguras.firstElement();
					else
						figuraOriginal = null;
				}
				else
					figuraOriginal = null;
			}
		}
		else
		{
			pilhaFiguras = pilhaPilhasFiguras.pop();
			if (pilhaFiguras != null)
				figuraOriginal = pilhaFiguras.firstElement();
			else
				figuraOriginal = null;
		}
				
		pilhaPilhasFiguras.push(pilhaFiguras);
	}

	/**
	 * Realiza escala simples 
	 * @param escala
	 * @return sucesso
	 */
	public boolean fazerEscalaSimples(double escala)
	{
		// Declaracao de variaveis.
		Stack<Figura> pilhaFiguras, pilhaTransformada;
		Transformacao transformacaoSimples;
		Figura novaFigura;
		
		// Inicializacao
		pilhaFiguras = pilhaPilhasFiguras.peek();
		pilhaTransformada = new Stack<Figura>();
		transformacaoSimples = new Transformacao();
		transformacaoSimples.fazEscala(escala);

		// Varre a pilha de figuras, aplicando a transformacao.
		for (Figura figura : pilhaFiguras)
		{
			novaFigura = Figura.valida(figura.fazTransformacao(transformacaoSimples, pontoReferencia), altura, largura);
			if (novaFigura != null)
				pilhaTransformada.add(novaFigura);
			else
				return false;
		}
		
		// Nao exibe as figuras originais.
		if (manterInicial == false)
		{
			pilhaFiguras = new Stack<Figura>();
			pilhaPilhasFiguras.push(pilhaFiguras);
		}

		// Adiciona nova pilha.
		pilhaFiguras.addAll(pilhaTransformada);
		
		return true;
	}

	/**
	 * Realiza escala composta 
	 * @param escala
	 * @return sucesso
	 */
	public void fazerEscalaComposta(double escala)
	{
		//	Declaracao de variaveis.
		List<Figura> listaTransformadas;
		Transformacao transformacao; 
		
		// Inicializacao.
		listaTransformadas = new ArrayList<Figura>();
		transformacao = new Transformacao();
		transformacao.fazEscala(escala);
		
		// Inicia transformacao composta.
		if (listaFigurasEmTransformacaoComposta.isEmpty() == true)
			listaFigurasEmTransformacaoComposta.addAll(pilhaPilhasFiguras.peek());

		// Aplica transformacao composta.
		for (Figura figura: listaFigurasEmTransformacaoComposta)
			listaTransformadas.add(figura.fazTransformacao(transformacao, pontoReferencia));
		
		// Adiciona transformacoes.
		listaFigurasEmTransformacaoComposta.clear();
		listaFigurasEmTransformacaoComposta.addAll(listaTransformadas);
	}

	/**
	 * Realiza rotacao simples 
	 * @param angulo
	 * @return sucesso
	 */
	public boolean fazerRotacaoSimples(int angulo)
	{
		// Declaracao de variaveis.
		Stack<Figura> pilhaFiguras, pilhaTransformada;
		Transformacao transformacaoSimples;
		Figura novaFigura;
		
		// Inicializacao
		pilhaFiguras = pilhaPilhasFiguras.peek();
		pilhaTransformada = new Stack<Figura>();
		transformacaoSimples = new Transformacao();
		transformacaoSimples.fazRotacao(angulo);

		// Varre a pilha de figuras, aplicando a transformacao.
		for (Figura figura : pilhaFiguras)
		{
			novaFigura = Figura.valida(figura.fazTransformacao(transformacaoSimples, pontoReferencia), altura, largura);
			if (novaFigura != null)
				pilhaTransformada.add(novaFigura);
			else
				return false;
		}
		
		//	 Nao exibe as figuras originais.
		if (manterInicial == false)
		{
			pilhaFiguras = new Stack<Figura>();
			pilhaPilhasFiguras.push(pilhaFiguras);
		}
		
		// Adiciona nova pilha.
		pilhaFiguras.addAll(pilhaTransformada);
		
		return true;
	}

	/**
	 * Realiza rotacao composta 
	 * @param angulo
	 * @return sucesso
	 */
	public void fazerRotacaoComposta(int angulo)
	{
		//	Declaracao de variaveis.
		List<Figura> listaTransformadas;
		Transformacao transformacao; 
		
		// Inicializacao.
		listaTransformadas = new ArrayList<Figura>();
		transformacao = new Transformacao();
		transformacao.fazRotacao(angulo);
		
		// Inicia transformacao composta.
		if (listaFigurasEmTransformacaoComposta.isEmpty() == true)
			listaFigurasEmTransformacaoComposta.addAll(pilhaPilhasFiguras.peek());

		// Aplica transformacao composta.
		for (Figura figura: listaFigurasEmTransformacaoComposta)
			listaTransformadas.add(figura.fazTransformacao(transformacao, pontoReferencia));
		
		// Adiciona transformacoes.
		listaFigurasEmTransformacaoComposta.clear();
		listaFigurasEmTransformacaoComposta.addAll(listaTransformadas);
	}

	/**
	 * Realiza translacao simples 
	 * @param x
	 * @param y
	 * @return sucesso
	 */
	public boolean fazerTranslacaoSimples(int x, int y)
	{
		// Declaracao de variaveis.
		Stack<Figura> pilhaFiguras, pilhaTransformada;
		Transformacao transformacaoSimples;
		Figura novaFigura;
		
		// Inicializacao
		pilhaFiguras = pilhaPilhasFiguras.peek();
		pilhaTransformada = new Stack<Figura>();
		transformacaoSimples = new Transformacao();
		transformacaoSimples.fazTranslacao(x, y);

		// Varre a pilha de figuras, aplicando a transformacao.
		for (Figura figura : pilhaFiguras)
		{
			novaFigura = Figura.valida(figura.fazTransformacao(transformacaoSimples), altura, largura);
			if (novaFigura != null)
				pilhaTransformada.add(novaFigura);
			else
				return false;
		}
		
		//	 Nao exibe as figuras originais.
		if (manterInicial == false)
		{
			pilhaFiguras = new Stack<Figura>();
			pilhaPilhasFiguras.push(pilhaFiguras);
		}

		// Adiciona nova pilha.
		pilhaFiguras.addAll(pilhaTransformada);
		
		return true;
	}

	/**
	 * Realiza translacao composta 
	 * @param x
	 * @param y
	 * @return sucesso
	 */
	public void fazerTranslacaoComposta(int x, int y)
	{
		//	Declaracao de variaveis.
		List<Figura> listaTransformadas;
		Transformacao transformacao; 
		
		// Inicializacao.
		listaTransformadas = new ArrayList<Figura>();
		transformacao = new Transformacao();
		transformacao.fazTranslacao(x, y);
		
		// Inicia transformacao composta.
		if (listaFigurasEmTransformacaoComposta.isEmpty() == true)
			listaFigurasEmTransformacaoComposta.addAll(pilhaPilhasFiguras.peek());

		// Aplica transformacao composta.
		for (Figura figura: listaFigurasEmTransformacaoComposta)
			listaTransformadas.add(figura.fazTransformacao(transformacao));
		
		// Adiciona transformacoes.
		listaFigurasEmTransformacaoComposta.clear();
		listaFigurasEmTransformacaoComposta.addAll(listaTransformadas);
	}

	public void changeManterInicial()
	{
		manterInicial = !manterInicial;
	}

	/**
	 * Reinicia as transformacoes compostas.
	 */
	public void reinicia()
	{
		listaFigurasEmTransformacaoComposta.clear();
		
		if (pilhaPilhasFiguras.isEmpty() == false)
			listaFigurasEmTransformacaoComposta.addAll(pilhaPilhasFiguras.peek());
	}
	
	/**
	 * Finaliza as transformacoes compostas. 
	 * @return sucesso
	 */
	public boolean finalizarTransformacoes()
	{
		// Declaracao de variaveis.
		Stack<Figura> pilhaFiguras;
		Figura novaFigura;
		
		pilhaFiguras = pilhaPilhasFiguras.peek();
		
		//	Varre a lista de figuras, validando.
		for (Figura figura: listaFigurasEmTransformacaoComposta)
		{
			novaFigura = Figura.valida(figura, altura, largura);
			if (novaFigura == null)
				return false;
		}
		
		// Nao exibe as figuras originais.
		if (manterInicial == false)
		{
			pilhaFiguras = new Stack<Figura>();
			pilhaPilhasFiguras.push(pilhaFiguras);
		}

		// Adiciona nova pilha e reinicia transformacoes.
		pilhaFiguras.addAll(listaFigurasEmTransformacaoComposta);
		reinicia();
		return true;
	}
	
	// Metodos utilitarios
	public void setDimensoesDesenho(int altura, int largura)
	{
		this.altura = altura;
		this.largura = largura;
	}
	
	public List<Figura> getListaFiguras()
	{
		return pilhaPilhasFiguras.peek();
	}
	
	public String[] getNomesDasCores()
	{
		return tabelaCores.keySet().toArray(new String[tabelaCores.size()]);
	}

	public void setPontoReferencia(int x, int y)
	{
		this.pontoReferencia = new Ponto(x, y);
	}

	/**
	 * @return  the pontoReferencia
	 * @uml.property  name="pontoReferencia"
	 */
	public int[] getPontoReferencia()
	{
		int[] pontoVet = new int[2];
		pontoVet[0] = pontoReferencia.getX();
		pontoVet[1] = pontoReferencia.getY();

		return pontoVet;
	}

}
