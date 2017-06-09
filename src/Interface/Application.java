package Interface;

import javax.swing.JFrame;

import Moteur.Joueur;
import Moteur.PuissanceMode;
import Score.GestionnaireScore;

public class Application {
	private final JFrame frame = new JFrame("Puissance X");
	private GestionnaireScore gs;
	public Application()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
	    frame.setLocationByPlatform(true);
	    frame.setLocationRelativeTo(null);
	    gs = new GestionnaireScore("Scores.txt");
	    
	    montrerMenu();
	}
	
	public void montrerMenu()
	{
		frame.setSize(700, 700);
	    frame.setContentPane(new PMenu(this));
	    frame.setVisible(true);
	}
	
	public String montrerPseudo(int numJoueur)
	{
		PPseudo pseudo = new PPseudo(this, numJoueur);
		frame.setSize(700, 700);
	    frame.setContentPane(pseudo);
	    frame.setVisible(true);
	    
	    return pseudo.getSelectedNom();
	}
	
	public void montrerScores()
	{
		frame.setSize(700, 700);
	    frame.setContentPane(new PScores(this));
	    frame.setVisible(true);
	}
	
	public void montrerOptions()
	{
	    frame.setContentPane(new POptions(this));
	    frame.setVisible(true);
	}
	
	public void montrerPlateau(Joueur j1, Joueur j2)
	{
		frame.setSize(900, 700);
		frame.setContentPane(new Plateau(j1, j2, this));
		frame.setVisible(true);
	}
	
	public GestionnaireScore getGestionnaireScore()
	{
		return gs;
	}
	
	public static void main(String[] args) {
    	Interface.Application f = new Interface.Application();
    }
}
