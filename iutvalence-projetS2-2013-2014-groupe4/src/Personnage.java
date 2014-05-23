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
}
