import java.util.Random;

/**
 * Definition d'une partie de Battle for Demacia
 */
public class Jeu {
	
	
	public final static int NB_CARTES_DECK=60;

	public static final int NB_CARTES_MAIN = 10;

	/**
	 * Plateau de la partie
	 */
	private Plateau plateau;

	/**
	 * Les decks des 2 joueurs de la partie
	 */
	private Joueur[] joueurs;
	
	
	/**
	 * Une partie a un plateau et 2 decks a sa création
	 * @param joueurs 
	 * Les joueurs du jeu
	 * @param affichage 
	 * Le type d'affichage du jeu
	 */
public Jeu(Joueur[] joueurs, Affichage affichage) {
		
		this.joueurs= joueurs;
		this.plateau= new Plateau();

	}
	
	/**
	 * Méthode qui permet de débuter le tour d'un joueur
	 * @param joueur
	 * joueur qui débute son tour
	 */
	public void debutTour(Joueur joueur){
		for (int i=0; i<this.joueurs[0].getNbCartesPlateau(this.plateau);i++)
			if (this.plateau.getCartesJoueur1().cartes[i].getEffet().getActivation().compareTo("debut")==0)
				this.plateau.getCartesJoueur1().cartes[i].getEffet().appliquerEffet(this.plateau, joueur, joueur.getNumeroJoueur());
		for (int j=0; j<this.joueurs[0].getNbCartesPlateau(this.plateau);j++)
			if (this.plateau.getCartesJoueur2().cartes[j].getEffet().getActivation().compareTo("debut")==0)
				this.plateau.getCartesJoueur2().cartes[j].getEffet().appliquerEffet(this.plateau, joueur, joueur.getNumeroJoueur());
		if (joueur.getMain().nbCartes == joueur.getMain().nbCartesMax)
			joueur.incrementerCurseurDeck();
		else joueur.piocherCarte();
		joueur.getHeros().incrementerNbMana();
	}
	
	/**
	 * Méthode permettant a un joueur de finir son tour
	 * @param joueur
	 * joueur qui fini son tour
	 */
	public void finTour(Joueur joueur){
		for (int i=0; i<this.joueurs[0].getNbCartesPlateau(this.plateau);i++)
			if (this.plateau.getCartesJoueur1().cartes[i].getEffet().getActivation().compareTo("fin")==0)
				this.plateau.getCartesJoueur1().cartes[i].getEffet().appliquerEffet(this.plateau, joueur, joueur.getNumeroJoueur());
		for (int j=0; j<this.joueurs[0].getNbCartesPlateau(this.plateau);j++)
			if (this.plateau.getCartesJoueur2().cartes[j].getEffet().getActivation().compareTo("fin")==0)
				this.plateau.getCartesJoueur2().cartes[j].getEffet().appliquerEffet(this.plateau, joueur, joueur.getNumeroJoueur());
			if (joueur.getNumeroJoueur()==1){
				for(int i=0;i<this.plateau.getNbCartesJoueur1();i++)
					this.plateau.getCartesJoueur1().cartes[i].modeActive();
			}
			else {
				for(int i=0;i<this.plateau.getNbCartesJoueur2();i++)
					this.plateau.getCartesJoueur2().cartes[i].modeActive();
			}
			joueur.getHeros().setNbManaCourant(joueur.getHeros().getNbMana());
			viderPlateau();
	}
	
	/**
	 * Methode qui permet de poser une carte
	 * @param carte
	 * carte à poser
	 * @param joueur
	 * joueur posant la carte
	 * @return String
	 */
	public String poserCarte(Carte carte, Joueur joueur) {
		if (this.plateau.estPlein(joueur))
			return "plateau plein";
		if (carte.getCoutEnMana() > joueur.getHeros().getNbManaCourant())
			return "Pas assez de mana";
		if (carte.getEffet().getActivation().compareTo("invocation")==0)
			carte.getEffet().appliquerEffet(this.plateau, joueur, joueur.getNumeroJoueur());
		if (joueur.getNumeroJoueur()==1)
			this.plateau.getCartesJoueur1().cartes[this.plateau.getNbCartesJoueur1()]=carte;
		else this.plateau.getCartesJoueur2().cartes[this.plateau.getNbCartesJoueur2()]=carte;
		joueur.getHeros().decrementerNbManaCourant(carte.getCoutEnMana());
		return "";
	}
	
	
	/**
	 * Permet d'utiliser la carte (invoquer ou attaquer)
	 * @param action 
	 * action de la carte
	 * @param carte 
	 * carte utilisée
	 * @param personnage 
	 * personnage qui utilise la carte
	 * @param joueur 
	 * joueur qui utilise la carte
	 * @return String
	 * 
	 */
	public String utiliserCarte(String action, Carte carte, Personnage personnage,Joueur joueur) {
		if (action.compareTo("attaquer") == 0) {
			if (carte.estInactif())
				return "ne peut pas attaquer";
			if (this.cibleViable(carte, personnage, joueur))
			carte.infligerDegats(personnage);
			carte.modeInactive();
		}
		if (action.compareTo("invoquer") == 0) {
			this.poserCarte(carte, joueur);
		}
		return "";

	}

