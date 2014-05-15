/**
 * Definition d'un plateau de jeu
 * 
 */
public class Plateau {

	/**
	 * Cartes du joueur 1 presentes sur le plateau
	 */
	private ListeDeCartes cartesJoueur1;

	/**
	 * Cartes du joueur 2 presente sur le plateau
	 */
	private ListeDeCartes cartesJoueur2;

	/**
	 * Le joueur ne peut pas avoir plus de DEFAULT_CONSTANT_CARTEMAX cartes sur
	 * le terrain
	 */
	private final int DEFAULT_CONSTANT_CARTEMAX = 7;


	public Plateau(){
		this.cartesJoueur1=new ListeDeCartes(this.DEFAULT_CONSTANT_CARTEMAX);		
		this.cartesJoueur2=new ListeDeCartes(this.DEFAULT_CONSTANT_CARTEMAX);
	}

	/**
	 * Renvoi vrai si le joueur a pose DEFAULT_CONSTANT_CARTEMAX sur le terrain
	 */
	public boolean estPlein(Joueur joueur) {
		if(joueur.getNbCartesPlateau(joueur, this)==this.DEFAULT_CONSTANT_CARTEMAX)
			return true;
		else
			return false;	
	}

	public ListeDeCartes getCartesJoueur1() {
		return this.cartesJoueur1;
	}


	public ListeDeCartes getCartesJoueur2() {
		return this.cartesJoueur2;
	}
	
	public void incrementerNbCartesJoueur1(){
		this.cartesJoueur1.nbCartes++;
	}
	
	public void incrementerNbCartesJoueur2(){
		this.cartesJoueur1.nbCartes++;
	}
}
