package Moteur;
import java.util.ArrayList;

import Interface.Plateau;
import Interface.SoundEffect;
/**
 * Class Jeu contenant plusieurs fonctions de gestion de la grille principale du Puissance X
 *
 */
public class Jeu {
	private Case[][] grille;
	private int tHorizontale;
	private int tVerticale;
	private Plateau plateau;
	private int nbAligneMax;
	
	public Jeu(Plateau p, int tHo, int tVe)
	{
		tHorizontale = tHo;
		tVerticale = tVe;
		plateau = p;
		
		switch (Options.Valeurs.getPuissanceMode())
		{
			case PUISSANCE4:
				nbAligneMax = 4;
				break;
			case PUISSANCE5:
				nbAligneMax = 5;
				break;
			case PUISSANCE6:
				nbAligneMax = 6;
				break;
		}
		
		InitGrille();
	}
	
	private void InitGrille()
	{
		grille = new Case[tHorizontale][tVerticale];
		
		for (int i = 0; i < tHorizontale; i++)
			for (int j = 0; j < tVerticale; j++)
				grille[i][j] = Case.VIDE;
	}
	
	/**
	 * Joue un coup
	 * @param col Colonne du coup
	 * @param joueur Joueur qui joue le coup
	 * @return True si le coup a pu �tre jou�, sinon False
	 */
	public boolean jouerCoup(int col, Joueur joueur)
	{
		if (col <= tVerticale &&  col >= 0)
		{
			int ligneChoisie = -1;
			// On regarde chaque case de la colonne en partant du bas
			for(int i = tVerticale - 1; i >= 0; i--)
				// Si une case est vide, le coup peut être joué
				if (grille[col][i] == Case.VIDE)
				{
//					grille[col][i] = joueur.getCouleur();
//					plateau.getPGrille().actualise(grille);
//					return true; 
					
					ligneChoisie = i;
					break;
				}
					
			// ANIMATION
			
			// Si le coup peut être joué
			if (ligneChoisie != -1)
			{
				// On parcout chaque case jusqu'à la ligne choisie
				for (int i = 0; i < ligneChoisie; i++)
				{
					// On met la couleur du joueur dans la case
					grille[col][i] = joueur.getCouleur();
					plateau.getPGrille().actualise(grille);
					
					
					// On attend 20 millisecondes
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// On remet la couleur de la case à VIDE
					grille[col][i] = Case.VIDE;
					plateau.getPGrille().actualise(grille);
				}
				
				// On change la couleur de la case finale
				grille[col][ligneChoisie] = joueur.getCouleur();
				plateau.getPGrille().actualise(grille);
				
				return true;
			}
		}
		
		return false;
	}
	/**
	 * Joue un coup sans animation et sans actualiser l'interface, elle permet de tester un coup.
	 * @param col Colonne du coup
	 * @param joueur Joueur qui joue le coup
	 * @return True si le coup a pu �tre jou�, sinon False
	 */
	
	public boolean jouerCoupTmp(int col, Joueur joueur)
	{
		for(int i = tVerticale - 1; i >= 0; i--)
			if (grille[col][i] == Case.VIDE)
			{
				grille[col][i] = joueur.getCouleur();
				return true;
			}
		return false;
	}
	
	/**
	 * Annule le coup sans actualiser l'interface, elle permet de retirer un coup de test.
	 * @param col Colonne du coup
	 */
	
	public void annulerCoup(int col)
	{
		// Trouve enleve la premi�re case non vide de la colonne
		for(int i = 0; i < tVerticale; i++)
			if (grille[col][i] != Case.VIDE)
			{
				grille[col][i] = Case.VIDE;
				break;
			}
	}
	
	/**
	 * Permet si savoir si la grille est pleine.
	 * @return True si la grille est plein, sinon False
	 */
	public boolean grillePleine()
	{
		// S'il n'y a pas de coup possible, la grille est pleine
		return getCoupsPossible().size() == 0;
	}
	
