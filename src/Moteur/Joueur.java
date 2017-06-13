package Moteur;

public interface Joueur {
	public Case getCouleur();
	public String getNom();
	
	public boolean jouer(Jeu jeu);
}

