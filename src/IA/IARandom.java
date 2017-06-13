package IA;
import java.util.ArrayList;
import java.util.Random;

import Moteur.Case;
import Moteur.Jeu;
import Moteur.Ordinateur;

public class IARandom extends Ordinateur {
	
	public IARandom(Case couleur)
	{
		super(couleur);
	}
	
	@Override
	public boolean jouer(Jeu jeu) {
		ArrayList<Integer> coups = jeu.getCoupsPossible();
		int random = new Random().nextInt(coups.size());
		
		return jeu.jouerCoup(coups.get(random), this);
	}
	
	
}


