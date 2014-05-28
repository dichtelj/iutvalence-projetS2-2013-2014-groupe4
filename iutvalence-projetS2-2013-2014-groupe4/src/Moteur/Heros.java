package Moteur;
/**
 * 
 *
 */
public class Heros extends Personnage {

	/**
	 * Nombre de mana max par tour utilisable
	 */
	private int nbMana;
	
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
	this.nbMana=0;
	this.nbManaCourant=0;		
	}

	public void setNbMana(int nbMana) {
		this.nbMana = nbMana;
	}

	public int getNbMana() {
		return this.nbMana;
	}
	
	public int getNbManaCourant() {
		return this.nbManaCourant;
	}

	public void setNbManaCourant(int nbManaCourant) {
		this.nbManaCourant = nbManaCourant;
	}
	
	public void decrementerNbManaCourant(int nbmana){
		this.nbMana-=nbmana;
	}
	
	public void incrementerNbMana(){
		this.nbMana++;
	}
}
