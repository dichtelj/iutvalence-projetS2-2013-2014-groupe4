/**
 * Definition d'un plateau de jeu
 * 
 */
public class Plateau {

	/**
	 * Nombre de cartes presentes sur le plateau au moment de l'appel
	 */
	private int nbCartes;

	/**
	 * Cartes du joueur 1 presentes sur le plateau
	 */
	private Carte[] cartesJoueur1;

	/**
	 * Cartes du joueur 2 presente sur le plateau
	 */
	private Carte[] carteJoueur2;

	/**
	 * Le joueur ne peut pas avoir plus de DEFAULT_CONSTANT_CARTEMAX cartes sur
	 * le terrain
	 */
	private final int DEFAULT_CONSTANT_CARTEMAX = 7;

	/**
	 * Un plateau a 1 coté par joueur qui a au plus DEFAULT_CONSTANT_CARTEMAX
	 * cartes posees Le plateau n'a aucune carte de posee a sa creation
	 */

	// TO CHECK Liste de carte ou tableau de carte ?
	public Plateau(){
		
		this.nbCartes=0;		
		this.cartesJoueur1=new Carte[7];		
		this.carteJoueur2=new Carte[7];
	}

	/**
	 * Renvoi vrai si le joueur a pose DEFAULT_CONSTANT_CARTEMAX sur le terrain
	 */
	public boolean estPlein() {
		if(this.nbCartes==7)
			return true;
		else
			return false;	
	}
}
