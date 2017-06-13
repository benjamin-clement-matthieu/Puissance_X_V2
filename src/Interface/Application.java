package Interface;

import java.awt.Color;

import javax.swing.JFrame;

import Moteur.Joueur;
import Score.GestionnaireScore;
/**
 * Class Application qui est la class principale de gestion de l'application Puissance X
 * Elle permet d'afficher les diff�rents panels.
 */
public class Application {
	private final JFrame frame = new JFrame("Puissance X");
	private GestionnaireScore gs;
	public Application()
	{	    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
	    frame.setLocationByPlatform(true);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    gs = new GestionnaireScore("Scores.txt");

	    SoundEffect.init();
	    montrerMenu();
	}
	
	public void montrerMenu()
	{
	    frame.setContentPane(new PMenu(this));

	    frame.getContentPane().setBackground(Color.ORANGE);
	    frame.setVisible(true);

	}
	
	public String montrerPseudo(int numJoueur)
	{
		PPseudo pseudo = new PPseudo(this, numJoueur);
		frame.setSize(700, 700);
	    frame.setContentPane(pseudo);
	    frame.getContentPane().setBackground(Color.ORANGE);
	    frame.setVisible(true);
	    
	    return pseudo.getSelectedNom();
	}
	
	public void montrerScores()
	{
	    frame.setContentPane(new PScores(this));
	    frame.getContentPane().setBackground(Color.ORANGE);
	    frame.setVisible(true);
	}
	
	public void montrerOptions()
	{
	    frame.setContentPane(new POptions(this));
	    frame.getContentPane().setBackground(Color.ORANGE);
	    frame.setVisible(true);
	}
	
	public void montrerPlateau(Joueur j1, Joueur j2)
	{
		frame.setContentPane(new Plateau(j1, j2, this));
	    frame.getContentPane().setBackground(Color.ORANGE);
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
