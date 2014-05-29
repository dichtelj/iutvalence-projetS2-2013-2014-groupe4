package Moteur;
/**
 * @author antoine 
 * Classe représentant le cimetière de chaque joueur
 */
public class Cimetiere extends ListeDeCartes {

	/**
	 * le cimetière contient 0 cartes a sa création et peut contenir au maximum
	 * nbCartesMax cartes
	 * @param nbCartesMax 
	 * Nombre de cartes maximale du cimetiere
	 */
	public Cimetiere()
	{
		super(Jeu.NB_CARTES_DECK);
		this.cartes = new Carte[Jeu.NB_CARTES_DECK];
		this.nbCartes = 0;
	}	


	/**
	 * méthode qui affiche au joueur son cimetière
	 */
	public void afficherCartes() {

	}

	/**
	 * Renvoi vrai si le cimetiere est vide
	 * @return boolean
	 */
	public boolean estVide() {
		if (this.nbCartes == 0)
			return true;
		return false;
	}

	//TODO Faire le toString
	
	/**
	 * Implemente le cas d'utilisation voir cimetiere
	 * @return String
	 */
	public boolean voirCimetiere() {
		if (this.estVide())
			return true;
		this.afficherCartes();
		this.finVoirCimetiere();
		return false;

	}

	public void finVoirCimetiere() {

	}

	/**
	 * methode qui attribue au cimetière les cartes
	 * @return void
	 * @param carte
	 */
	public void setCimetiere(Carte carte) {
		this.cartes[this.nbCartes]=carte;
		
	}

	

}
