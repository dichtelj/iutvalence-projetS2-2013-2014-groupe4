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
	public String effet;
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
	public Carte(String nom, int attaque, int vie, String effet, int mana,
			String description) {
		this.nom = nom;
		this.pointDeVie = vie;
		this.pointsDAttaque = attaque;
		this.effet = effet;
		this.coutEnMana = mana;
		this.estInactive = false;
		this.description = description;
	}

	// TODO Verifier pour le return string
	/**
	 * Permet d'utiliser la carte (invoquer ou attaquer)
	 */
	public String utiliserCarte(String action, Personnage personnage,Joueur joueur) {
		if (action.compareTo("attaquer") == 0) {
			if (this.estInactif())
				return "ne peut pas attaquer";
			this.infligerDegats(personnage);
			this.modeInactive();
		}
		if (action.compareTo("invoquer") == 0) {
			this.poserCarte(null, null, null);
		}
		return " carte jouée";

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
	 * Pose la carte sur le plateau
	 */
	public boolean poserCarte(Plateau plateau, Carte carte, Joueur joueur) {
		if (plateau.estPlein(joueur) == true)
			return false;
		else {
			return true;
		}

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
		this.pointDeVie = this.pointDeVie - personnage.pointsDAttaque;
	}
}