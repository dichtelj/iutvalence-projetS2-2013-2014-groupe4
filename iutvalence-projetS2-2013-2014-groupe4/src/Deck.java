/**
 * Definition d'un deck de jeu
 *
 */
public class Deck extends ListeDeCarte {

	
	/**
	 * Creation d'un deck vide qui peut acceuillir jusqu'a nbCartesMax cartes
	 */
	public Deck(){
		this.nbCartes=0;
		this.nbCartesMax=60;
		this.cartes=new Carte[this.nbCartesMax];
	}

}
