package Moteur;
import java.util.Random;

public class JoueurAleatoire extends Joueur {
		
	
	/**
	 * permet de créer un joueur aléatoire ( ou IA )
	 * @param numeroDuJoueur
	 * @param nomHeros
	 */
	public JoueurAleatoire(int numeroDuJoueur, String nomHeros) {
		super(numeroDuJoueur, nomHeros);
	}
	/**
	 * Attribue à l'IA un deck aleatoire 
	 */
	public void attribuerDeckAleatoire() {
		for (int nbCartesDeck=0; nbCartesDeck < Jeu.NB_CARTES_DECK; nbCartesDeck++){
			this.setDeck(this.choisirCarteDeck());
			}
	}

	/**
	 * Choisi des cartes aléatoirement à attribuer au deck parmi la liste des cartes du jeu
	 * @return Carte
	 */
	public Carte choisirCarteDeck() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(122);
			while (this.estDansDeck(Jeu.LISTE_CARTE_GENERALE.cartes[indexCarteChoisie]))
					indexCarteChoisie=generateurDeNombresAleatoires.nextInt(122);
			return Jeu.LISTE_CARTE_GENERALE.cartes[indexCarteChoisie];
	}

	/**
	 * Choisi des cartes aléatoirement à attribuer au deck parmi la liste des cartes du jeu
	 * @return Carte
	 */
	private boolean estDansDeck(Carte carte) {
		for (int indiceCarte=this.getCurseurDeck(); indiceCarte < Jeu.NB_CARTES_DECK; indiceCarte++)
			if (this.getDeck().cartes[this.getCurseurDeck()]==carte)
				return true;
		return false;
	}

	public Position choisirCarteAUtiliser() {
		return null;
	}
	/**
	 * Méthode qui renvoi la carte choisi à être posée
	 * @return Carte
	 */
	public Carte choisirCarteAPoser(){
		Carte carteChoisie=new Carte(null, 0, 0, null, 0, null);
		for (int indiceCarte=0;indiceCarte<this.getMain().nbCartes;indiceCarte++)
			if (this.getMain().cartes[indiceCarte].getCoutEnMana() > carteChoisie.getCoutEnMana() && this.getMain().cartes[indiceCarte].getCoutEnMana() <=this.getHeros().getNbManaCourant())
				carteChoisie=this.getMain().cartes[indiceCarte];
		return carteChoisie;
	}
	/**
	 * Méthode vérifiant s'il est possible de jouer encore ou non
	 * @return boolean
	 */
	public boolean peutEncoreJouer() {
		if ((this.peutPoserUneCarte()) || (this.peutEncoreAttaquer())){
			return true;}
		return false;

	}
	/**
	 * Méthode vérifiant s'il est possible de poser encore une carte ou non
	 * @return boolean
	 */
	public boolean peutPoserUneCarte(){
		if(this.getNbCartesPosees() < Jeu.NB_CARTES_MAX_POSEES)
			for (int indiceCarte = 0; indiceCarte < this.getNbCartesMain(); indiceCarte++){
				if (this.getMain().cartes[indiceCarte].getCoutEnMana() <= this.getHeros().getNbManaCourant())
					return true;}
		return false;		
	}
	
	/**
	 * Méthode vérifiant s'il est possible d'attaquer à nouveau ou non
	 * @return boolean
	 */
	public boolean peutEncoreAttaquer(){
		for (int indiceCarte = 0; indiceCarte < this.getNbCartesPosees(); indiceCarte++){
			if (this.getCartesPosees().cartes[indiceCarte].estInactif() == false)
				return true;}
	return false;
	}
	/**
	 * Méthode qui définit le personnage à attaquer de manière aléatoire
	 * @return Personnage
	 * @param joueurAdverse
	 */
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
	/**
	 * Méthode qui définit la carte qui attaque de manière aléatoire
	 * @return Carte
	 */
	public Carte choisirCarteAttaquante() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(this.getNbCartesPosees());
		while (this.getCartesPosees().cartes[indexCarteChoisie].estInactif()){
			indexCarteChoisie=generateurDeNombresAleatoires.nextInt(this.getNbCartesPosees());}
		return this.getCartesPosees().cartes[indexCarteChoisie];
	}
	/**
	 * Méthode qui définit la carte à buffer dans manière aleatoire
	 * @return Carte
	 */
	public Carte choisirCarteABuffer() {
		Random generateurDeNombresAleatoires= new Random();
		int indexCarteChoisie=generateurDeNombresAleatoires.nextInt(this.getNbCartesPosees());
			return this.getCartesPosees().cartes[indexCarteChoisie];
	}
	
	
}
