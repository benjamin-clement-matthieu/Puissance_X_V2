package Options;

import IA.IADifficulte;
import Moteur.PuissanceMode;

public class Valeurs {
	// Par défaut puissance 4
	private static PuissanceMode puissanceMode = PuissanceMode.PUISSANCE4;
	private static IADifficulte iaDifficulte = IADifficulte.MOYEN;
	private static boolean sonActive = true;
	
	public static void setPuissanceMode(PuissanceMode mode)
	{
		puissanceMode = mode;
	}
		
	public static PuissanceMode getPuissanceMode()
	{
		return puissanceMode;
	}
	
	public static void setIADifficulte(IADifficulte difficulte)
	{
		iaDifficulte = difficulte;
	}
		
	public static IADifficulte getIADifficulte()
	{
		return iaDifficulte;
	}
	
	public static void setSonActive(boolean valeur)
	{
		sonActive = valeur;
	}
		
	public static boolean getSonActive()
	{
		return sonActive;
	}
}
