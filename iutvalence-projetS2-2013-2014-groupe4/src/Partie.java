/**
 * Definition d'une partie de Battle for Demacia
 */
public class Partie {

	/**
	 * Plateau de la partie
	 */
	private Plateau plateau;

	/**
	 * Les decks des 2 joueurs de la partie
	 */
	private ListeDeCarte[] decks;

	/**
	 * Une partie a un plateau et 2 decks a sa création
	 */
	public Partie() {
		
		this.plateau= new Plateau();
		
		this.decks= new ListeDeCarte[2];
	}
}
