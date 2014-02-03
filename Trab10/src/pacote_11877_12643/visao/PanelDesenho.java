package pacote_11877_12643.visao;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

import pacote_11877_12643.modelo.*;

/**
 * Panel para o desenho das formas desenh�veis desejadas. 
 */
@SuppressWarnings("serial")
public class PanelDesenho extends JPanel 
{
	// Atributos
	/**
	 * Cor que o desenho sera pintado.
	 */
	private Color cor;
	
	// Relacionamentos
	/**
	 * Lista de Formas Desenhaveis a ser desenhada em cada chamada
	 * do metodo paint().
	 */
	private List<FormaDesenhavel> listaDesenhavel;
	
	private String posicao;

	// Construtor
	public PanelDesenho()
   {
	   listaDesenhavel = new ArrayList<FormaDesenhavel>();
	   posicao = "";
	   
	   cor = Color.BLACK;
	   
	   this.addMouseMotionListener(new MouseMotionListener(){
	   	public void mouseMoved(MouseEvent arg0)
	   	{
	   		posicao = arg0.getPoint().toString();
	   		repaint();
	   	}

			public void mouseDragged(MouseEvent arg0)
			{
			}
	   });
   }
	
	// Metodos
	/**
	 * Limpa a lista existente.
	 */
	public void limpar()
	{
		listaDesenhavel.clear();
	}
	
	/**
	 * Adiciona uma forma desenhavel a lista. 
	 * @param forma	Forma desenhavel a ser adicionada.
	 */
	public void adicionarFormaDesenhavel(FormaDesenhavel forma)
	{
		listaDesenhavel.add(forma);
	}
	
	/**
	 * Adiciona uma lista de formas desenhaveis a lista. 
	 * @param lista	Lista de formas desenhaveis a ser adicionada.
	 */
	public void adicionarListaDeFormaDesenhavel(Collection<? extends FormaDesenhavel> lista)
	{
		listaDesenhavel.addAll(lista);
	}

   /**
    * Executa os procedimentos de desenho deste componente.
    */
	public void paint(Graphics desenho)
   {
		desenho.clearRect(0, 0, this.getWidth(), this.getHeight());
		desenho.drawString(posicao, 0, 20);
		
			
   	// Percorre a lista de formas desenhaveis.
   	for (FormaDesenhavel forma: listaDesenhavel)
   		forma.desenhar(desenho, cor);
   }
   
	/**
	 * Executa um procedimento de alterar a cor de todas as formas
	 * desenhaveis.
	 * @param cor	Cor a ser assumida.
	 */
   public void mudarCor(Color cor)
	{
   	// Percorre a lista de formas desenhaveis.
		this.cor = cor;
		
		// Redesenha.
		repaint();
	}
}
