
public abstract class Joueur {
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
	private ListeDeCartes cimetiere;
	
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
	 * Correspond au nombre de créatures posées sur le plateau coté joueur
	 */
	private int curseurPlateau;
/**
 * Permet de créer un joueur avec un numero, un deck, une main de départ et un cimetière
 * @param numeroDuJoueur numero du joueur
 * @param deckJoueur deck du joueur
 * @param mainJoueur main du joueur 
 * @param cimetiereJoueur cimetiere du joueur
 */
	public Joueur(int numeroDuJoueur)
	{
		this.numeroJoueur=numeroDuJoueur;
		this.deck=new ListeDeCartes(60);
		this.main=new ListeDeCartes(10);
		this.cimetiere=new ListeDeCartes(60);
		this.curseurCimetiere=0;
		this.curseurDeck=0;
		this.curseurMain=0;
		this.curseurPlateau=0;
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
	public ListeDeCartes getCimetiere() {
		return this.cimetiere;
	}

	/**
	 * Ajoute la carte passée en parametre dans le cimetiere du joueur
	 */
	public void setCimetiere(Carte carteMorte) {
		this.cimetiere.cartes[this.curseurCimetiere]=carteMorte;
		this.incrementerCurseurCimetiere();
	}

	/**
	 * Renvoi le curseur deck du joueur
	 */
	public int getCurseurDeck() {
		return this.curseurDeck;
	}

	/**
	 * Incremente le curseurMain du joueur
	 */
	public void incrementerCurseurMain() {
		this.curseurMain ++;
	}
	
	/**
	 * Décremente le curseurMain du joueur
	 */
	public void decrementerCurseurMain() {
		this.curseurMain --;
	}
	/**
	 * Renvoi le curseur deck du joueur
	 */
	public int getCurseurMain() {
		return this.curseurMain;
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
	
	/**
	 * Pioche la carte sur le dessus du deck et la place dans la main du joueur
	 */
	public void piocherCarte(){
		this.setMain(this.deck.cartes[this.curseurDeck]);
		this.curseurDeck++;
	}
	
	/**
	 * Incremente le curseur deck du joueur
	 */
	public void incrementerCurseurDeck(){
		this.curseurDeck++;
	}
	
	/**
	 * Renvoi le curseur plateau du joueur
	 */
	public int getCurseurPlateau() {
		return this.curseurPlateau;
	}
	/**
	 * Incremente le curseur plateau du joueur
	 */
	public void incrementerCurseurPlateau(){
		this.curseurPlateau++;
	}

	public int getNumeroJoueur() {
		return this.numeroJoueur;
	}
	
	public abstract Carte choisirCartes(ListeDeCartes liste);
}