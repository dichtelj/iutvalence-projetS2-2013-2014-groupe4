package Moteur;
import java.util.Random;
import java.util.Scanner;

import IHM.Affichage;
import IHM.AffichageFenetre;

/**
 * Definition d'une partie de Battle for Demacia
 */
public class Jeu implements Controleur {
	/**
	 * Le nombre de cartes constituant un deck
	 */
	public final static int NB_CARTES_DECK = 60;
	/**
	 * Le nombre de cartes maximum pouvant composer une main
	 */
	public static final int NB_MAX_CARTES_MAIN = 10;
	/**
	 * Le mana maximum pouvant être atteint
	 */
	public static final int NB_MANA_MAX = 15;
	/**
	 * La liste de toutes les cartes du jeu
	 */
	public static final ListeDeCartes LISTE_CARTE_GENERALE = Jeu.creerListeDeCartesGenerale();
	/**
	 * Choix de l'affichage du jeu ( console ou IHM )
	 */
	private Affichage vue;
	/**
	 * Le nombre de cartes maximum pouvant être posé sur son côté du plateau
	 */
	public final static int NB_CARTES_MAX_POSEES = 7;

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
	public Jeu(Joueur[] joueurs, Affichage vue) {
		this.joueurs = joueurs;
		this.vue=vue;
	}

	/**
	 * Méthode qui permet de débuter le tour d'un joueur
	 * 
	 * @param joueur
	 *            joueur qui débute son tour
	 */
	public void debutTour(Joueur joueur) {
		for (int i = 0; i < this.joueurs[0].getNbCartesPosees(); i++)
			if (this.joueurs[0].getCartesPosees().cartes[i].getEffet()!=null)
			if (this.joueurs[0].getCartesPosees().cartes[i].getEffet().getActivation().compareTo("debut") == 0)
				this.joueurs[0].getCartesPosees().cartes[i].getEffet().appliquerEffet(this.joueurs[0],this.joueurs[1]);
		
		for (int j = 0; j < this.joueurs[1].getNbCartesPosees(); j++)
			if (this.joueurs[1].getCartesPosees().cartes[j].getEffet()!=null)
			if (this.joueurs[1].getCartesPosees().cartes[j].getEffet().getActivation().compareTo("debut") == 0)
				this.joueurs[1].getCartesPosees().cartes[j].getEffet().appliquerEffet(this.joueurs[1],this.joueurs[0]);
		if (joueur.getMain().nbCartes < Jeu.NB_MAX_CARTES_MAIN)
			joueur.piocherCarte();
		joueur.incrementerCurseurDeck();
		if (joueur.getHeros().getNbManaMax() < NB_MANA_MAX)
		joueur.getHeros().incrementerNbManaMax();
		joueur.getHeros().setNbManaCourant(joueur.getHeros().getNbManaMax());		
	}
	
