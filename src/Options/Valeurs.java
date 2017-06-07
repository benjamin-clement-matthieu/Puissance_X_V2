package Options;

import Moteur.PuissanceMode;

public class Valeurs {
	// Par défaut puissance 4
	private static PuissanceMode puissanceMode = PuissanceMode.PUISSANCE4;
		
	public static void setPuissanceMode(PuissanceMode mode)
	{
		puissanceMode = mode;
	}
		
	public static PuissanceMode getPuissanceMode()
	{
		return puissanceMode;
	}
}
