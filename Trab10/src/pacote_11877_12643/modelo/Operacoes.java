package pacote_11877_12643.modelo;

import java.util.*;

/**
 * Classe que abriga operacoes realizaveis entre os objetos graficos do
 * trabalho.
 */
public class Operacoes
{
	/**
	 * Calcula a insersecao entre uma circunferencia e uma reta horizontal.
	 * 
	 * @param circulo
	 *           circulo em questao
	 * @param yAltura
	 *           coordenada y da reta horizontal
	 * @return O ponto de intersecao
	 */
	public static Ponto intersecaoCircunferenciaRetaHorizontal(
			Circunferencia circulo, int yAltura)
	{
		// Declaracao de variaveis.
		Ponto centro;
		int x;

		// Calculo do ponto.
		centro = circulo.getCentro();
		x = (int) (centro.getX() - Math.sqrt(Math.pow(circulo.getRaio(), 2)
				- Math.pow(yAltura - centro.getY(), 2)));

		return new Ponto(x, yAltura);
	}

	/**
	 * Calcula a insersecao entre uma circunferencia e uma reta vertical.
	 * 
	 * @param circulo
	 *           circulo em questao
	 * @param xLargura
	 *           coordenada x da reta vertical
	 * @param superior
	 *           indica se deseja o ponto no quadrante superior ou nao
	 * @return O ponto de intersecao
	 */
	public static Ponto intersecaoCircunferenciaRetaVertical(
			Circunferencia circulo, int xLargura, boolean superior)
	{
		// Declaracao de variaveis.
		int y, delta;
		Ponto centro;

		// Calcuo do ponto.
		centro = circulo.getCentro();
		delta = (int) (Math.sqrt(Math.pow(circulo.getRaio(), 2)
				- Math.pow(xLargura - centro.getX(), 2)));

		// Escolha do ponto em relacao ao quadrante.
		if (superior)
			y = centro.getY() - delta;
		else
			y = centro.getY() + delta;

		return new Ponto(xLargura, y);
	}

	/**
	 * Calcula a intersecao entre dois arcos.
	 * 
	 * @param cA
	 *           circunferencia representando um arco
	 * @param cB
	 *           circunferencia representando outro arco
	 * @param superior
	 *           indica se deseja o ponto no quadrante superior ou nao.
	 * @return O ponto de intersecao ou o ponto (0, 0) se nao encontrou
	 */
	public static Ponto intersecaoArcos(Circunferencia cA, Circunferencia cB,
			boolean superior)
	{
		// Declaracao de variaveis.
		float dx, dy, d2;
		double d, a, h, x2, y2, paX, paY, pbX, pbY;

		// Calculo das distancias.
		dx = cA.getCentro().getX() - cB.getCentro().getX();
		dy = cA.getCentro().getY() - cB.getCentro().getY();
		d2 = dx * dx + dy * dy;
		d = Math.sqrt((double) d2);

		// Sem intersecao.
		if (d > cA.getRaio() + cB.getRaio()
				|| d < Math.abs(cA.getRaio() - cB.getRaio()))
			return new Ponto(0, 0);

		// Calculo do ponto.
		a = (Math.pow(cA.getRaio(), 2) - Math.pow(cB.getRaio(), 2) + d2)
				/ (2 * d);
		h = Math.sqrt(Math.pow(cA.getRaio(), 2) - a * a);
		x2 = cA.getCentro().getX() + a
				* (cB.getCentro().getX() - cA.getCentro().getX()) / d;
		y2 = cA.getCentro().getY() + a
				* (cB.getCentro().getY() - cA.getCentro().getY()) / d;

		paX = x2 + h * (cB.getCentro().getY() - cA.getCentro().getY()) / d;
		paY = y2 - h * (cB.getCentro().getX() - cA.getCentro().getX()) / d;
		pbX = x2 - h * (cB.getCentro().getY() - cA.getCentro().getY()) / d;
		pbY = y2 + h * (cB.getCentro().getX() - cA.getCentro().getX()) / d;

		// Escolha do ponto em relacao ao quadrante.
		if (superior)
		{
			if (paY < pbY)
				return new Ponto((int) paX, (int) paY);
			else
				return new Ponto((int) pbX, (int) pbY);
		} else
		{
			if (paY > pbY)
				return new Ponto((int) paX, (int) paY);
			else
				return new Ponto((int) pbX, (int) pbY);
		}

	}

	/**
	 * Realiza a reflexao horizontal em relacao a um eixo.
	 * 
	 * @param listaFormasPlotaveis
	 *           lista preenchida com o lado esquerdo da figura
	 * @param xEixo
	 *           coordenada x do eixo vertical
	 */
	public static void fazReflexaoHorizontal(
			List<FormaPlotavel> listaFormasPlotaveis, int xEixo)
	{
		// Declaracao de variaveis.
		FormaPlotavel reflexao;
		List<Ponto> listaRefletida;
		Ponto pontoRefletido;
		int distancia;

		// Inicializacao.
		reflexao = new FormaPlotavel();
		listaRefletida = reflexao.getListaPontos();

		// Varredura da lista e reflexao ponto a ponto.
		for (FormaPlotavel forma : listaFormasPlotaveis)
		{
			for (Ponto ponto : forma.getListaPontos())
			{
				distancia = xEixo - ponto.getX();
				pontoRefletido = new Ponto(xEixo + distancia, ponto.getY());

				listaRefletida.add(pontoRefletido);
			}
		}
		listaFormasPlotaveis.add(reflexao);
	}

	/**
	 * Calcula o angulo correto relativo ao arco, em relacao a origem, obtido por
	 * arco seno.
	 * 
	 * @param centro
	 *           ponto central do arco
	 * @param raio
	 *           raio do arco
	 * @param pontoArco
	 *           ponto final do arco
	 * @return
	 */
	public static double anguloDeArco(Ponto centro, int raio, Ponto pontoArco)
	{
		// Declaracao de variaveis
		double angulo;

		// Inicializacao
		angulo = 0.0;

		// Quadrante 1
		if (pontoArco.getX() >= centro.getX()
				&& pontoArco.getY() <= centro.getY())
			angulo = Math.PI / 2
					- Math.asin(((double) centro.getX() - pontoArco.getX()) / raio);
		// Quadrante 2
		else if (pontoArco.getX() <= centro.getX()
				&& pontoArco.getY() <= centro.getY())
			angulo = Math.PI / 2
					+ Math.asin(((double) centro.getX() - pontoArco.getX()) / raio);
		// Quadrante 3
		else if (pontoArco.getX() <= centro.getX()
				&& pontoArco.getY() >= centro.getY())
			angulo = Math.PI * 1.5
					- Math.asin(((double) centro.getX() - pontoArco.getX()) / raio);
		// Quadrante 4
		else if (pontoArco.getX() >= centro.getX()
				&& pontoArco.getY() >= centro.getY())
			angulo = Math.PI * 1.5
					+ Math.asin(((double) centro.getX() - pontoArco.getX()) / raio);

		return angulo;
	}
	
}
