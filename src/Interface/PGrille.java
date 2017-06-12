package Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import Moteur.Case;
/**
 * Class PGrille correspondant à l'interface de la grille du Puissance X
 */
public class PGrille extends JPanel {
	Rond[][] grille;
	public static ClickColEvent click = new ClickColEvent();
	private int nbLigne;
	private int nbColonne;
	private ArrayList<JButton> buttons;
	
	public PGrille(int nbCol, int nbLig) {
		nbLigne = nbLig;
		nbColonne = nbCol;
		initGrille();
	}

	public void paintComponent(Graphics g) {
		
		/*try {
			Image img = ImageIO.read(new File("fond-bleu.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	private void initGrille() {
		int tailleRond = -1;
		buttons = new ArrayList<JButton>();
		// Taille des ronds en fonction de la taille de la grille
		switch(nbColonne)
		{
			case 7:
				tailleRond = 65;
				break;
			case 8:
				tailleRond = 58;
				break;
			case 9:
				tailleRond = 51;
				break;
		}
		this.setLayout(new GridLayout(nbLigne + 1, nbColonne));

		grille = new Rond[nbLigne + 1][nbColonne];
		for (int i = 0; i < nbColonne; i++)
			for (int j = 0; j < nbColonne; j++)
				grille[i][j] = new Rond(tailleRond);

		for (int i = 0; i < nbColonne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				if (i < nbLigne)
					this.add(grille[i][j]);
				else {
					String name = String.valueOf(j);
					JButton b = new JButton();
					buttons.add(b);
					b.setName(name);
					b.setEnabled(false);
					b.addActionListener(click);
					this.add(b);

				}
			}
		}
	}
	/**
	 * Permet d'activer les boutons de la grille
	 * @param val True pour activer, False pour désactiver
	 */
	public void enableButtons(boolean val)
	{
		for (JButton button : buttons) {
		    button.setEnabled(val);
		}
	}

	/**
	 * Permet de synchroniser la grille interface avec celle du moteur de jeu
	 * @param g Grille du moteur de jeu
	 */
	public void actualise(Case[][] g) {
		for (int i = 0; i < nbColonne; i++)
			for (int j = 0; j < nbLigne; j++)
			{
				grille[j][i].setColor(g[i][j]);
			}
	}
}


