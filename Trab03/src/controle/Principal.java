package controle;

import visao.*;

/**
 *	Chamada principal do programa. 
 */
public class Principal
{
	public static void main(String[] args)
	{
		// Instancia o controle e a janela.
		new JanelaPrincipal(new ControlePrincipal());
	}

}
