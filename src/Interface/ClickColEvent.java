package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * Class ClickColEvent permettant de g�rer les boutons de la grille
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
	 * Permet de savoir si un bouton a �t� cliqu�
	 * @return
	 */
	public boolean clicked()
	{
		return clicked;
	}
	
	/**
	 * Permet de r�cup�rer la colonne s�lectionn�e.
	 * @return La colonne s�lectionn�e
	 */
	public int getSelectedCol()
	{
		clicked = false;
		return selectedCol;
	}
}

