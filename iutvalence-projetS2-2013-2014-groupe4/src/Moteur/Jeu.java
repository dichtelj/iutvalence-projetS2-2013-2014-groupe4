package Moteur;
import java.util.Random;
import java.util.Scanner;

import IHM.Affichage;

/**
 * Definition d'une partie de Battle for Demacia
 */
public class Jeu implements Controleur {

	public final static int NB_CARTES_DECK = 60;

	public static final int NB_MAX_CARTES_MAIN = 10;
	
	public static final int NB_MANA_MAX = 15;

	public static final ListeDeCartes LISTE_CARTE_GENERALE = Jeu.creerListeDeCartesGenerale();
	
	private Affichage vue;
	
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
	

	public void preparerPartie() throws DeckInvalide {
		((JoueurAleatoire) this.joueurs[1]).attribuerDeckAleatoire();
		((JoueurAleatoire) this.joueurs[0]).attribuerDeckAleatoire();
		this.attribuerMainDepart(this.joueurs[0]);
		this.melangerDeck(this.joueurs[0]);
		this.attribuerMainDepart(this.joueurs[1]);
		if (!(this.joueurs[0].getDeck().cartes[NB_CARTES_DECK - 1] instanceof Carte))
			throw new DeckInvalide();
		System.out.println(this.toString());
	}

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
			System.out.println("FIN TOUR"+this.toString());
			indiceJoueurCourant++;
			compteurTourEfectif++;
			if (compteurTourEfectif%2==0)
				compteurTour++;
		}
		System.out.println("PARTIE FINIE NIGGA");
	}

	public void jouerTourBot(JoueurAleatoire joueurCourant) {
		while(joueurCourant.peutEncoreJouer())
			if(joueurCourant.peutPoserUneCarte()){
				Carte carteAUtiliser=joueurCourant.carteDePlusHauteValeurJouable();
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
				for (int indiceCarte=0; indiceCarte<joueurCourant.getNbCartesPosees();indiceCarte++){
					Personnage personnageAttaque = joueurCourant.choisirPersonnageAAttaquer(this.joueurs[joueurCourant.getNumeroJoueur()-1]);
					Carte carteAttaquante=joueurCourant.choisirCarteAttaquanteAleatoire();
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
			}


	public void jouerTour(Joueur joueurCourant) {
		Scanner sc= new Scanner(System.in);
		String str=sc.nextLine();
			while(str!="p")
				this.jouerTourIntermediaire(joueurCourant);		
			
	}
	

	public void jouerTourIntermediaire(Joueur joueurCourant) {
		Position carteAUtiliser = joueurCourant.choisirCarteAUtiliser();
			if (joueurCourant.getNumeroJoueur() == 1) {
				try {
					this.poserCarte(carteAUtiliser.getListe().cartes[carteAUtiliser.getIndex()], joueurCourant);
				}
				catch (PlateauPlein e1) {
					this.vue.afficherMessageErreur("Plateau Plein");
				}
				catch (ManaInsuffisant e2) {
					this.vue.afficherMessageErreur("Mana Insuffisant");
				}
			}
			else {
				Personnage personnageAttaque = joueurCourant.choisirPersonnageAAttaquer(this.joueurs[2-joueurCourant.getNumeroJoueur()]);
				try {
					this.attaquerAvecCarte(carteAUtiliser.getListe().cartes[carteAUtiliser.getIndex()], personnageAttaque, joueurCourant);
				}
				catch (CibleInvalide e1) {
					this.vue.afficherMessageErreur("Cible invalide");
				}
				catch (EstInactif e2) {
					this.vue.afficherMessageErreur("La carte est inactive");
				}
			}
	}


	public boolean partieFinie() {
		if ((this.joueurs[0].getHeros().getPointsDeVie() > 0) || (this.joueurs[1].getHeros().getPointsDeVie() > 0)){
			return false;}
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
			System.out.println("CARTE : "+carte.toString()+"  ATTAQUANTE \n"+this.toString());
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

		
		liste.cartes[6] = new Carte("Malzahar", 0, 3, (new Effet("fin", "piocher", 1, 0)), 3, "Le prophete du vide");
		
		liste.cartes[16] = new Carte("Poppy", 8, 8, null, 8, "Ambassadeur de fer");
		
		liste.cartes[14] = new Carte("Shen", 6, 6, (new Effet("invocation", "buff vie", 3, 1)), 6, "Oeil du crepuscule");
		liste.cartes[18] = new Carte("Sona",3,3, (new Effet("fin","heal",2,7)),4,"Virtuose de la harpe");
		liste.cartes[5] = new Carte("Syndra", 6, 6, (new Effet("invocation", "piocher", 2, 0)), 7, "Souveraine obscure");
		
		liste.cartes[0] = new Carte("Teemo", 2, 2, (new Effet("invocation", "degat direct", 3, 3)), 5, "Petite peste");
		liste.cartes[13] = new Carte("Thresh", 3, 3, (new Effet("invocation", "heal", 2, 1)), 3, "Garde aux chaines");
				
		liste.cartes[3] = new Carte("Yasuo", 2, 3, (new Effet("invocation", "degat direct", 2, 1)), 3, "Le disgracie");
		
		
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
		if (carte.getEffet()!=null)
		if (carte.effet.getActivation().compareTo("mort") == 0)
			carte.effet.appliquerEffet(joueur,this.joueurs[2-joueur.getNumeroJoueur()]);
		joueur.getCimetiere().setCimetiere(carte);
	}
	
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
	
	public Affichage getVue(){
		return this.vue;
	}
	
	public int trouverIndexCarteDansMain(Carte carte, Joueur joueur){
		int indiceCarteCherchee=0;
		while(joueur.getMain().cartes[indiceCarteCherchee]!=carte)
			indiceCarteCherchee++;
		return indiceCarteCherchee;
	}
	
}
