package Moteur;

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
	
	private ListeDeCartes cartesPosees;
	

/**
 * Permet de créer un joueur avec un numero, un deck, une main de départ et un cimetière
 * @param numeroDuJoueur numero du joueur
 * @param deckJoueur deck du joueur
 * @param mainJoueur main du joueur 
 * @param cimetiereJoueur cimetiere du joueur
 */
	public Joueur(int numeroDuJoueur, String nomHeros)
	{
		this.numeroJoueur=numeroDuJoueur;
		this.deck=new ListeDeCartes(Jeu.NB_CARTES_DECK);
		this.main=new ListeDeCartes(Jeu.NB_CARTES_MAIN);
		this.cimetiere=new ListeDeCartes(Jeu.NB_CARTES_DECK);
		this.heros=new Heros(nomHeros);
		this.cartesPosees=new ListeDeCartes(Jeu.NB_CARTES_MAX_POSEES);
		this.curseurDeck=Jeu.NB_CARTES_DECK-1;
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
		this.incrementerNbCartesMain();
	}

	/**
	 * Renvoi le cimetiere du joueur
	 */
	public Cimetiere getCimetiere() {
		return (Cimetiere)this.cimetiere;
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
	public void incrementerNbCartesMain() {
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
		if (this.getMain().nbCartes < Jeu.NB_CARTES_MAIN){
		this.setMain(this.deck.cartes[this.curseurDeck]);
		this.incrementerCurseurDeck();
		}

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
	public int getNbCartesPlateau() {
		return this.cartesPosees.nbCartes; 
	}
	

	public int getNumeroJoueur() {
		return this.numeroJoueur;
	}
	
	public void setDeck(Carte carteChoisie) {
		this.deck.cartes[this.getCurseurDeck()]=carteChoisie;
		if (this.curseurDeck > 0)
			this.decrementerNbCartesDeck();
	}
	
	public void decrementerNbCartesDeck() {
		this.curseurDeck--;
	}
	
	public Heros getHeros() {
		return this.heros;
	}
	
	public boolean estPlein(Joueur joueur) {
		if(this.cartesPosees.nbCartes==Jeu.NB_CARTES_MAX_POSEES)
			return true;
		else
			return false;	
	}
	
	public void incrementerNbCartes(){
		this.cartesPosees.nbCartes++;
	}
	
	public void decrementerNbCartesPosees(){
		if (this.cartesPosees.nbCartes > 0)
		this.cartesPosees.nbCartes--;
	}
	
	public ListeDeCartes getCartesPosees(){
		return this.cartesPosees;
	}
	
	public abstract Carte choisirCarteDeck();
	
	public abstract Position choisirCarteAUtiliser();

	public abstract Personnage choisirPersonnageAAttaquer(Joueur joueurAdverse);

	public abstract Personnage choisirCarteABuffer();	

}