package Interface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Moteur.Case;
import Moteur.Humain;
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
	private final Application app;
	private Rond couleurJC; // Couleur du joueur courrant
	private JLabel jCourrant;
	private JLabel jGagnant;
	private JLabel lJoueur1;
	private JLabel lJoueur2;
	
	public Plateau(Joueur j1, Joueur j2, final Application appli)
	{
		app = appli;
		initPlateau(j1, j2);
		initComposant();
	}
	
	private void initComposant()
	{
		String nomJ1 = partie.getJoueur1().getNom();
		String nomJ2 = partie.getJoueur2().getNom();
		// Label pour les noms des joueurs
		lJoueur1 = new JLabel("Joueur 1 : " + nomJ1);
		lJoueur2 = new JLabel("Joueur 2 : " + nomJ2);
		
		if (partie.getJoueur1() instanceof Humain)
			lJoueur1.setText("Joueur 1 : " + nomJ1 + "  " + app.getGestionnaireScore().getJoueurScore(nomJ1));
		
		if (partie.getJoueur2() instanceof Humain)
			lJoueur2.setText("Joueur 2 : " + nomJ2 + "  " + app.getGestionnaireScore().getJoueurScore(nomJ2));
		
		
		Rond couleurJ1 = new Rond(40);
		couleurJ1.setColor(partie.getJoueur1().getCouleur());
		
		Rond couleurJ2 = new Rond(40);
		couleurJ2.setColor(partie.getJoueur2().getCouleur());
		
		couleurJC = new Rond(40);
		couleurJC.setBounds(750, 250, 200, 50);
				
		// Label du joueur courrant
		jCourrant = new JLabel("Joueur en cours :");
		jCourrant.setBounds(600, 250, 150, 50);
		
		// Label du gagnant
		jGagnant = new JLabel();
		jGagnant.setBounds(600, 350, 300, 50);
		
		JButton bMenu = new JButton("Menu");
		bMenu.setPreferredSize(new Dimension(80, 50));
		bMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				// On force la fin de la partie en cours
				partie.dispose();
				
				app.montrerMenu();
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
					
					recommencer();
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
	
	public void recommencer()
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
		this.add(jCourrant);
		this.add(couleurJC);
		this.remove(jGagnant);
		this.repaint();
		
		// S'il y a au moins un humain, on active les boutons
		if (partie.getJoueur1() instanceof Humain || partie.getJoueur2() instanceof Humain)
			pGrille.enableButtons(true);
		
		partieThread = new Thread() {
			public void run()
			{
				partie.commencer();
			}
		};
		
		partieThread.start();
		
	}
	
	public void partieFinie()
	{
		String nomJ1 = partie.getJoueur1().getNom();
		String nomJ2 = partie.getJoueur2().getNom();
		
		if (jeu.grillePleine())
			jGagnant.setText("La grille est pleine ! Aucun gagnant.");
		else
		{
			jGagnant.setText("Le joueur " + partie.getJoueurCourrant().getNom() + " (" + partie.getJoueurCourrant().getCouleur() + ") a gagné !");
			if (partie.getJoueur1() instanceof Humain)
				lJoueur1.setText("Joueur 1 : " + nomJ1 + "  " + app.getGestionnaireScore().getJoueurScore(nomJ1));
			
			if (partie.getJoueur2() instanceof Humain)
				lJoueur2.setText("Joueur 2 : " + nomJ2 + "  " + app.getGestionnaireScore().getJoueurScore(nomJ2));
		}
			
		
		this.remove(jCourrant);
		this.remove(couleurJC);
		this.add(jGagnant);
		pGrille.enableButtons(false);
		
		this.repaint();
	}
}

