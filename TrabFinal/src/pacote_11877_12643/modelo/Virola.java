package pacote_11877_12643.modelo;

import javax.swing.*;

/**
 * Figura que representa uam virola.
 */
public class Virola extends Figura
{
	// Atributos
	/**
	 * Ponto central
	 */
	private Ponto centro;
	/**
	 * Altura da virola
	 */
	private int altura;
	/**
	 * Largura da virola
	 */
	private int largura;
	/**
	 * Quantidade de arcos do quadrante superior esquerdo
	 */
	private int quantidadeArcosSuperior;
	/**
	 * Quantidade de arcos do quadrante inferior esquerdo
	 */
	private int quantidadeArcosInferior;
	/**
	 * Descricao de cada arco de quadrante superior (raio, X centro e Y centro)
	 */
	private int[] arcosSuperior;
	/**
	 * Descricao de cada arco de quadrante inferior (raio, X centro e Y centro)
	 */
	private int[] arcosInferior;

	// Construtores
	public Virola(Ponto centro, int quantidadeArcosSuperior,
			int[] arcosSuperior, int quantidadeArcosInferior, int[] arcosInferior,
			int largura, int altura)
	{
		super();
		this.centro = centro;
		this.altura = altura;
		this.largura = largura;
		this.quantidadeArcosSuperior = quantidadeArcosSuperior;
		this.quantidadeArcosInferior = quantidadeArcosInferior;
		this.arcosSuperior = arcosSuperior;
		this.arcosInferior = arcosInferior;

		constroiVirola();
	}

	public Virola(Ponto centro, Virola modelo)
	{
		this(centro, modelo.quantidadeArcosSuperior, modelo.arcosSuperior,
				modelo.quantidadeArcosInferior, modelo.arcosInferior,
				modelo.largura, modelo.altura);
	}

