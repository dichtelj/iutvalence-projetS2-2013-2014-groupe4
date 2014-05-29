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

	public int getNbManaMax() {
		return this.nbManaMax;
	}
	
	public int getNbManaCourant() {
		return this.nbManaCourant;
	}

	public void setNbManaCourant(int nbManaCourant) {
		this.nbManaCourant = nbManaCourant;
	}
	
	public void decrementerNbManaCourant(int nbmana){
		this.nbManaCourant-=nbmana;
	}
	
	public void incrementerNbManaMax(){
		this.nbManaMax++;
	}
}
