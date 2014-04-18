public class Cimetiere extends ListeDeCarte {

	public Cimetiere() {
		
		this.nbCartes=0;
		this.nbCartesMax=60;
		this.cartes= new Carte[nbCartesMax];
	}

	public void voirCimetiere() {

	}

	/**
	 * Renvoi vrai si le cimetiere est vide
	 */
	public boolean estVide() {
		return false;
	}

	/**
	 * Renvoin la liste de carte dans le cimetiere
	 */
	public void afficherCartes(ListeDeCarte cartes) {

	}

}
