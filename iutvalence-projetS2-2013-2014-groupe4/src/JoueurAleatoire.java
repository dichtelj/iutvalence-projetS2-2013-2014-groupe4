import java.util.Random;

public class JoueurAleatoire extends Joueur {
		
	
	
	public JoueurAleatoire(int numeroDuJoueur, String nomHeros) {
		super(numeroDuJoueur, nomHeros);
	}
	
	public void attribuerDeckAleatoire() {
		for (int nbCartesDeck=0; nbCartesDeck < Jeu.NB_CARTES_DECK; nbCartesDeck++){
			this.setDeck(choisirCarteDeck());
			}
	}


	public Carte choisirCarteDeck() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(63);
		System.out.println(Jeu.LISTE_CARTE_GENERALE.cartes[indexCarteChoisie].toString());
			return Jeu.LISTE_CARTE_GENERALE.cartes[indexCarteChoisie];
	}


	public Position choisirCarteAUtiliser() {
		
		return null;
	}



	public Personnage choisirPersonnageAAttaquer()
	{
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(Jeu.NB_CARTES_MAX_POSEES);
			return this.getCartesPosees().cartes[indexCarteChoisie];
	}
	
	
	public Carte carteDePlusHauteValeur(){
		Carte carteChoisie=this.getMain().cartes[0];
		for (int indiceCarte=1;indiceCarte<this.getMain().nbCartes;indiceCarte++)
			if (this.getMain().cartes[indiceCarte].getCoutEnMana()<carteChoisie.getCoutEnMana())
				carteChoisie=this.getMain().cartes[indiceCarte];
		return carteChoisie;
	}
	
	public boolean peutEncoreJouer() {
		if ((this.peutPoserUneCarte()) || (this.peutEncoreAttaquer()))
			return true;
		return false;

	}
	
	public boolean peutPoserUneCarte(){
		for (int indiceCarte = 0; indiceCarte < this.getNbCartesMain(); indiceCarte++)
			if (this.getMain().cartes[indiceCarte].getCoutEnMana() < this.getHeros().getNbManaCourant())
				return true;
		return false;		
	}
	
	
	public boolean peutEncoreAttaquer(){
		for (int indiceCarte = 0; indiceCarte < this.getNbCartesPlateau(); indiceCarte++)
			if (this.getCartesPosees().cartes[indiceCarte].estInactif() == false)
				return true;
	return false;
	}

	@Override
	public Personnage choisirPersonnageAAttaquer(Joueur joueurAdverse) {
		Random generateurNombreAleatoire = new Random();
		int indexPersonnageAAttaque = generateurNombreAleatoire.nextInt(joueurAdverse.getNbCartesPlateau() + 1);
			if ((indexPersonnageAAttaque) == joueurAdverse.getNbCartesPlateau())
				return joueurAdverse.getHeros();
			return joueurAdverse.getCartesPosees().cartes[indexPersonnageAAttaque];
	}

	public Carte choisirCarteAttaqueeAleatoire() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(Jeu.NB_CARTES_MAX_POSEES);
			return this.getCartesPosees().cartes[indexCarteChoisie];
	}

	@Override
	public Carte choisirCarteABuffer() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(this.getNbCartesPlateau());
			return this.getCartesPosees().cartes[indexCarteChoisie];
	}
	
	
}
