package Moteur;
/**
 * Definition d'une liste de carte du jeu
 *
 */
public class ListeDeCartes {

	/**
	 * Nombre de cartes presentes au moment de l'appel
	 */
	protected int nbCartes;

	/**
	 * Nombre de cartes que peut contenir au maximum la liste
	 */
	protected int nbCartesMax;

	/**
	 * Les cartes de la liste
	 */
	protected Carte[] cartes;

	/**
	 * Une liste de carte n'a pas de cartes a sa création mais on connait son nombres de cartes qu'elle pourra contenir
	 * @param nbCartesMax 
	 * Nombres de cartes Max
	 */
	public ListeDeCartes(int nbCartesMax){
		this.nbCartesMax=nbCartesMax;
		this.cartes= new Carte[this.nbCartesMax];
		this.nbCartes=0;
	}
	/**
	 * méthode qui incremente le nombre de cartes
	 */
	public void incrementerNbCartes(){
		this.nbCartes++;
	}
	/**
	 * méthode qui decremente le nombre de cartes
	 */
	public void decretementerNbCartes(){
		this.nbCartes--;
	}
	/**
	 * méthode qui renvoi le nombre de cartes
	 */
	public int getNbCartes(){
		return this.nbCartes;
	}
	/**
	 * méthode qui renvoie le nombre de cartes maximum
	 * @return int
	 */
	public int getNbCartesMax(){
		return this.getNbCartesMax();
	}
	/**
	 * méthode qui renvoi les cartes de la liste de carte
	 * @return Cartes[]
	 */
	public Carte[] getCartes(){
		return this.cartes;
	}
}
