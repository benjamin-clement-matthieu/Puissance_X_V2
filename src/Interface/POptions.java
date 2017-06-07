package Interface;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Interface.Application;
import Moteur.Case;
import Moteur.Humain;
import Moteur.PuissanceMode;

public class POptions extends JPanel{

	final String TITRE = "PUISSANCE X";
	JButton option1 = new JButton("Choix Textures");
	JButton option2 = new JButton("Choix Difficulté");
	JButton option3 = new JButton("Activer Son");
	JButton option4 = new JButton("<");
	Font f = new Font("Serif", Font.PLAIN, 36);

	
	public POptions(final Application application){
		setLayout(null);
		option1.setBounds(100, 50, 500, 100);
		option1.setFont(f); 
		option2.setBounds(100, 250, 500, 100);
		option2.setFont(f); 
		option3.setBounds(100, 450, 500, 100);
		option3.setFont(f); 
		option4.setBounds(0, 570, 100, 100);
		option4.setFont(f); 
		this.add(option1);
		this.add(option2);
		this.add(option3);
		this.add(option4);
		option1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//
			}
		});
		option2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//
			}
		});
		option3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//
			}
		});
		option4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				application.montrerMenu();
			}
		});
		
	}
	
}

