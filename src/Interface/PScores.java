package Interface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Interface.Application;

public class PScores extends JPanel{

	final String TITRE = "PUISSANCE X";
	JLabel Titre = new JLabel("TOP SCORES");
	JButton score1 = new JButton("<");
	Font f = new Font("Serif", Font.PLAIN, 50);

	
	public PScores(final Application application){
		setLayout(null);
		score1.setBounds(0, 570, 100, 100);
		Titre.setBounds(300, 0, 100, 100);
		score1.setFont(f); 
		this.add(score1);
		this.add(Titre);
		score1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				application.montrerMenu();
			}
		});
	
}
}