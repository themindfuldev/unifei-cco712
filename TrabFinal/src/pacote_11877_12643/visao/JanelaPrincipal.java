package pacote_11877_12643.visao;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import pacote_11877_12643.controle.*;

/**
 * @author  tiago
 */
@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame implements ActionListener, MouseListener
{
	// Controle
	private ControlePrincipal controle;
	
	// Componentes GUI
	private JLabel lblObjetos;
	// Componentes GUI
	private JLabel lblCores;
	private JComboBox cmbObjetos;
	private JComboBox cmbCores;
	private JRadioButton rdoReferenciaOrigem;
	private JRadioButton rdoOutroPontoReferencia;
	private JRadioButton rdoTransformacaoSimples;
	private JRadioButton rdoTransformacaoComposta;
	private JPanel pnlMenuSuperior;
	private JPanel pnlMenuInferior;
	private JPanel pnlReferencia;
	private JPanel pnlTipoTransformacao;
	private PanelDesenho pnlAreaDesenho;
	private JButton btnReinicia;
	private JButton btnTranslada;
	private JButton btnRotaciona;
	private JButton btnEscala;
	private JButton btnUndo;
	private JButton btnFinalizaTransformacoes;
	private JButton btnFechaPrograma;
	private JCheckBox chkMantemInicial;

	public JanelaPrincipal(ControlePrincipal controle)
	{
		super("Trabalho Final: Transformacoes Geometricas");
		this.controle = controle;
		controle.setPontoReferencia(0, 0);

		// Inicializacao do GUI
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(d.width, d.height - 50);
		setResizable(false);

		pnlMenuSuperior = new JPanel();
		pnlMenuInferior = new JPanel();
		pnlAreaDesenho = new PanelDesenho();
		pnlReferencia = new JPanel();
		pnlTipoTransformacao = new JPanel();
		
		pnlMenuSuperior.setBackground(Color.ORANGE);
		pnlAreaDesenho.setBackground(Color.WHITE);
		pnlMenuInferior.setBackground(Color.ORANGE);
		pnlReferencia.setBackground(Color.ORANGE);
		pnlTipoTransformacao.setBackground(Color.ORANGE);

		String[] objetos = {"", "Desenho 1", "Desenho 2", "Desenho 3" };
		lblObjetos = new JLabel("Tipos de objetos:");
		cmbObjetos = new JComboBox(objetos);
		cmbObjetos.addActionListener(this);

		String[] cores = controle.getNomesDasCores();
		lblCores = new JLabel("Cores disponiveis:");
		cmbCores = new JComboBox(cores);
		cmbCores.setSelectedItem("Preto");
		cmbCores.addActionListener(this);

		rdoReferenciaOrigem = new JRadioButton("Referencia na origem", true);
		rdoReferenciaOrigem.addActionListener(this);
		rdoOutroPontoReferencia = new JRadioButton(
				"Outro ponto de referencia            ");
		rdoOutroPontoReferencia.addActionListener(this);
		rdoReferenciaOrigem.setBackground(Color.ORANGE);
		rdoOutroPontoReferencia.setBackground(Color.ORANGE);

		// Agrupando os radiobutton de ponto de referencia
		ButtonGroup grpReferencia = new ButtonGroup();
		grpReferencia.add(rdoReferenciaOrigem);
		grpReferencia.add(rdoOutroPontoReferencia);
		pnlReferencia.setLayout(new GridLayout(2, 1));
		pnlReferencia.add(rdoReferenciaOrigem);
		pnlReferencia.add(rdoOutroPontoReferencia);

		// Instanciando checkbox
		chkMantemInicial = new JCheckBox("Mantem desenho inicial", true);
		chkMantemInicial.setBackground(Color.ORANGE);
		chkMantemInicial.addActionListener(this);
		pnlMenuInferior.setLayout(new FlowLayout());
		pnlMenuSuperior.setLayout(new FlowLayout());

		// Inserindo os itens no menu superior
		pnlMenuSuperior.add(lblObjetos);
		pnlMenuSuperior.add(cmbObjetos);
		pnlMenuSuperior.add(pnlReferencia);
		pnlMenuSuperior.add(lblCores);
		pnlMenuSuperior.add(cmbCores);
		pnlMenuSuperior.add(chkMantemInicial);

		// Criando botoes do menu inferior
		btnEscala = new JButton("Escala");
		btnReinicia = new JButton("Reinicia");
		btnRotaciona = new JButton("Rotaciona");
		btnTranslada = new JButton("Translada");
		btnUndo = new JButton("Undo");
		btnFinalizaTransformacoes = new JButton("Finaliza transformacoes");
		btnFechaPrograma = new JButton("Fecha programa");
		
		// Configurando botoes
		btnReinicia.setEnabled(false);
		btnRotaciona.setEnabled(false);
		btnTranslada.setEnabled(false);
		btnUndo.setEnabled(false);
		btnEscala.setEnabled(false);
		btnFinalizaTransformacoes.setEnabled(false);

		// Adicionando listener nos botoes
		btnEscala.addActionListener(this);
		btnReinicia.addActionListener(this);
		btnRotaciona.addActionListener(this);
		btnTranslada.addActionListener(this);
		btnUndo.addActionListener(this);
		btnFinalizaTransformacoes.addActionListener(this);
		btnFechaPrograma.addActionListener(this);

		// Inserindo os itens no menu inferior
		pnlTipoTransformacao.setLayout(new GridLayout(2, 1));
		rdoTransformacaoSimples = new JRadioButton("Transformacao simples", true);
		rdoTransformacaoSimples.addActionListener(this);
		rdoTransformacaoComposta = new JRadioButton("Transformacao composta");
		rdoTransformacaoComposta.addActionListener(this);
		rdoTransformacaoSimples.setBackground(Color.ORANGE);
		rdoTransformacaoComposta.setBackground(Color.ORANGE);
		pnlTipoTransformacao.add(rdoTransformacaoSimples);
		pnlTipoTransformacao.add(rdoTransformacaoComposta);

		// Agrupando os radiobutton de ponto de referencia
		ButtonGroup grpTransformacao = new ButtonGroup();
		grpTransformacao.add(rdoTransformacaoSimples);
		grpTransformacao.add(rdoTransformacaoComposta);

		pnlMenuInferior.add(btnReinicia);
		pnlMenuInferior.add(btnTranslada);
		pnlMenuInferior.add(btnRotaciona);
		pnlMenuInferior.add(btnEscala);
		pnlMenuInferior.add(btnFinalizaTransformacoes);
		pnlMenuInferior.add(pnlTipoTransformacao);
		pnlMenuInferior.add(btnUndo);
		pnlMenuInferior.add(btnFechaPrograma);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0)
			{
				optSair();
			}
		});
		
		Container c = getContentPane();
		c.removeAll();
		c.setLayout(new BorderLayout());
		c.add(pnlMenuSuperior, BorderLayout.NORTH);
		c.add(pnlAreaDesenho, BorderLayout.CENTER);
		c.add(pnlMenuInferior, BorderLayout.SOUTH);
		setVisible(true);
		
		controle.setDimensoesDesenho(pnlAreaDesenho.getHeight(), pnlAreaDesenho.getWidth());
	}

   /**
    * Manipulador do listener de acoes dos componentes GUI.
    */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();

		if (source == cmbObjetos)
		{
			optObjetos(cmbObjetos.getSelectedIndex());
			return;
		}
		if (source == cmbCores)
		{
			pnlAreaDesenho.mudarCor(controle.getCor((String) cmbCores
					.getSelectedItem()));
			return;
		}
		if (source == chkMantemInicial)
		{
			optManterInicial();
			return;
		}
		if (source == btnEscala)
		{
			optEscala();
			return;
		}
		if (source == btnReinicia)
		{
			JOptionPane.showMessageDialog(this, "Operacoes compostas zeradas",
					"Transformacao composta", JOptionPane.INFORMATION_MESSAGE);
			optReinicia();
			return;
		}
		if (source == btnRotaciona)
		{
			optRotacionar();
			return;
		}
		if (source == btnFinalizaTransformacoes)
		{
			optFinalizarTransformacoes();
			return;
		}
		if (source == btnFechaPrograma)
		{
			optSair();
			return;
		}
		if (source == btnTranslada)
		{
			optTransladar();
			return;
		}
		if (source == btnUndo)
		{
			optUndo();
			return;
		}
		if (source == rdoReferenciaOrigem)
		{
			optZerarReferencia();
			return;
		}
		if (source == rdoOutroPontoReferencia)
		{
			optMudarReferencia();
			return;
		}
		if (source == rdoTransformacaoComposta)
		{
			optTransformacaoComposta();
			return;
		}
		if (source == rdoTransformacaoSimples)
		{
			optTransformacaoSimples();
		}
	}

	// Operacoes da interface
	/**
	 * Realizar transformacao simples.
	 */
	private void optTransformacaoSimples()
	{
		JOptionPane.showMessageDialog(this, "Clique nas operacoes para efetua-las.",
				"Transformacao Simples", JOptionPane.INFORMATION_MESSAGE);
		btnReinicia.setEnabled(false);
		btnFinalizaTransformacoes.setEnabled(false);
	}

	/**
	 * Realizar transformacao composta.
	 */
	private void optTransformacaoComposta()
	{
		JOptionPane.showMessageDialog(this, "Clique nas operacoes desejadas e depois em 'Finalizar Transformacao' para efetua-las.",
				"Transformacao Composta", JOptionPane.INFORMATION_MESSAGE);
		btnReinicia.setEnabled(true);
		btnFinalizaTransformacoes.setEnabled(true);
		optReinicia();
	}

	/**
	 * Reiniciar transformacao composta.
	 */
	private void optReinicia()
	{
		controle.reinicia();
	}

	/**
	 * Alterar manter inicial.
	 */
	private void optManterInicial()
	{
		controle.changeManterInicial();
	}

	/**
	 * Realizar undo
	 */
	private void optUndo()
	{
		controle.undo();

		// atualizar lista de figuras exibida no panel
		pnlAreaDesenho.limpar();
		pnlAreaDesenho
				.adicionarListaDeFormaDesenhavel(controle.getListaFiguras());
		pnlAreaDesenho.repaint();
		
		// desabilitacao
		if (controle.getListaFiguras().size() == 0)
		{
			cmbObjetos.setSelectedIndex(0);
			
			btnRotaciona.setEnabled(false);
			btnTranslada.setEnabled(false);
			btnUndo.setEnabled(false);
			btnEscala.setEnabled(false);
		}
	}

	/**
	 * Inserir figura
	 * @param selectedIndex indice da figura
	 */
	private void optObjetos(int selectedIndex)
	{
		if (selectedIndex < 1 || selectedIndex > 3)
			return;

		btnRotaciona.setEnabled(true);
		btnTranslada.setEnabled(true);
		btnUndo.setEnabled(true);
		btnEscala.setEnabled(true);
		
		controle.addFiguraOriginal(pnlAreaDesenho.getWidth()/2, pnlAreaDesenho.getHeight()/2, selectedIndex-1);
		
		// atualizar lista de figuras exibida no panel
		pnlAreaDesenho.limpar();
		pnlAreaDesenho
				.adicionarListaDeFormaDesenhavel(controle.getListaFiguras());
		pnlAreaDesenho.repaint();
	}

	/**
	 * Zera referencia para (0, 0).
	 */
	private void optZerarReferencia()
	{
		JOptionPane.showMessageDialog(this, "Ponto de referencia mudado para: (0, 0).", 
				"Mudar ponto de referencia", JOptionPane.INFORMATION_MESSAGE);
		controle.setPontoReferencia(0, 0);
	}

	/**
	 * Altera referencia.
	 */
	private void optMudarReferencia()
	{
		JOptionPane.showMessageDialog(this, "Clique no local onde deseja definir o novo ponto de referencia.",
				"Mudar ponto de referencia", JOptionPane.INFORMATION_MESSAGE);
		pnlAreaDesenho.addMouseListener(this);
	}

	/**
	 * Realiza escala.
	 */
	private void optEscala()
	{
		// Declaracao de variaveis.
		double escala;
		String escalaStr;
		
		// Inicializacao e entrada.
		escala = 0;
		escalaStr = JOptionPane.showInputDialog("Digite o fator de escala: ");
		if (escalaStr == null)
			return;
		try
		{
			escala = Double.parseDouble(escalaStr);
		} catch (NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(this, "Preencha o campo corretamente!",
					"Erro!", JOptionPane.ERROR_MESSAGE);
		}

		// Realiza operacao.
		if (rdoTransformacaoSimples.isSelected() == false)
		{
			controle.fazerEscalaComposta(escala);
			return;
		}
		if (controle.fazerEscalaSimples(escala) == false)
		{
			JOptionPane.showMessageDialog(this, "Figura fora das dimensões da tela!", "Erro!", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// atualizar lista de figuras exibida no panel
		pnlAreaDesenho.limpar();
		pnlAreaDesenho
				.adicionarListaDeFormaDesenhavel(controle.getListaFiguras());
		pnlAreaDesenho.repaint();
	}

	/**
	 * Realiza rotacao.
	 */
	private void optRotacionar()
	{		
		// Declaracao de variaveis.
		int angulo;
		String anguloStr;

		// Inicializacao e entrada.
		angulo = 0;
		anguloStr = JOptionPane.showInputDialog("Digite o angulo de rotacao (em graus): ");
		if (anguloStr == null)
			return;
		try
		{
			angulo = Integer.parseInt(anguloStr);
		} catch (NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(this, "Preencha o campo corretamente!",
					"Erro!", JOptionPane.ERROR_MESSAGE);
		}

		// Realiza operacao.
		if (rdoTransformacaoSimples.isSelected() == false)
		{
			controle.fazerRotacaoComposta(angulo);
			return;
		}
		if (controle.fazerRotacaoSimples(angulo) == false)
		{
			JOptionPane.showMessageDialog(this, "Figura fora das dimensões da tela!", "Erro!", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// atualizar lista de figuras exibida no panel
		pnlAreaDesenho.limpar();
		pnlAreaDesenho
				.adicionarListaDeFormaDesenhavel(controle.getListaFiguras());
		pnlAreaDesenho.repaint();
	}

	/**
	 * Realiza translacao.
	 */
	private void optTransladar()
	{
		// Declaracao de variaveis.
		int x, y;
		MediatorTranslacao mediator;

		//	Inicializacao e entrada.
		mediator = new MediatorTranslacao();
		new DialogoTranslacao(this, mediator);

		x = mediator.getX();
		y = mediator.getY();

		// Realiza operacao.
		if (rdoTransformacaoSimples.isSelected() == false)
		{
			controle.fazerTranslacaoComposta(x, y);
			return;
		}
		if (controle.fazerTranslacaoSimples(x, y) == false)
		{
			JOptionPane.showMessageDialog(this, "Figura fora das dimensões da tela!", "Erro!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// atualizar lista de figuras exibida no panel
		pnlAreaDesenho.limpar();
		pnlAreaDesenho
				.adicionarListaDeFormaDesenhavel(controle.getListaFiguras());
		pnlAreaDesenho.repaint();
	}
	
	/**
	 * Finaliza transformacoes compostas.
	 */
	private void optFinalizarTransformacoes()
	{
		if (rdoTransformacaoSimples.isSelected() == true)
			return;
		
		if (controle.finalizarTransformacoes() == false)
		{
			JOptionPane.showMessageDialog(this, "Figura fora das dimensões da tela!", "Erro!", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// atualizar lista de figuras exibida no panel
		pnlAreaDesenho.limpar();
		pnlAreaDesenho
				.adicionarListaDeFormaDesenhavel(controle.getListaFiguras());
		pnlAreaDesenho.repaint();
	}

	/**
	 * Evento de mouse para definir pontos com clique.
	 */
	public void mouseClicked(MouseEvent e)
	{
		//	Declaracao de variaveis.
		String ponto;
		int x, y;
		
		//	Inicializacao.
		x = e.getX();
		y = e.getY();
		
		controle.setPontoReferencia(x, y);
		
		ponto = String.format("(%3d,%3d).", x, y);
		
		// Mensagens
		JOptionPane.showMessageDialog(this, "Ponto de referencia mudado para: " + ponto,
				"Mudar ponto de referencia", JOptionPane.INFORMATION_MESSAGE);
		rdoOutroPontoReferencia.setText("Outro ponto de referencia: " + ponto);
	
		// Desabilita evento
		pnlAreaDesenho.removeMouseListener(this);
	}

	/**
	 * Finaliza programa.
	 */
	private void optSair()
	{
		int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmacao", JOptionPane.YES_NO_OPTION);
		if (opcao == 0)
			System.exit(0);
	}	
	
	// Metodos nao-implementados de listeners
	public void mouseEntered(MouseEvent arg0)
	{ }

	public void mouseExited(MouseEvent arg0)
	{ }

	public void mousePressed(MouseEvent arg0)
	{	}

	public void mouseReleased(MouseEvent arg0)
	{ }

}
