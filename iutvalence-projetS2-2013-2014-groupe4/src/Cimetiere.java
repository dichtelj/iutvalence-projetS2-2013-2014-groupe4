/**
 * @author antoine
 * Classe représentant le cimetière de chaque joueur
 */
public class Cimetiere extends ListeDeCarte {

	/**
	 * le cimetière contient 0 cartes a sa création et peut contenir au maximum nbCartesMax cartes
	 */
	public Cimetiere() {
		
		this.nbCartes=0;
		this.nbCartesMax=60;
		this.cartes= new Carte[this.nbCartesMax];
	}

	/**
	 * méthode qui affiche au joueur son cimetière
	 */
	public void voirCimetiere() {

	}

	/**
	 * Renvoi vrai si le cimetiere est vide
	 */
	public boolean estVide() {
		if (this.nbCartes==0)
			return true;
		return false;
	}

}
