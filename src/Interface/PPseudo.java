package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Score.JoueurScore;

public class PPseudo extends JPanel implements ActionListener {
	private String nomChoisie = null;
	private JCoolButton bAnnuler = new JCoolButton("Annuler");
	private JCoolButton bOk = new JCoolButton("Ok");
	private JComboBox box;
	private final Application app;
	
	
	public PPseudo(final Application app, int numJoueur, ArrayList<String> noms)
	{
		this.app = app;
		
		JLabel lNom = new JLabel("Sélectionner le nom du joueur " + numJoueur + " :");
		
		box = new JComboBox(noms.toArray());
		box.setEditable(true);
		
		bOk.addActionListener(this);
		bAnnuler.addActionListener(this);
		
		this.setLayout(null);
		
		box.setBounds(225, 300, 250, 30);
		this.add(box);
		
		lNom.setBounds(235, 250, 250, 20);
		this.add(lNom);
		
		bOk.setBounds(350, 330, 125, 40);
		this.add(bOk);
			
		bAnnuler.setBounds(225, 330, 125, 40);
		this.add(bAnnuler);
	}
	
	public String getSelectedNom()
	{
		synchronized(this)
		{
			try 
			{
				this.getRootPane().setDefaultButton(bOk);
				box.getEditor().selectAll();
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return nomChoisie;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		synchronized(this)
		{
			if (e.getSource() == bOk)
			{
				String tmp = (String)box.getSelectedItem();
				
				// Impossible de choisir comme pseudo Ordinateur
				if (tmp != null && tmp != "Ordinateur")
				{
					nomChoisie = (String)box.getSelectedItem();
					this.notify();
				}
			}
			else if (e.getSource() == bAnnuler)
			{
				this.notify();
				app.montrerMenu();
			}
		}
	}
	
	
}
