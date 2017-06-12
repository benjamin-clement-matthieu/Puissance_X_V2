package Score;

import java.io.Serializable;

public class JoueurScore implements Serializable {
	private int score;
	private String nom;
	
	public JoueurScore(String nom, int score)
	{
		this.nom = nom;
		this.score = score;
	}
	
	public void incrementeScore()
	{
		this.score++;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public String getNom()
	{
		return nom;
	}
}
