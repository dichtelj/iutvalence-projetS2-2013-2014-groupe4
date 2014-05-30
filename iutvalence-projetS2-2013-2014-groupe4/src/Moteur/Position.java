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
	/**
	 * Affecte à une position une liste de carte et un index afin de la positionner
	 * @param liste
	 * @param index
	 */
	public Position(ListeDeCartes liste, int index){
		this.liste= liste;
		this.index= index;
	}
	/**
	 * méthode qui renvoie l'index de liste
	 * @return int
	 */
	public int getIndex() {
		return this.index;
	}
	/**
	 * méthode qui affecte un index
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * méthode qui renvoie la liste de cartes
	 * @return ListeDeCartes
	 */
	public ListeDeCartes getListe() {
		return this.liste;
	}
	
}
