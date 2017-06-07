package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ClickColEvent implements ActionListener {
	
	private int selectedCol = -1;
	private boolean clicked = false;
	
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton) arg0.getSource();
		selectedCol = Integer.valueOf(b.getName());
		clicked = true;
	}
	
	public boolean clicked()
	{
		return clicked;
	}
	
	public int getSelectedCol()
	{
		clicked = false;
		return selectedCol;
	}
}

