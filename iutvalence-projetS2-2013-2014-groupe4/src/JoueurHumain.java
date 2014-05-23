/**
 * Caractérise un joueur humain
 *
 */
public class JoueurHumain extends Joueur {

	public JoueurHumain(int numeroDuJoueur, Heros heros) {
		super(numeroDuJoueur, heros);
	}

	
	public Carte choisirCarte(ListeDeCartes liste) {

		return null;
	}


	@Override
	public Carte choisirCarteDeck() {

		return null;
	}


	@Override
	public Carte choisirCarteAUtiliser() {

		return null;
	}


	@Override
	public Personnage choisirCarteAAttaquer() {

		return null;
	}
}
