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
	 * Une liste de carte n'a pas de cartes a sa création mais on connait son nombres de cartes qu'ele pourras contenir
	 * @param nbCartesMax 
	 * Nombres de cartes Max
	 */
	public ListeDeCartes(int nbCartesMax){
		this.nbCartesMax=nbCartesMax;
		this.cartes= new Carte[this.nbCartesMax];
		this.nbCartes=0;
	}
	
	public void incrementerNbCartes(){
		this.nbCartes++;
	}

	public void decretementerNbCartes(){
		this.nbCartes--;
	}
	
	public int getNbCartes(){
		return this.nbCartes;
	}
	
	public int getNbCartesMax(){
		return this.getNbCartesMax();
	}
}