	/**
	 * V�rifie si le joueur a gagn�.
	 * @param joueur Le joueur qui doit �tre v�rifi�
	 * @return True si le joueur a gagn�, sinon False
	 */
	public boolean verifierGagnant(Joueur joueur)
	{
		for (int i = 0; i < tHorizontale; i++)
		{
			if (grille[i][tVerticale - 1] == Case.VIDE)
				continue;
		
			for (int j = 0; j < tVerticale; j++){
				//Si on a trouvé un jeton du joueur, on regarde dans toutes les directions
				if (grille[i][j] == joueur.getCouleur()){
					// Boucle pour toutes les directions (Nord, NordEst, Sud etc ...)
					for (int k = 0; k < 8; k++)
						if (verifierDirection(joueur, i, j, k, nbAligneMax))
							return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Permet d'avoir les coups possibles.
	 * @return Liste d'entiers des colonnes jouables.
	 */
	// Retourne les coups possible
	public ArrayList<Integer> getCoupsPossible()
	{
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		// On regarde chaque case de la première ligne, si une est vide la grille le coup est possible.
		for (int i = 0; i < tHorizontale; i++)
			if (grille[i][0] == Case.VIDE)
					tmp.add(i);
		
		return tmp;
	}
	
	/**
	 * Permet de v�rifier s'il y a nbAligne jetons align�s dans une direction � partir d'un point x,y
	 * et s'il est possible d'avoir nbAligneMax jetons align�s dans cette m�me direction
	 * @param j Le joueur a v�rifier
	 * @param x Num�ro de la colonne
	 * @param y Num�ro de la ligne
	 * @param direction Compris entre 0 et 7 pour indiquer la direction (ex: 0 correspont au Nord)
	 * @param nbAligne Nombre de jeton align�s a tester
	 * @return True si il y a nbAligne de jeton align�s et s'il est possible d'en aligner 
	 * le nombre maximale pour gagner, sinon False
	 */
	private boolean verifierDirection(Joueur j, int x, int y, int direction, int nbAligne)
	{
		for (int i = 1; i < nbAligneMax; i++)
		{
			int x2 = x;
			int y2 = y;
			
			switch (direction)
			{
				case 0: // Nord
					y2 -= i;
					break;
				case 1: // NordEst
					y2 -= i;
					x2 += i;
					break;
				case 2: // NordOuest
					y2 -= i;
					x2 -= i;
					break;
				case 3: // Sud
					y2 += i;
					break;
				case 4: // SudEst
					y2 += i;
					x2 += i;
					break;
				case 5: // SudOuest
					y2 += i;
					x2 -= i;
					break;
				case 6: // Ouest
					x2 -= i;
					break;
				case 7: // Est
					x2 += i;
					break;
			}
			
			//Si le point x2,y2 n'est pas dans la grille il n'y a donc pas d'alignement
			if (!estDansLaGrille(x2,y2))
				return false;
			
			// Si la case n'est pas celle du joueur
			// et si on test toujours s'il y a nbAligne de jetons align�
			if (grille[x2][y2] != j.getCouleur() && i < nbAligne)
				return false;
			
			// Si une des cases est celle d'un joueur adversaire, il n'est pas possible d'aligner
			// nbAligneMax de jetons
			if (grille[x2][y2] != j.getCouleur() && grille[x2][y2] != Case.VIDE)
				return false;
		}
		
		
		return true;
	}
	
	/**
	 * Permet de compter les alignements de nbAligne jetons pour un joueur donn�
	 * et s'il est possible d'en aller nbAligneMax.
	 * @param nbAligne Nombre de jetons align�s � compter
	 * @param joueur Le joueur a tester
	 * @return Le nombre d'alignement.
	 */
	public int nombreAlignement(int nbAligne, Joueur joueur)
	{
		int count = 0;
		for (int i = 0; i < tHorizontale; i++)
			for (int j = 0; j < tVerticale; j++)
				if (grille[i][j] == joueur.getCouleur())
					for (int k = 0; k < 8; k++)
						if (verifierDirection(joueur, i, j, k, nbAligne))
							count++;
		return count;
	}
	
	/**
	 * Permet de savoir si le point x, y est dans la grille
	 * @param x Num�ro de la colonne
	 * @param y Num�ro de la ligne
	 * @return	True si le point est dans la grille, sinon False
	 */
	public boolean estDansLaGrille(int x, int y)
	{
		// On enlève 1 à la taille Horizontale et Verticale car le tableau commence à 0 et non pas 1
		int tH = tHorizontale - 1;
		int tV = tVerticale - 1;
		
		return !(x > tH || x < 0 || y > tV || y < 0);
	}
	
	/**
	 * Permet d'avoir le plateau
	 * @return Le plateau
	 */
	public Plateau getPlateau()
	{
		return plateau;
	}
	

	/**
	 * Permet d'avoir la grille
	 * @return La grille
	 */
	public Case[][] getGrille()
	{
		return grille;
	}
	
	/**
	 * Permet d'avoir la largeur de la grille
	 * @return La largeur de la grille
	 */
	public int getTHorizontale()
	{
		return tHorizontale;
	}
	
	/**
	 * Permet d'avoir la hauteur de la grille
	 * @return La grille hauteur de la grille
	 */
	public int getTVerticale()
	{
		return tVerticale;
	}
	
	/**
	 * Permet d'avoir le nombre d'alignement de jeton pour gagner une partie
	 * @return Le nombre d'alignement de jeton pour gagner une partie
	 */
	public int getNbAligneMax()
	{
		return nbAligneMax;
	}
}

