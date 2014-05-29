package Moteur;
/**
 * 
 *
 */
public class Heros extends Personnage {

	/**
	 * Nombre de mana max par tour utilisable
	 */
	private int nbManaMax;
	
	/**
	 * Nombre de mana qui peut changer si le joueur joue une carte qui coute du mana,
	 * il reviens au max au debut de chaque tour
	 */
	private int nbManaCourant;
	
	/**
	 *  Creation d'un héros
	 * @param nom 
	 * nom du heros a crée
	 */
	public Heros(String nom){
	
	this.nom=nom;	
	this.pointDeVie=30;
	this.nbManaMax=0;
	this.nbManaCourant=0;		
	}

	/**
	 * methode qui renvoie le nombre de mana maximum du heros
	 * @return int 
	 */
	public int getNbManaMax() {
		return this.nbManaMax;
	}
	/**
	 * methode qui renvoie le nombre de mana courant du heros
	 * @return int
	 */
	public int getNbManaCourant() {
		return this.nbManaCourant;
	}
	/**
	 * methode qui attribue au heros un nombre de mana maximum du heros
	 * @return void
	 */
	public void setNbManaCourant(int nbManaCourant) {
		this.nbManaCourant = nbManaCourant;
	}
	/**
	 * methode qui decremente le nombre de mana courant
	 * @return void 
	 * @param nbmana : le nombre de mana à decrementer
	 */
	
	public void decrementerNbManaCourant(int nbmana){
		this.nbManaCourant-=nbmana;
	}
	/**
	 * methode qui incremente le nombre de mana maximum du heros
	 * @return void
	 */
	
	public void incrementerNbManaMax(){
		this.nbManaMax++;
	}
	
	public void setPointsDeVie(int pointDeVie){
		this.pointDeVie=pointDeVie;
	}
}
