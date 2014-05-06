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
	private ListeDeCartes carteJoueur2;

	/**
	 * Le joueur ne peut pas avoir plus de DEFAULT_CONSTANT_CARTEMAX cartes sur
	 * le terrain
	 */
	private final int DEFAULT_CONSTANT_CARTEMAX = 7;


	public Plateau(){
				
		this.cartesJoueur1=new ListeDeCartes(DEFAULT_CONSTANT_CARTEMAX);		
		this.carteJoueur2=new ListeDeCartes(DEFAULT_CONSTANT_CARTEMAX);
	}

	/**
	 * Renvoi vrai si le joueur a pose DEFAULT_CONSTANT_CARTEMAX sur le terrain
	 */
	public boolean estPlein(Joueur joueur) {
		if(joueur.getCurseurPlateau()==DEFAULT_CONSTANT_CARTEMAX)
			return true;
		else
			return false;	
	}
}