	/**
	 * Methode qui prepare la partie en attribuant un deck et une main à chaque joueur
	 *@return void
	 */
	public void preparerPartie() throws DeckInvalide {
		((JoueurAleatoire) this.joueurs[1]).attribuerDeckAleatoire();
		((JoueurAleatoire) this.joueurs[0]).attribuerDeckAleatoire();
		this.attribuerMainDepart(this.joueurs[0]);
		this.melangerDeck(this.joueurs[0]);
		this.attribuerMainDepart(this.joueurs[1]);
		if (!(this.joueurs[0].getDeck().cartes[NB_CARTES_DECK - 1] instanceof Carte))
			throw new DeckInvalide();
		this.vue.afficherPlateau();
	}
	/**
	 * methode qui permet de commencer la partie
	 * @return void 
	 */
	public void jouer() {
		int indiceJoueurCourant = 0;
		Joueur joueurCourant = this.joueurs[indiceJoueurCourant];
		int compteurTour=1;
		int compteurTourEfectif=0;
		while (!(this.partieFinie())){
			System.out.println("partie finie : "+this.partieFinie());
			joueurCourant = this.joueurs[(indiceJoueurCourant + 1) % 2];
			System.out.println("tour n°"+compteurTour+" du joueur "+joueurCourant.getNumeroJoueur());
			this.debutTour(joueurCourant);
			if(joueurCourant instanceof JoueurAleatoire)
				this.jouerTourBot((JoueurAleatoire)joueurCourant);
			this.finTour(joueurCourant);
			System.out.println("FIN TOUR"+this.toString()+"\n");
			indiceJoueurCourant++;
			compteurTourEfectif++;
			if (compteurTourEfectif%2==0)
				compteurTour++;
		}
		System.out.println("PARTIE FINIE NIGGA");
	}
	/**
	 * Méthode qui définit comment joue l'IA à chaque tour
	 * @param joueurCourant
	 * 			L'IA courante
	 * @return void
	 * @throws PlateauPlein
	 * @throws ManaInsuffisant
	 * @throws CibleInvalide
	 * @throws EstInactif
	 */
	public void jouerTourBot(JoueurAleatoire joueurCourant) {
		while(joueurCourant.peutEncoreJouer())
			if(joueurCourant.peutPoserUneCarte()){
				Carte carteAUtiliser=joueurCourant.choisirCarteAPoser();
				try {
					this.poserCarte(carteAUtiliser, joueurCourant);
				}
				catch (PlateauPlein e1) {
					this.vue.afficherMessageErreur("Plateau Plein");
				}
				catch (ManaInsuffisant e2) {
					this.vue.afficherMessageErreur("Mana Insuffisant");
				}
				}
			else {
					Personnage personnageAttaque = joueurCourant.choisirPersonnageAAttaquer(this.joueurs[joueurCourant.getNumeroJoueur()-1]);
					Carte carteAttaquante=joueurCourant.choisirCarteAttaquante();
				try {
					this.attaquerAvecCarte(carteAttaquante, personnageAttaque, joueurCourant);
				}
				catch (CibleInvalide e1) {
					this.vue.afficherMessageErreur("Cible invalide");
				}
				catch (EstInactif e2) {
					this.vue.afficherMessageErreur("La carte est inactive");
				}
			}
			}
			
	/**
	 * Méthode qui permet au joueur humain de jouer le tour
	 * @return void
	 * @param joueurCourant
	 * 		Le joueur humain courant
	 */

	public void jouerTour(Joueur joueurCourant) {
		Scanner sc= new Scanner(System.in);
		String str=sc.nextLine();
			while(str!="p")
				this.jouerTourIntermediaire(joueurCourant);		
			
	}
	
	/**
	 * Méthode qui permet au joueur d'effectuer des actions pendant le tour
	 * @return void
	 * @param joueurCourant
	 * @throws PlateauPlein
	 * @throws ManaInsuffisant
	 * @throws CibleInvalide
	 * @throws EstInactif
	 */
	public void jouerTourIntermediaire(Joueur joueurCourant) {
		while(this.veutPoser() || 	this.veutAttaquer());
		if (this.veutPoser()){
			Carte carteAPoser=joueurCourant.choisirCarteAPoser();
		try {
					this.poserCarte(carteAPoser, joueurCourant);
				}
				catch (PlateauPlein e1) {
					this.vue.afficherMessageErreur("Plateau Plein");
				}
				catch (ManaInsuffisant e2) {
					this.vue.afficherMessageErreur("Mana Insuffisant");
				}
			}
			else {
				Carte carteAttaquante=joueurCourant.choisirCarteAttaquante();
				Personnage personnageAttaque = joueurCourant.choisirPersonnageAAttaquer(this.joueurs[2-joueurCourant.getNumeroJoueur()]);
				try {
					this.attaquerAvecCarte(carteAttaquante, personnageAttaque, joueurCourant);
				}
				catch (CibleInvalide e1) {
					this.vue.afficherMessageErreur("Cible invalide");
				}
				catch (EstInactif e2) {
					this.vue.afficherMessageErreur("La carte est inactive");
				}
			}
	}

