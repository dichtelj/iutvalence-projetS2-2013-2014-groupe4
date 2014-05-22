import java.util.Random;

public class JoueurAleatoire extends Joueur {
	
	
	
	
	public JoueurAleatoire(int numeroDuJoueur, Heros heros) {
		super(numeroDuJoueur, heros);
	}



	public Carte choisirCarte(ListeDeCartes liste) {
		return null;

	}
	
	public void attribuerDeckAleatoire(ListeDeCartes liste) {
		Random generateurDeNombresAleatoires= new Random();
		for (int nbCartesDeck=0; nbCartesDeck < Jeu.NB_CARTES_DECK; nbCartesDeck++){
			int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(Jeu.NB_CARTES_DECK);
			this.setDeck(liste.cartes[indexCarteChoisie]);
			}
		
	}
}
