package Moteur;
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
			return Jeu.LISTE_CARTE_GENERALE.cartes[indexCarteChoisie];
	}


	public Position choisirCarteAUtiliser() {
		return null;
	}
	
	public Carte carteDePlusHauteValeurJouable(){
		Carte carteChoisie=new Carte(null, 0, 0, null, 0, null);
		for (int indiceCarte=0;indiceCarte<this.getMain().nbCartes;indiceCarte++)
			if (this.getMain().cartes[indiceCarte].getCoutEnMana() > carteChoisie.getCoutEnMana() && this.getMain().cartes[indiceCarte].getCoutEnMana() <=this.getHeros().getNbManaCourant())
				carteChoisie=this.getMain().cartes[indiceCarte];
		return carteChoisie;
	}
	
	public boolean peutEncoreJouer() {
		if ((this.peutPoserUneCarte()) || (this.peutEncoreAttaquer())){
			return true;}
		return false;

	}
	
	public boolean peutPoserUneCarte(){
		for (int indiceCarte = 0; indiceCarte < this.getNbCartesMain(); indiceCarte++)
			if (this.getMain().cartes[indiceCarte].getCoutEnMana() <= this.getHeros().getNbManaCourant())
				return true;
		return false;		
	}
	
	
	public boolean peutEncoreAttaquer(){
		for (int indiceCarte = 0; indiceCarte < this.getNbCartesPosees(); indiceCarte++)
			if (this.getCartesPosees().cartes[indiceCarte].estInactif() == false)
				return true;
	return false;
	}

	@Override
	public Personnage choisirPersonnageAAttaquer(Joueur joueurAdverse) {
		Random generateurNombreAleatoire = new Random();
		if (joueurAdverse.getNbCartesPosees()==0)
			return joueurAdverse.getHeros();
		int indexPersonnageAAttaque = generateurNombreAleatoire.nextInt(joueurAdverse.getNbCartesPosees() + 1);
			if ((indexPersonnageAAttaque) == joueurAdverse.getNbCartesPosees())
				return joueurAdverse.getHeros();
			return joueurAdverse.getCartesPosees().cartes[indexPersonnageAAttaque];
	}

	public Carte choisirCarteAttaquanteAleatoire() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(this.getNbCartesPosees());
			return this.getCartesPosees().cartes[indexCarteChoisie];
	}

	public Carte choisirCarteABuffer() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(this.getNbCartesPosees());
			return this.getCartesPosees().cartes[indexCarteChoisie];
	}
	
	
}
