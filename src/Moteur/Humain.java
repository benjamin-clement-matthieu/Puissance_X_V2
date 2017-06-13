package Moteur;

public class Humain implements Joueur {
	private String nom;
	private Case couleur;

	public Humain(String nom, Case couleur) {
		this.nom = nom;
		this.couleur = couleur;
	}

	@Override
	public boolean jouer(Jeu jeu) {
		try {

			int selectedCol;
			do {
				// On attend que le joueur click ou on regarde se la partie a été annulé
				while (Interface.PGrille.click.clicked() == false && !jeu.getPlateau().getPartie().estArrete()) {
					Thread.sleep(100);
				}
				
				if (jeu.getPlateau().getPartie().estArrete())
					return false;
				
				selectedCol = Interface.PGrille.click.getSelectedCol();

			} while (!jeu.jouerCoup(selectedCol, this)); // Tant que le coup n'est pas possible

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public Case getCouleur() {
		return couleur;
	}

	@Override
	public String getNom() {
		return nom;
	}

}

