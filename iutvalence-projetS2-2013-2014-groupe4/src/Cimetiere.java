/**
 * @author antoine 
 * Classe représentant le cimetière de chaque joueur
 */
public class Cimetiere extends ListeDeCarte {

	/**
	 * le cimetière contient 0 cartes a sa création et peut contenir au maximum
	 * nbCartesMax cartes
	 */
	public Cimetiere() {

		this.nbCartes = 0;
		this.nbCartesMax = 60;
		this.cartes = new Carte[this.nbCartesMax];
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

	//TODO Verifier pour le return string
	/**
	 * Implemente le cas d'utilisation voir cimetiere
	 * @return String
	 */
	public void voirCimetiere() {
		if (this.estVide())
			return "Cimetiere vide";
		this.afficherCartes();
		this.finVoirCimetiere();

	}

	public void finVoirCimetiere() {

	}

}
