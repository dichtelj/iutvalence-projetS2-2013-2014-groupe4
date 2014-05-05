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
	private Joueur[] joueur;

	/**
	 * Une partie a un plateau et 2 decks a sa création
	 */
	public Partie(Joueur[] joueurs) {
		
		this.joueur= joueurs;
		this.plateau= new Plateau();
	}
}
