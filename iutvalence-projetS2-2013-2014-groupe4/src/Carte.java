
public class Carte extends Personnage
{
	/**
	 * Points d'attaque de la carte
	 */
	private int pointsDAttaque;
	/**
	 * L'etat de la carte
	 */
	private boolean estInactive;
	/**
	 * Cout en mana de la carte
	 */
	private int coutEnMana;
	/**
	 * Description de la carte
	 */
	private String description;
	
	/**
	 * Permet de creer une nouvelle carte
	 */
	public Carte()
	{
		
	}
	
	/**
	 * Permet d'utiliser la carte
	 */
	
	public void utiliserCarte()
	{
		
	}
	
	/**
	 * Test si la carte est en mode Inactif
	 * @return True si la carte est inactive
	 */
	public boolean estInactif()
	{
		return this.estInactive;
	}
	
	/**
	 * Permet d'obtenir les points d'attaque de la carte
	 * @return les points d'attaques
	 */
	public int obtenirDegatsDAttaque()
	{
		return this.pointsDAttaque;
	}
	
	/**
	 * Change l'etat de la carte en Inactive
	 */
	public void modeInactive()
	{
		this.estInactive=true;
	}
	
	/**
	 * Pose la carte sur le plateau
	 */
	public void poserCarte()
	{
		
	}
	
	/**
	 * Inflige des degats a un personnage
	 * @param personnage : Le personnage cible par l'attaque
	 */
	public void infligerDegats(Personnage personnage)
	{
		
	}
}