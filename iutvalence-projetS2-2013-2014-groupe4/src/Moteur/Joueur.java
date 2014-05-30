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
	private Cimetiere cimetiere;

	/**
	 * Permet de savoir où le joueur en ai de la pioche de son deck
	 */
	private int curseurDeck;

	private Heros heros;

	private ListeDeCartes cartesPosees;

	/**
	 * Permet de créer un joueur avec un numero, un deck, une main de départ et
	 * un cimetière
	 * 
	 * @param numeroDuJoueur
	 *            numero du joueur
	 * @param deckJoueur
	 *            deck du joueur
	 * @param mainJoueur
	 *            main du joueur
	 * @param cimetiereJoueur
	 *            cimetiere du joueur
	 */
	public Joueur(int numeroDuJoueur, String nomHeros) {
		this.numeroJoueur = numeroDuJoueur;
		this.deck = new ListeDeCartes(Jeu.NB_CARTES_DECK);
		this.main = new ListeDeCartes(Jeu.NB_MAX_CARTES_MAIN);
		this.cimetiere = new Cimetiere();
		this.heros = new Heros(nomHeros);
		this.cartesPosees = new ListeDeCartes(Jeu.NB_CARTES_MAX_POSEES);
		this.curseurDeck = Jeu.NB_CARTES_DECK - 1;
	}

	/**
	 * Renvoi le deck du joueur
	 * @return ListeDeCartes
	 */
	public ListeDeCartes getDeck() {
		return this.deck;
	}

	/**
	 * Renvoi la main du joueur
	 * @return ListeDeCartes
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
		return this.cimetiere;
	}

	/**
	 * Ajoute la carte passée en parametre dans le cimetiere du joueur
	 */
	public void setCimetiere(Carte carteMorte) {
		this.cimetiere.cartes[this.cimetiere.nbCartes] = carteMorte;
		this.incrementerNbCartesCimetiere();
	}

	/**
	 * Renvoi le curseur deck du joueur
	 * @return int
	 */
	public int getCurseurDeck() {
		return this.curseurDeck;
	}

	/**
	 * Incremente le curseurMain du joueur
	 */
	public void incrementerNbCartesMain() {
		this.main.nbCartes++;
	}

	/**
	 * Décremente le curseurMain du joueur
	 */
	public void decrementerNbCartesMain() {
		this.main.nbCartes--;
	}

	/**
	 * Renvoi le curseur deck du joueur
	 * @return int
	 */
	public int getNbCartesMain() {
		return this.main.nbCartes;
	}

	/**
	 * Renvoi le curseur deck du joueur
	 * @return int
	 */
	public int getNbCartesCimetiere() {
		return this.cimetiere.nbCartes;
	}

	/**
	 * Incremente le curseur cimetiere du joueur
	 */
	public void incrementerNbCartesCimetiere() {
		this.cimetiere.nbCartes++;
	}

	/**
	 * Pioche la carte sur le dessus du deck et la place dans la main du joueur
	 */
	public void piocherCarte() {
		if (this.getMain().nbCartes < Jeu.NB_MAX_CARTES_MAIN) {
			if (this.curseurDeck < Jeu.NB_CARTES_DECK){
			this.setMain(this.deck.cartes[this.curseurDeck]);
			this.incrementerCurseurDeck();}
			else {
				this.getHeros().setPointsDeVie(0);
			}
		}

	}

	/**
	 * Incremente le curseur deck du joueur
	 */
	public void incrementerCurseurDeck() {
		this.curseurDeck++;
	}

	/**
	 * Renvoi le curseur plateau du joueur
	 * @return int
	 */
	public int getNbCartesPosees() {
		return this.cartesPosees.nbCartes;
	}
	/**
	 * Renvoi le numero du joueur
	 * @return int
	 */
	public int getNumeroJoueur() {
		return this.numeroJoueur;
	}
	/**
	 * Méthode qui affecte au deck la carte choisie en paramètre
	 * @param carteChoisie
	 */
	public void setDeck(Carte carteChoisie) {
		this.deck.cartes[this.getCurseurDeck()] = carteChoisie;
		if (this.curseurDeck > 0)
			this.decrementerCurseurDeck();
	}

	/**
	 * décremente le curseur du deck
	 */
	public void decrementerCurseurDeck() {
		this.curseurDeck--;
	}
	/**
	 * Renvoi le heros
	 * @return Heros
	 */
	public Heros getHeros() {
		return this.heros;
	}
	/**
	 * Méthode qui test si le nombre de cartes mc=ximum à poser est atteint 
	 * @param joueur
	 * @return boolean
	 */
	public boolean estPlein(Joueur joueur) {
		if (this.cartesPosees.nbCartes == Jeu.NB_CARTES_MAX_POSEES)
			return true;
		else
			return false;
	}
	/**
	 * Incremente le nombre de cartes posées
	 */
	public void incrementerNbCartesPosees() {
		this.cartesPosees.nbCartes++;
	}
	/**
	 * Decremente le nombre de cartes posées
	 */
	public void decrementerNbCartesPosees() {
		if (this.cartesPosees.nbCartes > 0)
			this.cartesPosees.nbCartes--;
	}
	/**
	 * Méthode qui renvoie la carte posée
	 * @return ListeDeCartes
	 */
	public ListeDeCartes getCartesPosees() {
		return this.cartesPosees;
	}
	/**
	 * Méthode qui réorganise la plateau en fonction des cartes posées
	 */
	public void reOrganiserPlateau() {
		for (int i = 0; i < this.getCartesPosees().getNbCartes(); i++)
			if (!(this.getCartesPosees().cartes[i] instanceof Carte)) {
				int indiceProchaineCarteNonNull = i + 1;
				while (this.getCartesPosees().cartes[indiceProchaineCarteNonNull] == null
						&& indiceProchaineCarteNonNull < this.getCartesPosees().getNbCartes())
					indiceProchaineCarteNonNull++;
				if ((indiceProchaineCarteNonNull < this.getCartesPosees().getNbCartes() - 1)
						|| (this.getCartesPosees().cartes[indiceProchaineCarteNonNull] != null)) {
					this.getCartesPosees().cartes[i] = this.getCartesPosees().cartes[indiceProchaineCarteNonNull];
					this.getCartesPosees().cartes[indiceProchaineCarteNonNull] = null;
				}
			}
	}

	public abstract Carte choisirCarteDeck();

	public abstract Carte choisirCarteAttaquante();

	public abstract Personnage choisirPersonnageAAttaquer(Joueur joueurAdverse);
	
	public abstract Carte choisirCarteAPoser();

	public abstract Personnage choisirCarteABuffer();
	/**
	 * Méthode qui réorganise la main en fonction des cartes de la main
	 */
	public void reOrganiserMain() {
		for (int i = 0; i < this.getMain().getNbCartes(); i++)
			if (!(this.getMain().cartes[i] instanceof Carte)) {
				int indiceProchaineCarteNonNull = i + 1;
				while (this.getMain().cartes[indiceProchaineCarteNonNull] == null && indiceProchaineCarteNonNull < this.getMain().getNbCartes())
					indiceProchaineCarteNonNull++;
				if ((indiceProchaineCarteNonNull < this.getMain().getNbCartes() - 1) || (this.getMain().cartes[indiceProchaineCarteNonNull] != null)) {
					this.getMain().cartes[i] = this.getMain().cartes[indiceProchaineCarteNonNull];
					this.getMain().cartes[indiceProchaineCarteNonNull] = null;
				}
			}
	}
	
	
	
}