	/**
	 * @param carte
	 * Carte ciblée
	 * @param personnage
	 * personnage ciblé
	 * @param joueur
	 * joueur ciblé
	 * @return boolean
	 */
	private boolean cibleViable(Carte carte, Personnage personnage,Joueur joueur) {
		if (this.existeProvocation(joueur))
			if (!(personnage instanceof Carte))
				return false;
			if (((Carte)personnage).getEffet().getNom().compareTo("provocation")!=0)
				return false;
		return true;
	}

	/**
	 * Methode qui permet de savoir s'il existe une carte avec provocation sur le terrain adverse
	 * (on ne peut frapper que les cartes avec provocation s'il y en a)
	 * @param joueur
	 * Joueur jouant son tour
	 * @return boolean
	 * Dit si oui ou non il y a une provocation sur le terrain adverse
	 */
	private boolean existeProvocation(Joueur joueur) {
		if (joueur.getNumeroJoueur()==1){
			for (int i=0; i<this.plateau.getNbCartesJoueur1();i++)
				if (this.plateau.getCartesJoueur2().cartes[i].getEffet().getNom().compareTo("provocation")==0)
					return true;
			return false;}
		else{
			for (int i=0; i<this.plateau.getNbCartesJoueur2();i++)
				if (this.plateau.getCartesJoueur1().cartes[i].getEffet().getNom().compareTo("provocation")==0)
					return true;
			return false;}
	}

	/**
	 * Methode qui renvoi le tableau des joueurs de la partie
	 * @return joueurs
	 * Les joueurs de la partie 
	 */
	public Joueur[] getJoueurs() {
		return this.joueurs;
	}
	
	/**
	 * Methode qui permet de crée un deck
	 * @param liste
	 * Liste qui fait office de deck
	 */
	public void construireDeck(ListeDeCartes liste){
		int compteurCarte=0;
		while (compteurCarte < NB_CARTES_DECK)
		{
			Carte carteChoisie=this.joueurs[0].choisirCarte(liste);
			this.joueurs[0].setDeck(carteChoisie);
			
		}
			
	}
	
	/**
	 * Méthode qui crée la liste de carte générale
	 * @return liste
	 * Liste de carte générale
	 * 
	 */
	public static ListeDeCartes creerListeDeCartesGenerale(){
		ListeDeCartes liste=new ListeDeCartes(200);
		liste.cartes[0]= new Carte("Teemo", 3, 2, (new Effet("debut", "fromage", 3, 3)), 3, "Petite peste");
		return liste;
	}
	
	/**
	 * 
	 * Methode qui permet d'enlever du plateau les cartes n'ayant plus de vie.
	 */
	public void viderPlateau(){
		for (int i=0; i<this.joueurs[0].getNbCartesPlateau(this.plateau);i++)
			if (this.plateau.getCartesJoueur1().cartes[i].getPointsDeVie()<=0){
				this.joueurs[1].getCimetiere().jeterCarte(this.plateau.getCartesJoueur1().cartes[i]);
				this.plateau.getCartesJoueur1().cartes[i]=null;
				this.plateau.getCartesJoueur1().decretementerNbCartes();
				this.reOrganiserCartes(this.joueurs[0]);
			}
		for (int j=0; j<this.joueurs[0].getNbCartesPlateau(this.plateau);j++){
			if (this.plateau.getCartesJoueur2().cartes[j].getEffet().getActivation().compareTo("fin")<=0)
				this.joueurs[1].getCimetiere().jeterCarte(this.plateau.getCartesJoueur2().cartes[j]);
				this.plateau.getCartesJoueur2().cartes[j]=null;
				this.plateau.getCartesJoueur2().decretementerNbCartes();
				this.reOrganiserCartes(this.joueurs[1]) ;
	}
		}
	
	public void reOrganiserCartes(Joueur joueur){
		if (joueur.getNumeroJoueur()==1){
			for (int i=0; i < 7; i++)
				if (!(this.plateau.getCartesJoueur1().cartes[i] instanceof Carte))
					for (int j=i; j < 7; j++)
						this.plateau.getCartesJoueur1().cartes[j]=this.plateau.getCartesJoueur1().cartes[j+1];		}
		else 
			for (int i=0; i < 7; i++)
			if (!(this.plateau.getCartesJoueur2().cartes[i] instanceof Carte))
				for (int j=i; j < 7; j++)
					this.plateau.getCartesJoueur2().cartes[j]=this.plateau.getCartesJoueur1().cartes[j+1];		
		}
	
	
	public void melangerDeck(Joueur joueur){
		Random generateurDeNombresAleatoires = new Random();

		for (int nombreDePermutations = 0; nombreDePermutations < 100; nombreDePermutations++)
		{
		int indexSrc = generateurDeNombresAleatoires.nextInt(NB_CARTES_DECK);
		int indexDest = generateurDeNombresAleatoires.nextInt(NB_CARTES_DECK);
		if (joueur.getNumeroJoueur()==1){
			Carte carteEchangee = this.joueurs[0].getDeck().cartes[indexSrc];
			this.joueurs[0].getDeck().cartes[indexSrc]=carteEchangee;
			this.joueurs[0].getDeck().cartes[indexSrc]=this.joueurs[0].getDeck().cartes[indexDest];
			this.joueurs[0].getDeck().cartes[indexDest]=carteEchangee;
		}}
	}
	public void attribuerMainDepart(Joueur joueur){
		joueur.piocherCarte();
		joueur.piocherCarte();
		joueur.piocherCarte();
	}
}




