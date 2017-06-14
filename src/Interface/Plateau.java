package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Moteur.Case;
import Moteur.Humain;
import Moteur.Jeu;
import Moteur.Joueur;
import Moteur.Partie;

public class Plateau extends JPanel {
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
	private Font font = new Font("ARIAL", Font.BOLD, 22);
	
	public Plateau(Joueur j1, Joueur j2, final Application appli) {
		app = appli;
		initPlateau(j1, j2);
		initComposant();
	}

	private void initComposant() {
		String nomJ1 = partie.getJoueur1().getNom();
		String nomJ2 = partie.getJoueur2().getNom();
		// Label pour les noms des joueurs
		lJoueur1 = new JLabel("Joueur 1 : " + nomJ1);
		lJoueur2 = new JLabel("Joueur 2 : " + nomJ2);

		if (partie.getJoueur1() instanceof Humain)
			lJoueur1.setText("Joueur 1 : " + nomJ1 + "  " + app.getGestionnaireScore().getJoueurScore(nomJ1));

		if (partie.getJoueur2() instanceof Humain)
			lJoueur2.setText("Joueur 2 : " + nomJ2 + "  " + app.getGestionnaireScore().getJoueurScore(nomJ2));

		Rond couleurJ1 = new Rond(40, -1);
		couleurJ1.setColor(partie.getJoueur1().getCouleur());

		Rond couleurJ2 = new Rond(40, -1);
		couleurJ2.setColor(partie.getJoueur2().getCouleur());

		couleurJC = new Rond(40, -1);
		couleurJC.setBounds(250, 20, 200, 50);

		// Label du joueur courrant
		jCourrant = new JLabel("Joueur en cours :");
		jCourrant.setForeground(new Color(43, 0, 154));
		jCourrant.setBounds(110, 20, 150, 50);

		// Label du gagnant
		jGagnant = new JLabel();
		jGagnant.setFont(font);
		jGagnant.setForeground(new Color(43, 0, 154));
		jGagnant.setHorizontalAlignment(SwingConstants.CENTER);
		jGagnant.setBounds(0, 20, 700, 50);

		JCoolButton bMenu = new JCoolButton("Menu");
		bMenu.setPreferredSize(new Dimension(80, 50));
		bMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// On force la fin de la partie en cours
				partie.dispose();

				app.montrerMenu();
			}
		});

		JCoolButton bRecommencer = new JCoolButton("Commencer");
		bRecommencer.setPreferredSize(new Dimension(80, 50));
		bRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Aucune partie n'a été commencé
				if (partieThread == null) {
					bRecommencer.setText("Recommencer");
					commencerPartie();
				} else {
					// On force la fin de la partie en cours
					partie.dispose();

					recommencer();
				}

			}
		});

		setLayout(null);

		// Pseudo joueur 1 (avec sa couleur)
		lJoueur1.setBounds(120, 520, 200, 20);
		lJoueur1.setForeground(new Color(43, 0, 154));
		this.add(lJoueur1);

		couleurJ1.setBounds(300, 505, 50, 50);
		this.add(couleurJ1);

		// Pseudo joueur 2 (avec sa couleur)
		lJoueur2.setBounds(420, 520, 200, 20);
		lJoueur2.setForeground(new Color(43, 0, 154));
		this.add(lJoueur2);

		couleurJ2.setBounds(350, 505, 50, 50);
		this.add(couleurJ2);

		// Grille
		pGrille.setBounds(100, 80, 490, 420);
		this.add(pGrille);

		// Bouton menu
		bMenu.setBounds(360, 570, 200, 50);
		this.add(bMenu);

		// Bouton recommencer
		bRecommencer.setBounds(150, 570, 200, 50);
		this.add(bRecommencer);
	}

	private void initPlateau(Joueur j1, Joueur j2) {
		nbC = -1;
		nbL = -1;
		switch (Options.Valeurs.getPuissanceMode()) {
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

	/**
	 * Permet de changer la couleur du joueur courrant.
	 * 
	 * @param couleur
	 *            La couleur du joueur courrant
	 */
	public void setCouleurJC(Case couleur) {
		couleurJC.setColor(couleur);
	}

	public void recommencer() {
		jeu = new Jeu(this, nbC, nbL);
		pGrille.actualise(jeu.getGrille());

		commencerPartie();
	}

	public PGrille getPGrille() {
		return pGrille;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public Partie getPartie() {
		return partie;
	}

	public void commencerPartie() {
		this.add(jCourrant);
		this.add(couleurJC);
		this.remove(jGagnant);
		this.repaint();

		partieThread = new Thread() {
			public void run() {
				partie.commencer();
			}
		};

		partieThread.start();

	}

	/**
	 * Permet d'actualiser et d'afficher les informations de fin de partie
	 */
	public void partieFinie(boolean partieGagne) {
		String nomJ1 = partie.getJoueur1().getNom();
		String nomJ2 = partie.getJoueur2().getNom();

		if (jeu.grillePleine() && !partieGagne)
			jGagnant.setText("La grille est pleine ! Aucun gagnant.");
		else {
			jGagnant.setText("Le joueur " + partie.getJoueurCourrant().getNom() + " ("
					+ partie.getJoueurCourrant().getCouleur() + ") a gagné !");
			if (app.getGestionnaireScore().incrementeJoueur(partie.getJoueurCourrant().getNom())) {
				SoundEffect.VICTOIRE.play();
				if (partie.getJoueur1() instanceof Humain)
					lJoueur1.setText("Joueur 1 : " + nomJ1 + "  " + app.getGestionnaireScore().getJoueurScore(nomJ1));
					
				if (partie.getJoueur2() instanceof Humain)
					lJoueur2.setText("Joueur 2 : " + nomJ2 + "  " + app.getGestionnaireScore().getJoueurScore(nomJ2));
			}
		}

		this.remove(jCourrant);
		this.remove(couleurJC);
		this.add(jGagnant);

		this.repaint();
	}
}
