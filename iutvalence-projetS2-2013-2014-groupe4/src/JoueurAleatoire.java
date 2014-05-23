import java.util.Random;

public class JoueurAleatoire extends Joueur {
	
	
	
	
	public JoueurAleatoire(int numeroDuJoueur, Heros heros) {
		super(numeroDuJoueur, heros);
	}



	public Carte choisirCarte(ListeDeCartes liste) {
		return null;

	}
	
	public void attribuerDeckAleatoire(ListeDeCartes liste) {
		for (int nbCartesDeck=0; nbCartesDeck < Jeu.NB_CARTES_DECK; nbCartesDeck++){
			this.setDeck(choisirCarteDeck());
			}
	}


	public Carte choisirCarteDeck() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(Jeu.NB_CARTES_DECK);
			return Jeu.LISTE_CARTE_GENERALE.cartes[indexCarteChoisie];
	}


	public Position choisirCarteAUtiliser() {

		return null;
	}




	public Personnage choisirCarteAAttaquer(ListeDeCartes liste) {
		
		return null;
	}



	@Override
	public Personnage choisirCarteAAttaquer() {

		return null;
	}
}
