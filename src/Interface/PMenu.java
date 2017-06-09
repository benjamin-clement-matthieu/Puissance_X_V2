package Interface;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import IA.IAMinMax;
import IA.IARandom;
import Moteur.Case;
import Moteur.Humain;
import Score.JoueurScore;

public class PMenu extends JPanel implements ActionListener{
	final String TITRE = "PUISSANCE X";
	JButton bIAvIA = new JButton("IA vs IA");
	JButton bJvIA = new JButton("Joueur vs IA");
	JButton bJvJ = new JButton("Joueur vs Joueur");
	JButton bScores = new JButton("Scores");
	JButton bOptions = new JButton("Options");
	Font f = new Font("Serif", Font.PLAIN, 36);
	private final Application app;
	
	public PMenu(final Application application){
		this.app = application;
		setLayout(null);
		bIAvIA.setBounds(100, 50, 500, 100);
		bIAvIA.setFont(f); 
		bJvIA.setBounds(100, 150, 500, 100);
		bJvIA.setFont(f); 
		bJvJ.setBounds(100, 250, 500, 100);
		bJvJ.setFont(f); 
		bScores.setBounds(100, 350, 500, 100);
		bScores.setFont(f); 
		bOptions.setBounds(100, 550, 500, 100);
		bOptions.setFont(f);
		
		this.add(bIAvIA);
		this.add(bJvIA);
		this.add(bJvJ);
		this.add(bScores);
		this.add(bOptions);
		
		bIAvIA.addActionListener(this);
		bJvIA.addActionListener(this);
		bJvJ.addActionListener(this);
		bScores.addActionListener(this);
		bOptions.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Bouton IA vs IA
		if (e.getSource() == bIAvIA)
		{
			IAMinMax r1 = new IAMinMax(Case.ROUGE);
	    	IAMinMax r2 = new IAMinMax(Case.JAUNE);
			app.montrerPlateau(r1, r2);
		}
		// Bouton Joueur vs IA
		else if (e.getSource() == bJvIA){
			Thread thread = new Thread(){
				public void run(){
					String nom = app.montrerPseudo(1);
					
					if (nom != null)
					{
						app.getGestionnaireScore().ajouterJoueur(nom);
						Humain j = new Humain(nom, Case.ROUGE);
					   	IAMinMax r1 = new IAMinMax(Case.JAUNE);
						app.montrerPlateau(j, r1);
					}
				}
			};
			thread.start();
		}
		// Bouton Joueur vs Joueur
		else if (e.getSource() == bJvJ){
			Thread thread = new Thread(){
				public void run(){
					String nomJ1 = app.montrerPseudo(1);
					
					if (nomJ1 != null)
					{
						app.getGestionnaireScore().ajouterJoueur(nomJ1);
						String nomJ2;
						
						// On ne veut pas que les 2 joueurs aient les mêmes pseudo
						do{
							nomJ2 = app.montrerPseudo(2);
						}
						while (nomJ2 == nomJ1);
							
						if (nomJ2 != null)
						{
							app.getGestionnaireScore().ajouterJoueur(nomJ2);
							Humain j1 = new Humain(nomJ1, Case.ROUGE);
						   	Humain j2 = new Humain(nomJ2, Case.JAUNE);
							app.montrerPlateau(j1, j2);
						}
					}
				}
			};
			thread.start();
		}
		// Bouton Scores
		else if (e.getSource() == bScores){
			app.montrerScores();
		}
		// Bouton Options
		else if (e.getSource() == bOptions){
			app.montrerOptions();
		}
	}
	
}