	// Metodos
	private void constroiVirola()
	{
		// Declaracao de variaveis
		Circunferencia circulo, circuloEsquerdo, circuloDireito;
		Ponto p1, pFoco, pFocoEsquerdo, pFocoDireito, pInicioArco, pFimArco, pIntersecaoArcos;
		int xCentro, yCentro, xArco, yArco, raioArco, yMax, yMin, yMinArco, yMaxArco, xMin, xMinArco, xArcoEsquerdo, yArcoEsquerdo, raioArcoEsquerdo, xArcoDireito, yArcoDireito, raioArcoDireito;

		// Inicializacao
		xCentro = centro.getX();
		yCentro = centro.getY();

		// Verificando o caso com 1 arco
		if (quantidadeArcosSuperior == 1)
		{
			raioArco = arcosSuperior[0];
			xArco = xCentro - arcosSuperior[1];
			yArco = yCentro - arcosSuperior[2];
			yMinArco = yArco - raioArco;
			xMinArco = xArco - raioArco;
			yMin = yCentro - altura / 2;
			xMin = xCentro - largura / 2;
			pFoco = new Ponto(xArco, yArco);
			circulo = new Circunferencia(pFoco, raioArco);

			/** *************************************************************************** */
			// Verificando a parte de cima
			// Verificando se o X do arco está à esquerda do centro
			if (xArco < xCentro)
			{
				// Ponto de yMin no eixo Y
				p1 = new Ponto(xCentro, yMin);

				// Verificando o caso em que o arco NÃO é cortado superiormente pela
				// caixa
				if (yMin < yMinArco)
				{
					pInicioArco = new Ponto(xArco, yMinArco);// Início superior do
																			// arco
				}
				// Verificando o caso em que o arco É cortado superiormente pela
				// caixa
				else
				{
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circulo, yMin);
				}
				// Imprimindo reta da parte superior do arco
				listaFormasPlotaveis.add(new Segmento(p1, pInicioArco));
			}
			// Verificando se o X do arco está à direita do centro
			else
			{
				// Verificando o caso em que o arco NÃO é cortado superiormente pela
				// caixa
				if (yMin < yMinArco)
				{
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circulo, xCentro, true);// Início superior do arco
				}
				// Verificando o caso em que o arco É cortado superiormente pela
				// caixa
				else
				{
					// Ponto de yMin no eixo Y
					p1 = new Ponto(xCentro, yMin);
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circulo, yMin);
					// Imprimindo reta da parte superior do arco cortada pela caixa
					listaFormasPlotaveis.add(new Segmento(p1, pInicioArco));
				}
			}
			/** *************************************************************************** */
			// Verificando a parte da esquerda
			// Verificando se o Y do arco está acima do centro
			if (yArco < yCentro)
			{
				// Ponto de xMin no eixo X
				p1 = new Ponto(xMin, yCentro);

				// Verificando o caso em que o arco NÃO é cortado à esquerda pela
				// caixa
				if (xMin < xMinArco)
				{
					pFimArco = new Ponto(xMinArco, yArco);// Início superior do arco
				}
				// Verificando o caso em que o arco É cortado à esquerda pela caixa
				else
				{
					pFimArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circulo, xMin, true);
				}
				// Imprimindo reta da parte esquerda do arco
				listaFormasPlotaveis.add(new Segmento(p1, pFimArco));
			}

			// Verificando se o Y do arco está abaixo do centro
			else
			{
				// Verificando o caso em que o arco NÃO é cortado à esquerda pela
				// caixa
				if (xMin < xMinArco)
				{
					pFimArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circulo, yCentro);
				} else
				{
					// Ponto de xMin no eixo X
					p1 = new Ponto(xMin, yCentro);
					pFimArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circulo, xMin, true);
					// Imprimindo reta da parte esquerda do arco cortada pela caixa
					listaFormasPlotaveis.add(new Segmento(p1, pFimArco));
				}
			}
			/** *************************************************************************** */
			listaFormasPlotaveis.add(new ArcoDeCircunferencia(pFoco, raioArco,
					pInicioArco, pFimArco));
		}
		/** *************************************************************************** */
		// Verificando o caso com 2 arcos
		else if (quantidadeArcosSuperior == 2)
		{
			// Verificando qual arco está mais à esquerda
			if (xCentro - arcosSuperior[1] < xCentro - arcosSuperior[4])
			{
				raioArcoEsquerdo = arcosSuperior[0];
				xArcoEsquerdo = xCentro - arcosSuperior[1];
				yArcoEsquerdo = yCentro - arcosSuperior[2];

				raioArcoDireito = arcosSuperior[3];
				xArcoDireito = xCentro - arcosSuperior[4];
				yArcoDireito = yCentro - arcosSuperior[5];

			} else
			{
				raioArcoDireito = arcosSuperior[0];
				xArcoDireito = xCentro - arcosSuperior[1];
				yArcoDireito = yCentro - arcosSuperior[2];

				raioArcoEsquerdo = arcosSuperior[3];
				xArcoEsquerdo = xCentro - arcosSuperior[4];
				yArcoEsquerdo = yCentro - arcosSuperior[5];
			}
			pFocoEsquerdo = new Ponto(xArcoEsquerdo, yArcoEsquerdo);
			circuloEsquerdo = new Circunferencia(pFocoEsquerdo, raioArcoEsquerdo);
			pFocoDireito = new Ponto(xArcoDireito, yArcoDireito);
			circuloDireito = new Circunferencia(pFocoDireito, raioArcoDireito);

			yMinArco = yArcoDireito - raioArcoDireito;
			xMinArco = xArcoEsquerdo - raioArcoEsquerdo;

			yMin = yCentro - altura / 2;
			xMin = xCentro - largura / 2;

			/** *************************************************************************** */
			// Verificando a parte de cima
			// Verificando se o X do arco está à esquerda do centro
			if (xArcoDireito < xCentro)
			{
				// Ponto de yMin no eixo Y
				p1 = new Ponto(xCentro, yMin);

				// Verificando o caso em que o arco NÃO é cortado superiormente pela
				// caixa
				if (yMin < yMinArco)
				{
					pInicioArco = new Ponto(xArcoDireito, yMinArco);// Início
																					// superior do
																					// arco
				}
				// Verificando o caso em que o arco É cortado superiormente pela
				// caixa
				else
				{
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circuloDireito, yMin);
				}
				// Imprimindo reta da parte superior do arco
				listaFormasPlotaveis.add(new Segmento(p1, pInicioArco));
			}
			// Verificando se o X do arco está à direita do centro
			else
			{
				// Verificando o caso em que o arco NÃO é cortado superiormente pela
				// caixa
				if (yMin < yMinArco)
				{
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circuloDireito, xCentro, true);// Início superior do arco
				}
				// Verificando o caso em que o arco É cortado superioyCentrormente
				// pela caixa
				else
				{
					// Ponto de yMin no eixo Y
					p1 = new Ponto(xCentro, yMin);
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circuloDireito, yMin);
					// Imprimindo reta da parte superior do arco cortada pela caixa
					listaFormasPlotaveis.add(new Segmento(p1, pInicioArco));
				}
			}
			/** *************************************************************************** */
			// Verificando a parte da esquerda
			// Verificando se o Y do arco está acima do centro
			if (yArcoEsquerdo < yCentro)
			{
				// Ponto de xMin no eixo X
				p1 = new Ponto(xMin, yCentro);

				// Verificando o caso em que o arco NÃO é cortado à esquerda pela
				// caixa
				if (xMin < xMinArco)
				{
					pFimArco = new Ponto(xMinArco, yArcoEsquerdo);// Início
																					// superior do
																					// arco
				}
				// Verificando o caso em que o arco É cortado à esquerda pela caixa
				else
				{
					pFimArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circuloEsquerdo, xMin, true);
				}
				// Imprimindo reta da parte esquerda do arco
				listaFormasPlotaveis.add(new Segmento(p1, pFimArco));
			}

			// Verificando se o Y do arco está abaixo do centro
			else
			{
				// Verificando o caso em que o arco NÃO é cortado à esquerda pela
				// caixa
				if (xMin < xMinArco)
				{
					pFimArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circuloEsquerdo, yCentro);// Início superior do arco
				} else
				{
					// Ponto de xMin no eixo X
					p1 = new Ponto(xMin, yCentro);
					pFimArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circuloEsquerdo, xMin, true);
					// Imprimindo reta da parte esquerda do arco cortada pela caixa
					listaFormasPlotaveis.add(new Segmento(p1, pFimArco));
				}
			}
			/** *************************************************************************** */
			pIntersecaoArcos = Operacoes.intersecaoArcos(circuloEsquerdo,
					circuloDireito, true);
			listaFormasPlotaveis.add(new ArcoDeCircunferencia(pFocoDireito,
					raioArcoDireito, pInicioArco, pIntersecaoArcos));
			listaFormasPlotaveis.add(new ArcoDeCircunferencia(pFocoEsquerdo,
					raioArcoEsquerdo, pIntersecaoArcos, pFimArco));
		}

		/** ************************************************************************************************************************ */
		// Desenhando a parte inferior
		/** ************************************************************************************************************************ */

		// Verificando o caso com 1 arco
		if (quantidadeArcosInferior == 1)
		{
			raioArco = arcosInferior[0];
			xArco = xCentro - arcosInferior[1];
			yArco = yCentro + arcosInferior[2];
			yMaxArco = yArco + raioArco;
			xMinArco = xArco - raioArco;
			yMax = yCentro + altura / 2;
			xMin = xCentro - largura / 2;
			pFoco = new Ponto(xArco, yArco);
			circulo = new Circunferencia(pFoco, raioArco);
			if (yMax > yMaxArco)
			{
				pFimArco = new Ponto(xArco, yMaxArco);
			}
			/** *************************************************************************** */
			// Verificando a parte debaixo
			// Verificando se o X do arco está à esquerda do centro
			if (xArco < xCentro)
			{
				// Ponto de yMax no eixo Y
				p1 = new Ponto(xCentro, yMax);

				// Verificando o caso em que o arco NÃO é cortado inferiormente pela
				// caixa
				if (yMax > yMaxArco)
				{
					pFimArco = new Ponto(xArco, yMaxArco);
				}
				// Verificando o caso em que o arco É cortado inferiormente pela
				// caixa
				else
				{
					pFimArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circulo, yMax);
				}
				// Imprimindo reta da parte inferior do arco
				listaFormasPlotaveis.add(new Segmento(p1, pFimArco));
			}
			// Verificando se o X do arco está à direita do centro
			else
			{
				// Verificando o caso em que o arco NÃO é cortado inferiormente pela
				// caixa
				if (yMax > yMaxArco)
				{
					pFimArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circulo, xCentro, false);
				}
				// Verificando o caso em que o arco É cortado inferiormente pela
				// caixa
				else
				{
					// Ponto de yMax no eixo Y
					p1 = new Ponto(xCentro, yMax);
					pFimArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circulo, yMax);
					// Imprimindo reta da parte inferior do arco cortada pela caixa
					listaFormasPlotaveis.add(new Segmento(p1, pFimArco));
				}
			}
			/** *************************************************************************** */
			// Verificando a parte da esquerda
			// Verificando se o Y do arco está acima do centro
			if (yArco < yCentro)
			{
				// Ponto de xMin no eixo X
				p1 = new Ponto(xMin, yCentro);

				// Verificando o caso em que o arco NÃO é cortado à esquerda pela
				// caixa
				if (xMin < xMinArco)
				{
					pInicioArco = new Ponto(xMinArco, yArco);// Início superior do
																			// arco
				}
				// Verificando o caso em que o arco É cortado à esquerda pela caixa
				else
				{
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circulo, xMin, false);
				}
				// Imprimindo reta da parte esquerda do arco
				listaFormasPlotaveis.add(new Segmento(p1, pInicioArco));
			}

			// Verificando se o Y do arco está abaixo do centro
			else
			{
				// Verificando o caso em que o arco NÃO é cortado à esquerda pela
				// caixa
				if (xMin < xMinArco)
				{
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circulo, yCentro);// Início superior do arco
				} else
				{
					// Ponto de xMin no eixo X
					p1 = new Ponto(xMin, yCentro);
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circulo, xMin, false);
					// Imprimindo reta da parte esquerda do arco cortada pela caixa
					listaFormasPlotaveis.add(new Segmento(p1, pInicioArco));
				}
			}
			/** *************************************************************************** */
			listaFormasPlotaveis.add(new ArcoDeCircunferencia(pFoco, raioArco,
					pInicioArco, pFimArco));
		}
		/** *************************************************************************** */
		// Verificando o caso com 2 arcos
		else if (quantidadeArcosInferior == 2)
		{
			// Verificando qual arco está mais à esquerda
			if (xCentro - arcosInferior[1] < xCentro - arcosInferior[4])
			{
				raioArcoEsquerdo = arcosInferior[0];
				xArcoEsquerdo = xCentro - arcosInferior[1];
				yArcoEsquerdo = yCentro + arcosInferior[2];

				raioArcoDireito = arcosInferior[3];
				xArcoDireito = xCentro - arcosInferior[4];
				yArcoDireito = yCentro + arcosInferior[5];

			} else
			{
				raioArcoDireito = arcosInferior[0];
				xArcoDireito = xCentro - arcosInferior[1];
				yArcoDireito = yCentro + arcosInferior[2];

				raioArcoEsquerdo = arcosInferior[3];
				xArcoEsquerdo = xCentro - arcosInferior[4];
				yArcoEsquerdo = yCentro + arcosInferior[5];
			}
			pFocoEsquerdo = new Ponto(xArcoEsquerdo, yArcoEsquerdo);
			circuloEsquerdo = new Circunferencia(pFocoEsquerdo, raioArcoEsquerdo);
			pFocoDireito = new Ponto(xArcoDireito, yArcoDireito);
			circuloDireito = new Circunferencia(pFocoDireito, raioArcoDireito);

			yMaxArco = yArcoDireito + raioArcoDireito;
			xMinArco = xArcoEsquerdo - raioArcoEsquerdo;

			yMax = yCentro + altura / 2;
			xMin = xCentro - largura / 2;

			/** *************************************************************************** */
			// Verificando a parte debaixo
			// Verificando se o X do arco está à esquerda do centro
			if (xArcoDireito < xCentro)
			{
				// Ponto de yMin no eixo Y
				p1 = new Ponto(xCentro, yMax);

				// Verificando o caso em que o arco NÃO é cortado inferiormente pela
				// caixa
				if (yMax > yMaxArco)
				{
					pFimArco = new Ponto(xArcoDireito, yMaxArco);
				}
				// Verificando o caso em que o arco É cortado inferiormente pela
				// caixa
				else
				{
					pFimArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circuloDireito, yMax);
				}
				// Imprimindo reta da parte superior do arco
				listaFormasPlotaveis.add(new Segmento(p1, pFimArco));
			}
			// Verificando se o X do arco está à direita do centro
			else
			{
				// Verificando o caso em que o arco NÃO é cortado inferiormente pela
				// caixa
				if (yMax > yMaxArco)
				{
					pFimArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circuloDireito, xCentro, false);
				}
				// Verificando o caso em que o arco É cortado inferiormente pela
				// caixa
				else
				{
					// Ponto de yMax no eixo Y
					p1 = new Ponto(xCentro, yMax);
					pFimArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circuloDireito, yMax);
					// Imprimindo reta da parte inferior do arco cortada pela caixa
					listaFormasPlotaveis.add(new Segmento(p1, pFimArco));
				}
			}
			/** *************************************************************************** */
			// Verificando a parte da esquerda
			// Verificando se o Y do arco está acima do centro
			if (yArcoEsquerdo < yCentro)
			{
				// Ponto de xMin no eixo X
				p1 = new Ponto(xMin, yCentro);

				// Verificando o caso em que o arco NÃO é cortado à esquerda pela
				// caixa
				if (xMin < xMinArco)
				{
					pInicioArco = new Ponto(xMinArco, yArcoEsquerdo);// Início
																						// superior do
																						// arco
				}
				// Verificando o caso em que o arco É cortado à esquerda pela caixa
				else
				{
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circuloEsquerdo, xMin, false);
				}
				// Imprimindo reta da parte esquerda do arco
				listaFormasPlotaveis.add(new Segmento(p1, pInicioArco));
			}

			// Verificando se o Y do arco está abaixo do centro
			else
			{
				// Verificando o caso em que o arco NÃO é cortado à esquerda pela
				// caixa
				if (xMin < xMinArco)
				{
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaHorizontal(
							circuloEsquerdo, yCentro);
				} else
				{
					// Ponto de xMin no eixo X
					p1 = new Ponto(xMin, yCentro);
					pInicioArco = Operacoes.intersecaoCircunferenciaRetaVertical(
							circuloEsquerdo, xMin, false);
					// Imprimindo reta da parte esquerda do arco cortada pela caixa
					listaFormasPlotaveis.add(new Segmento(p1, pInicioArco));
				}
			}
			/** *************************************************************************** */
			// Calculo da intersecao entre os arcos 
			pIntersecaoArcos = Operacoes.intersecaoArcos(circuloEsquerdo,
					circuloDireito, false);
			if (pIntersecaoArcos.equals(new Ponto(0, 0)))
			{
				pFocoDireito = new Ponto(xArcoDireito + 1, yArcoDireito);
				circuloDireito = new Circunferencia(pFocoDireito, raioArcoDireito);
				pIntersecaoArcos = Operacoes.intersecaoArcos(circuloEsquerdo,
						circuloDireito, false);

				if (pIntersecaoArcos.equals(new Ponto(0, 0)))
					JOptionPane.showMessageDialog(null,
							"Figura não possui ponto de intersecao entre arcos!",
							"Erro!", JOptionPane.ERROR_MESSAGE);
			}

			// Calculo dos arcos
			listaFormasPlotaveis.add(new ArcoDeCircunferencia(pFocoDireito,
					raioArcoDireito, pIntersecaoArcos, pFimArco));
			listaFormasPlotaveis.add(new ArcoDeCircunferencia(pFocoEsquerdo,
					raioArcoEsquerdo, pInicioArco, pIntersecaoArcos));
		}

		// Reflexao sob o eixo vertical
		Operacoes.fazReflexaoHorizontal(listaFormasPlotaveis, xCentro);

	}

}