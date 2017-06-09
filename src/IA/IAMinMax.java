package IA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Moteur.Case;
import Moteur.Jeu;
import Moteur.Joueur;
import Moteur.Ordinateur;

public class IAMinMax extends Ordinateur {
	
	private final int MINVAL=-100000;
	private final int MAXVAL=100000;
	private int profondeur = 1;
	private Joueur autreJoueur;
	
	public IAMinMax(Case couleur)
	{
		super(couleur);
		switch(Options.Valeurs.getIADifficulte())
		{
			case FACILE:
				profondeur = 1;
				break;
			case MOYEN:
				profondeur = 3;
				break;
			case DIFFICILE:
				profondeur = 6;
				break;
		}
	}
	
	@Override
	public void jouer(Jeu jeu) {
		autreJoueur = jeu.getPlateau().getPartie().getJoueurSuivant(this);
		int max = MINVAL;
		ArrayList<Integer> coupsPossibles = jeu.getCoupsPossible();
		ArrayList<Integer> coupsChoisies = new ArrayList<Integer>();
		
		for(int col : coupsPossibles)
		{
			jeu.jouerCoupTmp(col, this);
			int tmpMax = min(jeu, profondeur);
			
			if (tmpMax > max)
			{
				//System.out.println(tmpMax + ">" + max + " on choisit la colonne " + col);
				max = tmpMax;
				// On reset la liste des choix car celui ci est le meilleur
				coupsChoisies.clear();
				coupsChoisies.add(col);
			}
			else if (tmpMax == max)
			{
				//System.out.println(tmpMax + "=" + max + " on ajoute " + col + " à la liste des possibilités");
				coupsChoisies.add(col);
			}
			
			jeu.annulerCoup(col);
		}
		
		// On mélange la liste des choix pour que l'IA ne joue pas toujours la même chose
		Collections.shuffle(coupsChoisies);
		System.out.println(getCouleur() + " : " + coupsChoisies.get(0));
		jeu.jouerCoup(coupsChoisies.get(0), this);
	}
	
	private int min(Jeu jeu, int prof)
	{
		if(prof != 0 && !jeu.verifierGagnant(this) && !jeu.verifierGagnant(autreJoueur))
		{
			int valeur = MAXVAL;
			
			ArrayList<Integer> coupsPossibles = jeu.getCoupsPossible();
			for(int col : coupsPossibles)
			{
				jeu.jouerCoupTmp(col, autreJoueur);
				valeur = Math.min(valeur, this.max(jeu, prof - 1));
				
				jeu.annulerCoup(col);
			}
			
			return valeur;
		}
		else
		{
			return eval(jeu);
		}
	}
	
	private int max(Jeu jeu, int prof)
	{
		if(prof!= 0 && !jeu.verifierGagnant(this) && !jeu.verifierGagnant(autreJoueur))
		{
			int valeur = MINVAL;
			
			ArrayList<Integer> coupsPossibles = jeu.getCoupsPossible();
			for(int col : coupsPossibles)
			{
				jeu.jouerCoupTmp(col, this);
				valeur = Math.max(valeur, this.min(jeu, prof - 1));
				
				jeu.annulerCoup(col);
			}
			
			return valeur;
		}
		else
		{
			return eval(jeu);
		}
	}
	
	private int eval(Jeu jeu)
	{
		int valeur = 0;
		if (jeu.verifierGagnant(this))
			valeur = MAXVAL;
		
		if (jeu.verifierGagnant(autreJoueur))
			valeur = MINVAL;
		
		for (int i = 2; i <= jeu.getNbAligneMax(); i++)
		{
			int multiple = 1;
			switch (i)
			{
				case 3:
					multiple = 3;
					break;
				case 4:
					multiple = 9;
					break;
				case 5:
					multiple = 27;
					break;
				case 6:
					multiple = 81;
					break;
			}
			valeur += multiple * jeu.nombreAlignement(i, this);
		}
		
		return valeur;
	}
}
