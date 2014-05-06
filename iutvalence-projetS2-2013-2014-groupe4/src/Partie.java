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
}
