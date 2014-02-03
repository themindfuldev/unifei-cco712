package pacote_11877_12643.visao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import pacote_11877_12643.controle.*;


/**
 * @author  tiago
 */
@SuppressWarnings("serial")
public class DialogoTranslacao extends JDialog implements ActionListener
{
	// Controle
	private MediatorTranslacao mediator;
	
	// Componentes GUI
	private JLabel lblX;
	// Componentes GUI
	private JLabel lblY;
	private JTextField txfX;
	private JTextField txfY;
	private JButton btnOK;
	private JButton btnCancelar;
	private JPanel pnlBotoes;

	public DialogoTranslacao(JFrame janela, MediatorTranslacao controle)
	{
		super(janela, "Nova translacao");
		
		setModal(true);		
		this.mediator = controle;
		
		// Inicializacao do GUI
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(250, 150);
		setResizable(false);

		// Posicionamento
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int posX = (d.width - this.getWidth()) / 2;
		int posY = (d.height - this.getHeight()) / 2;
		setLocation(posX, posY);

      // Inicializacao dos componentes
      initComponents();

      // Configuracoes dos componentes
      lblX.setHorizontalAlignment(SwingConstants.RIGHT);
      lblY.setHorizontalAlignment(SwingConstants.RIGHT);
      
      // Registro dos listeners
      txfX.addActionListener(this);
      txfY.addActionListener(this);
      btnOK.addActionListener(this);
      btnCancelar.addActionListener(this);

      // Adicao dos componentes      
      GridLayout layout = new GridLayout(1, 2);
      layout.setHgap(10);
      pnlBotoes.setLayout(layout);
      pnlBotoes.add(btnOK);
      pnlBotoes.add(btnCancelar);
      
      getContentPane().setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.fill = GridBagConstraints.HORIZONTAL;
      c.insets = new Insets(5, 0, 0, 0);
      c.ipadx = 10;      
      add(lblX, c);
      
      c.fill = GridBagConstraints.NONE;
      c.gridx = 1;
      c.ipadx = 80;
      add(txfX, c);
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.insets = new Insets(0, 0, 0, 0);      
      c.gridy = 1;
      c.gridx = 0;  
      c.ipadx = 10; 
      add(lblY, c);
      
      c.fill = GridBagConstraints.NONE;
      c.gridx = 1;    
      c.ipadx = 80;
      add(txfY, c);
      
      c.fill = GridBagConstraints.NONE;  
      c.insets = new Insets(20, 0, 0, 0);
      c.anchor = GridBagConstraints.CENTER;
      c.gridy = 2;
      c.gridx = 0;
      c.ipadx = 0;
      c.gridwidth = 2;
      add(pnlBotoes, c);
      
      // Exibicao
      setVisible(true);
   }

   // Metodos
   /**
    * Instanciacao dos componentes GUI.
    */
   private void initComponents()
   {
      // JLabel
      lblX = new JLabel("Deslocamento em X: ");
      lblY = new JLabel("Deslocamento em Y: ");      

      // JTextField
      txfX = new JTextField(3);
      txfY = new JTextField(3);      
      
      // JButton
      btnOK = new JButton("OK");
      btnCancelar = new JButton("Cancelar");      
      
      // JPanel
      pnlBotoes = new JPanel();
   }

   /**
    * Manipulador do listener de acoes dos componentes GUI.
    */
   public void actionPerformed(ActionEvent evento)
   {
      Object obj = evento.getSource();

      // Tenta aceitar translacao
      if (obj == btnOK || obj == txfX || obj == txfY)
      {
         optOK();            
      }
      // Sair do programa
      else if (obj == btnCancelar)
      {
         dispose();
      }      
   }

   /**
    * Aceitar translacao.
    */
   private void optOK()
   {
      // Obtem os dados digitados
      String xStr = txfX.getText();
      String yStr = txfY.getText();
      
      // Validacao dos dados
      int x = -1, y = -1;        
      
      try
      {
      	x = Integer.parseInt(xStr);
      	y = Integer.parseInt(yStr);         
      } 
      catch (NumberFormatException e)
      {
         JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!", "Erro!", JOptionPane.ERROR_MESSAGE);
         if (x == -1)
         	txfX.requestFocus();
         else
         	txfY.requestFocus();
         return;
      }      

      // Cria um controle com os dados digitados e validados
      mediator.setPonto(x, y);
      
      dispose();
   }
}
