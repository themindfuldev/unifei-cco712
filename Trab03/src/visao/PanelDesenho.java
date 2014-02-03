package visao;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import modelo.*;

/**
 * Panel para o desenho das formas desenh�veis desejadas. 
 */
@SuppressWarnings("serial")
public class PanelDesenho extends JPanel
{
	// Atributos
	/**
	 * Cor que o desenho ser� pintado.
	 */
	private Color cor;
	
	// Relacionamentos
	/**
	 * Lista de Formas Desenh�veis a ser desenhada em cada chamada
	 * do m�todo paint().
	 */
	private List<FormaDesenhavel> listaDesenhavel;

	// Construtor
	public PanelDesenho()
   {
	   listaDesenhavel = new LinkedList<FormaDesenhavel>();
	   cor = Color.BLACK;
   }
	
	// M�todos
	/**
	 * Limpa a lista existente.
	 */
	public void limpar()
	{
		listaDesenhavel.clear();
	}
	
	/**
	 * Adiciona uma forma desenh�vel � lista. 
	 * @param forma	Forma desenhavel a ser adicionada.
	 */
	public void adicionarFormaDesenhavel(FormaDesenhavel forma)
	{
		listaDesenhavel.add(forma);
	}
	
	/**
	 * Adiciona uma lista de formas desenh�veis � lista. 
	 * @param lista	Lista de formas desenh�veis a ser adicionada.
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
   	// Percorre a lista de formas desenh�veis.
   	for (FormaDesenhavel forma: listaDesenhavel)
   		forma.desenhar(desenho, cor);
   }
   
	/**
	 * Executa um procedimento de alterar a cor de todas as formas
	 * desenh�veis.
	 * @param cor	Cor a ser assumida.
	 */
   public void mudarCor(Color cor)
	{
   	// Percorre a lista de formas desenh�veis.
		this.cor = cor;
		
		// Redesenha.
		repaint();
	}
	
}
