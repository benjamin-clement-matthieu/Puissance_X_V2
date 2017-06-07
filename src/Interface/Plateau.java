package Interface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Moteur.Case;
import Moteur.Jeu;
import Moteur.Joueur;
import Moteur.Partie;

public class Plateau extends JPanel{
	private PGrille pGrille;
	private Jeu jeu;
	private Partie partie;
	private int nbC = -1; // Nombre de colonnes
	private int nbL = -1; // Nombre de lignes
	private Thread partieThread = null;
	private Rond couleurJC; // Couleur du joueur courrant
	
	public Plateau(Joueur j1, Joueur j2, final Application appli)
	{
		initPlateau(j1, j2);
		initComposant(appli);
	}
	
	private void initComposant(final Application appli)
	{
		// Label pour les noms des joueurs
		JLabel lJoueur1 = new JLabel("Joueur 1 : " + partie.getJoueur1().getNom());
		JLabel lJoueur2 = new JLabel("Joueur 2 : " + partie.getJoueur2().getNom());
		JLabel jCourrant = new JLabel("Joueur en cours :");
		
		Rond couleurJ1 = new Rond(40);
		couleurJ1.setColor(partie.getJoueur1().getCouleur());
		
		Rond couleurJ2 = new Rond(40);
		couleurJ2.setColor(partie.getJoueur2().getCouleur());
		
		couleurJC = new Rond(40);
		
		JButton bMenu = new JButton("Menu");
		bMenu.setPreferredSize(new Dimension(80, 50));
		bMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				// On force la fin de la partie en cours
				partie.dispose();
				
				appli.montrerMenu();
			}
		});
		
		
		JButton bRecommencer = new JButton("Commencer");
		bRecommencer.setPreferredSize(new Dimension(80, 50));
		bRecommencer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				// Aucune partie n'a été commencé
				if (partieThread == null)
				{
					bRecommencer.setText("Recommencer");
					commencerPartie();
				}
				else
				{
					// On force la fin de la partie en cours
					partie.dispose();
					
					restart();
				}
				
			}
		});
		
		
		setLayout(null);
		
		// Pseudo joueur 1 (avec sa couleur)
		lJoueur1.setBounds(50, 35, 180, 20);
		this.add(lJoueur1);
		
		couleurJ1.setBounds(230, 20, 50, 50);
		this.add(couleurJ1);
		
		// Pseudo joueur 2 (avec sa couleur)
		lJoueur2.setBounds(50, 590, 200, 20);
		this.add(lJoueur2);
		
		couleurJ2.setBounds(230, 575, 50, 50);
		this.add(couleurJ2);
		
		// Grille
		pGrille.setBounds(50, 80, 490, 490);
		this.add(pGrille);
		
		// Bouton menu
		bMenu.setBounds(650, 100, 200, 50);
		this.add(bMenu);
		
		// Bouton recommencer
		bRecommencer.setBounds(650, 170, 200, 50);
		this.add(bRecommencer);
		
		// Joueur courrant
		jCourrant.setBounds(600, 250, 150, 50);
		this.add(jCourrant);
		
		// Couleur joueur courrant
		couleurJC.setBounds(750, 250, 200, 50);
		this.add(couleurJC);
	}
	
	private void initPlateau(Joueur j1, Joueur j2)
	{
		nbC = -1;
		nbL = -1;
		switch (Options.Valeurs.getPuissanceMode())
		{
			case PUISSANCE4:
				nbC = 7;
				nbL = 6;
				break;
			case PUISSANCE5:
				nbC = 8;
				nbL = 7;
				break;
			case PUISSANCE6:
				nbC = 9;
				nbL = 8;
				break;
		}
		
		pGrille = new PGrille(nbC, nbL);
		jeu = new Jeu(this, nbC, nbL);
		partie = new Partie(j1, j2, this);
	
	}
	
	public void setCouleurJC(Case couleur)
	{
		couleurJC.setColor(couleur);
	}
	
	private void restart()
	{
		jeu = new Jeu(this, nbC, nbL);
		pGrille.actualise(jeu.getGrille());
		
		commencerPartie();
	}
	
	public PGrille getPGrille()
	{
		return pGrille;
	}
	
	public Jeu getJeu()
	{
		return jeu;
	}
	
	public Partie getPartie()
	{
		return partie;
	}
	
	public void commencerPartie()
	{
		pGrille.enableButtons(true);
		partieThread = new Thread() {
			public void run()
			{
				partie.commencer();
			}
		};
		
		partieThread.start();
	}
}

