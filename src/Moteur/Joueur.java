package Moteur;

public interface Joueur {
	public Case getCouleur();
	public String getNom();
	
	public void jouer(Jeu jeu);
}

