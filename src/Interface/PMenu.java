package Interface;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import IA.IAAlphaBeta;
import IA.IAMinMax;
import Moteur.Case;
import Moteur.Humain;
import Score.JoueurScore;
/**
 * 
 * @author mlaniess
 *Class Menu qui permet de g�rer le menu principale
 */
public class PMenu extends JPanel implements ActionListener{
	private JCoolButton bIAvIA = new JCoolButton("IA vs IA");
	private JCoolButton bJvIA = new JCoolButton("Joueur vs IA");
	private JCoolButton bJvJ = new JCoolButton("Joueur vs Joueur");
	private JCoolButton bScores = new JCoolButton("Scores");
	private JCoolButton bOptions = new JCoolButton("Options");
	private JLabel lTitre = new JLabel("PUISSANCE X");
	private Font buttonFont = new Font("Serif", Font.PLAIN, 26);
	private Font titreFont = new Font("ARIAL", Font.BOLD, 45);
	private final Application app;
	private int buttonWidth = 400;
	private int buttonHeight = 70;
	
	public PMenu(final Application application){
		this.app = application;
		setLayout(null);
		lTitre.setBounds(175, 20, 350, 100);
		lTitre.setHorizontalTextPosition(SwingConstants.CENTER);
		lTitre.setFont(titreFont);
		lTitre.setForeground(new Color(43, 0, 154));
		
		bIAvIA.setBounds(150, 150, buttonWidth, buttonHeight);
		bIAvIA.setFont(buttonFont); 
		bJvIA.setBounds(150, 220, buttonWidth, buttonHeight);
		bJvIA.setFont(buttonFont); 
		bJvJ.setBounds(150, 290, buttonWidth, buttonHeight);
		bJvJ.setFont(buttonFont); 
		bScores.setBounds(150, 360, buttonWidth, buttonHeight);
		bScores.setFont(buttonFont); 
		bOptions.setBounds(150, 550, buttonWidth, buttonHeight);
		bOptions.setFont(buttonFont);
		
		this.add(bIAvIA);
		this.add(bJvIA);
		this.add(bJvJ);
		this.add(bScores);
		this.add(bOptions);
		this.add(lTitre);
		
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
			IAAlphaBeta r1 = new IAAlphaBeta(Case.ROUGE);
			IAAlphaBeta r2 = new IAAlphaBeta(Case.JAUNE);
			app.montrerPlateau(r1, r2);
		}
		// Bouton Joueur vs IA
		else if (e.getSource() == bJvIA){
			Thread thread = new Thread(){
				public void run(){
					ArrayList<String> noms = new ArrayList<String>();
					for ( JoueurScore score : app.getGestionnaireScore().getListScores() ) {
					    noms.add(score.getNom());
					}
					String nom = app.montrerPseudo(1, noms);
					
					if (nom != null)
					{
						app.getGestionnaireScore().ajouterJoueur(nom);
						Humain j = new Humain(nom, Case.ROUGE);
					   	IAAlphaBeta r1 = new IAAlphaBeta(Case.JAUNE);
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
					ArrayList<String> noms = new ArrayList<String>();
					for ( JoueurScore score : app.getGestionnaireScore().getListScores() ) {
					    noms.add(score.getNom());
					}
					String nomJ1 = app.montrerPseudo(1, noms);
					
					if (nomJ1 != null)
					{
						noms.remove(nomJ1);
						app.getGestionnaireScore().ajouterJoueur(nomJ1);
						String nomJ2;
						
						// On ne veut pas que les 2 joueurs aient les mêmes pseudo
						do{
							nomJ2 = app.montrerPseudo(2, noms);
						}
						while (nomJ2 != null && nomJ2.equals(nomJ1));
							
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
