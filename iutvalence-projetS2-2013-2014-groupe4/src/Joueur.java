
public class Joueur {
	/**
	 * numero du joueur
	 */
	private int numeroJoueur;
	/**
	 * deck du joueur
	 */
	private ListeDeCartes deck;
	/**
	 * Cartes dans la main du joueur
	 */
	private ListeDeCartes main;
	/**
	 * Cartes dans le cimetiere du joueur
	 */
	private Cimetiere cimetiere;
	
/**
 * Permet de créer un joueur avec un numero, un deck, une main de départ et un cimetière
 * @param numeroDuJoueur numero du joueur
 * @param deckJoueur deck du joueur
 * @param mainJoueur main du joueur 
 * @param cimetiereJoueur cimetiere du joueur
 */
	public Joueur(int numeroDuJoueur, ListeDeCartes deckJoueur, ListeDeCartes mainJoueur, Cimetiere cimetiereJoueur)
	{
		this.numeroJoueur=numeroDuJoueur;
		this.deck=deckJoueur;
		this.main=mainJoueur;
		this.cimetiere=cimetiereJoueur;
	}

}