package Moteur;
import java.text.DecimalFormat;
import java.util.Random;

import Interface.Plateau;
import Interface.SoundEffect;
/**
 * Class Partie qui permet de g�rer une partie de Puissance X
 *
 */
public class Partie {
	private Joueur[] joueurs;
	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private int jCourrant;
	private boolean disposed;
	private boolean partieFinie;
	
	public Partie(Joueur j1, Joueur j2, Plateau plateau)
	{
		this.joueur1 = j1;
		this.joueur2 = j2;
		this.partieFinie = true;
		this.disposed = false;
		joueurs = new Joueur[2];
		joueurs[0] = joueur1;
		joueurs[1] = joueur2;
		
		this.plateau = plateau;
	}
	
	public void commencer()
	{
		partieFinie = false;
		jCourrant = new Random().nextInt(2);
		boolean partieGagne = false;
		while(!disposed)
		{			
			plateau.getPGrille().click.setClicked(false);
			plateau.setCouleurJC(joueurs[jCourrant].getCouleur());
			if (joueurs[jCourrant].jouer(plateau.getJeu()))
				SoundEffect.SON_PIECE.play();
			
			if (plateau.getJeu().verifierGagnant(joueurs[jCourrant]))
			{
				partieGagne = true;
				break;
			}
			
			
			if (plateau.getJeu().grillePleine())
				break;
			
			jCourrant++;
			jCourrant = jCourrant%2;
		}
		
		if (!disposed)
			plateau.partieFinie(partieGagne);
		
		partieFinie = true;
	}
	
	/**
	 * Permet d'arr�ter une partie en cours
	 */
	public void dispose()
	{
		disposed = true;
		while(!partieFinie)
		{
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		disposed = false;
	}
	
	public boolean estArrete()
	{
		return disposed;
	}
	
	public Joueur getJoueur1()
	{
		return joueur1;
	}
	
	public Joueur getJoueur2()
	{
		return joueur2;
	}
	
	/**
	 * Permet d'avoir l'autre joueur que celui pass� en param�tre
	 * @param joueur
	 * @return L'autre joueur que celui pass� en param�tre
	 */
	public Joueur getJoueurSuivant(Joueur joueur)
	{
		return (joueur == joueur1) ? joueur2 : joueur1;
	}
	
	/**
	 * Permet d'avoir le joueur courrant.
	 * @return Le joueur courrant
	 */
	public Joueur getJoueurCourrant()
	{
		if (partieFinie)
			return null;
		
		return joueurs[jCourrant];
	}
}
// x % a = x -(a * int(x/a))

