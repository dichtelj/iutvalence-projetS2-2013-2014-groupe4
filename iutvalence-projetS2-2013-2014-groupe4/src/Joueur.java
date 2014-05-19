
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
	 * Permet de savoir où le joueur en ai de la pioche de son deck
	 */
	private int curseurDeck;
	
	private Heros heros;
	

/**
 * Permet de créer un joueur avec un numero, un deck, une main de départ et un cimetière
 * @param numeroDuJoueur numero du joueur
 * @param deckJoueur deck du joueur
 * @param mainJoueur main du joueur 
 * @param cimetiereJoueur cimetiere du joueur
 */
	public Joueur(int numeroDuJoueur, Heros heros)
	{
		this.numeroJoueur=numeroDuJoueur;
		this.deck=new ListeDeCartes(60);
		this.main=new ListeDeCartes(10);
		this.cimetiere=new ListeDeCartes(60);
		this.heros=heros;
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
		this.main.cartes[this.main.nbCartes] = cartePiochee;
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
		this.cimetiere.cartes[this.cimetiere.nbCartes]=carteMorte;
		this.incrementerNbCartesCimetiere();
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
	public void incrementerNbCartesrMain() {
		this.main.nbCartes ++;
	}
	
	/**
	 * Décremente le curseurMain du joueur
	 */
	public void decrementerNbCartesMain() {
		this.main.nbCartes --;
	}
	/**
	 * Renvoi le curseur deck du joueur
	 */
	public int getNbCartesMain() {
		return this.main.nbCartes;
	}

	/**
	 * Renvoi le curseur deck du joueur
	 */
	public int getNbCartesCimetiere() {
		return this.cimetiere.nbCartes;
	}

	/**
	 * Incremente le curseur cimetiere du joueur
	 */
	public void incrementerNbCartesCimetiere() {
		this.cimetiere.nbCartes ++;
	}
	
	/**
	 * Pioche la carte sur le dessus du deck et la place dans la main du joueur
	 */
	public void piocherCarte(){
		this.setMain(this.deck.cartes[this.deck.nbCartes]);
		this.incrementerCurseurDeck();
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
	public int getNbCartesPlateau(Plateau plateau) {
		if (this.getNumeroJoueur()==1)
			return plateau.getCartesJoueur1().nbCartes;
		else return plateau.getCartesJoueur2().nbCartes;
	}
	

	public int getNumeroJoueur() {
		return this.numeroJoueur;
	}
	
	public void setDeck(Carte carteChoisie) {
		this.deck.cartes[this.deck.nbCartes]=carteChoisie;
		this.decrementerNbCartesDeck();
	}
	
	public void decrementerNbCartesDeck() {
		this.curseurDeck--;
	}
	
	public abstract Carte choisirCarte(ListeDeCartes liste);

	public Heros getHeros() {
		return this.heros;
	}

}