/**
 * Definition d'une partie de Battle for Demacia
 */
public class Partie {

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
	 */
	public Partie(Joueur[] joueurs) {
		
		this.joueurs= joueurs;
		this.plateau= new Plateau();
	}
	
	public void debutTour(Joueur joueur){
		if (joueur.getMain().nbCartes == joueur.getMain().nbCartesMax)
			joueur.incrementerCurseurDeck();
		else joueur.piocherCarte();
	}
	
	public void finTour(Joueur joueur){
		for (int i=0;i<joueur.getCurseurPlateau();i++){
			if (joueur==this.joueurs[0])
				this.plateau.getCartesJoueur1().cartes[i].modeActive();
			if (joueur==this.joueurs[1])
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
			carte.infligerDegats(personnage);
			carte.modeInactive();
		}
		if (action.compareTo("invoquer") == 0) {
			this.poserCarte(carte, joueur);
		}
		return "";

	}
}
