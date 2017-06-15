package Interface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Moteur.Case;
/**
 * 
 * @author mlaniess
 *Class qui crée les cases du puissance X
 */
public class Rond extends JPanel {
	private Color currentColor = Color.WHITE;
	private int dimension;
	private int colonne;
	
	public Rond(int dim, int colonne)
	{
		this.dimension = dim;
		this.colonne = colonne;
	}
	
	public void paintComponent(Graphics g) {
		
	    g.setColor(currentColor);
		g.fillOval(2, 2, dimension, dimension);
	}
	
	public int getColonne()
	{
		return colonne;
	}
	
	public void setColor(Case color)
	{
		switch(color)
		{
			case ROUGE:
				currentColor = Color.RED;
				break;
			case JAUNE:
				currentColor = Color.YELLOW;
				break;
			case VIDE:
				currentColor = Color.WHITE;
				break;
		}
		this.repaint();
	}
}

