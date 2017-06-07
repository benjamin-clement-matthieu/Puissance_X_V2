package Moteur;
import java.util.ArrayList;

import Interface.Plateau;

public class Jeu {
	private Case[][] grille;
	private int tHorizontale;
	private int tVerticale;
	private Plateau plateau;
	private int nbAligne;
	
	public Jeu(Plateau p, int tHo, int tVe)
	{
		tHorizontale = tHo;
		tVerticale = tVe;
		plateau = p;
		
		switch (Options.Valeurs.getPuissanceMode())
		{
			case PUISSANCE4:
				nbAligne = 4;
				break;
			case PUISSANCE5:
				nbAligne = 5;
				break;
			case PUISSANCE6:
				nbAligne = 6;
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
					
					
					// On attend 50 millisecondes
					try {
						Thread.sleep(50);
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
	
	public boolean grillePleine()
	{
		// S'il n'y a pas de coup possible, la grille est pleine
		return getCoupsPossible().size() == 0;
	}
	
	public boolean verifierGagnant(Joueur joueur)
	{
		for (int i = 0; i < tHorizontale; i++)
			for (int j = 0; j < tVerticale; j++)
				//Si on a trouvé un jeton du joueur, on regarde dans toutes les directions
				if (grille[i][j] == joueur.getCouleur()){
					// Boucle pour toutes les directions (Nord, NordEst, Sud etc ...)
					for (int k = 0; k < 8; k++)
						if (verifierDirection(joueur, i, j, k))
							return true;
				}
		return false;
	}
	
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
	
	// Permet de vérifier s'il a y 3 jetons alignés dans une direction
	private boolean verifierDirection(Joueur j, int x, int y, int direction)
	{
		for (int i = 1; i < nbAligne; i++)
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
			
			// Si la case n'est pas celle du joueur ou si le point x2,y2 n'est pas dans la grille
			if (!estDansLaGrille(x2,y2) || grille[x2][y2] != j.getCouleur())
				return false;
		}
		
		return true;
	}
	
	private boolean estDansLaGrille(int x, int y)
	{
		// On enlève 1 à la taille Horizontale et Verticale car le tableau commence à 0 et non pas 1
		int tH = tHorizontale - 1;
		int tV = tVerticale - 1;
		
		return !(x > tH || x < 0 || y > tV || y < 0);
	}
	
	// Retourne le plateau qui contient l'interface
	public Plateau getPlateau()
	{
		return plateau;
	}
	
	// Retourne la grille du jeu
	public Case[][] getGrille()
	{
		return grille;
	}
	
	// Retourne la taille horizontale de la grille
	public int getTHorizontale()
	{
		return tHorizontale;
	}
	
	// Retourne la taille verticale de la grille
	public int getTVerticale()
	{
		return tVerticale;
	}
}

