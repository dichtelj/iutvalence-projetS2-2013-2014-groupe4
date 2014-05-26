import java.util.Random;

/**
 * Definition d'une partie de Battle for Demacia
 */
public class Jeu {

	public final static int NB_CARTES_DECK = 60;

	public static final int NB_CARTES_MAIN = 10;

	public static final ListeDeCartes LISTE_CARTE_GENERALE = Jeu.creerListeDeCartesGenerale();
	
	/**
	 * Le joueur ne peut pas avoir plus de DEFAULT_CONSTANT_CARTEMAX cartes sur
	 * le terrain
	 */
	public final static int DEFAULT_CONSTANT_CARTEMAX = 7;

	/**
	 * Les decks des 2 joueurs de la partie
	 */
	private Joueur[] joueurs;

	/**
	 * Une partie a un plateau et 2 decks a sa création
	 * 
	 * @param joueurs
	 *            Les joueurs du jeu
	 * @param affichage
	 *            Le type d'affichage du jeu
	 */
	public Jeu(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Méthode qui permet de débuter le tour d'un joueur
	 * 
	 * @param joueur
	 *            joueur qui débute son tour
	 */
	public void debutTour(Joueur joueur) {
		for (int i = 0; i < this.joueurs[0].getNbCartesPlateau(); i++)
			if (this.joueurs[0].getCartesPosees().cartes[i].getEffet().getActivation().compareTo("debut") == 0)
				this.joueurs[0].getCartesPosees().cartes[i].getEffet().appliquerEffet(joueur);
		for (int j = 0; j < this.joueurs[1].getNbCartesPlateau(); j++)
			if (this.joueurs[1].getCartesPosees().cartes[j].getEffet().getActivation().compareTo("debut") == 0)
				this.joueurs[1].getCartesPosees().cartes[j].getEffet().appliquerEffet(joueur);
		if (!(joueur.getMain().nbCartes == joueur.getMain().nbCartesMax))
			joueur.piocherCarte();
		joueur.incrementerCurseurDeck();
		joueur.getHeros().incrementerNbMana();
	}

	public void preparerPartie() throws DeckInvalide {
		if (!(this.joueurs[0].getDeck().cartes[NB_CARTES_DECK - 1] instanceof Carte))
			throw new DeckInvalide();
		JoueurAleatoire joueur2 = (JoueurAleatoire) this.joueurs[1];
		joueur2.attribuerDeckAleatoire(LISTE_CARTE_GENERALE);
		this.attribuerMainDepart(this.joueurs[0]);
		this.melangerDeck(this.joueurs[0]);
		this.attribuerMainDepart(this.joueurs[1]);
	}

	public void jouer() {
		int indiceJoueurCourant = 0;
		Joueur joueurCourant = this.joueurs[indiceJoueurCourant];
		while (!(this.partieFinie())) {
			this.debutTour(joueurCourant);
			this.jouerTour(joueurCourant);
			this.finTour(joueurCourant);
			joueurCourant = this.joueurs[(indiceJoueurCourant + 1) % 2];
		}
	}

	private void jouerTour(Joueur joueurCourant) {
		while (peutEncoreJouer(joueurCourant)) {
			this.jouerTourIntermediaire(joueurCourant);
		}
	}

	private void jouerTourIntermediaire(Joueur joueurCourant) {
		Position carteAUtiliser = joueurCourant.choisirCarteAUtiliser();
		if (this.estDansMain(carteAUtiliser))
			if (joueurCourant.getNumeroJoueur() == 1) {
				try {
					this.poserCarte(carteAUtiliser.getListe().cartes[carteAUtiliser.getIndex()], joueurCourant);
				}
				catch (PlateauPlein e1) {
					System.out.println("Plateau Plein");
				}
				catch (ManaInsuffisant e2) {
					System.out.println("Mana Insuffisant");
				}
			}
			else {
				Personnage personnageAttaque = this.choisirCarteAAttaquerAleatoire(joueurCourant);
				try {
					this.attaquerAvecCarte(carteAUtiliser.getListe().cartes[carteAUtiliser.getIndex()], personnageAttaque, joueurCourant);
				}
				catch (CibleInvalide e1) {
					System.out.println("Cible invalide");
				}
				catch (EstInactif e2) {
					System.out.println("La carte est inactive");
				}
			}

	}

	private boolean estDansMain(Position carte) {
		if (carte.getListe().getNbCartesMax() == 10)
			return true;
		return false;
	}

	public boolean partieFinie() {
		if ((this.joueurs[0].getHeros().getPointsDeVie() < 0) || (this.joueurs[1].getHeros().getPointsDeVie() < 0))
			return true;
		return false;
	}

	/**
	 * Méthode permettant a un joueur de finir son tour
	 * 
	 * @param joueur
	 *            joueur qui fini son tour
	 */
	public void finTour(Joueur joueur) {
		for (int i = 0; i < this.joueurs[0].getNbCartesPlateau(); i++)
			if (this.joueurs[0].getCartesPosees().cartes[i].getEffet().getActivation().compareTo("fin") == 0)
				this.joueurs[0].getCartesPosees().cartes[i].getEffet().appliquerEffet(joueur);
		for (int j = 0; j < this.joueurs[1].getNbCartesPlateau(); j++)
			if (this.joueurs[1].getCartesPosees().cartes[j].getEffet().getActivation().compareTo("fin") == 0){
				this.joueurs[1].getCartesPosees().cartes[j].getEffet().appliquerEffet(joueur);
				this.joueurs[1].getCartesPosees().cartes[j].getEffet().appliquerEffet(joueur);
			}
			for (int i = 0; i < joueur.getNbCartesPlateau(); i++)
				joueur.getCartesPosees().cartes[i].modeActive();
		joueur.getHeros().setNbManaCourant(joueur.getHeros().getNbMana());
		viderPlateau();
	}

	/**
	 * Methode qui permet de poser une carte
	 * 
	 * @param carte
	 *            carte à poser
	 * @param joueur
	 *            joueur posant la carte
	 * @return String
	 * @throws PlateauPlein
	 * @throws ManaInsuffisant
	 */
	public void poserCarte(Carte carte, Joueur joueur) throws PlateauPlein, ManaInsuffisant {
		if (joueur.estPlein(joueur))
			throw new PlateauPlein();
		if (carte.getCoutEnMana() > joueur.getHeros().getNbManaCourant())
			throw new ManaInsuffisant();
		if (carte.getEffet().getActivation().compareTo("invocation") == 0)
			carte.getEffet().appliquerEffet(joueur);
		joueur.getCartesPosees().cartes[joueur.getNbCartesPlateau()] = carte;
		joueur.getHeros().decrementerNbManaCourant(carte.getCoutEnMana());
		this.viderPlateau();
		this.reOrganiserCartes(joueur);
	}

	/**
	 * Permet d'utiliser la carte (invoquer ou attaquer)
	 * 
	 * @param action
	 *            action de la carte
	 * @param carte
	 *            carte utilisée
	 * @param personnage
	 *            personnage qui utilise la carte
	 * @param joueur
	 *            joueur qui utilise la carte
	 * @return String
	 * @throws CibleInvalide
	 * @throws ManaInsuffisant
	 * @throws PlateauPlein
	 * 
	 */
	public void attaquerAvecCarte(Carte carte, Personnage personnage, Joueur joueur) throws CibleInvalide, EstInactif{		
			if (carte.estInactif())
				throw new EstInactif();
			if (!(this.cibleViable(personnage, joueur)))
				throw new CibleInvalide();
			carte.infligerDegats(personnage);
			carte.modeInactive();
			this.viderPlateau();
			this.reOrganiserCartes(joueur);		
	}

	/**
	 * @param carte
	 *            Carte ciblée
	 * @param personnage
	 *            personnage ciblé
	 * @param joueur
	 *            joueur ciblé
	 * @return boolean
	 */
	private boolean cibleViable(Personnage personnage, Joueur joueur) {
		if (this.existeProvocation(joueur))
			if (!(personnage instanceof Carte))
				return false;
		if (((Carte) personnage).getEffet().getNom().compareTo("provocation") != 0)
			return false;
		return true;
	}

	/**
	 * Methode qui permet de savoir s'il existe une carte avec provocation sur
	 * le terrain adverse (on ne peut frapper que les cartes avec provocation
	 * s'il y en a)
	 * 
	 * @param joueur
	 *            Joueur jouant son tour
	 * @return boolean Dit si oui ou non il y a une provocation sur le terrain
	 *         adverse
	 */
	private boolean existeProvocation(Joueur joueur) {
			for (int i = 0; i < joueur.getNbCartesPlateau(); i++)
				if (joueur.getCartesPosees().cartes[i].getEffet().getNom().compareTo("provocation") == 0)
					return true;
			return false;
	}

	/**
	 * Methode qui renvoi le tableau des joueurs de la partie
	 * 
	 * @return joueurs Les joueurs de la partie
	 */
	public Joueur[] getJoueurs() {
		return this.joueurs;
	}

	/**
	 * Methode qui permet de crée un deck
	 * 
	 * @param liste
	 *            Liste qui fait office de deck
	 */
	public void construireDeck(ListeDeCartes liste) {
		int compteurCarte = 0;
		while (compteurCarte < NB_CARTES_DECK) {
			Carte carteChoisie = this.joueurs[0].choisirCarteDeck();
			this.joueurs[0].setDeck(carteChoisie);
		}

	}

	/**
	 * Méthode qui crée la liste de carte générale
	 * 
	 * @return liste Liste de carte générale
	 * 
	 */
	public static ListeDeCartes creerListeDeCartesGenerale() {
		ListeDeCartes liste = new ListeDeCartes(200);
		liste.cartes[0] = new Carte("Teemo", 2, 2, (new Effet("invocation", "degat direct", 3, 3)), 5, "Petite peste");
		liste.cartes[1] = new Carte("Katarina", 5, 5, (new Effet("fin", "degat direct", 2, 7)), 6, "Lame sinistre");
		liste.cartes[2] = new Carte("Jarvan IV", 4, 5, null, 4, "Exemple Demacien");
		liste.cartes[3] = new Carte("Yasuo", 2, 3, (new Effet("invocation", "degat direct", 2, 1)), 3, "Le disgracie");
		liste.cartes[4] = new Carte("Creuset de michael", 0, 0, (new Effet("invocation", "heal", 2, 1)), 3, "Cloche regeneratrice");
		liste.cartes[5] = new Carte("Syndra", 6, 6, (new Effet("invocation", "piocher", 2, 0)), 7, "Souveraine obscure");
		liste.cartes[6] = new Carte("Malzahar", 0, 3, (new Effet("fin", "piocher", 1, 0)), 3, "Le prophete du vide");
		liste.cartes[7] = new Carte("Darius", 5, 6, (new Effet("fin", "degat direct", 1, 7)), 8, "Main de noxus");
		liste.cartes[8] = new Carte("Big Fucking glaive", 0, 0, (new Effet("invocation", "buff attaque", 3, 1)), 1, "L'epee des heros");
		liste.cartes[9] = new Carte("Ashe", 3, 2, (new Effet("invocation", "degat direct", 1, 7)), 3, "Archere de givre");
		liste.cartes[10] = new Carte("Aatrox", 4, 5, (new Effet("invocation", "heal", 2, 2)), 5, "Epee des darkin");
		liste.cartes[11] = new Carte("Irelia", 4, 4, (new Effet("invocation", "piocher", 1, 4)), 5, "Volonte des lames");
		liste.cartes[12] = new Carte("Jinx", 2, 2, null, 1, "La gachette folle");
		liste.cartes[13] = new Carte("Thresh", 3, 3, (new Effet("invocation", "heal", 2, 1)), 3, "Garde aux chaines");
		liste.cartes[14] = new Carte("Shen", 6, 6, (new Effet("invocation", "buff vie", 3, 1)), 6, "Oeil du crepuscule");
		liste.cartes[15] = new Carte("Anivia", 5, 5, null, 5, "Cryophenix");
		liste.cartes[16] = new Carte("Poppy", 8, 8, null, 8, "Ambassadeur de fer");
		return liste;
	}

	/**
	 * 
	 * Methode qui permet d'enlever du plateau les cartes n'ayant plus de vie.
	 */
	public void viderPlateau() {
		for (int i = 0; i < this.joueurs[0].getNbCartesPlateau(); i++)
			if (this.joueurs[0].getCartesPosees().cartes[i].getPointsDeVie() <= 0) {
				this.jeterCarte(this.joueurs[0].getCartesPosees().cartes[i], this.joueurs[0]);
				this.joueurs[0].getCartesPosees().cartes[i] = null;
				this.joueurs[0].decrementerNbCartes();
				this.reOrganiserCartes(this.joueurs[0]);
			}
		for (int j = 0; j < this.joueurs[1].getNbCartesPlateau(); j++) {
			if (this.joueurs[1].getCartesPosees().cartes[j].getEffet().getActivation().compareTo("fin") <= 0)
				this.jeterCarte(this.joueurs[1].getCartesPosees().cartes[j], this.joueurs[1]);
			this.joueurs[1].getCartesPosees().cartes[j] = null;
			this.joueurs[1].decrementerNbCartes();
			this.reOrganiserCartes(this.joueurs[1]);
		}
	}

	public void reOrganiserCartes(Joueur joueur) {
		for (int i = 0; i < Jeu.DEFAULT_CONSTANT_CARTEMAX; i++)
			if (!(joueur.getCartesPosees().cartes[i] instanceof Carte)){
				joueur.getCartesPosees().cartes[i] = joueur.getCartesPosees().cartes[i + 1];
				joueur.getCartesPosees().cartes[i] = null;
			}
		
	}

	public void melangerDeck(Joueur joueur) {
		Random generateurDeNombresAleatoires = new Random();

		for (int nombreDePermutations = 0; nombreDePermutations < 100; nombreDePermutations++) {
			int indexSrc = generateurDeNombresAleatoires.nextInt(NB_CARTES_DECK);
			int indexDest = generateurDeNombresAleatoires.nextInt(NB_CARTES_DECK);
			if (joueur.getNumeroJoueur() == 1) {
				Carte carteEchangee = this.joueurs[0].getDeck().cartes[indexSrc];
				this.joueurs[0].getDeck().cartes[indexSrc] = carteEchangee;
				this.joueurs[0].getDeck().cartes[indexSrc] = this.joueurs[0].getDeck().cartes[indexDest];
				this.joueurs[0].getDeck().cartes[indexDest] = carteEchangee;
			}
		}
	}

	public void attribuerMainDepart(Joueur joueur) {
		joueur.piocherCarte();
		joueur.piocherCarte();
		joueur.piocherCarte();
	}

	/**
	 * Envoi la carte passer en paramètre dans le cimetière
	 */
	public void jeterCarte(Carte carte, Joueur joueur) {
		if (carte.effet.getActivation().compareTo("mort") == 0)
			carte.effet.appliquerEffet(joueur);
			joueur.getCimetiere().setCimetiere(carte);
			joueur.getCimetiere().incrementerNbCartes();

	}

	public boolean peutEncoreJouer(Joueur joueur) {
		for (int indiceCarte = 0; indiceCarte < joueur.getNbCartesMain(); indiceCarte++)
			if (joueur.getMain().cartes[indiceCarte].getCoutEnMana() < joueur.getHeros().getNbManaCourant())
				return true;
		for (int indiceCarte = 0; indiceCarte < joueur.getNbCartesPlateau(); indiceCarte++)
				if (joueur.getCartesPosees().cartes[indiceCarte].estInactif() == false)
					return true;
		return false;
	}

	public Personnage choisirCarteAAttaquerAleatoire(Joueur joueur) {
		Random generateurNombreAleatoire = new Random();
		int indexPersonnageAAttaque = generateurNombreAleatoire.nextInt(joueur.getNbCartesPlateau() + 1);
		if (joueur.getNumeroJoueur() == 1) {
			if ((indexPersonnageAAttaque) == joueur.getNbCartesPlateau())
				return this.joueurs[1].getHeros();
			return this.joueurs[0].getCartesPosees().cartes[indexPersonnageAAttaque];
		}
		else if ((indexPersonnageAAttaque) > joueur.getNbCartesPlateau())
			return this.joueurs[0].getHeros();
		return this.joueurs[1].getCartesPosees().cartes[indexPersonnageAAttaque];
	}
	
	public Carte choisirCarteAUtilise(Joueur joueur){
		Random generateurNombreAleatoire = new Random();
		int indexPersonnageAAttaque = generateurNombreAleatoire.nextInt(joueur.getNbCartesPlateau());
	}
	
	
	
}
