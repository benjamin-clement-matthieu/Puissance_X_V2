package Interface;

import javax.swing.JFrame;

import IA.IARandom;
import Moteur.Case;
import Moteur.Humain;
import Moteur.Joueur;
import Moteur.PuissanceMode;

public class Application {
	private final JFrame frame = new JFrame("Puissance X");
	
	public Application()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(900, 700);
	    frame.setLocationByPlatform(true);
	    frame.setLocationRelativeTo(null);
	    
	    montrerMenu();
	}
	
	public void montrerMenu()
	{
		frame.setSize(700, 700);
	    frame.setContentPane(new PMenu(this));
	    frame.setVisible(true);
	}
	
	public void montrerPseudo()
	{
		
	}
	
	public void montrerScores()
	{
		frame.setSize(700, 700);
	    frame.setContentPane(new PScores(this));
	    frame.setVisible(true);
	}
	
	public void montrerOptions()
	{
		frame.setSize(700, 700);
	    frame.setContentPane(new POptions(this));
	    frame.setVisible(true);
	}
	
	public void montrerPlateau(Joueur j1, Joueur j2)
	{
		frame.setSize(900, 700);
		frame.setContentPane(new Plateau(j1, j2, this));
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
    	Interface.Application f = new Interface.Application();
    }
}