	/**
	 * Méthode vérifiant si la partie est finie ou non 
	 * @return boolean
	 */
	public boolean partieFinie() {
		if ((this.joueurs[0].getHeros().getPointsDeVie() <= 0) || (this.joueurs[1].getHeros().getPointsDeVie() <= 0)){
			return true;}
		return false;
	}

	/**
	 * Méthode renvoyant si le joueur veut poser
	 * @return boolean
	 */
	public boolean veutPoser(){
		if (((AffichageFenetre)this.getVue()).getCarteAPoser()==null)
				return false;
		return true;
	}
	
	/**
	 * Méthode renvoyant si le joueur veut attaquer
	 * @return boolean
	 */
	public boolean veutAttaquer(){
		if (((AffichageFenetre)this.getVue()).getCarteAttaquante()==null)
			return false;
		return true;
	}
	/**
	 * Méthode permettant a un joueur de finir son tour
	 * 
	 * @param joueur
	 *            joueur qui fini son tour
	 */
	public void finTour(Joueur joueur) {
		for (int indiceCarte = 0; indiceCarte < this.joueurs[0].getNbCartesPosees(); indiceCarte++){
		if (this.joueurs[0].getCartesPosees().cartes[indiceCarte].getEffet()!=null)
			if (this.joueurs[0].getCartesPosees().cartes[indiceCarte].getEffet().getActivation().compareTo("fin") == 0)
				this.joueurs[0].getCartesPosees().cartes[indiceCarte].getEffet().appliquerEffet(this.joueurs[0],this.joueurs[1]);
		}
		for (int indiceCarte = 0; indiceCarte < this.joueurs[1].getNbCartesPosees(); indiceCarte++){
			if (this.joueurs[1].getCartesPosees().cartes[indiceCarte].getEffet()!=null)
			if (this.joueurs[1].getCartesPosees().cartes[indiceCarte].getEffet().getActivation().compareTo("fin") == 0){
				this.joueurs[1].getCartesPosees().cartes[indiceCarte].getEffet().appliquerEffet(this.joueurs[1],this.joueurs[0]);
				}
			}
			for (int i = 0; i < joueur.getNbCartesPosees(); i++)
				joueur.getCartesPosees().cartes[i].modeActive();
		this.viderPlateau();
		
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
		if (carte.getCoutEnMana() > joueur.getHeros().getNbManaCourant()){
			throw new ManaInsuffisant();}
		if(carte.getEffet()!=null)
			if (carte.getEffet().getActivation().compareTo("invocation") == 0)
				carte.getEffet().appliquerEffet(joueur,this.joueurs[2-joueur.getNumeroJoueur()]);
		joueur.getCartesPosees().cartes[joueur.getNbCartesPosees()] = carte;
		joueur.getHeros().decrementerNbManaCourant(carte.getCoutEnMana());
		int indexCartePosee=this.trouverIndexCarteDansMain(carte, joueur);
		joueur.getMain().cartes[indexCartePosee]=null;
		joueur.decrementerNbCartesMain();
		joueur.incrementerNbCartesPosees();
		joueur.reOrganiserMain();
		this.viderPlateau();
		joueur.reOrganiserPlateau();
		System.out.println("CARTE : "+carte.toString()+"  POSÉE \n"+this.toString());
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
			joueur.reOrganiserPlateau();
			System.out.println("CARTE : "+carte.toString()+"  ATTAQUE"+personnage.toString()+"  \n"+this.toString());
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
	public boolean cibleViable(Personnage personnageAttaque, Joueur joueur) {
		if (this.existeProvocation(this.joueurs[joueur.getNumeroJoueur()-1])){
		if (!(personnageAttaque instanceof Carte))
			return false;
		if(((Carte) personnageAttaque).getEffet()==null)
			return false;
		if (((Carte) personnageAttaque).getEffet().getNom().compareTo("provocation") != 0)
			return false;
		}
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
	public boolean existeProvocation(Joueur joueur) {
			for (int i = 0; i < joueur.getNbCartesPosees(); i++)
				if(joueur.getCartesPosees().cartes[i].getEffet()!=null)
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
		liste.cartes[10] = new Carte("Aatrox", 4, 5, (new Effet("invocation", "heal", 2, 2)), 5, "Epee des darkin");
		liste.cartes[19] = new Carte("Ahri",4,4,(new Effet("invocation","degat direct",2,2)),4,"Renard a 9 queues");
		liste.cartes[20] = new Carte("Akali",8,8,(new Effet("invocation","degat direct",4,1)),7,"Poing des ombres");
		liste.cartes[21] = new Carte("Alistar",2,2,(new Effet("invocation","heal",2,1)),2,"Minotaure");
		liste.cartes[22] = new Carte("Amumu",4,4,(new Effet("","provocation",0,0)),4,"Momie melancolique");
		liste.cartes[15] = new Carte("Anivia", 5, 5, null, 5, "Cryophenix");
		liste.cartes[23] = new Carte("Annie",1,4,(new Effet("","provocation",0,0)),3,"Enfant des tenebres");
		liste.cartes[9] = new Carte("Ashe", 3, 2, (new Effet("invocation", "degat direct", 1, 7)), 3, "Archere de givre");
		
		liste.cartes[8] = new Carte("Big Fucking glaive", 0, 0, (new Effet("invocation", "buff attaque", 3, 1)), 1, "L'epee des heros");
		liste.cartes[24] = new Carte("Blitzcrank",1,7,(new Effet("","provocation",0,0)),4,"Golem de vapeur");
		liste.cartes[25] = new Carte("Brand",4,5,null,4,"Vengeur flamboyant");
		liste.cartes[26] = new Carte("Braum",3,4,(new Effet ("","provocation",0,0)),3,"Coeur de Freljord");
		
		liste.cartes[27] = new Carte("Caitlyn",5,3,(new Effet("","charge",0,0)),5,"Agent de Piltover");
		liste.cartes[28] = new Carte("Cassiopeia",6,4,null,5,"Etreinte du serpent");
		liste.cartes[29] = new Carte("Cho'Gath",4,4,(new Effet("invocation","piocher",2,0)),4,"Terreur noire");
		liste.cartes[30] = new Carte("Corki",4,5,null,4,"Artilleur téméraire");
		liste.cartes[4] = new Carte("Creuset de michael", 0, 0, (new Effet("invocation", "heal", 2, 1)), 3, "Cloche regeneratrice");
		
		liste.cartes[7] = new Carte("Darius", 5, 6, (new Effet("fin", "degat direct", 1, 7)), 8, "Main de noxus");
		liste.cartes[31] = new Carte("Diana",3,1,(new Effet("","charge",0,0)),3,"Mépris de la lune");
		liste.cartes[32] = new Carte("Dr Mundo",3,3,(new Effet("","provocation",0,0)),2,"Dément de Zaun");
		liste.cartes[33] = new Carte("Draven",6,6,(new Effet("invocation","buff attaque",1,7)),7,"Glorieux exécuteur");

		liste.cartes[34] = new Carte("Elise",4,4,null,4,"Reine araignée");
		liste.cartes[35] = new Carte("Evelynn",4,4,null,4,"Faiseuse de veuves");
		liste.cartes[36] = new Carte("Ezreal",4,2,(new Effet("","charge",0,0)),5,"Explorateur prodigue");
		
		liste.cartes[37] = new Carte("Fiddlesticks",3,2,null,3,"faucheur");
		liste.cartes[38] = new Carte("Fiora",2,3,(new Effet("invocation","piocher",1,0)),3,"sublime bretteuse");
		liste.cartes[39] = new Carte("Fizz",3,3,null,3,"Filou des mers");
		
		liste.cartes[40] = new Carte("Galio",5,7,(new Effet("","provocation",0,0)),6,"Veilleur lugubre");
		liste.cartes[41] = new Carte("Gangplank",3,2,(new Effet("invocation","piocher",2,0)),3,"Fléau des mers");
		liste.cartes[42] = new Carte("Garen",4,5,null,4,"Force de Demacia");
		liste.cartes[43] = new Carte("Gragas",4,4,null,4,"Agitateur");
		liste.cartes[44] = new Carte("Graves",6,5,null,6,"le Hors-la-loi");
		
		liste.cartes[45] = new Carte("Hecarim",3,3,(new Effet("","provocation",0,0)),3,"Ombre de la guerre");
		liste.cartes[46] = new Carte("Heimerdinger",2,1,(new Effet("debut","piocher",1,0)),3,"Inventeur réputé");
		
		liste.cartes[11] = new Carte("Irelia", 4, 4, (new Effet("invocation", "piocher", 1, 4)), 5, "Volonte des lames");
		
		liste.cartes[47] = new Carte("Janna",1,3,(new Effet("fin","heal",2,0)),3,"Avatar de l'air");
		liste.cartes[2] = new Carte("Jarvan IV", 4, 5, null, 4, "Exemple Demacien");
		liste.cartes[48] = new Carte("Jax",4,4,(new Effet("","provocation",0,0)),4,"Maître d'armes");
		liste.cartes[49] = new Carte("Jayce",3,2,(new Effet("invocation","piocher",1,0)),3,"Protecteur du futur");
		liste.cartes[12] = new Carte("Jinx", 2, 2, null, 1, "La gachette folle");
		
		liste.cartes[50] = new Carte("Karma",3,2,(new Effet("fin","buff attaque",1,1)),4,"Sagesse incarnée");
		liste.cartes[51] = new Carte("Karthus",4,4,(new Effet("invocation","buff attaque",3,1)),6,"Liche");
		liste.cartes[52] = new Carte("kassadin",5,4,null,5,"Chasseur du néant");
		liste.cartes[1] = new Carte("Katarina", 5, 5, (new Effet("fin", "degat direct", 2, 7)), 6, "Lame sinistre");
		liste.cartes[53] = new Carte("Kayle",4,4,(new Effet("fin","heal",2,7)),5,"Justicière");
		liste.cartes[54] = new Carte("kennen",4,3,null,3,"Coeur de la tempête");
		liste.cartes[55] = new Carte("kha'zix",5,4,(new Effet("invocation","charge",0,0)),6,"Faucheur du Néant");
		liste.cartes[56] = new Carte("Kog'Maw",4,4,null,4,"Gueule des abysses");
		
		liste.cartes[57] = new Carte("LeBlanc",8,8,(new Effet("debut","degat direct",4,1)),7,"Manipulatrice");
		liste.cartes[58] = new Carte("Lee sin",4,4,null,4,"Moine Aveugle");
		liste.cartes[17] = new Carte("Leona",5,5,(new Effet("invocation","buff vie",3,1)),6,"Aube radieuse");
		liste.cartes[59] = new Carte("Lissandra",2,1,(new Effet("debut","piocher",1,0)),1,"Sorcière de glace");
		liste.cartes[60] = new Carte("lucian",3,3,null,3,"le Purificateur");
		liste.cartes[61] = new Carte("Lulu",2,1,(new Effet("invocation","buff vie",2,1)),2,"Sorcière féérique");
		liste.cartes[62] = new Carte("lux",4,3,(new Effet("fin","degat direct",3,1)),4,"Dame de lumiere");

		liste.cartes[63] = new Carte("Malphite",4,5,null,4,"Éclat du Monolithe");
		liste.cartes[6] = new Carte("Malzahar", 0, 3, (new Effet("fin", "piocher", 1, 0)), 3, "Le prophete du vide");
		liste.cartes[64] = new Carte("Maokai",4,5,null,4,"Tréant torturé");
		liste.cartes[65] = new Carte("MasterYi",6,6,(new Effet("","charge",0,0)),6," Fine lame Wuju");
		liste.cartes[66] = new Carte("MissFortune",5,5,null,5,"Chasseur de primes");
		liste.cartes[67] = new Carte("Mordekaiser",2,3,(new Effet("fin","piocher",1,0)),4,"Maître du métal");
		liste.cartes[68] = new Carte("Morgana",2,7,(new Effet("invocation","buff attaque",2,1)),5,"Ange déchu");


		liste.cartes[69] = new Carte("Nami",1,4,(new Effet("fin","heal",2,7)),4,"Aquamancienne");
		liste.cartes[70] = new Carte("Nasus",5,5,(new Effet("debut","degat direct",2,7)),6,"Gardien des sables");
		liste.cartes[71] = new Carte("Nautilus",4,5,null,4,"Titan des profondeurs");
		liste.cartes[72] = new Carte("Nidalee",4,3,(new Effet("invocation","buff vie",2,1)),5,"Chasseresse Bestiale");
		liste.cartes[73] = new Carte("Nocturne",4,5,null,4,"Eternel cauchemar");
		liste.cartes[74] = new Carte("Nunu",4,5,null,4,"Dompteur de yéti");
		
		liste.cartes[75] = new Carte("Olaf",4,2,(new Effet("","charge",0,0)),4,"Berserker");
		liste.cartes[76] = new Carte("Orianna",4,4,(new Effet("invocation","piocher",2,0)),4,"Demoiselle mécanique");

		liste.cartes[77] = new Carte("Pantheon",4,5,null,4,"Artisan de la guerre");
		liste.cartes[16] = new Carte("Poppy", 8, 8, null, 8, "Ambassadeur de fer");
		
		liste.cartes[78] = new Carte("Quinn",3,3,(new Effet ("","charge",0,0)),3,"Ailes de Demacia");
		
		liste.cartes[79] = new Carte("Rammus",3,3,(new Effet("","provocation",0,0)),4,"Tatou blindé");
		liste.cartes[80] = new Carte("Renekton",6,7,(new Effet("","provocation",0,0)),6,"Dévoreur des sables");
		liste.cartes[81] = new Carte("Rengar",4,5,null,4,"Fier traqueur");
		liste.cartes[82] = new Carte("Riven",5,4,null,4,"Exilée brisée");
		liste.cartes[83] = new Carte("Rumble",6,6,null,6,"Menace Mécanisée");
		liste.cartes[84] = new Carte("Ryze",5,8,(new Effet("","provocation",0,0)),7,"Mage renégat");
				
		liste.cartes[85] = new Carte("Sejuani",4,5,null,4,"Colére de l'hiver");
		liste.cartes[14] = new Carte("Shen", 6, 6, (new Effet("invocation", "buff vie", 3, 1)), 6, "Oeil du crepuscule");
		liste.cartes[86] = new Carte("Shyvana",7,7,(new Effet("","provocation",0,0)),7,"Demi dragon");
		liste.cartes[87] = new Carte("Singed",4,5,null,4,"Chimiste fou");
		liste.cartes[88] = new Carte("sion",12,12,(new Effet("","charge",0,0)),6,"Abomubation mort-vivante");
		liste.cartes[89] = new Carte("Sivir",6,5,null,6,"Vierge martiale");
		liste.cartes[90] = new Carte("Skarner",4,5,null,4,"Gardien de cristal");
		liste.cartes[18] = new Carte("Sona",3,3, (new Effet("fin","heal",2,7)),4,"Virtuose de la harpe");
		liste.cartes[91] = new Carte("Soraka",12,12,(new Effet("debut","heal",4,7)),6,"Enfant des etoiles");
		liste.cartes[92] = new Carte("Swain",6,6,(new Effet("","provocation",0,0)),6,"Maitre tacticien");
		liste.cartes[5] = new Carte("Syndra", 6, 6, (new Effet("invocation", "piocher", 2, 0)), 7, "Souveraine obscure");
		
		liste.cartes[93] = new Carte("Talon",5,2,(new Effet("invocation","degat direct",2,1)),4,"Lame des ténebres");
		liste.cartes[94] = new Carte("Taric",4,2,(new Effet("fin","heal",2,1)),4,"Chevalier au gemmes");
		liste.cartes[0] = new Carte("Teemo", 2, 2, (new Effet("invocation", "degat direct", 3, 3)), 5, "Petite peste");
		liste.cartes[13] = new Carte("Thresh", 3, 3, (new Effet("invocation", "heal", 2, 1)), 3, "Garde aux chaines");
		liste.cartes[95] = new Carte("Tristana",5,3,(new Effet("","charge",0,0)),5,"Cannonière Megling");
		liste.cartes[97] = new Carte("Trundle",7,7,(new Effet("","provocation",0,0)),7,"Troll maudit");
		liste.cartes[98] = new Carte("Tryndamere",12,12,(new Effet("","provocatio",0,0)),6,"Roi barbare");
		liste.cartes[99] = new Carte("Twisted Fate",3,3,(new Effet("debut","piocher",2,0)),3,"Maitres des cartes");
		liste.cartes[100] = new Carte("Twitch",12,12,(new Effet("fin","degat direct",5,1)),6,"Sémeur de peste");
		
		liste.cartes[101] = new Carte("Udyr",4,5,null,4,"Esprit annimal");
		liste.cartes[102] = new Carte("urgot",3,3,null,3,"Fierté du bourreau");

		liste.cartes[103] = new Carte("Varus",4,4,null,4,"la Fléche de la vengeance");
		liste.cartes[104] = new Carte("Vayne",7,6,null,6,"Chasseur nocturne");
		liste.cartes[105] = new Carte("Veigar",8,3,null,5,"Seigneur des maléfices");
		liste.cartes[106] = new Carte("Vel'Koz",4,4,null,4,"Oeil du néant");
		liste.cartes[107] = new Carte("Vi",8,8,(new Effet("","provocation",0,0)),8,"Gogne de Piltover");
		liste.cartes[108] = new Carte("Viktor",5,5,null,5,"Héraut des machines");
		liste.cartes[109] = new Carte("Vladimir",5,6,null,5,"Saigneur Pourpre");
		liste.cartes[110] = new Carte("Volibear",5,6,(new Effet("","provocation",0,0)),5,"Grondement du tonnere");
		
		liste.cartes[111] = new Carte("Warwick",8,8,(new Effet("","provocation",0,0)),8,"Traque sang");
		liste.cartes[112] = new Carte("Wukong",5,5,null,5,"Roi des singes");
		
		liste.cartes[113] = new Carte("Xerath",4,5,null,5,"Mage supréme");
		liste.cartes[114] = new Carte("Xin Zhao",4,5,null,4,"Sénéchat de Démacia");
		
		liste.cartes[3] = new Carte("Yasuo", 2, 3, (new Effet("invocation", "degat direct", 2, 1)), 3, "Le disgracie");
		liste.cartes[115] = new Carte("Yorick",4,4,(new Effet("invocation","buf vie",2,1)),4,"Fossoyeur");
		
		liste.cartes[117] = new Carte("Zac",4,5,null,4,"Arme secrète");
		liste.cartes[118] = new Carte("Zed",6,6,null,6,"Maitre des ombres");
		liste.cartes[119] = new Carte("Ziggs",7,4,null,6,"Expert des Hexplosifs");
		liste.cartes[120] = new Carte("Zilean",12,12,(new Effet("debut","buf vie",4,7)),6,"Gardien du temps");
		liste.cartes[121] = new Carte("Zyra",4,5,(new Effet("invocation","buff attaque",2,7)),6,"Dame aux ronces");
		return liste;		
		
	}

	/**
	 * 
	 * Methode qui permet d'enlever du plateau les cartes n'ayant plus de vie.
	 */
	public void viderPlateau() {
		for (int i = 0; i < this.joueurs[0].getNbCartesPosees(); i++){
			if (this.joueurs[0].getCartesPosees().cartes[i].getPointsDeVie() <= 0){
						this.jeterCarte(this.joueurs[0].getCartesPosees().cartes[i], this.joueurs[0]);
						this.joueurs[0].getCartesPosees().cartes[i] = null;
						this.joueurs[0].decrementerNbCartesPosees();
						this.joueurs[0].reOrganiserPlateau();
					}
		}
				
		
		for (int j = 0; j < this.joueurs[1].getNbCartesPosees(); j++) {
			if (this.joueurs[1].getCartesPosees().cartes[j].getPointsDeVie() <= 0){
						this.jeterCarte(this.joueurs[1].getCartesPosees().cartes[j], this.joueurs[1]);
						this.joueurs[1].getCartesPosees().cartes[j] = null;
						this.joueurs[1].decrementerNbCartesPosees();
						this.joueurs[1].reOrganiserPlateau();
					}
		}
		}
	
	/**
	 * méthode qui mélange le deck d'un joueur, donné en paramètre, de manière aléatoire
	 * @param joueur
	 */

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
	/**
	 * Méthode qui attribue une main de départ au joueur en faisant appelle à piocherCarte()
	 * @return void 
	 * @param joueur
	 * 		Le joueur à qui on attribue la main
	 */
	public void attribuerMainDepart(Joueur joueur) {
		joueur.piocherCarte();
		joueur.piocherCarte();
		joueur.piocherCarte();
	}

	/**
	 * Envoi la carte passer en paramètre dans le cimetière
	 * @param carte
	 * 		La carte à envoyer dans le cimetière
	 * @param joueur
	 * 		Le joueur à qui appartient la carte
	 */
	public void jeterCarte(Carte carte, Joueur joueur) {
		if (carte.getEffet()!=null)
		if (carte.effet.getActivation().compareTo("mort") == 0)
			carte.effet.appliquerEffet(joueur,this.joueurs[2-joueur.getNumeroJoueur()]);
		joueur.getCimetiere().setCimetiere(carte);
	}
	/**
	 * méthode qui converti les actions en ascii pour l'affichage console
	 * @return String
	 */
	public String toString(){
		String partie="";
		partie+="point de vie héros :"+this.joueurs[1].getHeros().getPointsDeVie()+"\n";	
		partie+=" peut jouer : "+((JoueurAleatoire)this.joueurs[1]).peutEncoreJouer()+"\n";
		partie+="mana max :"+this.joueurs[1].getHeros().getNbManaMax()+"\n";
		partie+="mana courant :"+this.joueurs[1].getHeros().getNbManaCourant()+"\n";	
		for (int indiceCarte=0;indiceCarte<this.joueurs[1].getMain().getNbCartes();indiceCarte++)
				partie+=this.joueurs[1].getMain().cartes[indiceCarte].toString();
		partie+="\n-------------------\n"; 
		for (int indiceCarte=0;indiceCarte < this.joueurs[1].getCartesPosees().getNbCartes();indiceCarte++)
			partie+=this.joueurs[1].getCartesPosees().cartes[indiceCarte].toString();
		partie+="\n-------------------\n";
		for (int indiceCarte=0;indiceCarte < this.joueurs[0].getCartesPosees().getNbCartes();indiceCarte++)
			partie+=this.joueurs[0].getCartesPosees().cartes[indiceCarte].toString();
		partie+="\n-------------------\n";
		for (int indiceCarte=0;indiceCarte < this.joueurs[0].getMain().getNbCartes();indiceCarte++){
			partie+=this.joueurs[0].getMain().cartes[indiceCarte].toString();
		}
		
		partie+="\n point de vie héros :"+this.joueurs[0].getHeros().getPointsDeVie()+"\n";
		partie+=" peut jouer : "+((JoueurAleatoire)this.joueurs[0]).peutEncoreJouer()+"\n";
		partie+="mana max :"+this.joueurs[0].getHeros().getNbManaMax()+"\n";
		partie+="mana courant :"+this.joueurs[0].getHeros().getNbManaCourant()+"\n \n \n";
		return partie;
	}
	/**
	 * méthode renvoi un affichage au jeu
	 * @return Affichage
	 */
	public Affichage getVue(){
		return this.vue;
	}
	/**
	 * Méthode qui cherche l'index d'une carte dans la main
	 * @param carte
	 * @param joueur
	 * @return int
	 */
	public int trouverIndexCarteDansMain(Carte carte, Joueur joueur){
		int indiceCarteCherchee=0;
		while(joueur.getMain().cartes[indiceCarteCherchee]!=carte)
			indiceCarteCherchee++;
		return indiceCarteCherchee;
	}
	
}
