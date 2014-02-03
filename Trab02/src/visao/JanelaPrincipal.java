package visao;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import controle.*;

/**
 * Janela JFrame principal do programa. 
 * 
 * Abstrai a camada de visão do programa (MVC).  
 */
@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame implements ActionListener
{
	/**
	 * Controle utilizado.
	 */
	private ControlePrincipal controle;
	
	// Componentes GUI
	private JMenuBar mnbPrincipal;
	private JMenu mnuArquivo, mnuPlotar, mnuAjuda;
	private JMenuItem mniCarregar, mniSair, mniMostrarTodasRetas,
			mniMostrarRetasHorizontais, mniMostrarRetasVerticais,
			mniMostrarRetasNaoHorizontaisNemVerticais, mniMostrarRetasInterceptas,
			mniMostrarPoligonos, mniMudarCor, mniSobre;
	private JFileChooser fchCarregar;	
	private PanelDesenho pnlDesenho;
	
	// Construtor
	public JanelaPrincipal(ControlePrincipal controle)
	{
		// Construtor da superclasse
		super("Trabalho 2");
		
		// Inicialização do controle
		this.controle = controle;

		// Inicialização do GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setResizable(false);

		// Posicionamento
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int posX = (d.width - this.getWidth()) / 2;
		int posY = (d.height - this.getHeight()) / 2;
		setLocation(posX, posY);

		// Inicialização dos componentes
		initComponents();

		// Configurações dos componentes
		mnbPrincipal.add(mnuArquivo);
		mnbPrincipal.add(mnuPlotar);
		mnbPrincipal.add(mnuAjuda);

		mnuArquivo.add(mniCarregar);
		mnuArquivo.addSeparator();
		mnuArquivo.add(mniSair);

		mnuPlotar.add(mniMostrarTodasRetas);
		mnuPlotar.add(mniMostrarRetasHorizontais);
		mnuPlotar.add(mniMostrarRetasVerticais);
		mnuPlotar.add(mniMostrarRetasNaoHorizontaisNemVerticais);
		mnuPlotar.add(mniMostrarRetasInterceptas);
		mnuPlotar.add(mniMostrarPoligonos);
		mnuPlotar.addSeparator();
		mnuPlotar.add(mniMudarCor);
		
		mnuAjuda.add(mniSobre);

		// Registro dos listeners
		mniCarregar.addActionListener(this);
		mniSair.addActionListener(this);
		mniMostrarTodasRetas.addActionListener(this);
		mniMostrarRetasHorizontais.addActionListener(this);
		mniMostrarRetasVerticais.addActionListener(this);
		mniMostrarRetasNaoHorizontaisNemVerticais.addActionListener(this);
		mniMostrarRetasInterceptas.addActionListener(this);
		mniMostrarPoligonos.addActionListener(this);
		mniMudarCor.addActionListener(this);
		mniSobre.addActionListener(this);

		// Adição dos componentes
		setJMenuBar(mnbPrincipal);
		
		Container c = getContentPane();
		c.removeAll();		
		c.setLayout(new BorderLayout());
		c.add(pnlDesenho, BorderLayout.CENTER);		

		// Exibição
		setVisible(true);
	}

	// Métodos
	/**
	 * Instanciação dos componentes GUI.
	 */
	private void initComponents()
	{
		// JMenuBar e JMenu
      mnbPrincipal = new JMenuBar();
		mnuArquivo = new JMenu("Arquivo");
		mnuPlotar = new JMenu("Plotar");
		mnuAjuda = new JMenu("Ajuda");

		// JMenuItem
		mniCarregar = new JMenuItem("Carregar...");
		mniSair = new JMenuItem("Sair");
		mniMostrarTodasRetas = new JMenuItem("Mostrar todas as retas");
		mniMostrarRetasHorizontais = new JMenuItem("Mostrar as retas horizontais");
		mniMostrarRetasVerticais = new JMenuItem("Mostrar as retas verticais");
		mniMostrarRetasNaoHorizontaisNemVerticais = new JMenuItem(
				"Mostrar as retas não horizontais nem verticais");
		mniMostrarRetasInterceptas = new JMenuItem(
				"Mostrar as retas que se interceptam");
		mniMostrarPoligonos = new JMenuItem(
				"Mostrar os polígonos fechados formados pelas retas");
		mniMudarCor = new JMenuItem("Mudar a cor do desenho...");
		mniSobre = new JMenuItem("Sobre...");		

		// JPanel
		pnlDesenho = new PanelDesenho();		

		// JFileChooser
		fchCarregar = new JFileChooser();		
	}

	/**
	 * Manipulador do listener de ações dos componentes GUI.
	 */
	public void actionPerformed(ActionEvent evento)
	{
		JMenuItem obj = (JMenuItem) evento.getSource();

		// Carregar o arquivo
		if (obj == mniCarregar)
		{
			opcCarregar();
		}

		// Sair do programa
		else if (obj == mniSair)
		{
			System.exit(0);
		}
		
		// Mostrar todas as retas
		else if (obj == mniMostrarTodasRetas)
		{
			pnlDesenho.limpar();
			pnlDesenho.adicionarListaDeFormaDesenhavel(controle.getListaSegmentos());
			pnlDesenho.repaint();
		}

		// Mostrar as retas horizontais
		else if (obj == mniMostrarRetasHorizontais)
		{
			pnlDesenho.limpar();
			pnlDesenho.adicionarListaDeFormaDesenhavel(controle.retasHorizontais());
			pnlDesenho.repaint();
		}

		// Mostrar as retas verticais
		else if (obj == mniMostrarRetasVerticais)
		{
			pnlDesenho.limpar();
			pnlDesenho.adicionarListaDeFormaDesenhavel(controle.retasVerticais());
			pnlDesenho.repaint();		
		}

		// Mostrar as retas não horizontais nem verticais
		else if (obj == mniMostrarRetasNaoHorizontaisNemVerticais)
		{
			pnlDesenho.limpar();
			pnlDesenho.adicionarListaDeFormaDesenhavel(controle.retasNaoHorizontaisNemVerticais());
			pnlDesenho.repaint();		
		}

		// Mostrar as retas que se interceptam
		else if (obj == mniMostrarRetasInterceptas)
		{
			pnlDesenho.limpar();
			pnlDesenho.adicionarListaDeFormaDesenhavel(controle.retasInterceptadas());
			pnlDesenho.repaint();		
		}

		// Mostrar os polígonos fechados formados pelas retas
		else if (obj == mniMostrarPoligonos)
		{
			pnlDesenho.limpar();
			pnlDesenho.adicionarListaDeFormaDesenhavel(controle.retasPoligono());
			pnlDesenho.repaint();		
		}
		
		// Mudar a cor do desenho
		else if (obj == mniMudarCor)
		{
			String[] nomesDasCores = controle.getNomesDasCores();
			
			String nomeDaCor = (String) JOptionPane.showInputDialog(this, "Escolha a nova cor:", "Mudar cor", JOptionPane.INFORMATION_MESSAGE, null, nomesDasCores, nomesDasCores[0]);
			Color cor = controle.getCor(nomeDaCor);
			
			controle.mudarCor(cor);
			pnlDesenho.mudarCor(cor);	
		}

		// Exibir dados do programa
		else if (obj == mniSobre)
		{
			JOptionPane.showMessageDialog(this, "Trabalho 02 de CCO712 - Computação Gráfica I\n\nHeron Yugo Inouye - 11877\nTiago Romero Garcia - 12643", "Créditos",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Procedimentos do carregamento de um arquivo.
	 */
	@SuppressWarnings("unchecked")
	private void opcCarregar()
	{
		int returnVal = fchCarregar.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				controle.lerArquivo(fchCarregar.getSelectedFile());
				
				// Aviso do sucesso.
				JOptionPane.showMessageDialog(this, "Arquivo carregado com sucesso!", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				
			} catch (FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(this, "Arquivo não encontrado!",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} catch (EOFException e)
			{
				JOptionPane.showMessageDialog(this, "Arquivo inválido!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, "Erro na leitura do arquivo!",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
