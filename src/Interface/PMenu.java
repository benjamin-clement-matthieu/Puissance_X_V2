package Interface;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import IA.IARandom;
import Moteur.Case;
import Moteur.Humain;

public class PMenu extends JPanel {
	final String TITRE = "PUISSANCE X";
	JButton bIAvIA = new JButton("IA vs IA");
	JButton bJvIA = new JButton("Joueur vs IA");
	JButton bJvJ = new JButton("Joueur vs Joueur");
	JButton bScores = new JButton("Scores");
	JButton bOptions = new JButton("Options");
	Font f = new Font("Serif", Font.PLAIN, 36); // par exemple

	
	public PMenu(final Application application){
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
		
		bIAvIA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   	IARandom r1 = new IARandom(Case.ROUGE);
		    	IARandom r2 = new IARandom(Case.JAUNE);
				application.montrerPlateau(r1, r2);
			}
		});
		bJvIA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   	Humain Benjamin = new Humain("Benjamin", Case.ROUGE);
			   	IARandom r1 = new IARandom(Case.JAUNE);
				application.montrerPlateau(Benjamin, r1);
			}
		});
		bJvJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   	Humain Benjamin = new Humain("Benjamin", Case.ROUGE);
		    	Humain Matthieu = new Humain("Matthieu", Case.JAUNE);
				application.montrerPlateau(Benjamin, Matthieu);
			}
		});
		bScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				application.montrerScores();
			}
		});
		bOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				application.montrerOptions();
			}
		});
		
		
	}
	
}
