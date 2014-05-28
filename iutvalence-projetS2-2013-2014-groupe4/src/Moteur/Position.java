package Moteur;

/**
 * Décrit la position d'une carte dans une liste de carte donnée
 * @author antoine
 *
 */
public class Position {

	/**
	 * Liste dans laquelle sa trouvela carte
	 */
	private ListeDeCartes liste;
	
	/**
	 * Index où se trouve la carte
	 */
	private int index;

	public Position(ListeDeCartes liste, int index){
		this.liste= liste;
		this.index= index;
	}
	
	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ListeDeCartes getListe() {
		return this.liste;
	}
	
}
