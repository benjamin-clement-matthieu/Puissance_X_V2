package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Score.JoueurScore;
/**
 * 
 * @author mlaniess
 * Class gerant le tableau des scores via un fichier : score
 */
public class PScores extends JPanel{

	final String TITRE = "PUISSANCE X";
	JLabel Titre = new JLabel("TOP SCORES");
	JCoolButton score1 = new JCoolButton("<");
	Font f = new Font("Serif", Font.PLAIN, 50);

	
	public PScores(final Application application){
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BorderLayout(20,20));
		DefaultTableModel model = new DefaultTableModel(); 
		Titre.setHorizontalAlignment(SwingConstants.CENTER);
		score1.setPreferredSize(new Dimension(50, 80));
		score1.setHorizontalAlignment(SwingConstants.CENTER);
		// Create a couple of columns 
		model.addColumn("Pseudo"); 
		model.addColumn("Total partie gagné"); 

		ArrayList<JoueurScore> scores = application.getGestionnaireScore().getListScores();
		for (JoueurScore score : scores)
		{
			model.addRow(new Object[]{score.getNom(), score.getScore()});
		}
		
		JTable table = new JTable(model);
		table.setEnabled(false);
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		
		score1.setFont(f); 
		this.add(score1, BorderLayout.SOUTH);
		this.add(Titre, BorderLayout.NORTH);
		score1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				application.montrerMenu();
			}
		});
	
}
}