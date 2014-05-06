
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
	 * Permet de savoir où en est le joueur dans la pioche de son deck
	 */
	private int curseurDeck;
	
	/**
	 * Permet de savoir où en est la main du joueur
	 */
	private int curseurMain;
	
	/**
	 * Permet de savoir où en est le cimetiere du joueur
	 */
	private int curseurCimetiere;

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

	/**
	 * Renvoi le deck du joueur
	 */
	public ListeDeCartes getDeck() {
		return this.deck;
	}

	/**
	 * Renvoi la main du joueur
	 */
	public ListeDeCartes getMain() {
		return this.main;
	}

	/**
	 * Ajoute la carte passée en parametre dans la main du joueur
	 */
	public void setMain(Carte cartePiochee) {
		this.main.cartes[this.curseurMain] = cartePiochee;
	}

	/**
	 * Renvoi le cimetiere du joueur
	 */
	public Cimetiere getCimetiere() {
		return this.cimetiere;
	}

	/**
	 * Ajoute la carte passée en parametre dans le cimetiere du joueur
	 */
	public void setCimetiere(Carte carteMorte) {
		this.cimetiere.cartes[this.curseurCimetiere]=carteMorte;
		this.curseurCimetiere++;
	}

	/**
	 * Renvoi le curseur deck du joueur
	 */
	public int getCurseurDeck() {
		return this.curseurDeck;
	}

	public void incrementerCurseurMain() {
		this.curseurDeck ++;
	}
	/**
	 * Renvoi le curseur deck du joueur
	 */
	public int getCurseurMain() {
		return this.curseurMain;
	}

	/**
	 * Incremente le curseurMain du joueur
	 */
	public void incrementerCurseurMain() {
		this.curseurMain ++;
	}
	/**
	 * Renvoi le curseur deck du joueur
	 */
	public int getCurseurCimetiere() {
		return this.curseurCimetiere;
	}

	/**
	 * Incremente le curseur cimetiere du joueur
	 */
	public void incrementerCurseurCimetiere() {
		this.curseurCimetiere ++;
	}
}