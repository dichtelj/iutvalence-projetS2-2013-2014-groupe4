package Moteur;
/**
 * Definition d'un personnage du jeu(carte ou heros)
 */
public abstract class Personnage 
{
	/**
	 * Nom du personnage
	 */
	protected String nom;
	
	/**
	 * Point de vie du personnage 
	 */
	protected int pointDeVie;
	
	protected int pointsDeVieMax;

	/**
	 * Points d'attaque du personnage
	 */
	protected int pointsDAttaque;
	
	
	/**
	 * methode qui permet d'obtenir les points de vie de la carte
	 * @return int
	 * point de vie de la carte
	 */
	public int getPointsDeVie(){
		return this.pointDeVie;
	}
	
	/**
	 * methode qui permet d'enlever de la vie par rapport aux dégats subis
	 * @param degatsSubit
	 * dégats subis
	 */
	public void subirDegats(int degatsSubit){
		this.pointDeVie-=degatsSubit;
	}
	/**
	 * méthode qui augment les points de vie actuel sans dépasser les points de vie maximum
	 * @param vieSoigne
	 */
	public void soigner(int vieSoigne) {
		this.pointDeVie+=vieSoigne;
		if (this.pointDeVie > this.pointsDeVieMax)
			this.pointDeVie=this.pointsDeVieMax;		
	}
	
}
