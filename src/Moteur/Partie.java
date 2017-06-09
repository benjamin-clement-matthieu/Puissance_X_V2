package Moteur;
import java.text.DecimalFormat;
import java.util.Random;

import Interface.Plateau;

public class Partie {
	private Joueur[] joueurs;
	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private int jCourrant;
	private boolean disposed;
	private boolean partieFinie;
	
	private int nbWinJ1 = 0;
	private int nbPartie = 0;
	
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
		nbPartie++;
		while(!disposed)
		{
			if (joueurs[jCourrant] instanceof Humain)
				 plateau.getPGrille().enableButtons(true);
			else
				plateau.getPGrille().enableButtons(false);
			
			plateau.setCouleurJC(joueurs[jCourrant].getCouleur());
			joueurs[jCourrant].jouer(plateau.getJeu());
			
			if (plateau.getJeu().verifierGagnant(joueurs[jCourrant]))
			{
				if (joueurs[jCourrant] == joueur1)
				{
					nbWinJ1++;
				}
				//System.out.println("Le joueur " + joueurs[jCourrant].getNom() + " : " + joueurs[jCourrant].getCouleur() + " a gagné");
				break;
			}
			
			
			if (plateau.getJeu().grillePleine())
			{
				System.out.println("La grille est pleine !");
				break;
			}
			jCourrant++;
			jCourrant = jCourrant%2;
		}
		
		plateau.partieFinie();
		partieFinie = true;
		
		/*if (nbPartie < 1000)
			plateau.recommencer();
		else
		{
			System.out.println(pourcentage(nbWinJ1, nbPartie));
		} */
	}
	
	private String pourcentage(int a,int b){
        double c = new Double(b);
 
        double resultat = a/c;
        double resultatFinal = resultat*100;
 
 
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(resultatFinal) + " %";
    }
	
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
	
	public Joueur getJoueurSuivant(Joueur joueur)
	{
		return (joueur == joueur1) ? joueur2 : joueur1;
	}
	
	// Retourne null si il n'y a pas de partie en cours
	public Joueur getJoueurCourrant()
	{
		if (partieFinie)
			return null;
		
		return joueurs[jCourrant];
	}
}
// x % a = x -(a * int(x/a))

