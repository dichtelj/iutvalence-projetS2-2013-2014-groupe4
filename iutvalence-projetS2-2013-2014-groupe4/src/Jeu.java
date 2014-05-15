/**
 * Definition d'une partie de Battle for Demacia
 */
public class Jeu {

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
		if (joueur.getMain().nbCartes == joueur.getMain().nbCartesMax)
			joueur.incrementerCurseurDeck();
		else joueur.piocherCarte();
	}
	
	/**
	 * Méthode permettant a un joueur de finir son tour
	 * @param joueur
	 * joueur qui fini son tour
	 */
	public void finTour(Joueur joueur){
			if (joueur==this.joueurs[0]){
				for(int i=0;i<this.plateau.getNbCartesJoueur1();i++)
					this.plateau.getCartesJoueur1().cartes[i].modeActive();
			}
			if (joueur==this.joueurs[1]){
				for(int i=0;i<this.plateau.getNbCartesJoueur2();i++)
					this.plateau.getCartesJoueur2().cartes[i].modeActive();
		}
	}
	
	public String poserCarte(Carte carte, Joueur joueur) {
		if (this.plateau.estPlein(joueur))
			return "plateau plein";
		if (carte.getEffet().getActivation().compareTo("invocation")==0)
			carte.getEffet().appliquerEffet(this.plateau, joueur);
		if (joueur.getNumeroJoueur()==1)
			this.plateau.getCartesJoueur1().cartes[joueur.getCurseurPlateau()]=carte;
		else this.plateau.getCartesJoueur2().cartes[joueur.getCurseurPlateau()]=carte;
		return "";
	}
	
	
	/**
	 * Permet d'utiliser la carte (invoquer ou attaquer)
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

	private boolean cibleViable(Carte carte, Personnage personnage,Joueur joueur) {
		if (this.existeProvocation(joueur))
			if (!(personnage instanceof Carte))
				return false;
			if (((Carte)personnage).getEffet().getNom().compareTo("provocation")!=0)
				return false;
		return true;
	}

	private boolean existeProvocation(Joueur joueur) {
		if (joueur==this.joueurs[0]){
			for (int i=0; i<this.joueurs[1].getCurseurPlateau();i++)
				if (this.plateau.getCartesJoueur2().cartes[i].getEffet().getNom().compareTo("provocation")==0)
					return true;
			return false;}
		else{
			for (int i=0; i<this.joueurs[0].getCurseurPlateau();i++)
				if (this.plateau.getCartesJoueur1().cartes[i].getEffet().getNom().compareTo("provocation")==0)
					return true;
			return false;}
	}

	public Joueur[] getJoueurs() {
		return this.joueurs;
	}
	
	public void construireDeck(ListeDeCartes liste){
		int compteurCarte=0;
		while (compteurCarte < 60)
		{
			Carte carteChoisie=this.joueurs[0].choisirCarte(liste);
			this.joueurs[0].setDeck(carteChoisie);
			
		}
			
	}
}
