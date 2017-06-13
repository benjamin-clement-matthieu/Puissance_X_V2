package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 * Class ClickColEvent permettant de g�rer les boutons de la grille
 *
 */
public class ClickColEvent extends MouseAdapter {
	
	private int selectedCol;
	private boolean clicked = false;
	
	/*public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton) arg0.getSource();
		selectedCol = Integer.valueOf(b.getName());
		clicked = true;
		
	}*/
	
	/**
	 * Permet de savoir si un bouton a �t� cliqu�
	 * @return
	 */
	public boolean clicked()
	{
		return clicked;
	}
	
	public void setClicked(boolean val)
	{
		clicked = val;
	}
	
	public void mousePressed(MouseEvent e) {
		Rond rond = (Rond)e.getSource();
		this.selectedCol = rond.getColonne();
		clicked = true;
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

