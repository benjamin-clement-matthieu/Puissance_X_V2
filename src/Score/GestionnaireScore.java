package Score;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionnaireScore {
	private String nomFichier;
	private ArrayList<JoueurScore> listScores;
	
	public GestionnaireScore(String nomFichier)
	{
		this.nomFichier = nomFichier;
		listScores = ouvrirScore();
	}
	
	public ArrayList<JoueurScore> getListScores()
	{
		return listScores;
	}
	
	public int getJoueurScore(String nom)
	{
		int index = getIndex(nom);
		
		if (index != -1)
			return listScores.get(index).getScore();
		
		return -1;
	}
	
	public boolean incrementeJoueur(String nom)
	{
		int index = getIndex(nom);
		
		if (index != -1)
		{
			listScores.get(index).incrementeScore();
			enregistrerScore();
			return true;
		}
		
		return false;
	}
	
	public boolean ajouterJoueur(String joueur)
	{
		for (int i = 0; i < listScores.size(); i++)
		{
			// Si le joueur existe déjà
			if (listScores.get(i).getNom().equals(joueur))
			{
				return false;
			}
		}
		
		listScores.add(new JoueurScore(joueur, 0));
		enregistrerScore();
		return true;
	}
	
	public boolean enleverJoueur(String nom)
	{
		if (listScores.remove(nom))
		{
			enregistrerScore();
			return true;
		}
		
		return false;
	}
	
	private void enregistrerScore()
	{
		try {
			FileOutputStream fichier = new FileOutputStream(nomFichier);
			ObjectOutputStream stream = new ObjectOutputStream(fichier);
			stream.writeObject(listScores);
			stream.close();
			fichier.close();
		 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<JoueurScore> ouvrirScore()
	{
		ArrayList<JoueurScore> scores = new ArrayList<JoueurScore>();
		try {
			FileInputStream fichier = new FileInputStream(nomFichier);
			ObjectInputStream stream = new ObjectInputStream(fichier);
			scores = (ArrayList<JoueurScore>)stream.readObject();
			stream.close();
			fichier.close();
			
		} catch (FileNotFoundException e) {
			return scores;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		return scores;
	}
	
	private int getIndex(String nom)
	{
		int index = -1;
		for (int i = 0; i < listScores.size(); i++)
		{
			if (listScores.get(i).getNom().equals(nom))
			{
				index = i;
				break;
			}
		}
		
		return index;
	}
}
