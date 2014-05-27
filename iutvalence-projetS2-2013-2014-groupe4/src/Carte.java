/**
 * 
 * Definition d'une carte de jeu
 */
public class Carte extends Personnage {
	/**
	 * L'etat de la carte
	 */
	private boolean estInactive;

	/**
	 * Effet de la carte
	 */
	public Effet effet;
	/**
	 * Cout en mana de la carte
	 */
	private int coutEnMana;
	/**
	 * Description de la carte
	 */
	@SuppressWarnings("unused")
	private String description;

	/**
	 * Permet de creer une nouvelle carte
	 * 
	 * @param nom
	 *            nom de la carte
	 * @param attaque
	 *            point d'attaque de la carte
	 * @param vie
	 *            point de vie de la carte
	 * @param effet
	 *            effet(s) de la carte
	 * @param mana
	 *            cout en mana de la carte
	 * @param description
	 *            description de la carte
	 */
	public Carte(String nom, int attaque, int vie, Effet effet, int mana,
			String description) {
		this.nom = nom;
		this.pointsDeVieMax = vie;
		this.pointDeVie=this.pointsDeVieMax;
		this.pointsDAttaque = attaque;
		this.effet = effet;
		this.coutEnMana = mana;
		this.estInactive=true;
		this.description = description;
	}



	/**
	 * Test si la carte est en mode Inactif
	 * 
	 * @return True si la carte est inactive
	 */
	public boolean estInactif() {
		return this.estInactive;
	}

	/**
	 * Permet d'obtenir les points d'attaque de la carte
	 * 
	 * @return les points d'attaques
	 */
	public int obtenirDegatsDAttaque() {
		return this.pointsDAttaque;
	}

	/**
	 * Change l'etat de la carte en Inactive
	 */
	public void modeInactive() {
		this.estInactive = true;
	}

	/**
	 * Change l'etat de la carte en active
	 */
	public void modeActive() {
		this.estInactive = false;
	}

	/**
	 * Inflige des degats a un personnage
	 * 
	 * @param personnage
	 *            : Le personnage cible par l'attaque
	 */
	public void infligerDegats(Personnage personnage)
	{
		personnage.pointDeVie = personnage.pointDeVie - this.pointsDAttaque;
		this.subirDegats(personnage.pointsDAttaque);
	}

	/**
	 * methode qui renvoi l'effet de la carte
	 * @return effet
	 * effet de  la carte
	 */
	public Effet getEffet() {
		return this.effet;
	}
	
	/**
	 * methode qui renvoi le cout en mana de la carte
	 * @return int	
	 * cout en mana de la carte
	 */
	public int getCoutEnMana(){
		return this.coutEnMana;
	}
	
	
	public void setDegatAttaque(int attaque){
		this.pointsDAttaque+=attaque;
	}
	
	
	public void setPointDeVie(int vie){
		this.pointDeVie+=vie;
	}



	public void buffVieMax(int vie) {
		this.pointsDeVieMax+=vie;
		this.pointDeVie+=vie;
		
	}
	
	public String toString(){
		return ""+this.nom+" "+this.pointDeVie+" "+this.pointsDAttaque;
	}
		
}