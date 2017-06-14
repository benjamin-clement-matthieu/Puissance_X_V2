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
 * Class PGrille correspondant � l'interface de la grille du Puissance X
 */
public class PGrille extends JPanel {
	Rond[][] grille;
	public static ClickColEvent click = new ClickColEvent();
	private int nbLigne;
	private int nbColonne;
	
	public PGrille(int nbCol, int nbLig) {
		nbLigne = nbLig;
		nbColonne = nbCol;
		initGrille();
	}

	public void paintComponent(Graphics g) {
		
		try {
			Image img = ImageIO.read(new File(this.getClass().getClassLoader().getResource("bleu1.jpg").getPath()));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initGrille() {
		int tailleRond = -1;
		// Taille des ronds en fonction de la taille de la grille
		switch(nbColonne)
		{
			case 7:
				tailleRond = 65;
				break;
			case 8:
				tailleRond = 55;
				break;
			case 9:
				tailleRond = 48;
				break;
		}
		this.setLayout(new GridLayout(nbLigne, nbColonne));

		grille = new Rond[nbLigne][nbColonne];
		
		for (int i = 0; i < nbLigne; i++)
			for (int j = 0; j < nbColonne; j++)
			{
				Rond rond = new Rond(tailleRond, j);
				rond.addMouseListener(click);
				grille[i][j] = rond;
			}
				

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
					this.add(grille[i][j]);
			}
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


