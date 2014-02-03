package visao;

import java.awt.*;
import javax.swing.*;

import controle.*;

/**
 * Janela JFrame principal do programa. 
 * 
 * Abstrai a camada de visão do programa (MVC).  
 */
@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame 
{
	/**
	 * Controle utilizado.
	 */
	private ControlePrincipal controle;
	
	// Componentes GUI
	private PanelDesenho pnlDesenho;
	
	// Construtor
	public JanelaPrincipal(ControlePrincipal controle)
	{
		// Construtor da superclasse
		super("Trabalho 4: Plotando circunferências");
		
		// Inicialização do controle
		this.controle = controle;

		// Inicialização do GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 550);
		setResizable(false);

		// Posicionamento
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int posX = (d.width - this.getWidth()) / 2;
		int posY = (d.height - this.getHeight()) / 2;
		setLocation(posX, posY);

		// Inicialização dos componentes
		pnlDesenho = new PanelDesenho();		
		
		// Configuração dos componentes
		pnlDesenho.adicionarListaDeFormaDesenhavel(this.controle.getListaFiguras());
		
		// Adição dos componentes
		Container c = getContentPane();
		c.removeAll();		
		c.setLayout(new BorderLayout());
		c.add(pnlDesenho, BorderLayout.CENTER);		

		// Exibição
		setVisible(true);
	}
}
