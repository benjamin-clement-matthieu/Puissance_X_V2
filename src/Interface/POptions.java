package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import IA.IADifficulte;
import Moteur.PuissanceMode;
/**
 * 
 * @author mlaniess
 *Class qui gere le Panel "Options" 
 */
public class POptions extends JPanel implements ActionListener {
	
	private JCoolButton back = new JCoolButton("<");
	private JRadioButton jr1 = new JRadioButton("Facile");
	private JRadioButton jr2 = new JRadioButton("Moyen");
	private JRadioButton jr3 = new JRadioButton("Difficile");
	private JRadioButton jrP4 = new JRadioButton("Puissance 4");
	private JRadioButton jrP5 = new JRadioButton("Puissance 5");
	private JRadioButton jrP6 = new JRadioButton("Puissance 6");
	private JLabel lDifficulte = new JLabel("Difficulté :");
	private JLabel lSon = new JLabel("Son :");
	private JLabel lPuissanceMode = new JLabel("Puissance mode :");
	private ButtonGroup bgDifficulte = new ButtonGroup();
	private ButtonGroup bgPuissanceMode = new ButtonGroup();
	private JCheckBox check1 = new JCheckBox("Son Activé");
	private Font f = new Font("Serif", Font.PLAIN, 36);
	private final Application app;
	
	public POptions(final Application application) {
		this.app = application;
		
		switch(Options.Valeurs.getIADifficulte())
		{
			case FACILE:
				jr1.setSelected(true);
				break;
			case MOYEN:
				jr2.setSelected(true);
				break;
			case DIFFICILE:
				jr3.setSelected(true);
				break;
		}
		
		switch(Options.Valeurs.getPuissanceMode())
		{
			case PUISSANCE4:
				jrP4.setSelected(true);
				break;
			case PUISSANCE5:
				jrP5.setSelected(true);
				break;
			case PUISSANCE6:
				jrP6.setSelected(true);
				break;
		}
		
		check1.setSelected(Options.Valeurs.getSonActive());
		lDifficulte.setForeground(new Color(43, 0, 154));
		lPuissanceMode.setForeground(new Color(43, 0, 154));
		lSon.setForeground(new Color(43, 0, 154));
		back.addActionListener(this);
		jr1.addActionListener(this);
		jr2.addActionListener(this);
		jr3.addActionListener(this);
		jrP4.addActionListener(this);
		jrP5.addActionListener(this);
		jrP6.addActionListener(this);
		check1.addActionListener(this);
		
		back.setBounds(20, 580, 660, 75);
		back.setFont(f);


		jr1.setBounds(100, 100, 100, 100);
		jr2.setBounds(300, 100, 100, 100);
		jr3.setBounds(500, 100, 100, 100);
		jrP4.setBounds(100, 300, 200, 100);
		jrP5.setBounds(300, 300, 200, 100);
		jrP6.setBounds(500, 300, 200, 100);
		check1.setBounds(300, 475, 100, 100);
		lDifficulte.setBounds(250, 25, 200, 100);
		lDifficulte.setFont(f);
		lPuissanceMode.setBounds(175, 200, 350, 100);
		lPuissanceMode.setFont(f);
		jr1.setBackground(new Color(49, 140, 231));
		jr2.setBackground(new Color(49, 140, 231));
		jr3.setBackground(new Color(49, 140, 231));
		jrP4.setBackground(new Color(49, 140, 231));
		jrP5.setBackground(new Color(49, 140, 231));
		jrP6.setBackground(new Color(49, 140, 231));
		lSon.setBounds(300, 400, 350, 100);
		lSon.setFont(f);
		check1.setBackground(new Color(49, 140, 231));
		setLayout(null);
		this.add(check1);
		this.add(back);
		this.add(jr1);
		this.add(jr2);
		this.add(jr3);
		this.add(lDifficulte);
		this.add(jrP4);
		this.add(jrP5);
		this.add(jrP6);
		this.add(lPuissanceMode);
		this.add(lSon);
		bgDifficulte.add(jr1);
		bgDifficulte.add(jr2);
		bgDifficulte.add(jr3);
		
		bgPuissanceMode.add(jrP4);
		bgPuissanceMode.add(jrP5);
		bgPuissanceMode.add(jrP6);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back)
		{
			app.montrerMenu();
		}
		else if (e.getSource() == jr1)
		{
			Options.Valeurs.setIADifficulte(IADifficulte.FACILE);
		}
		else if (e.getSource() == jr2)
		{
			Options.Valeurs.setIADifficulte(IADifficulte.MOYEN);
		}
		else if (e.getSource() == jr3)
		{
			Options.Valeurs.setIADifficulte(IADifficulte.DIFFICILE);
		}
		else if (e.getSource() == jrP4)
		{
			Options.Valeurs.setPuissanceMode(PuissanceMode.PUISSANCE4);
		}
		else if (e.getSource() == jrP5)
		{
			Options.Valeurs.setPuissanceMode(PuissanceMode.PUISSANCE5);
		}
		else if (e.getSource() == jrP6)
		{
			Options.Valeurs.setPuissanceMode(PuissanceMode.PUISSANCE6);
		}
		else if (e.getSource() == check1)
		{
			Options.Valeurs.setSonActive(check1.isSelected());
		}
	}

}

