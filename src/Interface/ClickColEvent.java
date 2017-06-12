package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * Class ClickColEvent permettant de gérer les boutons de la grille
 *
 */
public class ClickColEvent implements ActionListener {
	
	private int selectedCol = -1;
	private boolean clicked = false;
	
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton) arg0.getSource();
		selectedCol = Integer.valueOf(b.getName());
		clicked = true;
	}
	
	/**
	 * Permet de savoir si un bouton a été cliqué
	 * @return
	 */
	public boolean clicked()
	{
		return clicked;
	}
	
	/**
	 * Permet de récupérer la colonne sélectionnée.
	 * @return La colonne sélectionnée
	 */
	public int getSelectedCol()
	{
		clicked = false;
		return selectedCol;
	}
}

